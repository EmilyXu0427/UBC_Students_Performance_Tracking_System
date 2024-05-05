package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Construct a welcome window for user;
   The system will ask if user wants to load previous saved data; Select Yes to load data;
   Select No to start with an empty table;
 */
public class WelcomeWindow implements ActionListener {
    private JFrame welcomeFrame = new JFrame();
    private JButton loadButton = new JButton("Yes");
    private JButton noLoadButton = new JButton("No");
    private JLabel welcomeLabel1 = new JLabel("Welcome to UBC CPSC210 Student Management System!");
    private JLabel welcomeLabel2 = new JLabel("Do you want to load the saved data?");

    // EFFECTS: Create a welcome window with buttons and labels
    public WelcomeWindow() {

        welcomeLabel1.setFont(new Font("Verdana",1,20));
        welcomeLabel1.setBounds(80, 200, 800, 40);

        welcomeLabel2.setFont(new Font("Verdana",1,15));
        welcomeLabel2.setBounds(200, 260, 800, 40);

        loadButton.setBounds(260, 350, 200, 40);
        loadButton.setFocusable(false);
        loadButton.addActionListener(this); // need to figure out

        noLoadButton.setBounds(260, 400, 200, 40);
        noLoadButton.setFocusable(false);
        noLoadButton.addActionListener(this); // need to figure out

        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(800, 800);
        welcomeFrame.setLayout(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setTitle("CPSC 210 Student Management System_WelcomeWindow"); //need to rewrite this title later

        welcomeFrame.add(welcomeLabel1);
        welcomeFrame.add(welcomeLabel2);
        welcomeFrame.add(loadButton);
        welcomeFrame.add(noLoadButton);
    }

    // EFFECTS: Add button actions; If user press noLoadButton, data would not be loaded
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == noLoadButton) {
            new OperationWindowWithNoData();
        } else {
//            new OperationWindowWithData();
            new OperationWindowWithSQLDataBase();
        }
    }
}
