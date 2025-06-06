package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.ArbitroBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
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
    protected ComboBox naturalidade;


    @FXML
    protected ChoiceBox genero;

    @FXML
    protected ChoiceBox<String> modalidades;

    @FXML
    protected Label usernameLabel;


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
            arbitro.setNome(nome.getText());
            nomeArbitro = nome.getText();
        }

        if (data.getValue() != null){
            arbitro.setDataNascimento(java.sql.Date.valueOf(data.getValue()));
        }

        arbitro.setId(ArbitroBLL.getArbitro(nome.getPromptText()).getId());
        arbitro.setNacionalidade((String) nacionalidade.getSelectionModel().getSelectedItem());
        arbitro.setNaturalidade((String) naturalidade.getSelectionModel().getSelectedItem());
        arbitro.setGenero((String) genero.getValue());
        arbitro.setModalidade(ModalidadeBLL.getModalidade(modalidades.getSelectionModel().getSelectedItem()));

        ArbitroBLL.updateArbitro(arbitro);
        ControladorGlobal.editarArbitro();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        naturalidade.setDisable(true);
        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<String> modalidade = ((List<Modalidade>)ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());


        modalidades.getItems().addAll(modalidade);

        nome.setPromptText(ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getNome());

        modalidades.setValue(ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getModalidade().getNome());

        Date data_nascimento = ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getDataNascimento();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));
        nacionalidade.setValue(ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getNacionalidade());
        naturalidade.setValue(ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getNaturalidade());
        genero.setValue(ArbitroBLL.getArbitroId(ConsultarDadosArbitroController.arbitroSceneConsultar).getGenero());
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
        nacionalidade.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                naturalidade.setDisable(false);

                naturalidade.getItems().clear();
                for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
                    if (nacionalidade.getSelectionModel().getSelectedItem().equals(pais)) {
                        naturalidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(pais));
                        break;
                    }
                }
                naturalidade.setVisibleRowCount(11);

            }
        });
    }
}
