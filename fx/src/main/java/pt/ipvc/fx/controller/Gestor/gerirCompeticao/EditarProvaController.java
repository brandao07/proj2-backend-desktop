package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.Resultados;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class EditarProvaController implements Initializable {
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
    private ComboBox<String> resultadoEC;

    @FXML
    private ComboBox<String> resultadoEF;

    @FXML
    private Label invalidDados;

    @FXML
    private Label usernameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        //atrinuir aos cmapos os dados da prova que o user selecionou

        equipaCasa.setValue(GerirProvaController.aux.getEquipaCasa());
        equipaFora.setValue(GerirProvaController.aux.getEquipaFora());
        recinto.setValue(GerirProvaController.aux.getRecinto());
        arbitro.setValue(GerirProvaController.aux.getArbitro());
        System.out.println(GerirProvaController.aux.getArbitro());
        data.setPromptText(String.valueOf(GerirProvaController.aux.getDataInicio()));
        resultadoEC.setValue(GerirProvaController.aux.getResultadoEquipaCasa());
        resultadoEF.setValue(GerirProvaController.aux.getResultadoEquipaFora());

        //dados para as choiceboxes so os nomes
        Set<String> equipas = new HashSet<>();
        List<Equipa> equipasList = EquipasBLL.getEquipas();
        for (Equipa e : equipasList) {
            if(e.getModalidade().getNome().equals(ProvaBLL.getProva(GerirProvaController.aux.getId()).getCompeticao().getModalidade().getNome())){
                equipas.add(e.getNome());
            }
        }

        Set<String> recintos = ((List<Recinto>) RecintoBLL.getRecintos()).stream().
                map(Recinto::getNome).collect(Collectors.toSet());

        Set<String> arbitros = new HashSet<>();
        List<Arbitro> arbitrosList = ArbitroBLL.getArbitros();
        for (Arbitro a : arbitrosList) {
            if(a.getModalidade().getNome().equals(ProvaBLL.getProva(GerirProvaController.aux.getId()).getCompeticao().getModalidade().getNome())){
                arbitros.add(a.getNome());
            }
        }

        Set<String> equipasFora = new HashSet<>();

        //verificap da equipa selecionada nao pode ser selecionada para equipa fora
        equipaCasa.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                equipaFora.getItems().clear();
                equipasFora.clear();
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
        resultadoEC.getItems().addAll(Resultados.resultados());
        resultadoEC.setVisibleRowCount(7);
        resultadoEF.getItems().addAll(Resultados.resultados());
        resultadoEF.setVisibleRowCount(7);
    }

    public void confirmar(ActionEvent event){
        Prova prova = new Prova();

        //update da prova

        prova.setId(ProvaBLL.getProva(GerirProvaController.aux.getId()).getId());

        prova.setCompeticao(CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp));

        prova.setEquipaCasa(EquipasBLL.getEquipa(equipaCasa.getValue()));

        prova.setEquipaFora(EquipasBLL.getEquipa(equipaFora.getValue()));

        prova.setRecinto(RecintoBLL.getRecinto(recinto.getValue()));

        prova.setArbitro(ArbitroBLL.getArbitro(arbitro.getValue()));

        prova.setResultadoEquipaCasa(resultadoEC.getValue());

        prova.setResultadoEquipaFora(resultadoEF.getValue());


        if (data.getValue() != null){
            java.util.Date dt = java.util.Date.from((data.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(!((GerirCompeticaoController.aux.getDataInicio().compareTo(dt) <= 0)
                    && (GerirCompeticaoController.aux.getDataFim().compareTo(dt) >= 0))){
                invalidDados.setText("A data da Prova tem de estar entre as datas: " + GerirCompeticaoController.aux.getDataInicio() +
                        " e a Data: " + GerirCompeticaoController.aux.getDataFim() + "!");
                return;
            }
        }

        prova.setDataInicio(Date.valueOf(data.getPromptText()));
        if (data.getValue() != null)
            prova.setDataInicio(java.sql.Date.valueOf(data.getValue()));

        ProvaBLL.updateProva(prova);
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-prova.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/gerir-prova.fxml", event);
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
