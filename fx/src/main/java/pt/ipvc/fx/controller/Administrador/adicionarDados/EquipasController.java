package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ChangeListener;
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
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipasController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox<String> modalidades;

    @FXML
    protected Label labelErro;

    @FXML
    protected Button confirmar;

    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroModalidade;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ListView jogadores;

    @FXML
    protected ListView jogadorEscolhidos;


    public boolean testar() {
        boolean validarNome = true;
        boolean validarModalidades = true;

        if (!ValidarInput.validarString(nome.getText())) {
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            nome.setPromptText("Por favor introduza um nome.");
            validarNome = false;
        } else {
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())) {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidades = false;
        } else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }


        return validarNome && validarModalidades;
    }

    @FXML
    public void confirmar(ActionEvent event) {
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
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        List<Modalidade> modalidadeObjeto = ModalidadeBLL.getModalidades();
        System.out.println("testeeeee");
        List<String> nomeModalidades = new ArrayList<>();

        for (Modalidade md : modalidadeObjeto) {
            nomeModalidades.add(md.getNome());
        }

        System.out.println(nomeModalidades);

        modalidades.getItems().addAll(nomeModalidades);


        modalidades.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                List<Atleta> listaAtletas = AtletaBLL.atletasSemEquipa(modalidades.getValue());

                List<String> items = new ArrayList<>();

                for (Atleta a:
                     listaAtletas) {
                    System.out.println(a.getNome());
                }
            }
        });


    }

}