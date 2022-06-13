package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class DivulgacaoController {

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox tipo;

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText())) {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event) {
        ControladorGlobal.chamaScene("adicionarDados/admin-sistema-adicionar-user.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event){
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
