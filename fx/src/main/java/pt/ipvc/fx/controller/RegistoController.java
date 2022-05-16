package pt.ipvc.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.backend.bll.UtilizadorBLL;
import pt.ipvc.backend.entity.UtilizadoresEntity;

public class RegistoController {

    @FXML
    private Button btnRegistar;

    @FXML
    private Button btnTras;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

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
        String email = emailField.getText();
        String password_repetida = passwordField2.getText();


        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || password_repetida.isEmpty()){
            labelErro.setText("Por favor preencha todos os campos.");
            return;
        }
        if (!UtilizadorBLL.validarEmail(email)){
            labelErro.setText("Este email já existe");
            return;
        }
        if (!UtilizadorBLL.validarUsername(username)){
            labelErro.setText("Este username já existe");
            return;
        }
        if (!UtilizadorBLL.validarPasswords(password, password_repetida)){
            labelErro.setText("Palavras passes não coincidem");
           return;
        }
        if (!UtilizadorBLL.validarCaracteresPassword(password)){
            labelErro.setText("A password deve conter uma letra maiscula e um numero.");
            return;
        }
        if (!checkBoxTermos.isSelected()){
            labelErro.setText("Aceite os termos, para continuar.");
           return;
        }

        labelErro.setText("Utilizador Criado Com Sucesso");
        UtilizadoresEntity.create(username, password, email);

}

    @FXML
    protected void btnBackClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (UtilizadorBLL.validarLogin(username, password)) System.out.println("Entrou!!!");
    }
}
