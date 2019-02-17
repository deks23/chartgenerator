package pl.damiankotynia.chartgenerator;



import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.CompileableComposite;

import org.jzy3d.plot3d.rendering.canvas.Quality;


public class TestFun extends AbstractAnalysis {


    @Override
    public void init() {
        // Define a function to plot
        Mapper mapper = new Mapper() {
            @Override
            public double f(double x, double y) {
               /* Argument xArg = new Argument("x", x);
                Argument yArg = new Argument("y", y);

                String qwe = "sqrt(1 - x) +  sqrt(y-x*x)";
                Expression expression = new Expression(qwe, xArg, yArg);*/

               //BigDecimal xBD =  BigDecimal.valueOf(x);
               //BigDecimal yBD = BigDecimal.valueOf(y);
                double tempA = Math.pow(1.0-x, 2);
                double tempB = Math.pow((y-(x*x)), 2);
               return 100.0 * tempB + tempA;
            }
        };

        // Define range and precision for the function to plot
        Range range = new Range(-3, 3);
        int steps = 40;

        // Create the object to represent the function over the given range.
        //final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        final CompileableComposite surface =      Builder.buildOrthonormalBig(new OrthonormalGrid(range, steps, range, steps), mapper);

        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);

        // Create a chart
        chart = AWTChartComponentFactory.chart(Quality.Fastest, getCanvasType());
        chart.getScene().getGraph().add(surface);
    }
}