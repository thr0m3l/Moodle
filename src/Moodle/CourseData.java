package Moodle;

//Singleton class used to save courseData on file

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CourseData{

        private static Moodle.CourseData courseData = new Moodle.CourseData();
        private static String fileName = new String("courseData.dat");
        private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    private static final long serialVersionUID = 68L;

    public static CourseData getCourseData() {
        return courseData;
    }

    public static void setCourseData(CourseData courseData) {
        CourseData.courseData = courseData;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        CourseData.fileName = fileName;
    }

    public ObservableList<Course> getCourseObservableList() {
        return courseObservableList;
    }

    public void setCourseObservableList(ObservableList<Course> courseObservableList) {
        this.courseObservableList = courseObservableList;
    }

    public void loadCourseData() throws IOException {
            Path locPath = FileSystems.getDefault().getPath(fileName);
            try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
                boolean eof = false;
                while(!eof) {
                    try {
                        Course course = (Course) locFile.readObject();
                        courseObservableList.add(course);
                    } catch(EOFException e) {
                        eof = true;
                    }
                }
            } catch(InvalidClassException e) {
                System.out.println("InvalidClassException " + e.getMessage());
            } catch(IOException e) {
                System.out.println("IOException " + e.getMessage());
            } catch(ClassNotFoundException e) {
                System.out.println("ClassNotFoundException " + e.getMessage());
            }
        }
        public void saveCourseData () throws IOException{
            Path locPath = FileSystems.getDefault().getPath(fileName);
            try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
                for(Course course : courseObservableList) {
                    locFile.writeObject(course);
                }

            } catch (IOException e){
                System.out.println("Unable to save CourseData" + " " + e.getMessage());
            }
        }

}
