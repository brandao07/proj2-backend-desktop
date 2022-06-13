package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ArbitrosController implements Initializable {

    //trocar a função pelo genero e adicionar a modalidade

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox nacionalidade;

    @FXML
    protected ChoiceBox naturalidade;

    @FXML
    protected ChoiceBox categoria;

    @FXML
    protected ChoiceBox modalidade;

    @FXML
    protected ChoiceBox associacao;

    @FXML
    protected ChoiceBox choiceBoxItemAdiconar;

    @FXML
    protected ChoiceBox genero;

    @FXML
    protected DatePicker data;

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(data.toString())) {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event) {
        ControladorGlobal.chamaScene("adicionarDados/admin-sistema-adicionar-user.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //adicionar pais à choiceBox nacionalidade
        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        nacionalidade.getItems().addAll(paises);


        //adicionar cidades à choiceBox naturalidade
        nacionalidade.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            naturalidade.getItems().clear();
            for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
                if (nacionalidade.getSelectionModel().getSelectedItem().equals(pais)) {
                    naturalidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(pais));
                    break;
                }
            }
        });


    }
}
