package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

public class EquipasController {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField sigla;

    @FXML
    protected TextField contacto;

    @FXML
    protected ChoiceBox pais;

    @FXML
    protected ChoiceBox cidade;

    @FXML
    protected ChoiceBox associacao;

    @FXML
    protected DatePicker data;

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(contacto.getText()) &&
                ValidarInput.validarString(sigla.getText()) &&
                ValidarInput.validarString(associacao.toString())) {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event) {
        ControladorGlobal.chamaScene("adicionarDados/admin-sistema-adicionar-user.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event){
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
