/*
 * AUTHOR
 * Seahawks
 * 
 * Program to help students manage their course registration.
 */
package course.registration;

import java.io.IOException;

public class CourseRegistration {

    static MainWindow mainwin;

    public static void main(String[] args) throws IOException {
        mainwin = new MainWindow("Schedule.csv");
    }
    
    public MainWindow getMainWindow(){
        return mainwin;
    }
    
    //EOF
}
