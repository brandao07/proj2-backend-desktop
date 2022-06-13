package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class adicionarProvaController implements Initializable {
    @FXML
    private ChoiceBox equipaCasa;

    @FXML
    private ChoiceBox equipaFora;

    @FXML
    private ChoiceBox recinto;

    @FXML
    private DatePicker data;

    @FXML
    private Label invalidDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //TODO: CAMPOS JA TENS OS DADOS
        //FIXED listar as equipas
        // EquipasBLL.getEquipas()
        //FIXED listar os recintos
        // RecintoBLL.getRecintos()
    }

    public void confirmarContinuar(ActionEvent event) {
        if (ValidarInput.validarChoiceBox(equipaCasa.getValue())
                && ValidarInput.validarChoiceBox(equipaFora.getValue())
                && ValidarInput.validarChoiceBox(recinto.getValue())
                && ValidarInput.validarDataPicker(data.getValue())) {
            return;
        }
        invalidDados.setText("Campos Inválidos");
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }

    public void confirmarSair(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

}
