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
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.services.PosicaoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosPosicaoController implements Initializable {

    public static String posicaoSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected Label labelErro;

    @FXML
    protected TableView<Posicao> tabelaPosicao;

    @FXML
    protected TableColumn<Posicao, String> colunaNome;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes","Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
        choiceBoxOpcoes.setValue("Posições");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaPosicao.getColumns().clear();
        ObservableList<Posicao> dados = FXCollections.observableArrayList(PosicaoBLL.getPosicoes());

        colunaNome.setCellValueFactory(new PropertyValueFactory<Posicao, String>("nome"));
        tabelaPosicao.setItems(dados);
        tabelaPosicao.getColumns().add(colunaNome);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            PosicaoBLL.removerPosicao(tabelaPosicao.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-posicao.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione uma posição.");
        }
    }

    public void setBtnEditar(ActionEvent event){
        try {
            posicaoSceneConsultar = tabelaPosicao.getSelectionModel().getSelectedItem().getNome();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-posicao.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um tipo de prémio.");
        }
    }



}
