package Moodle;

import java.io.Serializable;

public class Reply implements Serializable {
    private  String username,coursename,posttitle,time,detail;
    private static long SerialVersionUID=101L;

    public Reply(String username, String coursename, String posttitle, String time,String detail) {
        this.username = username;
        this.coursename = coursename;
        this.posttitle = posttitle;
        this.time = time;
        this.detail=detail;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "username='" + username + '\'' +
                ", coursename='" + coursename + '\'' +
                ", posttitle='" + posttitle + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}