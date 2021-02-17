package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    public static String fileAsString;
    File file; // File Object

    @FXML
    private Button FileChooser;

    @FXML
    private Button CopyText;

    @FXML
    private ListView listview;

    @FXML
    private TextArea textview;

    @FXML
    public void PreviewHandler(ActionEvent event) {
        try {
            if (listview != null) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("File not found.");
            errorAlert.setContentText("Please choose a file first.");
            errorAlert.showAndWait();
        }
    }

    /*
    Choose file button
     */
    @FXML
    public void FileChooserHandler(ActionEvent event) {
        // creates file chooser object
        FileChooser fc = new FileChooser();
        // filters only text files
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        // opens file explorer
        file = fc.showOpenDialog(null);
        setFileAsString(file.toString()); // Converts the file to a string
        //System.out.println(fileAsString);

        if (file != null) {
            // adds file to listview
            listview.getItems().add(file.getName());
            System.out.println(file.getPath());
        }
    }

    /*
    Copy from clipboard button
     */
    @FXML
    public void CopyClipBoardHandler(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String clipBoardText = clipboard.getString();
        textview.setText(clipBoardText);
    }

    /*
    Goes back to welcome scene
     */
    @FXML
    public void BackToWelcome(ActionEvent event) throws IOException {
        Parent back = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        Scene welcomeScene = new Scene(back);
        welcomeScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    /*
  switches from file upload / clipboard scene to conversion complete scene
   */
    @FXML
    public void ConvertHandler(ActionEvent event) throws IOException {
        Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("ConversionComplete.fxml"));
        Scene ClipBoardScene = new Scene(conversionCompleteParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ClipBoardScene);
        window.show();
    }

    public String getFileAsString() {
        return fileAsString;
    }

    public void setFileAsString(String fileAsString) {
        this.fileAsString = fileAsString;
    }
}
