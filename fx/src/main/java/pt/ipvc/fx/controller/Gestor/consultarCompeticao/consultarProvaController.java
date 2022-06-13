package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ResourceBundle;

public class consultarProvaController implements Initializable {

    @FXML
    private TableView competicoes;

    @FXML
    private Label nomeCompeticao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO listar as provas da competicao selecionada na scene consultar-competicao

        nomeCompeticao.setText("Competição: ");
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/consultarCompeticao/admin-sistema-adicionar-user.fxml", event);
    }
}
