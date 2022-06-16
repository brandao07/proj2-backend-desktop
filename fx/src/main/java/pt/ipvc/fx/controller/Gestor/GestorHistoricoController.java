package pt.ipvc.fx.controller.Gestor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class GestorHistoricoController implements Initializable {
    @FXML
    private TableView historico;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: CAMPOS JA TENS OS DADOS
        //FIXED: listar as competiçoes criadas pelo gestor logado
        Set competicoes;
        if (UtilizadorBLL.getUserSession() instanceof Gestor)
            competicoes = ((Gestor) UtilizadorBLL.getUserSession()).getCompeticoes();
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
