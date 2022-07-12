package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.PodiosAux;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CriarCompeticaoController implements Initializable {
    public static Competicao compSelecionada;

    @FXML
    private TextField nomeCompeticao;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    private ChoiceBox<String> genero;

    @FXML
    private ChoiceBox<String> modalidade;

    @FXML
    private ChoiceBox<String> podio;

    @FXML
    private CheckBox checkBox;
    @FXML
    private Label invalidDados;

    @FXML
    private Label invalidDados1;


    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView erroNome;

    @FXML
    private ImageView erroDataInicio;

    @FXML
    private ImageView erroDataFim;

    @FXML
    private ImageView erroGenero;

    @FXML
    private ImageView erroModalidade;

    @FXML
    private ImageView erroPodio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        Set<String> modalidades = ((List<Modalidade>)ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidade.getItems().addAll(modalidades);
        genero.getItems().addAll(StringGeneros.generos());
        podio.getItems().addAll(PodiosAux.getPodios());
        podio.setDisable(true);
    }

    public void seguinte(ActionEvent event) throws InterruptedException {
        boolean controlo = true;
        //restricoes para criar prova
        if (!ValidarInput.validarString(nomeCompeticao.getText())) {
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            invalidDados.setText("Preencha o campo Nome");
            controlo = false;
        } else {
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        List<Competicao> competicoesList = CompeticaoBLL.getCompeticoes();

        for (Competicao c: competicoesList) {
            if(c.getNome().equals(nomeCompeticao.getText())){
                invalidDados.setText("Nome da Competição já Utilizado!");
                erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                controlo = false;
            } else {
                erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
            }
        }

        if (!ValidarInput.validarDataPicker(dataInicio.getValue())) {
            erroDataInicio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            invalidDados.setText("Preencha o campo Data Início");
            controlo = false;
        } else {
            erroDataInicio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarDataPicker(dataFim.getValue())){
            erroDataFim.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            invalidDados.setText("Preencha o campo Data Fim");
            controlo = false;
        } else {
            erroDataFim.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(genero.getValue())){
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            invalidDados.setText("Selecione uma opção no campo Genero");
            controlo = false;
        } else {
            erroGenero.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(modalidade.getValue())){
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            invalidDados.setText("Selecione uma opção no campo Modalidade");
            controlo = false;
        } else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if(dataInicio.getValue() != null && dataFim.getValue() != null){
            if (!ValidarInput.validarDatas(dataInicio.getValue(), dataFim.getValue())){
                erroDataInicio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                erroDataFim.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                invalidDados.setText("Data Início com início posteior a Data Fim");
                controlo = false;
            } else {
                erroDataInicio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
                erroDataFim.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
            }
        }

        //se a choicebox estiver selecionada faz isto
        if (checkBox.isSelected()){
            if(!ValidarInput.validarChoiceBox(podio.getValue())){
                erroPodio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                invalidDados1.setText("Selecione uma opção no Campo Pódio");
                controlo = false;
            }

            if (!controlo){
                return;
            }

            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));

            CompeticaoBLL.criarCompeticao(nomeCompeticao.getText(), genero.getValue(), dataInicio.getValue(),
                    dataFim.getValue(), ModalidadeBLL.getModalidade(modalidade.getValue()));

            //atribuir os varios podios a base de dados
            for(int i = 0; i < Integer.parseInt(podio.getValue()); i++) {
                Premio p = new Premio(i+1,null);
                PremioBLL.criarPremio(p, nomeCompeticao.getText(), null);
            }

            erroPodio.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
            compSelecionada = CompeticaoBLL.getCompeticao(nomeCompeticao.getText());
            ControladorGlobal.criarCompeticao();
            ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml", event);
        }

        if (!controlo){
            return;
        }

        if(!checkBox.isSelected()){
            compSelecionada = CompeticaoBLL.getCompeticao(nomeCompeticao.getText());
            CompeticaoBLL.criarCompeticao(nomeCompeticao.getText(), genero.getValue(), dataInicio.getValue(),
                    dataFim.getValue(), ModalidadeBLL.getModalidade(modalidade.getValue()));
            ControladorGlobal.criarCompeticao();
            ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
        }

    }

    public void check(){
        podio.setDisable(!checkBox.isSelected());
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void homePage(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }
}
