package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements Serializable {
    private String title;
    private String date;
    private String adminName;
    private String details;
    private Course course;
    private PostType type;
    private File file;
    private LocalDateTime deadline;
    private ArrayList<File> submissions = new ArrayList<>();

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Post> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Post> replies) {
        this.replies = replies;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private static final long serialVersionUID = 117L;

    public Post(String title, String date, String admin, String details, Course course) {
        this.title = title;
        this.date = date;
        this.adminName = admin;
        this.details = details;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
                ", courseName='" + course.getTitle() + '\'' +
                '}';
    }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
