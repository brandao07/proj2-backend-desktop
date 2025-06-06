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
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AtletasController implements Initializable {

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
    protected ComboBox naturalidade;

    @FXML
    protected ChoiceBox < String > posicao;

    @FXML
    protected ChoiceBox < String > modalidades;

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
    protected ImageView erroNaturalidade;

    @FXML
    protected ImageView erroPeso;

    @FXML
    protected ImageView erroAltura;

    @FXML
    protected ImageView erroGenero;

    @FXML
    protected ImageView erroPosicao;

    @FXML
    protected ImageView imagem;

    @FXML
    protected ImageView teste;

    @FXML
    protected Button btnFoto;

    private static String path;

    @FXML
    protected Label usernameLabel;

    @FXML
    public void escolherFoto(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("fx/src/main/resources/pt/ipvc/fx/modalidades/"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All filles", "*.*"));

        try {
            File  file = fileChooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            path = path.substring(path.indexOf("proj2/") + 1);
            path = path.substring(path.indexOf("/") + 1);

            imagem.setImage(new Image(new File(path).toURI().toString()));
            btnFoto.setText("");
        } catch (Exception e) {
            System.out.println("Selecione uma imagem.");
        }
    }

    public boolean testar() {
        boolean validarNome = true;
        boolean validarData = true;
        boolean validarModalidade = true;
        boolean validarNacionalidade = true;
        boolean validarNaturalidade = true;
        boolean validarGenero = true;
        boolean validarPeso = true;
        boolean validarAltura = true;
        boolean validarPosicao = true;
        boolean validarImagem = true;

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

        if (!ValidarInput.validarChoiceBox(modalidades.getValue())) {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidade = false;
        } else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(peso.getText()) || Float.parseFloat(peso.getText()) > 200) {
            erroPeso.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPeso = false;
        } else {
            erroPeso.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarString(altura.getText())) {
            erroAltura.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarAltura = false;
            labelErro.setText("Altura Inválida.");
        } else {
            erroAltura.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
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

        if (!ValidarInput.validarChoiceBox(posicao.getValue()) && !modalidades.getValue().equals("Ténis")) {
            erroPosicao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarPosicao = false;
        } if (ValidarInput.validarChoiceBox(posicao.getValue()) || modalidades.getValue().equals("Ténis")){
            erroPosicao.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (imagem.getImage() == null) {
            btnFoto.setTextFill(Color.web("#ff0000"));
            validarImagem = false;
        }

        return validarNome && validarData && validarNacionalidade && validarNaturalidade && validarAltura && validarPeso && validarPosicao &&
                validarModalidade && validarGenero && validarImagem;
    }

    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException, IOException {
        if (testar()) {
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);
            String posicao_excepcao;

            if (modalidades.getSelectionModel().getSelectedItem().equals("Ténis")){
                posicao_excepcao = null;
            }else{
                posicao_excepcao = posicao.getSelectionModel().getSelectedItem();
            }

            File fi = new File(path);
            byte[] fileContent = Files.readAllBytes(fi.toPath());

            AtletaBLL.criarAtleta(
                    nome.getText(),
                    genero.getSelectionModel().getSelectedItem().toString(),
                    nacionalidade.getSelectionModel().getSelectedItem().toString(),
                    naturalidade.getSelectionModel().getSelectedItem().toString(),
                    data.getValue(),
                    Double.parseDouble(peso.getText()),
                    Double.parseDouble(altura.getText()),
                    posicao_excepcao,
                    modalidades.getSelectionModel().getSelectedItem().toString(),
                    fileContent);

            Atleta atleta = AtletaBLL.getAtleta(nome.getText());
            atleta.setEquipa(null);

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
        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        naturalidade.setDisable(true);
        posicao.setDisable(true);

        choiceBoxOpcoes.setValue("Atletas");
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });

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

        modalidades.valueProperty().addListener(new ChangeListener < String > () {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (modalidades.getValue().equals("Ténis")){
                    posicao.setDisable(true);
                }else{
                    posicao.setDisable(false);
                }
                posicao.getItems().clear();

                List < Modalidade > listaModalidades = ModalidadeBLL.getModalidades();
                List < String > posicoesPretendidas = new ArrayList < > ();

                for (Modalidade md: listaModalidades) {
                    if (md.getNome().equals(modalidades.getSelectionModel().getSelectedItem()))
                        for (Posicao psc: md.getPosicoes()) {
                            posicoesPretendidas.add(psc.getNome());
                        }
                }

                posicao.getItems().addAll(posicoesPretendidas);
            }
        });
    }
}