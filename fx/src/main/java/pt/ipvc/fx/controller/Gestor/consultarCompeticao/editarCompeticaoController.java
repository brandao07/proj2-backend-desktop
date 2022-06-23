package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.PodiosAux;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class editarCompeticaoController implements Initializable {
    public static Competicao compSelecionada;

    @FXML
    private TextField nomeCompeticao;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    private ChoiceBox<String> genero;
    //FIXED carregar os generos para a choiceBox
    @FXML
    private ChoiceBox<String> modalidade;

    @FXML
    private ChoiceBox<String> podio;

    @FXML
    private CheckBox checkBox;
    @FXML
    private Label invalidDados;

    @FXML
    private Label invalidDados1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Set<String> modalidades = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidade.getItems().addAll(modalidades);
        genero.getItems().addAll(StringGeneros.generos());
        podio.getItems().addAll(PodiosAux.getPodios());
        podio.setDisable(true);

        nomeCompeticao.setText(CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getNome());

        Date dI = CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getDataInicio();
        dataInicio.setValue(Instant.ofEpochMilli(dI.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

        Date dF = CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getDataFim();
        dataFim.setValue(Instant.ofEpochMilli(dF.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());


        genero.setValue(CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getGenero());
        modalidade.setValue(CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getModalidade().getNome());
    }

    public void confirmar(ActionEvent event) {
        Competicao competicao = new Competicao();
        competicao.setId(CompeticaoBLL.getCompeticao(consultarCompeticaoController.comp).getId());
        competicao.setNome(nomeCompeticao.getText());
        competicao.setDataInicio(java.sql.Date.valueOf(dataInicio.getValue()));
        competicao.setDataFim(java.sql.Date.valueOf(dataFim.getValue()));
        competicao.setModalidade(ModalidadeBLL.getModalidade(modalidade.getValue()));
        competicao.setGestor(null);
        competicao.setGenero(genero.getValue());

        if (!ValidarInput.validarString(nomeCompeticao.getText()))
            invalidDados.setText("Preencha o campo Nome");

        else if (!ValidarInput.validarDataPicker(dataInicio.getValue()))
            invalidDados.setText("Preencha o campo Data Início");

        else if (!ValidarInput.validarDataPicker(dataFim.getValue()))
            invalidDados.setText("Preencha o campo Data Fim");

        else if (!ValidarInput.validarChoiceBox(genero.getValue()))
            invalidDados.setText("Selecione uma opção no campo Genero");

        else if (!ValidarInput.validarChoiceBox(modalidade.getValue()))
            invalidDados.setText("Selecione uma opção no campo Modalidade");

        else if (!ValidarInput.validarDatas(dataInicio.getValue(), dataFim.getValue()))
            invalidDados.setText("Data Início com início posteior a Data Fim");

        else if (checkBox.isSelected()){
            if(ValidarInput.validarChoiceBox(podio.getValue())){
                CompeticaoBLL.updateCompeticao(competicao);
                for(int i = 0; i < Integer.parseInt(podio.getValue()); i++) {
                    Premio p = new Premio(i+1,null);
                    PremioBLL.criarPremio(p, nomeCompeticao.getText(), null);
                }
                compSelecionada = CompeticaoBLL.getCompeticao(nomeCompeticao.getText());
                ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml", event);
                return;
            }
            invalidDados1.setText("Selecione uma opção no Campo Pódio");

        } else if ((!checkBox.isSelected())){
            CompeticaoBLL.updateCompeticao(competicao);
            ControladorGlobal.chamaScene("Gestor/consultarCompeticao/consultar-competicao.fxml", event);
        }
        else
            invalidDados.setText("Nome da Competição já Utilizado!");
    }

    public void check(){
        podio.setDisable(!checkBox.isSelected());
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }


}
