package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class EditarDadosTipoRecintoController implements Initializable {
    @FXML
    protected TextField nome;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        TipoRecinto tipoRecinto = new TipoRecinto();
        String nomeTipoRecinto = nome.getPromptText();
        tipoRecinto.setNome(nomeTipoRecinto);

        if (!nome.getText().isEmpty()){
            tipoRecinto.setNome(nome.getText());
            nomeTipoRecinto = nome.getText();
        }

        tipoRecinto.setId(TipoRecintoBLL.getTipoRecinto(nome.getPromptText()).getId());
        tipoRecinto.setNome((String) nome.getText());

        TipoRecintoBLL.updateTipoRecinto(tipoRecinto);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-tipo-recinto.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nome.setPromptText(ConsultarDadosTipoRecintoController.tipoRecintoSceneConsultar);
    }




}
