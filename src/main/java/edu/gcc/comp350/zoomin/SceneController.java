package edu.gcc.comp350.zoomin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController{

    @FXML
    BorderPane mainPane;

    @FXML
    private void handleCreateButton(ActionEvent event){
        System.out.println("clicked load create scene");
        FxmlLoader object = new FxmlLoader();
        Pane pane = object.getPane("CreateDefault");
        mainPane.setCenter(pane);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    }

}
