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
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.AdminChoiceBoxOpcoes;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ArbitrosController implements Initializable {

    //trocar a função pelo genero e adicionar a modalidade

    @FXML
    protected TextField nome;

    @FXML
    protected ChoiceBox<String> nacionalidade;

    @FXML
    protected ChoiceBox<String> naturalidade;

    @FXML
    protected ChoiceBox<String> categoria;

    @FXML
    protected ChoiceBox<String> modalidades;

    @FXML
    protected ChoiceBox<String> associacao;

    @FXML
    protected ChoiceBox choiceBoxOpcoes;

    @FXML
    protected ChoiceBox<String> genero;

    @FXML
    protected DatePicker data;

    @FXML
    protected Label labelErro;

    @FXML
    public void confirmar(ActionEvent event) {
        if (ValidarInput.validarString(nome.getText()) &&
                ValidarInput.validarString(data.toString()) &&
                ValidarInput.validarChoiceBox(nacionalidade) &&
                ValidarInput.validarChoiceBox(naturalidade) &&
                ValidarInput.validarChoiceBox(associacao) &&
                ValidarInput.validarChoiceBox(categoria) &&
                ValidarInput.validarChoiceBox(genero) &&
                ValidarInput.validarChoiceBox(modalidades)) {
            labelErro.setText("");

            ArbitroBLL.criarArbitro(nome.getText(),
                    associacao.getSelectionModel().getSelectedItem(),
                    data.getValue(),
                    genero.getValue(),
                    categoria.getSelectionModel().getSelectedItem(),
                    nacionalidade.getSelectionModel().getSelectedItem(),
                    modalidades.getSelectionModel().getSelectedItem());
            ControladorGlobal.chamaScene("admin-home-page.fxml", event);
            return;
        }
        labelErro.setText("Preencha todos os campos");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
            LocalRepository.associacoes_portuguesas();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        choiceBoxOpcoes.getItems().addAll(AdminChoiceBoxOpcoes.opcoesAdmin());

        ArrayList<String> lista_associacoes = new ArrayList<>();
        for (String t : LocalRepository.getMapAssosiacoesPortuguesas().values()){
                lista_associacoes.add(t);
        }
        Collections.sort(lista_associacoes);
        lista_associacoes.remove(0);
        associacao.getItems().addAll(lista_associacoes);

        categoria.getItems().addAll("Internacional", "Nacional");

        Set<String> modalidade = ((List<Modalidade>)ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);
        genero.getItems().addAll(StringGeneros.generos());


        //adicionar pais à choiceBox nacionalidade
        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        Collections.sort(paises);
        nacionalidade.getItems().addAll(paises);


        //adicionar cidades à choiceBox naturalidade
        nacionalidade.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                naturalidade.getItems().clear();
                for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
                    if (nacionalidade.getSelectionModel().getSelectedItem().equals(pais)) {
                        naturalidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(pais));
                        break;
                    }
                }
            }
        });
    }

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }
}