module TheNote {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.desktop;

    opens org.note.controller to javafx.fxml;
    opens org.note to javafx.fxml;
    exports org.note;
}