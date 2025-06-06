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
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ArbitrosController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected ComboBox < String > nacionalidade;

    @FXML
    protected ComboBox < String > naturalidade;

    @FXML
    protected ChoiceBox < String > modalidades;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ChoiceBox < String > genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected Label labelErro;
    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroData;

    @FXML
    protected ImageView erroNaturalidade;

    @FXML
    protected ImageView erroNacionalidade;

    @FXML
    protected ImageView erroGenero;

    @FXML
    protected ImageView erroModalidade;

    @FXML
    protected Button confirma;

    @FXML
    protected Label usernameLabel;
    public boolean testar() {
        boolean validarNome = true;
        boolean validarData = true;
        boolean validarNacionalidade = true;
        boolean validarNaturalidade = true;
        boolean validarGenero = true;
        boolean validarModalidade = true;

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

        if (!ValidarInput.validarDataPicker(data.getValue())) {
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarData = false;
        } else {
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(nacionalidade.getValue())) {
            erroNacionalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarNacionalidade = false;
        } else {
            erroNacionalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(naturalidade.getValue())) {
            erroNaturalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarNaturalidade = false;
        } else {
            erroNaturalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(naturalidade.getValue())) {
            erroNaturalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarNaturalidade = false;
        } else {
            erroNaturalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(genero.getValue())) {
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarGenero = false;
        } else {
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())) {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidade = false;
        } else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }
        return validarNome && validarData && validarNacionalidade && validarNaturalidade &&
                validarModalidade && validarGenero;
    }

    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        if (testar()) {
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);

            ArbitroBLL.criarArbitro(nome.getText(),
                    data.getValue(),
                    genero.getValue(),
                    nacionalidade.getSelectionModel().getSelectedItem(),
                    naturalidade.getSelectionModel().getSelectedItem(),
                    modalidades.getSelectionModel().getSelectedItem());

            ControladorGlobal.adicionarArbitro();
            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);
        } else {
            labelErro.setText("Preencha todos os campos.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
            LocalRepository.associacoes_portuguesas();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());
        naturalidade.setDisable(true);
        choiceBoxOpcoes.setValue("Árbitros");
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

        ArrayList < String > lista_associacoes = new ArrayList < > ();
        for (String t: LocalRepository.getMapAssosiacoesPortuguesas().values()) {
            lista_associacoes.add(t);
        }
        Collections.sort(lista_associacoes);
        lista_associacoes.remove(0);

        Set < String > modalidade = ((List < Modalidade > ) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);
        genero.getItems().addAll(StringGeneros.generos());

        ArrayList paises = new ArrayList < > ();
        for (String pais: LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        nacionalidade.getItems().addAll(paises);
        nacionalidade.setVisibleRowCount(11);

        nacionalidade.valueProperty().addListener(new ChangeListener < String > () {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                naturalidade.setDisable(false);

                naturalidade.getItems().clear();
                for (String pais: LocalRepository.getMapCidadesPais().keySet()) {
                    if (nacionalidade.getSelectionModel().getSelectedItem().equals(pais)) {
                        naturalidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(pais));
                        break;
                    }
                }
                naturalidade.setVisibleRowCount(11);
            }
        });
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        System.out.println(nome_scene);
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}