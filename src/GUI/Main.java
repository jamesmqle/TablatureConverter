
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/sample.fxml"));
        primaryStage.setTitle("Tablature Converter");
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("GUI/StyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}