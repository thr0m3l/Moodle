package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.io.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowingFriendsController implements Initializable{
    @FXML private TableView<User> table;
    @FXML private TableColumn<User,String>userName;
    @FXML private TableColumn<User,String>userType;
    @FXML private TableColumn<User,String>eMail;
    @FXML private Hyperlink home;
    public ObservableList<User>list=FXCollections.observableArrayList();

    private ObservableList<User> users = FXCollections.observableArrayList();
    private static String fileName = new String("userCredentials.dat");
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

    @FXML
    public void BackAction() throws Exception {
        main.showHomePage(currentUser);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            java.io.File file = new File(fileName);
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
        eMail.setCellValueFactory(new PropertyValueFactory<User,String>("eMail"));
        table.setItems(list);
    }
}
