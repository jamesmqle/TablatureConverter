package GUI;

import XMLTags.Common.ConvertedSongTest;
import Parser.textReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import static Parser.textReader.*;

/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller {

    File inputFile = null; // Input file Object
    File outputFile = new File("src/main/resources/sample/ConvertedSong.xml"); // Output file Object
    File textFile = new File("src/main/resources/sample/textarea.txt");

    int error = 0; // this will handle errors

    @FXML
    public Button ConvertButton;

    @FXML
    private ListView listview;

    @FXML
    public TextArea textview;

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
                } else {
                    textview.appendText(s.next() + " "); // else read the next token
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
    private void textAreaCheck() {

        if (textview != null) { // makes the convert button blue when tablature is displayed in textarea
            ConvertButton.setTextFill(Paint.valueOf("blue"));
        }
        else{
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
        if (textview != null) { // gives error message if textarea is empty
            textViewToFile(textFile, textview);
            if(ErrorHandler() == 0){
                ConvertedSongTest.createXML(textReader.readTabFile2(textFile.toString()), outputFile.toString(), inputFile.toString()); // Passes textarea file through parser
                Parent conversionCompleteParent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/ConversionComplete.fxml"));
                Scene ClipBoardScene = new Scene(conversionCompleteParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(ClipBoardScene);
                window.show();
            }
        }
    }

    /**
     * This method will handle errors within the given text tablature
     * @throws FileNotFoundException
     */
    @FXML
    public int ErrorHandler() throws FileNotFoundException {

        error = TabIsOK(getTab(textFile.toString()),detectInstrument(textFile.toString())); // this will assign the error

        if(error == 1){ // error 1 if all lines are not the same length
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Tablature Format Error.");
            errorAlert.setContentText("Please make sure all lines are the same length.");
            errorAlert.showAndWait();
        }
        else if(error == 2){ // error 2 if incorrect tuning letters
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Tablature Format Error.");
            errorAlert.setContentText("Please make sure all lines have the correct tuning letter");
            errorAlert.showAndWait();
        }

        System.out.println("Controller Error: " + error);
        return error;
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
     * Opens the converted xml file
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void OpenXMLHandler(ActionEvent event) throws IOException {
        Desktop.getDesktop().open(outputFile);
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

}
