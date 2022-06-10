package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


import java.net.URL;
import java.util.ResourceBundle;


public class Consultar_Editar_Dados_Controller implements Initializable {
    @FXML
    private ChoiceBox itemPesquisar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Equipa",  "Modalidade", "Prêmio", "Recintos", "Tipos de Recinto", "Tipos de Prêmio");

    }
}
