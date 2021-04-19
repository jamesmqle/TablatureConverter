package GUI;

import XMLTags.Common.ConvertedSongTest;
import Parser.textReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Parser.textReader.*;

/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller implements Initializable {

    File inputFile; // Input file Object
    File outputFile = new File("src/main/resources/sample/convertedSong.xml"); // Output file Object
    File textFile = new File("src/main/resources/sample/textarea.txt");

    int[] error = new int[3]; // this will handle errors

    @FXML
    public Tab inputTab, outputTab;
    public TabPane tabPane;

    @FXML
    public Button ConvertButton, xml;

    @FXML
    private ListView listview;

    @FXML
    public TextField measure;

    @FXML
    public TextArea textview, title, XMLTextArea;

    @FXML
    private CodeArea codeArea = new CodeArea();

    @FXML
    public ComboBox<String> timeSignatureList;

    public static String timeSignature = "4/4";

    /**
     * This method initializes is for pre-processing information in the scene
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("4/4", "2/4"); // list of time signatures
        timeSignatureList.setItems(list); // display list of time signatures in combo box
        timeSignatureList.getSelectionModel().selectFirst(); // select the first index of list to be default value

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
//        new ErrorHighLighting(codeArea).enableHighlighting(); // error highlighting
    }


    @FXML
    void timeSignatureHandler(ActionEvent event) {
        timeSignature = timeSignatureList.getSelectionModel().getSelectedItem();
    }

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
            codeArea.clear();
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                codeArea.appendText(sc.nextLine() + "\n"); // else read the next token
            }
            textAreaCheck();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Makes the convert button blue when tablature is displayed in textarea
     */
    @FXML
    public void textAreaCheck() {

        if (codeArea.getText() != "") { // makes the convert button blue when tablature is displayed in textarea
            ConvertButton.setTextFill(Paint.valueOf("blue"));
        } else {
            ConvertButton.setTextFill(Paint.valueOf("white"));
        }
    }

    /**
     * This method creates a file from the textarea
     *
     * @param textFile
     * @param textArea
     */
    public void textViewToFile(File textFile, CodeArea textArea) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(textFile));
            bf.write(textArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Conversion is done through this method
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void ConvertHandler(ActionEvent event) throws IOException {
        if (codeArea.getText() != "") { // gives error message if textarea is empty
            textViewToFile(textFile, codeArea);
            error = TabIsOKTracker(getTab(textFile.toString()), detectInstrument(textFile.toString())); // this will assign the error
            System.out.println("Error: " + error);
            if (error[0] >= 10) { // handle critical errors (will not convert)
                CriticalErrorHandler(error);
            } else if (WarningErrorHandler(error) == true) {
                ConvertedSongTest.createXML(textReader.readTabFile(textFile.toString()), outputFile.toString(), textFile.toString()); // Passes textarea file through parser
                tabPane.getSelectionModel().select(outputTab); // automatically goes to output tab
                XMLTextArea.clear();
                displayXML();
            }
        } else { //  error message if textarea is empty
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Conversion Failed");
            errorAlert.setContentText("The textarea is empty. Please input tablature before converting.");
            errorAlert.showAndWait();
            errorAlert.close();
        }
    }

    /**
     * This method will handle CRITICAL errors within the given text tablature
     * 10 - No instrument detected
     *
     * @throws FileNotFoundException
     * @param error
     */
    @FXML
    public void CriticalErrorHandler(int[] error) throws FileNotFoundException {
        if (error[0] == 10) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR!");
            errorAlert.setContentText("Please Insert Correct Tablature Format.");
            errorAlert.showAndWait();
        }
    }

    /**
     * This method will handle CRITICAL errors within the given text tablature
     *
     * @throws FileNotFoundException
     * @param error
     */
    @FXML
    public boolean WarningErrorHandler(int[] error) throws FileNotFoundException {

        if (error[0] == 1) { // error 1 if all lines are not the same length
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tablature is misaligned at line " + error[2] + ", this may have affected the output. Do you wish to ignore?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ignore");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } else if (error[0] == 2) { // error 2 if incorrect tuning letters
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect tuning letters at line " + error[2] + ", this may have affected the output. Do you wish to ignore?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ignore");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }


    /**
     * This button will display the converted XML to the textarea
     */
    public void displayXML() throws IOException {
        XMLTextArea.clear();
        try {
            Scanner s = new Scanner(new File(outputFile.toString())).useDelimiter("'");
            while (s.hasNext()) {
                XMLTextArea.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    /**
     * This method will allow the user to navigate to a specific measure
     * @param event
     */
    @FXML
    public void MeasureHandler(ActionEvent event){
        try {
            int measureNum = Integer.parseInt(measure.getText());
            textViewToFile(textFile, codeArea);
            ConvertedSongTest.createXML(textReader.readTabFile(textFile.toString()), outputFile.toString(), textFile.toString());
            if (ConvertedSongTest.MEASURE_POSITION_MAP.containsKey(measureNum)) {
                Integer[] position = ConvertedSongTest.MEASURE_POSITION_MAP.get(measureNum);
                codeArea.moveTo(position[0]-1, position[1]);
                codeArea.requestFollowCaret();
                codeArea.requestFocus();
                return;
            }
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Measure Not Found");
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Measure number "+measureNum+" could not be found.");
            errorAlert.showAndWait();
            errorAlert.close();
        }catch (Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Measure Input Empty");
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Please Enter a Measure Number");
            errorAlert.showAndWait();
            errorAlert.close();
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

}
