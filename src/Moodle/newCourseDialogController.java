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
import java.util.ResourceBundle;


public class newCourseDialogController implements Initializable {
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
    private TextField CourseCode;
    @FXML
    private AnchorPane AC1;
    @FXML
    private AnchorPane AC3;
    @FXML private AnchorPane AC2;
    @FXML
    private TextField CourseName;
    @FXML
    private TextArea courseOutline;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView table;
    @FXML
    private Hyperlink confirm;
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
}
