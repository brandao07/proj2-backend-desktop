module pt.ipvc.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires pt.ipvc.backend;
    exports pt.ipvc.fx;

    opens pt.ipvc.fx to javafx.fxml;
}