module org.example.schoolmanagementwithsql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.schoolmanagementwithsql to javafx.fxml;
    exports org.example.schoolmanagementwithsql;
    exports org.example.schoolmanagementwithsql.controllers;
    opens org.example.schoolmanagementwithsql.controllers to javafx.fxml;
}