package pt.ipvc.fx.controller.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class RecintosController {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField pais;

    @FXML
    protected TextField capacidade;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
            ValidarInput.validarString(pais.getText()) &&
            ValidarInput.validarInt(Integer.parseInt(capacidade.getText()))) {
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
