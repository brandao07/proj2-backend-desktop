package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipvc.fx.misc.ValidarInput;

public class AdicionarUserController {

    @FXML
    private Button btnAdicionarDados;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    //TODO: HUGO JA TENS ADICIONAR ADMINISTRADOR
    //AdministradorBLL.criarAdministrador("username", "password");

    //TODO: HUGO JA TENS ADICIONAR GESTOR
    // String password = GestorBLL.criarGestor(username);

}
