package model;


/* Student class to store student information including studentID, project grade, midterm grade, final exam grade
 * total grade and total performance.
 */
public class Student {
    private int studentID;
    private double projectGrade;
    private double midtermGrade;
    private double finalExamGrade;

    /* REQUIRES: student number needs to be 8 digit int number;
                 project grade is in the range of 0.0 - 25.0;
                 midterm grade is in the range of 0.0 - 35.0;
                 final exam grade is in the range of 0.0 - 40.0;
     * EFFECTS: create a Student object to record the student information and grade. It includes student number,
     *          project grade, midterm grade and final grade
     */
    public Student(int studentNumber, double projectGrade, double midtermGrade, double finalExamGrade) {
        this.studentID = studentNumber;
        this.projectGrade = projectGrade;
        this.midtermGrade = midtermGrade;
        this.finalExamGrade = finalExamGrade;
    }

    //EFFECTS: get student ID
    public int getStudentID() {
        return this.studentID;
    }

    //EFFECTS: get project grade of student
    public double getProjectGrade() {
        return this.projectGrade;
    }

    //Effects: get midterm grade of student
    public double getMidtermGrade() {
        return this.midtermGrade;
    }

    //EFFECTS: get final exam grade of student
    public double getFinalExamGrade() {
        return this.finalExamGrade;
    }

    /* EFFECTS: get the total grade of student, calculated as the sum of project grade, midterm grade and final
     *          exam grade
     */
    public double getTotalGrade() {
        return this.projectGrade + this.midtermGrade + this.finalExamGrade;
    }

    // EFFECTS: get the final performance based on the student's project grade, midterm grade and final exam grade.
    public String getFinalPerformance() {
        double totalGrade = this.projectGrade + this.midtermGrade + this.finalExamGrade;
        if (totalGrade >= 90) {
            return "A";
        } else if (totalGrade >= 80) {
            return "B";
        } else if (totalGrade >= 70) {
            return "C";
        } else if (totalGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
