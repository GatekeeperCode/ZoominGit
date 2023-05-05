package edu.gcc.comp350.zoomin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bson.Document;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static edu.gcc.comp350.zoomin.Main.readInFile;
import static edu.gcc.comp350.zoomin.Main.schedList;

public class SearchController implements Initializable {
    private Stage stage = null;
    private Scene scene;
    private Parent root;
    private ArrayList<Course> courseList = new ArrayList<>();
    public Schedule s = new Schedule("tempsch", "tempsem");

    @FXML TableView<Course> CourseList;
    @FXML TableColumn<Course, String> CourseCode;
    @FXML TableColumn<Course, String> CourseName;
    @FXML TableColumn<Course, String> Professor;
    @FXML TableColumn<Course, String> Days;
    @FXML TableColumn<Course, String> time;
    @FXML TableColumn<Course, Integer> CreditHours;
    @FXML TableColumn <Course, Button> Add;
    @FXML TextField SearchBar;
    @FXML ChoiceBox<String> choice;

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
    public void addButton(ActionEvent event) throws IOException {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String temp = getClass().getResource("CSV/2020-2021.csv").toExternalForm();
            String filename = "";
            for (int i = 6; i<temp.length(); i++){
                filename += temp.charAt(i);
            }

            int yearSelect = GUIDriver.selectYear;
            String semesterSelect = GUIDriver.selectSemester;
            Bson semesterFilter = Filters.regex("semester", semesterSelect);
            Bson yearFilter = Filters.eq("year", yearSelect);
            MongoCollection collection = GUIDriver.getDatabase();
            FindIterable<Document> allCourses = collection.find(Filters.and(semesterFilter, yearFilter));
            allCourses.forEach(doc -> courseList.add(new Course(doc)));

        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        CourseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        Professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professor"));
        Days.setCellValueFactory(new PropertyValueFactory<Course, String>("days"));
        time.setCellValueFactory(new PropertyValueFactory<Course, String>("time"));
        CreditHours.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credits"));
        CourseList.setItems(getCourses());

        choice.getItems().addAll("Course Code", "Course Name", "Professor");
        choice.setValue("Course Code");
        addButtonToTable();
    }
    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
        FilteredList<Course> flcourses = new FilteredList(courses, p -> true);

        SearchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choice.getValue())//Switch on choiceBox value
            {
                case "Course Code":
                    flcourses.setPredicate(p -> p.getCourseCode().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Course Name":
                    flcourses.setPredicate(p -> p.getCourseName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
                case "Professor":
                    flcourses.setPredicate(p -> p.getProfessor().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by email
                    break;
                case "Days":
                    flcourses.setPredicate(p -> p.getDays().toUpperCase().contains(newValue.toLowerCase().trim()));//filter table by email
                    break;
            }
        });
        choice.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                SearchBar.setText("");
            }
        });
        return flcourses;
    }



    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    private void addButtonToTable() {
        Callback<TableColumn<Course, Button>, TableCell<Course, Button>> cellFactory = new Callback<TableColumn<Course, Button>, TableCell<Course, Button>>() {
            @Override
            public TableCell<Course, Button> call(final TableColumn<Course, Button> param) {
                final TableCell<Course, Button> cell = new TableCell<Course, Button>() {

                    private final Button btn = new Button("Add Course");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Course course = (Course) getTableRow().getItem();
                            if (!course.isOnSchedule()) {
                                GUIDriver.schedList.add(course);
                                course.setOnSchedule(true);
                                btn.setText("Remove");
                            } else {
                                Course toRemove = null;
                                if(!GUIDriver.schedList.isEmpty()){
                                    for (Course c: GUIDriver.schedList) {
                                        if(course.getCourseCode().equals(c.getCourseCode()) && course.getTime().equals(c.getTime())
                                                && course.getCourseName().equals(c.getCourseName())){
                                            toRemove = c;
                                        }
                                    }
                                }
                                if(toRemove != null){
                                    GUIDriver.schedList.remove(toRemove);
                                }
                                course.setOnSchedule(false);
                                btn.setText("Add Course");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Course course = getTableView().getItems().get(getIndex());
                            for (Course c: GUIDriver.schedList) {
                                if(course.getCourseCode().equals(c.getCourseCode()) && course.getTime().equals(c.getTime())
                                        && course.getCourseName().equals(c.getCourseName())) {
                                    btn.setText("Remove");
                                    course.setOnSchedule(true);
                                }
                            }
                            btn.setText(course.isOnSchedule() ? "Remove" : "Add Course");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        Add.setCellFactory(cellFactory);

    }
}
