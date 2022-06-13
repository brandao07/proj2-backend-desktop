package pt.ipvc.fx.controller.Gestor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.services.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;

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
}
