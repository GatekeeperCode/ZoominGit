package edu.gcc.comp350.zoomin;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static edu.gcc.comp350.zoomin.Main.readInFile;
import static edu.gcc.comp350.zoomin.Main.schedList;

public class SceneController implements Initializable{
    private Stage stage = null;

    private Scene scene;
    private Parent root;
    private ObservableList<String> Schedules = FXCollections.observableArrayList();
    public static ArrayList<Course> courseList = new ArrayList<>();

    @FXML
    BorderPane mainPane;
    @FXML
    Pane homeCenter;
    @FXML
    Pane homeBottom;
    @FXML
    ListView list;
    @FXML
    Button contButton;
    @FXML
    ChoiceBox year;
    @FXML
    ChoiceBox Semester;
    @FXML
    Pane choicePane;


    @FXML
    private void handleCreateButton(ActionEvent event) throws IOException {
        schedList.clear();
        Semester.getItems().addAll("Fall", "Spring");
        year.getItems().addAll(2018, 2019, 2020);
        choicePane.setVisible(true);
        choicePane.setDisable(false);
        //add Choice pane stuff here

    }

    @FXML
    private void handleSubmitButton(ActionEvent event) throws IOException {
        choicePane.setVisible(false);
        choicePane.setDisable(true);
        root = FXMLLoader.load(getClass().getResource("CourseSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCancelButton(ActionEvent event) throws IOException {
        choicePane.setVisible(false);
        choicePane.setDisable(true);
    }

    @FXML
    private void handleContinueButton(ActionEvent event) throws IOException {
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
    private void handleAIButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AIWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Schedules.clear();
        list.getItems().removeAll();
        for (File f : getResourceFolderFiles("Schedules")) {
            Schedules.add(f.getName());
        }
        if(list != null){
            list.setItems(Schedules);
        }
        if(!GUIDriver.schedList.isEmpty()){
            contButton.setDisable(false);
        } else if (GUIDriver.schedList.isEmpty()) {
            contButton.setDisable(true);
        }
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                GUIDriver.schedList.clear();
                GUIDriver.openedSchedule = newValue;
                for (Course c: GUIDriver.openSchedule(newValue).getSchedule().values()) {
                    GUIDriver.schedList.add(c);
                    c.setOnSchedule(true);
                }
                try {
                    loadCalendar();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loadCalendar() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        stage = (Stage)list.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

}
