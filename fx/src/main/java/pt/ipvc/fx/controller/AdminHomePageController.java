package pt.ipvc.fx.controller;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminHomePageController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNome.setText(UtilizadorBLL.getUserLog().getUsername());
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Administradores");
        series2.setName("Gestores");
        series1.getData().add(new XYChart.Data("Numero de Administradores", AdministradoresEntity.n_total_admistradores()));
        series2.getData().add(new XYChart.Data("Numero de Gestores", GestoresEntity.n_total_gestores()));
        barrasAdminsGestores.getData().addAll(series1, series2);

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Utilizadores");
    }

    public void setBtnAdicionarDados(ActionEvent event){
        ControladorGlobal.chamaScene("admin-adicionar-dados.fxml", event);
    }
}
