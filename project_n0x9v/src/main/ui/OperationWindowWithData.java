package ui;

import model.EventLog;
import model.Event;
import model.Student;
import model.Students;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/* Operation window interface displays previously saved data, buttons, text fields, for user to add, find, remove
 * students
 */
public class OperationWindowWithData implements ActionListener {
    private JFrame operationFrame = new JFrame();
    protected Students students = new Students("2020Winter1");
    protected DefaultTableModel model; // this field shared with OperationWindowWithNoData
    protected JTable studentsTable; // this field shared with OperationWindowWithNoData

    private JButton addStudentButton = new JButton("Add New Student");
    private JButton removeStudentButton;
    private JButton findStudentButton;

    private JPanel headerPanel;
    protected JPanel displayPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;
    private JPanel buttonPanel3;

    private JTextField textStudentId = new JTextField();
    private JTextField textProjectGrade = new JTextField();
    private JTextField textMidtermGrade = new JTextField();
    private JTextField textFinalExamGrade = new JTextField();

    private JLabel studentIdLabel = new JLabel("Student ID");
    private JLabel projectGradeLabel = new JLabel("Project Grade");
    private JLabel midtermGradeLabel = new JLabel("Midterm Grade");
    private JLabel finalExamGradeLabel = new JLabel("Final Exam Grade");

    private JTextField findStudentIdText;
    private JLabel findStudentIdLabel;

    private JTextField removeStudentIdText;
    private JLabel removeStudentIdLabel;

    private JOptionPane showMessageDialog;

    public String jsonFilePath = "./project_n0x9v/data/ProjectData.json";
    public String logoFilePath = "./project_n0x9v/data/UBC logo.JPG";

    // EFFECTS: construct an operation window with previous saved data
    public OperationWindowWithData() {
        windowSetUp();
    }

    /* EFFECTS: set up operation window with header panel, display panel and button panel: The display panel will
     * display previously saved data; The button panels includes labels, texts and buttons for user to add, find and
     * remove student.
     */
    public void windowSetUp() {

        operationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        operationFrame.setSize(1000, 800);
        operationFrame.setLayout(null);
        operationFrame.setVisible(true);
        operationFrame.setTitle("CPSC 210 Student Management System_OperationWindow");
        operationFrame.setBackground(Color.white);

        headerPanel = new JPanel();
        headerPanel.setBackground(Color.white);
        headerPanel.setBounds(0, 0, 1000, 100);
        setUpHeaderPanel();
        setUpButtonPanel();

        operationFrame.add(buttonPanel1);
        operationFrame.add(buttonPanel2);
        operationFrame.add(buttonPanel3);
        operationFrame.add(displayPanel);
        operationFrame.add(headerPanel);
        setUpDisplayPanel();

        operationFrame.addWindowListener(new MyWindowListener() {
            /* EFFECTS: prompt a window to ask if user wants to save the updated data; If yes, the data will be saved to
             * Json file; if not, changes will not be saved.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                promptToSaveData();
            }
        });
    }

    // EFFECTS: set up button panel
    public void setUpButtonPanel() {
        buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(Color.white);
        buttonPanel1.setBounds(0, 400, 400, 400);

        buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(Color.white);
        buttonPanel2.setBounds(400, 400, 300, 400);

        buttonPanel3 = new JPanel();
        buttonPanel3.setBackground(Color.white);
        buttonPanel3.setBounds(700, 400, 300, 400);

        displayPanel = new JPanel();
        displayPanel.setBackground(Color.white);
        displayPanel.setBounds(0, 100, 1000,300);

        setUpButtonPanel1();
        setUpButtonPanel2();
        setUpButtonPanel3();
    }

    // EFFECTS: set up header panel with UBC logo
    public void setUpHeaderPanel() {
        BufferedImage myPicture = null;
        try {
//            myPicture = ImageIO.read(new File("./data/UBC logo.JPG"));
            myPicture = ImageIO.read(new File(logoFilePath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image newImage = myPicture.getScaledInstance(600, 90, Image.SCALE_SMOOTH);
        JLabel headerLabel = new JLabel(new ImageIcon(newImage));
        headerLabel.setSize(200, 200);
        headerPanel.add(headerLabel);
    }

    // EFFECTS: set up the button panel 1 which allows user to add new student.
    public void setUpButtonPanel1() {

        addStudentButton.setBounds(100,430,200,25);
        textStudentId.setBounds(200, 460, 100, 25);
        textProjectGrade.setBounds(200, 490, 100, 25);
        textMidtermGrade.setBounds(200, 520, 100, 25);
        textFinalExamGrade.setBounds(200, 550, 100, 25);
        studentIdLabel.setBounds(60, 460, 150, 25);
        projectGradeLabel.setBounds(60, 490, 150, 25);
        midtermGradeLabel.setBounds(60, 520, 150, 25);
        finalExamGradeLabel.setBounds(60, 550, 150, 25);

        textStudentId.setBounds(200, 460, 100, 25);
        textProjectGrade.setBounds(200, 490, 100, 25);
        textMidtermGrade.setBounds(200, 520, 100, 25);
        textFinalExamGrade.setBounds(200, 550, 100, 25);

        operationFrame.add(addStudentButton);
        operationFrame.add(textStudentId);
        operationFrame.add(textProjectGrade);
        operationFrame.add(textMidtermGrade);
        operationFrame.add(textFinalExamGrade);
        operationFrame.add(studentIdLabel);
        operationFrame.add(projectGradeLabel);
        operationFrame.add(midtermGradeLabel);
        operationFrame.add(finalExamGradeLabel);

        addStudentButton.addActionListener(this);
    }

    // EFFECTS: set up button panel 2 which allows user to search for student by entering student ID
    public void setUpButtonPanel2() {
        findStudentButton = new JButton("Find Student");
        findStudentIdText = new JTextField();
        findStudentIdLabel = new JLabel("Student ID");

        findStudentButton.setBounds(430, 430, 170, 25);
        findStudentIdText.setBounds(500, 480, 100, 25);
        findStudentIdLabel.setBounds(430, 480, 100, 25);

        operationFrame.add(findStudentButton);
        operationFrame.add(findStudentIdLabel);
        operationFrame.add(findStudentIdText);

        findStudentButton.addActionListener(this);
    }

    // EFFECTS: set up button panel 3 which allows user to delete a student by entering student ID.
    public void setUpButtonPanel3() {
        removeStudentButton = new JButton("Remove Student");
        removeStudentButton.setBounds(730, 430, 200, 25);

        removeStudentIdText = new JTextField();
        removeStudentIdText.setBounds(800, 480, 100, 25);

        removeStudentIdLabel = new JLabel("Student ID");
        removeStudentIdLabel.setBounds(730, 480, 100, 25);

        operationFrame.add(removeStudentButton);
        operationFrame.add(removeStudentIdText);
        operationFrame.add(removeStudentIdLabel);

        removeStudentButton.addActionListener(this);
    }

    // EFFECTS: set up display panel to display the previous saved data of students information
    public void setUpDisplayPanel() {
        importData();
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        displayPanel.add(scrollPane);
    }

    // EFFECTS: check if the given student ID already exists in the system; If yes, return true; otherwise, return false
    public boolean checkStudent(String studentID) {
        for (int i = 0; i < model.getRowCount(); i++) {
            String modelString = model.getValueAt(i, 0).toString();
            if (studentID.equals(modelString)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: initiate an empty table and set up the column name
    public void constructModel() {
        String[] columNames = {"Student ID", "Project Grade", "Midterm Grade", "Final Exam Grade", "Total Grade",
                "Final Performance"};
        int numRows = 0;

        model = new DefaultTableModel(numRows, columNames.length);
        model.setColumnIdentifiers(columNames);
    }

    // EFFECTS: import previous saved data from Json file to Jtable
    public void importData() {
        try {
            JsonReader jsonReader = new JsonReader(jsonFilePath);
            students = jsonReader.read();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        constructModel();

        if (students != null) {
            List<Student> stu = students.getStudents();
            for (int i = 0; i < stu.size(); i++) {
                String col0 = Integer.toString(stu.get(i).getStudentID());
                String col1 = Double.toString(stu.get(i).getProjectGrade());
                String col2 = Double.toString(stu.get(i).getMidtermGrade());
                String col3 = Double.toString(stu.get(i).getFinalExamGrade());
                String col4 = Double.toString(stu.get(i).getTotalGrade());
                String col5 = stu.get(i).getFinalPerformance();
                model.addRow(new Object[] {col0,col1, col2, col3,col4,col5});
            }
        }
        studentsTable = new JTable(model);
    }

    // EFFECTS: save the updated data from Jtable to Jason file
    public void saveData() {
        JsonWriter jsonWriter = new JsonWriter(jsonFilePath);

        try {
            jsonWriter.open();
            jsonWriter.write(students);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: ./data/ProjectData.json");
        }
    }

    /* EFFECTS: prompt a window to ask if user wants to save the updated data; If yes, the data will be saved to
     * Json file; if not, changes will not be saved.
     */
    public void promptToSaveData() {
        int option = JOptionPane.showConfirmDialog(
                operationFrame,
                "Do you want to save your data before closing?",
                "Save Data",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_NO_OPTION) {
            saveData();
        }

        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n\n");
        }
        EventLog.getInstance().clear();
    }

    /* MODIFIES: this
     * EFFECTS: take user input to add student in the system.
     */
    public void addStudent() {
        Object[] row = new Object[6];
        row[0] = textStudentId.getText();
        row[1] = textProjectGrade.getText();
        row[2] = textMidtermGrade.getText();
        row[3] = textFinalExamGrade.getText();

        int studentID = Integer.parseInt(textStudentId.getText());
        double projectGrade = Double.parseDouble(textProjectGrade.getText());
        double midTermGrade = Double.parseDouble(textMidtermGrade.getText());
        double finalExamGrade = Double.parseDouble(textFinalExamGrade.getText());

        Student s = new Student(studentID, projectGrade, midTermGrade, finalExamGrade);

        row[4] = Double.toString(s.getTotalGrade());
        row[5] = s.getFinalPerformance();

        String addStudentID = textStudentId.getText().toString();
        if (checkStudent(addStudentID)) {
            showMessageDialog.showMessageDialog(null, "Student "
                    + Integer.parseInt(String.valueOf(studentID)) + " already exists!");
        } else {
            model.addRow(row);
            showMessageDialog.showMessageDialog(null, "Student "
                    + Integer.parseInt(String.valueOf(studentID)) + " added!");
            students.addStudent(s);
        }
    }

    /* MODIFIES: this
     * EFFECTS: take user input to remove student in the system.
     */
    public void removeStudent() {
        String removeStudentID = removeStudentIdText.getText();
        if (! checkStudent(removeStudentID)) {
            showMessageDialog.showMessageDialog(null, "Student " + removeStudentID
                    + " does not exist!");
        } else {
            for (int i = 0; i < model.getRowCount(); i++) {
                if (removeStudentIdText.getText().toString().equals(model.getValueAt(i, 0).toString())) {
                    String storeValue = removeStudentIdText.getText().toString();
                    model.removeRow(i);
                    showMessageDialog.showMessageDialog(null, "Student " + storeValue
                            + " removed!");
                    students.removeStudent(Integer.parseInt(storeValue));
                }
            }
        }
    }

    /* MODIFIES: this
     * EFFECTS: take user input to find student in the system.
     */
    public void findStudent() {
        String findStudentID = findStudentIdText.getText().toString();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        studentsTable.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(findStudentID));

        if (!checkStudent(findStudentID) && (!findStudentID.equals(""))) {
            showMessageDialog.showMessageDialog(null, "Student " + findStudentIdText.getText()
                    .toString() + " cannot be found!");
            System.out.println(findStudentID);
        }
    }

    /* MODIFIES: this
     * EFFECTS: create action listener for each button to add, find, and remove student.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            addStudent();
        } else if (e.getSource() == removeStudentButton) {
            removeStudent();
        } else if (e.getSource() == findStudentButton) {
            findStudent();
        }
    }
}

