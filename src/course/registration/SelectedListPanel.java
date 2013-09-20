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
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SelectedListPanel {

    private JPanel selectedListPane;
    private CourseRegistration cr = new CourseRegistration();
    private MainWindow mainwin = cr.getMainWindow();
    private StudyTablePanel studytableclass = mainwin.getstudytableclass();
    private DefaultListModel clickedlistModel;
    private DefaultListModel selectedlistModel;

    public SelectedListPanel() {
        selectedListPane = new JPanel();
        selectedListPane.setBorder(BorderFactory.createLineBorder(Color.black));
        selectedListPane.setBackground(Color.white);
        selectedListPane.setLayout(new GridLayout(0, 1));

        //List for clicked button
        //Items to add into list
        clickedlistModel = new DefaultListModel();
        //The list
        JList clickedlist = new JList(clickedlistModel);
        //Make it scrollable
        JScrollPane clickedlistScroller = new JScrollPane(clickedlist);
        clickedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedListPane.add(clickedlistScroller);

        //Label
        JLabel info = new JLabel("Current selected course");
        selectedListPane.add(info);

        //List for selected course
        selectedlistModel = new DefaultListModel();
        JList selectedlist = new JList(selectedlistModel);
        JScrollPane selectedlistScroller = new JScrollPane(selectedlist);
        selectedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedListPane.add(selectedlistScroller);

        //Send email button
        JButton sendemail = new JButton("Send e-mail");
        selectedListPane.add(sendemail);
        addaction();
    }

    private void addaction() {
        for (int x = 1; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                //studytableclass.getxybutton(x, y).addActionListener(new xyButtonListener(x, y));
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
            //Remove precious JList clickedlist records
            clickedlistModel.removeAllElements();
            //Make the clicked button yellow
            //studytableclass.getxybutton(xa, ya).setBackground(Color.yellow);
            //Determines which button is pressed
            int tmpint = 0;
            if (evt.getSource() == studytableclass.getxybutton(xa, ya)) {
                //System.out.println("It's x" + xa + " y" + ya);
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
//                            studytableclass.getxybutton(xa, 1).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 3).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 1).setEnabled(false);
                            studytableclass.getxybutton(xa, 3).setEnabled(false);
                            break;
                        case 2:
                        case 4:
//                            studytableclass.getxybutton(xa, 2).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 4).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 2).setEnabled(false);
                            studytableclass.getxybutton(xa, 4).setEnabled(false);
                            break;
                        case 5:
                        case 6:
//                            studytableclass.getxybutton(xa, 5).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 6).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 5).setEnabled(false);
                            studytableclass.getxybutton(xa, 6).setEnabled(false);
                            break;
                    }
                }
            }
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
