package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;

import java.io.File;
import java.io.*;
import java.util.*;

import java.io.IOException;
import java.text.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

public class PostingNoticeController {
    public ArrayList<SiteNews>news=new ArrayList<>();
    private Main main;
    private final String filename="SiteNews.data";
    private final String filename2="SiteNewsData2.txt";
    private User currentUser;
    @FXML
    private Button Post_to_Forum;
    @FXML
    private TextArea TakeDetail;
    @FXML
    private Button Back;
    private int idx=0;



    @FXML
    void PostToForumAction()throws Exception{
        TakeDetail.setWrapText(true);
        String detail=TakeDetail.getText();

        DateFormat dtf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        String time=dtf.format(date);
        String name=currentUser.getFullName();
        String fin="New Post:"+detail+"#"+"\nPosted By:"+name+"#"+"Last Modified at:"+time;

        //making a sitenews object and writing it to a file
        SiteNews siteNews=new SiteNews(name,time,detail);
        SiteNewsData.getSiteNewsData().getSiteNews().add(siteNews);
        SiteNewsData.getSiteNewsData().saveSiteNewsData();

        //writing the post in a text file
        try{
            File file=new File(filename2);
            FileOutputStream fos=new FileOutputStream(file,true);
            String filepost=fin+"###";
            fos.write(filepost.getBytes());
            fos.flush();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        main.showHomePage(currentUser);
    }


    @FXML
    void Action()throws Exception{
        main.showHomePage(currentUser);
    }


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
