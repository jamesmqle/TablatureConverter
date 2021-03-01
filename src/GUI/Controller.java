package GUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EventObject;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Parser.textReader;

//import javax.swing.*;



/**
 * Controller class controls the functionality of the User Interface
 */
public class Controller {	
	private static Scene welcomeScene;
	private static Stage window;
	private static EventObject event;
	
	
    
    private void launchScreen(String fileName) throws IOException {
    	fileName = "/screens/" + fileName + ".fxml";
        if(fileName.equals("welcomeScene") && welcomeScene != null){        
				/*if we want to open up welcomeScene and it has been created before (meaning it's not null) then open the already existing scene.*/
        		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
                primaryStage.setTitle("Tablature Converter");
                primaryStage.setScene(welcomeScene);
                primaryStage.show();
                primaryStage.setOnCloseRequest(t -> {
                    Platform.exit();
                    System.exit(420);
                });
        }else{
            fileName = "/screens/" + fileName + ".fxml";
            try {
            	// Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("ConversionComplete.fxml"));
                //Scene ClipBoardScene = new Scene(conversionCompleteParent);
            	
            	//Parent conversionCompleteParent = null;
				Parent conversionCompleteParent = FXMLLoader.load(getClass().getClassLoader().getResource("ConversionComplete.fxml"));
        		Scene ClipBoardScene = new Scene(conversionCompleteParent);
        		//ClipBoardScene.getStylesheets()
        			//	.add(getClass().getClassLoader().getResource("GUI/WelcomeStyleSheet.css").toExternalForm());
        	
        		//event = null;
				window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(ClipBoardScene);
              
            	//           
                FXMLLoader.load(Controller.class.getResource(fileName));
                //Scene scene = new Scene(root);
                window.show();
                window.setOnCloseRequest(t -> {
                    Platform.exit();
                    System.exit(420);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }   
        }   
    
        }   
	
	 /*
	private Scene ClipBoardScene;

    public void setSecondScene(Scene scene) {
        ClipBoardScene = scene;
    }
    
    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(ClipBoardScene);
    }
    
    */
    

    public static String fileAsString;
    File inputFile = null; // Input file Object
    File outputFile = new File("src/sample/ConvertedSong.xml"); // Output file Object

    @FXML
    private ListView<String> listview;

    @FXML
    private TextArea textview;
	private Stage primaryStage;
    
    

       
    

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
        Controller.fileAsString = fileAsString;
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




    /**
     * Copy and paste to text area
     *
     * @param event
     */
    // edited
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
    //HERE
    @FXML
    public void BackToWelcome(ActionEvent event) throws IOException {
        inputFile = null;
        String fileName = null;
		//Object back;
		launchScreen(fileName);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/WelcomeScene.fxml"));
        Scene welcomeScene = new Scene(root);
        //Shortcut for closing the program
        // closes the program when the "esc" button is pressed...
        welcomeScene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent ke) -> {
            if (KeyCode.ESCAPE == ke.getCode()) {
                Platform.exit();
           }
        });
        welcomeScene.getStylesheets()
		.add(getClass().getClassLoader().getResource("GUI/WelcomeStyleSheet.css").toExternalForm());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(welcomeScene);
        //window.setScene(welcomeScene);
        primaryStage.show();
        //window.show();
        
    }

    /**
     * Go to conversion complete screen
     *
     * @param event
     * @throws IOException
     */
    
    //HERE
    
    
    @FXML
    public void ConvertHandler(ActionEvent event) throws IOException {
        try {
            if (listview != null) {
                textReader.readTabFile(fileAsString); // passes the input file to the parser
                Parent conversionCompleteParent = FXMLLoader.load(getClass().getResource("ConversionComplete.fxml"));
                Scene ClipBoardScene = new Scene(conversionCompleteParent);
                //Shortcut for closing the program
                // closes the program when the "esc" button is pressed...
                ClipBoardScene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent ke) -> {
                    if (KeyCode.ESCAPE == ke.getCode()) {
                        Platform.exit();
                   }
                });
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
    
    
    //HERE
    
    
    @FXML
    public void OpenXMLHandler(ActionEvent event) throws IOException {
        Desktop.getDesktop().open(outputFile);
        
    
    
    

}
}