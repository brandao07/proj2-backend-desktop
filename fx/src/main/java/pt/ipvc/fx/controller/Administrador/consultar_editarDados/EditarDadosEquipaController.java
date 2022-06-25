package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


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
        Equipa equipa = new Equipa();
        String nomeEquipa = nome.getPromptText();
        equipa.setNome(nomeEquipa);
        equipa.setSigla(sigla.getText());
        equipa.setContacto(contacto.getText());
        equipa.setDataFundacao(java.sql.Date.valueOf(data.getPromptText()));

        if (!nome.getText().isEmpty()){
            equipa.setNome(nome.getText());
            nomeEquipa = nome.getText();
        }

        if (!sigla.getText().isEmpty()){
            equipa.setSigla(sigla.getText());
        }

        if (!contacto.getText().isEmpty()){
            equipa.setContacto(contacto.getText());
        }

        if (data.getValue() != null){
            equipa.setDataFundacao(java.sql.Date.valueOf(data.getValue()));
        }

        equipa.setId(EquipasBLL.getEquipa(nome.getPromptText()).getId());
        equipa.setPais((String) pais.getValue());
        equipa.setCidade((String) cidade.getValue());

        EquipasBLL.updateEquipa(equipa);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-equipa.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        nome.setPromptText(ConsultarDadosEquipaController.equipaSceneConsultar);
        sigla.setPromptText(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getSigla());
        contacto.setPromptText(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getContacto());

        Date data_nascimento = EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getDataFundacao();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        pais.setValue(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getPais());

        cidade.setValue(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getCidade());


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
