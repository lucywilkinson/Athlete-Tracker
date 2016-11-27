package views;

import common.User;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/18/16.
 */
public class myProfileView extends basicView {
    protected JPanel mainWindow;

    // nav menu
    private JToolBar navToolbar;
    private JButton adminsButton;
    private JButton athletesButton;
    private JButton warehouseWorkersButton;
    private JButton shipmentsButton;
    private JButton productsButton;
    private JButton myProfileButton;
    private JPanel navPanel;

    // side menu
    protected JPanel sideMenu;
    protected JButton viewMyShipments;
    protected JButton editMyProfileButton;
    protected JButton saveButton;

    // My Profile form
    protected JPanel viewWindow;
    protected JPanel myProfileRow;
    protected JCheckBox enabledCheckBox;
    protected JPanel id_row;
    protected JTextField idText;
    protected JPanel name_row;
    protected JTextField nameText;
    protected JPanel email_row;
    protected JTextField emailText;
    protected JPanel user_type_row;
    protected JComboBox accountTypeComboBox;
    protected JPanel enabled_row;


    public myProfileView() {
        super();
    }

    public myProfileView(HashMap<String, ActionListener> actionListeners, User user) {
        this.setContentPane(mainWindow);

        // insert data (should these be in the controller?)
        this.setNameText(user.getFirstName() + " " + user.getLastName());
        this.setIdText(Integer.toString(user.getUserId()));
        this.setUserType(user.getUserType());

        // attach action listeners
        editMyProfileButton.addActionListener(actionListeners.get("editProfileAction"));
        saveButton.addActionListener(actionListeners.get("saveProfileAction"));

        this.attachNavMenuActionListeners(actionListeners);

        this.setTitle("Athlete Tracker - My Profile");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public void close() {
        this.setVisible(false);
    }

    public void attachNavMenuActionListeners(HashMap<String, ActionListener> actionListeners) {
        athletesButton.addActionListener(actionListeners.get("clickAthletesButton"));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setNameText(String name) {
        nameText.setText(name);
    }

    protected void setIdText(String id) {
        idText.setText(id);
    }

    protected void setUserType(String userType) {
        accountTypeComboBox.setSelectedItem(userType);
    }

    public void startEditProfile() {
        editMyProfileButton.setEnabled(false);
        saveButton.setEnabled(true);

        // make fields editable
        nameText.setEnabled(true);
        nameText.setEditable(true);
        emailText.setEnabled(true);
        emailText.setEditable(true);
        accountTypeComboBox.setEnabled(true);
        accountTypeComboBox.setEditable(true);
        enabledCheckBox.setEnabled(true);
    }

    public void saveEditProfile() {
        editMyProfileButton.setEnabled(true);
        saveButton.setEnabled(false);

        // update model

        // make fields uneditable
        nameText.setEnabled(false);
        nameText.setEditable(false);
        emailText.setEnabled(false);
        emailText.setEditable(false);
        accountTypeComboBox.setEnabled(false);
        accountTypeComboBox.setEditable(false);
        enabledCheckBox.setEnabled(false);
    }
}
