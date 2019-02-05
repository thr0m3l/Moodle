package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.util.Iterator;

public class LoginController {
    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    @FXML
    private AnchorPane loginAnchorPane;



    @FXML
    void loginAction() throws Exception{
//        ObservableList<User> userObservableList = Main.getUserData().getData();
//        Iterator<User> iter = userObservableList.iterator();
//        boolean success = false;
//        while(iter.hasNext()){
//            User temp = iter.next();
//            if(temp.getUserName().equals(userText.getText()) && temp.getPassword().equals(passwordText.getText())){
//                main.showHomePage(temp);
//                success = true;
//            }
//        }
//
//            if(!success) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Incorrect Credentials");
//                alert.setHeaderText("Incorrect Credentials");
//                alert.setContentText("The username and password you provided is not correct.");
//                alert.showAndWait();
//            }
        Message message = new Message();
        message.setMessageType(MessageType.LOGIN);
        message.setUser(new User(userText.getText(),passwordText.getText()));
        Main.getClient().send(message);
    }

    @FXML
    void resetAction() {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    @FXML
    void signUp() throws Exception{
        main.showSignUpPage();
    }
    @FXML
    public void forgotPassword(){

    }
    @FXML
    public void initialize(){
        userText.setText("r0m3l");
        passwordText.setText("12345678");
    }
}

