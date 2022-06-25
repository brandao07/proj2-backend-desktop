package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class EditarDadosModalidadeController implements Initializable {
    @FXML
    protected TextField nome;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Modalidade modalidade = new Modalidade();
        String nomeModalidade = nome.getPromptText();
        modalidade.setNome(nomeModalidade);

        if (!nome.getText().isEmpty()){
            modalidade.setNome(nome.getText());
            nomeModalidade = nome.getText();
        }

        modalidade.setId(ModalidadeBLL.getModalidade(nome.getPromptText()).getId());
        modalidade.setNome((String) nome.getText());

        ModalidadeBLL.updateModalidade(modalidade);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-modalidade.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nome.setPromptText(ConsultarDadosModalidadeController.modalidadeSceneConsultar);
    }




}
