package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminPageAdicionarController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxItemAdiconar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxItemAdiconar.getItems().add("Árbitros");
        choiceBoxItemAdiconar.getItems().add("Atletas");
        choiceBoxItemAdiconar.getItems().add("Contratos");
        choiceBoxItemAdiconar.getItems().add("Equipas");
        choiceBoxItemAdiconar.getItems().add("Modalidades");
        choiceBoxItemAdiconar.getItems().add("Recintos");
        choiceBoxItemAdiconar.getItems().add("Tipos de Divulgação");
        choiceBoxItemAdiconar.getItems().add("Tipos de Prémios");
        choiceBoxItemAdiconar.getItems().add("Tipos de Recintos");
    }

    @FXML
    public void seguinte(ActionEvent event) {
        if (!ValidarInput.validarString(choiceBoxItemAdiconar.getValue())) {
            System.out.println("Campo inválido!");
            return;
        }
        ValidarInput.validarAdicionarDados(choiceBoxItemAdiconar.getValue(), event);
    }

    @FXML
    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("admin-home-page.fxml", event);
    }


}
