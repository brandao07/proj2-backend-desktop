package pt.ipvc.fx.controller.Gestor.gerirCompeticao;

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
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.controller.Gestor.criarCompeticao.CriarCompeticaoController;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        competicao = CompeticaoBLL.getCompeticao(GerirCompeticaoController.comp);
        //dados para as choiceboxes so os nomes
        Set<String> equipas = new HashSet<>();
        List<Equipa> equipasList = EquipasBLL.getEquipas();
        for (Equipa e : equipasList) {
            if(e.getModalidade().getNome().equals(competicao.getModalidade().getNome())){
                equipas.add(e.getNome());
            }
        }

        Set<String> recintos = ((List<Recinto>) RecintoBLL.getRecintos()).stream().
                map(Recinto::getNome).collect(Collectors.toSet());

        Set<String> arbitros = new HashSet<>();
        List<Arbitro> arbitrosList = ArbitroBLL.getArbitros();
        for (Arbitro a : arbitrosList) {
            if(a.getModalidade().getNome().equals(competicao.getModalidade().getNome())){
                arbitros.add(a.getNome());
            }
        }

        Set<String> equipasFora = new HashSet<>();

        //verificar a equipa casa selcionada e ja nao aparece na possivel escolha para a equipa fora
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
        boolean controlo = true;
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
            if(!((competicao.getDataInicio().compareTo(dt) <= 0)
                    && (competicao.getDataFim().compareTo(dt) >= 0))){
                invalidDados.setText("A data da Prova tem de estar entre as datas: " + competicao.getDataInicio() +
                        " e a Data: " + competicao.getDataFim() + "!");
                erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/erro.png").toURI().toString()));
                controlo = false;
            } else {
                erroData.setImage(new Image(new File("fx/src/main/resources/pt/ipvc/fx/icons/correct.png").toURI().toString()));
            }
        }

        if (!controlo){
            return;
        }

        ProvaBLL.criarProva(data.getValue(), competicao.getNome(), equipaCasa.getValue(),
                equipaFora.getValue(), recinto.getValue(), arbitro.getValue());
        CompeticaoBLL.addEquipa(competicao, EquipasBLL.getEquipa(equipaCasa.getValue()));
        CompeticaoBLL.addEquipa(competicao, EquipasBLL.getEquipa(equipaFora.getValue()));
        ControladorGlobal.criarProva();
        ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-provas.fxml", event);
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
