package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class criarCompeticaoController implements Initializable {
    @FXML
    private TextField nomeCompeticao;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    private ChoiceBox genero;
    //FIXED carregar os generos para a choiceBox
    @FXML
    private ChoiceBox modalidade;
    //TODO: CAMPOS ja tens os dados

    //FIXED carregar as modalidades para a choiceBox
    //ModalidadeBLL.getModalidades()

    @FXML
    private Label invalidDados;


    public void seguinte(ActionEvent event) {
        if (ValidarInput.validarString(nomeCompeticao.getText())
                && ValidarInput.validarDataPicker(dataInicio.getValue())
                && ValidarInput.validarDataPicker(dataFim.getValue())
                && ValidarInput.validarChoiceBox(genero.getValue())
                && ValidarInput.validarChoiceBox(modalidade.getValue())) {
            return;
        }
        invalidDados.setText("Campos Inválidos");
        if (ValidarInput.validarDatas(dataInicio.getValue(), dataFim.getValue())) {
            return;
        }
        invalidDados.setText("Data Inicio com inicio posteior a Data Fim");
        //TODO: CAMPOS LÊ
        //FIXED verificar se o nome da competicao é único
        // O NOME DA COMPETICAO E SEMPRE UNICO NA BD
        //TODO: CAMPOS JA TENS O CRIAR COMPETICAO
        //FIXED CompeticaoBLL.criarCompeticao(nome, genero, dataInicio, dataFim, modalidade);
//        if(condicao){
//            ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml", event);
//            return;
//        }
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }

    public void adicionarDetalhes(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-detalhes.fxml", event);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Set<String> generos = new HashSet<>();
        generos.add("Masculino");
        generos.add("Feminino");
        generos.add("Misto");
        //TODO: CAMPOS adicionar generos a choicebox
    }
}
