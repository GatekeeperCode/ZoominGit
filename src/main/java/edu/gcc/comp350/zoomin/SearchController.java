package edu.gcc.comp350.zoomin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
            courseList = readInFile(filename);
        } catch(Exception e) {
            System.out.println(e);
        }
        CourseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        Professor.setCellValueFactory(new PropertyValueFactory<Course, String>("professor"));
        Days.setCellValueFactory(new PropertyValueFactory<Course, String>("days"));
        time.setCellValueFactory(new PropertyValueFactory<Course, String>("time"));
        CreditHours.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credits"));
        CourseList.setItems(getCourses());

        addButtonToTable();
    }
    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
        return courses;
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
//                            try {
//                                Course toAdd = (Course) ((TableCell) ((Button) event.getSource()).getParent()).getTableRow().getItem();
//                                if (!GUIDriver.schedList.contains(toAdd)) {
//                                    GUIDriver.schedList.add(toAdd);
//                                    toAdd.setOnSchedule(true);
//                                    btn.setText("Remove");
//                                } else {
//                                    GUIDriver.schedList.remove(toAdd);
//                                    btn.setText("Add Course");
//                                }
//                            } catch (Exception e) {
//                            }
                            Course course = (Course) getTableRow().getItem();
                            if (!course.isOnSchedule()) {
                                GUIDriver.schedList.add(course);
                                course.setOnSchedule(true);
                                btn.setText("Remove");
                            } else {
                                for (Course c: GUIDriver.schedList) {
                                    if(course.getCourseCode().equals(c.getCourseCode()) && course.getTime().equals(c.getTime())){
                                        GUIDriver.schedList.remove(c);
                                    }
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
                                if(course.getCourseCode().equals(c.getCourseCode()) && course.getTime().equals(c.getTime())) {
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
