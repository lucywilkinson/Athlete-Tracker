package views;

import common.User;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/18/16.
 */
public class myProfileView extends basicView {
    public JPanel mainWindow;

    // side menu
    public JPanel sideMenu;
    public JButton viewMyShipments;
    public JButton editMyProfileButton;
    public JButton saveButton;

    // My Profile form
    public JPanel viewWindow;
    public JPanel myProfileRow;
    public JCheckBox enabledCheckBox;
    public JPanel id_row;
    public JTextField idText;
    public JPanel name_row;
    public JTextField nameText;
    public JPanel email_row;
    public JTextField emailText;
    public JPanel user_type_row;
    public JComboBox accountTypeComboBox;
    public JPanel enabled_row;

    public myProfileView(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners);

        // attach action listeners
        editMyProfileButton.addActionListener(actionListeners.get("editProfileAction"));
        saveButton.addActionListener(actionListeners.get("saveProfileAction"));

        this.setTitle("Athlete Tracker - My Profile");

        // TODO: if left out, only displays menu. If on, doesn't display menu. Should probably use CardLayout.
        this.setContentPane(mainWindow);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setNameText(String name) {
        nameText.setText(name);
    }

    public void setIdText(String id) {
        idText.setText(id);
    }

    public void setUserType(String userType) {
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
