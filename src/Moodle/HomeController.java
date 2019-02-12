package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    private Main main;

    private User currentUser;

    @FXML
    private ListView<Course> courseListView;
    @FXML
    private ObservableList<Course> courseObservableList = FXCollections.observableArrayList(Main.getCurrentUser().getCourses());
    @FXML
    private AnchorPane homeAnchorPane;
    @FXML
    private Button btnOverView;
    @FXML
    private Button btnMessage;
    @FXML
    private Button btnFriends;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnStorage;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button PostBtn;
    @FXML
    private Button SiteNews;
    @FXML
    private Label timeLabel;

    @FXML
    private ImageView profilePicture;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;

        userNameLabel.setText(currentUser.getFullName());
        if (currentUser.getUserType().equals("Student")) {
            addCourseButton.setVisible(false);
        } else {
            addCourseButton.setVisible(true);
        }
    }

    public ListView<Course> getCourseListView() {
        return courseListView;
    }

    public void setCourseListView(ListView<Course> courseListView) {
        this.courseListView = courseListView;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){

       //Loading the CSS
        btnMessage.setOnAction(event -> {
                    try{
                        main.showChatScreen();
                    } catch (java.lang.Exception e){
                        e.printStackTrace();
                    }
                }
        );

        btnStorage.setOnAction(event -> {
            try{
                main.showStorage();
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        profilePicture.setImage(new Image(getClass().getResource("resources/images/Dominic.png").toExternalForm()));
        profilePicture.setOnMouseClicked( event -> {
            try{
                main.showMyProfile();
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });




        courseListView.getStylesheets().add(getClass().getResource("listViewStyle.css").toExternalForm());

        System.out.println("Course Observable list er size :"+courseObservableList.size());
        courseListView.setItems(courseObservableList);
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseListView.getSelectionModel().selectFirst();

        //Deals with the cell properties of the listview
        courseListView.setCellFactory(new Callback<ListView<Course>,  ListCell<Course>>() {
            @Override
            public ListCell<Course> call(final ListView<Course> lv) {
                ListCell<Course> cell = new ListCell<Course>(){
                    int height = 80;
                    @Override
                    protected void updateItem(final Course item, boolean empty) {
                        super.updateItem(item, empty);
                        final VBox vbox = new VBox();
                        setGraphic(vbox);

                        if (item != null && getIndex() > -1) {
                            final Label labelHeader = new Label(item.getNumber() + " " + item.getTitle());
                            labelHeader.setStyle("-fx-text-fill: #e7e5e5");
                            labelHeader.setGraphic(createArrowPath(height, false));
                            labelHeader.setGraphicTextGap(10);
                            labelHeader.setId("tableview-columnheader-default-bg");
                            labelHeader.setPrefWidth(courseListView.getWidth() - 10);
                            labelHeader.setPrefHeight(height);
                            Label description = new Label(item.getDescription());

                            Button btn = new Button("Enter");
                            btn.setVisible(false);
                            btn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override public void handle(ActionEvent e) {
                                    try{
                                        //main.showCoursePage(currentUser, item);
                                        main.showCoursePage2(currentUser, item);
                                    } catch (java.lang.Exception exception){
                                        exception.printStackTrace();
                                    }
                                }
                            });
                            labelHeader.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
                                    item.setHidden(item.isHidden() ? false : true);
                                    if (item.isHidden()) {
                                        labelHeader.setGraphic(createArrowPath(height, false));
                                        vbox.getChildren().removeAll(btn,description);
                                    }
                                    else {
                                        labelHeader.setGraphic(createArrowPath(height, true));

//                                        user jodi course e thake tahole enter button visible hobe
                                        ArrayList<String>faculty=item.getFaculty();

                                        ArrayList<String>student=item.getStudent();

                                        for(String str : item.getFaculty()){
                                            System.out.println(str);
                                        }

                                        vbox.getChildren().addAll(btn,description);

                                        if(item.getStudent().contains(Main.getCurrentUser().getUserName()) |
                                                item.getFaculty().contains(Main.getCurrentUser().getUserName())){
                                            btn.setVisible(true);
                                        }
                                    }
                                }
                            });
                            vbox.getChildren().add(labelHeader);
                        }
                    }

                };
                return cell;
            }
        });

        //showing the server time in home
        //final Label clock = new Label();
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                (event -> {
                    final Calendar cal = Calendar.getInstance();
                    timeLabel.setText(format.format(cal.getTime()));
                })));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    //Used in showing arrows on listView, will be modified later
    private SVGPath createArrowPath(int height, boolean up) {
        SVGPath svg = new SVGPath();
        int width = height / 4;

        if (up)
            svg.setContent("M" + width + " 0 L" + (width * 2) + " " + width + " L0 " + width + " Z");
        else
            svg.setContent("M0 0 L" + (width * 2) + " 0 L" + width + " " + width + " Z");

        return svg;
    }


    public Main getMain() {
        return main;
    }
    @FXML
    public void logOut() throws Exception{

        Message message = new Message();
        message.setMessageType(MessageType.LOGOUT);
        message.setUser(getCurrentUser());

        Main.getClient().send(message);
        main.showLoginPage();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    /*@FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;

        userNameLabel.setText(currentUser.getFullName());
        if (currentUser.getUserType().equals("Student")) {
            addCourseButton.setVisible(false);
        } else {
            addCourseButton.setVisible(true);
        }
    }*/
    @FXML
    public void showNewCourseDialog()throws Exception {
        main.showNewCourseDialog(currentUser);


    }

    @FXML
    public void PostBtnAction()throws  Exception{
        main.showPostingNotice(currentUser);
    }
    @FXML
    public void SiteNewsAction()throws Exception{
        main.showSiteNews(currentUser);

    }
    @FXML
    public void MyProfileAction()throws Exception{
        main.showMyProfile();

    }

    @FXML
    public void FriendAction(){
        try {
            main.showFriends(currentUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

