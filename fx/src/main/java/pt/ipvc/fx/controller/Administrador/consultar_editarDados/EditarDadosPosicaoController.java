package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.services.PosicaoBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class EditarDadosPosicaoController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected Label usernameLabel;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Posicao posicao = new Posicao();
        String nomePosicao = nome.getPromptText();
        posicao.setNome(nomePosicao);

        if (!nome.getText().isEmpty()){
            posicao.setNome(nome.getText());
            nomePosicao = nome.getText();
        }

        posicao.setId(PosicaoBLL.getPosicao(nome.getPromptText()).getId());
        posicao.setNome((String) nome.getText());

        PosicaoBLL.updatePosicao(posicao);
        ControladorGlobal.editarPosicao();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-posicao.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        nome.setPromptText(ConsultarDadosPosicaoController.posicaoSceneConsultar);
    }




}
