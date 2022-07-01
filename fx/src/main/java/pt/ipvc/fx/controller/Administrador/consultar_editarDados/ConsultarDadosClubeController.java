package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.Equipa;
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
    protected TableView<Equipa> tabelaEquipas;

    @FXML
    protected TableColumn<Equipa, String> colunaNome;

    @FXML
    protected TableColumn<Equipa, String> colunaData;

    @FXML
    protected TableColumn<Equipa, String> colunaPais;

    @FXML
    protected TableColumn<Equipa, String> colunaCidade;

    @FXML
    protected TableColumn<Equipa, String> colunaSigla;

    @FXML
    protected TableColumn<Equipa, String> colunaContacto;

    @FXML
    protected TextField pesquisa;


    public void pesquisar() {
        //pesquisar pelo nome da competicao e mandar os daods da query para a tabela
        if (!ValidarInput.validarString(pesquisa.getText())) {
            tabelaEquipas.setItems(FXCollections.observableArrayList(EquipasBLL.getEquipas()));
            return;
        }
        tabelaEquipas.setItems(FXCollections.observableArrayList(EquipasBLL.getEquipa(pesquisa.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ValidarInput.validarString(pesquisa.getText())) {
            pesquisar();
        }
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Equipas", "Modalidades", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        choiceBoxOpcoes.setValue("Equipas");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaEquipas.getColumns().clear();
        ObservableList<Equipa> dados = FXCollections.observableArrayList(EquipasBLL.getEquipas());

        colunaNome.setCellValueFactory(new PropertyValueFactory<Equipa, String>("nome"));
        colunaPais.setCellValueFactory(new PropertyValueFactory<Equipa, String>("pais"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<Equipa, String>("cidade"));
        colunaSigla.setCellValueFactory(new PropertyValueFactory<Equipa, String>("sigla"));
        colunaContacto.setCellValueFactory(new PropertyValueFactory<Equipa, String>("contacto"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Equipa, String>("dataFundacao"));

        tabelaEquipas.setItems(dados);

        tabelaEquipas.getColumns().add(colunaNome);
        tabelaEquipas.getColumns().add(colunaCidade);
        tabelaEquipas.getColumns().add(colunaPais);
        tabelaEquipas.getColumns().add(colunaContacto);
        tabelaEquipas.getColumns().add(colunaData);
        tabelaEquipas.getColumns().add(colunaSigla);


    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        EquipasBLL.removerEquipa(tabelaEquipas.getSelectionModel().getSelectedItem().getNome());
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-equipa.fxml", event);

    }

    public void setBtnEditar(ActionEvent event){
        clubeSceneConsultar = tabelaEquipas.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-equipa.fxml", event);
    }

    //TUDO: HUGO JA TENS PARA LISTAR TODOS ARBITROS
//        ArbitroBLL.getArbitros();
    //TUDO: HUGO JA TENS PARA OBTER Arbitro
//        Arbitro arbitro = ArbitroBLL.getArbitro(nome);
    //TUDO: HUGO JA TENS UPDATE
    //ArbitroBLL.updateArbitro(arbitro);


}
