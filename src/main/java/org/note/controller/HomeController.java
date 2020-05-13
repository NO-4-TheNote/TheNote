package org.note.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static org.note.handler.CatalogHandler.*;
import static org.note.handler.NoteHandler.*;
import static org.note.util.FileTool.*;

public class HomeController implements Initializable {
    private static final String DEFAULT_NEW_CATALOG_NAME = "new-catalog";
    private static final String DEFAULT_NEW_NOTE_NAME = "new-note";

    @FXML
    private HTMLEditor content;
    @FXML
    private TextField title;
    @FXML
    private TextField searchTextField; // todo: search
    @FXML
    private ListView<String> catalogList;
    @FXML
    private ListView<String> noteList;

    private String clipboardCatalog;
    private String clipboardNote;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCatalogListMenu();
        initNoteListMenu();

        content.getStylesheets().add(getCss("hide-editor-tool.css"));

        try {
            List<String> catalogList = getCatalogList();
            this.catalogList.getItems().addAll(catalogList);
        } catch (IOException e) {
            // todo: show alert and exit
            e.printStackTrace();
        }
    }

    public void newCatalogAction(ActionEvent evt) {
        var ref = new Object() {
            String nameNew = DEFAULT_NEW_CATALOG_NAME;
        };
        catalogList.getItems().forEach(name -> {
            if (name.equals(ref.nameNew)) {
                if (ref.nameNew.equals(DEFAULT_NEW_CATALOG_NAME)) {
                    ref.nameNew = ref.nameNew.concat("1");
                } else {
                    String last = ref.nameNew.substring(DEFAULT_NEW_CATALOG_NAME.length());
                    ref.nameNew = ref.nameNew.replace(last, String.valueOf(Integer.parseInt(last) + 1));
                }
            }
        });
        if (!createCatalog(ref.nameNew)) {
            // todo: show alert of failing create
        }

        catalogList.getItems().add(ref.nameNew);
    }

    public void catalogListClicked(MouseEvent evt) {
        try {
            String catalog = getCurrentCatalog();
            if (catalog != null) {
                this.noteList.getItems().setAll(getNoteList(catalog));
//                clearNoteContent();
            }
        } catch (IOException e) {
            // todo: show alert
            e.printStackTrace();
        }
    }

    public void initCatalogListMenu() {
        ContextMenu contextMenu = new ContextMenu();

        addMenuItem(contextMenu, "new", this::newCatalogAction);
        addMenuItem(contextMenu, "rename", event -> {
            final String catalog = getCurrentCatalog();
            if (catalog != null) {
                TextInputDialog dialog = new TextInputDialog(catalog);
                dialog.setContentText("enter new catalog name:");
                dialog.setTitle("");
                dialog.setHeaderText("");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(s -> {
                    if (existsCatalog(s)) {
                        // todo: show alert
                    } else {
                        if (!renameCatalog(catalog, s)) {
                            // todo: show alert
                        } else {
                            catalogList.getItems().remove(catalog);
                            catalogList.getItems().add(s);
                            catalogList.getSelectionModel().select(s);
                        }
                    }
                });
            }
        });
        addMenuItem(contextMenu, "delete", event -> {
            String catalog = getCurrentCatalog();
            if (catalog != null) {
                // todo: show alert
                catalogList.getSelectionModel().selectPrevious();
                catalogList.getItems().remove(catalog);
                // delete catalog and noteList
                deleteCatalog(catalog);

                clearNoteContent();

                // show next catalog's noteList
                catalog = getCurrentCatalog();
                if (catalog != null) {
                    try {
                        noteList.getItems().setAll(getNoteList(catalog));
                    } catch (IOException e) {
                        // todo: ignore?
                        e.printStackTrace();
                    }
                } else {
                    noteList.getItems().setAll(new ArrayList<>());
                }
            }
        });

        catalogList.setContextMenu(contextMenu);
    }

    public void newNoteAction(ActionEvent evt) {
        String catalog = getCurrentCatalog();
        if (catalog == null) {
            // todo: show alert
            return;
        }

        var ref = new Object() {
            String nameNew = DEFAULT_NEW_NOTE_NAME;
        };
        noteList.getItems().forEach(name -> {
            if (name.equals(ref.nameNew)) {
                if (ref.nameNew.equals(DEFAULT_NEW_NOTE_NAME)) {
                    ref.nameNew = ref.nameNew.concat("1");
                } else {
                    String last = ref.nameNew.substring(DEFAULT_NEW_NOTE_NAME.length());
                    ref.nameNew = ref.nameNew.replace(last, String.valueOf(Integer.parseInt(last) + 1));
                }
            }
        });

        if (!createNote(catalog, ref.nameNew)) {
            // todo: show alert
        }

        noteList.getItems().add(ref.nameNew);
    }

    public void noteListClicked(MouseEvent evt) throws IOException {
        showNoteContent();
    }

    public void initNoteListMenu() {
        ContextMenu contextMenu = new ContextMenu();

        addMenuItem(contextMenu, "new", this::newNoteAction);
        addMenuItem(contextMenu, "rename", event -> {
            final String note = getCurrentNote();
            if (note != null) {
                final String catalog = getCurrentCatalog();

                TextInputDialog dialog = new TextInputDialog(note);
                dialog.setTitle("");
                dialog.setHeaderText("");
                dialog.setContentText("enter new note name:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(s -> {
                    if (existsNote(catalog, s)) {
                        // todo: show alert
                    } else {
                        if (!renameNote(catalog, note, s)) {
                            // todo: show alert
                        } else {
                            noteList.getItems().remove(note);
                            noteList.getItems().add(s);
                            noteList.getSelectionModel().select(s);
                            title.setText(s);
                        }
                    }
                });
            }
        });
        addMenuItem(contextMenu, "edit", event -> {
            final String note = getCurrentNote();
            if (note != null) {
                try {
                    // pass catalog and note
                    final String catalog = getCurrentCatalog();
                    FXMLLoader loader = new FXMLLoader(getFxml("editor.fxml"));
                    loader.setControllerFactory(controllerClass -> {
                        if (controllerClass == EditController.class) {
                            return new EditController(catalog, note);
                        } else {
                            try {
                                return controllerClass.getDeclaredConstructor().newInstance();
                            } catch (Exception exc) {
                                throw new RuntimeException(exc); // just bail
                            }
                        }
                    });

                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getCss("styles.css"));

                    Stage editStage = new Stage();

                    editStage.setTitle("The Note");
                    editStage.setScene(scene);
                    editStage.show();
                    editStage.setOnCloseRequest(evt -> {
                        try {
                            showNoteContent();
                        } catch (IOException e) {
                            // ignore
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        addMenuItem(contextMenu, "copy", event -> {
            String note = getCurrentNote();
            if (note != null) {
                clipboardNote = note;
                clipboardCatalog = getCurrentCatalog();
            }
        });
        addMenuItem(contextMenu, "paste", event -> {
            String catalog = getCurrentCatalog();
            if (clipboardNote != null && clipboardCatalog != null && catalog != null) {
                if (existsNote(catalog, clipboardNote)) {
                    // todo: show alert
                } else {
                    try {
                        copyNote(clipboardCatalog, clipboardNote, catalog);
                        noteList.getItems().add(clipboardNote);
                    } catch (IOException e) {
                        // todo: show alert
                        e.printStackTrace();
                    }
                }
            }
        });
        addMenuItem(contextMenu, "delete", event -> {
            String note = getCurrentNote();
            if (note != null) {
                // todo: show alert and get confirm
                noteList.getSelectionModel().selectPrevious();
                noteList.getItems().remove(note);
                deleteNote(getCurrentCatalog(), note);

                note = getCurrentNote();
                if (note != null) {
                    try {
                        showNoteContent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    clearNoteContent();
                }
            }
        });

        noteList.setContextMenu(contextMenu);
    }

    private void addMenuItem(ContextMenu menu, String name, EventHandler<ActionEvent> value) {
        MenuItem item = new MenuItem();
        item.setText(name);
        item.setOnAction(value);

        menu.getItems().add(item);
    }

    // nullable
    private String getCurrentCatalog() {
        return catalogList.getSelectionModel().getSelectedItem();
    }

    // nullable
    private String getCurrentNote() {
        return noteList.getSelectionModel().getSelectedItem();
    }

    private void showNoteContent() throws IOException {
        String catalog = getCurrentCatalog();
        String note = getCurrentNote();
        if (catalog != null && note != null) {
            title.setText(note);
            String str = getNoteContent(catalog, note);
            content.setHtmlText(str);
        }
    }

    private void clearNoteContent() {
        title.setText("");
        content.setHtmlText("");
    }

    public void importAndExport(ActionEvent evt) {
        Desktop desktop = Desktop.getDesktop();
        try {
            File dirToOpen = new File(getDataPath());
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException | IOException iae) {
            System.out.println("File Not Found");
            // todo: show alert
        }
    }
}
