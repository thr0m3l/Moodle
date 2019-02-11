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
import java.net.InetAddress;
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

    private static Data<Moodle.File> fileData = new Data<>("files.haha");

    public static Data<Moodle.File> getFileData() {
        return fileData;
    }

    public static void setFileData(Data<Moodle.File> fileData) {
        fileData = fileData;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("The server is running");

        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());

        try {
            userData.loadData();
            courseData.loadData();
            fileData.loadData();
//            users = userData.getData();
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
                                    onlineUsers.put(currentUser,objectOutputStream);
                                    for(Moodle.File file : fileData.getData()){
                                        if(file.getUsers().contains(currentUser.getUserName())){
                                            currentUser.getFiles().add(file);
                                        }
                                        else if(file.getOwner().equals(currentUser.getUserName())){
                                            currentUser.getFiles().add(file);
                                        }
                                    }

                                    newMsg.setUser(currentUser);

                                }
                                objectOutputStream.writeObject(newMsg);
                                break;
                            case CLIENT:
//                                message.getGroup().getMessages().add(message);
                                for(String userName : message.getGroup().getUsers()){
                                    for(User user : onlineUsers.keySet()){
                                        if(user.getUserName().equals(userName) &&
                                                !user.getUserName().equals(message.getUser())){
                                            try{
                                                onlineUsers.get(user).writeObject(message);
                                            } catch (SocketException se){
                                                System.err.println("Socket exception");
                                            }
                                        }
                                    }
                                }

//                                for(User user1: userData.getData()){
//                                    for(Group group : user1.getGroups()){
//                                        if(group.getName().equals(message.getGroup().getName())){
//                                            group.getMessages().add(message);
//                                        }
//                                    }
//                                }
//                                userData.saveData();
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
                                    onlineUsers.put(currentUser,objectOutputStream);
                                }
                                objectOutputStream.writeObject(signUpMsg);
                                break;
                            case GROUP:
                                System.out.println("Handling group : " + message.getGroup().getName());
                                groups.add(message.getGroup());
                                for(User user1 : userData.getData()){
                                    if(message.getGroup().getUsers().contains(user1.getUserName())){
                                        user1.getGroups().add(message.getGroup());
                                    }
                                }
                                for(User user1 : onlineUsers.keySet()){
                                    if(message.getGroup().getUsers().contains(user1.getUserName())){
                                        onlineUsers.get(user1).writeObject(message);
                                    }
                                }

                                userData.saveData();

                                break;
                            case FILE:
                                System.out.println("Receiving file : " + message.getFile().getName() + " from: " +
                                        message.getFile().getOwner());

//                                message.getFile().byteToFile();
                                fileData.getData().add(message.getFile());


//                                for(User user1 : userData.getData()){
//                                    if(message.getFile().getUsers().contains(user1.getUserName())){
//                                        user1.getFileNames().add(message.getFile().getName());
//                                        System.out.println("File saved successfully");
//                                    }
//                                    else if(message.getFile().getOwner().equals(user1.getUserName())){
//                                        user1.getFileNames().add(message.getFile().getName());
//                                        System.out.println("File saved successfully");
//                                    }
//                                }


                                for(User user1 : onlineUsers.keySet()){
                                    if(message.getFile().getUsers().contains(user1.getUserName())){
                                        onlineUsers.get(user1).writeObject(message);
                                    }
                                }

//                                userData.saveData();
                                fileData.saveData();
                                objectOutputStream.writeObject(message);
                                break;
                            case COURSE:
                                System.out.println(message.getCourse().getTitle());

                                courseData.getData().add(message.getCourse());

                                for(User user1 : userData.getData()){
                                    if(message.getCourse().getStudent().contains(user1.getUserName())){
                                        user1.getCourses().add(message.getCourse());
                                    } else if(message.getCourse().getFaculty().contains(user1.getUserName())){
                                        user1.getCourses().add(message.getCourse());
                                    }
                                }

                                for(User user1 : onlineUsers.keySet()){
                                    if(message.getCourse().getStudent().contains(user1.getUserName())){
                                        onlineUsers.get(user1).writeObject(message);
                                    } else if(message.getCourse().getFaculty().contains(user1.getUserName())){
                                        onlineUsers.get(user1).writeObject(message);
                                    }
                                }
//                                objectOutputStream.writeObject(message);
                                courseData.saveData();
                                userData.saveData();
                                break;
                            case POST:
                                boolean done = false;
                                for(User user1 : userData.getData()){
                                    for(Course course : user1.getCourses()){
                                        if(course.getTitle().equals(message.getPost().getCourseName())){
                                            course.getPosts().add(message.getPost());
                                            System.out.println("Post added successfully");
                                            done = true;
                                            break;
                                        }
                                    }
                                    if(done) break;
                                }
                                userData.saveData();
                                objectOutputStream.writeObject(message);
                                break;
                            case GETUPDATE:
                                userData.loadData();
                                for(User user1 : userData.getData()){
                                    if(user1.getUserName().equals(message.getUser().getUserName())){
                                        message.setUser(user1);
                                        objectOutputStream.writeObject(message);
                                        break;
                                    }
                                }
                                break;
                            case LOGOUT:
                                onlineUsers.remove(message.getUser(),onlineUsers.get(message.getUser()));
                                System.err.println("User logged out: " + message.getUser());
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
            for (User user : userData.getData()) {
                if (user.getUserName().equals(message.getUser().getUserName()) &&
                        user.getPassword().equals(message.getUser().getPassword()))
                         {
                    tempUser = user;
                }
            }

            return tempUser;
        }
        public User handleSignUp(Message message) throws IOException{
            for(User user : userData.getData()){
                if(message.getUser().getUserName().equals(user.getUserName())){
                    return null;
                }
            }
            return message.getUser();
        }
    }
}

