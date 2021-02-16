package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
import java.awt.Desktop;
//import java.awt.TextField;
=======
import java.nio.channels.FileChannel;
import java.util.Scanner;
>>>>>>> refs/heads/feature-jamesmqle

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

<<<<<<< HEAD
//import javax.swing.filechooser.FileNameExtensionFilter;

=======
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
>>>>>>> refs/heads/feature-jamesmqle

public class Controller {
<<<<<<< HEAD
	@FXML
=======

    String fileAsString;
    File file; // File Object

    @FXML
>>>>>>> refs/heads/feature-jamesmqle
    private Button FileChooser;
	
	String fileAsString;

    @FXML
    private Button CopyText;
    
    @FXML
    private TextField textPro;

    @FXML
    private TextField textfield;
    
    @FXML
    private AnchorPane anchorid;

    @FXML
    private Label description;

<<<<<<< HEAD
=======
//    /*
//    switches from welcome scene to file upload scene
//     */
//    @FXML
//    public void ToUploadFileHandler(ActionEvent event) throws IOException {
//        Parent fileUploadScreenParent = FXMLLoader.load(getClass().getResource("FileUploadScreen.fxml"));
//        Scene FileUploadScene = new Scene(fileUploadScreenParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(FileUploadScene);
//        window.show();
//    }
//
//    /*
//    switches from welcome scene to clipboard scene
//     */
//    @FXML
//    public void ToClipBoardHandler(ActionEvent event) throws IOException {
//        Parent clipBoardScreenParent = FXMLLoader.load(getClass().getResource("ClipBoardScreen.fxml"));
//        Scene ClipBoardScene = new Scene(clipBoardScreenParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(ClipBoardScene);
//        window.show();
//    }

    /*
    Preview File Handler
     */

>>>>>>> refs/heads/feature-jamesmqle
    @FXML
<<<<<<< HEAD
    private Label title;

    @FXML
    void ToUploadFile(ActionEvent event) throws IOException{   
    	 // creates file chooser object 

        FileChooser fc = new FileChooser(); 

        // Title for file explorer dialogue 

        fc.setTitle("Text File"); 
        Stage stage = (Stage)anchorid.getScene().getWindow(); 
 
        // filters only text files 
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt")); 

        // opens file explorer 
        File selectedFile = fc.showOpenDialog(stage); 

  

        if(selectedFile != null){ 

            // adds file to listview 
        	//fileAsString = selectedFile.toString();
        	textPro.setText(selectedFile.getAbsolutePath());
        	System.out.println("path : " + selectedFile.getAbsolutePath());
        	//System.out.printf("%sn", fileAsString); 

            //listview.getItems().add(selectedFile.getParentFile()); 

            //System.out.println(selectedFile.getPath()); 

            // Opens the file 

             //Desktop desktop = Desktop.getDesktop(); 
             //desktop.open(selectedFile); 

        } 
=======
    public void PreviewHandler(ActionEvent event){
        try{
            if(listview != null){
                Desktop.getDesktop().open(file);
            };
        }
        catch(Exception e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("File not found.");
            errorAlert.setContentText("Please choose a file first.");
            errorAlert.showAndWait();
        }

>>>>>>> refs/heads/feature-jamesmqle
    }
<<<<<<< HEAD
    
    @FXML
    void ToPreviewFile(ActionEvent event) throws IOException { 
    //Open previous selected file
    	//Desktop desktop = Desktop.getDesktop();
    	//desktop.open(fileAsString);
    	Runtime.getRuntime().exec("explorer /select, <selectedFile>");
    }
    
    
    
=======

    /*
    Choose file button
     */
>>>>>>> refs/heads/feature-jamesmqle
    @FXML
<<<<<<< HEAD
   public void ToClipBoardHandler(ActionEvent event) throws IOException { 
    	
              Clipboard clipboard = Clipboard.getSystemClipboard(); 
              String clipBoardText = clipboard.getString(); 
              textfield.setText(clipBoardText); 
              
          } 
    
    
    /* 
=======
    public void FileChooserHandler(ActionEvent event){
        // creates file chooser object
        FileChooser fc = new FileChooser();
        // filters only text files
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
        // opens file explorer
        file = fc.showOpenDialog(null);
        fileAsString = file.toString(); // Converts the file to a string
        //System.out.println(fileAsString);
>>>>>>> refs/heads/feature-jamesmqle

<<<<<<< HEAD
    switches from file upload / clipboard scene to conversion complete scene 
=======
        if(file != null){
            // adds file to listview
            listview.getItems().add(file.getName());
            System.out.println(file.getPath());
        }
    }
>>>>>>> refs/heads/feature-jamesmqle

<<<<<<< HEAD
     */ 
=======
    /*
    Copy from clipboard button
     */
    @FXML
    public void CopyClipBoardHandler(ActionEvent event){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String clipBoardText = clipboard.getString();
        textview.setText(clipBoardText);
    }
>>>>>>> refs/heads/feature-jamesmqle

<<<<<<< HEAD
      @FXML 
=======
    /*
    Goes back to welcome scene
     */
    @FXML
    public void BackToWelcome(ActionEvent event) throws IOException {
        Parent back = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        Scene welcomeScene = new Scene(back);
        welcomeScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.show();
    }
>>>>>>> refs/heads/feature-jamesmqle

      public void ConvertHandler(ActionEvent event) throws IOException { 

          Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("Conversion.fxml")); 

          Scene ClipBoardScene = new Scene(conversionCompleteParent); 

          ClipBoardScene.getStylesheets().add("GUI/WelcomeStyleSheet.css"); 

          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 

          window.setScene(ClipBoardScene); 

          window.show(); 

      } 
      
      /*
      Goes back to welcome scene
       */
      @FXML
      public void BackToWelcome(ActionEvent event) throws IOException {
          Parent Home = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
          Scene welcomeScene = new Scene(Home);
          welcomeScene.getStylesheets().add("GUI/WelcomeStyleSheet.css");
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(welcomeScene);
          window.show();
      }
     

}