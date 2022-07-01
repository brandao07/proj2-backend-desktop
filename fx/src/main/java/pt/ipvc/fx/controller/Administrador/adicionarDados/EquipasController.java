package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EquipasController implements Initializable {

    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroModalidade;

    @FXML
    protected TextField nome;

    @FXML
    protected ComboBox modalidade;

    @FXML
    protected ChoiceBox<String> choiceBoxOpcoes;

    @FXML
    protected ListView jogadores;

    @FXML
    protected ListView jogadoresEscolhidos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Equipas");
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        List<Atleta> listaJogadores = AtletaBLL.atletasSemEquipa((String) modalidade.getSelectionModel().getSelectedItem());
        ObservableList<String> items = FXCollections.observableArrayList();


        for (Atleta objeto: listaJogadores){
            if (!items.contains(objeto.getNome()))
                items.add(objeto.getNome());
        }

        jogadores.setItems(items);
        jogadores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
}
