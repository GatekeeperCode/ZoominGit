package edu.gcc.comp350.zoomin;

import com.calendarfx.view.DetailedWeekView;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController{
    private Stage stage = null;
    private Scene scene;
    private Parent root;

    @FXML
    BorderPane mainPane;
    @FXML
    Pane homeCenter;
    @FXML
    Pane homeBottom;
    @FXML
    Pane schedule;
    @FXML
    HBox CourseList;

    @FXML
    private void handleCreateButton(ActionEvent event) throws IOException {
//        System.out.println("clicked create");
//        FxmlLoader loader = new FxmlLoader();
//        Pane pane = loader.getPane("CreateDefault");
//        Pane botpane = loader.getPane("ClassList");
//        mainPane.setCenter(pane);
//        mainPane.setBottom(botpane);
//        Stage stage = (Stage) mainPane.getScene().getWindow();
//        stage.sizeToScene();
        root = FXMLLoader.load(getClass().getResource("CourseSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHomeButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCalendarButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleAIButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AIWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleSaveButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleDeleteButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleEditButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CourseSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    }

}
