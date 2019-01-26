package Moodle.Client;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ClientThread implements Runnable {

    private Client client;
    private OutputStream outStream;
    private InputStream inStream;
    private Socket socket;

    public ClientThread(Client client, OutputStream outStream, InputStream inStream, Socket socket) {
        this.client = client;
        this.outStream = outStream;
        this.inStream = inStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(outStream));
            DataInputStream in = new DataInputStream(new BufferedInputStream(inStream));
            byte[] bytes = new byte[1024];
            while (socket.isConnected()) {
                System.out.println("Client is up and running");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                out.write(str.getBytes());
                in.read(bytes);
                System.out.println(bytes);
//                chatClient.getMainApp().appendToChat(message + "\n");
                FileOutputStream fos = new FileOutputStream("test2.dat");
                int count;
                while((count = in.read(bytes)) > 0){
                    fos.write(bytes,0,count);
                }
                fos.flush();
            }
        } catch (SocketException e) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Connection Lost");
                    alert.setHeaderText("Lost the connection to the server.");
                    alert.setContentText("The connection to the server was lost.");
                    alert.showAndWait();

                    // Return to the config view
//                    chatClient.getMainApp().setView(new ConfigView());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}