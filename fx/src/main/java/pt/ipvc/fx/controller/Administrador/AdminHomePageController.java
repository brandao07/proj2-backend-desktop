package pt.ipvc.fx.controller.Administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pt.ipvc.backend.bll.UtilizadorBLL;
import pt.ipvc.backend.entity.AdministradoresEntity;
import pt.ipvc.backend.entity.GestoresEntity;
import pt.ipvc.backend.entity.UtilizadoresEntity;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminHomePageController{
    @FXML
    private BarChart<? , ?> barrasAdminsGestores;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAdicionarDados;

    @FXML
    private Button btnConsultarDados;

    @FXML
    private Button btnEditarDados;

    @FXML
    private Button btnRemoverDados;

    @FXML
    private Label labelNome;

    @FXML
    private LineChart<?, ?> lineChartClientes;


    public void setBtnAdicionarDados(ActionEvent event){
        ControladorGlobal.chamaScene("adicionarDados/admin-adicionar-dados-arbitro.fxml", event);
    }
}
