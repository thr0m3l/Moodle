package Moodle;

/**
 * Sample Skeleton for 'chatScreen.fxml' Controller Class
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class ChatController {

    @FXML // fx:id="borderPane"
    private BorderPane borderPane; // Value injected by FXMLLoader

    @FXML // fx:id="chatPane"
    private ListView<?> chatPane; // Value injected by FXMLLoader

    @FXML // fx:id="messageBox"
    private TextArea messageBox; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSend"
    private Button buttonSend; // Value injected by FXMLLoader

    @FXML // fx:id="recordBtn"
    private Button recordBtn; // Value injected by FXMLLoader

    @FXML // fx:id="microphoneImageView"
    private ImageView microphoneImageView; // Value injected by FXMLLoader

    @FXML // fx:id="onlineUsersHbox"
    private HBox onlineUsersHbox; // Value injected by FXMLLoader

    @FXML // fx:id="onlineCountLabel"
    private Label onlineCountLabel; // Value injected by FXMLLoader

    @FXML // fx:id="userList"
    private ListView<?> userList; // Value injected by FXMLLoader

    @FXML // fx:id="userImageView"
    private ImageView userImageView; // Value injected by FXMLLoader

    @FXML // fx:id="statusComboBox"
    private ComboBox<?> statusComboBox; // Value injected by FXMLLoader

    @FXML
    void closeApplication(MouseEvent event) {

    }

    @FXML
    void recordVoiceMessage(MouseEvent event) {

    }

    @FXML
    void sendButtonAction(ActionEvent event) {

    }

    @FXML
    void sendMethod(KeyEvent event) {

    }

}

