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

public class CalendarController  implements Initializable {
    private Stage stage = null;
    private Scene scene;
    private Parent root;

    private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();



    @FXML GridPane schedule;
    @FXML FlowPane M, T, W, R, F;
    @FXML Label Credits;
    @FXML
    private void handleHomeButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleDeleteButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Schedule?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            deleteAndLeave();
        }else{
            alert.close();
        }
    }
    @FXML
    private void handleSaveButton(ActionEvent event) throws IOException {
        //Check to see if the person has between 12 and 17 credits.
        //If not, send a warning & confirmation.
        int numHours = 0;
        for (Course c : GUIDriver.schedList) {
            numHours += c.getCredits();
        }
        if (numHours > 17 || numHours < 12) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "NOTE: You do not have 12 - 17 credit hours. Continue?",
                    ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() != ButtonType.YES) {
                alert.close();
                return;
            }
            alert.close();
        }


        Schedule schedule = new Schedule("test", "test");
        for (Course c: GUIDriver.schedList){
            schedule.addClassToSchedule(c);
        }
        TextInputDialog td = new TextInputDialog("Enter a filename");
        td.setHeaderText("Save your Schedule");
        td.setGraphic(null);
        td.showAndWait();
        System.out.println(td.getEditor().getText());
        saveSchedule(schedule, td.getEditor().getText());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int totalHours = 0;
        for (Course c: GUIDriver.schedList) {
            String courseCode = c.getDepartment() + " " +  c.getCourseCode();
            addTimeslots(convertToTime(c), c.getDays(), courseCode);
            totalHours += c.getCredits();
        }
        Credits.setText("Credit hours: " + totalHours);
        Collections.sort(timeSlots);
        placeClasses();
    }

    private void placeClasses(){
        int fonsize = 16;
        for (TimeSlot time : timeSlots) {
            Label l;
            if(time.days.contains("M")){
                l = new Label(time.getCourseCode() + "\n" + time.getStart() + " - " + time.getStart());
                l.setPadding(new Insets(20));
                l.setFont(Font.font("System", fonsize));
                l.setStyle("-fx-background-color: #ff152a; -fx-text-fill: WHITE; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-background-radius: 10px;");
                M.getChildren().add(l);
            } if (time.days.contains("T")) {
                l = new Label(time.getCourseCode() + "\n" + time.getStart() + " - " + time.getStart());
                l.setPadding(new Insets(20));
                l.setFont(Font.font("System", fonsize));
                l.setStyle("-fx-background-color: #ff152a; -fx-text-fill: WHITE; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-background-radius: 10px;");
                T.getChildren().add(l);
            }   if (time.days.contains("W")) {
                l = new Label(time.getCourseCode() + "\n" + time.getStart() + " - " + time.getStart());
                l.setPadding(new Insets(20));
                l.setFont(Font.font("System", fonsize));
                l.setStyle("-fx-background-color: #ff152a; -fx-text-fill: WHITE; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-background-radius: 10px;");
                W.getChildren().add(l);
            } if (time.days.contains("R")) {
                l = new Label(time.getCourseCode() + "\n" + time.getStart() + " - " + time.getStart());
                l.setPadding(new Insets(20));
                l.setFont(Font.font("System", fonsize));
                l.setStyle("-fx-background-color: #ff152a; -fx-text-fill: WHITE; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-background-radius: 10px;");
                R.getChildren().add(l);
            } if (time.days.contains("F")) {
                l = new Label(time.getCourseCode() + "\n" + time.getStart() + " - " + time.getStart());
                l.setPadding(new Insets(20));
                l.setFont(Font.font("System", fonsize));
                l.setStyle("-fx-background-color: #ff152a; -fx-text-fill: WHITE; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-background-radius: 10px;");
                F.getChildren().add(l);
            }
        }
    }
    private ArrayList<LocalTime> convertToTime(Course c){
        ArrayList<LocalTime> out = new ArrayList<LocalTime>();
        DateFormat format = new SimpleDateFormat("KK:mm a");
        int count = 0;
        String string = c.getTime();
        if(!string.equals(" - ")) {
            String[] test = string.split(" - ");
            LocalTime startTime = null;
            LocalTime endTime = null;
            for (String j : test) {
                j = j.replace(":00 ", " ");
                Date date = null;
                try {
                    date = format.parse(j);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (count == 0) {
                    startTime = new Time(date.getTime()).toLocalTime();
                } else {
                    endTime = new Time(date.getTime()).toLocalTime();
                }
                count++;
            }
            out.add(startTime);
            out.add(endTime);
        }
        else{
            LocalTime lT = new Time(01, 00,00).toLocalTime();
            out.add(lT);
            out.add(lT);
        }
        return out;
    }

    private void addTimeslots(ArrayList<LocalTime> times, String days, String courseCode){
            TimeSlot slot = new TimeSlot(times.get(0), times.get(1), days, courseCode);
            if (!timeSlots.contains(slot)) {
                timeSlots.add(slot);
        }
    }
    private void deleteAndLeave() throws IOException {
        if(GUIDriver.schedList.size() > 0 ){
            for (Course c: GUIDriver.schedList) {
                c.setOnSchedule(false);
            }
            M.getChildren().clear();
            T.getChildren().clear();
            W.getChildren().clear();
            R.getChildren().clear();
            F.getChildren().clear();
            GUIDriver.schedList.clear();
        }

        root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage = (Stage) schedule.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    class TimeSlot  implements Comparable<TimeSlot>{
        private LocalTime start;
        private LocalTime end;
        private String days;
        private String courseCode;
        TimeSlot(LocalTime start, LocalTime end, String days, String courseCode){
            this.start = start;
            this.end = end;
            this.days = days;
            this.courseCode = courseCode;
        }
        public void setEnd(LocalTime end) {this.end = end;}
        public void setStart(LocalTime start) {this.start = start;}
        public LocalTime getEnd() {return end;}
        public LocalTime getStart() {return start;}
        public String getDays() {return days;}
        public void setDays(String days) {this.days = days;}
        public String getCourseCode() {return courseCode;}
        public void setCourseCode(String courseCode) {this.courseCode = courseCode;}

        @Override
        public int compareTo(TimeSlot other) {
            return this.start.compareTo(other.start);
        }
    }
}
