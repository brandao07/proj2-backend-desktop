package pt.ipvc.fx.controller.Administrador.adicionarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class AtletasController implements Initializable {

    //apagar os contratos e adicionar a modalidade

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected TextField peso;

    @FXML
    protected TextField altura;

    @FXML
    protected ChoiceBox naturalidade;

    @FXML
    protected ChoiceBox nacionalidade;

    @FXML
    protected ChoiceBox posicao;

    @FXML
    protected ChoiceBox modalidades;

    @FXML
    protected ChoiceBox equipa;

    @FXML
    protected Label labelErro;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;


    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(altura.getText()) &&
                ValidarInput.validarString(peso.getText()) &&
                ValidarInput.validarDataPicker(data.getValue()) &&
                ValidarInput.validarChoiceBox(nacionalidade) &&
                ValidarInput.validarChoiceBox(modalidades) &&
                ValidarInput.validarChoiceBox(posicao) &&
                ValidarInput.validarChoiceBox(genero) &&
                ValidarInput.validarChoiceBox(modalidades)) {
            labelErro.setText("");
            AtletaBLL.criarAtleta(nome.getText(),
                    genero.getSelectionModel().getSelectedItem().toString(),
                    nacionalidade.getSelectionModel().getSelectedItem().toString(),
                    data.getValue(),
                    Double.parseDouble(peso.getText()) ,
                    Double.parseDouble(altura.getText()) ,
                    equipa.getSelectionModel().getSelectedItem().toString(),
                    posicao.getSelectionModel().getSelectedItem().toString());
            return;
        }
        labelErro.setText("Preencha todos os campos");
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());

        choiceBoxOpcoes.setOnAction(actionEvent -> {
            ValidarInput.opcoesMenuAdicionarAdmin((String) choiceBoxOpcoes.getSelectionModel().getSelectedItem(), (ActionEvent) actionEvent);
        });
        //ControladorGlobal.chamaScene("admin-home-page.fxml", event);



        Set<String> modalidade = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);
        genero.getItems().addAll(StringGeneros.generos());

        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        nacionalidade.getItems().addAll(paises);

        modalidades.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                posicao.getItems().clear();
                equipa.getItems().clear();

                Modalidade modalidade = ModalidadeBLL.getModalidade((String) modalidades.getSelectionModel().getSelectedItem());

                for (Posicao p: modalidade.getPosicoes()
                ) {
                    posicao.getItems().add(p.getNome());
                }

                for (Equipa e: modalidade.getEquipas()
                ) {
                    equipa.getItems().add(e.getNome());
                }
            }
        });


    }
}
