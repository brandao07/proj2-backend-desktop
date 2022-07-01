package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController  {
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
    private ImageView fundo;


    @FXML
    protected void btnEntrarClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String password_encriptada = Encrypt.encrypt(password);

        List<Utilizador> lista_utilizadores = new ArrayList<>();
        lista_utilizadores = UtilizadorBLL.getUtilizadores();

        for (Utilizador u : lista_utilizadores){
            if (u.getUsername().equals(username) && u.getPassword().equals(password_encriptada)){
                if (UtilizadorBLL.getUtilizador(username) instanceof Administrador){
                    UtilizadorBLL.setUserSession(UtilizadorBLL.getUtilizador(username));
                    ControladorGlobal.chamaScene("Administrador/admin-home-page.fxml", event);
                    return;
                }else{
                    UtilizadorBLL.setUserSession(UtilizadorBLL.getUtilizador(username));
                    ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
                    return;
                }
            }
        }
            System.out.println("Não entrou!!");
            labelErroLogin.setText("Credenciais Incorretas.");
    }

    @FXML
    protected void setLinkRegistar(ActionEvent event) {
        ControladorGlobal.chamaScene("registo-view.fxml", event);
    }

}
