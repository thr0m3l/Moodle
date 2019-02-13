package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

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
    private JFXTextField fullName;

    @FXML
    private JFXTextField eMail;

    @FXML
    private JFXTextField idNumber;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton selectProfilePicture;

    private Main main;

    private java.io.File dp;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void PostBtnAction(ActionEvent event) {

    }

    @FXML
    void SiteNewsAction(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }



    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

        fullName.setText(Main.getCurrentUser().getFullName());
        eMail.setText(Main.getCurrentUser().geteMail());
        password.setText(Main.getCurrentUser().getPassword());

        userNameLabel.setText(Main.getCurrentUser().getUserName());

        selectProfilePicture.setOnAction( event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a profile picture");
            dp = fileChooser.showOpenDialog(homeAnchorPane.getScene().getWindow());
            profilePicture.setImage(new Image(dp.toURI().toString()));
        });


        saveButton.setOnAction( event -> {

            if(dp != null){
                final File file = new File(dp);
                Main.getCurrentUser().setProfilePicture(file);
            }

            Message message = new Message();
            Main.getCurrentUser().setFullName(fullName.getText());
            Main.getCurrentUser().seteMail(eMail.getText());
            Main.getCurrentUser().setPassword(password.getText());
            Main.getCurrentUser().setStudentID(idNumber.getText());
            message.setUser(Main.getCurrentUser());
            message.setMessageType(MessageType.PROFILEUPDATE);
            Main.getClient().send(message);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Profile Updated");
            alert.setHeaderText("Profile successfully updated!");
            alert.showAndWait();


        });

        btnOverview.setOnAction( event -> {
            try{
                main.showHomePage(Main.getCurrentUser());
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

    }



}