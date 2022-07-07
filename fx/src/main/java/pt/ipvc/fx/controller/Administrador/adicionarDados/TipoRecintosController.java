package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class TipoRecintosController implements Initializable {

    @FXML
    protected TextField tipo;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected Label labelErro;

    @FXML
    protected ImageView erroNome;

    @FXML
    protected Label usernameLabel;

    public boolean testar() {
        boolean validarNome = true;


        if (!ValidarInput.validarString(tipo.getText())) {
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            tipo.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            validarNome = false;
        } else {
            tipo.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        return validarNome;
    }

    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        if (testar()){
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);

            TipoRecintoBLL.criarTipoRecinto(tipo.getText());

            ControladorGlobal.adicionarTipoRecinto();

            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-tipoRecinto.fxml", event);
        }
        else{
            labelErro.setText("Preencha todos os campos.");
        }

    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        choiceBoxOpcoes.setValue("Tipos de Recinto");
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());
        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.choiceBoxAdminAdicionarDados((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
    }
}
