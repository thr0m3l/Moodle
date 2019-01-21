package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class SiteNews implements Serializable {
    private String user;
    private String date;
    private String details;

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public SiteNews(String user, String date, String details) {
        this.user = user;
        this.date = date;
        this.details = details;
    }

    @Override
    public String toString() {
        return details+"\n\n"+"Posted by: "+user+"\n"+"Last Modified at: "+date+"\n\n\n";
    }
}