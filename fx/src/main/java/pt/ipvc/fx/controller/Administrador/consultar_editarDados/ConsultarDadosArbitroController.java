package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosArbitroController implements Initializable {

    public static long arbitroSceneConsultar;
    @FXML
    private ChoiceBox itemPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField pesquisa;

    @FXML
    private TableView tableArbitros;

    @FXML
    private Button btnDetalhes;

    @FXML
    protected TableView<ArbitroNomeModalidade> tabela;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaNome;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaData;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaGenero;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaNacionalidade;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaAssociacao;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaCategoria;

    @FXML
    protected TableColumn<ArbitroNomeModalidade, String> colunaModalidade;

    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabela.setItems(FXCollections.observableArrayList(ArbitroBLL.getArbitroNomeModalidade()));

            return;
        }
        tabela.setItems(FXCollections.observableArrayList(ArbitroBLL.getArbitrosNomePesquisa(pesquisa.getText())));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }

        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Equipas", "Modalidades", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        itemPesquisar.setValue("Árbitros");
        itemPesquisar.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) itemPesquisar.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabela.getColumns().clear();
        ObservableList<ArbitroNomeModalidade> dados = FXCollections.observableArrayList(ArbitroBLL.getArbitroNomeModalidade());

        colunaNome.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("dataNascimento"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("genero"));
        colunaNacionalidade.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("nacionalidade"));
        colunaAssociacao.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("associacao"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("categoria"));
        colunaModalidade.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("modalidade"));

        tabela.getColumns().add(colunaNome);
        tabela.getColumns().add(colunaData);
        tabela.getColumns().add(colunaGenero);
        tabela.getColumns().add(colunaNacionalidade);
        tabela.getColumns().add(colunaAssociacao);
        tabela.getColumns().add(colunaCategoria);
        tabela.getColumns().add(colunaModalidade);

        tabela.setItems(dados);

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        System.out.println(nome_scene);
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        ArbitroBLL.removerArbitroById(tabela.getSelectionModel().getSelectedItem().getId());
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);

    }

    public void setBtnEditar(ActionEvent event){
        System.out.printf("id:" + tabela.getSelectionModel().getSelectedItem().getId());
        arbitroSceneConsultar = tabela.getSelectionModel().getSelectedItem().getId();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-arbitro.fxml", event);
    }

}
