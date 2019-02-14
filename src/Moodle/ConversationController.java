package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class ConversationController implements Initializable {
    @FXML private ObservableList<Reply>replies=FXCollections.observableArrayList();
    //private ArrayList<Reply>replyArrayList;

    @FXML private Label userNameLabel,coursecodeholder,coursenameholder;
    @FXML
    Button home,message,friends,menus,storage,settings,posttoforum,sitenews,signout;

    //private ListView postlistview;
    @FXML private Hyperlink myprofile;
    @FXML private ListView<String>postListView;
    @FXML private ScrollPane sp;

    private Main main;
    private User currentUser;
    private Course currentCourse;
    private Post post;
    private VBox box=new VBox();

    public Post getPost() {
        return post;
    }
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void setCurrentUser(User currentUser) {
        //userName.setText(currentUser.getFullName());
        this.currentUser=currentUser;
        userNameLabel.setText(this.currentUser.getFullName());
    }
    @FXML
    public void setCurrentCourse(Course currentCourse) {
        coursecodeholder.setText(currentCourse.getNumber());
        coursenameholder.setText(currentCourse.getTitle());
        this.currentCourse = currentCourse;
    }


    public void setPost(Post post) {
        this.post = post;
        replies.addAll(post.getReplies());
        //scrollpane e box add
        sp.setContent(box);
        VBox.setVgrow(sp, Priority.ALWAYS);
        //while(true){
        TextArea ta=new TextArea();
        ta.setWrapText(true);
        Text name=new Text("Started by: "+post.getAdminName());
        name.setFont(new Font("Italic",20));
        name.setFill(ORANGERED);
        Text dateP=new Text("Started at :"+post.getDate());
        dateP.setFill(ORANGERED);

        ta.setMaxSize(1000,200);
        ta.setFont(new Font("Arial",25));
        ta.setText(post.getDetails());



        Button btn=new Button("Reply");
        box.getChildren().addAll(name,dateP,ta,btn);



        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //CREATE A NEW TEXTAREA FOR TAKING REPLY
                TextArea taR=new TextArea();
                Label userName=new Label();
                Text nameUser=new Text("Replied: "+currentUser.getUserName());
                nameUser.setFont(new Font("Bold",20));
                nameUser.setFill(RED);
                taR.setPromptText("Enter your replies here");
                taR.setWrapText(true);

                taR.setMaxSize(1000,200);
                //NOW GET THE TEXT FROM TEXTAREA AND MAKING A REPLY OBJECT
                taR.setFont(new Font("Arial",25));
                DateFormat dtf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date=new Date();
                String time=dtf.format(date);
                //replies.add(reply);
                //MAKING A DUMMY TEXTAREA AND SHOW THE REPLY
                //making a submit button
                Button button=new Button("Submit");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Reply reply=new Reply(currentUser.getFullName(),currentCourse.getTitle(),post.getTitle(),time,taR.getText());
                        button.setVisible(false);
                        Label lb=new Label("Your reply has been submitted");
                        box.getChildren().add(lb);
                        replies.add(reply);
                        taR.setDisable(true);


                        Message message = new Message();
                        message.setMessageType(MessageType.REPLY);
                        message.setReply(reply);
                        Main.getClient().send(message);
                    }
                });
                box.getChildren().addAll(nameUser,taR,button);
//
            }
        });


    }


    @FXML
    public void BackAction(ActionEvent actionEvent) throws Exception {
        main.showHomePage(currentUser);
    }

    @FXML
    public void PostAction(ActionEvent ae)throws Exception{
        try {
            main.showCoursePostEdit(currentUser, currentCourse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void FriendAction(){
        try {
            main.showFriends(currentUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void SiteNewsAction()throws Exception{
        main.showSiteNews(currentUser);
    }
    @FXML
    public void logOut() throws Exception{
        main.showLoginPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*VBox box=new VBox();
        //scrollpane e box add
        sp.setContent(box);
        VBox.setVgrow(sp, Priority.ALWAYS);
         //while(true){
            TextArea ta=new TextArea();
            ta.setText(post.toString());
            Button btn=new Button("Reply");
            box.getChildren().addAll(ta,btn);
*/

    }

    private SVGPath createArrowPath(int height, boolean up) {
        SVGPath svg = new SVGPath();
        int width = height / 4;

        if (up)
            svg.setContent("M" + width + " 0 L" + (width * 2) + " " + width + " L0 " + width + " Z");
        else
            svg.setContent("M0 0 L" + (width * 2) + " 0 L" + width + " " + width + " Z");

        return svg;
    }

    public ObservableList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ObservableList<Reply> replies) {
        this.replies = replies;
    }

    public void addReply(Reply reply){
        TextArea textArea=new TextArea();
        textArea.setText(reply.getUsername()+"\n"+reply.getTime()+"\n"+reply.getDetail());
        box.getChildren().add(textArea);
        System.out.println(reply.getUsername()+"\n"+reply.getTime()+"\n"+reply.getDetail());
    }
}