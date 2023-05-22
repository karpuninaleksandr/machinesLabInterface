module ru.ac.uniyar.machineslabinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens ru.ac.uniyar.application to javafx.fxml;
    exports ru.ac.uniyar.application;
    exports ru.ac.uniyar.objects;
    opens ru.ac.uniyar.objects to javafx.fxml;
    exports ru.ac.uniyar.utils;
    opens ru.ac.uniyar.utils to javafx.fxml;
}