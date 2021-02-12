package GUI;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {

    @FXML
    private Button FileChooser;

    @FXML
    private Button CopyText;

    @FXML
    private ListView listview;

    @FXML
    private TextArea textview;

    /*
    switches from welcome scene to file upload scene
     */
    @FXML
    public void ToUploadFileHandler(ActionEvent event) throws IOException {
        Parent fileUploadScreenParent = FXMLLoader.load(getClass().getResource("FileUploadScreen.fxml"));
        Scene FileUploadScene = new Scene(fileUploadScreenParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(FileUploadScene);
        window.show();
    }

    /*
    switches from welcome scene to clipboard scene
     */
    @FXML
    public void ToClipBoardHandler(ActionEvent event) throws IOException {
        Parent clipBoardScreenParent = FXMLLoader.load(getClass().getResource("ClipBoardScreen.fxml"));
        Scene ClipBoardScene = new Scene(clipBoardScreenParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ClipBoardScene);
        window.show();
    }

    /*
    upload text file button
     */
    @FXML
    public void FileChooserHandler(ActionEvent event){
        // creates file chooser object
        FileChooser fc = new FileChooser();
        // filters only text files
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
        // opens file explorer
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            // adds file to listview
            listview.getItems().add(selectedFile.getParentFile());
            System.out.println(selectedFile.getPath());
        }
    }

    /*
    Paste to clipboard button
     */
    @FXML
    public void PasteClipBoardHandler(ActionEvent event){
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
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ClipBoardScene);
        window.show();
    }
}
