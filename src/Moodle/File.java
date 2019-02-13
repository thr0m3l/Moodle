package Moodle;

import javafx.application.Platform;

import java.io.*;
import java.util.ArrayList;

public class File implements Serializable {
    private byte[] file = null;
    private String name;
    private long size;
    private String owner;
    private ArrayList<String> users = new ArrayList<>();
    private long serialVersionUID = 3L;
    private boolean hidden = true;

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public File(java.io.File file){
        FileInputStream fis = null;
        try{
           fis = new FileInputStream(file);
           name = file.getName();
           size = file.length();
        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        }
        this.file = new byte[(int)file.length()];
        DataInputStream dis = new DataInputStream(fis);

        try{
            dis.readFully(this.file);
            dis.close();
        } catch (IOException e){
            System.err.println("Unable to convert the file to byte array");
        }
    }

    public void byteToFile(){
        try (FileOutputStream fos = new FileOutputStream(this.name)) {
            fos.write(this.file);
        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public java.io.File byteToTempFile(){
        try{
            java.io.File tempFile = java.io.File.createTempFile("pic", "jpg", null);
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(file);
            return tempFile;
        } catch (java.io.IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name;
    }
}
