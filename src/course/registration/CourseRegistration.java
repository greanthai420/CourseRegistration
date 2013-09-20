/*
 * AUTHOR
 * SUTHIPONG THONGJAROEN 212210025
 * Tanakrit Pilaphaeng 212310097
 * 
 * Program to help students manage their course registration.
 */
package course.registration;

import java.io.IOException;

public class CourseRegistration {

    static MainWindow mainwin;

    public static void main(String[] args) throws IOException {
        mainwin = new MainWindow("Schedule.csv");
        //FileReader filereader = new FileReader();
        //filereader.getCourseName(2);
    }
    
    public MainWindow getMainWindow(){
        return mainwin;
    }
    
    //EOF
}
