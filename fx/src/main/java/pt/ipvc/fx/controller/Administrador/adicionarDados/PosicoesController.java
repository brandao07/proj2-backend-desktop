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
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class PosicoesController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox modalidades;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ImageView erroNome;

    @FXML
    protected ImageView erroModalidade;

    @FXML
    protected Label labelErro;




    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxOpcoes.setValue("Posições");
        List<Modalidade> listaModalidades = ModalidadeBLL.getModalidades();

        for (Modalidade item: listaModalidades){
            modalidades.getItems().addAll(item.getNome());
        }
    }

    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        if (testar()){
            labelErro.setTextFill(Color.web("#32CD32"));
            labelErro.setText("Sucesso.");
            TimeUnit.SECONDS.sleep(1);

        PosicaoBLL.criarPosicao(nome.getText());
        ModalidadeBLL.addPosicao(ModalidadeBLL.getModalidade(modalidades.getSelectionModel().getSelectedItem().toString()), PosicaoBLL.getPosicao(nome.getText()));
            ControladorGlobal.adicionarPosicao();

            ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-posicao.fxml", event);
        }
        else{
            labelErro.setText("Preencha todos os campos.");
        }
        }




    public boolean testar(){
        boolean validarNome = true;
        boolean validarModalidade = true;

        if (!ValidarInput.validarString(nome.getText())){
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            validarNome = false;
        }
        else {
            nome.setBorder(new Border(new BorderStroke(Color.valueOf("#32CD32"), BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            erroNome.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }


        if (!ValidarInput.validarChoiceBox(modalidades.getValue())){
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            validarModalidade = false;
        }
        else {
            erroModalidade.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }
        return validarNome && validarModalidade ;
    }

}
