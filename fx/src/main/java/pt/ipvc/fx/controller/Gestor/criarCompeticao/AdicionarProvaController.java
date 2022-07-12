package pt.ipvc.fx.controller.Gestor.criarCompeticao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class AdicionarProvaController implements Initializable {
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

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView erroEquipaCasa;

    @FXML
    private ImageView erroEquipaFora;

    @FXML
    private ImageView erroRecinto;

    @FXML
    private ImageView erroData;

    @FXML
    private ImageView erroArbitro;

    private static boolean controlo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        //Conjuntos de dados necessarios para as choiceboxes
        Set<String> equipas = new HashSet<>();
        List<Equipa> equipasList = EquipasBLL.getEquipas();
        for (Equipa e : equipasList) {
            if(e.getModalidade().getNome().equals(CriarCompeticaoController.compSelecionada.getModalidade().getNome())){
                equipas.add(e.getNome());
            }
        }

        Set<String> recintos = ((List<Recinto>) RecintoBLL.getRecintos()).stream().
                map(Recinto::getNome).collect(Collectors.toSet());

        Set<String> arbitros = new HashSet<>();
        List<Arbitro> arbitrosList = ArbitroBLL.getArbitros();
        for (Arbitro a : arbitrosList) {
            if(a.getModalidade().getNome().equals(CriarCompeticaoController.compSelecionada.getModalidade().getNome())){
                arbitros.add(a.getNome());
            }
        }

        Set<String> equipasFora = new HashSet<>();

        //Se a choice box euqipa casa selecionar uma euqipa essa equipa ja nao pode ser escolhida como equipa  fora
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
    }

    public void criarProva() {
        controlo = true;
        //validacoes para criar prova
        if (!ValidarInput.validarChoiceBox(equipaCasa.getValue())){
            invalidDados.setText("Selecione uma opção no Campo Equipa Casa");
            erroEquipaCasa.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            controlo = false;
        } else {
            erroEquipaCasa.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(equipaFora.getValue())){
            invalidDados.setText("Selecione uma opção no Campo Equipa Fora");
            erroEquipaFora.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            controlo = false;
        }  else {
            erroEquipaFora.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(recinto.getValue())){
            invalidDados.setText("Selecione um opção no Campo Recinto");
            erroRecinto.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            controlo = false;
        } else {
            erroRecinto.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarDataPicker(data.getValue())){
            invalidDados.setText("Seleciona uma data no campo Data");
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            controlo = false;
        } else {
            erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        if (!ValidarInput.validarChoiceBox(arbitro.getValue())){
            invalidDados.setText("Selecione uma opção no campo Árbitro");
            erroArbitro.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
            controlo = false;
        } else {
            erroArbitro.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
        }

        //verificar se a data prova esta entre as datas da competicao
        if (data.getValue() != null){
            Date dt = Date.from((data.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(!((CriarCompeticaoController.compSelecionada.getDataInicio().compareTo(dt) <= 0)
                    && (CriarCompeticaoController.compSelecionada.getDataFim().compareTo(dt) >= 0))){
                invalidDados.setText("A data da Prova tem de estar entre as datas: " + CriarCompeticaoController.compSelecionada.getDataInicio() +
                        " e a Data: " + CriarCompeticaoController.compSelecionada.getDataFim() + "!");
                erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                controlo = false;
            } else {
                erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
            }
        }
    }
    public void confirmarContinuar(ActionEvent event) {
        this.criarProva();
        if (!controlo){
            return;
        }
        ProvaBLL.criarProva(data.getValue(), CriarCompeticaoController.compSelecionada.getNome(), equipaCasa.getValue(),
                equipaFora.getValue(), recinto.getValue(), arbitro.getValue());
        CompeticaoBLL.addEquipa(CriarCompeticaoController.compSelecionada, EquipasBLL.getEquipa(equipaCasa.getValue()));
        CompeticaoBLL.addEquipa(CriarCompeticaoController.compSelecionada, EquipasBLL.getEquipa(equipaFora.getValue()));
        ControladorGlobal.criarProva();
        ControladorGlobal.chamaScene("Gestor/criarCompeticao/adicionar-prova.fxml", event);
    }
    public void confirmarSair(ActionEvent event) {
        this.criarProva();
        if (!controlo){
            return;
        }
        ProvaBLL.criarProva(data.getValue(), CriarCompeticaoController.compSelecionada.getNome(), equipaCasa.getValue(),
                equipaFora.getValue(), recinto.getValue(), arbitro.getValue());
        CompeticaoBLL.addEquipa(CriarCompeticaoController.compSelecionada, EquipasBLL.getEquipa(equipaCasa.getValue()));
        CompeticaoBLL.addEquipa(CriarCompeticaoController.compSelecionada, EquipasBLL.getEquipa(equipaFora.getValue()));
        ControladorGlobal.criarProva();
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

    public void homePage(ActionEvent event) {
        ControladorGlobal.chamaScene("Gestor/gestor-home-page.fxml", event);
    }

}
