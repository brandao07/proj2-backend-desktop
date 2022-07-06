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
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.ClubeBLL;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    protected ImageView erroClube;


    @FXML
    protected ChoiceBox clube;

    @FXML
    protected ListView jogadores;

    @FXML
    protected ListView jogadorEscolhidos;

    private ObservableList<String> listaJogadoresEscolhidos;


    public boolean testar() {
        boolean validarNome = true;
        boolean validarModalidades = true;
        boolean validarClube = true;

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

        if (!ValidarInput.validarChoiceBox(clube.getValue())) {
            erroClube.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarClube = false;
        } else {
            erroClube.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())) {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidades = false;
        } else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }
        return validarNome && validarModalidades && validarClube;
    }

    @FXML
    public void confirmar(ActionEvent event) {
        if (testar()) {
            EquipasBLL.criarEquipa(nome.getText(), ClubeBLL.getClube((String) clube.getValue()), ModalidadeBLL.getModalidade(modalidades.getValue()));
            List<Atleta> jogadoresDaEquipa = new ArrayList<>();
            for (String player: listaJogadoresEscolhidos){
                jogadoresDaEquipa.add(AtletaBLL.getAtleta(player));
                Atleta atleta = AtletaBLL.getAtleta(player);
                atleta.setEquipa(EquipasBLL.getEquipa(nome.getText()));
                AtletaBLL.updateAtleta(atleta);
            }
            ControladorGlobal.adicionarClube();
            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-equipa.fxml", event);
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
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        List<Clube> listaClubes = ClubeBLL.getClubes();
        List<String> nomeClubes =   FXCollections.observableArrayList();

        for (Clube c : listaClubes){
            nomeClubes.add(c.getNome());;
        }

        clube.getItems().addAll(nomeClubes);

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
                ObservableList<String> items = FXCollections.observableArrayList();
                for (Atleta a: listaAtletas) {
                    System.out.println("ENNNNNNNNNNNNNNNNNNTREIIIIIIIIIIIIIIIIIII");
                    items.add(a.getNome());
                }
                jogadores.setItems(items);
            }
        });

        jogadores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



        modalidades.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                List<Atleta> listaAtletas = AtletaBLL.atletasSemEquipa(modalidades.getValue());
                ObservableList<String> items = FXCollections.observableArrayList();
                for (Atleta a:
                        listaAtletas) {
                    items.add(a.getNome());
                }
                jogadores.setItems(items);
            }
        });

        jogadores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                listaJogadoresEscolhidos = jogadorEscolhidos.getItems();
                if (!listaJogadoresEscolhidos.contains(jogadores.getSelectionModel().getSelectedItem())){
                    listaJogadoresEscolhidos.add((String) jogadores.getSelectionModel().getSelectedItem());
                }
                jogadorEscolhidos.setItems(listaJogadoresEscolhidos);
            }
        });

    }

    @FXML
    public void remove(ActionEvent event){
        listaJogadoresEscolhidos.remove(jogadorEscolhidos.getSelectionModel().getSelectedItem());
        jogadorEscolhidos.setItems(listaJogadoresEscolhidos);
    }


}