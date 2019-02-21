package pl.damiankotynia.chartgenerator.connector;


import pl.damiankotynia.chartgenerator.service.ChartGenerator;
import pl.damiankotynia.model.ChartGeneratorResponse;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.model.ResponseType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import static pl.damiankotynia.chartgenerator.service.Utils.CONNECTION_LOGGER;


public class Connection implements Runnable {
    private Socket socket;
    private int clientNumber;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private List<Connection> connectionList;
    private boolean running;
    private ChartGenerator chartGenerator;

    public Connection(Socket socket, int client, List<Connection> connectionList) {
        System.out.println(CONNECTION_LOGGER + "New Connection");
        this.clientNumber = client;
        this.socket = socket;
        this.connectionList = connectionList;
        this.running = true;
        this.chartGenerator = new ChartGenerator();
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        while (running) {
            try {
                Object request = inputStream.readObject();
                System.out.println(CONNECTION_LOGGER + "new Request, client id: " + clientNumber);
                Response response = null;
                List<Point> qwe = new ArrayList<>((ArrayList) request);

                chartGenerator.setPointsFromOptimizer(qwe);
                BufferedImage screenshot = chartGenerator.getChart();
                ChartGeneratorResponse response1 = new ChartGeneratorResponse(ResponseType.CORRECT, screenshot);
                sendMessageImage(response1);

            } catch (SocketException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie");
                e.printStackTrace();
                running = !running;
                connectionList.remove(this);
            } catch (IOException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie2");
                e.printStackTrace();
                running = !running;
                connectionList.remove(this);
            } catch (ClassNotFoundException e) {
                System.out.println(CONNECTION_LOGGER + "Niepoprawny format zapytania");
            }
        }
        try {
            System.out.println("Zamykam połączenie");
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void sendMessageImage(ChartGeneratorResponse response) {
        try {
            synchronized (outputStream) {
                outputStream.writeObject(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
