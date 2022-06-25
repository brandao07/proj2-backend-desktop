package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

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
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class CriarProvaController implements Initializable {
    public static Competicao competicao;

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

        competicao = CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp);

        Set<String> equipas = ((List<Equipa>) EquipasBLL.getEquipas()).stream().
                map(Equipa::getNome).collect(Collectors.toSet());

        Set<String> recintos = ((List<Recinto>) RecintoBLL.getRecintos()).stream().
                map(Recinto::getNome).collect(Collectors.toSet());

        Set<String> arbitros = ((List<Arbitro>) ArbitroBLL.getArbitros()).stream().
                map(Arbitro::getNome).collect(Collectors.toSet());

        Set<String> equipasFora = new HashSet<>();

        equipaCasa.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                equipaFora.getItems().clear();
                equipasFora.clear();
                for (String e: equipas) {
                    if (!e.equals(equipaCasa.getValue())){
                        equipasFora.add(e);
                    }
                }
                equipaFora.getItems().addAll(equipasFora);
            }
        });

        equipaCasa.getItems().addAll(equipas);
        recinto.getItems().addAll(recintos);
        arbitro.getItems().addAll(arbitros);
    }

    public void confirmar(ActionEvent event){
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


        else if (data.getValue() != null){
            Date dt = Date.from((data.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(!((competicao.getDataInicio().compareTo(dt) <= 0)
                    && (competicao.getDataFim().compareTo(dt) >= 0))){
                invalidDados.setText("A data da Prova tem de estar entre as datas: " + competicao.getDataInicio() +
                        " e a Data: " + competicao.getDataFim() + "!");
                return;
            }
        }
        ProvaBLL.criarProva(data.getValue(), competicao.getNome(), equipaCasa.getValue(),
                equipaFora.getValue(), recinto.getValue(), arbitro.getValue());
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-prova.fxml", event);
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
