package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditarDadosRecintoController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected ComboBox pais;
    @FXML
    protected Spinner capacidade;


    @FXML
    protected ListView<String> tiposRecinto;

    @FXML
    protected ListView<String> tiposRecintoEscolhidos;

    @FXML
    protected Label usernameLabel;

    @FXML
    public void remove(ActionEvent event){
        tiposRecintoEscolhidos.getItems().remove(tiposRecintoEscolhidos.getSelectionModel().getSelectedItem());
    }

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
        recinto.setCapacidade(Long.valueOf(capacidade.getPromptText()));

        if (ValidarInput.validarString(nome.getText())){
            System.out.println("entrou nome");
            recinto.setNome(nome.getText());
        }

        if (ValidarInput.validarString((String) capacidade.getValue())){
            System.out.println("entrou capacidade");
            recinto.setCapacidade((Long) capacidade.getValue());
        }

        if (!tiposRecintoEscolhidos.getItems().isEmpty()){
            System.out.println("entrou tipos");

            for (String n: tiposRecinto.getItems()){
                RecintoBLL.removeTipo(RecintoBLL.getRecinto(nome.getPromptText()), TipoRecintoBLL.getTipoRecinto(n));
            }

            for (String n: tiposRecintoEscolhidos.getItems()){
                RecintoBLL.addTipo(RecintoBLL.getRecinto(nome.getPromptText()), TipoRecintoBLL.getTipoRecinto(n));
            }
        }

        recinto.setId(RecintoBLL.getRecinto(nome.getPromptText()).getId());
        recinto.setPais((String) pais.getValue());
        RecintoBLL.updateRecinto(recinto);
        ControladorGlobal.editarRecinto();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-recinto.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        nome.setPromptText(ConsultarDadosRecintoController.recintoSceneConsultar);
        pais.setValue(RecintoBLL.getRecinto(ConsultarDadosRecintoController.recintoSceneConsultar).getPais());
        capacidade.setPromptText(String.valueOf(RecintoBLL.getRecinto(ConsultarDadosRecintoController.recintoSceneConsultar).getCapacidade()));

        List<TipoRecinto> listaTiposRecinto = TipoRecintoBLL.getTiposRecinto();
        ObservableList<String> items = FXCollections.observableArrayList();


        for (TipoRecinto objeto: listaTiposRecinto){
            if (!items.contains(objeto.getNome()))
                items.add(objeto.getNome());
        }

        tiposRecinto.setItems(items);
        tiposRecinto.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tiposRecinto.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                    ObservableList<String> selectedItems = tiposRecinto.getSelectionModel().getSelectedItems();
                    for (String name: selectedItems){
                        if (!tiposRecintoEscolhidos.getItems().contains(name)){
                            tiposRecintoEscolhidos.getItems().add(name);
                        }
                    }
                });

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
