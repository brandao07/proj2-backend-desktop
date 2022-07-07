package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.Text;
import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.GestorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCriarUtilizadorController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox tipo;
    @FXML
    protected PasswordField password;

    @FXML
    protected Label label1_password;

    @FXML
    protected Label label2_password;

    @FXML
    protected PasswordField password1;

    @FXML
    protected Button confirmar;

    @FXML
    protected Label usernameLabel;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        tipo.getItems().addAll("Administrador", "Gestor");
        tipo.setValue("Administrador");

        tipo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (tipo.getValue().equals("Administrador")){
                    password.setDisable(false);
                    password1.setDisable(false);
                    label1_password.setDisable(false);
                    label2_password.setDisable(false);
                }else{
                    password.setDisable(true);
                    password1.setDisable(true);
                    label1_password.setDisable(true);
                    label2_password.setDisable(true);
                }
            }
        });
    }

    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarChoiceBox(tipo) &&
                ValidarInput.validarString(nome.getText())) {
            String tipo_user = (String) tipo.getValue();
            String password_gerada = "";

            List<Utilizador> lista_utilizadores = new ArrayList<>();
            lista_utilizadores = UtilizadorBLL.getUtilizadores();

            for (Utilizador u : lista_utilizadores) {
                if (u.getUsername().equals(nome.getText())) {
                    System.out.println("username não disponível");
                    return;
                }
            }

            if (tipo_user.equals("Administrador")) {
                AdministradorBLL.criarAdministrador(nome.getText(), password.getText());
                Utilizador utilizador = AdministradorBLL.getAdministrador(nome.getText());
            } else {
                GestorBLL.criarGestor(nome.getText());
                password_gerada = GestorBLL.criarGestor(nome.getText());
                Utilizador utilizador = GestorBLL.getGestor(nome.getText());
                }

            }



    }
}
