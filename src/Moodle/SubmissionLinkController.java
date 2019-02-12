package Moodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class SubmissionLinkController implements Initializable {

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
    private Label title;

    @FXML
    private Label dueDate;

    @FXML
    private Label timeRemaining;

    @FXML
    private Label submissionTime;

    @FXML
    private Label timeLabel;

    private Main main;

    private Course currentCourse;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

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



    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

    }

}

