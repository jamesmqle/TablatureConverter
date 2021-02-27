package GUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
import Parser.textReader;

import javax.swing.*;

/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller {

    public static String fileAsString;
    File inputFile = null; // Input file Object
    File outputFile = new File("src/main/resources/sample/ConvertedSong.xml"); // Output file Object

    @FXML
    private ListView listview;

    @FXML
    private TextArea textview;

    /**
     * This method uploads a file
     *
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void FileChooserHandler(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt")); // filters only text files
        try {
            inputFile = fc.showOpenDialog(null); // opens file explorer
            setFileAsString(inputFile.toString()); // Converts the file to a string
        }
        catch (Exception e) {
        }
        System.out.println(fileAsString);

        if (inputFile != null) {
            listview.getItems().add(inputFile.getName()); // adds file to listview

            // If the user uploads a second file, the second file will override the first file
            if (listview.getItems().size() == 2) { listview.getItems().remove(0); }

        }
        displayTablature(); // displays file content to textarea
    }

    public void setFileAsString(String fileAsString) {
        this.fileAsString = fileAsString;
    }

    /**
     * This method displays the text from the file to the textarea
     *
     * @throws FileNotFoundException
     */
    private void displayTablature() throws FileNotFoundException {
        try {
            Scanner input = new Scanner(new File(fileAsString)).useDelimiter("\\s");
            while (input.hasNext()) {
                if (input.hasNextInt()) { // check if next token is an int
                    textview.appendText(input.nextInt() + " "); // display the found integer
                } else {
                    textview.appendText(input.next() + " "); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }


//    @FXML
//    public void PreviewHandler(ActionEvent event) {
//        try {
//            if (listview != null) {
//                Desktop.getDesktop().open(inputFile);
//            }
//        } catch (Exception e) {
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setHeaderText("File not found.");
//            errorAlert.setContentText("Please choose a file first before you preview");
//            errorAlert.showAndWait();
//        }
//    }

    /**
     * Copy and paste to text area
     *
     * @param event
     */
    @FXML
    public void CopyClipBoardHandler(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String clipBoardText = clipboard.getString();
        textview.setText(clipBoardText);
    }

    /**
     * Go back to welcome scene
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void BackToWelcome(ActionEvent event) throws IOException {
        inputFile = null;
        Parent back = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        Scene welcomeScene = new Scene(back);
        welcomeScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }

    /**
     * Go to conversion complete screen
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void ConvertHandler(ActionEvent event) throws IOException {
        try {
            if (listview != null) {
                textReader.readTabFile(fileAsString); // passes the input file to the parser
                Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("ConversionComplete.fxml"));
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

    /**
     * Opens the converted xml file
     *
     * @param event
     * @throws IOException
     */

    @FXML
    public void OpenXMLHandler(ActionEvent event) throws IOException {
        Desktop.getDesktop().open(outputFile);
    }

}
