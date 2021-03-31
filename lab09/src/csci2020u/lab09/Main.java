package csci2020u.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 09 Stock Performance");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
