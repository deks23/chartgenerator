package pl.damiankotynia.chartgenerator.service;

import com.jogamp.opengl.util.texture.TextureData;
import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.view.AWTRenderer3d;
import org.jzy3d.plot3d.rendering.view.modes.ViewPositionMode;
import pl.damiankotynia.model.ChartConstants;
import pl.damiankotynia.model.Point;

import  static pl.damiankotynia.model.ChartConstants.MAX_POSITION;
import  static pl.damiankotynia.model.ChartConstants.MIN_POSITION;

import java.awt.image.BufferedImage;
import java.util.List;


public class ChartGenerator extends AbstractAnalysis {
    private List<Point> pointsFromOptimizer;
    private TextureData screenShot;

    public ChartGenerator(List<Point> pointsFromOptimizer) {
        this.pointsFromOptimizer = pointsFromOptimizer;
    }

    public List<Point> getPointsFromOptimizer() {
        return pointsFromOptimizer;
    }

    public void setPointsFromOptimizer(List<Point> pointsFromOptimizer) {
        this.pointsFromOptimizer = pointsFromOptimizer;
    }

    public TextureData getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(TextureData screenShot) {
        this.screenShot = screenShot;
    }

    @Override
    public void init(){
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

        AWTRenderer3d renderer3d =(AWTRenderer3d) chart.getCanvas().getRenderer();
        BufferedImage image = renderer3d.getLastScreenshotImage();

    }

    private void setBoundaryPoints(float z, Coord3d[] points, Color[] colors, int i) {
        points[i] = new Coord3d(ChartConstants.MAX_POSITION, MAX_POSITION, z);
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
