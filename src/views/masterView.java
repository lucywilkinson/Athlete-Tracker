package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class masterView {
    JFrame frame = new JFrame("Athlete Tracker");
    CardLayout layout = new CardLayout();
    Integer preferredWidth = 800;
    Integer preferredHeight = 800;
    Container content;

    public masterView() {
        this.content = this.frame.getContentPane();

        content.setLayout(layout);

        this.content.setPreferredSize(new Dimension(this.preferredWidth, this.preferredHeight));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void addCard(String name, JPanel panel) {
        this.content.add(name, panel);
    }

    public void showCard(String name) {
        this.layout.show(content, name);
    }
}
