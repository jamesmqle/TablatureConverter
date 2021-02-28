
package GUI;

//import java.io.IOException;
//import java.util.EventObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyCodeCombination;
//import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {
	Stage primaryStage;
	public static void main(String[] args) {
        launch(args);
	}

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/WelcomeScene.fxml"));
		primaryStage.setTitle("Tablature Converter");
		Scene welcomeScene = new Scene(root, 1200, 800);
		welcomeScene.getStylesheets()
				.add(getClass().getClassLoader().getResource("GUI/WelcomeStyleSheet.css").toExternalForm());
		primaryStage.setScene(welcomeScene);
		
        //Shortcut for closing the program
        // closes the program when the "esc" button is pressed...
        welcomeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.println("You closed the program by clicking on the: " + ke.getCode() + "button");
                    Platform.exit();
                }
            }
        });
        primaryStage.show();
        primaryStage.setResizable(false);   
    }
     								


     
}