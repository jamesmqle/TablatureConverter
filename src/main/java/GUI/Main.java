
package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/WelcomeScene.fxml"));
		primaryStage.setTitle("Tablature Converter");
		Scene scene = new Scene(root, 1200, 800);
		//Shortcut for closing the program closes the program when the "esc" button is pressed...
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.println("You closed the program by clicking on the: " + ke.getCode() + "button");
                    Platform.exit();
                }
            }
        });
		scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/WelcomeStyleSheet.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
}