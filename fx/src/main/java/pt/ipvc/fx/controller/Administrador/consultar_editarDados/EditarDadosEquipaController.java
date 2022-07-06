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


public class EditarDadosEquipaController implements Initializable {
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
        clube.setNome(nome.getPromptText());
        clube.setSigla(sigla.getPromptText());
        clube.setContacto(contacto.getPromptText());
        clube.setDataFundacao(java.sql.Date.valueOf(data.getPromptText()));

        if (!ValidarInput.validarString(nome.getText())){
            clube.setNome(nome.getText());
        }

        if (!ValidarInput.validarString(sigla.getText())){
            clube.setSigla(sigla.getText());
        }

        if (!ValidarInput.validarString(contacto.getText())){
            clube.setContacto(contacto.getText());
        }

        if (!ValidarInput.validarDataPicker(data.getValue())){
            clube.setDataFundacao(java.sql.Date.valueOf(data.getValue()));
        }

        clube.setId(ClubeBLL.getClube(nome.getPromptText()).getId());
        clube.setPais(pais.getValue());
        clube.setCidade(cidade.getValue());

        ClubeBLL.updateClube(clube);
        ControladorGlobal.editarEquipa();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-clube.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        nome.setPromptText(ConsultarDadosEquipaController.clubeSceneConsultar);
        //sigla.setPromptText(ClubeBLL.getClube(ConsultarDadosEquipaController.clubeSceneConsultar).getSigla());
        contacto.setPromptText(ClubeBLL.getClube(ConsultarDadosEquipaController.clubeSceneConsultar).getContacto());

        Date data_nascimento = ClubeBLL.getClube(ConsultarDadosEquipaController.clubeSceneConsultar).getDataFundacao();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        pais.setValue(ClubeBLL.getClube(ConsultarDadosEquipaController.clubeSceneConsultar).getPais());

        cidade.setValue(ClubeBLL.getClube(ConsultarDadosEquipaController.clubeSceneConsultar).getCidade());


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
