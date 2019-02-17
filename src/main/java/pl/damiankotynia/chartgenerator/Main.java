package pl.damiankotynia.chartgenerator;

import pl.damiankotynia.chartgenerator.connector.Connector;


public class Main {

    public static void main(String[] args) {
        // write your code here
      /*  List<Point> qwe = new ArrayList<>();
        qwe.add(new Particle(new MVector(2.2,2)));
        qwe.add(new Particle(new MVector(3,3)));
        qwe.add(new Particle(new MVector(1,1)));
        try {
            AnalysisLauncher.open(new ChartGenerator(qwe));

        } catch (Exception e) {
            e.printStackTrace();
        }*/



        int port = 4443;
        new Thread(new Connector(port)).start();

    }
}



