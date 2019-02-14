package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SubmissionsController {

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

    @FXML
    private JFXButton addSubmission;
    @FXML private JFXButton submit;

    private Course currentCourse;

    private Post currentPost;
    private Main main;
    private java.io.File file = null;
    private File submissionFile = null;

    public void initialize(){

        btnOverview.setOnAction( event -> {
            try {
                main.showHomePage(Main.getCurrentUser());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        addSubmission.setOnAction( event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Please select a .zip file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".zip files", "*.zip"));
            file = fileChooser.showOpenDialog(homeAnchorPane.getScene().getWindow());

            if(file!=null){
                submissionFile = new File(file);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDateTime = LocalDateTime.now().format(formatter);
                submissionTime.setText(formattedDateTime);
            }
        });

        submit.setOnAction( event -> {
            Message message = new Message();
            message.setMessageType(MessageType.SUBMISSION);
            message.setFile(submissionFile);
            message.setUser(Main.getCurrentUser());
            message.setTime(LocalDateTime.now());
            message.setPost(currentPost);

            Main.getClient().send(message);

            try{
                main.showCoursePage2(Main.getCurrentUser(),currentCourse);
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }

        });



        
    }

    public void setCurrentPost(Post item) {
        this.currentPost = item;
        title.setText(currentPost.getTitle());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = item.getDeadline().format(formatter);
        dueDate.setText(formattedDateTime);



        long days = item.getDeadline().until( LocalDateTime.now(), ChronoUnit.DAYS);
        LocalDateTime tempDateTime = item.getDeadline().plusDays(days );
        long hours = tempDateTime.until(LocalDateTime.now(), ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        long minutes = tempDateTime.until( LocalDateTime.now(), ChronoUnit.MINUTES);
//        tempDateTime = tempDateTime.plusHours( hours );
        timeRemaining.setText(Long.toString(-days) + " days " + Long.toString(-hours) + " hours " +
                Long.toString(-minutes) + " minutes");

        if(minutes > 0) {
            addSubmission.setDisable(true);
            timeRemaining.setTextFill(Color.RED);
        }
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;


    }

    public void setMain(Main main) {
        this.main = main;
    }
}