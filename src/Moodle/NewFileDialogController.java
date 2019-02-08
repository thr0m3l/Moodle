package Moodle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewFileDialogController {
    @FXML
    private Label fileName;

    @FXML
    private Label userTextArea;

    @FXML
    private JFXButton selectFile;

    private FileChooser fileChooser;

    @FXML
    private DialogPane dialogPane;

    private Dialog<ButtonType> dialog;
    @FXML
    public void initialize(){
        selectFile.setOnAction(event -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a file");
            Window dialogWindow = dialog.getDialogPane().getScene().getWindow();
            File file = fileChooser.showOpenDialog(dialogWindow);
            System.out.println(file.getPath());
        });


//        File file = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());
//        Path path = Paths.get(file.getPath());
//        System.out.println(file.getPath());
    }

    public void setDialog(Dialog<ButtonType> dialog) {
        this.dialog = dialog;
    }
}
