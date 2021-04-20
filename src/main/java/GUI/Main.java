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
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/AppScene.fxml"));
		primaryStage.setTitle("Tablature Converter");
		Scene scene = new Scene(root, 1200, 800);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/AppScene.css").toExternalForm());
		scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/ErrorHighlighting.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}
