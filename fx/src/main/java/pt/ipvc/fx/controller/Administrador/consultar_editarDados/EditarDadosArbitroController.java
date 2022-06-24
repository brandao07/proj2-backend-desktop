package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class EditarDadosArbitroController implements Initializable {

    @FXML
    protected TextField nome;

    @FXML
    protected DatePicker data;

    @FXML
    protected ComboBox nacionalidade;


    @FXML
    protected ChoiceBox associacao;

    @FXML
    protected ChoiceBox categoria;

    @FXML
    protected ChoiceBox genero;

    @FXML
    protected ChoiceBox<String> modalidades;


    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Arbitro arbitro = new Arbitro();
        String nomeArbitro = nome.getPromptText();
        arbitro.setNome(nomeArbitro);
        arbitro.setDataNascimento(java.sql.Date.valueOf(data.getPromptText()));


        if (!nome.getText().isEmpty()){
            System.out.println("entrou");
            arbitro.setNome(nome.getText());
            nomeArbitro = nome.getText();
        }


        if (data.getValue() != null){
            System.out.println("Data1: " + data.getValue());
            arbitro.setDataNascimento(java.sql.Date.valueOf(data.getValue()));
        }


        arbitro.setId(ArbitroBLL.getArbitro(nome.getPromptText()).getId());
        arbitro.setNacionalidade((String) nacionalidade.getSelectionModel().getSelectedItem());
        arbitro.setAssociacao((String) associacao.getValue());
        arbitro.setCategoria((String) categoria.getValue());
        arbitro.setGenero((String) genero.getValue());
        arbitro.setModalidade(ModalidadeBLL.getModalidade(modalidades.getSelectionModel().getSelectedItem()));

    ArbitroBLL.updateArbitro(arbitro);
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-editar-dados-arbitro.fxml", event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
            LocalRepository.associacoes_portuguesas();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<String> modalidade = ((List<Modalidade>)ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);

        nome.setPromptText(ConsultarDadosArbitroController.arbitroSceneConsultar);
        modalidades.setValue(ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getModalidade().getNome());

        Date data_nascimento = ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getDataNascimento();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        nacionalidade.setValue(ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getNacionalidade());

        associacao.setValue(ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getAssociacao());

        genero.setValue(ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getGenero());

        categoria.setValue(ArbitroBLL.getArbitro(ConsultarDadosArbitroController.arbitroSceneConsultar).getCategoria());

        categoria.getItems().addAll("Internacional", "Nacional");


        ArrayList<String> lista_associacoes = new ArrayList<>();
        for (String t : LocalRepository.getMapAssosiacoesPortuguesas().values()){
            lista_associacoes.add(t);
        }
        Collections.sort(lista_associacoes);
        lista_associacoes.remove(0);
        associacao.getItems().addAll(lista_associacoes);

        genero.getItems().addAll(StringGeneros.generos());


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
