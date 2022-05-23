package pt.ipvc.fx.controller.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.backend.bll.ArbitroBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class ArbitrosController {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField nacionalidade;

    @FXML
    protected TextField naturalidade;

    @FXML
    protected TextField categoria;

    @FXML
    protected TextField associacao;

    @FXML
    protected DatePicker data;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(nacionalidade.getText()) &&
                ValidarInput.validarString(naturalidade.getText()) &&
                ValidarInput.validarString(categoria.getText()) &&
                ValidarInput.validarString(associacao.getText()) &&
                ValidarInput.validarString(data.toString())) {
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
