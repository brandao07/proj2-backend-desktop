package pt.ipvc.fx.controller.Gestor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GestorHomePageController implements Initializable {

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
    private Label usernameLabel;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserSession(null);
        ControladorGlobal.chamaScene("login-view.fxml", event);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        competicoes.getColumns().clear();
        //atrinuir dados a tabela
        ObservableList<CompeticaoNomeModalidade> dados = FXCollections.observableArrayList(CompeticaoBLL.getCompeticoesActive());


        //atribuir o campo a cada coluna
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

    public void historico(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-historico.fxml", event);
    }

    public void homePage(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }


    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
