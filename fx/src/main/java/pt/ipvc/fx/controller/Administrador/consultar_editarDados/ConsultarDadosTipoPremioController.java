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
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosTipoPremioController implements Initializable {

    public static String tipoPremioSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected Label labelErro;

    @FXML
    protected TableView<TipoPremio> tabelaTipoPremios;

    @FXML
    protected TableColumn<TipoPremio, String> colunaNome;

    @FXML
    protected Label usernameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes","Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
        choiceBoxOpcoes.setValue("Tipos de Prémios");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaTipoPremios.getColumns().clear();
        ObservableList<TipoPremio> dados = FXCollections.observableArrayList(TipoPremioBLL.getTiposPremio());

        colunaNome.setCellValueFactory(new PropertyValueFactory<TipoPremio, String>("nome"));

        tabelaTipoPremios.setItems(dados);

        tabelaTipoPremios.getColumns().add(colunaNome);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            TipoPremioBLL.removerTipoPremio(tabelaTipoPremios.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-tipo-premio.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um tipo de prémio.");
        }


    }

    public void setBtnEditar(ActionEvent event){
        try {
            tipoPremioSceneConsultar = tabelaTipoPremios.getSelectionModel().getSelectedItem().getNome();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-tipo-premio.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um tipo de prémio.");
        }

    }



}
