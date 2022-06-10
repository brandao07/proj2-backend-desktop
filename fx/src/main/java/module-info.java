module pt.ipvc.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires pt.ipvc.backend;
    requires annotations;
    exports pt.ipvc.fx;

    opens pt.ipvc.fx to javafx.fxml;
    exports pt.ipvc.fx.controller;
    opens pt.ipvc.fx.controller to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador.adicionarDados;
    opens pt.ipvc.fx.controller.Administrador.adicionarDados to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador;
    opens pt.ipvc.fx.controller.Administrador to javafx.fxml;
    exports pt.ipvc.fx.controller.Administrador.consultar_editarDados;
    opens pt.ipvc.fx.controller.Administrador to javafx.fxml;
}