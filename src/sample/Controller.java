package sample;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;


public class Controller {

    @FXML
    private Button button;

    @FXML
    private ListView listView;

    public void Button1Action(ActionEvent event){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            listView.getItems().add(selectedFile.getName());
        } else{
            System.out.println("File not valid");
        }
    }

}
