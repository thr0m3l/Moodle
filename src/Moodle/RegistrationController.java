package Moodle;
import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import Moodle.Server.Server;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistrationController {
    private Main main;
    @FXML
    private ToggleGroup SF;
    @FXML
    private RadioButton faculty;
    @FXML
    private RadioButton student;
    @FXML
    private Button confirm;
    @FXML
    private Button returnLogin;
    @FXML
    private TextField fullName;
    @FXML
    private TextField userName;
    @FXML
    private TextField eMail;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password1;



    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void initialize(){ }
    @FXML
    public void signUpComplete() throws Exception {
        boolean success = true;
        RadioButton selectedRadioButton = (RadioButton) SF.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if(!password.getText().equals(password1.getText()) || password.getText().length() <8){
            success = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password mismatch");
            alert.setHeaderText("Please try another password");
            alert.setContentText("Please try a password of at least 8 characters and both of the passwords are to be same");
            alert.showAndWait();
        }
        Message message = new Message();
        message.setMessageType(MessageType.SIGNUP);
        message.setUser(new User(userName.getText(),password.getText(),fullName.getText(),
                eMail.getText(),toogleGroupValue));
        Main.getClient().send(message);



    }
    @FXML
    public void returnToLogin() throws Exception{
        main.showLoginPage();
    }
}
