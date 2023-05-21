module ru.ac.uniyar.machineslabinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ru.ac.uniyar.machineslabinterface to javafx.fxml;
    exports ru.ac.uniyar.machineslabinterface;
}