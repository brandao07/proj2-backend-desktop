package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ClubeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;


public class EditarDadosClubeController implements Initializable {
    @FXML
    protected TextField nome;
    @FXML
    protected TextField sigla;

    @FXML
    protected TextField contacto;

    @FXML
    protected DatePicker data;

    @FXML
    protected ComboBox<String> pais;

    @FXML
    protected ComboBox<String> cidade;


    @FXML
    protected Button button;


    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Clube clube = new Clube();
        String nomeClube = nome.getPromptText();
        clube.setNome(nomeClube);
        clube.setSigla(sigla.getText());
        clube.setContacto(contacto.getText());
        clube.setDataFundacao(java.sql.Date.valueOf(data.getPromptText()));

        if (!nome.getText().isEmpty()){
            clube.setNome(nome.getText());
            nomeClube = nome.getText();
        }

        if (!sigla.getText().isEmpty()){
            clube.setSigla(sigla.getText());
        }

        if (!contacto.getText().isEmpty()){
            clube.setContacto(contacto.getText());
        }

        if (data.getValue() != null){
            clube.setDataFundacao(java.sql.Date.valueOf(data.getValue()));
        }

        clube.setId(ClubeBLL.getClube(nome.getPromptText()).getId());
        clube.setPais((String) pais.getValue());
        clube.setCidade((String) cidade.getValue());

        ClubeBLL.updateClube(clube);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-clube.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        nome.setPromptText(ConsultarDadosClubeController.clubeSceneConsultar);
        sigla.setPromptText(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getSigla());
        contacto.setPromptText(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getContacto());

        Date data_nascimento = ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getDataFundacao();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        pais.setValue(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getPais());

        cidade.setValue(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getCidade());


        //adicionar pais à choiceBox nacionalidade
        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        pais.getItems().addAll(paises);
        pais.setVisibleRowCount(11);


        pais.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                cidade.getItems().clear();
                for (String p : LocalRepository.getMapCidadesPais().keySet()) {
                    if (pais.getSelectionModel().getSelectedItem().equals(p)) {
                        cidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(p));
                        break;
                    }
                }
                cidade.setVisibleRowCount(11);
            }
        });
    }




}
