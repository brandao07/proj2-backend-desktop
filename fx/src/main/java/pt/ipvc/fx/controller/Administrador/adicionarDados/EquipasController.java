package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class EquipasController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField sigla;

    @FXML
    protected TextField contacto;

    @FXML
    protected ComboBox pais;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ComboBox cidade;

    @FXML
    protected ChoiceBox associacao;

    @FXML
    protected DatePicker data;

    @FXML
    protected Label labelErro;

    @FXML
    protected Button confirmar;

    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroData;

    @FXML
    protected ImageView erroPais;

    @FXML
    protected ImageView erroCidade;

    @FXML
    protected ImageView erroAssociacao;

    @FXML
    protected ImageView erroSigla;

    @FXML
    protected ImageView erroContacto;

    public boolean testar(){
        boolean validarNome = true;
        boolean validarData = true;
        boolean validarPais = true;
        boolean validarCidade = true;
        boolean validarAssociacao = true;
        boolean validarSigla = true;
        boolean validarContacto = true;
        boolean validarModalidade = true;


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

        if (!ValidarInput.validarString(sigla.getText())){
            erroSigla.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarSigla = false;
        }
        else {
            erroSigla.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(contacto.getText())){
            erroContacto.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarContacto = false;
        }
        else {
            erroContacto.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(pais.getValue())){
            erroPais.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPais = false;
        }
        else {
            erroPais.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(cidade.getValue())){
            erroCidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarCidade = false;
        }
        else {
            erroCidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(associacao.getValue())){
            erroAssociacao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarAssociacao = false;
        }
        else {
            erroAssociacao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        return validarNome && validarData && validarPais && validarCidade && validarAssociacao && validarSigla
                && validarContacto && validarModalidade;
    }

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(contacto.getText()) &&
                ValidarInput.validarString(sigla.getText()) &&
                ValidarInput.validarString(associacao.toString()) &&
                ValidarInput.validarDataPicker(data.getValue()) &&
                ValidarInput.validarChoiceBox(pais) &&
                ValidarInput.validarChoiceBox(cidade)) {
            EquipasBLL.criarEquipa(nome.getText(), associacao.getValue().toString(), pais.getValue().toString(), cidade.getValue().toString(),
                    data.getValue(), sigla.getText(), contacto.getText());
            return;
        }

        labelErro.setText("Preencha todos os campos");
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Equipas");

        try {
            LocalRepository.paises_e_cidades();
            LocalRepository.associacoes_portuguesas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());

        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        ArrayList < String > lista_associacoes = new ArrayList < > ();
        for (String t: LocalRepository.getMapAssosiacoesPortuguesas().values()) {
            lista_associacoes.add(t);
        }
        Collections.sort(lista_associacoes);
        associacao.getItems().addAll(lista_associacoes);

        ArrayList paises = new ArrayList < > ();
        for (String p: LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(p))) {
                paises.add(p);
            }
        }
        Collections.sort(paises);
        pais.getItems().addAll(paises);
        pais.setVisibleRowCount(11);

        pais.valueProperty().addListener(new ChangeListener < String > () {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                cidade.getItems().clear();
                for (String p: LocalRepository.getMapCidadesPais().keySet()) {
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