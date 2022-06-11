package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarEditarDadosArbitroController implements Initializable {
    @FXML
    private ChoiceBox itemPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField textFieldpesquisa;

    @FXML
    private TableView tableArbitros;

    @FXML
    private Button btnDetalhes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Equipa",  "Modalidade", "Prêmio", "Recintos", "Tipos de Recinto", "Tipos de Prêmio");

    }
}
