package pl.damiankotynia.connector;

import org.jzy3d.analysis.AnalysisLauncher;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.service.ChartGenerator;
import pl.damiankotynia.service.ChartGeneratorQ;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import static pl.damiankotynia.service.Utils.CONNECTION_LOGGER;

public class Connection implements Runnable {
    private Socket socket;
    private int clientNumber;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private ChartGenerator chartGenerator;
    private List<Connection> connectionList;
    private boolean running;

    public Connection(Socket socket, int client, List<Connection> connectionList) {
        System.out.println(CONNECTION_LOGGER + "New Connection");
        this.clientNumber = client;
        this.socket = socket;
        this.connectionList = connectionList;
        this.running = true;
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void run() {
        ChartGeneratorQ chartGeneratorQ = new ChartGeneratorQ();
        while (running) {
            try {
                Object request = inputStream.readObject();
                System.out.println(CONNECTION_LOGGER + request.toString());
                Response response = null;
                List<Point>qwe = new ArrayList<>((ArrayList)request);
                try {
                    //AnalysisLauncher.open(new ChartGenerator(qwe));
                    chartGeneratorQ.setPointsFromOptimizer(qwe);
                    chartGeneratorQ.getChart();

                } catch (Exception e) {
                    e.printStackTrace();
                }






            } catch (SocketException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie");
                running = !running;
                connectionList.remove(this);
            } catch (IOException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie2");
                running = !running;
                connectionList.remove(this);
            } catch (ClassNotFoundException e) {
                System.out.println(CONNECTION_LOGGER + "Niepoprawny format zapytania");
            }
        }


    }

    public void sendMessage(Object message) {
        try {
            synchronized (outputStream) {
                outputStream.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
