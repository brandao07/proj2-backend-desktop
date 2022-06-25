package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.*;

public class GerirCompeticaoController implements Initializable {

    public static String comp;

    public static Long id;

    public static CompeticaoNomeModalidade aux;
    @FXML
    private TableView<CompeticaoNomeModalidade> competicoes;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colNome;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, Date> colDataInicio;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colDataFim;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colGenero;

    @FXML
    private TableColumn<CompeticaoNomeModalidade,String> colModalidade;

    @FXML
    private Label verDetalhesInvalido;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        competicoes.getColumns().clear();

        ObservableList<CompeticaoNomeModalidade> dados = FXCollections.observableArrayList(CompeticaoBLL.getCompeticoesModalidadeNome());

        colNome.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("nome"));

        colDataInicio.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, Date>("dataInicio"));

        colDataFim.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("dataFim"));

        colGenero.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("genero"));

        colModalidade.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("modalidade"));

        competicoes.setItems(dados);

        competicoes.getColumns().add(colNome);
        competicoes.getColumns().add(colDataInicio);
        competicoes.getColumns().add(colDataFim);
        competicoes.getColumns().add(colGenero);
        competicoes.getColumns().add(colModalidade);
    }

    public void pesquisar() {
        if (ValidarInput.validarString(pesquisa.getText())) {
            return;
        }
    }

    public void editar(ActionEvent event){
        aux = competicoes.getSelectionModel().getSelectedItem();

        if(aux == null){
            verDetalhesInvalido.setText("Selecione uma Competição");
            return;
        }
        comp = competicoes.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/editar-competicoes.fxml", event);
    }

    public void gerirProvas (ActionEvent event) {
        aux = competicoes.getSelectionModel().getSelectedItem();

        if (aux == null){
            verDetalhesInvalido.setText("Selecione uma Competição!");
            return;
        }
        id = competicoes.getSelectionModel().getSelectedItem().getId();
        comp = competicoes.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-prova.fxml", event);
    }

    public void gerirPremios(ActionEvent event) {
        aux = competicoes.getSelectionModel().getSelectedItem();

        if (aux == null){
            verDetalhesInvalido.setText("Selecione uma Competição!");
            return;
        }
        comp = competicoes.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-premios.fxml", event);
    }

    public void remover(ActionEvent event){
        aux = competicoes.getSelectionModel().getSelectedItem();

        if (aux == null){
            verDetalhesInvalido.setText("Selecione uma Competição!");
            return;
        }
        CompeticaoBLL.removerCompeticao(competicoes.getSelectionModel().getSelectedItem().getNome());
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
