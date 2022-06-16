package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
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
        //FIXED LISTAR TODOS ARBITROS
        // ArbitroBLL.getArbitros()
    }

    public void confirmarContinuar(ActionEvent event) {
        if (ValidarInput.validarChoiceBox(equipaCasa.getValue())
                && ValidarInput.validarChoiceBox(equipaFora.getValue())
                && ValidarInput.validarChoiceBox(recinto.getValue())
                && ValidarInput.validarDataPicker(data.getValue())) {
            return;
        }
        invalidDados.setText("Campos Inválidos");
        //TODO: CAMPOS JA TENS CRIAR PROVA FEITO
//        ProvaBLL.criarProva(data,competicao,equipaCasa, equipaFora, recinto, arbitro);
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }

    public void confirmarSair(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

    public void setBtnNavMenu(@NotNull ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

}
