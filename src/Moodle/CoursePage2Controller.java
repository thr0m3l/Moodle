package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoursePage2Controller implements Initializable {
    @FXML private Label userNameLabel,coursecodeholder,coursenameholder;
    @FXML
    Button home,message,friends,menus,storage,settings,posttoforum,sitenews,signout;

    //private ListView postlistview;
    @FXML private Hyperlink myprofile;
    @FXML private ListView<Post>postListView;


    @FXML
    private Course currentCourse;
    private ObservableList<Post> postObservableList = FXCollections.observableArrayList();
    private Main main;
    private User currentUser;
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
        this.postObservableList = FXCollections.observableArrayList(this.currentCourse.getPosts());
        postListView.getItems().addAll(this.postObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        postListView.getStylesheets().add(getClass().getResource("listViewStyle.css").toExternalForm());


        postListView.setItems(postObservableList);
        postListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        postListView.getSelectionModel().selectFirst();


        postListView.setCellFactory(new Callback<ListView<Post>,  ListCell<Post>>() {
            @Override
            public ListCell<Post> call(final ListView<Post> lv) {
                ListCell<Post> cell = new ListCell<Post>(){
                    int height = 80;
                    @Override
                    protected void updateItem(final Post item, boolean empty) {
                        super.updateItem(item, empty);
                        final VBox vbox = new VBox();
                        setGraphic(vbox);

                        if (item != null && getIndex() > -1) {
                            final Label labelHeader = new Label(item.getTitle() );
                            labelHeader.setStyle("-fx-text-fill: #e7e5e5");
                            labelHeader.setGraphic(createArrowPath(height, false));
                            labelHeader.setGraphicTextGap(10);
                            labelHeader.setId("tableview-columnheader-default-bg");
                            labelHeader.setPrefWidth(postListView.getWidth() - 10);
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
                                        vbox.getChildren().add(new Label(item.getDetails()));
                                        final PostType type=item.getType();

                                        Button btn = new Button("Enter");
                                        if(type == PostType.FILE) {
                                            btn.setText("Download");
                                        }

                                        btn.setVisible(true);
                                        btn.setOnAction( event -> {
                                            try{
                                                if(type == PostType.NORMAL){
                                                    main.showConversation(currentUser,currentCourse,item);
                                                } else if (type == PostType.FILE) {
                                                    if(item.getFile() != null) {
                                                        item.getFile().byteToFile();
                                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                                        alert.setTitle("Successful");
                                                        alert.setHeaderText("File downloaded successfully");
                                                        alert.showAndWait();
                                                    } else {
                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                                        alert.setTitle("Failed");
                                                        alert.setHeaderText("Couldn't download the file!");
                                                        alert.showAndWait();
                                                    }

                                                } else if(type == PostType.SUBMISSION){
                                                    if(Main.getCurrentUser().getUserType().equals("Faculty")){
                                                        main.showTeacherSubmissionLink(currentCourse,item);
                                                    } else{
                                                        main.showSubmissionLink(currentCourse, item);
                                                    }


                                                }
                                            } catch (java.lang.Exception exception){
                                                exception.printStackTrace();
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

    private SVGPath createArrowPath(int height, boolean up) {
        SVGPath svg = new SVGPath();
        int width = height / 4;

        if (up)
            svg.setContent("M" + width + " 0 L" + (width * 2) + " " + width + " L0 " + width + " Z");
        else
            svg.setContent("M0 0 L" + (width * 2) + " 0 L" + width + " " + width + " Z");

        return svg;
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

    public void addPost (Post post) throws Exception{
        postListView.getItems().add(post);

    }
}
