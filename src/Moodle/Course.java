package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Course {
    private ObservableList<User> faculty = FXCollections.observableArrayList();
    private ObservableList<User> student = FXCollections.observableArrayList();
    private ObservableList<Post> posts = FXCollections.observableArrayList();
    private String number = new String();
    private String title = new String();
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private boolean hidden = true;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public ObservableList<User> getFaculty() {
        return faculty;
    }

    public void setFaculty(ObservableList<User> faculty) {
        this.faculty = faculty;
    }

    public ObservableList<User> getStudent() {
        return student;
    }

    public void setStudent(ObservableList<User> student) {
        this.student = student;
    }

    public ObservableList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ObservableList<Post> posts) {
        this.posts = posts;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course(User faculty, String number, String title) {
        this.faculty.add(faculty);
        this.number = number;
        this.title = title;
    }
    public Course(User faculty, String number, String title, String description) {
        this.faculty.add(faculty);
        this.number = number;
        this.title = title;
        this.description = description;
    }


    @Override
    public String toString() {
        return number + " " + title;
    }
}
