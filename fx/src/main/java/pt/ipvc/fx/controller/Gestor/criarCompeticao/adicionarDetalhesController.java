package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class adicionarDetalhesController implements Initializable {

    @FXML
    private Button adicionar;

    @FXML
    private TextField premio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void adiconar(ActionEvent event) {
        // Para cada linha da tabela
//       Premio p = new Premio(lugar, valor);
//       PremioBLL.criarPremio(p, "nomeCompeticao", "nomeTipoPremio");


    }

    public void seguinte(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

    public void setBtnNavMenu(ActionEvent event){
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

}
