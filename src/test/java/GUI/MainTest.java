//package GUI;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testfx.api.FxAssert;
//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//import org.testfx.matcher.control.LabeledMatchers;
//
//import java.io.IOException;
//
//@ExtendWith(ApplicationExtension.class)
//public class MainTest {
//
//    /**
//     * Will be called with {@code @Before} semantics, i. e. before each test method.
//     *
//     * @param primaryStage - Will be injected by the test runner.
//     * @ throws IOException
//     */
//    @Start
//    private void start(Stage primaryStage) throws IOException {
//        try {
//
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/WelcomeScene.fxml"));
//            primaryStage.setTitle("Tablature Converter");
//            Scene scene = new Scene(root, 1200, 800);
//            scene.getStylesheets().add(getClass().getClassLoader().getResource("GUI/WelcomeStyleSheet.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @param robot - Will be injected by the test runner.
//     */
//    @Test
//    void should_contain_button_with_text(FxRobot robot) {
//        FxAssert.verifyThat("#UploadFileButton", LabeledMatchers.hasText("\u2B06 Upload File"));
//    }
//
//    @Test
//    void should_contain_button_with_text2(FxRobot robot) {
//        FxAssert.verifyThat("#ConvertButton", LabeledMatchers.hasText("Convert File \uD83D\uDD01"));
//
//    }
//
//
//
//
//
//}