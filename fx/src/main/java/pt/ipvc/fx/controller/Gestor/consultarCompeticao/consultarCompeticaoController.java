package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.controller.Temp.Person;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class consultarCompeticaoController implements Initializable {
    @FXML
    private ChoiceBox modalidade;

    @FXML
    private TableView<Person> competicoes;

    @FXML
    private TableColumn<Person, String> colunaNome;

    @FXML
    private TableColumn<Person, String> colunaInicio;

    @FXML
    private TableColumn<Person, String> colunaFim;

    @FXML
    private TableColumn<Person, String> colunaGenero;

    @FXML
    private TableColumn<Person, String> colunaModalidade;

    @FXML
    private Label pesquisaInvalida;

    @FXML
    private Label verDetalhesInvalido;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        competicoes.getColumns().clear();
        colunaNome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
        colunaInicio.setCellValueFactory(new PropertyValueFactory<Person, String>("inicio"));
        colunaFim.setCellValueFactory(new PropertyValueFactory<Person, String>("fim"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<Person, String>("genero"));
        colunaModalidade.setCellValueFactory(new PropertyValueFactory<Person, String>("modalidade"));

        competicoes.getColumns().add(colunaNome);
        competicoes.getColumns().add(colunaInicio);
        competicoes.getColumns().add(colunaFim);
        competicoes.getColumns().add(colunaGenero);
        competicoes.getColumns().add(colunaModalidade);
        competicoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        competicoes.getItems().add(new Person("Teste", "10-10-2020", "20-12-2021", "M", "Futebol"));
        competicoes.getItems().add(new Person("Hugo", "10-06-2020", "20-05-2021", "F", "Puta"));

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
