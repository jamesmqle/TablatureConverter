package GUI;

import XMLTags.Common.ConvertedSongTest;
import Parser.textReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Label;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Parser.textReader.*;

/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller {

    File inputFile = null; // Input file Object
    File outputFile = new File("src/main/resources/sample/convertedSong.xml"); // Output file Object
    File textFile = new File("src/main/resources/sample/textarea.txt");

    int warningError = 0, criticalError = 0; // this will handle errors

//    @FXML
//    private Label progress;
//    public static Label label;
//
//    @FXML
//    private ProgressBar progressBar;
//    public static ProgressBar statProgressBar;

    @FXML
    public Button ConvertButton, xml;

    @FXML
    private ListView listview;

    @FXML
    public TextArea textview, title, timeSignature, XMLTextArea;


    /**
     * This method uploads a file
     *
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void FileChooserHandler(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt")); // filters only text files
        try {
            inputFile = fc.showOpenDialog(null); // opens file explorer
        } catch (Exception e) {
        }

        if (inputFile != null) {
            listview.getItems().add(inputFile.getName()); // adds file to listview

            // If the user uploads a second file, the second file will override the first file
            if (listview.getItems().size() == 2) {
                listview.getItems().remove(0);
            }

        }

        displayTablature(); // displays file content to textarea
    }

    /**
     * This method displays the text from the file to the textarea
     *
     * @throws FileNotFoundException
     */
    private void displayTablature() throws IOException {
        try {
            textview.clear();
            Scanner s = new Scanner(new File(inputFile.toString())).useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    textview.appendText(s.nextInt() + " "); // display the found integer
                }
                else{
                    textview.appendText(s.next() + " ");
                }
                textview.appendText("\n");
            }

            textAreaCheck();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Makes the convert button blue when tablature is displayed in textarea
     */
    public void textAreaCheck() {

        if (textview != null) { // makes the convert button blue when tablature is displayed in textarea
            ConvertButton.setTextFill(Paint.valueOf("blue"));
        } else {
            ConvertButton.setTextFill(Paint.valueOf("white"));
        }
    }

    /**
     * This method creates a file from the textarea
     *
     * @param textFile
     * @param textview
     */
    public void textViewToFile(File textFile, TextArea textview) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(textFile));
            bf.write(textview.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(textFile.toString());
        System.out.println(outputFile.toString());
    }

    /**
     * Go to conversion complete screen
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void ConvertHandler(ActionEvent event) throws IOException {
        if (textview.getText() != "") { // gives error message if textarea is empty
            textViewToFile(textFile, textview);
            if (CriticalErrorHandler() == 0) {
                ConvertedSongTest.createXML(textReader.readTabFile2(textFile.toString()), outputFile.toString(), textFile.toString()); // Passes textarea file through parser
                Parent conversionCompleteParent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/ConversionComplete.fxml"));
                Scene ClipBoardScene = new Scene(conversionCompleteParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(ClipBoardScene);
                window.show();
                WarninglErrorHandler();
            }
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Conversion Failed");
            errorAlert.setContentText("The textarea is empty. Please input tablature before converting.");
            errorAlert.showAndWait();
            errorAlert.close();
        }
    }

    /**
     * This method will handle CRITICAL errors within the given text tablature
     *
     * @throws FileNotFoundException
     */
    @FXML
    public int CriticalErrorHandler() throws FileNotFoundException {
        return criticalError;
    }

    /**
     * This method will handle CRITICAL errors within the given text tablature
     *
     * @throws FileNotFoundException
     */
    @FXML
    public int WarninglErrorHandler() throws FileNotFoundException {

        warningError = TabIsOK(getTab(textFile.toString()), detectInstrument(textFile.toString())); // this will assign the error

        if (warningError == 1) { // error 1 if all lines are not the same length
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("WARNING!");
            errorAlert.setContentText("All lines were not the same length. This may have affected the output.");
            errorAlert.showAndWait();
        } else if (warningError == 2) { // error 2 if incorrect tuning letters
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("WARNING!");
            errorAlert.setContentText("Incorrect tuning letters. This may have affected the output.");
            errorAlert.showAndWait();
        }

        System.out.println("Controller Error: " + warningError);
        return warningError;
    }

    /**
     * This button will display the converted XML to the textarea
     */
    public void displayXML(ActionEvent event) throws IOException {
        try {
            Scanner s = new Scanner(new File(outputFile.toString())).useDelimiter("'");
            while (s.hasNext()) {
                if (s.hasNextByte()) { // check if next token is an int
                    XMLTextArea.appendText(s.nextLine() + " "); // display the found integer
                } else {
                    XMLTextArea.appendText(s.next() + " "); // else read the next token
                }
                XMLTextArea.appendText("\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }


    /**
     * This button in conversion complete scene will save the xml file to local desktop
     *
     * @param event
     */
    @FXML
    public void SaveXMLFileHandler(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML File", "*.XML"));
        File dest = fc.showSaveDialog(null);
        if (dest != null) {
            try {
                Files.copy(outputFile.toPath(), dest.toPath());
            } catch (IOException ex) {
                // handle exception...
            }
        }
    }

    /**
     * Go back to welcome scene
     *
     * @param event
     * @throws IOException
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

}
