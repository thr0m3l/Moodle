package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

import java.util.Arrays;

public class NewFileDialogController {

    @FXML
    private DialogPane dialogPane;

    @FXML
    private JFXTextField fileName;

    @FXML
    private Label userTextArea;

    @FXML
    private JFXTextArea userList;

    @FXML
    private JFXButton saveButton;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void initialize (){
        fileName.setText(file.getName());

        saveButton.setOnAction( event -> {
            String users = userList.getText();
            String[] users1 = users.split(";");
            file.getUsers().addAll(Arrays.asList(users1));
            System.out.println(file.getUsers());

            Message fileUpdateMsg = new Message();
            fileUpdateMsg.setMessageType(MessageType.FILEUPDATE);
            fileUpdateMsg.setFile(file);

            Main.getClient().send(fileUpdateMsg);
        });

    }

}
