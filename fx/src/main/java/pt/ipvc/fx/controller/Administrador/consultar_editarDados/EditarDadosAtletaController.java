package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


public class EditarDadosAtletaController implements Initializable {
    @FXML
    protected TextField nome;

    @FXML
    protected TextField peso;

    @FXML
    protected TextField altura;

    @FXML
    protected DatePicker data;

    @FXML
    protected ComboBox nacionalidade;

    @FXML
    protected ChoiceBox modalidades;

    @FXML
    protected ChoiceBox equipa;

    @FXML
    protected ChoiceBox genero;
    @FXML
    protected ChoiceBox posicao;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Atleta atleta = new Atleta();
        String nomeAtleta = nome.getPromptText();
        atleta.setNome(nomeAtleta);
        atleta.setPeso(Double.valueOf(peso.getPromptText()));
        atleta.setAltura(Double.valueOf(altura.getPromptText()));
        atleta.setDataNascimento(java.sql.Date.valueOf(data.getPromptText()));

        if (!nome.getText().isEmpty()){
            atleta.setNome(nome.getText());
            nomeAtleta = nome.getText();
        }

        if (!peso.getText().isEmpty()){
            atleta.setPeso(Double.valueOf(peso.getText()));
        }

        if (!altura.getText().isEmpty()){
            atleta.setAltura(Double.valueOf(altura.getText()));
        }

        if (data.getValue() != null){
            atleta.setDataNascimento(java.sql.Date.valueOf(data.getValue()));
        }

        atleta.setId(AtletaBLL.getAtleta(nome.getPromptText()).getId());
        atleta.setNacionalidade((String) nacionalidade.getSelectionModel().getSelectedItem());
        atleta.setGenero((String) genero.getValue());
        atleta.setModalidade(ModalidadeBLL.getModalidade((String) modalidades.getSelectionModel().getSelectedItem()));
        atleta.setEquipa(EquipasBLL.getEquipa((String) equipa.getValue()));
        atleta.setPosicao(String.valueOf(posicao.getValue()));



        AtletaBLL.updateAtleta(atleta);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-atleta.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<String> modalidade = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);

        genero.getItems().addAll(StringGeneros.generos());

        List<Posicao> posicoes = PosicaoBLL.getPosicoes();
        List<String> nomePosicoes = new ArrayList<>();

        for (Posicao nome: posicoes){
            nomePosicoes.add(nome.getNome());
        }

        List<Equipa> equipas = EquipasBLL.getEquipas();
        List<String> nomeEquipa = new ArrayList<>();

        for (Equipa equipa: equipas){
            nomeEquipa.add(equipa.getNome());
        }

        posicao.getItems().addAll(nomePosicoes);

        equipa.getItems().addAll(nomeEquipa);

        nome.setPromptText(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getNome());
        modalidades.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getModalidade().getNome());

        Date data_nascimento = AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getDataNascimento();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        nacionalidade.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getNacionalidade());

        genero.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getGenero());

        posicao.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getPosicao());

        equipa.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getEquipa().getNome());

        altura.setPromptText(String.valueOf(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getAltura()));

        peso.setPromptText(String.valueOf(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getPeso()));


        //adicionar pais à choiceBox nacionalidade
        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        nacionalidade.getItems().addAll(paises);
        nacionalidade.setVisibleRowCount(11);

    }


}
