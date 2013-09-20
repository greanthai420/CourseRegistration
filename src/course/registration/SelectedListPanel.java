/*
 * AUTHOR
 * SUTHIPONG THONGJAROEN 212210025
 * Tanakrit Pilaphaeng 212310097
 * 
 * Selected listpanel in main window.
 * Migrated to a new class/file because of long code.
 */
package course.registration;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SelectedListPanel {

    //Import things from other class without duplicating windows
    private final CourseRegistration cr = new CourseRegistration();
    private final MainWindow mainwin = cr.getMainWindow();
    private final StudyTablePanel studytableclass = mainwin.getstudytableclass();
    //fields to use
    private JPanel selectedListPane;
    private JList clickedlist;
    private DefaultListModel clickedlistModel;
    private JList selectedlist;
    private DefaultListModel selectedlistModel;
    private int tmpint;

    public SelectedListPanel() {
        selectedListPane = new JPanel();
        selectedListPane.setBorder(BorderFactory.createLineBorder(Color.black));
        selectedListPane.setBackground(Color.white);
        selectedListPane.setLayout(new GridLayout(0, 1));

        JLabel greetmsg = new JLabel("Welcome! " + mainwin.getStudentInfo("insname_f"));
        selectedListPane.add(greetmsg);

        /*
         * Create list for listing overlaps from xy buttons
         */
        clickedlistModel = new DefaultListModel();
        clickedlist = new JList(clickedlistModel);
        clickedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Event listener
        clickedlist.addMouseListener(new clickedlist_mouselisten());
        JScrollPane clickedlistScroller = new JScrollPane(clickedlist);        
        selectedListPane.add(clickedlistScroller);

        //Label
        JLabel info = new JLabel("Current selected course");
        selectedListPane.add(info);

        /*
         * Create list for chosen courses
         */
        selectedlistModel = new DefaultListModel();
        selectedlist = new JList(selectedlistModel);
        selectedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane selectedlistScroller = new JScrollPane(selectedlist);
        selectedListPane.add(selectedlistScroller);

        //Send email button
        JButton sendemail = new JButton("Send e-mail");
        selectedListPane.add(sendemail);

        /*
         * Add actions for xy buttons from studytablepanel
         * Add actions for lists
         */
        addEventListener();
    }

    private void addEventListener() {
        //Adds event listener for every buttons
        for (int x = 1; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                studytableclass.getxybutton(x, y).addActionListener(new xyButtonListener(x, y));
            }
        }
    }

    private class xyButtonListener implements ActionListener {

        private int xa, ya;

        public xyButtonListener(int x, int y) {
            xa = x;
            ya = y;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //Initialize the list
            if (!clickedlistModel.isEmpty()) {
                clickedlistModel.removeAllElements();
            }
            clickedlist.setEnabled(true);

            //Remove other button's background color if they are yellow
            for (int xi = 1; xi <= 5; xi++) {
                for (int yi = 1; yi <= 6; yi++) {
                    if (studytableclass.getxybutton(xi, yi).getBackground().equals(Color.yellow)) {
                        studytableclass.getxybutton(xi, yi).setBackground(Color.white);
                    }
                }
            }

            /*
             * Determines which button is pressed
             * Only do event if the button is not cyan (not already added to selected list
             */
            tmpint = 0;
            if (evt.getSource() == studytableclass.getxybutton(xa, ya) && !(studytableclass.getxybutton(xa, ya).getBackground().equals(Color.cyan))) {
                if (xa == 1 && (ya == 1 || ya == 3)) {
                    //timeday.get(0)                    
                    tmpint = 0;
                } else if (xa == 1 && (ya == 2 || ya == 4)) {
                    //timeday.get(2)
                    tmpint = 2;
                } else if (xa == 1 && (ya == 5 || ya == 6)) {
                    //timeday.get(4)
                    tmpint = 4;
                } else if (xa == 2 && (ya == 1 || ya == 3)) {
                    //timeday.get(6)
                    tmpint = 6;
                } else if (xa == 2 && (ya == 2 || ya == 4)) {
                    //timeday.get(8)
                    tmpint = 8;
                } else if (xa == 2 && (ya == 5 || ya == 6)) {
                    //timeday.get(10)
                    tmpint = 10;
                } else if (xa == 3 && (ya == 1 || ya == 3)) {
                    //timeday.get(12)
                    tmpint = 12;
                } else if (xa == 3 && (ya == 2 || ya == 4)) {
                    //timeday.get(14)
                    tmpint = 14;
                } else if (xa == 3 && (ya == 5 || ya == 6)) {
                    //timeday.get(16)
                    tmpint = 16;
                } else if (xa == 4 && (ya == 1 || ya == 3)) {
                    //timeday.get(18)
                    tmpint = 18;
                } else if (xa == 4 && (ya == 2 || ya == 4)) {
                    //timeday.get(20)
                    tmpint = 20;
                } else if (xa == 4 && (ya == 5 || ya == 6)) {
                    //timeday.get(22)
                    tmpint = 22;
                } else if (xa == 5 && (ya == 1 || ya == 3)) {
                    //timeday.get(24)
                    tmpint = 24;
                } else if (xa == 5 && (ya == 2 || ya == 4)) {
                    //timeday.get(26)
                    tmpint = 26;
                } else if (xa == 5 && (ya == 5 || ya == 6)) {
                    //timeday.get(28)
                    tmpint = 28;
                }

                /*
                 * Shows the complete list of overlapping courses in JList clickedlist
                 */
                if (studytableclass.getTimedaysize(tmpint) != 1 && clickedlistModel.isEmpty()) {
                    for (int i = 0; i < studytableclass.getTimedaysize(tmpint); i++) {
                        clickedlistModel.addElement(studytableclass.getTimedaystr(tmpint, i));
                        clickedlistModel.add(i, studytableclass.getTimedaystr(tmpint, i));
                        //ArrayList<String[]> a = studytableclass.getTimeday();
                        //clickedlistModel.addElement(a.get(tmpint)[i]);
                    }
                    //Make the corresponding buttons yellow
                    switch (ya) {
                        case 1:
                        case 3:
                            studytableclass.getxybutton(xa, 1).setBackground(Color.yellow);
                            studytableclass.getxybutton(xa, 3).setBackground(Color.yellow);
                            break;
                        case 2:
                        case 4:
                            studytableclass.getxybutton(xa, 2).setBackground(Color.yellow);
                            studytableclass.getxybutton(xa, 4).setBackground(Color.yellow);
                            break;
                        case 5:
                        case 6:
                            studytableclass.getxybutton(xa, 5).setBackground(Color.yellow);
                            studytableclass.getxybutton(xa, 6).setBackground(Color.yellow);
                            break;
                    }
                    //Adds directly to selectedlist if there is only one course without overlaps
                } else if (studytableclass.getTimedaysize(tmpint) == 1 && !(selectedlistModel.contains(studytableclass.getTimedaystr(tmpint, 0)))) {
                    selectedlistModel.addElement(studytableclass.getTimedaystr(tmpint, 0));
                    switch (ya) {
                        case 1:
                        case 3:
                            studytableclass.getxybutton(xa, 1).setBackground(Color.cyan);
                            studytableclass.getxybutton(xa, 3).setBackground(Color.cyan);
//                            studytableclass.getxybutton(xa, 1).setEnabled(false);
//                            studytableclass.getxybutton(xa, 3).setEnabled(false);
                            break;
                        case 2:
                        case 4:
                            studytableclass.getxybutton(xa, 2).setBackground(Color.cyan);
                            studytableclass.getxybutton(xa, 4).setBackground(Color.cyan);
//                            studytableclass.getxybutton(xa, 2).setEnabled(false);
//                            studytableclass.getxybutton(xa, 4).setEnabled(false);
                            break;
                        case 5:
                        case 6:
                            studytableclass.getxybutton(xa, 5).setBackground(Color.cyan);
                            studytableclass.getxybutton(xa, 6).setBackground(Color.cyan);
//                            studytableclass.getxybutton(xa, 5).setEnabled(false);
//                            studytableclass.getxybutton(xa, 6).setEnabled(false);
                            break;
                    }
                }
            }
        }
    }

    private class clickedlist_mouselisten implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                //Only adds to selectedlist if the item isn't already there
                String tmpstr = studytableclass.getTimedaystr(tmpint, clickedlist.getSelectedIndex());
                if (!(selectedlistModel.contains(tmpstr))) {

                    selectedlistModel.addElement(studytableclass.getTimedaystr(tmpint, clickedlist.getSelectedIndex()));
                    //Prevents user from adding more from the same day/time
                    clickedlist.setEnabled(false);                    

                    //DEBUG : checks for course name in addition to course ID
                    //System.out.println(studytableclass.getTimedaystr(tmpint+1, clickedlist.getSelectedIndex()));

                    int x = 0;
                    int[] y = new int[2];


                    if (tmpint == 0 || tmpint == 2 || tmpint == 4) {
                        x = 1;
                    } else if (tmpint == 6 || tmpint == 8 || tmpint == 10) {
                        x = 2;
                    } else if (tmpint == 12 || tmpint == 14 || tmpint == 16) {
                        x = 3;
                    } else if (tmpint == 18 || tmpint == 20 || tmpint == 22) {
                        x = 4;
                    } else if (tmpint == 24 || tmpint == 26 || tmpint == 28) {
                        x = 5;
                    }

                    if (tmpint == 0 || tmpint == 6 || tmpint == 12 || tmpint == 18 || tmpint == 24) {
                        y[0] = 1;
                        y[1] = 3;
//                        for (int i = 1; i <= 6; i++) {
//                            studytableclass.getxybutton(x, i).setBackground(Color.gray);
//                        }
                    } else if (tmpint == 2 || tmpint == 8 || tmpint == 14 || tmpint == 20 || tmpint == 26) {
                        y[0] = 2;
                        y[1] = 4;
//                        for (int i = 1; i <= 6; i++) {
//                            studytableclass.getxybutton(x, i).setBackground(Color.gray);
//                        }
                    } else {
                        y[0] = 5;
                        y[1] = 6;
//                        for (int i = 1; i <= 6; i++) {
//                            studytableclass.getxybutton(x, i).setBackground(Color.gray);
//                        }
                    }

                    studytableclass.getxybutton(x, y[0]).setBackground(Color.cyan);
                    studytableclass.getxybutton(x, y[1]).setBackground(Color.cyan);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent evt) {
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
        }

        @Override
        public void mousePressed(MouseEvent evt) {
        }

        @Override
        public void mouseExited(MouseEvent evt) {
        }
    }

    public JPanel getpanel() {
        return selectedListPane;
    }

    public GridBagConstraints returnc() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        return c;
    }
}
