package csci2020u.lab09;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.*;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Controller {
    @FXML
    private Canvas canvas;
    private double inset = 50;

    @FXML
    private void initialize() {

        //Start Unix Timestamp from a Date
        long start = LocalDate.of(2010, 1, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        long end = LocalDate.of(2015, 12, 31)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        ArrayList<Float> apple = downloadStockPrices("AAPL", String.valueOf(start), String.valueOf(end));
        ArrayList<Float> google = downloadStockPrices("GOOG", String.valueOf(start), String.valueOf(end));

        drawLinePlot(apple,google);

    }

    private ArrayList<Float> downloadStockPrices(String tickerSymbol, String period1, String period2) {

        //URL
        String sURL = "https://query1.finance.yahoo.com/v7/finance/download/" +
                tickerSymbol + "?period1=" + period1 + "&period2=" + period2 + "&interval=1mo&" +
                "events=history&includeAdjustedClose=true";

        ArrayList<Float> result = null;
        try {
            URL netURL = new URL(sURL);
            URLConnection conn = netURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = new ArrayList<>();

            String line = reader.readLine();
            line = reader.readLine();
            //parsing CSV File
            while (line != null) {
                String[] cells = line.split(",");

                float currentPrice = Float.parseFloat(cells[4]);

                result.add(currentPrice);

                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());
        return result;
    }
    private float getMax(ArrayList<Float> a, ArrayList<Float> b){
        float max = 0;
        for(int i = 0; i < a.size(); i++){
            if(a.get(i) >= max){
                max = a.get(i);
            }
            if(b.get(i) >= max){
                max = b.get(i);
            }
        }

        return max;
    }

    public void drawLinePlot(ArrayList<Float> list1, ArrayList<Float> list2){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double height = canvas.getHeight();
        double width = canvas.getWidth();

        gc.setStroke(Color.BLACK);
        gc.strokeLine(inset,inset,inset,height-inset);
        gc.strokeLine(inset,height-inset,width-inset,height-inset);

        float maxInList = getMax(list1,list2);

        plotLine(list1,maxInList, Color.BLUE);
        plotLine(list2,maxInList, Color.RED);

    }
    public void plotLine(ArrayList<Float> list, float maxInList, Color color){

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        int iterations = list.size()-1;

        double newWidth = width-(inset*2);
        double newHeight = height-(inset*2);
        double padding = newWidth/(iterations);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);

        for(int i = 0; i < iterations; i++){
            gc.strokeLine(inset+(i*padding),
                         inset+(newHeight*(1-list.get(i)/maxInList)),
                         inset+((i+1)*padding),
                         inset+(newHeight*(1-list.get(i+1)/maxInList)));
        }

    }
}
