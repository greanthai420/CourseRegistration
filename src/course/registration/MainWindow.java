/*
 * AUTHOR
 * SUTHIPONG THONGJAROEN 212210025
 * Tanakrit Pilaphaeng 212310097
 * 
 * Class to create main window GUI and a bunch of things.
 */
package course.registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory.*;

public class MainWindow extends JFrame {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private Container contentpane;
    //menu bar
    private JMenuBar menuBar;
    //menu categories
    private JMenu menu;
    private JMenu about;
    //items under menu categories
    private JMenuItem openItem;
    private JMenuItem exitItem;
    private JMenuItem aboutItem;
    //panels
    private JPanel selectedListPane;
    //Study table panel
    private StudyTablePanel studytableclass;
    private SelectedListPanel selectedclass;
    //private JPanel studyTablePane;
    private JPanel headerLogoPane;
    private JPanel mainPane;
    //Input fields
    private JTextField insname_f_first;
    private String insname_f;
    private JTextField insname_f_last;
    private String insname_l;
    private JTextField insID_f;
    private int insID;
    private JComboBox insMajor_c;
    private String insMajor;
    //File reader
    private FileReader filereader;
    //Lists in selectedpanel
    private DefaultListModel clickedlistModel;
    private DefaultListModel selectedlistModel;

    //Constructor without fixed read from file
    public MainWindow() {
        //Setting up
        contentpane = getContentPane();
        contentpane.setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Course Selection");
        //Calling and adding items
        menubar();
        headerpanel();
        mainpanel();
        //Finalize
        setContentPane(contentpane);
        setVisible(true);
    }

    //Constructor with fixed read from file
    public MainWindow(String file) throws IOException {
        //Setting up
        contentpane = getContentPane();
        contentpane.setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Course Selection");
        //Calling and adding items
        menubar();
        headerpanel();
        mainpanel();
        //Finalize
        setContentPane(contentpane);
        setVisible(true);
        //Read from file
        File filechosen = new File(file);
        filereader = new FileReader(filechosen);
    }

    private void menubar() {
        //Create the menu bar and assign to window
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //Create and setup menu categories and items
        menu = new JMenu("Menu");
        about = new JMenu("About");
        openItem = new JMenuItem("Read from file");
        exitItem = new JMenuItem("Exit");
        aboutItem = new JMenuItem("About this program");
        menuBar.add(menu);
        menuBar.add(about);
        about.add(aboutItem);
        menu.add(openItem);
        openItem.setEnabled(false);
        menu.add(exitItem);
        menu.setMnemonic(KeyEvent.VK_E);
        openItem.setMnemonic(KeyEvent.VK_O);
        exitItem.setMnemonic(KeyEvent.VK_X);

        //Reads file
        //Add event listener
        openItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openItemPerformed(evt);
                } catch (IOException ex) {
                    //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exitItemPerformed(evt);
            }
        });
    }

    /**
     * UNFINISHED Open a file chooser and call return chosen file to FileReader
     * class *
     */
    private void openItemPerformed(ActionEvent evt) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        //Set filter (TXT and CSV files only)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT and CSV files", "csv", "txt");
        fileChooser.setFileFilter(filter);
        int chosenFile = fileChooser.showOpenDialog(MainWindow.this);
        if (chosenFile == JFileChooser.APPROVE_OPTION) {
            File chosen = fileChooser.getSelectedFile();
            //Sends the file to FileReader class to read from
            filereader = new FileReader(chosen);
            //filereader.getCourseName(0);
        }
    }

    //Exit the program
    private void exitItemPerformed(ActionEvent evt) {
        System.exit(0);
    }

    //Setup the header panel on the top with image of stamford.jpg and black line border
    private void headerpanel() {
        ImageIcon logo = new ImageIcon("stamford.jpg");
        JLabel label = new JLabel();
        label.setIcon(logo);
        headerLogoPane = new JPanel();
        headerLogoPane.setBorder(BorderFactory.createLineBorder(Color.black));
        headerLogoPane.setBackground(Color.white);
        headerLogoPane.add(label);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 0.00;
        contentpane.add(headerLogoPane, c);
    }

    private void mainpanel() {
        mainPane = new JPanel();
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        mainPane.setBorder(BorderFactory.createLineBorder(Color.black));
        //mainPane.setBackground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        contentpane.add(mainPane, c);

        //Name fields        
        //Label
        JLabel insname_la = new JLabel("Insert your name");
        Font labelfontdef = insname_la.getFont();
        insname_la.setFont(new Font(labelfontdef.getName(), labelfontdef.getStyle(), 20));
        c.gridx = 0;
        c.gridy = 0;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.00;
        c.weighty = 0.00;
//        c.insets = new Insets(0,10,0,0);
        mainPane.add(insname_la, c);

        //First name
        insname_f_first = new JTextField();
        insname_f_first.setText("First name");
        c.gridx = 1;
        c.gridy = 0;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        mainPane.add(insname_f_first, c);

        //Last name
        insname_f_last = new JTextField();
        insname_f_last.setText("Last name");
        c.gridx = 2;
        c.gridy = 0;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        //c.fill = GridBagConstraints.VERTICAL;
        //c.weightx = 1.00;
//        c.weighty = 1.00;
        mainPane.add(insname_f_last, c);

        //Student ID field
        //Label
        JLabel insID_la = new JLabel("Insert your student ID");
        insID_la.setFont(new Font(labelfontdef.getName(), labelfontdef.getStyle(), 20));
        c.gridx = 0;
        c.gridy = 1;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.00;
        c.weighty = 0.00;
        mainPane.add(insID_la, c);

        //Field
        insID_f = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        mainPane.add(insID_f, c);

        //Major field   
        //Label
        JLabel insMajor_la = new JLabel("Select your Major");
        insMajor_la.setFont(new Font(labelfontdef.getName(), labelfontdef.getStyle(), 20));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.00;
        c.weighty = 0.00;
        mainPane.add(insMajor_la, c);

        //Combobox
        String[] insMajor_l = {
            "Airline Business Management", "Finance and Banking Management", "International Business Management",
            "Logistics Management", "Management", "Marketing", "International Hotel Management", "Accountancy",
            "Communication Arts", "Advertising", "English Language Studies", "Information Technology", "Business Information Management"
        };
        insMajor_c = new JComboBox(insMajor_l);
        insMajor_c.setBackground(Color.white);
        //Limit the size of combobox
        insMajor_c.setPrototypeDisplayValue("x");
        //insMajor_c.setPreferredSize(new Dimension(100, 0));
        //insMajor_c.setSize(1, insMajor_c.getPreferredSize().height);
        c.gridx = 1;
        c.gridy = 2;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        //c.weightx = 1.00;
        // c.weighty = 1.00;
        mainPane.add(insMajor_c, c);

        //OK Button
        JButton okButton = new JButton("OK");
        //okButton.setBackground(Color.white);
        c.gridx = 2;
        c.gridy = 2;
//        c.gridwidth = 1;
//        c.gridheight = 1;
        //c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        mainPane.add(okButton, c);
        //Add event listener
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                OkButtonPerformed(evt);
            }
        });
    }

    //OK button on mainPane
    private void OkButtonPerformed(ActionEvent evt) {
        //Show error popup if a text field is empty
        //if not empty
        if (!(insname_f_first.getText().isEmpty() || insname_f_last.getText().isEmpty() || insID_f.getText().isEmpty())) {
            insname_f = insname_f_first.getText();
            insname_l = insname_f_last.getText();
            insMajor = insMajor_c.getSelectedItem().toString();

            //Input validation for student ID
            Scanner tmp = new Scanner(insID_f.getText());
            if (tmp.hasNextInt()) {
                insID = Integer.parseInt(insID_f.getText());
                //Remove headerpane and mainpane
                mainPane.setVisible(false);
                headerLogoPane.setVisible(false);
                contentpane.remove(mainPane);
                contentpane.remove(headerLogoPane);
                //Call studytablepane and selectedpane
                //studytablepanel();
                studytableclass = new StudyTablePanel(filereader);
                contentpane.add(studytableclass.getpanel(), studytableclass.returnc());
                //selectedpanel();
                selectedclass = new SelectedListPanel();
                contentpane.add(selectedclass.getpanel(), selectedclass.returnc());
            } else {
                JOptionPane.showMessageDialog(null, "Student ID must be numbers!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // if empty
            JOptionPane.showMessageDialog(null, "Please complete all information!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectedpanel() {
        selectedListPane = new JPanel();
        selectedListPane.setBorder(BorderFactory.createLineBorder(Color.black));
        selectedListPane.setBackground(Color.white);
        //selectedListPane.setLayout(new BoxLayout(selectedListPane, BoxLayout.PAGE_AXIS));
        selectedListPane.setLayout(new GridLayout(0, 1));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.00;
        c.weighty = 1.00;
        contentpane.add(selectedListPane, c);

        //Greeting message with first name
        JLabel greetmsg = new JLabel("Welcome! " + insname_f);
        //greetmsg.setHorizontalAlignment(SwingConstants.CENTER);
        //greetmsg.setBorder(BorderFactory.createLineBorder(Color.black));
        selectedListPane.add(greetmsg);

        /*
         * Adds actionlistener to all studytablepane cells
         * Do things when cells (xy buttons) in studytablepane are clicked
         * 
         * Starts from x1y1 not x0y0 becausex x0y0 are null to x5y6
         */
        for (int x = 1; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                studytableclass.getxybutton(x, y).addActionListener(new xyButtonListener(x, y));
            }
        }

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
    }

    /*
     * For use with selectedListPane
     * Perform operations based on which cells (xy buttons) are pressed
     */
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
    //private StudyTablePanel studytableclass;

    public StudyTablePanel getstudytableclass() {
        return studytableclass;
    }
    //EOF
}