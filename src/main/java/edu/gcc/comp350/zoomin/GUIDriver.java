package edu.gcc.comp350.zoomin;

import com.google.gson.Gson;
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
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class GUIDriver extends Application {
    protected Scene StartScene;
    public static ArrayList<Course> schedList = new ArrayList<Course>();
    public static String openedSchedule;


    public static void main(String[] args) throws URISyntaxException {
        launch(args);
        System.out.println();
    }

    public static void saveSchedule(Schedule s, String name) {
        Gson gson = FxGson.create();
        String json = gson.toJson(s);
        try {
            FileWriter file = new FileWriter(Paths.get(GUIDriver.class.getResource("/Schedules/").toURI()) + "/" + name + ".json");
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteSchedule(String toDelete) {
        for (File f : getResourceFolderFiles("Schedules")) {
            if (f.getName().equals(toDelete)) {
                f.delete();
            }
        }
    }
    public static Schedule openSchedule(String filename) {
        Gson gson = new Gson();
        try {
            URI uri = GUIDriver.class.getResource("/Schedules/").toURI();
            String path = Paths.get(uri).toString() + "/";
            FileReader fr = new FileReader(path + filename);
            Schedule sched = gson.fromJson(fr, Schedule.class);
            fr.close();
            return sched;
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem loading the file: " + e.getMessage());
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        System.out.println(path);
        return new File(path).listFiles();
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
