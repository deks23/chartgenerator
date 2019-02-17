package pl.damiankotynia.service;

import com.jogamp.opengl.util.texture.TextureData;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.view.AWTRenderer3d;
import org.jzy3d.plot3d.rendering.view.modes.ViewPositionMode;
import pl.damiankotynia.model.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static pl.damiankotynia.model.ChartConstants.MAX_POSITION;
import static pl.damiankotynia.model.ChartConstants.MIN_POSITION;

public class ChartGeneratorQ {

    private List<Point> pointsFromOptimizer;
    private Chart chart;

    public ChartGeneratorQ(List<Point> pointsFromOptimizer) {
        this.pointsFromOptimizer = pointsFromOptimizer;
    }


    public ChartGeneratorQ(){

    }


    public List<Point> getPointsFromOptimizer() {
        return pointsFromOptimizer;
    }

    public void setPointsFromOptimizer(List<Point> pointsFromOptimizer) {
        this.pointsFromOptimizer = pointsFromOptimizer;
    }



    public void getChart(){
        float x;
        float y;
        float z = 0.0f;

        Coord3d[] points = new Coord3d[pointsFromOptimizer.size()+4];
        Color[]   colors = new Color[pointsFromOptimizer.size()+4];

        int i = 0;
        for(Point point : pointsFromOptimizer){
            x = (float)point.getPosition().getX();
            y = (float)point.getPosition().getY();

            points[i] = new Coord3d(x, y, z);
            colors[i] = Color.BLACK;
            i++;
        }

        setBoundaryPoints(z, points, colors, i);

        Scatter scatter = new Scatter(points);
        scatter.setWidth(4.2f);

        chart = AWTChartComponentFactory.chart(Quality.Fastest, "offscreen");

        chart.getScene().add(scatter);
        chart.setViewMode(ViewPositionMode.TOP);
        chart.screenshot();

        AWTRenderer3d renderer3d =(AWTRenderer3d) chart.getCanvas().getRenderer();
        BufferedImage image = renderer3d.getLastScreenshotImage();
        File asd = new File("qwe.jpg");

        try {
            ImageIO.write(image, "jpg", asd);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setBoundaryPoints(float z, Coord3d[] points, Color[] colors, int i) {
        points[i] = new Coord3d(MAX_POSITION, MAX_POSITION, z);
        colors[i] = Color.BLACK;
        i++;
        points[i] = new Coord3d(MIN_POSITION, MIN_POSITION, z);
        colors[i] = Color.BLACK;
        i++;
        points[i] = new Coord3d(MAX_POSITION, MIN_POSITION, z);
        colors[i] = Color.BLACK;
        i++;
        points[i] = new Coord3d(MIN_POSITION, MAX_POSITION, z);
        colors[i] = Color.BLACK;
    }


}
