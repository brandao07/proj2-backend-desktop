package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.fx.controller.BufferedImage;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;


public class ConsultarDadosAtletaController implements Initializable {

    public static long atletaSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected TableView<AtletaNomeEquipa_Modalidade> tabela1;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaNome;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaData;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaModalidade;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaNacionalidade;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaGenero;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, Double> colunaPeso;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, Double> colunaAltura;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaPosicao;

    @FXML
    protected TableColumn<AtletaNomeEquipa_Modalidade, String> colunaEquipa;

    @FXML
    protected TextField pesquisa;

    @FXML
    protected Label labelErro;

    @FXML
    protected ImageView teste;


    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabela1.setItems(FXCollections.observableArrayList(AtletaBLL.getAtletaNomeEquipa_Modalidade()));
            tabela1.setItems(FXCollections.observableArrayList(AtletaBLL.getAtletaNomeEquipa_Modalidade()));
            return;
        }
        tabela1.setItems(FXCollections.observableArrayList(AtletaBLL.getAtletaPesquisa(pesquisa.getText())));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image img = new Image(new ByteArrayInputStream(AtletaBLL.getAtletaById(2).getImagem()));

        teste.setImage(img);

        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes","Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios", "Posições");
        choiceBoxOpcoes.setValue("Atletas");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabela1.getColumns().clear();
        ObservableList<AtletaNomeEquipa_Modalidade> dados = FXCollections.observableArrayList(AtletaBLL.getAtletaNomeEquipa_Modalidade());

        colunaNome.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("dataNascimento"));
        colunaModalidade.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("modalidade"));
        colunaNacionalidade.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("nacionalidade"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("genero"));
        colunaPeso.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, Double>("peso"));
        colunaAltura.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, Double>("altura"));
        colunaPosicao.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("posicao"));
        colunaEquipa.setCellValueFactory(new PropertyValueFactory<AtletaNomeEquipa_Modalidade, String>("equipa"));

        tabela1.setItems(dados);

        tabela1.getColumns().add(colunaNome);
        tabela1.getColumns().add(colunaData);
        tabela1.getColumns().add(colunaModalidade);
        tabela1.getColumns().add(colunaNacionalidade);
        tabela1.getColumns().add(colunaGenero);
        tabela1.getColumns().add(colunaPeso);
        tabela1.getColumns().add(colunaAltura);
        tabela1.getColumns().add(colunaPosicao);
        tabela1.getColumns().add(colunaEquipa);
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            AtletaBLL.removerAtleta(tabela1.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-atleta.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um atleta.");
        }
    }

    public void setBtnEditar(ActionEvent event){
        try {
            atletaSceneConsultar = tabela1.getSelectionModel().getSelectedItem().getId();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-atleta.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um atleta.");
        }
    }

}
