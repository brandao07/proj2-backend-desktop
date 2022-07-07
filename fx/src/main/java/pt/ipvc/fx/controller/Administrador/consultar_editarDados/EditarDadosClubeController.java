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
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.AtletaBLL;
import pt.ipvc.backend.services.ClubeBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class EditarDadosClubeController implements Initializable {
    @FXML
    protected TextField nome;
    @FXML
    protected TextField sigla;

    @FXML
    protected TextField contacto;

    @FXML
    protected DatePicker data;

    @FXML
    protected ComboBox<String> pais;

    @FXML
    protected ComboBox<String> cidade;

    @FXML
    protected ImageView imagem;
    @FXML
    protected Button btnFoto;

    @FXML
    protected Button button;

    private static String path = null;

    @FXML
    protected Label usernameLabel;

    @FXML
    public void escolherFoto(ActionEvent event){

        final FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("fx/src/main/resources/pt/ipvc/fx/modalidades/"));

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All filles", "*.*"));

        try {
            File  file = fileChooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            path = path.substring(path.indexOf("proj2/") + 1);
            path = path.substring(path.indexOf("/") + 1);

            imagem.setImage(new Image(new File(path).toURI().toString()));
            btnFoto.setText("");
        } catch (Exception e) {
            System.out.println("Selecione uma imagem.");
        }

    }



    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    public void confirmar(ActionEvent event){
        Clube clube = new Clube();
        String nomeClube = nome.getPromptText();
        clube.setNome(nomeClube);
        clube.setSigla(sigla.getPromptText());
        clube.setContacto(contacto.getPromptText());
        clube.setDataFundacao(java.sql.Date.valueOf(data.getPromptText()));
        clube.setImage(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getImage());

        if (!nome.getText().isEmpty()){
            clube.setNome(nome.getText());
        }

        if (!sigla.getText().isEmpty()){
            clube.setSigla(sigla.getText());
        }

        if (!contacto.getText().isEmpty()){
            clube.setContacto(contacto.getText());
        }

        if (data.getValue() != null){
            clube.setDataFundacao(java.sql.Date.valueOf(data.getValue()));
        }

        if (path != null){
            clube.setImage(path);
        }

        clube.setId(ClubeBLL.getClube(nome.getPromptText()).getId());
        clube.setPais(pais.getValue());
        clube.setCidade(cidade.getValue());

        ClubeBLL.updateClube(clube);
        ControladorGlobal.editarClube();
        ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-clube.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        try {
            LocalRepository.paises_e_cidades();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        nome.setPromptText(ConsultarDadosClubeController.clubeSceneConsultar);
        sigla.setPromptText(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getSigla());
        contacto.setPromptText(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getContacto());
        Date data_nascimento = ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getDataFundacao();
        data.setPromptText(String.valueOf(Instant.ofEpochMilli(data_nascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));
        pais.setValue(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getPais());
        cidade.setValue(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getCidade());
        imagem.setImage(new Image(new File(ClubeBLL.getClube(ConsultarDadosClubeController.clubeSceneConsultar).getImage()).toURI().toString()));

        //adicionar pais à choiceBox nacionalidade
        ArrayList paises = new ArrayList<>();
        for (String pais : LocalRepository.getMapCidadesPais().keySet()) {
            if (!paises.contains(LocalRepository.getMapCidadesPais().get(pais))) {
                paises.add(pais);
            }
        }
        pais.getItems().addAll(paises);
        pais.setVisibleRowCount(11);

        pais.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                cidade.getItems().clear();
                for (String p : LocalRepository.getMapCidadesPais().keySet()) {
                    if (pais.getSelectionModel().getSelectedItem().equals(p)) {
                        cidade.getItems().addAll(LocalRepository.getMapCidadesPais().get(p));
                        break;
                    }
                }
                cidade.setVisibleRowCount(11);
            }
        });
    }

}
