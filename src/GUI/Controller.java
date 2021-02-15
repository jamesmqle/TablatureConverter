package GUI;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
//import java.awt.TextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

//import javax.swing.filechooser.FileNameExtensionFilter;


public class Controller {
	@FXML
    private Button FileChooser;
	
	String fileAsString;

    @FXML
    private Button CopyText;

    @FXML
    private TextField textfield;
    
    @FXML
    private AnchorPane anchorid;

    @FXML
    private Label description;

    @FXML
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
        	fileAsString = selectedFile.toString();
        	textfield.setText(selectedFile.getAbsolutePath());
        	System.out.println("path : " + selectedFile.getAbsolutePath());

            //listview.getItems().add(selectedFile.getParentFile()); 

            //System.out.println(selectedFile.getPath()); 

            // Opens the file 

             //Desktop desktop = Desktop.getDesktop(); 
             //desktop.open(selectedFile); 

        } 
    }
    
    @FXML
    void ToPreviewFile(ActionEvent event) throws IOException { 
    //Open previous selected file
    	//Desktop desktop = Desktop.getDesktop();
    	//desktop.open(fileAsString.);
    	Runtime.getRuntime().exec("explorer /select, <selectedFile>");
    }
    
    
    
    @FXML
   public void ToClipBoardHandler(ActionEvent event) throws IOException { 
    	
              Clipboard clipboard = Clipboard.getSystemClipboard(); 
              String clipBoardText = clipboard.getString(); 
              textfield.setText(clipBoardText); 
              
          } 
    
    
    /* 

    switches from file upload / clipboard scene to conversion complete scene 

     */ 

      @FXML 

      public void ConvertHandler(ActionEvent event) throws IOException { 

          Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("Conversion.fxml")); 

          Scene ClipBoardScene = new Scene(conversionCompleteParent); 

          ClipBoardScene.getStylesheets().add("GUI/WelcomeStyleSheet.css"); 

          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 

          window.setScene(ClipBoardScene); 

          window.show(); 

      } 
          
        
    

    


}