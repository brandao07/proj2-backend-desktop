package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EditarDadosRecintoController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected ComboBox pais;

    @FXML
    protected Spinner capacidade;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Recinto recinto = new Recinto();
        String nomeRecinto = nome.getPromptText();
        recinto.setNome(nomeRecinto);
        recinto.setCapacidade((Long) capacidade.getValue());

        if (!nome.getText().isEmpty()){
            recinto.setNome(nome.getText());
            nomeRecinto = nome.getText();
        }

        if (capacidade.getValue() != null){
            recinto.setCapacidade((Long) capacidade.getValue());
        }

        recinto.setId(RecintoBLL.getRecinto(nome.getPromptText()).getId());
        recinto.setPais((String) pais.getValue());
        RecintoBLL.updateRecinto(recinto);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-recinto.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        nome.setPromptText(ConsultarDadosRecintoController.recintoSceneConsultar);
        pais.setValue(RecintoBLL.getRecinto(ConsultarDadosRecintoController.recintoSceneConsultar).getPais());
        capacidade.setPromptText(String.valueOf(RecintoBLL.getRecinto(ConsultarDadosRecintoController.recintoSceneConsultar).getCapacidade()));

        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        pais.getItems().addAll(paises);
        pais.setVisibleRowCount(11);

    }




}
