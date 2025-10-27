module org.example.schoolmanagementwithsql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.schoolmanagementwithsql to javafx.fxml;
    exports org.example.schoolmanagementwithsql;
}