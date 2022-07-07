package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;


public class EditarDadosTipoPremioController implements Initializable {
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
        TipoPremio tipoPremio = new TipoPremio();
        String nomeTipoPremio = nome.getPromptText();
        tipoPremio.setNome(nomeTipoPremio);

        if (!nome.getText().isEmpty()){
            tipoPremio.setNome(nome.getText());
        }

        tipoPremio.setId(TipoPremioBLL.getTipoPremio(nome.getPromptText()).getId());
        tipoPremio.setNome(nome.getText());

        TipoPremioBLL.updateTipoPremio(tipoPremio);
        ControladorGlobal.editarTipoPremio();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-tipo-premio.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        nome.setPromptText(ConsultarDadosTipoPremioController.tipoPremioSceneConsultar);
    }




}
