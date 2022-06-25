package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class RecintosController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected ComboBox pais;

    @FXML
    protected TextField capacidade;

    @FXML
    protected ListView<String> tipo;

    @FXML
    protected ListView<String> tipoFinal;

    @FXML
    protected Label labelErro;

    @FXML
    protected Button remove;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    public void remove(ActionEvent event){
        tipoFinal.getItems().remove(tipoFinal.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarInt(Integer.parseInt(capacidade.getText())) &&
                ValidarInput.validarChoiceBox(pais.getValue()) &&
                ValidarInput.validarListView(tipoFinal.getSelectionModel().isEmpty())
        ) {
            Recinto recinto = new Recinto();
            recinto.setCapacidade(Long.valueOf(capacidade.getText()));
            recinto.setNome(nome.getText());
            recinto.setPais((String) pais.getValue());
            RecintoBLL.criarRecinto(recinto);
            recinto = RecintoBLL.getRecinto(nome.getText());

            for (String nome: tipoFinal.getItems()){
                RecintoBLL.addTipo(recinto, TipoRecintoBLL.getTipoRecinto(nome));
                RecintoBLL.updateRecinto(recinto);
            }
            System.out.println("Campos válidos");

        }else{
            labelErro.setText("Preencha todos os campos");
            System.out.println("Campos Inválidos");

        }

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Recintos");
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());


        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        List<TipoRecinto> listaTiposRecinto = TipoRecintoBLL.getTiposRecinto();
        ObservableList<String> items = FXCollections.observableArrayList();


        for (TipoRecinto objeto: listaTiposRecinto){
            if (!items.contains(objeto.getNome()))
                    items.add(objeto.getNome());
        }

        tipo.setItems(items);
        tipo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tipo.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                    ObservableList<String> selectedItems = tipo.getSelectionModel().getSelectedItems();
                        for (String name: selectedItems){
                            if (!tipoFinal.getItems().contains(name)){
                                tipoFinal.getItems().add(name);
                            }
                        }
                });


        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        pais.getItems().addAll(paises);
    }
}
