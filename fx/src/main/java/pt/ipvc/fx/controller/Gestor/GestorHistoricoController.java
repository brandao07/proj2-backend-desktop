package pt.ipvc.fx.controller.Gestor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ResourceBundle;

public class GestorHistoricoController implements Initializable {
    @FXML
    private TableView historico;

    @FXML
    private Button anterior;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO listar as competiçoes criadas pelo gestor logado
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/gestor-home-page.fxml", event);
    }
}
