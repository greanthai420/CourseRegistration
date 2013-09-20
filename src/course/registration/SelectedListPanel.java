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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private ListSelectionModel selectedlistmodel;
    private int tmpint, tmpint2;

    public SelectedListPanel() {
        selectedListPane = new JPanel();
        selectedListPane.setBorder(BorderFactory.createLineBorder(Color.black));
        selectedListPane.setBackground(Color.white);
        selectedListPane.setLayout(new GridLayout(0, 1));

        JLabel greetmsg = new JLabel("Welcome! " + mainwin.getStudentInfo("insname_f"));
        selectedListPane.add(greetmsg);

        //List for clicked button
        //Items to add into list
        clickedlistModel = new DefaultListModel();
        //The list
        clickedlist = new JList(clickedlistModel);
        //Make it scrollable
        JScrollPane clickedlistScroller = new JScrollPane(clickedlist);
        clickedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedListPane.add(clickedlistScroller);
        //Adds mouse listener
        clickedlist.addMouseListener(new clickedlist_mouselisten());

        //Label
        JLabel info = new JLabel("Current selected course");
        selectedListPane.add(info);

        //List for selected course

        selectedlistModel = new DefaultListModel();
        selectedlist = new JList(selectedlistModel);
        JScrollPane selectedlistScroller = new JScrollPane(selectedlist);
        selectedlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedlistmodel = selectedlist.getSelectionModel();
        //selectedlistmodel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        for (int x = 1; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                studytableclass.getxybutton(x, y).addActionListener(new xyButtonListener(x, y));
            }
        }

        selectedlistmodel.addListSelectionListener(new selectedlistListener());
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

            //Remove other button's background color if they are yellow
            for (int xi = 1; xi <= 5; xi++) {
                for (int yi = 1; yi <= 6; yi++) {
                    if (studytableclass.getxybutton(xi, yi).getBackground().equals(Color.yellow)) {
                        studytableclass.getxybutton(xi, yi).setBackground(Color.white);
                    }
                }
            }

            //Determines which button is pressed
            tmpint = 0;
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
                            studytableclass.getxybutton(xa, 1).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 3).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 1).setEnabled(false);
//                            studytableclass.getxybutton(xa, 3).setEnabled(false);
                            break;
                        case 2:
                        case 4:
                            studytableclass.getxybutton(xa, 2).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 4).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 2).setEnabled(false);
//                            studytableclass.getxybutton(xa, 4).setEnabled(false);
                            break;
                        case 5:
                        case 6:
                            studytableclass.getxybutton(xa, 5).setBackground(Color.gray);
                            studytableclass.getxybutton(xa, 6).setBackground(Color.gray);
//                            studytableclass.getxybutton(xa, 5).setEnabled(false);
//                            studytableclass.getxybutton(xa, 6).setEnabled(false);
                            break;
                    }
                }
            }
        }
    }

    private class selectedlistListener implements ListSelectionListener {

        private int a = 0;

        @Override
        public void valueChanged(ListSelectionEvent e) {
            boolean isAdjusting = e.getValueIsAdjusting();

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
