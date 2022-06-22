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
    @FXML
    private ChoiceBox<String> modalidade;

    @FXML
    private TableView<CompeticaoNomeModalidade> competicoes;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colNome;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colDataInicio;

    @FXML
    private DatePicker teste;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colDataFim;

    @FXML
    private TableColumn<CompeticaoNomeModalidade, String> colGenero;

    @FXML
    private TableColumn<CompeticaoNomeModalidade,String> colModalidade;

    @FXML
    private Label pesquisaInvalida;

    @FXML
    private Label verDetalhesInvalido;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        competicoes.getColumns().clear();
        competicoes.setEditable(true);

        ObservableList<CompeticaoNomeModalidade> dados = FXCollections.observableArrayList(CompeticaoBLL.getCompeticoesModalidadeNome());

        colNome.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("nome"));

        colDataInicio.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("dataInicio"));
//        colDataInicio.setCellFactory(TextFieldTableCell.forTableColumn());

        colDataInicio.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompeticaoNomeModalidade, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CompeticaoNomeModalidade, String> event) {
                CompeticaoNomeModalidade competicao = event.getRowValue();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    competicao.setDataInicio(format.parse(event.getNewValue()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        colDataFim.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("dataFim"));
//        colDataFim.setCellFactory(TextFieldTableCell.forTableColumn());
//        colDataFim.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompeticaoNomeModalidade, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<CompeticaoNomeModalidade, String> event) {
//                CompeticaoNomeModalidade competicao = event.getRowValue();
//                competicao.setDataFim(Date.valueOf((event.getNewValue())));
//            }
//        });

        ObservableList<String> list = FXCollections.observableArrayList(StringGeneros.generos());

        colGenero.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("genero"));
        colGenero.setCellFactory(ComboBoxTableCell.forTableColumn(list));
        colGenero.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompeticaoNomeModalidade, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CompeticaoNomeModalidade, String> event) {
                CompeticaoNomeModalidade competicao = event.getRowValue();
                competicao.setGenero(event.getNewValue());
            }
        });


        List<String> ob = new ArrayList<>();
        List<Modalidade> modalidades = ModalidadeBLL.getModalidades();
        for(Modalidade m : modalidades){
            ob.add(m.getNome());
        }

        ObservableList<String> teste = FXCollections.observableArrayList(ob);

        colModalidade.setCellValueFactory(new PropertyValueFactory<CompeticaoNomeModalidade, String>("modalidade"));
        colModalidade.setCellFactory(ComboBoxTableCell.forTableColumn(teste));
        colModalidade.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompeticaoNomeModalidade, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CompeticaoNomeModalidade, String> event) {
                CompeticaoNomeModalidade competicao = event.getRowValue();
                competicao.setModalidade(event.getNewValue());
            }
        });

        competicoes.setItems(dados);

        competicoes.getColumns().add(colNome);
        competicoes.getColumns().add(colDataInicio);
        competicoes.getColumns().add(colDataFim);
        competicoes.getColumns().add(colGenero);
        competicoes.getColumns().add(colModalidade);

        //TODO: CAMPOS implementar os metodos abaixo

        // FIXED listar as modalidades
        // ModalidadeBLL.getModalidades()
        // FIXED listar competicoes por modalidade
        // CompeticaoBLL.getCompeticoesModalidade(string);

    }

    public void pesquisar() {
        if (ValidarInput.validarString(pesquisa.getText())) {
            return;
        }
        pesquisaInvalida.setText("Insira um Campo para Pesquisar!");
    }

    public void editar() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        d = competicoes.getSelectionModel().getSelectedItem().getDataInicio();
        teste.setValue(Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        Competicao p = CompeticaoBLL.getCompeticao(competicoes.getSelectionModel().getSelectedItem().getNome());
        p.setDataInicio(java.sql.Date.valueOf(teste.getValue()));
    }

    public void verDetalhes() {
        verDetalhesInvalido.setText("Selecione uma Competição!");
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
}
