package csci2020u.lab08;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class DataSource {
    public static ObservableList<StudentRecord> getAllMarks()
    {
        ObservableList<StudentRecord> marks =
                FXCollections.observableArrayList();
// Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f,
                54.25f)); marks.add(new StudentRecord("100100101", 70.0f,
            69.25f, 51.5f)); marks.add(new StudentRecord("100100102",
            100.0f, 97.0f, 92.5f)); marks.add(new
            StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f,
                58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f,
                62.5f)); marks.add(new StudentRecord("100100106", 70.0f,
            66.5f, 61.75f)); marks.add(new StudentRecord("100100107",
            55.0f, 47.0f, 50.5f)); marks.add(new
            StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f,
                74.25f));
        return marks;
    }
    public ObservableList<StudentRecord> load(File fileName){
        System.out.println("Loading data from file: "+ fileName.getAbsolutePath());

        //Current working Directory
        File directory = new File(".");

        //For CSV File (inFile)
        File input = fileName;

        ObservableList<StudentRecord> result = null;

        try{
            if (input.exists()){

                BufferedReader reader = new BufferedReader(new FileReader(input));
                result = FXCollections.observableArrayList();

                String line = reader.readLine();

                line = reader.readLine();
                //parsing CSV File
                while(line != null){
                    String[] cells = line.split(",");
                    StudentRecord currentRecord = new StudentRecord(cells[0],
                                                Double.parseDouble(cells[1]),
                                                Double.parseDouble(cells[2]),
                                                Double.parseDouble(cells[3]));

                    result.add(currentRecord);
                    line = reader.readLine();
                }
                reader.close();
            }
            else{
                System.err.printf("File '%s' does not exist.", input.getAbsolutePath());
            }
        } catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        return result;
    }
    public void save(File output, TableView<StudentRecord> table) throws IOException {
        System.out.println("Saving data to file: " + output.getAbsolutePath());

        //if (!output.exists()){
            if(output.exists()){
                output.delete();
            }
            output.createNewFile();
            if (output.canWrite()){
                //we need a file writer to write to the file.
                PrintWriter fileOutput = new PrintWriter(output);
                fileOutput.print("SID,Assignments,Midterm,Final Exam\n");
                //loop through table rows
                for(int i = 0; i < table.getItems().size(); i++){
                    //Output each row of table
                    for(int j = 0; j < table.getColumns().size() - 2; j++) {
                        if (j == table.getColumns().size()){
                            fileOutput.print(table.getColumns().get(j).getCellData(i));
                        }
                        else{
                            fileOutput.print(table.getColumns().get(j).getCellData(i) + ",");
                        }

                    }
                    fileOutput.println();
                }
                fileOutput.close();
            }
        //}else{
            //System.out.println("Error: The output file already exists: " + output.getAbsolutePath());
        //}
    }
}
