package pt.ipvc.fx.controller.Gestor.criarCompeticao;


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
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdicionarDetalhesController implements Initializable {
    public static String path;
    @FXML
    private TableView<Premio> tableView;

    @FXML
    private TableColumn<Premio,Integer> colunaPosicao;

    @FXML
    private TableColumn<Premio, String> colunaPremioAtribuido;

    @FXML
    private TableColumn<Premio, String> colunaTipoPremio;

    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getColumns().clear();
        tableView.setEditable(true);
        path = CompeticaoBLL.getCompeticao(CriarCompeticaoController.compSelecionada.getNome()).getNome();

        ObservableList<Premio> dados = FXCollections.observableArrayList(PremioBLL.getPremio(path));

        colunaPosicao.setCellValueFactory(new PropertyValueFactory<Premio, Integer>("lugar"));

        colunaPremioAtribuido.setCellValueFactory(new PropertyValueFactory<Premio, String>("valor"));
        colunaPremioAtribuido.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaPremioAtribuido.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Premio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Premio, String> event) {
                Premio premio = event.getRowValue();
                premio.setValor(event.getNewValue());
                PremioBLL.updatePremio(premio);
            }
        });

        List<TipoPremio> tiposPremio = TipoPremioBLL.getTiposPremio();
        List<String> nomes = new ArrayList<>();

        for(TipoPremio tp : tiposPremio){
            nomes.add(tp.getNome());
        }

        ObservableList<String> list = FXCollections.observableArrayList(nomes);

        colunaTipoPremio.setCellValueFactory(new PropertyValueFactory<Premio, String>("tipoPremio"));
        colunaTipoPremio.setCellFactory(ComboBoxTableCell.forTableColumn(list));
        colunaTipoPremio.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Premio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Premio, String> event) {
                Premio premio = event.getRowValue();
                premio.setTipoPremio(TipoPremioBLL.getTipoPremio(event.getNewValue()));
                PremioBLL.updatePremio(premio);
            }
        });

        tableView.setItems(dados);

        tableView.getColumns().add(colunaPosicao);
        tableView.getColumns().add(colunaTipoPremio);
        tableView.getColumns().add(colunaPremioAtribuido);
    }
    public void getTableViewValues(ActionEvent event) {

            for (Object r : this.tableView.getItems()) {
                for (Object c : this.tableView.getColumns()){
                    javafx.scene.control.TableColumn column = (javafx.scene.control.TableColumn) c;
                        if (column.getCellData(r) == null){
                            checkDados.setText("Preencha todos os campos da tabela");
                            return;
                        }
                }
            }
            ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

}
