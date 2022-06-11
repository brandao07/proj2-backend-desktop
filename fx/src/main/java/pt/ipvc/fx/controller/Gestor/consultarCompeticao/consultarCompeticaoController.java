package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class consultarCompeticaoController implements Initializable {
    @FXML
    private ChoiceBox modalidade;

    @FXML
    private TableView competicoes;

    @FXML
    private Label pesquisaInvalida;

    @FXML
    private Label verDetalhesInvalido;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO listar as modalidades
        //TODO listar competicoes por modalidade
    }

    public void pesquisar (){
        if(ValidarInput.validarString(pesquisa.getText())){
            return;
        }
        pesquisaInvalida.setText("Insira um Campo para Pesquisar!");
    }

    public void verDetalhes (){
        verDetalhesInvalido.setText("Selecione uma Competição!");
    }

    public void anterior (ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

}
