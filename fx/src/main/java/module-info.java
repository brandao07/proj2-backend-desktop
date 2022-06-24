module pt.ipvc.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires pt.ipvc.backend;
    requires annotations;
    requires javafx.graphics;
    requires java.sql;
    exports pt.ipvc.fx;

    opens pt.ipvc.fx to javafx.fxml;
    exports pt.ipvc.fx.controller;
    opens pt.ipvc.fx.controller to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador.adicionarDados;
    opens pt.ipvc.fx.controller.Administrador.adicionarDados to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador.consultar_editarDados;
    opens pt.ipvc.fx.controller.Administrador.consultar_editarDados to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador;
    opens pt.ipvc.fx.controller.Administrador to javafx.fxml;
    exports pt.ipvc.fx.controller.Gestor;
    opens pt.ipvc.fx.controller.Gestor to javafx.fxml;
    exports pt.ipvc.fx.controller.Gestor.criarCompeticao;
    opens pt.ipvc.fx.controller.Gestor.criarCompeticao to javafx.fxml;
    exports pt.ipvc.fx.controller.Gestor.gerirCompeticao;
    opens pt.ipvc.fx.controller.Gestor.gerirCompeticao to javafx.fxml;
    exports pt.ipvc.fx.controller.Temp;
    opens pt.ipvc.fx.controller.Temp to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador.sistema;
    opens pt.ipvc.fx.controller.Administrador.sistema to javafx.fxml;
}