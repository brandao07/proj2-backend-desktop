package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    protected ImageView erroNome;

    @FXML
    protected ImageView erroPais;

    @FXML
    protected ImageView erroCapacidade;

    public boolean testar(){
        boolean validarNome = true;
        boolean validarPais = true;
        boolean validarCapacidade = true;
        boolean validarTipoFinal = true;


        if (!ValidarInput.validarString(nome.getText())){
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            nome.setPromptText("Por favor introduza um nome.");
            validarNome = false;
        }
        else {
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(pais.getValue())){
            erroPais.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPais = false;
        }
        else {
            erroPais.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(capacidade.getText())){
            erroCapacidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarCapacidade = false;
        }
        else {
            erroCapacidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (tipoFinal.getItems().isEmpty()){
            labelErro.setText("Selecione pelo menos um tipo de recinto.");
            validarTipoFinal = false;
        }



        return validarNome && validarCapacidade && validarPais && validarTipoFinal;
    }
    @FXML
    public void remove(ActionEvent event){
        tipoFinal.getItems().remove(tipoFinal.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        if (testar()) {
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);
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

            ControladorGlobal.adicionarRecinto();

            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-recintos.fxml", event);
        }else{
            labelErro.setText("Preencha todos os campos.");
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
