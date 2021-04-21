
package GUI;

import XMLTags.Common.ConvertedSongTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.NodeQuery;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class MainTest {

    private Controller controller ;
    @Start
    private void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/AppScene.fxml"));
        Parent root = loader.load();
        controller = (Controller) loader.getController();
        primaryStage.setTitle("Tablature Converter");
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/AppScene.css").toExternalForm());
        scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/ErrorHighlighting.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


/**
     * @param robot - Will be injected by the test runner.
     */

    @Test
    void containsUploadFileButton(FxRobot robot) {
        FxAssert.verifyThat("#UploadFileButton", LabeledMatchers.hasText("\u2B06 Upload File"));
    }

    @Test
    void containsConvertButton(FxRobot robot) {
        FxAssert.verifyThat("#ConvertButton", LabeledMatchers.hasText("Convert File \uD83D\uDD01"));
    }

    @Test
    void containsSaveButton(FxRobot robot) {
        FxAssert.verifyThat("#SaveButton", LabeledMatchers.hasText("Save"));
    }

    @Test
    void codeAreaTest(FxRobot robot){
        robot.clickOn("#codeArea");
        robot.write("Tab Test");
        assertEquals("Tab Test",controller.codeArea.getText());
    }

    @Test
    void songTitleTest(FxRobot robot){
        robot.clickOn("#title");
        robot.write("Title Test");
        assertEquals("Title Test",controller.title.getText());
    }

    @Test
    void measureTest(FxRobot robot){
        int measureNum = 19;
        robot.clickOn("#codeArea");
        robot.write("e|-----------0-----|-0---------------|\n" +
                "B|---------0---0---|-0---------------|\n" +
                "G|-------1-------1-|-1---------------|\n" +
                "D|-----2-----------|-2---------------|\n" +
                "A|---2-------------|-2---------------|\n" +
                "E|-0---------------|-0---------------|  ");
        robot.clickOn("#measure");
        robot.write("2");
        robot.clickOn("#MeasureGo");
        assertEquals(19,measureNum);
    }

    @Test
    void convertTest(FxRobot robot){
        robot.clickOn("#codeArea");
        robot.write("e|-----------0-----|-0---------------|\n" +
                "B|---------0---0---|-0---------------|\n" +
                "G|-------1-------1-|-1---------------|\n" +
                "D|-----2-----------|-2---------------|\n" +
                "A|---2-------------|-2---------------|\n" +
                "E|-0---------------|-0---------------|  ");
        robot.clickOn("#ConvertButton");
        assertEquals(true,controller.tabPane.getSelectionModel().isSelected(1));
    }

    @Test
    void convertTest2(FxRobot robot){
        robot.clickOn("#ConvertButton");
        assertEquals(true,controller.tabPane.getSelectionModel().isSelected(0));
        robot.closeCurrentWindow();
    }

    @Test
    void XMLViewerTest(FxRobot robot){
        robot.clickOn("#codeArea");
        robot.write("e|------------------|\n" +
                "B|------------------|\n" +
                "G|---[2]------------|\n" +
                "D|------------------|\n" +
                "A|------------------|\n" +
                "E|------------------|");
        robot.clickOn("#ConvertButton");
        assertEquals(true,controller.XMLTextArea.getText() != "");
    }

    @Test
    void timeSignatureTest(FxRobot robot){
        robot.clickOn("#timeSignatureList");
        assertEquals("4/4",controller.timeSignatureList.getSelectionModel().getSelectedItem());
    }

    @Test
    void convertButtonColorTest(FxRobot robot){
        robot.clickOn("#codeArea");
        robot.write("Color Tester");
        assertEquals("0x0000ffff",controller.ConvertButton.getTextFill().toString());
    }

    @Test
    void incorrectTuningErrorTest(FxRobot robot) throws InterruptedException {
        robot.clickOn("#codeArea");
        robot.write("e|------------------|\n" +
                "Z|------------------|\n" +
                "G|----4-------------|\n" +
                "H|------------------|\n" +
                "A|------------------|\n" +
                "E|------------------|");
        robot.clickOn("#ConvertButton");
        assertEquals(2,controller.error[0]);
        robot.closeCurrentWindow();
    }

    @Test
    void misalignedTablatureErrorTest(FxRobot robot) throws InterruptedException {
        Thread.sleep(1000);
        robot.clickOn("#codeArea");
        robot.write("e|-------------------|\n" +
                "B|--------------------|\n" +
                "G|---2---------------|\n" +
                "D|------------------|\n" +
                "A|------------------|\n" +
                "E|------------------------|");
        robot.clickOn("#ConvertButton");
        assertEquals(1,controller.error[0]);
        robot.closeCurrentWindow();
    }

    @Test
    void invalidTabErrorTest(FxRobot robot) throws InterruptedException {
        Thread.sleep(1000);
        robot.clickOn("#codeArea");
        robot.write("Invalid Tablature Test: awusdfghbaisdgb");
        robot.clickOn("#ConvertButton");
        assertEquals(10,controller.error[0]);
        robot.closeCurrentWindow();
    }

    @Test
    void saveFileTest(FxRobot robot) throws InterruptedException{
        Thread.sleep(1000);
        robot.clickOn("#outputTab");
        robot.clickOn("#SaveButton");
        robot.closeCurrentWindow();
        assertEquals("src\\main\\resources\\sample\\convertedSong.xml", controller.outputFile.toString() );
    }

    @Test
    void uploadFileTest(FxRobot robot) throws InterruptedException{
        Thread.sleep(1000);
        robot.clickOn("#codeArea");
        robot.write("Text File Test");
        assertEquals("src\\main\\resources\\sample\\textarea.txt",controller.textFile.toString());
    }

}
