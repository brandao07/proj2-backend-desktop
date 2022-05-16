package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.backend.bll.AdministradorBLL;
import pt.ipvc.backend.bll.UtilizadorBLL;
import pt.ipvc.backend.entity.AdministradoresEntity;

public class LoginController {

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink linkPassword;

    @FXML
    private Hyperlink linkRegistar;

    @FXML
    private Label labelErroLogin;

    @FXML
    protected void btnEntrarClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (UtilizadorBLL.validarLogin(username, password)) System.out.println("Entrou!!!");
        else{
            System.out.println("Não entrou!!");
            labelErroLogin.setText("Credenciais Incorretas.");
            return;
        }
        if (AdministradorBLL.existeAdministrador(username)){
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("conaaaaa");

    }

    @FXML
    protected void setLinkRegistar(ActionEvent event) {
        ControladorGlobal.chamaScene("registo-view.fxml", event);
    }
}
