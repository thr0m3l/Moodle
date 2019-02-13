package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    String title;
    String date;
    String adminName;
    String details;
    String courseName;
    PostType type;
    private static final long serialVersionUID = 117L;

    public Post(String title, String date, String admin, String details, String courseName) {
        this.title = title;
        this.date = date;
        this.adminName = admin;
        this.details = details;
        this.courseName = courseName;
    }


    ArrayList<Post> replies = new ArrayList<>();
    ArrayList<File>files=new ArrayList<>();

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", adminName='" + adminName + '\'' +
                ", details='" + details + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
