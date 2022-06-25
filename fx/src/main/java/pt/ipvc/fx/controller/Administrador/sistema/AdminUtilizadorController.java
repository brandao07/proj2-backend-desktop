package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
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
    protected TableView<Utilizador> tabelaUtilizadores;

    @FXML
    protected TableColumn<Utilizador, String> colunaEmail;

    @FXML
    protected TableColumn<Utilizador, String> colunaTipo;
    @FXML
    protected TableColumn<Utilizador, String> colunaUsername;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabelaUtilizadores.getColumns().clear();
        ObservableList<Utilizador> dados = FXCollections.observableArrayList(UtilizadorBLL.getUtilizadores());

        colunaEmail.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("email"));
        colunaUsername.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("username"));
        //colunaTipo.setCellValueFactory(new PropertyValueFactory<Utilizador, String>("username"));

        tabelaUtilizadores.setItems(dados);

        tabelaUtilizadores.getColumns().add(colunaEmail);
        tabelaUtilizadores.getColumns().add(colunaUsername);
        //tabelaUtilizadores.getColumns().add(colunaTipo);

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
    //TODO: HUGO JA TENS ADICIONAR ADMINISTRADOR
    //AdministradorBLL.criarAdministrador("username", "password");

    //TODO: HUGO JA TENS ADICIONAR GESTOR
    // String password = GestorBLL.criarGestor(username);
}

