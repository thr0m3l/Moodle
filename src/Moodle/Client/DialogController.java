package Moodle.Client;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DialogController {

    @FXML
    private TextField groupName;

    @FXML
    private TextArea Users;

    public Group processResults() {
        String name = groupName.getText();
        String[] users = Users.getText().split(";");
        ArrayList<String> userArrayList = new ArrayList<>(Arrays.asList(users));
        Group group = new Group();
        group.setUsers(userArrayList);
        group.setName(name);

        return group;
    }

}
