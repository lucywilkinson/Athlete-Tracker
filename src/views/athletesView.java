package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class athletesView extends basicView {
    protected JPanel mainWindow;

    private JPanel headerPanel;
    private JPanel actionPanel;
    private JPanel filterPanel;
    private JCheckBox activeCheckbox;
    private JCheckBox inactiveCheckbox;
    private JFormattedTextField afterDateTextField;
    private JFormattedTextField beforeDateTextField;
    private JTable athletesTable;
    private JButton newAthleteButton;

    private String[] columnNames = {
            "ID",
            "Name",
            "Mailing Address",
            "Date Joined",
            "Total Value Sent",
            "Status"
    };

    private Object[][] data = {
            {
                001,
                "Alex Goodwin",
                "2244 Goss Circle E #4 Boulder, CO 80302",
                "1/1/2016",
                "Active"
            }, {
                002,
                "Jack Goodwin",
                "2242 Goss Circle E #4 Boulder, CO 80302",
                "1/1/2015",
                "Inactive"
            },

    };

    public athletesView(HashMap<String, ActionListener> actionListeners) {
        super();

        // not currently working?
        athletesTable = new JTable(data, columnNames);

        this.setTitle("Athlete Tracker - Athletes");
        this.pack();
    }
}
