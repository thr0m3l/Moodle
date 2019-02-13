package Moodle;

import Moodle.Client.Group;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String userName;
    private String password;
    private String fullName;
    private String eMail;
    private String userType;
    private String picture;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<File> files = new ArrayList<>();
    private ArrayList<String> fileNames = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private boolean approved = false;
    private File profilePicture = null;
    private String studentID;
    private boolean hidden;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getStudentID() {
        return studentID;
    }

    public File getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(File profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(ArrayList<String> fileNames) {
        this.fileNames = fileNames;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

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

