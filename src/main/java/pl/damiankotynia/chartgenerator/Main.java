package pl.damiankotynia.chartgenerator;

import pl.damiankotynia.chartgenerator.connector.Connector;
import pl.damiankotynia.model.ChartGeneratorResponse;


public class Main {

    public static void main(String[] args) {
        int port = 4443;
        new Thread(new Connector(port)).start();

    }
}



