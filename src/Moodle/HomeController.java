package Moodle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;


public class HomeController {
    private static String fileName = new String("userCredentials.dat");
    private static String fileName2 = new String("courseData.dat");
    private Main main;

    private User currentUser;

    @FXML
    private ListView<Course> courseListView;
    @FXML
    private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
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
    private  Label timeLabel;
    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;

        userNameLabel.setText(currentUser.getFullName());
        if (currentUser.getUserType().equals("Student")) {
            addCourseButton.setVisible(false);
        } else {
            addCourseButton.setVisible(true);
        }
    }


    public void initialize(){

       //Loading the CSS
        courseListView.getStylesheets().add(getClass().getResource("listViewStyle.css").toExternalForm());
        try {
            File file = new File(fileName2);
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);
            boolean cond = true;
            while (cond) {
                Object obj = null;
                try {
                    obj = oin.readObject();
                    Course course = (Course) obj;
                    System.out.println(course);
                    courseObservableList.add(course);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (obj == null) {
                    cond = false;
                    fin.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Temporary Code, will be removed after adding courseData class
        courseObservableList.add(
                new Course(new User("abc","123","ABC","abc","Faculty")
                        ,"101","EEE","Introduction to Electrical Engineering"));
        courseObservableList.add(
                new Course(new User("abc","123","ABC","abc","Faculty")
                        ,"101","CSE", "Structured Programming Language"));
        */
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

                            labelHeader.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
                                    item.setHidden(item.isHidden() ? false : true);
                                    if (item.isHidden()) {
                                        labelHeader.setGraphic(createArrowPath(height, false));
                                        vbox.getChildren().remove(vbox.getChildren().size() - 1);
                                        vbox.getChildren().remove(vbox.getChildren().size() - 1);
                                    }
                                    else {
                                        labelHeader.setGraphic(createArrowPath(height, true));
                                        vbox.getChildren().add(new Label(item.getDescription()));
                                        //user jodi course e thake tahole enter button visible hobe
                                        ArrayList<User>faculty=item.getFaculty();
                                        System.out.println("HomeController e faculty of course: "+faculty);
                                        ArrayList<User>student=item.getStudent();
                                        System.out.println("HomeCOtroller e students of course: "+student);
                                        Button btn = new Button("Enter");
                                        btn.setVisible(false);
                                        for(User userF:faculty){
                                            if(userF.getFullName().equals(currentUser.getFullName())){
                                                btn.setVisible(true);break;
                                            }
                                        }
                                        for(User userS:student){
                                            if(userS.getFullName().equals(currentUser.getFullName())){
                                                btn.setVisible(true);break;
                                            }
                                        }

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
                                        vbox.getChildren().add(btn);
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
                (EventHandler) event -> {
                    final Calendar cal = Calendar.getInstance();
                    timeLabel.setText(format.format(cal.getTime()));
                }));

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
        main.showMyProfile(currentUser);

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

