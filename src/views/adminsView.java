package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class adminsView extends basicView {
    protected JPanel mainWindow;

    private JPanel headerPanel;
    private JButton newAdminButton;
    private JPanel actionPanel;
    private JPanel filterPanel;
    private JCheckBox activeCheckbox;
    private JCheckBox inactiveCheckbox;
    private JFormattedTextField afterDateTextField;
    private JFormattedTextField beforeDateTextField;
    private JTable adminsTable;

    public adminsView(HashMap<String, ActionListener> actionListeners) {
        super();

        this.attachNavMenuActionListeners(actionListeners);

        this.setTitle("Athlete Tracker - Admins");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
}
