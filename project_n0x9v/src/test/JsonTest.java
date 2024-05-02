import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Student;


public class JsonTest {
    protected void checkStudent(Student student, int studentID, double projectGrade, double midtermGrade, double finalExamGrade) {
        assertEquals(studentID, student.getStudentID());
        assertEquals(projectGrade, student.getProjectGrade());
        assertEquals(midtermGrade, student.getMidtermGrade());
        assertEquals(finalExamGrade, student.getFinalExamGrade());
    }
}
