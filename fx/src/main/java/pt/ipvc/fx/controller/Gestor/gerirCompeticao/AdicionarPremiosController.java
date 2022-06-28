package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.PremioBLL;
import pt.ipvc.backend.services.TipoPremioBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdicionarPremiosController implements Initializable {
    public static Competicao competicao;

    public static Integer nLugar = 0;

    @FXML
    private Label infoComp;

    @FXML
    private Label checkDados;

    @FXML
    private ChoiceBox<String> tipoPremio;

    @FXML
    private TextField descricao;

    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        competicao = CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp);

        List<Premio> aux = PremioBLL.getPremio(competicao.getNome());
        //verifica o lugar do premio mais elevado referente a esta competicao
        for (Premio p : aux) {
            if (p.getLugar() > nLugar) {
                nLugar = p.getLugar();
            }
        }

        infoComp.setText("Competição: " + competicao.getNome() + "Lugar Pódio: " + (nLugar + 1));

        List<TipoPremio> tiposPremio = TipoPremioBLL.getTiposPremio();
        List<String> nomes = new ArrayList<>();

        for(TipoPremio tp : tiposPremio){
            nomes.add(tp.getNome());
        }

        tipoPremio.getItems().addAll(nomes);
    }

    public void confirmar(ActionEvent event){
        Premio premio = new Premio();

        premio.setLugar(nLugar + 1);
        // validacoes para o adicionar um premio
        if(!ValidarInput.validarChoiceBox(tipoPremio.getValue()))
            checkDados.setText("Selecione uma opção no Campo Tipo Prémio");
        premio.setTipoPremio(TipoPremioBLL.getTipoPremio(tipoPremio.getValue()));

        if(!ValidarInput.validarString(descricao.getText()))
            checkDados.setText("Preencha o Campo Descrição");
        premio.setValor(descricao.getText());

        PremioBLL.criarPremio(premio, competicao.getNome(), tipoPremio.getValue());
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

    public void homePage(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }
}
