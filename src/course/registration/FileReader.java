/*
 * AUTHOR
 * SUTHIPONG THONGJAROEN 212210025
 * 
 * Class to read from file
 */
package course.registration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author becky_000
 */
public class FileReader {

    private String[] courseID;
    private String[] courseName;
    private String[] courseDays;
    private String[] courseTime;
    private int linenum = 0;
    public int test = 0;

    public FileReader(File x) throws IOException {
        String tmpstr;
        int i = 0;
        File file = new File(x.getName());

        //Detects the total number of lines to create array based on that number
        try (Scanner filereader = new Scanner(file)) {
            while (filereader.hasNext()) {
                filereader.nextLine();
                linenum++;
            }
            filereader.close();
        }

        //Create arrays with proper size from total line number
        try (Scanner filereader = new Scanner(file)) {
            if (linenum > 0) {
                courseID = new String[linenum];
                courseName = new String[linenum];
                courseDays = new String[linenum];
                courseTime = new String[linenum];

                //Sepearte data with , into each according array
                while (filereader.hasNext()) {
                    tmpstr = filereader.nextLine();
                    StringTokenizer strTok = new StringTokenizer(tmpstr, ",");
                    courseID[i] = strTok.nextToken();
                    courseName[i] = strTok.nextToken();
                    courseDays[i] = strTok.nextToken();
                    courseTime[i] = strTok.nextToken();
                    i++;
                }
            }
        }
    }

    public int getLineNum(){
        return linenum;
    }
    public String getCourseID(int courseid) {
        return courseID[courseid];
    }

    public String getCourseName(int coursename) {
        return courseName[coursename];
        //System.out.println(courseName[coursename]);
    }

    public String getCourseDays(int coursedays) {
        return courseDays[coursedays];
    }

    public String getCourseTime(int coursetime) {
        return courseTime[coursetime];
    }
}
