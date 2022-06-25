package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.models.ProvaNomeEquipas;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ProvaBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class GerirProvaController implements Initializable {
    public static ProvaNomeEquipas aux;

    @FXML
    private TableView<ProvaNomeEquipas> provas;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colData;

    @FXML
    private  TableColumn<ProvaNomeEquipas, String> colResultadoEquipaCasa;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colResultadoEquipaFora;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colArbitro;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colEquipaCasa;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colEquipaFora;

    @FXML
    private TableColumn<ProvaNomeEquipas, String> colRecinto;

    @FXML
    private Label nomeCompeticao;

    @FXML
    private Label invalidData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        provas.getColumns().clear();

        ObservableList<ProvaNomeEquipas> dados = FXCollections.observableArrayList(ProvaBLL.getProvaEquipasNome(GerirCompeticaoController.id));

        colResultadoEquipaCasa.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("resultadoEquipaCasa"));
        colData.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("dataInicio"));
        colRecinto.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("recinto"));
        colEquipaCasa.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("equipaCasa"));
        colEquipaFora.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("equipaFora"));
        colResultadoEquipaFora.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("resultadoEquipaFora"));
        colArbitro.setCellValueFactory(new PropertyValueFactory<ProvaNomeEquipas, String>("arbitro"));

        provas.setItems(dados);

        provas.getColumns().add(colEquipaCasa);
        provas.getColumns().add(colResultadoEquipaCasa);
        provas.getColumns().add(colEquipaFora);
        provas.getColumns().add(colResultadoEquipaFora);
        provas.getColumns().add(colRecinto);
        provas.getColumns().add(colData);
        provas.getColumns().add(colArbitro);

        nomeCompeticao.setText("Competição: " + GerirCompeticaoController.comp);
    }

    public void remover(ActionEvent event) {
        aux = provas.getSelectionModel().getSelectedItem();

        if (aux == null){
            invalidData.setText("Selecione uma Prova!");
            return;
        }
        ProvaBLL.removerProva(aux.getId());
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-prova.fxml", event);
    }

    public void editar(ActionEvent event) {
        aux = provas.getSelectionModel().getSelectedItem();

        if (aux == null){
            invalidData.setText("Selecione uma Prova!");
            return;
        }
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/editar-prova.fxml", event);
    }

    public void adiconar(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/criar-prova.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
