package edu.gcc.comp350.zoomin;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController{

    @FXML
    BorderPane mainPane;
    @FXML
    Pane homeCenter;
    @FXML
    Pane homeBottom;

    @FXML
    private void handleCreateButton(ActionEvent event){
        System.out.println("clicked create");
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPane("CreateDefault");
        Pane botpane = loader.getPane("ClassList");
        mainPane.setCenter(pane);
        mainPane.setBottom(botpane);
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.sizeToScene();
    }

    @FXML
    private void handleHomeButton(ActionEvent event){
        System.out.println("clicked home");
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPane("GUI");
        mainPane.setCenter(homeCenter);
        mainPane.setBottom(homeBottom);
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.sizeToScene();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    }

}
