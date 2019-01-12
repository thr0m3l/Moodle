package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class UserData {
    private static UserData userData = new UserData();
    private static String fileName = new String("userCredentials.txt");

    private ObservableList<User> users;

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
        //Debug Code
//        System.out.println("Inside loadUserData()");
        users = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine())!=null){
                String[] itemPieces = input.split(",");
                String userName = itemPieces[0];
                String password = itemPieces[1];

                users.add(new User(userName, password));
            }
        } finally {
            if(br!=null){
                br.close();
            }
        }
    }
    public void saveUserData () throws IOException{
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<User> iter = users.iterator();
            while(iter.hasNext()){
                User item = iter.next();
                bw.write(String.format("%s,%s",item.getUserName(), item.getPassword()));
                bw.newLine();
            }
        }
        finally {
            if(bw!=null){
                bw.close();
            }
        }
    }
    public void habijabi(){
        System.out.println("Hello");
    }
}
