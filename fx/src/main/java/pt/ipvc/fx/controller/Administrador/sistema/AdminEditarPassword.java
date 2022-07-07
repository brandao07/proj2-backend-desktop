package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminEditarPassword implements Initializable {
    @FXML
    protected PasswordField password1;

    @FXML
    protected PasswordField password2;

    @FXML
    protected Label labelErro;

    @FXML
    protected Label usernameLabel;



    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        if (password1.getText().equals(password2.getText())){
            UtilizadorBLL.getUserSession().setPassword(Encrypt.encrypt(password1.getText()));
            UtilizadorBLL.updateUtilizador(UtilizadorBLL.getUserSession());
            ControladorGlobal.editarPassword();
            ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-utilizadores.fxml", event);
        }
        labelErro.setText("Palavras passes não coincidem.");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

    }
}
