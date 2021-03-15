package csci2020u.lab07;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Controller {
    @FXML private Canvas canvas;
    private final static Color[] pieColours =
            { Color.AQUA, Color.GOLD, Color.DARKORANGE,
        Color.DARKSALMON};//, Color.LAWNGREEN, Color.PLUM};

    @FXML private void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Map<String, Integer> warningFrequency = readCSV("weatherwarnings-2015.csv",5);

        drawPieChart(gc, warningFrequency);
        drawLabel(gc, warningFrequency);
    }

    private void drawLabel(GraphicsContext gc, Map<String,Integer> warningFrequency) {
        double labelHeight = 30.0;
        double labelWidth = 50.0;
        double x = 100.0;
        double y = 200.0;


        Set<String> keys = warningFrequency.keySet();
        Iterator<String> keyIterator = keys.iterator();

        int i = 0;

        while(keyIterator.hasNext()){
            String key = keyIterator.next();
            gc.setFill(pieColours[i]);
            gc.fillRect(x, y, labelWidth, labelHeight);
            gc.strokeRect(x, y, labelWidth, labelHeight);
            gc.setFill(Color.BLACK);
            gc.fillText(key,x + 70, y + 20);
            y += 50;

            i++;
        }



    }

    private Map<String,Integer> readCSV(String fileName, int colIndex){

        //Current working Directory
        File directory = new File("C:\\csci2020u\\lab07\\src\\csci2020u\\lab07\\input");

        //For CSV File (inFile)
        File input = new File(directory,fileName);

        Map<String,Integer> result = null;

        try{
            if (input.exists()){

                BufferedReader reader = new BufferedReader(new FileReader(input));
                result = new TreeMap<>();

                String line = reader.readLine();

                //parsing CSV File
                while(line != null){
                    String[] cells = line.split(",");

                    String currentWord = cells[colIndex];
                    //COUNTING WORD FREQUENCY
                    if(result.containsKey(currentWord)){
                        int previous = result.get(currentWord);
                        result.put(currentWord, previous+1);
                    }else{
                        result.put(currentWord, 1);
                    }
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


        System.out.println(result.toString());
        return result;
    }


    private void drawPieChart(GraphicsContext gc, Map<String,Integer> warningFrequency) {

        Set<String> keys = warningFrequency.keySet();
        Iterator<String> keyIterator = keys.iterator();

        int numWarnings = 0;
        while(keyIterator.hasNext()){
            String key = keyIterator.next();
            numWarnings += warningFrequency.get(key);
        }

        keyIterator = keys.iterator();

        double startAngle = 0.0;
        int i = 0;
        while(keyIterator.hasNext()){
            String key = keyIterator.next();

            double warningPercentage = (double) warningFrequency.get(key) / (double) numWarnings;

            //amount of the circle being taken up by purchase
            double sweepAngle = warningPercentage * 360.0;
            gc.setFill(pieColours[i]);
            gc.fillArc(500, 150, 300, 300, startAngle, sweepAngle, ArcType.ROUND);
            gc.strokeArc(500,150,300,300,startAngle,sweepAngle,ArcType.ROUND);
            startAngle += sweepAngle;
            i++;
        }

    }
}
