package Moodle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.*;
import java.io.File;
import java.io.*;

public class SiteNewsController {
    private Main main;
    private final String filename="SiteNews.data";


    private User currentUser;
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    @FXML
    private AnchorPane Root;
    @FXML
    private Button btn;
    @FXML
    private Button Back;
    @FXML
    TextField textField[];
    @FXML
    private TextArea Holder;
    @FXML
    public void ShowPost()throws Exception{
        FileInputStream fis = new FileInputStream(filename);
        ArrayList<Object> objectsList = new ArrayList<Object>();
        boolean cont = true;
        try{
            ObjectInputStream input = new ObjectInputStream(fis);
            while(cont){
                Object obj = input.readObject();
                Holder.setWrapText(true);
                Holder.setText(obj.toString());
                if(obj != null)
                    objectsList.add(obj);
                else
                    cont = false;
            }
        }catch(Exception e){
            //System.out.println(e.printStackTrace());
        }
    }
    @FXML
    public void BackAction()throws Exception{
        main.showHomePage(currentUser);
    }

}