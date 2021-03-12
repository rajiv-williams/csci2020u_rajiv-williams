package csci2020u.lab06;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import javax.swing.text.rtf.RTFEditorKit;

public class Controller {

    //Sample Dat for Bar Chart
    private final static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7
    };
    private final static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Sample Data for Pie Chart
    private final static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private final static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private final static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};


    @FXML private Canvas canvas;

    @FXML private void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawPieChart(gc);
        drawBarChart(gc, getMax(avgHousingPricesByYear, avgCommercialPricesByYear),
                        getMin(avgHousingPricesByYear, avgCommercialPricesByYear));
    }
    private double getMax(double[] a, double[] b){
        double max = 0.0;
        for(int i = 0; i < a.length; i++){
            if(a[i] >= max){
                max = a[i];
            }
            if(b[i] >= max){
                max = b[i];
            }
        }

        return max;
    }
    private double getMin(double[] a, double[] b){
        double min = a[0];
        for(int i = 0; i < a.length; i++){
            if(min >= a[i]){
                min = a[i];
            }
            if(min >= b[i]){
                min = b[i];
            }
        }

        return min;
    }
    private void drawBarChart(GraphicsContext gc, double max, double min){
        //avgHousingPricesByYear == RED
        //avgCommercialPricesByYear == BLUE
        int avgHouse = 0;
        int avgComm = 0;

        int width = 100;
        int height = 300;

        double graph_height;

        double max_height = max;
        double min_height = min;

        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            avgHouse += avgHousingPricesByYear[i];
        }
        for(int i = 0; i < avgCommercialPricesByYear.length; i++){
            avgHouse += avgCommercialPricesByYear[i];
        }
        double[] initialX = {0.0, 100/avgCommercialPricesByYear.length};
        double[] array;
        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            gc.setFill(Color.RED);

            graph_height = ((avgHousingPricesByYear[i] - min_height) / (max_height - min_height)) * height;
            gc.fillRect(initialX[0],(height - graph_height),width/avgHousingPricesByYear.length,graph_height + 50);

            gc.setFill(Color.BLUE);

            graph_height = ((avgCommercialPricesByYear[i] - min_height) / (max_height - min_height)) * height;
            gc.fillRect(initialX[1],(height - graph_height),width/avgCommercialPricesByYear.length,graph_height + 50);
            initialX[0] += 2 * (width/avgCommercialPricesByYear.length) + 5;
            initialX[1] += 2 * (width/avgCommercialPricesByYear.length) + 5;

        }
    }

    private void drawPieChart(GraphicsContext gc){
        int numPurchases = 0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            numPurchases += purchasesByAgeGroup[i];
        }
        double startAngle = 0.0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            double purchasePercentage = (double) purchasesByAgeGroup[i]/ (double) numPurchases;

            //amount of the circle being taken up by purchase
            double sweepAngle = purchasePercentage * 360.0;

            gc.setFill(pieColours[i]);

            //.fillArc() arguments
            //1st: x coordinate where it puts slice
            //2nd: y coordinate
            //3rd: Width Size
            //4th: Height
            gc.fillArc(350,150,300,300, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
        }
    }
}
