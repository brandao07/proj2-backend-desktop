package pt.ipvc.fx.controller.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class AtletasController {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected TextField peso;

    @FXML
    protected TextField altura;

    @FXML
    protected TextField naturalidade;

    @FXML
    protected TextField nacionalidade;

    @FXML
    protected TextField posicao;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(nacionalidade.getText()) &&
                ValidarInput.validarString(naturalidade.getText()) &&
                ValidarInput.validarString(posicao.getText()) &&
                ValidarInput.validarString(genero.getText()) &&
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
        ControladorGlobal.chamaScene("adicionarDados/admin-adicionar-dados.fxml", event);
    }
}
