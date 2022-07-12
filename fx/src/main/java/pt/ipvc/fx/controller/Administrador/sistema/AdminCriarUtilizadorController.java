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
import pt.ipvc.backend.services.util.Encrypt;
import pt.ipvc.backend.services.util.PasswordGenerator;
import pt.ipvc.fx.controller.ControladorGlobal;
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

    }

    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarChoiceBox(tipo) &&
                ValidarInput.validarString(nome.getText())) {
            String tipo_user = (String) tipo.getValue();
            String password_gerada;

            List<Utilizador> lista_utilizadores = new ArrayList<>();
            lista_utilizadores = UtilizadorBLL.getUtilizadores();

            for (Utilizador u : lista_utilizadores) {
                if (u.getUsername().equals(nome.getText())) {
                    System.out.println("username não disponível");
                    return;
                }
            }

            if (tipo_user.equals("Administrador")) {
                password_gerada = PasswordGenerator.generatePassword(10);
                AdministradorBLL.criarAdministrador(nome.getText(), password_gerada);
            } else {
                GestorBLL.criarGestor(nome.getText());
                }
            }

        ControladorGlobal.criarUtilizador();
        ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-utilizadores.fxml", event);

    }
}
