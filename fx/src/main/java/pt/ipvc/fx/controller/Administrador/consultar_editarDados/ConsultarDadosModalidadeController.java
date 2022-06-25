package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class ConsultarDadosModalidadeController implements Initializable {

    public static String modalidadeSceneConsultar;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected TableView<Modalidade> tabelaModalidades;

    @FXML
    protected TableColumn<Modalidade, String> colunaNome;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.getItems().addAll("Árbitros", "Atletas", "Equipas", "Modalidades", "Recintos", "Tipos de Recintos", "Tipos de Prémios");
        choiceBoxOpcoes.setValue("Modalidades");
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminConsultarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        tabelaModalidades.getColumns().clear();
        ObservableList<Modalidade> dados = FXCollections.observableArrayList(ModalidadeBLL.getModalidades());

        colunaNome.setCellValueFactory(new PropertyValueFactory<Modalidade, String>("nome"));

        tabelaModalidades.setItems(dados);

        tabelaModalidades.getColumns().add(colunaNome);

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void setBtnRemover(ActionEvent event){
        ModalidadeBLL.removerModalidade(tabelaModalidades.getSelectionModel().getSelectedItem().getNome());
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-modalidade.fxml", event);

    }

    public void setBtnEditar(ActionEvent event){
        modalidadeSceneConsultar = tabelaModalidades.getSelectionModel().getSelectedItem().getNome();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-modalidade.fxml", event);
    }

    //TUDO: HUGO JA TENS PARA LISTAR TODOS ARBITROS
//        ArbitroBLL.getArbitros();
    //TUDO: HUGO JA TENS PARA OBTER Arbitro
//        Arbitro arbitro = ArbitroBLL.getArbitro(nome);
    //TUDO: HUGO JA TENS UPDATE
    //ArbitroBLL.updateArbitro(arbitro);


}
