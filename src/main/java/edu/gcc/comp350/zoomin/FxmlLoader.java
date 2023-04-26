package edu.gcc.comp350.zoomin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private Pane pane;

    public Pane getPane(String fileName){
        try {
            URL fileURL = GUIDriver.class.getResource(fileName+ ".fxml");
            System.out.println(fileURL);
            if (fileURL == null){
                throw new java.io.FileNotFoundException("FXML File cannot be found");
            }

            pane = new FXMLLoader().load(fileURL);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
         return pane;
        }
}
