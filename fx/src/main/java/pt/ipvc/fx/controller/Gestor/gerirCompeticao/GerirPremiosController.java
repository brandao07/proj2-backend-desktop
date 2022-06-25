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
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.models.NomeTipoPremio;
import pt.ipvc.backend.models.PremioNomeTipoPremio;
import pt.ipvc.backend.models.ProvaNomeEquipas;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.backend.services.ProvaBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class GerirPremiosController implements Initializable {
    public static Competicao path;

    public static PremioNomeTipoPremio aux;
    @FXML
    private TableView<PremioNomeTipoPremio> tableView;

    @FXML
    private TableColumn<PremioNomeTipoPremio,Integer> colunaPosicao;

    @FXML
    private TableColumn<PremioNomeTipoPremio, String> colunaPremioAtribuido;

    @FXML
    private TableColumn<PremioNomeTipoPremio, String> colunaTipoPremio;

    @FXML
    private Label checkDados;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getColumns().clear();
        path = CompeticaoBLL.getCompeticao(GerirCompeticaoController.aux.getNome());

        System.out.println(path);

        ObservableList<PremioNomeTipoPremio> dados = FXCollections.observableArrayList(PremioBLL.getPremioNomeTipoPremio(path));

        colunaPosicao.setCellValueFactory(new PropertyValueFactory<PremioNomeTipoPremio, Integer>("lugar"));

        colunaPremioAtribuido.setCellValueFactory(new PropertyValueFactory<PremioNomeTipoPremio, String>("valor"));

        colunaTipoPremio.setCellValueFactory(new PropertyValueFactory<PremioNomeTipoPremio, String>("tipoPremio"));

        tableView.setItems(dados);

        tableView.getColumns().add(colunaPosicao);
        tableView.getColumns().add(colunaTipoPremio);
        tableView.getColumns().add(colunaPremioAtribuido);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void adicionar (ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/criar-prova.fxml", event);
    }

    public void editar (ActionEvent event){
        aux = tableView.getSelectionModel().getSelectedItem();

        if (aux == null){
            checkDados.setText("Selecione uma Prova!");
            return;
        }
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/editar-prova.fxml", event);
    }

    public void remover (ActionEvent event){
        aux = tableView.getSelectionModel().getSelectedItem();

        if (aux == null){
            checkDados.setText("Selecione uma Prova!");
            return;
        }
        //PremioBLL.removerPremio(aux.getId());
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-prova.fxml", event);
    }

}
