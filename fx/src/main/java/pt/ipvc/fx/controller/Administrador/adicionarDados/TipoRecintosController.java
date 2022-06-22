package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class TipoRecintosController implements Initializable {

    @FXML
    protected TextField tipo;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(tipo.getText())) {
            TipoRecintoBLL.criarTipoRecinto(tipo.getText());
        }
        System.out.println("Campos Inválidos");

    }


    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.opcoesMenuAdicionarAdmin((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
    }
}
