package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosRecintoController implements Initializable {

    public static String recintoSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected TableView<Recinto> tabelaRecintos;

    @FXML
    protected TableColumn<Recinto, String> colunaNome;

    @FXML
    protected TableColumn<Recinto, String> colunaPais;

    @FXML
    protected TableColumn<Recinto, Double> colunaCapacidade;

    @FXML
    protected Label labelErro;

    @FXML
    protected Label usernameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes","Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
        choiceBoxOpcoes.setValue("Recintos");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaRecintos.getColumns().clear();
        ObservableList<Recinto> dados = FXCollections.observableArrayList(RecintoBLL.getRecintos());

        colunaNome.setCellValueFactory(new PropertyValueFactory<Recinto, String>("nome"));
        colunaPais.setCellValueFactory(new PropertyValueFactory<Recinto, String>("pais"));
        colunaCapacidade.setCellValueFactory(new PropertyValueFactory<Recinto, Double>("capacidade"));

        tabelaRecintos.setItems(dados);

        tabelaRecintos.getColumns().add(colunaNome);
        tabelaRecintos.getColumns().add(colunaPais);
        tabelaRecintos.getColumns().add(colunaCapacidade);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            RecintoBLL.removerRecinto(tabelaRecintos.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-recinto.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um recinto.");
        }
        }

    public void setBtnEditar(ActionEvent event){
        try {
            recintoSceneConsultar = tabelaRecintos.getSelectionModel().getSelectedItem().getNome();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-recinto.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um recinto.");
        }
    }

}
