package Moodle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable {
    private Main main;
    private User currentUser;
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML private TextArea username;
    @FXML private TextArea fullname;
    @FXML private TextArea password;
    @FXML private TextArea email;
    @FXML private Hyperlink back;
    @FXML private Hyperlink confirm;
    private static String fileName = new String("userCredentials.dat");

    @FXML
    public void setCurrentUser(User currentUser) {
        username.setText(currentUser.getUserName());
        fullname.setText(currentUser.getFullName());
        email.setText(currentUser.geteMail());
        password.setText(currentUser.getPassword());
        this.currentUser = currentUser;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            User user=currentUser;
            username.setText(((User)user).getUserName());
            fullname.setText(currentUser.getFullName());
            password.setText(currentUser.getPassword());
            email.setText(currentUser.geteMail());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void BackAction()throws Exception{
        main.showHomePage(currentUser);
    }

    @FXML
    public void ConfirmAction()throws Exception{
        currentUser.setUserName(username.getText());
        currentUser.setFullName(fullname.getText());
        currentUser.setPassword(password.getText());
        currentUser.seteMail(password.getText());
        main.showHomePage(currentUser);
    }
}
