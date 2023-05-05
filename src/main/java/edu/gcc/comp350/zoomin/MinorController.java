package edu.gcc.comp350.zoomin;

import com.calendarfx.view.DetailedWeekView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.poi.hslf.record.CString;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static edu.gcc.comp350.zoomin.GUIDriver.saveSchedule;

public class MinorController  implements Initializable {
    private Stage stage = null;
    private Scene scene;
    private Parent root;

    @FXML
    private void handleHomeButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//
//    @FXML
//    private void handle

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}