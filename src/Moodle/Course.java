package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
    private ArrayList<String> faculty = new ArrayList<>();
    private ArrayList<String> student = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private String number;
    private String title;
    private String description;

    private long serialVersionUID = 999L;

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getFaculty() {
        return faculty;
    }

    public void setFaculty(ArrayList<String> faculty) {
        this.faculty = faculty;
    }

    public ArrayList<String> getStudent() {
        return student;
    }

    public void setStudent(ArrayList<String> student) {
        this.student = student;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    //public Post getAPost(){}

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void setaPost(Post post) {  posts.add(post);      }

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

//    public Course(User faculty, String number, String title) {
//        this.faculty.add(faculty);
//        this.number = number;
//        this.title = title;
//    }
//    public Course(User faculty, String number, String title, String description) {
//        this.faculty.add(faculty);
//        this.number = number;
//        this.title = title;
//        this.description = description;
//    }

    @Override
    public String toString() {
        return "Course{" +
                "faculty=" + faculty +
                ", student=" + student +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
