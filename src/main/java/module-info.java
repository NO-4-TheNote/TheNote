module TheNote {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens org.note to javafx.fxml;
    exports org.note;
}
