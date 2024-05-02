package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Operation window interface displays empty table and display buttons for user to add, find, remove students
public class OperationWindowWithNoData extends OperationWindowWithData {

    // EFFECTS: construct an operation window without data
    public OperationWindowWithNoData() {
        super();
    }

    // EFFECTS: set up display panel with empty data
    @Override
    public void setUpDisplayPanel() {
        importData();
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        displayPanel.add(scrollPane);
    }

    // EFFECTS: initiate a table with empty rows and set up the column name
    @Override
    public void importData() {
        String[] columNames = {"Student ID", "Project Grade", "Midterm Grade", "Final Exam Grade", "Total Grade",
                "Final Performance"};
        int numRows = 0;

        model = new DefaultTableModel(numRows, columNames.length);
        model.setColumnIdentifiers(columNames);

        studentsTable = new JTable(model);
    }
}
