package GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import Parser.textReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

	public static String fileAsString;
	File inputFile = null; // Input file Object
	File outputFile = new File("src/sample/ConvertedSong.xml"); // Output file Object

	@FXML
	private ListView listview;

	@FXML
	private TextArea textview;

	/*
	 * Choose file button
	 */
	@FXML
	public void FileChooserHandler(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt")); // filters only text
																								// files
		try {
			inputFile = fc.showOpenDialog(null); // opens file explorer
			setFileAsString(inputFile.toString()); // Converts the file to a string
		} catch (Exception e) {
		}
		System.out.println(fileAsString);

		if (inputFile != null) {
			// adds file to listview
			listview.getItems().add(inputFile.getName());
		}
	}

	@FXML
	public void PreviewHandler(ActionEvent event) {
		try {
			if (listview != null) {
				Desktop.getDesktop().open(inputFile);
			}
		} catch (Exception e) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("File not found.");
			errorAlert.setContentText("Please choose a file first before you preview");
			errorAlert.showAndWait();
		}
	}

	/*
	 * Copy from clipboard button
	 */
	@FXML
	public void CopyClipBoardHandler(ActionEvent event) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		String clipBoardText = clipboard.getString();
		textview.setText(clipBoardText);
	}

	/*
	 * Goes back to welcome scene
	 */
	@FXML
	public void BackToWelcome(ActionEvent event) throws IOException {
		inputFile = null;
		Parent back = FXMLLoader.load(getClass().getResource("GUI/WelcomeScene.fxml"));
		Scene welcomeScene = new Scene(back);
		welcomeScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(welcomeScene);
		window.show();
	}

	/*
	 * switches from welcome scene to conversion complete scene
	 */
	@FXML
	public void ConvertHandler(ActionEvent event) throws IOException {
		try {
			if (listview != null) {
				textReader.readTabFile(fileAsString); // passes the input file to the parser
				Parent conversionCompleteParent = FXMLLoader
						.load(getClass().getResource("GUI/ConversionComplete.fxml"));
				Scene ClipBoardScene = new Scene(conversionCompleteParent);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(ClipBoardScene);
				window.show();
			}
		} catch (Exception e) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("File not found.");
			errorAlert.setContentText("Please choose a file before you convert.");
			errorAlert.showAndWait();
		}
	}

	/*
	 * This button will open the converted xml file
	 */
	@FXML
	public void OpenXMLHandler(ActionEvent event) throws IOException {
		Desktop.getDesktop().open(outputFile);
	}

	public String getFileAsString() {
		return fileAsString;
	}

	public void setFileAsString(String fileAsString) {
		this.fileAsString = fileAsString;
	}
}
