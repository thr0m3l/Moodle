package Moodle.Client;

import Moodle.Main;
import Moodle.Messages.Message;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String hostname;
    private int PORT;
    private Main main;

    public Client(String hostname, int PORT) {
        this.hostname = hostname;
        this.PORT = PORT;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        try{
            socket = new Socket(hostname,PORT);

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

//                Message msg = new Message("Hello", "r0m3l");

//                objectOutputStream.writeObject(msg);
//                objectOutputStream.flush();
            while (socket.isConnected()){
                Message msg = null;
                try{
                    try{
                        msg = (Message) objectInputStream.readObject();
                    } catch (EOFException eof){
                        System.err.println("Logged out");
                        break;
                    }

                    if(msg != null){
                        switch (msg.getMessageType()){
                            case LOGIN:
                                final Message loginMsg = msg;
                                Platform.runLater(() -> {
                                    if(loginMsg.getUser() != null) {
                                        Main.setCurrentUser(loginMsg.getUser());
                                        try {
                                            main.showHomePage(loginMsg.getUser());
                                        } catch (java.lang.Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                       Alert alert = new Alert(Alert.AlertType.ERROR);
                                       alert.setTitle("Incorrect Credentials");
                                       alert.setHeaderText("Incorrect Credentials");
                                       alert.setContentText("The username and password you provided is not correct.");
                                       alert.showAndWait();
                                   }});
                                    break;
                            case SIGNUP:
                                {
                                    final Message tempMsg = msg;
                                    Platform.runLater(() ->{
                                        if(tempMsg.getUser() == null){
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Sign-up failed");
                                            alert.setHeaderText("Username already exists");
                                            alert.setContentText("The username you tried already exits, please try another");
                                            alert.showAndWait();
                                        } else {
                                            try{
                                                main.showHomePage(tempMsg.getUser());
                                            } catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }

                                    });

                                }

                        }
                    }

                } catch (java.lang.ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        } catch (IOException e){
            System.err.println("Couldn't connect to the server");
            e.printStackTrace();
        }
    }



    public  <T extends Message> void send(T message){
        try{
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e){
            System.err.println("Unable to send message");
        }
    }

}
