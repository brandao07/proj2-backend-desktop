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
    protected Label labelErro;

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
    protected TableColumn<ArbitroNomeModalidade, String> colunaNaturalidade;

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
        labelErro.setText("");

        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }

        itemPesquisar.getItems().addAll("Árbitros", "Atletas", "Clubes", "Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
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
        colunaNaturalidade.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("naturalidade"));
        colunaModalidade.setCellValueFactory(new PropertyValueFactory<ArbitroNomeModalidade, String>("modalidade"));

        tabela.getColumns().add(colunaNome);
        tabela.getColumns().add(colunaData);
        tabela.getColumns().add(colunaGenero);
        tabela.getColumns().add(colunaNacionalidade);
        tabela.getColumns().add(colunaNaturalidade);
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
            try {
                ArbitroBLL.removerArbitroById(tabela.getSelectionModel().getSelectedItem().getId());
                ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);
            }catch (Exception e){
                System.out.println("Selecione um árbitro.");
                labelErro.setText("Selecione um árbitro.");
            }
    }

    public void setBtnEditar(ActionEvent event){
        try {
            arbitroSceneConsultar = tabela.getSelectionModel().getSelectedItem().getId();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-arbitro.fxml", event);
        }catch (Exception e){
            System.out.println("Selecione um árbitro.");
            labelErro.setText("Selecione um árbitro.");
        }


    }

}
