package Moodle;

import Moodle.Client.Group;
import Moodle.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class User implements Serializable {

    private String userName;
    private String password;
    private String fullName;
    private String eMail;
    private String userType;
    private String picture;
    private ArrayList<Group> groups = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    //private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    private ArrayList<Course> courseArrayList = new ArrayList<>();
    private long serialVersionUID = 1L;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

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

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return userName + " " + password + " "+ fullName + " "+ eMail+" " + userType;
    }
}

