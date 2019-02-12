package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CoursePostEditController implements Initializable {
    private Main main;
    private User currentUser;
    private Course currentCourse;
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML private Label coursecode;
    @FXML private Label coursename;
    @FXML private TextArea TakeDetail;
    @FXML private Button Post_to_Forum;
    @FXML private Button Back;
    @FXML private TextArea taketitle;

    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
        coursecode.setText(currentCourse.getNumber());
        coursename.setText(currentCourse.getTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void BackAction()throws Exception{
        main.showCoursePage2(currentUser,currentCourse);
    }

    @FXML
    public void PostSiteAction()throws  Exception{
        TakeDetail.setWrapText(true);
        taketitle.setWrapText(true);
        String title=taketitle.getText();
        String detail=TakeDetail.getText();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String time=date.toString();
        Post post=new Post(title,time,currentUser.getFullName(),detail,currentCourse.getTitle());
        System.out.println("CoursePost Edit controller e post object: "+post);

        //post ta k course er post list e add kortesi
        Message postMsg = new Message();
        postMsg.setMessageType(MessageType.POST);
        postMsg.setPost(post);

        Main.getClient().send(postMsg);


        main.showCoursePage2(Main.getCurrentUser(),currentCourse);
        /*UserData.getUserData().getUsers().add(user);
        UserData.getUserData().saveUserData();*/
    }
}
