package Moodle.Client;


import Moodle.*;
import Moodle.Client.util.*;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import Moodle.Messages.bubble.*;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


public class ChatController implements Initializable {

    @FXML private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private Label onlineCountLabel;
    @FXML private ListView<Group> groupList;
    @FXML private ImageView userImageView;
    @FXML private Button recordBtn;
    @FXML ListView chatPane;
    @FXML ListView statusList;
    @FXML BorderPane borderPane;
    @FXML ComboBox statusComboBox;
    @FXML ImageView microphoneImageView;
    @FXML
    private Button addGroup;

    public TextArea getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(TextArea messageBox) {
        this.messageBox = messageBox;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Label getOnlineCountLabel() {
        return onlineCountLabel;
    }

    public void setOnlineCountLabel(Label onlineCountLabel) {
        this.onlineCountLabel = onlineCountLabel;
    }

    public ListView<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(ListView<Group> groupList) {
        this.groupList = groupList;
    }

    public ImageView getUserImageView() {
        return userImageView;
    }

    public void setUserImageView(ImageView userImageView) {
        this.userImageView = userImageView;
    }

    public Button getRecordBtn() {
        return recordBtn;
    }

    public void setRecordBtn(Button recordBtn) {
        this.recordBtn = recordBtn;
    }

    public ListView getChatPane() {
        return chatPane;
    }

    public void setChatPane(ListView chatPane) {
        this.chatPane = chatPane;
    }

    public ListView getStatusList() {
        return statusList;
    }

    public void setStatusList(ListView statusList) {
        this.statusList = statusList;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public ComboBox getStatusComboBox() {
        return statusComboBox;
    }

    public void setStatusComboBox(ComboBox statusComboBox) {
        this.statusComboBox = statusComboBox;
    }

    public ImageView getMicrophoneImageView() {
        return microphoneImageView;
    }

    public void setMicrophoneImageView(ImageView microphoneImageView) {
        this.microphoneImageView = microphoneImageView;
    }

    public Button getAddGroup() {
        return addGroup;
    }

    public void setAddGroup(Button addGroup) {
        this.addGroup = addGroup;
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    private ObservableList<Group> groupObservableList = FXCollections.observableArrayList(Main.getCurrentUser().getGroups());

//    Image microphoneActiveImage = new Image(getClass().getClassLoader().getResource("resources/images/microphone-active.png").toString());
//    Image microphoneInactiveImage = new Image(getClass().getClassLoader().getResource("resources/images/microphone.png").toString());


    public ObservableList<Group> getGroupObservableList() {
        return groupObservableList;
    }

    public void setGroupObservableList(ObservableList<Group> groupObservableList) {
        this.groupObservableList = groupObservableList;
    }

    private double xOffset;
    private double yOffset;

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void sendButtonAction() throws IOException {
        String str = messageBox.getText();
        Message msg = new Message();
        msg.setMsg(str);
        msg.setMessageType(MessageType.CLIENT);
        msg.setUser(Main.getCurrentUser());

        if (!messageBox.getText().isEmpty() && !groupObservableList.isEmpty()) {
//            Listener.send(msg);
            msg.setRecipient(groupList.getSelectionModel().getSelectedItem().toString());
            msg.setGroup(groupList.getSelectionModel().getSelectedItem());
            Main.getClient().send(msg);
//            addToChat(msg);
            messageBox.clear();
        }
    }

    public void recordVoiceMessage() throws IOException {
        if (VoiceUtil.isRecording()) {
            Platform.runLater(() -> {
//                        microphoneImageView.setImage(microphoneInactiveImage);
                    }
            );
            VoiceUtil.setRecording(false);
        } else {
            Platform.runLater(() -> {
//                        microphoneImageView.setImage(microphoneActiveImage);

                    }
            );
            VoiceRecorder.captureAudio();
        }
    }


    public synchronized void addToChat(Message msg) {


        Group selectedGroup = groupList.getSelectionModel().getSelectedItem();
        selectedGroup.getMessages().add(msg);
        chatPane.getItems().add(msg);

    }


    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    @FXML
    public void closeApplication() {
        Platform.exit();
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            setImageLabel();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        groupList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                if(newValue != null) {
                    Group item = groupList.getSelectionModel().getSelectedItem();
                    chatPane.setItems(FXCollections.observableArrayList(item.getMessages()));
                }
            }
        });



        /* Drag and Drop */

        borderPane.setOnMousePressed(event -> {
            xOffset = main.getStage().getX() - event.getScreenX();
            yOffset = main.getStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });


        usernameLabel = new Label();
        usernameLabel.setText(Main.getCurrentUser().getUserName());
        borderPane.setOnMouseDragged(event -> {
            main.getStage().setX(event.getScreenX() + xOffset);
            main.getStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });

        Platform.runLater(() -> {

            groupList.setItems(groupObservableList);
//            groupList.setCellFactory(new CellRenderer());
//                setOnlineLabel(String.valueOf(msg.getUserlist().size()));
        });

//        statusComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                try {
//                    Listener.sendStatusUpdate(Status.valueOf(newValue.toUpperCase()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        /* Added to prevent the enter from adding a new line to inputMessageBox */
        messageBox.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    sendButtonAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ke.consume();
            }
        });

    }




    public void showHomePage() throws Exception{
        main.showHomePage(Main.getCurrentUser());
    }
    @FXML
    public void showNewGroupDialog() throws Exception{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Add New Group");
        dialog.setHeaderText("To add multiple users, put ';' between users ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newGroupDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            Group newGroup = controller.processResults();
            newGroup.getUsers().add(Main.getCurrentUser().getUserName());
//            groupObservableList.add(newGroup);
            groupList.getSelectionModel().select(newGroup);
            groupList.refresh();

            Message msg = new Message();
            msg.setMessageType(MessageType.GROUP);
            msg.setGroup(newGroup);
            Main.getClient().send(msg);



        }





    }
}

