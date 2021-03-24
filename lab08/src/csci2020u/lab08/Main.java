package csci2020u.lab08;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private TableView<StudentRecord> table = new TableView<StudentRecord>();
    private final DataSource list = new DataSource();
    private ObservableList<StudentRecord> data = list.getAllMarks();
    public File currentFileName = new File("output1.csv");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){//primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Student Info");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        GridPane myGrid = new GridPane();

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("|Lab 08 Solutions| " + "Working on File: '" + currentFileName + "'");
        primaryStage.setWidth(650);
        primaryStage.setHeight(600);


        //Table Code

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
        vbox.setAlignment(Pos.TOP_CENTER);

        //Menu Code
        MenuItem newMenu = new MenuItem("New");
        MenuItem openMenu = new MenuItem("Open");
        MenuItem saveMenu = new MenuItem("Save");
        MenuItem saveAsMenu = new MenuItem("Save As");
        MenuItem exitMenu = new MenuItem("Exit");

        //Handling Menu Item Events

        newMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //clears TableView (new instance)
                table.getItems().clear();
                primaryStage.setTitle("|Lab 08 Solutions| " + "Working on File: 'untitled.csv'");
            }
        });
        openMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //creates FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                //Updates currentFileName
                currentFileName = selectedFile;
                primaryStage.setTitle("|Lab 08 Solutions| " + "Working on File: '" + currentFileName.getName() + "'");

                //calls load()
                data = list.load(currentFileName);

                table.setItems(data);
            }
        });
        saveMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //calls save()
                try {
                    list.save(currentFileName,table);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveAsMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //creates FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                //Updates currentFileName
                currentFileName = selectedFile;
                primaryStage.setTitle("|Lab 08 Solutions| " + "Working on File: '" + currentFileName.getName() + "'");

                //calls save()
                try {
                    list.save(currentFileName,table);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        exitMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Exits
                System.exit(0);
            }
        });

        Menu menu = new Menu("File");
        menu.getItems().addAll(newMenu,openMenu,saveMenu,saveAsMenu,exitMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);
        menuBar.getMenus().addAll(menu);

        Button addButton = new Button("Add");

        TextField tfSID = new TextField();
        tfSID.setPromptText("SID");


        TextField tfAssignments = new TextField();
        tfAssignments.setPromptText("Assignments/100");

        TextField tfMidterm = new TextField();
        tfMidterm.setPromptText("Midterm/100");

        TextField tfFinalExam = new TextField();
        tfFinalExam.setPromptText("Final Exam/100");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String SID = tfSID.getText();
                String assignments = tfAssignments.getText();
                String midterm = tfMidterm.getText();
                String finalExam = tfFinalExam.getText();

                if((SID != "")&&(assignments != "")&&(midterm != "")&&(finalExam != "")) {
                    data.add(new StudentRecord(SID,
                            Double.parseDouble(assignments),
                            Double.parseDouble(midterm),
                            Double.parseDouble(finalExam)));
                    table.setItems(data);

                    System.out.println("Data was added to the table.");
                }
            }
        });

        myGrid.add(tfSID,0,2);
        myGrid.add(tfAssignments,1,2);
        myGrid.add(tfMidterm,0,3);
        myGrid.add(tfFinalExam,1,3);
        myGrid.add(addButton,0,4);
        myGrid.setAlignment(Pos.BOTTOM_CENTER);

        vbox.getChildren().addAll(myGrid);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        ((Group) scene.getRoot()).getChildren().addAll(menuBar);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
