package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.models.UserTypeModel;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUtilizadorController implements Initializable {

    @FXML
    private Button btnAdicionarDados;

    @FXML
    protected Hyperlink adicionar;

    @FXML
    protected TableView<UserTypeModel> tabelaUtilizadores;

    @FXML
    protected TableColumn<UserTypeModel, String> colunaEmail;

    @FXML
    protected TableColumn<UserTypeModel, String> colunaTipo;
    @FXML
    protected TableColumn<UserTypeModel, String> colunaUsername;

    @FXML
    protected TextField pesquisa;

    ObservableList<UserTypeModel> dados;

    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabelaUtilizadores.setItems(FXCollections.observableArrayList(UtilizadorBLL.getUtilizadores()));

            return;
        }
        tabelaUtilizadores.setItems(FXCollections.observableArrayList(UtilizadorBLL.findUserTypeModel(pesquisa.getText())));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }
        tabelaUtilizadores.getColumns().clear();
        dados = FXCollections.observableArrayList(UtilizadorBLL.findUserTypes());

        colunaEmail.setCellValueFactory(new PropertyValueFactory<UserTypeModel, String>("email"));
        colunaUsername.setCellValueFactory(new PropertyValueFactory<UserTypeModel, String>("username"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<UserTypeModel, String>("type"));

        tabelaUtilizadores.setItems(dados);

        tabelaUtilizadores.getColumns().add(colunaEmail);
        tabelaUtilizadores.getColumns().add(colunaUsername);
        tabelaUtilizadores.getColumns().add(colunaTipo);

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }


    public void setAdicionar(ActionEvent event){
        ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-criar-utilizador.fxml", event);
    }

}

