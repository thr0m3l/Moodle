package Moodle;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class newCourseDialogController {
    @FXML
    private TextField courseNumberTextField;

    @FXML
    private TextField courseTitleTextField;

    @FXML
    private TextField courseDescriptionTextField;

    @FXML
    private TextField addStudents;

    @FXML
    private TextField addFaculties;

    public Course processResults(User currentUser) {
        String courseNumber = courseNumberTextField.getText().trim();
        String courseTitle = courseTitleTextField.getText().trim();
        String courseDescription = courseDescriptionTextField.getText().trim();

        Course newCourse = new Course(currentUser,courseNumber, courseTitle, courseDescription);
        newCourse.getFaculty().add(currentUser);
        String [] temp = addStudents.getText().split(";");
        System.out.println(temp);
//        for(String i : temp){
//            System.out.println("inside the outer loop");
//            for(User user : UserData.getUserData().getUsers()){
//                if(i.equals(user)){
//                    System.out.println("Found it!");
//                    newCourse.getStudent().add(user);
//                }
//            }
//        }


        CourseData.getCourseData().getCourseObservableList().add(newCourse);
        System.out.println(CourseData.getCourseData().getCourseObservableList());

        return newCourse;
    }
}
