package pt.ipvc.fx.controller.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class EquipasController {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField pais;

    @FXML
    protected TextField cidade;

    @FXML
    protected TextField contacto;

    @FXML
    protected TextField sigla;

    @FXML
    protected TextField associacao;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(pais.getText()) &&
                ValidarInput.validarString(cidade.getText()) &&
                ValidarInput.validarString(contacto.getText()) &&
                ValidarInput.validarString(sigla.getText()) &&
                ValidarInput.validarString(associacao.toString())) {
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
