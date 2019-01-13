package Moodle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

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
        RadioButton selectedRadioButton = (RadioButton) SF.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        User user = new User(userName.getText(),password.getText(),fullName.getText(),eMail.getText(),toogleGroupValue);
        System.out.println(user);
        UserData.getUserData().getUsers().add(user);
        UserData.getUserData().saveUserData();
        System.out.println("Successful");
        main.showHomePage(user);
    }
    @FXML
    public void returnToLogin() throws Exception{
        main.showLoginPage();
    }
}
