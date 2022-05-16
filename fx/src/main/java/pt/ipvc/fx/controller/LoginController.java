package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.backend.bll.UtilizadorBLL;

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
    protected void btnEntrarClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (UtilizadorBLL.validarLogin(username, password)) System.out.println("Entrou!!!");
        else{
            labelErroLogin.setText("Credenciais Incorretas.");
        }
    }

    @FXML
    protected void setLinkRegistar(ActionEvent event) {
        ControladorGlobal.chamaScene("registo-view.fxml", event);
    }
}
