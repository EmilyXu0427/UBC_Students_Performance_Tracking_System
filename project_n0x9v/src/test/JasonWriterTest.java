import model.Students;
import model.Student;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JasonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Students students = new Students("My Term");
            JsonWriter writer = new JsonWriter("./data/my\0IllegalFileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudents() {
        try {
            Students testStudents = new Students("My Term");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(testStudents);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            testStudents = reader.read();
            assertEquals("My Term", testStudents.getTerm());
            assertEquals(0, testStudents.getStudents().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudents() {
        try {
            Students testStudents = new Students("My Term");
            Student student1= new Student(30047420, 20, 35, 40);
            Student student2= new Student(30047418, 10, 25, 25);


            testStudents.addStudent(student1);
            testStudents.addStudent(student2);

            JsonWriter writer = new JsonWriter("./data/testWriterStudents.json");
            writer.open();
            writer.write(testStudents);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStudents.json");
            Students students = reader.read();
            assertEquals("My Term", students.getTerm());
            List<Student> studentList = students.getStudents();
            assertEquals(2, studentList.size());
            checkStudent(studentList.get(0),30047420, 20, 35, 40);
            checkStudent(studentList.get(1),30047418, 10, 25, 25);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
