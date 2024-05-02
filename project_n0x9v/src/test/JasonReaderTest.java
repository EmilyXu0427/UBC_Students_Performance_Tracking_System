import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;
import model.Students;
import model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JasonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Students students  = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudents() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudents.json");
        try {
            Students students = reader.read();
            assertEquals("Study Term", students.getTerm());
            assertEquals(0, students.getStudents().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudents() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudents.json");
        try {
            Students testStudents = reader.read();
            assertEquals("2020Winter1", testStudents.getTerm());
            List<Student> students = testStudents.getStudents();
            assertEquals(2, students.size());
            checkStudent(students.get(0), 30047420, 25, 35, 40);
            checkStudent(students.get(1), 30047415, 15, 25, 30);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
