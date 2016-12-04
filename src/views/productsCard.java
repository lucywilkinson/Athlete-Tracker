package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.Product;
import models.ProductModel;

/**
 * Created by alex on 11/29/16.
 */
public class productsCard extends card {


    JLabel titleLabel = new JLabel("Products");
    JPanel panel1 = new JPanel();

    //TEST FOR JTABLE
    //NEED TO POPULATE WITH DATABASE INSTEAD
    String[] columnNames = {"Id", "Name", "Value", "Quantity"};

    Object[][] data = {{new Integer(1), "Sticker", new Float(.50), new Integer(10000) },
            {new Integer(2), "Poster", new Float(5.00), new Integer(100)},
            {new Integer(3), "Jersey", new Float(50.00), new Integer(750)}};
    
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);

    public productsCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        this.add(this.titleLabel);
        this.add(scrollPane);


    }

    public void populateProductsTable(ArrayList<Product> products){

    }









}
