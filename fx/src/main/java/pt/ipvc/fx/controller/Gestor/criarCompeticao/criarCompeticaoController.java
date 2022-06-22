package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;
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
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class criarCompeticaoController implements Initializable {
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
        Set<String> modalidades = ((List<Modalidade>)ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidade.getItems().addAll(modalidades);
        genero.getItems().addAll(StringGeneros.generos());
        podio.getItems().addAll(PodiosAux.getPodios());
        podio.setDisable(true);
    }

    public void seguinte(ActionEvent event) {

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
                CompeticaoBLL.criarCompeticao(nomeCompeticao.getText(), genero.getValue(), dataInicio.getValue(),
                        dataFim.getValue(), ModalidadeBLL.getModalidade(modalidade.getSelectionModel().getSelectedItem()));
                for(int i = 0; i < Integer.parseInt(podio.getValue()); i++) {
                    Premio p = new Premio(i+1,null);
                    PremioBLL.criarPremio(p, nomeCompeticao.getText(), null);
                }
                compSelecionada = CompeticaoBLL.getCompeticao(nomeCompeticao.getText());
                ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml", event);
                return;
            }
            invalidDados1.setText("Selecione uma opção no Campo Pódio");

        } else if ((!checkBox.isSelected()) && (CompeticaoBLL.criarCompeticao(nomeCompeticao.getText(), genero.getValue(), dataInicio.getValue(),
                dataFim.getValue(), ModalidadeBLL.getModalidade(modalidade.getSelectionModel().getSelectedItem())) != null)){
            compSelecionada = CompeticaoBLL.getCompeticao(nomeCompeticao.getText());
            ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
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
