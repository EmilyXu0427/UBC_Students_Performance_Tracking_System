import org.junit.jupiter.api.BeforeEach;
import model.Students;
import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {
    Students studentsA;
    Students studentsB;
    Student student1;
    Student student2;

    @BeforeEach
    public void initRun() {
        studentsA = new Students("2020W1");
        studentsB = new Students("2020W2", student1);
        student1 = new Student (30046426, 20.0, 30.2, 30.0);
        student2 = new Student (30046427, 10.0, 20.4, 20.0);
    }

    @Test
    void constructorTest() {
        assertEquals("2020W1", studentsA.getTerm());
        assertEquals("2020W2", studentsB.getTerm());
        assertEquals(0, studentsA.getStudents().size());
        assertEquals(1, studentsB.getStudents().size());
    }

    @Test
    void addStudentTest(){
        assertTrue(studentsA.addStudent(student1));
        assertEquals(student1, studentsA.getStudents().get(0));
        assertFalse(studentsA.addStudent(student1));
        assertTrue(studentsA.addStudent(student2));
        assertEquals(student2, studentsA.getStudents().get(1));
    }

    @Test
    void removeStudentTest(){
        assertFalse(studentsA.removeStudent(30046426));
        studentsA.addStudent(student1);
        studentsA.addStudent(student2);
        assertEquals(2, studentsA.getStudents().size());
        assertTrue(studentsA.removeStudent(30046426));
        assertEquals(1, studentsA.getStudents().size());
        assertTrue(studentsA.removeStudent(30046427));
        assertEquals(0, studentsA.getStudents().size());
        assertFalse(studentsA.removeStudent(30046427));

        studentsA.addStudent(student1);
        studentsA.addStudent(student2);
        assertTrue(studentsA.removeStudent(30046427));
    }

    @Test
    void getTotalStudentsNumberTest() {
        assertEquals(0, studentsA.getTotalStudentsNumber());
        studentsA.addStudent(student1);
        studentsA.addStudent(student2);
        assertEquals(2, studentsA.getTotalStudentsNumber());
    }

    @Test
    void getStudentsAverageTest(){
        studentsA.addStudent(student1);
        assertEquals(student1.getTotalGrade(), studentsA.getStudentsAverage());
        studentsA.addStudent(student2);
        assertEquals((student1.getTotalGrade() + student2.getTotalGrade())/2, studentsA.getStudentsAverage());
    }

    @Test
    void checkStudentTest() {
        assertFalse(studentsA.checkStudent(30046426));
        studentsA.addStudent(student1);
        studentsA.addStudent(student2);
        assertTrue(studentsA.checkStudent(30046426));
        assertTrue(studentsA.checkStudent(30046427));
        assertFalse(studentsA.checkStudent(30046490));
    }
}
