package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class masterView {
    static JFrame frame = new JFrame("Athlete Tracker");
    CardLayout layout = new CardLayout();
    Integer preferredWidth = 800;
    Integer preferredHeight = 400;
    Container content;

    public masterView() {
        this.content = this.frame.getContentPane();

        content.setLayout(layout);

        this.content.setPreferredSize(new Dimension(this.preferredWidth, this.preferredHeight));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    public void addCard(String name, JPanel panel) {
        this.frame.getContentPane().removeAll();
        this.content.add(name, panel);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.repaint();
    }

    public void showCard(String name) {
        this.layout.show(content, name);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.repaint();
    }
}
