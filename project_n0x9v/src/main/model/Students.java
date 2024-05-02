package model;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


// Students Class include the term information and list of students attending CPSC 210 at that term
public class Students {
    private String term;
    private List<Student> students;

    // EFFECTS: create Students object including the term name and an empty Student list
    public Students(String term) {
        this.term = term;
        students = new ArrayList<>();
    }

    // EFFECTS: overload constructor to create Students object with term name and given first Student
    public Students(String term, Student student) {
        this.term = term;
        students = new ArrayList<>();
        students.add(student);
    }

    // EFFECTS: get term name
    public String getTerm() {
        return this.term;
    }

    // EFFECTS: get students
    public List<Student> getStudents() {
        return students;
    }

    // EFFECTS: get the total number of students
    public int getTotalStudentsNumber() {
        return students.size();
    }

    /* MODIFIES: this
     * EFFECTS: add a student to the list; if student already exists, return false;
     *          otherwise add student to the list and return true.
     */
    public boolean addStudent(Student student) {
        for (Student stu : students) {
            if (stu.getStudentID() == student.getStudentID()) {
                return false;
            }
        }
        students.add(student);
        EventLog.getInstance().logEvent(new Event("Student " + student.getStudentID() + " is added."));
        return true;
    }

    /* REQUIRES: stuID needs to be 8 digit int number
     * MODIFIES: this
     * EFFECTS: remove a student from the list; if student in the list, remove student and return true;
     *          if student not in the list, return false;
     */
    public boolean removeStudent(int stuID) {
        for (Student student: students) {
            if (student.getStudentID() == stuID) {
                students.remove(student);
                EventLog.getInstance()
                        .logEvent(new Event("Student " + student.getStudentID() + " is removed."));
                return true;
            }
        }
        return false;
    }

    /* REQUIRES: students cannot be empty list
     * EFFECTS: calculate the average total grade of students and return it.
     */
    public double getStudentsAverage() {
        double sum = 0;
        for (Student student : students) {
            sum += student.getTotalGrade();
        }
        return sum / students.size();
    }

    /* REQUIRES: student ID needs to be 8 digit int number
     * EFFECTS: check if a student already exist in the list; if the student already in the list, return true;
     * otherwise, return false.
     */
    public boolean checkStudent(int studentID) {
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                return true;
            }
        }
        return false;
    }

    // Citation: Code influenced by the JsonSerizalizationDemo link_to_demo
    // EFFECTS: Create a JSON Object of Students
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("term", this.term);
        json.put("students", this.students);
        return json;
    }
}
