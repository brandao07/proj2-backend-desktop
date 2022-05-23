package pt.ipvc.fx.controller.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class ContratosController implements Initializable {

    @FXML
    protected DatePicker dataFim;

    @FXML
    protected DatePicker dataInicio;

    @FXML
    protected ChoiceBox<String> idAtleta;

    @FXML
    protected ChoiceBox<String> idEquipa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void confirmar(ActionEvent event){
        //TODO: FALTA FAZER CHOICE BOX
        if (ValidarInput.validarString(dataFim.toString()) && ValidarInput.validarString(dataInicio.toString()))  {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("adicionarDados/admin-adicionar-dados.fxml", event);
    }

}
