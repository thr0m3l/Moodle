package Moodle.Server;



import Moodle.Client.Group;
import Moodle.Course;
import Moodle.Data;
import Moodle.Main;
import Moodle.User;
import Moodle.Messages.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;


public class Server {
    private static final int PORT = 8818;
    private static ObservableList<User> users;
    private static ObservableList<Group> groups = FXCollections.observableArrayList();
    private static HashMap<User, ObjectOutputStream> onlineUsers = new HashMap<>();
    private static ServerSocket listener;
    private static Data<User> userData = new Data<>("userCredentials.dat");
    private static Data<Course> courseData = new Data<>("courseData.dat");

    public static HashMap<User, ObjectOutputStream> getOnlineUsers() {
        return onlineUsers;
    }

    public static void setOnlineUsers(HashMap<User, ObjectOutputStream> onlineUsers) {
        Server.onlineUsers = onlineUsers;
    }

    public static Data<User> getUserData() {
        return userData;
    }

    public static void setUserData(Data<User> userData) {
        Server.userData = userData;
    }

    public static Data<Course> getCourseData() {
        return courseData;
    }

    public static void setCourseData(Data<Course> courseData) {
        Server.courseData = courseData;
    }

    public static void main(String[] args) {
        System.out.println("The server is running");

        try {
            userData.loadData();
            courseData.loadData();
            users = userData.getData();
        } catch (IOException e){
            System.err.println();
        }

        try{
            listener = new ServerSocket(PORT);
        } catch (IOException e){
            System.err.println("Unable to initiate server socket");
            e.printStackTrace();
        }

        try{
            while (true){
                Handler serverHandler = new Handler(listener.accept());
                Thread serverThread = new Thread(serverHandler);
                serverThread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                listener.close();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }
    private static class Handler implements Runnable {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private User currentUser;
        private User server ;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Attempting to connect a user . . .");
            try{
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectInputStream = new ObjectInputStream(inputStream);

                System.out.println("User connected!");

                while (socket.isConnected()){


                    //Message Processing

                    Message message = null;
                    try{
                        message = (Message) objectInputStream.readObject();
                    } catch (SocketException SE){
                        System.err.println("User disconnected : " + currentUser.getUserName());
                        break;
                    }

                    if(message != null) {
                        switch (message.getMessageType()) {
                            case LOGIN:
                                Message newMsg = new Message();
                                User tempUser = handleLogin(message);
                                newMsg.setMessageType(MessageType.LOGIN);
                                if(tempUser != null) {
                                    System.out.println("Login successful");
                                    currentUser = tempUser;
                                    newMsg.setUser(currentUser);
                                    onlineUsers.put(currentUser,objectOutputStream);
                                }
                                objectOutputStream.writeObject(newMsg);
                                break;
                            case CLIENT:
                                //debug code
                                System.out.println(message.getMsg());

                                message.getGroup().getMessages().add(message);
                                for(String userName : message.getGroup().getUsers()){
                                    for(User user : onlineUsers.keySet()){
                                        if(user.getUserName().equals(userName)){
                                            onlineUsers.get(user).writeObject(message);
                                        }
                                    }
//                                    for(User user : users){
//                                        if(user.getUserName().equals(userName)){
//
//                                        }
//                                    }
                                }
                                break;
                            case SIGNUP:
                                Message signUpMsg = new Message();
                                signUpMsg.setMessageType(MessageType.SIGNUP);
                                User user = handleSignUp(message);
                                if(user != null){
                                    currentUser = user;
                                    userData.getData().add(currentUser);
                                    signUpMsg.setUser(currentUser);
                                    userData.saveData();
                                }
                                objectOutputStream.writeObject(signUpMsg);
                                break;
                            case GROUP:
                                System.out.println("Handling group : " + message.getGroup().getName());
                                groups.add(message.getGroup());
                                for(User user1 : users){
                                    if(message.getGroup().getUsers().contains(user1.getUserName())){
                                        user1.getGroups().add(message.getGroup());
                                    }
                                }
                                for(User user1 : onlineUsers.keySet()){
                                    if(message.getGroup().getUsers().contains(user1.getUserName())){
                                        onlineUsers.get(user1).writeObject(message);
                                    }

                                }
//                                for(User user1 : onlineUsers.keySet()){
//                                    if(message.getGroup().getUsers().contains(user1.getUserName())){
//                                        user1.getGroups().add(message.getGroup());
//                                        onlineUsers.get(user1).writeObject(message);
//                                    }
//                                }
                                userData.saveData();

                                break;

                        }
                    }


                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (java.lang.ClassNotFoundException e1){
                e1.printStackTrace();
            }
        }

        public User handleLogin(Message message) throws IOException {
            User tempUser = null;
            System.out.println(message.getUser());
            for (User user : users) {
                if (user.getUserName().equals(message.getUser().getUserName()) &&
                        user.getPassword().equals(message.getUser().getPassword()))
                         {
                    tempUser = user;
                }
            }

            return tempUser;
        }
        public User handleSignUp(Message message) throws IOException{
            for(User user : users){
                if(message.getUser().getUserName().equals(user.getUserName())){
                    return null;
                }
            }
            return message.getUser();
        }
    }
}

