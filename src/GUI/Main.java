
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
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        primaryStage.setTitle("Tablature Converter");
        Scene scene = new Scene(root, 1200,800);
        //Shortcut for closing the program
        // closes the program when the "esc" button is pressed...
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.println("You closed the program by clicking on the: " + ke.getCode() + "button");
                    Platform.exit();
                }
            }
        });
        
        scene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
     
    
     Scene welcomeScreen;
    // Saved the actual scene and then if the filename argument corresponds to an already created scene then you can switch scene instead of creating a new one
     void launchScreen(String fileName) {
        if(fileName.equals("controller") && welcomeScreen != null){
             /*if we want to open up welcomeScreen and it has been created before (meaning it's not null) then open the already existing scene.*/
        		Stage primaryStage = null;
				primaryStage.setTitle("Tablature Converter");
                primaryStage.setScene(welcomeScreen);
                primaryStage.show();
               

        															}
        else{
            fileName = "/screens/" + fileName + ".fxml";
            try {
                Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));         
                FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setTitle("Tablature Coverter");
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setOnCloseRequest(t -> {
                    Platform.exit();
                    System.exit(420);
            });
        } catch (IOException e) {
            e.printStackTrace();
        	}
     									}




     }
}