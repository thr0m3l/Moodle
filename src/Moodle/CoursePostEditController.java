package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
    @FXML private CheckBox forumpost;
    @FXML private CheckBox uploadfile;
    @FXML private CheckBox submissionlink;
    @FXML private VBox box;
    @FXML private Button btn;
    @FXML private Button filebtn;
    @FXML private DateTimePicker datePicker;
    @FXML private AnchorPane anchorPane;
    private String posttype="Normal";
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private java.io.File file;
    private File file1;
    private LocalDateTime deadline;
    private CoursePage2Controller coursePage2Controller = null;

    public CoursePage2Controller getCoursePage2Controller() {
        return coursePage2Controller;
    }

    public void setCoursePage2Controller(CoursePage2Controller coursePage2Controller) {
        this.coursePage2Controller = coursePage2Controller;
    }

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1;
    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
        coursecode.setText(currentCourse.getNumber());
        coursename.setText(currentCourse.getTitle());
        btn.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn.setVisible(false);
        filebtn.setVisible(false);
        datePicker.setVisible(false);

        configureCheckBox(forumpost);
        configureCheckBox(submissionlink);
        configureCheckBox(uploadfile);
        //only 1 ta jate selected thake tar code
        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
            }
        });

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e)
            {
                if (submissionlink.isSelected())
                {
                    forumpost.setSelected(false);
                    uploadfile.setSelected(false);
                    filebtn.setVisible(false);
                    btn.setVisible(true);
                    btn.setText("Add Deadline");
                    System.out.println("click hoiseeeeeeeeeeeeeeeeeeeeeeeeeee");
                    posttype="Submission";
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            datePicker.setVisible(true);
                        }
                    });

                }
            }
        };
        submissionlink.setOnAction(event);




        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (uploadfile.isSelected())
                {
                    submissionlink.setSelected(false);
                    btn.setVisible(false);
                    forumpost.setSelected(false);
                    filebtn.setVisible(true);
                    posttype="File";
                    btn.setText("Add Deadline");
                    System.out.println("click hoiseeeeeeeeeeeeeeeeeeeeeeeeeee");


                    filebtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            FileChooser filechoser = new FileChooser();
                            file = filechoser.showOpenDialog(anchorPane.getScene().getWindow());
                            if(file != null) {
                                file1 = new File(file);
                            }
                        }
                    });

                }
            }
        };
        uploadfile.setOnAction(event2);

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (forumpost.isSelected())
                {
                    submissionlink.setSelected(false);
                    btn.setVisible(false);
                    filebtn.setVisible(false);
                }
            }
        };
        forumpost.setOnAction(event3);

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
        deadline = datePicker.getDateTime();




        //postttype ekhane ui theke type zanar ekta dummy variable
        Post post=new Post(title,time,currentUser.getFullName(),detail,currentCourse);
        post.setDeadline(deadline);
        System.out.println("CoursePost Edit controller e post object: "+post);
        if(posttype.equals("File")){
            post.setType(PostType.FILE);
        }else if(posttype.equals("Submission")){
            post.setType(PostType.SUBMISSION);
        }else {
            post.setType(PostType.NORMAL);
        }

        //post ta k course er post list e add kortesi
        Message postMsg = new Message();
        postMsg.setMessageType(MessageType.POST);
        postMsg.setPost(post);
        if(file1 != null) {
            postMsg.setFile(file1);
        }
//        System.err.println(postMsg.getFile().getName() + postMsg.getFile().getSize()/1024);

        Main.getClient().send(postMsg);

        main.showCoursePage2(Main.getCurrentUser(),currentCourse);

    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }
        });

    }
}
