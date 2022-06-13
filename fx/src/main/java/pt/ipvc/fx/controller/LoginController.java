package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        UtilizadorBLL.setUserLog(UtilizadorBLL.utilizadorAtivo(username));

        if (UtilizadorBLL.validarLogin(username, password)) System.out.println("Entrou!!!");
        else {
            System.out.println("Não entrou!!");
            labelErroLogin.setText("Credenciais Incorretas.");
            return;
        }
        if (AdministradorBLL.existeAdministrador(username)) {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }


    }

    @FXML
    protected void setLinkRegistar(ActionEvent event) {
        ControladorGlobal.chamaScene("registo-view.fxml", event);
    }


}
