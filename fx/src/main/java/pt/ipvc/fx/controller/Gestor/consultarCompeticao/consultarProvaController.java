package pt.ipvc.fx.controller.Gestor.consultarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class consultarProvaController implements Initializable {

    @FXML
    private TableView competicoes;

    @FXML
    private Label nomeCompeticao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: CAMPOS ja tens as provas
        //FIXED listar as provas da competicao selecionada na scene consultar-competicao
        // CompeticaoBLL.getCompeticao("nome").getProvas();

        nomeCompeticao.setText("Competição: ");
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/consultarCompeticao/admin-sistema-adicionar-user.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event){
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}
