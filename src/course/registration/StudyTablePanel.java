/*
 * AUTHOR
 * Seahawks
 * 
 * Study table panel in main window.
 * Migrated to a new class/file because of long code.
 */
package course.registration;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudyTablePanel {

    private JPanel studyTablePane;
    private FileReader fr;
    //Store course ID and name | timeday(0) = array of ID ,1 = array of name, 2 = array of next id...
    private ArrayList<String[]> timeday;
    //private String[] mw830, tr830, fs830, mw1030, tr1030, fs1030, mw1230, tr1230, fs1230, mw1430, tr1430, fs1430, mw1630, tr1630, fs1630;
    //Store course name
    //private String[] mw830n, tr830n, fs830n, mw1030n, tr1030n, fs1030n, mw1230n, tr1230n, fs1230n, mw1430n, tr1430n, fs1430n, mw1630n, tr1630n, fs1630n;
    //Store jbutton cells
    //private JButton x1y1, x1y2, x1y3, x1y4, x1y5, x1y6, x2y1, x2y2, x2y3, x2y4, x2y5, x2y6, x3y1, x3y2, x3y3, x3y4, x3y5, x3y6, x4y1, x4y2, x4y3, x4y4, x4y5, x4y6, x5y1, x5y2, x5y3, x5y4, x5y5, x5y6;
    private JButton[][] xy = new JButton[6][7];

    public StudyTablePanel(FileReader file) {
        fr = file;
        studyTablePane = new JPanel();
        studyTablePane.setBorder(BorderFactory.createLineBorder(Color.black));
        //studyTablePane.setBackground(Color.white);
        studyTablePane.setLayout(new GridBagLayout());
        GridBagConstraints sc = new GridBagConstraints();

        /* USE EXAMPLE
         ArrayList<JLabel> lol = new ArrayList<>();
         for (int i=0; i<6; i++){
         //String mon = new String("Monday");
         String tmpstr = "";
         switch (i){
         case 0:
         tmpstr = "Monday";
         break;
         case 1:
         tmpstr = "Tuesday";
         break;
         case 2:
         tmpstr = "Wednesday";
         break;
         }
         JLabel label = new JLabel(tmpstr);
         lol.add(label);
         }
         for (int i = 0; i < 6; i++){
         lol.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
         }
         studyTablePane.add(lol.get(0));
         */

        //Constant Rows
        JLabel mon = new JLabel("Monday");
        JLabel tue = new JLabel("Tuesday");
        JLabel wed = new JLabel("Wednesday");
        JLabel thu = new JLabel("Thursday");
        JLabel fri = new JLabel("Friday");
        JLabel sat = new JLabel("Saturday");
        mon.setBorder(BorderFactory.createLineBorder(Color.black));
        tue.setBorder(BorderFactory.createLineBorder(Color.black));
        wed.setBorder(BorderFactory.createLineBorder(Color.black));
        thu.setBorder(BorderFactory.createLineBorder(Color.black));
        fri.setBorder(BorderFactory.createLineBorder(Color.black));
        sat.setBorder(BorderFactory.createLineBorder(Color.black));

        //sc.gridwidth = 1;
        //sc.gridheight = 1;
        sc.fill = GridBagConstraints.BOTH;
        sc.weightx = 0.00;
        sc.weighty = 1.00;
        //sc.gridx = 0;
        sc.gridy = 1;
        studyTablePane.add(mon, sc);
        sc.gridy = 2;
        studyTablePane.add(tue, sc);
        sc.gridy = 3;
        studyTablePane.add(wed, sc);
        sc.gridy = 4;
        studyTablePane.add(thu, sc);
        sc.gridy = 5;
        studyTablePane.add(fri, sc);
        sc.gridy = 6;
        studyTablePane.add(sat, sc);

        //Constant columns
        JLabel _830 = new JLabel("8:30");
        JLabel _1030 = new JLabel("10:30");
        JLabel _1230 = new JLabel("12:30");
        JLabel _1430 = new JLabel("14:30");
        JLabel _1630 = new JLabel("16:30");
        _830.setBorder(BorderFactory.createLineBorder(Color.black));
        _1030.setBorder(BorderFactory.createLineBorder(Color.black));
        _1230.setBorder(BorderFactory.createLineBorder(Color.black));
        _1430.setBorder(BorderFactory.createLineBorder(Color.black));
        _1630.setBorder(BorderFactory.createLineBorder(Color.black));

        sc.weightx = 1.00;
        sc.weighty = 0.00;
        sc.gridy = 0;
        sc.gridx = 1;
        studyTablePane.add(_830, sc);
        sc.gridx = 2;
        studyTablePane.add(_1030, sc);
        sc.gridx = 3;
        studyTablePane.add(_1230, sc);
        sc.gridx = 4;
        studyTablePane.add(_1430, sc);
        sc.gridx = 5;
        studyTablePane.add(_1630, sc);

        checkOverlap();
        addCells();
    }

    //Checks for overlap and counts them adding to td[][]
    private void checkOverlap() {
        /*
         * To use with creation of arrays with proper size
         * td[time][day]
         * time 0 = 8:30, 1 = 10:30, 2 = 12:30, 3 = 14:30, 4 = 16:30
         * day 0 = mw, 1 = tr, 2 = fs
         * Adds one to each element for matching, if end result is more than 1 indicates overlap
         */
        int[][] td = new int[5][3];
        for (int i = 0; i < fr.getLineNum(); i++) {
            switch (fr.getCourseTime(i)) {
                case "8:30":
                    switch (fr.getCourseDays(i)) {
                        case "MW":
                            td[0][0]++;
                            break;
                        case "TR":
                            td[0][1]++;
                            break;
                        case "FS":
                            td[0][2]++;
                            break;
                    }
                    break;
                case "10:30":
                    switch (fr.getCourseDays(i)) {
                        case "MW":
                            td[1][0]++;
                            break;
                        case "TR":
                            td[1][1]++;
                            break;
                        case "FS":
                            td[1][2]++;
                            break;
                    }
                    break;
                case "12:30":
                    switch (fr.getCourseDays(i)) {
                        case "MW":
                            td[2][0]++;
                            break;
                        case "TR":
                            td[2][1]++;
                            break;
                        case "FS":
                            td[2][2]++;
                            break;
                    }
                    break;
                case "14:30":
                    switch (fr.getCourseDays(i)) {
                        case "MW":
                            td[3][0]++;
                            break;
                        case "TR":
                            td[3][1]++;
                            break;
                        case "FS":
                            td[3][2]++;
                            break;
                    }
                    break;
                case "16:30":
                    switch (fr.getCourseDays(i)) {
                        case "MW":
                            td[4][0]++;
                            break;
                        case "TR":
                            td[4][1]++;
                            break;
                        case "FS":
                            td[4][2]++;
                            break;
                    }
                    break;
            }
        }

        /*
         * Initialize arraylist of courseID and coursename arrays
         * timeday(0) = array of ID ,1 = array of name, 2 = array of next id...
         * timeday(0) = mw830id, 1 = mw830name, 2 = fs830id, 3 = fs830name...
         * ID and name are initialized with same size
         */
        timeday = new ArrayList<>();
        /*
         * Repeat for 30 combo, 15 for ID and 15 for name
         * mw8:30, tr8:30, fs8:30, mw10:30, tr10:30, fs10:30, mw12:30, tr12:30, fs12:30, mw14:30, tr14:30, fs143:0, mw16:30, tr16:30, fs16:30
         */
        for (int i = 0, t = 0, d = 0; i < 30; i++) {
            //Move from 8:30 to 10:30 and so on every 6 loop (mwid mwname trid trname fsid fsname -> mwid2 ...)
            if (i % 6 == 0) {
                t++;
            }
            //Move from mw to tr and to fs after 2 loops (id name -> id2 name2)
            if (i % 2 == 0 && i != 0) {
                if (d != 2) {
                    d++;
                } else {
                    //Return from fs to mw
                    d = 0;
                }
            }
            /*
             * Create str[] array with proper size
             * str[size] is str[td[time][day]]
             */
            String[] str = new String[td[t - 1][d]];
            /*
             * timeday(nameorid)[size]
             * timeday will store a total of 30 items
             * items are str[] array that are initialized with dynamic size
             */
            timeday.add(str);
        }
        //Put actual data to timeday ArrayList
        insDataArray();

        /**
         * DEBUGGER (unusable)
         */
//        for (int i = 0; i < mw830.length; i++) {
//            System.out.print(mw830[i]);
//            if (i < mw830.length - 1) {
//                System.out.print(" | ");
//            }
//        }
//        System.out.println();
//        for (int i = 0; i < mw1030.length; i++) {
//            System.out.print(mw1030[i]);
//            if (i < mw1030.length - 1) {
//                System.out.print(" | ");
//            }
//        }
    }

    //Loop add data from filereader class into timeday arraylist
    private void insDataArray() {
        for (int i = 0, a = 0, b = 0, c = 0; i < fr.getLineNum(); i++) {
            if (fr.getCourseTime(i).equals("8:30")) {
                switch (fr.getCourseDays(i)) {
                    case "MW":
//                        mw830[a] = fr.getCourseID(i);
//                        mw830n[a] = fr.getCourseName(i);
                        timeday.get(0)[a] = fr.getCourseID(i);
                        timeday.get(1)[a] = fr.getCourseName(i);
                        a++;
                        break;
                    case "TR":
//                        tr830[b] = fr.getCourseID(i);
//                        tr830n[b] = fr.getCourseName(i);                        
                        timeday.get(2)[b] = fr.getCourseID(i);
                        timeday.get(3)[b] = fr.getCourseName(i);
                        b++;
                        break;
                    case "FS":
//                        fs830[c] = fr.getCourseID(i);
//                        fs830n[c] = fr.getCourseName(i);
                        timeday.get(4)[c] = fr.getCourseID(i);
                        timeday.get(5)[c] = fr.getCourseName(i);
                        c++;
                        break;
                }
            }
        }
        for (int i = 0, a = 0, b = 0, c = 0; i < fr.getLineNum(); i++) {
            if (fr.getCourseTime(i).equals("10:30")) {
                switch (fr.getCourseDays(i)) {
                    case "MW":
//                        mw1030[a] = fr.getCourseID(i);
//                        mw1030n[a] = fr.getCourseName(i);
                        timeday.get(6)[a] = fr.getCourseID(i);
                        timeday.get(7)[a] = fr.getCourseName(i);
                        a++;
                        break;
                    case "TR":
//                        tr1030[b] = fr.getCourseID(i);
//                        tr1030n[b] = fr.getCourseName(i);
                        timeday.get(8)[b] = fr.getCourseID(i);
                        timeday.get(9)[b] = fr.getCourseName(i);
                        b++;
                        break;
                    case "FS":
//                        fs1030[c] = fr.getCourseID(i);
//                        fs1030n[c] = fr.getCourseName(i);
                        timeday.get(10)[c] = fr.getCourseID(i);
                        timeday.get(11)[c] = fr.getCourseName(i);
                        c++;
                        break;
                }
            }
        }
        for (int i = 0, a = 0, b = 0, c = 0; i < fr.getLineNum(); i++) {
            if (fr.getCourseTime(i).equals("12:30")) {
                switch (fr.getCourseDays(i)) {
                    case "MW":
//                        mw1230[a] = fr.getCourseID(i);
//                        mw1230n[a] = fr.getCourseName(i);
                        timeday.get(12)[a] = fr.getCourseID(i);
                        timeday.get(13)[a] = fr.getCourseName(i);
                        a++;
                        break;
                    case "TR":
//                        tr1230[b] = fr.getCourseID(i);
//                        tr1230n[b] = fr.getCourseName(i);
                        timeday.get(14)[b] = fr.getCourseID(i);
                        timeday.get(15)[b] = fr.getCourseName(i);
                        b++;
                        break;
                    case "FS":
//                        fs1230[c] = fr.getCourseID(i);
//                        fs1230n[c] = fr.getCourseName(i);
                        timeday.get(16)[c] = fr.getCourseID(i);
                        timeday.get(17)[c] = fr.getCourseName(i);
                        c++;
                        break;
                }
            }
        }
        for (int i = 0, a = 0, b = 0, c = 0; i < fr.getLineNum(); i++) {
            if (fr.getCourseTime(i).equals("14:30")) {
                switch (fr.getCourseDays(i)) {
                    case "MW":
//                        mw1430[a] = fr.getCourseID(i);
//                        mw1430n[a] = fr.getCourseName(i);
                        timeday.get(18)[a] = fr.getCourseID(i);
                        timeday.get(19)[a] = fr.getCourseName(i);
                        a++;
                        break;
                    case "TR":
//                        tr1430[b] = fr.getCourseID(i);
//                        tr1430n[b] = fr.getCourseName(i);
                        timeday.get(20)[b] = fr.getCourseID(i);
                        timeday.get(21)[b] = fr.getCourseName(i);
                        b++;
                        break;
                    case "FS":
//                        fs1430[c] = fr.getCourseID(i);
//                        fs1430n[c] = fr.getCourseName(i);
                        timeday.get(22)[c] = fr.getCourseID(i);
                        timeday.get(23)[c] = fr.getCourseName(i);
                        c++;
                        break;
                }
            }
        }
        for (int i = 0, a = 0, b = 0, c = 0; i < fr.getLineNum(); i++) {
            if (fr.getCourseTime(i).equals("16:30")) {
                switch (fr.getCourseDays(i)) {
                    case "MW":
//                        mw1630[a] = fr.getCourseID(i);
//                        mw1630n[a] = fr.getCourseName(i);
                        timeday.get(24)[a] = fr.getCourseID(i);
                        timeday.get(25)[a] = fr.getCourseName(i);
                        a++;
                        break;
                    case "TR":
//                        tr1630[b] = fr.getCourseID(i);
//                        tr1630n[b] = fr.getCourseName(i);
                        timeday.get(26)[b] = fr.getCourseID(i);
                        timeday.get(27)[b] = fr.getCourseName(i);
                        b++;
                        break;
                    case "FS":
//                        fs1630[c] = fr.getCourseID(i);
//                        fs1630n[c] = fr.getCourseName(i);
                        timeday.get(28)[c] = fr.getCourseID(i);
                        timeday.get(29)[c] = fr.getCourseName(i);
                        c++;
                        break;
                }
            }
        }
    }

    private void addCells() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;

        /*
         * Get length of ID array timeday.get(0,2,4,6,8...28)
         * timeday.get(0) = mw830id, 2 = tr830id, 4 = fs830id...28 = fs1630id
         * if there are overlaps in courses (same time and day) then display buttons labeled "MULTI"
         * If there is only one single course without overlaps then display the course ID directly on the button
         * tmpstr[0] = mw830id, 1 = tr830id, 2 = fs830id, 3 = mw1030id...14 = fs1630id
         */
        String[] tmpstr = new String[15];
        for (int i = 0, tmpstrnum = 0; i < 29; i += 2, tmpstrnum++) {
            //System.out.println(i + " = i | x =  " + tmpstrnum);
            if (timeday.get(i).length == 1) {
                tmpstr[tmpstrnum] = timeday.get(i)[0];
                //System.out.println("Single data detected at i:" + i + " | tmpstrnum:" + tmpstrnum + " and tmpstr" + tmpstrnum + " is " + tmpstr[tmpstrnum]);
            } else {
                tmpstr[tmpstrnum] = "multiple " + "(" + timeday.get(i).length + ")";
                //System.out.println(timeday.get(i).length);
            }
        }

        //Create buttons
        for (int x = 0, tmpstrnum = 0; x < 5; x++) {
            //tmpstr[0] = mw830id, 1 = tr830id, 2 = fs830id, 3 = mw1030id...14 = fs1630id
            for (int y = 0; y < 6; y++) {
                /*
                 * x1y1 tmpstr[0] tmpstrnum++ (real value x = 0, y = 0)
                 * x1y2 tmpstr[1] tmpstrnum-- (real value x = 0, y = 1)
                 * x1y3 tmpstr[0] tmpstrnum++
                 * x1y4 tmpstr[1] tmpstrnum++
                 * x1y5 tmpstr[2] 
                 * x1y6 tmpstr[2]
                 * x2y1 tmpstr[3] tmpstrnum++
                 * x2y2 tmpstr[4] tmpstrnum--
                 * x3y3 tmpstr[3] tmpstrnum++
                 * x3y4 tmpstr[4] tmpstrnum++
                 * x3y5 tmpstr[5]
                 * x3y6 tmpstr[5]
                 * and so on...
                 */
                xy[x + 1][y + 1] = new JButton(tmpstr[tmpstrnum]);                
                //Make buttons with multiple overlaps light gray, otherwise white
//                if (xy[x + 1][y + 1].getText().contains("multiple")){
//                    xy[x + 1][y + 1].setBackground(Color.lightGray);
//                } else {
                    xy[x + 1][y + 1].setBackground(Color.white);
//                }
                //For debugging
                //System.out.println("x" + (x+1) + "y" + (y+1) + " | real value x=" + x + " y=" + y + " | tmpstrnum = " + tmpstrnum);
                c.gridx = x + 1;
                c.gridy = y + 1;
                studyTablePane.add(xy[x + 1][y + 1], c);
                if (y == 0 || y == 2 || y == 3) {
                    tmpstrnum++;
                } else if (y == 1) {
                    tmpstrnum--;
                } else if (y == 5) {
                    tmpstrnum++;
                }
            }
        }
    }

    public ArrayList getTimeday() {
        return timeday;
    }

    /*
     * Get timeday as string
     * timeday.get(td)[course]
     * td 0 = mw830id, 1 = mw830name, 2 = tr830id, 3 = tr830name...
     * course = course that belongs to that timeday combo
     */
    public String getTimedaystr(int td, int course) {
        return timeday.get(td)[course];
    }

    public int getTimedaysize(int td) {
        return timeday.get(td).length;
    }

    public JButton getxybutton(int x, int y) {
        return xy[x][y];
    }

    public JPanel getpanel() {
        return studyTablePane;
    }

    //Return GridBagConstraints to use when adding this class's panel into MainWindow
    public GridBagConstraints returnc() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        return c;
    }
    //EOF
}
