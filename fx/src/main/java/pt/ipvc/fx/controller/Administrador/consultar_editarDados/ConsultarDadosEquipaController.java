package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.models.EquipaInfo;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosEquipaController implements Initializable {

    public static String equipaSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected TableView<EquipaInfo> tabelaEquipas;

    @FXML
    protected Label labelErro;

    @FXML
    protected TableColumn<EquipaInfo, String> colunaNome;

    @FXML
    protected TableColumn<EquipaInfo, String> colunaClube;

    @FXML
    protected TableColumn<EquipaInfo, String> colunaModalidade;

    @FXML
    protected TableColumn<EquipaInfo, Long> colunaJogadores;


    @FXML
    protected TextField pesquisa;


    ObservableList<EquipaInfo> dados;



    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabelaEquipas.setItems(FXCollections.observableArrayList(EquipasBLL.getEquipas()));
            tabelaEquipas.setItems(dados);
            return;
        }
        tabelaEquipas.setItems(FXCollections.observableArrayList(EquipasBLL.findEquipa(pesquisa.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes", "Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
        choiceBoxOpcoes.setValue("Equipas");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaEquipas.getColumns().clear();

        dados = FXCollections.observableArrayList(EquipasBLL.equipasConsultar());

        colunaNome.setCellValueFactory(new PropertyValueFactory<EquipaInfo, String>("nome"));
        colunaClube.setCellValueFactory(new PropertyValueFactory<EquipaInfo, String>("clube"));
        colunaModalidade.setCellValueFactory(new PropertyValueFactory<EquipaInfo, String>("modalidade"));
        colunaJogadores.setCellValueFactory(new PropertyValueFactory<EquipaInfo, Long>("n_jogadores"));
        tabelaEquipas.setItems(dados);

        tabelaEquipas.getColumns().add(colunaNome);
        tabelaEquipas.getColumns().add(colunaClube);
        tabelaEquipas.getColumns().add(colunaJogadores);
        tabelaEquipas.getColumns().add(colunaModalidade);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            EquipasBLL.removerEquipa(tabelaEquipas.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-equipa.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione uma Equipa.");
        }
    }

    public void setBtnEditar(ActionEvent event){
        try {
            equipaSceneConsultar = tabelaEquipas.getSelectionModel().getSelectedItem().getNome();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-equipa.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione uma Equipa.");
        }
    }
}
