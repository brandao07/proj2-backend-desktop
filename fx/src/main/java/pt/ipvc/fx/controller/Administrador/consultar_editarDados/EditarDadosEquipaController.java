package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.Administrador.adicionarDados.EquipasController;
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
    protected ChoiceBox clube;
    @FXML
    protected ComboBox modalidade;

    @FXML
    protected ListView<String> jogadores;

    @FXML
    protected ListView<String> jogadoresEquipa;

    @FXML
    protected Button button;

    private ObservableList<String> listaJogadoresEscolhidos;

    private List<String> jogadoresNaoEscolhidos;



    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Equipa equipa = new Equipa();
        equipa.setNome(nome.getPromptText());

        if (ValidarInput.validarString(nome.getText())){
            equipa.setNome(nome.getText());
        }

        for (String player: jogadoresNaoEscolhidos){
            if (!jogadoresNaoEscolhidos.isEmpty()){
                Atleta atleta = AtletaBLL.getAtleta(player);
                atleta.setEquipa(null);
                AtletaBLL.updateAtleta(atleta);
            }

        }


        for (String player: listaJogadoresEscolhidos){
            Atleta atleta = AtletaBLL.getAtleta(player);
            atleta.setEquipa(EquipasBLL.getEquipa(equipa.getNome()));
            AtletaBLL.updateAtleta(atleta);
          }

        equipa.setClube(ClubeBLL.getClube((String) clube.getValue()));
        equipa.setModalidade(ModalidadeBLL.getModalidade((String) modalidade.getValue()));
        equipa.setId(EquipasBLL.getEquipa(equipa.getNome()).getId());



        EquipasBLL.updateEquipa(equipa);
        ControladorGlobal.editarEquipa();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-equipa.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nome.setPromptText(ConsultarDadosEquipaController.equipaSceneConsultar);
        clube.setValue(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getNome());
        modalidade.setValue(EquipasBLL.getEquipa(ConsultarDadosEquipaController.equipaSceneConsultar).getModalidade().getNome());

        List<Equipa> listaEquipas = EquipasBLL.getEquipas();
        List<Atleta> listaAtletasEquipa = new ArrayList<>();
        List<Atleta> listaAtletas = AtletaBLL.getAtletas();


        for (Equipa eq: listaEquipas){
            if (eq.getNome().equals(nome.getPromptText())){
                listaAtletasEquipa.addAll(eq.getAtletas());
            }
        }

        for (Atleta at : listaAtletas){
            if ((!at.getModalidade().getNome().equals("Ténis")) && at.getEquipa() == (null)){
                jogadores.getItems().add(at.getNome());
            }
        }

        jogadoresNaoEscolhidos = jogadores.getItems();

        for (Atleta a: listaAtletasEquipa){
            jogadoresEquipa.getItems().add(a.getNome());
            listaJogadoresEscolhidos = FXCollections.observableArrayList(a.getNome());

        }

        jogadores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                listaJogadoresEscolhidos = jogadoresEquipa.getItems();
                if (!listaJogadoresEscolhidos.contains(jogadores.getSelectionModel().getSelectedItem())){
                    listaJogadoresEscolhidos.add(jogadores.getSelectionModel().getSelectedItem());
                }
                jogadoresEquipa.setItems(listaJogadoresEscolhidos);
            }
        });

    }

    @FXML
    public void remove(ActionEvent event){
        String jogador = jogadoresEquipa.getSelectionModel().getSelectedItem();
        if (!jogadores.getItems().contains(jogador)) jogadores.getItems().add(jogador);
        jogadoresEquipa.getItems().remove(jogador);
        listaJogadoresEscolhidos.remove(jogador);
        if (!jogadoresNaoEscolhidos.contains(jogador)) jogadoresNaoEscolhidos.add(jogador);

    }

}

