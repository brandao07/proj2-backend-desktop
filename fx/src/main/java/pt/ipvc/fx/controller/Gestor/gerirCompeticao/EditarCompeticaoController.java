package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.users.GestorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.PodiosAux;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class EditarCompeticaoController implements Initializable {

    @FXML
    private TextField nomeCompeticao;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    private ChoiceBox<String> genero;

    @FXML
    private ChoiceBox<String> modalidade;

    @FXML
    private Label invalidDados;

    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        Set<String> modalidades = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidade.getItems().addAll(modalidades);
        genero.getItems().addAll(StringGeneros.generos());

        //atribuir as campos da scene os valores da competicao que o user selecionou
        nomeCompeticao.setPromptText(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getNome());
        dataInicio.setPromptText(String.valueOf(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getDataInicio()));
        dataFim.setPromptText(String.valueOf(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getDataFim()));
        genero.setValue(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getGenero());
        modalidade.setValue(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getModalidade().getNome());
    }

    public void confirmar(ActionEvent event) {
        Competicao competicao = new Competicao();

        List<Competicao> competicoesList = CompeticaoBLL.getCompeticoes();

        competicao.setId(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getId());

        competicao.setNome(nomeCompeticao.getPromptText());
        //verificar o nome da competicao
        if(!(nomeCompeticao.getText().isEmpty())){
            for (Competicao c : competicoesList) {
                if (c.getNome().equals(nomeCompeticao.getText())){
                    invalidDados.setText("Nome de competição já existente!");
                    return;
                }
            }
            competicao.setNome(nomeCompeticao.getText());
        }
        //Verificar as datas
        if(dataInicio.getValue() != null || dataFim.getValue() != null) {
            if (dataInicio.getValue() != null && dataFim.getValue() != null) {
                if (!ValidarInput.validarDatas(dataInicio.getValue(), dataFim.getValue())) {
                    invalidDados.setText("Data Início com início posteior a Data Fim");
                    return;
                }
            } else if (dataInicio.getValue() != null) {
                if (!ValidarInput.validarDatas(dataInicio.getValue(), LocalDate.parse(dataFim.getPromptText()))) {
                    invalidDados.setText("Data Início com início posteior a Data Fim");
                    return;
                }
            } else if (dataFim.getValue() != null) {
                if (!ValidarInput.validarDatas(LocalDate.parse(dataInicio.getPromptText()), dataFim.getValue())) {
                    invalidDados.setText("Data Início com início posteior a Data Fim");
                    return;
                }
            }
        }
        //verificacoes dos restantes campos
        competicao.setDataInicio(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getDataInicio());
        if(dataInicio.getValue() != null)
            competicao.setDataInicio(java.sql.Date.valueOf(dataInicio.getValue()));

        competicao.setDataFim(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp).getDataFim());
        if(dataFim.getValue() != null)
            competicao.setDataFim(java.sql.Date.valueOf(dataFim.getValue()));

        competicao.setGenero(genero.getValue());
        if (!ValidarInput.validarChoiceBox(genero.getValue()))
            invalidDados.setText("Selecione uma opção no campo Genero");

        competicao.setGestor(GestorBLL.getGestor(UtilizadorBLL.getUserSession().getUsername()));

        competicao.setModalidade(ModalidadeBLL.getModalidade(modalidade.getValue()));
        if (!ValidarInput.validarChoiceBox(modalidade.getValue()))
            invalidDados.setText("Selecione uma opção no campo Modalidade");

        CompeticaoBLL.updateCompeticao(competicao);
        System.out.println(competicao);
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);

    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void homePage(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }
}
