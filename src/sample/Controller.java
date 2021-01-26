package sample;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {

    @FXML
    private Button FileChooser;

    @FXML
    private ListView listview;

    @FXML
    public void ButtonOnAction(ActionEvent event){
        FileChooser fc = new FileChooser();

        // send error message if text file is not given
        // FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("*.txt");
        // fc.getExtensionFilters().add(fileExtensions);

        File selectedFiled = fc.showOpenDialog(null);

        if(selectedFiled != null){
            listview.getItems().add(selectedFiled.getName());
        }else{
            System.out.println("File is not Valid");
        }

    }

}
