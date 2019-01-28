package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Post {
    String title;
    String date;
    User admin;
    String details;
    ObservableList<Post> replies = FXCollections.observableArrayList();

    public Post(String title, String date, User admin, String details) {
        this.title = title;
        this.date = date;
        this.admin = admin;
        this.details = details;
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
