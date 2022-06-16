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
        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Equipas", "Modalidades", "Prémios", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        itemPesquisar.setValue("Árbitros");
        itemPesquisar.setOnAction(actionEvent -> {
            ValidarInput.mudarPagConsultarEditarAdmin((String) itemPesquisar.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });


    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);

    }

    //TUDO: HUGO JA TENS PARA LISTAR TODOS ARBITROS
//        ArbitroBLL.getArbitros();
    //TUDO: HUGO JA TENS PARA OBTER Arbitro
//        Arbitro arbitro = ArbitroBLL.getArbitro(nome);
    //TUDO: HUGO JA TENS UPDATE
    //ArbitroBLL.updateArbitro(arbitro);


}
