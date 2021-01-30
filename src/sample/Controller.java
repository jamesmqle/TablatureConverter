package sample;

import java.io.File;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
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
    private Button CopyText;

    @FXML
    private ListView listview;

    @FXML
    private TextArea textview;

    @FXML
    public void ButtonOnAction(ActionEvent event){
        // creates file chooser object
        FileChooser fc = new FileChooser();
        // filters only text files
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
        // opens file explorer
        File selectedFiled = fc.showOpenDialog(null);

        if(selectedFiled != null){
            listview.getItems().add(selectedFiled.getName());
        }
    }

    @FXML
    public void ButtonOnAction2(ActionEvent event){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String clipBoardText = clipboard.getString();
        textview.setText(clipBoardText);
    }


}
