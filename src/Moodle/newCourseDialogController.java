package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class newCourseDialogController implements Initializable {
    private ArrayList<User> faculty = new ArrayList<>();
    private ArrayList<User> student = new ArrayList<>();
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
        this.currentUser = currentUser;
    }
    private static String fileName = new String("userCredentials.dat");
    @FXML
    private TextArea CourseCode;
    @FXML
    private TextArea CourseName;
    @FXML
    private TextArea courseOutline;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView table;
    @FXML
    private Hyperlink confirm;
    @FXML private TextArea take;
    @FXML private TextArea takeFaculty;
    @FXML private TextArea takeStudents;
    @FXML private Hyperlink home;
    @FXML private TableColumn<User,String>userType;
    @FXML private TableColumn<User,String>userName;
    @FXML private TableColumn<User,CheckBox>Action;
    public ObservableList<User> list= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userName.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
        userType.setCellValueFactory(new PropertyValueFactory<User,String>("userType"));
        table.setItems(list);
    }

    @FXML
    public void BackAction()throws Exception{
        main.showHomePage(currentUser);
    }

    @FXML
    public void ConfirmAction()throws Exception{
        takeFaculty.setWrapText(true);
        takeStudents.setWrapText(true);
        String allFaculty=takeFaculty.getText();
        String allStudents=takeStudents.getText();
        String[] faculties = allFaculty.split(";");
        String[] students = allStudents.split(";");


        //now getting the course relevant materials and make a course object
        String number=CourseCode.getText();
        String courseName=CourseName.getText();
        String outline=courseOutline.getText();
        //now create an object and save it to file

        System.out.println(faculty);
        System.out.println(student);

        Course course = new Course();
        course.getStudent().addAll(Arrays.asList(students));
        course.getFaculty().addAll(Arrays.asList(faculties));
        course.setNumber(number);
        course.setDescription(outline);
        course.setTitle(courseName);


        Message courseMsg = new Message();
        courseMsg.setUser(Main.getCurrentUser());
        courseMsg.setMessageType(MessageType.COURSE);
        courseMsg.setCourse(course);

        Main.getClient().send(courseMsg);

        main.showHomePage(Main.getCurrentUser());
    }
}
