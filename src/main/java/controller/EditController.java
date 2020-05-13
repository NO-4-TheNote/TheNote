package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static handler.NoteHandler.getNoteContent;

public class EditController implements Initializable {
    private final String catalog;
    private final String note;

    @FXML
    TextField editTitle;
    @FXML
    HTMLEditor editContent;

    public EditController(String catalog, String note) {
        this.catalog = catalog;
        this.note = note;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editTitle.setText(note);
        try {
            editContent.setHtmlText(getNoteContent(catalog, note));
        } catch (IOException e) {
            // todo: show alert
            editContent.setHtmlText("");
            e.printStackTrace();
        }
        // todo: init rich editor
    }

}
