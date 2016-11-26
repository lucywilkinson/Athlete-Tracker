package views;

import common.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/18/16.
 */
public class myProfileView extends basicView {
    private JPanel mainWindow;

    // nav menu
    private JPanel navMenu = new navMenuView();

    // side menu
    private JPanel sideMenu;
    private JButton viewMyShipments;
    private JButton editMyProfileButton;
    private JButton saveButton;

    // My Profile form
    private JPanel viewWindow;
    private JPanel myProfileRow;
    private JCheckBox enabledCheckBox;
    private JPanel id_row;
    private JTextField idText;
    private JPanel name_row;
    private JTextField nameText;
    private JPanel email_row;
    private JTextField emailText;
    private JPanel user_type_row;
    private JComboBox accountTypeComboBox;
    private JPanel enabled_row;

    public myProfileView(HashMap<String, ActionListener> actionListeners, User user) {
        this.setContentPane(mainWindow);

        // insert data (should these be in the controller?)
        this.setNameText(user.getFirstName() + " " + user.getLastName());
        this.setIdText(Integer.toString(user.getUserId()));
        this.setUserType(user.getUserType());

        // attach action listeners
        editMyProfileButton.addActionListener(actionListeners.get("editProfileAction"));

        this.setTitle("Athlete Tracker - My Profile");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setNameText(String name) {
        nameText.setText(name);
    }

    private void setIdText(String id) {
        idText.setText(id);
    }

    private void setUserType(String userType) {
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

    }
}
