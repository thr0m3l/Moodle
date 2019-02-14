package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class TeacherSubmissionsController {

    private Course currentCourse;
    private Post currentPost;
    private Main main;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button btnOverview;

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
    private Button PostBtn;

    @FXML
    private Button SiteNews;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ListView<File> fileListView;

    @FXML
    private Label timeLabel;
    private ObservableList<File> files = FXCollections.observableArrayList();

    @FXML
    void FriendAction(ActionEvent event) {

    }

    @FXML
    void PostBtnAction(ActionEvent event) {

    }

    @FXML
    void SiteNewsAction(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public void setCurrentPost(Post item) {
        this.currentPost = item;
        System.out.println(item.toString() + currentPost.getFiles().toString());
        files.addAll(currentPost.getFiles());
    }

    public void setMain(Main main) {
        this.main = main;
    }


    @FXML
    public void initialize(){

        fileListView.setItems(files);

        btnSignout.setOnAction(event -> {
            try{
                Message signOutMsg= new Message();
                signOutMsg.setUser(Main.getCurrentUser());
                signOutMsg.setMessageType(MessageType.LOGOUT);
                Main.getClient().send(signOutMsg);
                main.showLoginPage();
                Main.setCurrentUser(null);
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        btnOverview.setOnAction(event -> {
            try{
                main.showHomePage(Main.getCurrentUser());
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        btnMessage.setOnAction(event -> {
            try{
                main.showChatScreen();
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });


        fileListView.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
            @Override
            public ListCell<File> call(final ListView<File> lv) {
                ListCell<File> cell = new ListCell<File>(){
                    int height = 20;
                    @Override
                    protected void updateItem(final File item, boolean empty) {
                        super.updateItem(item, empty);
                        final HBox hbox = new HBox();
                        setGraphic(hbox);
                        if (item != null && getIndex() > -1) {
                            final Label labelHeader = new Label(item.getName());
                            labelHeader.setStyle("-fx-text-fill: #f7f9fc");
//                            labelHeader.setGraphic(createArrowPath(height, false));
                            labelHeader.setGraphicTextGap(20);
                            labelHeader.setId("tableview-columnheader-default-bg");
//                            labelHeader.setPrefWidth(fileList.getWidth() - 10);
                            labelHeader.setPrefHeight(height);

                            Button btn = new Button("Download");
//                            Label owner = new Label("Owner : " +  item.getOwner());
                            btn.setOnAction(event -> {
                                item.byteToFile();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("FILE DOWNLOAD SUCCESSFUL");
                                alert.setHeaderText(item.getName() + " is downloaded successfully");
                                alert.showAndWait();
                            });

                            Label studentName = new Label("Submitted by: " + item.getOwner());


                            labelHeader.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
//                                        labelHeader.setGraphic(createArrowPath(height, false));
//                                        labelHeader.setGraphic(createArrowPath(height, true));
                                    hbox.setSpacing(25);
                                    item.setHidden(item.getHidden() ? false : true);
                                    if(item.getHidden()){
                                        hbox.getChildren().removeAll(btn, studentName);
                                    }
                                    else {
                                        hbox.getChildren().addAll(btn, studentName);
                                    }

                                }
                            });

                            hbox.getChildren().add(labelHeader);
                        }
                    }

                };
                return cell;
            }
        });



    }
}
