package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosAtletaController implements Initializable {

    public static String atletaSceneConsultar;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Equipas", "Modalidades", "Prémios", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        choiceBoxOpcoes.setValue("Atletas");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.mudarPagConsultarEditarAdmin((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
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
        //AtletaBLL.removerAtleta(tabela.getSelectionModel().getSelectedItem());
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);

    }

    public void setBtnEditar(ActionEvent event){
        System.out.println(tabela1.getSelectionModel().getSelectedItem().toString());
        atletaSceneConsultar = tabela1.getSelectionModel().getSelectedItem().toString();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-arbitro.fxml", event);
    }

    //TUDO: HUGO JA TENS PARA LISTAR TODOS ARBITROS
//        ArbitroBLL.getArbitros();
    //TUDO: HUGO JA TENS PARA OBTER Arbitro
//        Arbitro arbitro = ArbitroBLL.getArbitro(nome);
    //TUDO: HUGO JA TENS UPDATE
    //ArbitroBLL.updateArbitro(arbitro);


}
