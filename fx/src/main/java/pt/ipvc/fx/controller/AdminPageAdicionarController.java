package pt.ipvc.fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminPageAdicionarController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxItemAdiconar;

    @FXML
    private Button btnAdicionarDados;

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

        if (choiceBoxItemAdiconar.getValue().equals("Árbitros")){

        }

    }



}
