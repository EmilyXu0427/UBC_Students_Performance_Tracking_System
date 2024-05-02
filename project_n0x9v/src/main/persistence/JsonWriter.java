package persistence;

import model.Event;
import model.EventLog;
import model.Students;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// Citation: Code influenced by the JsonSerizalizationDemo link_to_demo
// Json Writer Class to write data into Json File
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: construct  a JsonWriter object
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /* MODIFIES: this
     * EFFECTS: open a file to write the data into;
     * if file cannot be found, throws FileNotFoundException
     */
    public void open() throws FileNotFoundException {
        this.writer = new PrintWriter(new File(this.destination));
    }

    /* MODIFIES: this
     * EFFECTS: write Students objects into Json File
     */
    public void write(Students students) {
        JSONObject json = students.toJson();
        this.saveToFile(json.toString(4));

        EventLog.getInstance().logEvent(new Event("New changes has been saved."));
    }

    /* MODIFIES: this
     * EFFECTS: close the writer
     */
    public void close() {
        this.writer.close();
    }

    /* MODIFIES: this
     * EFFECTS: save the jason String into the Json File.
     */
    private void saveToFile(String json) {
        this.writer.print(json);
    }
}

