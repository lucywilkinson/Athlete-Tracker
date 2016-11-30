package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class productsView extends basicView {
    private JPanel mainWindowChild;
    private JButton newProductButton;
    private JPanel header;
    private JTable productsTable;
    private JPanel data;

    public JToolBar navMenu;
    public JButton adminsButton;
    public JButton athletesButton;
    public JButton warehouseWorkersButton;
    public JButton shipmentsButton;
    public JButton productsButton;
    public JButton myProfileButton;

    public productsView(HashMap<String, ActionListener> actionListeners) {
//        super(actionListeners);

//        this.setVisible(true);
//        this.setContentPane(mainWindow);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container content = this.getContentPane();

        JLabel label = new JLabel("test");
        content.add(label);
        content.revalidate();

        // attach action listeners
        newProductButton.addActionListener(actionListeners.get("newProductAction"));

        this.setTitle("Athlete Tracker - Products");
        this.pack();
        this.setVisible(true);
    }
}
