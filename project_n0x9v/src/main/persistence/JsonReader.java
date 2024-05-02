package persistence;

import model.Event;
import model.EventLog;
import model.Student;
import model.Students;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

// Citation: Code influenced by the JsonSerizalizationDemo link_to_demo
// Jason reader class to read data from Json file and convert into students class
public class JsonReader {
    private String source;

    // EFFECTS: create a JsonReader object
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: read data from Json file and transfer to Students data and return it
    public Students read() throws IOException {
        String jsonData = this.readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Saved data is imported."));
        return this.parseStudents(jsonObject);
    }

    // EFFECTS: read data from a file, transfer to String and return it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8);

        stream.forEach((s) -> {
            contentBuilder.append(s);
        });

        return contentBuilder.toString();
    }

    // MODIFIES: this
    // EFFECTS: parse Json object to Students;
    private Students parseStudents(JSONObject jsonObject) {
        String term = jsonObject.getString("term");
        Students students = new Students(term);
        this.addStudents(students, jsonObject);
        return students;
    }

    // MODIFIES: this
    // EFFECTS: add Json Object to Students list;
    private void addStudents(Students students, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextStudent = (JSONObject)json;
            this.loadStudent(students, nextStudent);
        }
    }

    // EFFECTS: parse the Json Object into Student data, and add to Students list
    private void loadStudent(Students students, JSONObject jsonObject) {

        int studentID = jsonObject.getInt("studentID");
        double projectGrade = jsonObject.getDouble("projectGrade");
        double midtermGrade = jsonObject.getDouble("midtermGrade");
        double finalExamGrade = jsonObject.getDouble("finalExamGrade");

        Student student = new Student(studentID, projectGrade, midtermGrade, finalExamGrade);
        students.addStudent(student);
    }
}
