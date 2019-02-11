package Moodle.Client;

import Moodle.*;
import Moodle.Client.util.*;
import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import Moodle.Messages.bubble.BubbleSpec;
import Moodle.Messages.bubble.BubbledLabel;
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

import java.io.IOException;
import java.net.URL;
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

    private ObservableList<Group> groupObservableList = FXCollections.observableArrayList(Main.getCurrentUser().getGroups());

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
//        chatPane.getItems().add(msg);


        ///////EXPERIMENTAL CODE, IN CASE IT DOESN'T WORK,
        ///IT WILL BE REMOVED


        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
//                Image image = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture() + ".png").toString());
//                ImageView profileImage = new ImageView(image);
//                profileImage.setFitHeight(32);
//                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getMessageType() == MessageType.VOICE){
//                    ImageView imageview = new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString()));
//                    bl6.setGraphic(imageview);
                    bl6.setText("Sent a voice message!");
//                    VoicePlayback.playAudio(msg.getVoiceMsg());
                }else {
                    bl6.setText(msg.getUser().getUserName() + ": " + msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.AQUA,null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(bl6);
//                setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            chatPane.getItems().add(othersMessages.getValue());
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = userImageView.getImage();
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);

                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getMessageType() == MessageType.VOICE){
//                    bl6.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString())));
                    bl6.setText("Sent a voice message!");
//                    VoicePlayback.playAudio(msg.getVoiceMsg());
                }else {
                    bl6.setText(msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(chatPane.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);

//                setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> chatPane.getItems().add(yourMessages.getValue()));

        if (msg.getUser().getUserName().equals(Main.getCurrentUser().getUserName())) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }


    }

    public synchronized void addToGroupList(Group group){
        System.out.println("added group: " + group.getName());
        groupList.getItems().add(group);
        Main.getCurrentUser().getGroups().add(group);
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

