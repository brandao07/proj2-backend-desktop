package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class adicionarProvaController implements Initializable {
    @FXML
    private ChoiceBox<String> equipaCasa;

    @FXML
    private ChoiceBox<String> equipaFora;

    @FXML
    private ChoiceBox<String> recinto;

    @FXML
    private ChoiceBox<String> arbitro;

    @FXML
    private DatePicker data;

    @FXML
    private Label invalidDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Set<String> equipas = ((List<Equipa>)EquipasBLL.getEquipas()).stream().
                map(Equipa::getNome).collect(Collectors.toSet());

        Set<String> recintos = ((List<Recinto>)RecintoBLL.getRecintos()).stream().
                map(Recinto::getNome).collect(Collectors.toSet());

        Set<String> arbitros = ((List<Arbitro>)ArbitroBLL.getArbitros()).stream().
                map(Arbitro::getNome).collect(Collectors.toSet());

        Set<String> equipasFora = new HashSet<>();

        equipaCasa.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                equipaFora.getItems().clear();
                    for (String e: equipas) {
                        if (!e.equals(equipaCasa.getValue()))
                            equipasFora.add(e);
                    }
                    equipaFora.getItems().addAll(equipasFora);
            }
        });

        equipaCasa.getItems().addAll(equipas);
        recinto.getItems().addAll(recintos);
        arbitro.getItems().addAll(arbitros);
    }

    public void criarProva() {

        if (!ValidarInput.validarChoiceBox(equipaCasa.getValue()))
            invalidDados.setText("Selecione uma opção no Campo Equipa Casa");

        else if (!ValidarInput.validarChoiceBox(equipaFora.getValue()))
            invalidDados.setText("Selecione uma opção no Campo Equipa Fora");

        else if (!ValidarInput.validarChoiceBox(recinto.getValue()))
            invalidDados.setText("Selecione um opção no Campo Recinto");

        else if (!ValidarInput.validarDataPicker(data.getValue()))
            invalidDados.setText("Seleciona uma data no campo Data");

        else if (!ValidarInput.validarChoiceBox(arbitro.getValue()))
            invalidDados.setText("Selecione uma opção no campo Árbitro");
        else
        ProvaBLL.criarProva(data.getValue(), criarCompeticaoController.compSelecionada.getNome(), equipaCasa.getValue(),
                    equipaFora.getValue(), recinto.getValue(), arbitro.getValue());
    }
    public void confirmarContinuar(ActionEvent event) {
        criarProva();
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }
    public void confirmarSair(ActionEvent event) {
        criarProva();
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
    }

    public void setBtnNavMenu(@NotNull ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

}
