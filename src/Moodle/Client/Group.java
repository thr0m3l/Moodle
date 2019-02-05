package Moodle.Client;

import Moodle.Messages.Message;
import Moodle.Messages.bubble.BubbledLabel;
import Moodle.User;


import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private ArrayList<String> users = new ArrayList<>();
    private String name = null;
    private ArrayList<Message> messages = new ArrayList<>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
