package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class AtletasController {

    //apagar os contratos e adicionar a modalidade

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected TextField peso;

    @FXML
    protected TextField altura;

    @FXML
    protected ChoiceBox naturalidade;

    @FXML
    protected ChoiceBox nacionalidade;

    @FXML
    protected ChoiceBox posicao;

    @FXML
    protected ChoiceBox modalidade;

    @FXML
    protected ChoiceBox associacao;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(data.toString()) &&
                ValidarInput.validarDouble(Double.parseDouble(peso.getText())) &&
                ValidarInput.validarDouble(Double.parseDouble(altura.getText())))  {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("adicionarDados/admin-sistema-adicionar-user.fxml", event);
    }
}
