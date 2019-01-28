package Moodle;

//This is a singleton class used to save user data on file

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserData {
    private static UserData userData = new UserData();
    private static String fileName = new String("userCredentials.dat");

    private User lastUser;

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }

    private ObservableList<User> users = FXCollections.observableArrayList();

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        UserData.userData = userData;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        UserData.fileName = fileName;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }
    public void loadUserData() throws IOException {
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while(!eof) {
                try {
                    User user = (User) locFile.readObject();
                    users.add(user);
                } catch(EOFException e) {
                    eof = true;
                }
            }
        } catch(InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }
    public void saveUserData () throws IOException{
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for(User user : users) {
                locFile.writeObject(user);
            }

        } catch (IOException e){
            System.out.println("Unable to save UserData" + " " + e.getMessage());
        }
    }

}
