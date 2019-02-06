package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;


public class HomeController {
    private static String fileName = new String("userCredentials.dat");
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


    public void initialize(){

       //Loading the CSS
        courseListView.getStylesheets().add(getClass().getResource("listViewStyle.css").toExternalForm());

        //Temporary Code, will be removed after adding courseData class
        courseObservableList.add(
                new Course(new User("abc","123","ABC","abc","Faculty")
                        ,"101","EEE","Introduction to Electrical Engineering"));
        courseObservableList.add(
                new Course(new User("abc","123","ABC","abc","Faculty")
                        ,"101","CSE", "Structured Programming Language"));
        //

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
                            final Label labelHeader = new Label(item.getTitle() + " " + item.getNumber());
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
                                        Button btn = new Button("Enter");
                                        btn.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override public void handle(ActionEvent e) {
                                                try{
                                                    main.showCoursePage(currentUser, item);
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

