package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;
import pt.ipvc.backend.servicos.Repositorio;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    protected DatePicker data;

    @FXML
    public void confirmar(ActionEvent event){
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(data.toString())) {
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        System.out.println("Campos Inválidos");
    }

    @FXML
    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("adicionarDados/admin-adicionar-dados.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Repositorio.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //adicionar pais à choiceBox nacionalidade
        for (String pais : Repositorio.getMapCidadesPais().keySet())
        {
            if (!nacionalidade.getItems().contains(Repositorio.getMapCidadesPais().get(pais))){
                nacionalidade.getItems().addAll(pais);
            }
        }



        nacionalidade.getSelectionModel().selectedIndexProperty()
                .addListener( (v, oldValue, newValue) -> {
                    System.out.println(v.toString());
                    for (String pais : Repositorio.getMapCidadesPais().keySet())
                    {


                        }

                });








    }
}
