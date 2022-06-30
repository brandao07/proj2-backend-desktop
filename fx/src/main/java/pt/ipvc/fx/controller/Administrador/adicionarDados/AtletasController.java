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
import javafx.stage.DirectoryChooser;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
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

public class AtletasController implements Initializable {

    //apagar os contratos e adicionar a modalidade

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected TextField peso;

    @FXML
    protected TextField altura;

    @FXML
    protected ComboBox nacionalidade;

    @FXML
    protected ChoiceBox posicao;

    @FXML
    protected ChoiceBox modalidades;

    @FXML
    protected ChoiceBox equipa;

    @FXML
    protected Label labelErro;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroData;

    @FXML
    protected ImageView erroModalidade;

    @FXML
    protected ImageView erroNacionalidade;

    @FXML
    protected ImageView erroPeso;

    @FXML
    protected ImageView erroAltura;

    @FXML
    protected ImageView erroGenero;

    @FXML
    protected ImageView erroPosicao;

    @FXML
    protected ImageView erroEquipa;

    @FXML
    public void escolherFoto(ActionEvent event){

        final DirectoryChooser dirchooser = new DirectoryChooser();
        dirchooser.setInitialDirectory(new File("fx/src/main/resources/pt/ipvc/fx/jogadores"));

        File file = dirchooser.showDialog(null);



        System.out.println(file.getAbsolutePath());

    }



    public boolean testar(){
        boolean validarNome = true;
        boolean validarData = true;
        boolean validarModalidade = true;
        boolean validarNacionalidade = true;
        boolean validarGenero = true;
        boolean validarPeso = true;
        boolean validarAltura = true;
        boolean validarPosicao = true;
        boolean validarEquipa = true;


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

        if (!ValidarInput.validarDataPicker(data.getValue())){
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarData = false;
        }
        else {
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(nacionalidade.getValue())){
            erroNacionalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarNacionalidade = false;
        }
        else {
            erroNacionalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())){
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidade = false;
        }
        else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(peso.getText()) || Float.parseFloat(peso.getText()) > 200){
            erroPeso.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPeso = false;
        }
        else {
            erroPeso.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(altura.getText()) || Float.parseFloat(altura.getText()) > 3.00){
            erroAltura.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarAltura = false;
            labelErro.setText("Altura Inválida.");
        }
        else {
            erroAltura.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(genero.getValue())){
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarGenero = false;
        }
        else {
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())){
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidade = false;
        }
        else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(equipa.getValue())){
            erroEquipa.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarEquipa = false;
        }
        else {
            erroEquipa.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(posicao.getValue())){
            erroPosicao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPosicao = false;
        }
        else {
            erroPosicao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }
        return validarNome && validarData && validarNacionalidade && validarAltura && validarPeso && validarPosicao
                && validarModalidade && validarGenero && validarEquipa;
    }


    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        if (testar()) {
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);
            AtletaBLL.criarAtleta(nome.getText(),
                    genero.getSelectionModel().getSelectedItem().toString(),
                    nacionalidade.getSelectionModel().getSelectedItem().toString(),
                    data.getValue(),
                    Double.parseDouble(peso.getText()) ,
                    Double.parseDouble(altura.getText()) ,
                    equipa.getSelectionModel().getSelectedItem().toString(),
                    posicao.getSelectionModel().getSelectedItem().toString(),
                    modalidades.getSelectionModel().getSelectedItem().toString());
            ControladorGlobal.adicionarAtleta();

            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-atleta.fxml", event);
        }
        labelErro.setText("Preencha todos os campos.");
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Atletas");

        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());

        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });



        Set<String> modalidade = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);
        genero.getItems().addAll(StringGeneros.generos());

        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        nacionalidade.getItems().addAll(paises);
        nacionalidade.setVisibleRowCount(11);

        modalidades.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                posicao.getItems().clear();
                equipa.getItems().clear();

                Modalidade modalidade = ModalidadeBLL.getModalidade((String) modalidades.getSelectionModel().getSelectedItem());

                for (Posicao p: modalidade.getPosicoes()
                ) {
                    posicao.getItems().add(p.getNome());
                }

                for (Equipa e: modalidade.getEquipas()
                ) {
                    equipa.getItems().add(e.getNome());
                }
            }
        });


    }
}
