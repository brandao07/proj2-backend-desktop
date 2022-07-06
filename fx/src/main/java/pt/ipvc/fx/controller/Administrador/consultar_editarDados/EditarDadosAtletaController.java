package pt.ipvc.fx.controller.Administrador.consultar_editarDados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.*;
import pt.ipvc.fx.controller.BufferedImage;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.StringGeneros;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    protected ComboBox naturalidade;

    @FXML
    protected ChoiceBox modalidades;

    @FXML
    protected ChoiceBox genero;
    @FXML
    protected ChoiceBox posicao;

    @FXML
    protected ImageView imagem;

    @FXML
    protected Button btnFoto;

    @FXML
    protected ChoiceBox equipa;

    private static String path = null;

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
        atleta.setImage(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getImage());
        atleta.setPosicao(String.valueOf(posicao.getValue()));
        atleta.setEquipa(EquipasBLL.getEquipa((String) equipa.getValue()));

        if (modalidades.getValue().equals("Ténis")){
            atleta.setPosicao(null);
            atleta.setEquipa(null);
        }


        if (!nome.getText().isEmpty()){
            atleta.setNome(nome.getText());
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

        if (path != null){
            atleta.setImage(path);
        }

        atleta.setId(AtletaBLL.getAtleta(nome.getPromptText()).getId());
        atleta.setNacionalidade((String) nacionalidade.getSelectionModel().getSelectedItem());
        atleta.setNaturalidade((String) naturalidade.getSelectionModel().getSelectedItem());
        atleta.setGenero((String) genero.getValue());
        atleta.setModalidade(ModalidadeBLL.getModalidade((String) modalidades.getSelectionModel().getSelectedItem()));

        AtletaBLL.updateAtleta(atleta);
        ControladorGlobal.editarAtleta();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-atleta.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocalRepository.paises_e_cidades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imagem.setImage(new Image(new File(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getImage()).toURI().toString()));
        Set<String> modalidade = ((List<Modalidade>) ModalidadeBLL.getModalidades()).stream().
                map(Modalidade::getNome).collect(Collectors.toSet());

        modalidades.getItems().addAll(modalidade);

        genero.getItems().addAll(StringGeneros.generos());


        nome.setPromptText(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getNome());
        modalidades.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getModalidade().getNome());

        Date data_nascimento = AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getDataNascimento();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));

        nacionalidade.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getNacionalidade());
        naturalidade.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getNaturalidade());

        equipa.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getEquipa().getNome());
        genero.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getGenero());

        posicao.setValue(AtletaBLL.getAtletaById(ConsultarDadosAtletaController.atletaSceneConsultar).getPosicao());

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

       modalidades.valueProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue ov, String t, String t1) {
               if (modalidades.getValue().equals("Ténis")){
                   posicao.setDisable(true);
                   equipa.setDisable(true);
               }else{
                   posicao.setDisable(false);
                   equipa.setDisable(false);
               }
               equipa.getItems().clear();
               posicao.getItems().clear();
               List<Equipa> listaEquipas = EquipasBLL.getEquipas();
               List<String> nomeEquipas = new ArrayList<>();
               for (Equipa eq : listaEquipas){
                   if (eq.getModalidade().getNome().equals(modalidades.getValue())){
                       nomeEquipas.add(eq.getNome());
                   }
               }

               equipa.getItems().addAll(nomeEquipas);




            }
       });



    }

    @FXML
    public void escolherFoto(ActionEvent event){

        final FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("fx/src/main/resources/pt/ipvc/fx/modalidades/"));

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All filles", "*.*"));

        File file = fileChooser.showOpenDialog(null);

        path = file.getAbsolutePath();
        path = path.substring(path.indexOf("proj2/") + 1);
        path = path.substring(path.indexOf("/") + 1);

        System.out.println(path);

        imagem.setImage(new Image(new File(path).toURI().toString()));

    }




}
