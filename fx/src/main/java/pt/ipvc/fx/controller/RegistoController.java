package pt.ipvc.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.validations.UtilizadorValidation;


public class RegistoController {

    @FXML
    private Button btnRegistar;

    @FXML
    private Button btnTras;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private CheckBox checkBoxTermos;

    @FXML
    private Label labelErro;

    @FXML
    protected void btnRegistarClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String password_repetida = passwordField2.getText();


        if (username.isEmpty() || password.isEmpty() || password_repetida.isEmpty()) {
            labelErro.setText("Por favor preencha todos os campos.");
            return;
        }
        if (UtilizadorBLL.getUtilizador(username) != null) {
            labelErro.setText("Este username já existe");
            return;
        }
        if (!UtilizadorValidation.passwordsMatch(password, password_repetida)) {
            labelErro.setText("Palavras passes não coincidem");
            return;
        }
        if (!UtilizadorValidation.caracteresPassword(password)) {
            labelErro.setText("A password deve conter uma letra maiscula e um numero.");
            return;
        }
        if (!checkBoxTermos.isSelected()) {
            labelErro.setText("Aceite os termos, para continuar.");
            return;
        }
        labelErro.setText("Utilizador Criado Com Sucesso");
        AdministradorBLL.criarAdministrador(username, password);
    }
}
