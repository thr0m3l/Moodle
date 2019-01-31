package Moodle;

//This is a singleton class used to save user data on file

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Data <T> {

    private  String fileName = null;

    public Data(String fileName) {
        this.fileName = fileName;
    }

    private User lastUser;

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }

    private ObservableList<T> data = FXCollections.observableArrayList();


    public ObservableList<T> getData() {
        return data;
    }

    public void setData(ObservableList<T> data) {
        this.data = data;
    }

    public void loadData() throws IOException {
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while(!eof) {
                try {
                    T thing = (T) locFile.readObject();
                    data.add(thing);
                } catch(EOFException e) {
                    eof = true;
                }
            }
        } catch(InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }
    public void saveData () throws IOException{
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for(T datum : data) {
                locFile.writeObject(datum);
            }

        } catch (IOException e){
            System.out.println("Unable to save Data" + " " + e.getMessage());
        }
    }

}
