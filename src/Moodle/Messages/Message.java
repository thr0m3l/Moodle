package Moodle.Messages;

import Moodle.Client.Group;
import Moodle.User;
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 2L;
    private String msg = null;
    private User user = null;
    private MessageType messageType = null;

    private String picture = null;

    private String recipient = null;

    private byte[] file;
    private String fileName;

    private byte[] voice;

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getVoice() {
        return voice;
    }



    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public Message(){

    }

    public Message(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return this.user.getUserName() + " : " + this.msg;
    }
}
