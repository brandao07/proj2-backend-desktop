package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SetPassword implements Initializable {

    @FXML
    protected TextField username;

    @FXML
    protected Label labelErro;

    @FXML
    protected PasswordField password1;

    @FXML
    protected PasswordField password2;

    public boolean testar() {
        boolean validarPass1 = true;
        boolean validarPass2 = true;


        if (!ValidarInput.validarString(password1.getText())) {
            password1.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            validarPass1 = false;
        } else {
            password1.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
        }

        if (!ValidarInput.validarString(password2.getText())) {
            password2.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            validarPass2 = false;
        } else {
            password2.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
        }


        return validarPass1 && validarPass2;
    }

    @FXML
    public void confirmar(ActionEvent event) {
        if (testar()){
            Utilizador utilizador = new Utilizador();
            utilizador.setUsername(username.getText());
            utilizador.setEmail(UtilizadorBLL.getUtilizador(username.getText()).getEmail());
            utilizador.setDataCriacao(UtilizadorBLL.getUtilizador(username.getText()).getDataCriacao());
            utilizador.setId(UtilizadorBLL.getUtilizador(username.getText()).getId());
            utilizador.setPassword(Encrypt.encrypt(password1.getText()));
            UtilizadorBLL.updateUtilizador(utilizador);
            ControladorGlobal.editarPassword();
            ControladorGlobal.chamaScene("login-view.fxml", event);
        }
        else{
            labelErro.setText("Palavras passes não correspondem.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setDisable(true);
        username.setText(ForgotPassword.user);
    }
}
