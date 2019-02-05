package Moodle.Client;

import Moodle.Main;
import Moodle.Messages.Message;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import Moodle.Messages.*;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private String hostname;
    private int PORT;
    private Main main;
    private ChatController chatController;

    public ChatController getChatController() {
        return chatController;
    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    public Client(String hostname, int PORT) {
        this.hostname = hostname;
        this.PORT = PORT;
    }

    public static InputStream getInputStream() {
        return inputStream;
    }

    public static void setInputStream(InputStream inputStream) {
        Client.inputStream = inputStream;
    }

    public static OutputStream getOutputStream() {
        return outputStream;
    }

    public static void setOutputStream(OutputStream outputStream) {
        Client.outputStream = outputStream;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static void setObjectInputStream(ObjectInputStream objectInputStream) {
        Client.objectInputStream = objectInputStream;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        Client.objectOutputStream = objectOutputStream;
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
                                    final Message tempMsg = msg;
                                    Platform.runLater(() ->{
                                        if(tempMsg.getUser() == null){
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Sign-up failed");
                                            alert.setHeaderText("Username already exists");
                                            alert.setContentText("The username you tried already exits, please try another");
                                            alert.showAndWait();
                                        } else {
                                            Main.setCurrentUser(tempMsg.getUser());
                                            try{
                                                main.showHomePage(tempMsg.getUser());
                                            } catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }

                                    });
                                break;

                            case CLIENT:
                                final Message clientMsg = msg;
                                Platform.runLater( () ->{
                                    chatController.addToChat(clientMsg);
                                });
//                                objectOutputStream.writeObject(msg);
                                System.out.println(msg.getUser().getUserName() + " " + msg.getMsg());
                                break;
                            case GROUP:
                                final Message groupMsg = msg;
                                Platform.runLater(() ->{
                                    chatController.getGroupObservableList().add(groupMsg.getGroup());
                                    chatController.getGroupList().getItems().add(groupMsg.getGroup());
                                });
                                if(groupMsg.getGroup().getUsers().contains(Main.getCurrentUser().getUserName())){
                                    Main.getCurrentUser().getGroups().add(groupMsg.getGroup());
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

//    public static void send(String msg) throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(MessageType.USER);
//        createMessage.setStatus(Status.AWAY);
//        createMessage.setMsg(msg);
//        createMessage.setPicture(picture);
//
//        oos.writeObject(createMessage);
//        oos.flush();
//    }

    /* This method is used for sending a voice Message
     * @param msg - The message which the user generates
     */
    public static void sendVoiceMessage(byte[] audio) throws IOException {
        Message createMessage = new Message();
        createMessage.setUser(Main.getCurrentUser());
        createMessage.setMessageType(MessageType.VOICE);
        createMessage.setVoice(audio);
//        createMessage.setPicture(picture);
        objectOutputStream.writeObject(createMessage);

    }

    /* This method is used for sending a normal Message
     * @param msg - The message which the user generates
     */
//    public static void sendStatusUpdate(Status status) throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(MessageType.STATUS);
//        createMessage.setStatus(status);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//        oos.flush();
//    }

//    /* This method is used to send a connecting message */
//    public static void connect() throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(CONNECTED);
//        createMessage.setMsg(HASCONNECTED);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//    }

}
