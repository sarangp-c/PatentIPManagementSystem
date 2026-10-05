package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Patent & IP Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "Patent & Intellectual Property Management System",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton ipButton = new JButton("IP Records");
        JButton applicationButton = new JButton("Applications");

        ipButton.addActionListener(e -> {
            new IPRecordFrame().setVisible(true);
        });

        applicationButton.addActionListener(e -> {
            new ApplicationFrame().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ipButton);
        buttonPanel.add(applicationButton);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}