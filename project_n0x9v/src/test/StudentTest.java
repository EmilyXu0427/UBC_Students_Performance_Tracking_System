import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Student;

public class StudentTest {
    Student student1;
    Student student2;
    Student student3;
    Student student4;
    Student student5;

    @BeforeEach
    public void initRun() {
        student1 = new Student (30046426, 20.0, 30.2, 30.0);
        student2 = new Student (30046427, 10.0, 20.4, 20.0);
        student3 = new Student (30046428, 24.5, 34.5, 39.0);
        student4 = new Student (30046429, 22.6, 20.5, 30.0);
        student5 = new Student (30046429, 15.0, 23.0, 25.0);
    }

    @Test
    void constructorTest(){
        assertEquals(30046426, student1.getStudentID());
        assertEquals(20.0, student1.getProjectGrade());
        assertEquals(30.2, student1.getMidtermGrade());
        assertEquals(30.0, student1.getFinalExamGrade());
    }

    @Test
    void getTotalGradeTest() {
        assertEquals(student1.getProjectGrade() + student1.getMidtermGrade() + student1.getFinalExamGrade(),
                student1.getTotalGrade());
        assertEquals(student2.getProjectGrade() + student2.getMidtermGrade() + student2.getFinalExamGrade(),
                student2.getTotalGrade());
    }

    @Test
    void getFinalPerformanceTest() {
        assertEquals("A", student3.getFinalPerformance());
        assertEquals("B", student1.getFinalPerformance());
        assertEquals("C", student4.getFinalPerformance());
        assertEquals("D",student5.getFinalPerformance());
        assertEquals("F", student2.getFinalPerformance());
    }

    @Test
    void testToJson() {
        // stub
    }
}

