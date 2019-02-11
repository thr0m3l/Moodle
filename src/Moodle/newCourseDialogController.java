package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


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



    /*public Course processResults(User currentUser) {

        return null;
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            File file = new File(fileName);
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);
            boolean cond = true;
            while (cond) {
                Object obj = null;
                try {
                    obj = oin.readObject();
                    User user = (User) obj;
                    list.add(user);

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
        StringTokenizer stf=new StringTokenizer(allFaculty,";");
        StringTokenizer sts=new StringTokenizer(allStudents,";");


        while (stf.hasMoreTokens()){
            String name=stf.nextToken();
            try {
                File file = new File(fileName);
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream oin = new ObjectInputStream(fin);
                boolean cond = true;
                while (cond) {
                    Object obj = null;
                    try {
                        obj = oin.readObject();
                        User user = (User) obj;
                        // if matched, add them to the list
                        if(user.getUserName().equals(name) ){
                                faculty.add(user);
                        }
                        //System.out.println("from file "+user.getUserName()+","+user.getUserType());
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
        }

        while (sts.hasMoreTokens()){
            String name=sts.nextToken();
            try {
                File file = new File(fileName);
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream oin = new ObjectInputStream(fin);
                boolean cond = true;
                while (cond) {
                    Object obj = null;
                    try {
                        obj = oin.readObject();
                        User user = (User) obj;
                        // if matched, add them to the list
                        if(user.getUserName().equals(name) ){
                            student.add(user);
                        }
                        //System.out.println("from file "+user.getUserName()+","+user.getUserType());
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
        }




        System.out.println("Now here,the size of faculty list is: "+faculty.size());
        System.out.println("Now here,the size of student list is: "+student.size());
        //now getting the course relevant materials and make a course object
        String number=CourseCode.getText();
        String courseName=CourseName.getText();
        String outline=courseOutline.getText();
        //now create an object and save it to file
        System.out.println(faculty);
        System.out.println(student);
        Course course=new Course(faculty,student,number,courseName,outline);
        CourseData.getCourseData().getCourseObservableList().add(course);
        CourseData.getCourseData().saveCourseData();
        System.out.println(course);


        //ekhon loop chalaye course er sob user der vitor ei course add korte hobe
        ArrayList<User>courseFaculty=course.getFaculty();
        ArrayList<User>courseStudents=course.getStudent();
        //now add the course to the user's data


    }
}
