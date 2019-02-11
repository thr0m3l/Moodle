package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Post implements Serializable {
    String title;
    String date;
    String adminName;
    String details;
    String courseName;
    private static final long serialVersionUID = 117L;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Post(String title, String date, String admin, String details, String courseName) {
        this.title = title;
        this.date = date;
        this.adminName = admin;
        this.details = details;
        this.courseName = courseName;
    }


    ObservableList<Post> replies = FXCollections.observableArrayList();

    private boolean hidden = true;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "Posted by: "+adminName+"\n"+"Posted at: "+date+"\n"+details+"\n";

    }
}
