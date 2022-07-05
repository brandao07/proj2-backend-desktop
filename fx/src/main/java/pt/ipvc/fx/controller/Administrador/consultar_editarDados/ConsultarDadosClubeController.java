package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.services.ClubeBLL;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosClubeController implements Initializable {

    public static String clubeSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected TableView<Clube> tabelaClubes;

    @FXML
    protected TableColumn<Clube, String> colunaNome;

    @FXML
    protected TableColumn<Clube, String> colunaData;

    @FXML
    protected TableColumn<Clube, String> colunaPais;

    @FXML
    protected TableColumn<Clube, String> colunaCidade;

    @FXML
    protected TableColumn<Clube, String> colunaSigla;

    @FXML
    protected TableColumn<Clube, String> colunaContacto;

    @FXML
    protected TextField pesquisa;

    @FXML
    protected Label labelErro;


    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabelaClubes.setItems(FXCollections.observableArrayList(ClubeBLL.getClubes()));
            return;
        }
        tabelaClubes.setItems(FXCollections.observableArrayList(ClubeBLL.getClube(pesquisa.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Clubes", "Equipas", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        choiceBoxOpcoes.setValue("Clubes");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaClubes.getColumns().clear();
        ObservableList<Clube> dados = FXCollections.observableArrayList(ClubeBLL.getClubes());

        colunaNome.setCellValueFactory(new PropertyValueFactory<Clube, String>("nome"));
        colunaPais.setCellValueFactory(new PropertyValueFactory<Clube, String>("pais"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<Clube, String>("cidade"));
        colunaSigla.setCellValueFactory(new PropertyValueFactory<Clube, String>("sigla"));
        colunaContacto.setCellValueFactory(new PropertyValueFactory<Clube, String>("contacto"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Clube, String>("dataFundacao"));

        tabelaClubes.setItems(dados);

        tabelaClubes.getColumns().add(colunaNome);
        tabelaClubes.getColumns().add(colunaCidade);
        tabelaClubes.getColumns().add(colunaPais);
        tabelaClubes.getColumns().add(colunaContacto);
        tabelaClubes.getColumns().add(colunaData);
        tabelaClubes.getColumns().add(colunaSigla);


    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        try {
            ClubeBLL.removerClube(tabelaClubes.getSelectionModel().getSelectedItem().getNome());
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-clube.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um clube.");
        }


    }

    public void setBtnEditar(ActionEvent event){
        try {
            clubeSceneConsultar = tabelaClubes.getSelectionModel().getSelectedItem().getNome();
            ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-clube.fxml", event);
        }catch (Exception e){
            labelErro.setText("Selecione um clube.");
            }
        }


    //TUDO: HUGO JA TENS PARA LISTAR TODOS ARBITROS
//        ArbitroBLL.getArbitros();
    //TUDO: HUGO JA TENS PARA OBTER Arbitro
//        Arbitro arbitro = ArbitroBLL.getArbitro(nome);
    //TUDO: HUGO JA TENS UPDATE
    //ArbitroBLL.updateArbitro(arbitro);


}
