package ui;

import model.Student;
import model.Students;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import persistence.JsonWriter;

import static java.lang.System.out;

//Student management system application
public class StudentApp {
    private Students students;
    private Scanner print;
    private String studentID = "";
    private String projectGrade = "";
    private String midtermGrade = "";
    private String finalGrade = "";
    private JsonReader jsonReader = new JsonReader("./data/ProjectData.json");
    private JsonWriter jsonWriter = new JsonWriter("./data/ProjectData.json");


    // EFFECTS: runs the student system application
    public StudentApp() {
        runApp();
    }

    // EFFECTS: run app to display menu, process user input and call corresponding method
    private void runApp() {
        print = new Scanner(System.in);
        String command = "";

        out.println("\nDo you want to load the saved students list?");
        out.println("\tY -> Yes");
        out.println("\tN -> No");
        command = print.nextLine();

        if (command.toUpperCase().equals("Y")) {
            loadStudents();
        }
        runOperationList();
        closeApplication();
    }

    // EFFECTS: run operation list
    public void runOperationList() {
        print = new Scanner(System.in);
        String command = "";
        do {
            displayMenu();
            command = print.nextLine();

            if (command.equals("1")) {
                printTotalStudentNumber();
            } else if (command.equals("2")) {
                addNewStudent();
            } else if (command.equals("3")) {
                removeExistStudent();
            } else if (command.equals("4")) {
                printStudentAverage();
            } else if (command.equals("5")) {
                printStudentGradeDetails();
            }
        } while (!command.equals("6"));

    }

    // EFFECTS: load Students data from Json File
    public void loadStudents() {
        try {
            students = jsonReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECTS: save Students data to Json File
    public void saveStudents() {
        try {
            jsonWriter.open();
            this.jsonWriter.write(students);
            this.jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: ./data/ProjectData.json");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        out.println("\nPlease select options from the following list by entering the letter:");
        out.println("\t1 -> Get total number of students");
        out.println("\t2 -> Add new student");
        out.println("\t3 -> Remove existing student");
        out.println("\t4 -> Get student average");
        out.println("\t5 -> Print grade details for all students");
        out.println("\t6 -> quit");
    }

    // EFFECTS: close application
    public void closeApplication() {
        print = new Scanner(System.in);
        String command = "";

        System.out.println("Do you want to save the changes to students list?");
        out.println("\tY -> Yes, save the list");
        out.println("\tN -> No, discard the changes");

        command = print.nextLine();
        if (command.toUpperCase().equals("Y")) {
            saveStudents();
            out.println("Changes Saved! Thank you for using Student Management System. Have a nice day!!!");
        } else {
            out.println("No Changes Saved! Thank you for using Student Management System. Have a nice day!!!");
        }
    }

    // EFFECTS: print out the total number of students
    private void printTotalStudentNumber() {
        if (students == null) {
            out.println("The total student number is: 0");
        } else {
            System.out.println("The total student number is: " + students.getTotalStudentsNumber());
        }
    }

    /* MODIFIES: this
     * EFFECTS: add a student to student list; if this student already in the list, the student will not be added
     * and system will print out "This student already exists!". Otherwise, student will be added to the list.
     */
    private void addNewStudent() {
        Scanner newInput = new Scanner(System.in);

        System.out.println("Please enter studentID (8 digit int number): ");
        studentID = newInput.nextLine();
        System.out.println("Please enter project grade: ");
        projectGrade = newInput.nextLine();
        System.out.println("Please enter midterm grade: ");
        midtermGrade = newInput.nextLine();
        System.out.println("Please enter final exam grade: ");
        finalGrade = newInput.nextLine();

        Student s = new Student(Integer.parseInt(studentID), Double.parseDouble(projectGrade),
                Double.parseDouble(midtermGrade), Double.parseDouble(finalGrade));
        if (students == null) {
            students = new Students("2020Winter1", s);
            students.addStudent(s);
            System.out.println("Student " + s.getStudentID() + " has been added to the students list");
        } else if (students.checkStudent(Integer.parseInt(studentID))) {
            System.out.println("This student already exists!");
        } else {
            students.addStudent(s);
            System.out.println("Student " + s.getStudentID() + " has been added to the students list");
        }
    }

    /* MODIFIES: this
     * EFFECTS: remove a student from student list; if this student is in the list, student will be removed; if student
     *          does not exist in the list, system print out "This student does not exist!".
     */
    private void removeExistStudent() {
        Scanner newInput = new Scanner(System.in);
        String enter = "";
        System.out.println("To remove a student, please enter studentID (8 digit int number) : ");
        enter = newInput.nextLine();

        int studentID = Integer.parseInt(enter);

        if (students == null) {
            System.out.println("This student does not exist!");
        } else if (students.checkStudent(studentID)) {
            students.removeStudent(studentID);
            System.out.println("Student " + studentID + " has been removed");
        } else {
            System.out.println("This student does not exist!");
        }
    }

    // EFFECTS: print out the average of total grade of all students
    private void printStudentAverage() {
        if (students == null) {
            System.out.println("The average of all students is: 0");
        } else {
            System.out.println("The average of all students is: " + students.getStudentsAverage());
        }
    }

    // EFFECTS: print out the grade details of all students
    private void printStudentGradeDetails() {
        if (students == null) {
            System.out.println("Currently no students in the list!");
        } else {
            for (Student student : students.getStudents()) {
                System.out.println("StudentID:" + student.getStudentID() + "; Project Grade: "
                        + student.getProjectGrade()
                        + "; Midterm Grade: " + student.getMidtermGrade() + "; Final Exam Grade: "
                        + student.getFinalExamGrade() + "; Total Grade: " + student.getTotalGrade()
                        + "; Final Performance: " + student.getFinalPerformance());
            }
        }
    }
}

