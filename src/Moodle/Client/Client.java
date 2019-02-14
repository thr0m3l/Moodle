package Moodle.Client;

import Moodle.*;
import Moodle.Messages.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import Moodle.Messages.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
    private StorageController storageController;
    private newCourseDialogController newCourseDialogController;
    private HomeController homeController;
    private CoursePage2Controller coursePage2Controller;
    private AdminController adminController;
    private ConversationController conversationController;

    public ConversationController getConversationController() {
        return conversationController;
    }

    public void setConversationController(ConversationController conversationController) {
        this.conversationController = conversationController;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public CoursePage2Controller getCoursePage2Controller() {
        return coursePage2Controller;
    }

    public void setCoursePage2Controller(CoursePage2Controller coursePage2Controller) {
        this.coursePage2Controller = coursePage2Controller;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public Moodle.newCourseDialogController getNewCourseDialogController() {
        return newCourseDialogController;
    }

    public void setNewCourseDialogController(Moodle.newCourseDialogController newCourseDialogController) {
        this.newCourseDialogController = newCourseDialogController;
    }

    public StorageController getStorageController() {
        return storageController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

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
                    } catch (SocketException se){
                        Platform.runLater( ()->{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Can't connect with the server");
                            alert.setTitle("Disconnected");
                            alert.showAndWait();
                        });
//                        Platform.exit();
                        break;
                    }

                    if(msg != null){
                        switch (msg.getMessageType()){
                            case LOGIN:
                                final Message loginMsg = msg;
                                Platform.runLater(() -> {
                                    if(loginMsg.getUser() != null) {

                                        if(loginMsg.getUser().isApproved()){
                                            Main.setCurrentUser(loginMsg.getUser());
                                            try {
                                                main.showHomePage(loginMsg.getUser());
                                            } catch (java.lang.Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Login failed");
                                            alert.setHeaderText("Your ID has not been approved by administrator yet." +
                                                    "Please contact admin.");
                                            alert.showAndWait();
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
//                                                main.showHomePage(tempMsg.getUser());
                                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                                alert.setTitle("Successfully signed up");
                                                alert.setHeaderText("Your ID : " + tempMsg.getUser().getUserName() +
                                                        " has been created and is sent to admin for approval. Please " +
                                                        "wait for the approval, Thank you!");
                                                alert.showAndWait();
                                                main.showLoginPage();
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
                                    chatController.addToGroupList(groupMsg.getGroup());

                                });
                                if(groupMsg.getGroup().getUsers().contains(Main.getCurrentUser().getUserName())){
                                    Main.getCurrentUser().getGroups().add(groupMsg.getGroup());
                                }
                                break;
                            case FILE:
                                final Message fileMsg = msg;
                                Main.getCurrentUser().getFiles().add(msg.getFile());
                                Platform.runLater(()->{
                                    storageController.addToFileList(fileMsg.getFile());
                                });
                                break;
                            case COURSE:
                                final Message courseMsg = msg;
                                Main.getCurrentUser().getCourses().add(msg.getCourse());
                                Platform.runLater(() -> {
                                    homeController.getCourseListView().getItems().add(courseMsg.getCourse());
                                });
                                break;
                            case POST:
                                final Message postMsg = msg;
                                Platform.runLater( () -> {
                                    try{
                                        coursePage2Controller.addPost(postMsg.getPost());
                                    } catch (java.lang.Exception e){
                                        e.printStackTrace();
                                    }



                                });

                                    for(Course course : Main.getCurrentUser().getCourses()){
                                        if(course.getTitle().equals(msg.getPost().getCourse().getTitle())){
                                            course.getPosts().add(msg.getPost());
                                            System.out.println("Post added successfully");
                                            break;
                                        }
                                    }

                                break;
                            case GETUPDATE:
                                Main.setCurrentUser(msg.getUser());
                                System.err.println("Main.currentUser updated!");
                                break;
                            case ADMINLOGIN:
                                final Message adminLoginMsg = msg;
                                Platform.runLater(() -> {
                                    if(adminLoginMsg.getUser() != null) {
                                        Main.setCurrentUser(adminLoginMsg.getUser());
                                        try {
                                            System.out.println(adminLoginMsg.getUsers());
                                            main.showAdminPage();
                                            adminController.setUsers(FXCollections.
                                                    observableArrayList(adminLoginMsg.getUsers()));

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
                            case REPLY:
                                final Message replyMsg = msg;
                                Platform.runLater( () -> {
                                    conversationController.addReply(replyMsg.getReply());
                                });
                                break;
                            case SUBMISSION:
                                boolean done1 = false;
                                for(Course course : Main.getCurrentUser().getCourses()){
                                    for(Post post :  course.getPosts()){
                                        if(msg.getPost().getTitle().equals(post.getTitle()) &&
                                                msg.getPost().getCourse().getTitle().equals(post.getCourse().getTitle())){
                                            msg.getFile().setOwner(msg.getUser().getUserName());
                                            post.getFiles().add(msg.getFile());
                                            System.err.println("Submission added : " + msg.getFile().getName());
                                            done1 = true;
                                            break;
                                        }
                                    }
                                    if(done1) break;
                                }
                                break;

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

    public static void sendVoiceMessage(byte[] audio) throws IOException {
        Message createMessage = new Message();
        createMessage.setUser(Main.getCurrentUser());
        createMessage.setMessageType(MessageType.VOICE);
        createMessage.setVoice(audio);
//        createMessage.setPicture(picture);
        objectOutputStream.writeObject(createMessage);

    }



}
