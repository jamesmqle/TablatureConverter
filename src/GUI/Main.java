
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
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        primaryStage.setTitle("Tablature Converter");
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        // makes the stage full screen
        primaryStage.setMaximized(true);
        // keeps it to one size(full screen)
        primaryStage.setResizable(false);
    }
    
        
}