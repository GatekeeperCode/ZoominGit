package edu.gcc.comp350.zoomin;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AIController implements Initializable {
    private ObservableList<String> classList = FXCollections.observableArrayList();
    String session;
    private Stage stage = null;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> times;
    @FXML
    ListView<String> listView;
    @FXML
    ChoiceBox Season;
    @FXML
    ChoiceBox Year;

    @FXML
    private void handleHomeButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    private void handleSubmitButton(ActionEvent event) throws IOException {
        if (Year.getValue() != null && Season.getValue() != null
                && !listView.getItems().isEmpty()){
            System.out.println(Season.getValue() + " " + Year.getValue());
            AISuggestion sugg = new AISuggestion("temp", Season.getValue() + "," + Year.getValue(), GUIDriver.collection);
            ArrayList<String> courses = new ArrayList<>(listView.getItems());
            ArrayList<String> hours = new ArrayList<>(times.getItems());
//        GUIDriver.schedList.addAll(sugg.generateSchedule(courses, hours).getSchedule().values());
            CalendarController.onlineSlots.clear();
            CalendarController.timeSlots.clear();
            GUIDriver.schedList.clear();
            Schedule s = sugg.generateSchedule(courses, hours);
            GUIDriver.openedSchedule = s.getScheduleName();
            System.out.println(s.getSchedule().values());
            for (Course c: s.getSchedule().values()) {
                GUIDriver.schedList.add(c);
                c.setOnSchedule(true);
            }
            GUIDriver.selectYear = (int) Year.getValue();
            GUIDriver.selectSemester = (String) Season.getValue();
            root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else if(Year.getValue() == null && Season.getValue() == null){
            showFlashMessage("You Must enter a Semester and Year!");
        } else if (listView.getItems().isEmpty()) {
            showFlashMessage("You Must enter at least one course!");
        }

    }

    @FXML
    private void handleCancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    private void handleAddButton(ActionEvent event) throws IOException {
        listView.getItems().add("Enter Course name");
    }
    @FXML
    private void handleAddTimesButton(ActionEvent event) throws IOException {
        times.getItems().add("Enter time");
    }

    private void showFlashMessage(String message) {
        Stage window = new Stage();
        window.initStyle(StageStyle.TRANSPARENT);
        Text output = new Text(message);
        VBox layout = new VBox(10, output);
        output.setStyle("-fx-font-size: 24;");
        layout.setPadding(new Insets(9));
        layout.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(3), Insets.EMPTY)));
        window.setScene(new Scene(layout, Color.TRANSPARENT));
        window.setAlwaysOnTop(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, evt -> window.show(), new KeyValue(layout.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(200), new KeyValue(layout.opacityProperty(), 1.0)),
                new KeyFrame(Duration.millis(2600), new KeyValue(layout.opacityProperty(), 1.0)),
                new KeyFrame(Duration.millis(3000), new KeyValue(layout.opacityProperty(), 0.2)));
        timeline.setOnFinished(evt -> window.close());
        timeline.play();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Season.getItems().addAll("Fall", "Spring");
        Year.getItems().addAll(2018, 2019, 2020);
        listView.setCellFactory(TextFieldListCell.forListView());
        times.setCellFactory(TextFieldListCell.forListView());
    }
}
