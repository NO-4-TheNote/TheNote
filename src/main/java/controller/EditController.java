package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static handler.NoteHandler.*;

// todo: 附件
public class EditController implements Initializable {
    private final String catalog;
    private String note;

    @FXML
    TextField editTitle;
    @FXML
    HTMLEditor editContent;
    @FXML
    MenuItem saveButton;

    public EditController(String catalog, String note) {
        this.catalog = catalog;
        this.note = note;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editTitle.setText(note);
        editTitle.focusedProperty().addListener((obs, valueOld, valueNew) -> {
            if (!valueNew) {
                String note = editTitle.getText();
                if (!renameNote(catalog, this.note, note)) {
                    // todo: show alert
                } else {
                    this.note = note;
                }
            }
        });

        saveButton.setOnAction(evt -> {
            try {
                saveNoteContent(catalog, note, editContent.getHtmlText());
            } catch (IOException e) {
                e.printStackTrace();
                // todo: show alert
            }
        });

        try {
            editContent.setHtmlText(getNoteContent(catalog, note));
        } catch (IOException e) {
            // todo: show alert
            editContent.setHtmlText("");
            e.printStackTrace();
        }
    }
}
