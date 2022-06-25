package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.*;

public class EditarPremiosController implements Initializable {
    private static Premio premioAux;
    @FXML
    private Label infoComp;

    @FXML
    private Label checkDados;

    @FXML
    private ChoiceBox<String> tipoPremio;

    @FXML
    private TextField descricao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        premioAux = PremioBLL.getPremioId(GerirPremiosController.aux.getId());

        infoComp.setText("Competição: " + premioAux.getCompeticao().getNome()
            + "Lugar Pódio: " + premioAux.getLugar());

        tipoPremio.setValue(GerirPremiosController.aux.getTipoPremio());
        descricao.setPromptText(GerirPremiosController.aux.getValor());

        List<TipoPremio> tiposPremio = TipoPremioBLL.getTiposPremio();
        List<String> nomes = new ArrayList<>();

        for(TipoPremio tp : tiposPremio){
            nomes.add(tp.getNome());
        }
        tipoPremio.getItems().addAll(nomes);
    }

    public void confirmar(ActionEvent event){
            Premio premio = new Premio();

            premio.setId(premioAux.getId());

            premio.setCompeticao(premio.getCompeticao());

            premio.setTipoPremio(TipoPremioBLL.getTipoPremio(tipoPremio.getValue()));

            premio.setValor(descricao.getPromptText());
            if (!descricao.getText().isEmpty())
                premio.setValor(descricao.getText());

            PremioBLL.updatePremio(premio);
            ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-premios.fxml", event);
        }
        public void anterior(ActionEvent event) {
            ControladorGlobal.chamaScene("Gestor/criarCompeticao/gerir-premios.fxml", event);
        }

        public void setBtnNavMenu(@NotNull ActionEvent event) {
            String nome_scene = String.valueOf(event.getTarget());
            nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
            nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
            ValidarInput.sideMenuBarButtonLink(nome_scene, event);
        }
}
