package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ResourceBundle;

public class criarCompeticaoController implements Initializable {
    @FXML
    private TextField nomeCompeticao;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    private ChoiceBox genero;

    @FXML
    private ChoiceBox modalidade;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/.fxml", event);
    }

    public void adicionarDetalhes(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml.fxml", event);
    }
    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }
}
