package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User implements Serializable, Comparable<User> {

    private String userName;
    private String password;
    private String fullName;
    private String eMail;
    private String userType;

    //private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    private ArrayList<Course> courseArrayList = new ArrayList<>();
    private long serialVersionUID = 1L;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


    public void setStudentID(String studentID) {
        this.eMail = studentID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User(String userName, String password, String fullName, String eMail, String userType) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.eMail = eMail;
        this.userType = userType;
    }

    @Override
    public int compareTo(User user){
      return user.getUserName().compareTo(this.userName);
    }

    public User (String userName){
        this.userName = userName;
        this.userType = "";
        this.eMail="";
        this.fullName="";
        this.password="";
    }



    @Override
    public String toString() {
        return userName + " " + password + " "+ fullName + " "+ eMail+" " + userType;
    }
}

