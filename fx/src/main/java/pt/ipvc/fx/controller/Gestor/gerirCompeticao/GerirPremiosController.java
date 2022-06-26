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
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.models.NomeTipoPremio;
import pt.ipvc.backend.models.PremioNomeTipoPremio;
import pt.ipvc.backend.models.ProvaNomeEquipas;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.backend.services.ProvaBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GerirPremiosController implements Initializable {
    public static Competicao path;

    public static Integer nLugar;

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

        //atribuir dados a tabela
        ObservableList<PremioNomeTipoPremio> dados = FXCollections.observableArrayList(PremioBLL.getPremioNomeTipoPremio(path));

        // atribuir o campo a acada coluna da tabela
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

    public void anterior (ActionEvent event){
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);
    }

    public void adicionar (ActionEvent event){
        // chama a scene adicionar um premio
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/adicionar-premio.fxml", event);
    }

    public void editar (ActionEvent event){
        // chama a scene para editar um premio
        aux = tableView.getSelectionModel().getSelectedItem();

        if (aux == null){
            checkDados.setText("Selecione um Prémio!");
            return;
        }
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/editar-premio.fxml", event);
    }

    public void remover (ActionEvent event){
        // remover um premio da tabeça
        aux = tableView.getSelectionModel().getSelectedItem();

        if (aux == null){
            checkDados.setText("Selecione um Prémio!");
            return;
        }

        List<Premio> premios = PremioBLL.getPremio(GerirPremiosController.path.getNome());

        //remover o premio com lugar mais alto primeiro
        for (Premio p : premios) {
            nLugar = 0;
            if (p.getLugar() > nLugar) {
                nLugar = p.getLugar();
            }
        }

        if (!Objects.equals(nLugar, aux.getLugar())){
            checkDados.setText("Remova primeiro o Prémio com Posição: " + nLugar);
            return;
        }
        PremioBLL.removerPremio(aux.getId());
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-premios.fxml", event);
    }

}
