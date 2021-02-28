
package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {
	Stage primaryStage;
	public static void main(String[] args) {
        launch(args);
	}

    @Override
    public void start(Stage primaryStage) throws Exception{
    	 // getting loader and a pane for the first scene. 
        // loader will then give a possibility to get related controller
    	FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("WelcomeScene.fxml"));
        Parent root = firstLoader.load();
        Scene firstScene = new Scene(root, 1200,800);
        firstScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        
        // getting loader and a pane for the second scene
        FXMLLoader conversionCompleteParent = FXMLLoader.load(getClass().getResource("ConversionComplete.fxml"));
        Parent conversionComplete = conversionCompleteParent.load();
        Scene ClipBoardScene = new Scene(conversionComplete);
        
        
        // injecting second scene into the controller of the first scene
        Controller firstController = (Controller) firstLoader.getController();
        firstController.setSecondScene(ClipBoardScene);
       
        
        
        //Shortcut for closing the program
        // closes the program when the "esc" button is pressed...
        firstScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.println("You closed the program by clicking on the: " + ke.getCode() + "button");
                    Platform.exit();
                }
            }
        });
        
        
        primaryStage.setScene(firstScene);
        primaryStage.show();
        primaryStage.setTitle("Tablature Converter");
        primaryStage.setResizable(false);
    }
     
    
     
     									




     
}