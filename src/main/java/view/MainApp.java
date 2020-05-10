package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;



public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getFxml("home.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getCss("styles.css"));

        stage.setTitle("The Note");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private URL getFxml(String name) {
        return getClass().getResource("/fxml/" + name);
    }

    private String getCss(String name) {
        return getClass().getResource("/css/" + name).toExternalForm();
    }
}
