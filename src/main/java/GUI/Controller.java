package GUI;

import ConvertedSong.*;
import Parser.textReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller {

    File inputFile = null; // Input file Object
    File outputFile = new File("src/main/resources/sample/ConvertedSong.xml"); // Output file Object
    File textFile = new File("src/main/resources/sample/textarea.txt");

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
    public void FileChooserHandler(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt")); // filters only text files
        try {
            inputFile = fc.showOpenDialog(null); // opens file explorer
        }
        catch (Exception e) {
        }

        if (inputFile != null) {
            listview.getItems().add(inputFile.getName()); // adds file to listview

            // If the user uploads a second file, the second file will override the first file
            if (listview.getItems().size() == 2) { listview.getItems().remove(0); }

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
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

    }

    /**
     * This method creates a file from the textarea
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
        //   try {
        if (textview != null) { // gives error message if textarea is empty
            textViewToFile(textFile, textview);
            ConvertedSongTest.createXML(textReader.readTabFile2(textFile.toString()),outputFile.toString(), inputFile.toString()); // Passes textarea file through parser
            Parent conversionCompleteParent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/ConversionComplete.fxml"));
            Scene ClipBoardScene = new Scene(conversionCompleteParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ClipBoardScene);
            window.show();
        }
//        } catch (Exception e) {
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setHeaderText("File not found.");
//            errorAlert.setContentText("Please choose a file before you convert.");
//            errorAlert.showAndWait();
//        }
    }

    /**
     * This button in conversion complete scene will save the xml file to local desktop
     * @param event
     */
    @FXML
    public void SaveXMLFileHandler(ActionEvent event){
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

}