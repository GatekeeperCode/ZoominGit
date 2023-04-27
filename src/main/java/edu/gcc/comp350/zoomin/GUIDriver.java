package edu.gcc.comp350.zoomin;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GUIDriver extends Application {
    protected Scene StartScene;

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

        StartScene = new Scene(root);
        StartScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        StartScene.getStylesheets().add(getClass().getResource( "CSS/modifications.css").toExternalForm());
        StartScene.getStylesheets().add(getClass().getResource( "CSS/BootstrapTest.css").toExternalForm());
        primaryStage.setScene(StartScene);
        primaryStage.setTitle("Zoomin");
        primaryStage.show();
    }

//    @FXML
//    private void loadCreateScene(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
//    }
}
