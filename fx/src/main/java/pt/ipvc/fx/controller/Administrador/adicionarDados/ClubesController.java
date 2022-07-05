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
import javafx.stage.FileChooser;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ClubeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ClubesController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected TextField sigla;

    @FXML
    protected TextField contacto;

    @FXML
    protected ComboBox<String> pais;

    @FXML
    protected ChoiceBox<String> choiceBoxOpcoes;

    @FXML
    protected ComboBox<String> cidade;

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
    protected ImageView erroSigla;

    @FXML
    protected ImageView erroContacto;

    private static String path;

    @FXML
    protected ImageView imagem;

    @FXML
    protected Button btnFoto;

    public boolean testar(){
        boolean validarNome = true;
        boolean validarData = true;
        boolean validarPais = true;
        boolean validarCidade = true;
        boolean validarSigla = true;
        boolean validarContacto = true;
        boolean validarImagem = true;


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

        if (!ValidarInput.validarDataPicker(data.getValue())){
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarData = false;
        }
        else {
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(contacto.getText())){
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

        if (imagem.getImage() == null){
            btnFoto.setTextFill(Color.web("#ff0000"));
            validarImagem = false;
        }


        return validarNome && validarData && validarPais && validarCidade && validarSigla
                && validarContacto && validarImagem;
    }

    @FXML
    public void confirmar(ActionEvent event) {
        if (testar()) {
            ClubeBLL.criarClube(nome.getText(), sigla.getText(), pais.getValue(), cidade.getValue(),
                    data.getValue(),  contacto.getText(), path);

            ControladorGlobal.adicionarClube();

            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-clube.fxml", event);
        }

        labelErro.setText("Preencha todos os campos");
    }

    @FXML
    public void escolherFoto(ActionEvent event){

        final FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("fx/src/main/resources/pt/ipvc/fx/modalidades/"));

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All filles", "*.*"));

        File file = fileChooser.showOpenDialog(null);

        path = file.getAbsolutePath();
        path = path.substring(path.indexOf("proj2/") + 1);
        path = path.substring(path.indexOf("/") + 1);

        System.out.println(path);

        imagem.setImage(new Image(new File(path).toURI().toString()));

        btnFoto.setText("");

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Clubes");

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