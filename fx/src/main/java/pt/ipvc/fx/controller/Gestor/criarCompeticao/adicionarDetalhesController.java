package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ResourceBundle;

public class adicionarDetalhesController implements Initializable {

    @FXML
    private ChoiceBox tipoPremio;

    @FXML
    private ChoiceBox podio;

    @FXML
    private TableView valores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }
    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

}
