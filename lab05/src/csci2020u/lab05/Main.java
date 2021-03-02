package csci2020u.lab05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<StudentRecord> table = new TableView<StudentRecord>();
    private final DataSource list = new DataSource();
    private final ObservableList<StudentRecord> data = list.getAllMarks();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){//primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Student Info");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Student Info");
        primaryStage.setWidth(450);
        primaryStage.setHeight(500);

        final Label label = new Label();
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn SID_Column = new TableColumn("SID");
        SID_Column.setMinWidth(100);
        SID_Column.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("studentID"));

        TableColumn assnColumn = new TableColumn("Assignments");
        assnColumn.setMinWidth(100);
        assnColumn.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("assignments"));

        TableColumn midColumn = new TableColumn("Midterm");
        midColumn.setMinWidth(100);
        midColumn.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("midterm"));

        TableColumn finalExCol = new TableColumn("Final Exam");
        finalExCol.setMinWidth(100);
        finalExCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("finalExam"));

        TableColumn finalMarkCol = new TableColumn("Final Mark");
        finalMarkCol.setMinWidth(100);
        finalMarkCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("finalMark"));

        TableColumn letterGradeCol = new TableColumn("Letter Grade");
        letterGradeCol.setMinWidth(100);
        letterGradeCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("letterGrade"));

        table.setItems(data);
        table.getColumns().addAll(SID_Column, assnColumn, midColumn,finalExCol, finalMarkCol, letterGradeCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
