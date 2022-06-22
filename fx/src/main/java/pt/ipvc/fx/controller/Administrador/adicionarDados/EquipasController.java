package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

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
        try {
            LocalRepository.paises_e_cidades();
            LocalRepository.associacoes_portuguesas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());

        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.opcoesMenuAdicionarAdmin((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
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