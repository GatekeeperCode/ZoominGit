package edu.gcc.comp350.zoomin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.*;
import java.io.IOException;

public class GUIDriver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add(getClass().getResource( "CSS/modifications.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource( "CSS/BootstrapTest.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoomin");
        primaryStage.show();
    }
}
