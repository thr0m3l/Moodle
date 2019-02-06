package Moodle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class newCourseDialogController {
    @FXML
    private TextField courseNumberTextField;

    @FXML
    private TextField courseTitleTextField;

    @FXML
    private TextArea courseDescriptionTextArea;

    @FXML
    private DatePicker deadlinePicker;

    public Course processResults(User currentUser) {
        String courseNumber = courseNumberTextField.getText().trim();
        String courseTitle = courseTitleTextField.getText().trim();
        String courseDescription = courseDescriptionTextArea.getText().trim();


        Course newCourse = new Course(currentUser,courseNumber, courseTitle, courseDescription);
//        Main.getCourseData().getData().add(newCourse);

        return newCourse;
    }
}
