
package sample;

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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tablature Converter");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("sample/StyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}