package pt.ipvc.fx.controller.Gestor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class GestorHomePageController implements Initializable {
    @FXML
    private TableView competicoes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: CAMPOS JA TENS VALORES!!
        //FIXED: listar as competiçoes que a data fim é superior a CurrentDate
        //CompeticaoBLL.getCompeticoesActive()
    }

    public void historico(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-historico.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
