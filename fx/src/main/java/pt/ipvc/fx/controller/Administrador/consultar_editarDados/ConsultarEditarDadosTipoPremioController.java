package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarEditarDadosTipoPremioController implements Initializable {
    @FXML
    private ChoiceBox itemPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField textFieldpesquisa;

    @FXML
    private TableView tableTipoPremios;

    @FXML
    private Button btnDetalhes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Equipas",  "Modalidades", "Prémios", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        itemPesquisar.setValue("Tipos de Prémios");
        itemPesquisar.setOnAction(actionEvent -> {
            ValidarInput.mudarPagConsultarEditarAdmin((String) itemPesquisar.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
    }

}
