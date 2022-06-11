package pt.ipvc.fx.controller.Gestor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ResourceBundle;

public class GestorHomePageController implements Initializable {
    @FXML
    private TableView competicoes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO listar as competiçoes que a data fim é superior a CurrentDate
    }

    public void historico (ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/gestor-historico.fxml", event);
    }
}
