package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.DatePickerSkin;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class consultarCompeticaoController implements Initializable {

    public static String comp;
    @FXML
    private TableView<CompeticaoNomeModalidade> competicoes;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colNome;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, Date> colDataInicio;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colDataFim;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colGenero;

    @FXML
    private TableColumn<CompeticaoNomeModalidade,String> colModalidade;

    @FXML
    private Label verDetalhesInvalido;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        competicoes.getColumns().clear();

        ObservableList<CompeticaoNomeModalidade> dados = FXCollections.observableArrayList(CompeticaoBLL.getCompeticoesModalidadeNome());

        colNome.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("nome"));

        colDataInicio.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, Date>("dataInicio"));

        colDataFim.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("dataFim"));

        colGenero.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("genero"));

        colModalidade.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("modalidade"));

        competicoes.setItems(dados);

        competicoes.getColumns().add(colNome);
        competicoes.getColumns().add(colDataInicio);
        competicoes.getColumns().add(colDataFim);
        competicoes.getColumns().add(colGenero);
        competicoes.getColumns().add(colModalidade);
    }

    public void pesquisar() {
        if (ValidarInput.validarString(pesquisa.getText())) {
            return;
        }
    }

    public void editar(ActionEvent event){
        comp = competicoes.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Gestor/consultarCompeticao/editar-competicoes.fxml", event);
    }

    public void verDetalhes() {
        verDetalhesInvalido.setText("Selecione uma Competição!");
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
