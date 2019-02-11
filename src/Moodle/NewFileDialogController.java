package Moodle;

//import com.jfoenix.controls.JFXButton;
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

//    @FXML
//    private JFXButton selectFile;

    private FileChooser fileChooser;

    @FXML
    private DialogPane dialogPane;

    private Dialog<ButtonType> dialog;
    @FXML
    public void initialize(){


    }

    public void setDialog(Dialog<ButtonType> dialog) {
        this.dialog = dialog;
    }
}
