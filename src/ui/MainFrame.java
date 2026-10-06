package ui;

import model.Role;
import model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private User loggedInUser;

    public MainFrame(User user) {

        loggedInUser = user;

        setTitle(
                "Patent & IP Management System - "
                        + user.getRole()
        );

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "Patent & Intellectual Property Management System",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JLabel userLabel = new JLabel(
                "Logged in as: "
                        + user.getUsername()
                        + " | Role: "
                        + user.getRole(),
                SwingConstants.CENTER
        );

        userLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        JButton ipButton =
                new JButton("IP Records");

        JButton applicationButton =
                new JButton("Applications");

        JButton userButton =
                new JButton("User Management");

        JButton logoutButton =
                new JButton("Logout");

        ipButton.addActionListener(e -> {

            new IPRecordFrame(
                    loggedInUser
            ).setVisible(true);

        });

        applicationButton.addActionListener(e -> {

            new ApplicationFrame(
                    loggedInUser
            ).setVisible(true);

        });

        userButton.addActionListener(e -> {

            new UserManagementFrame(
                    loggedInUser
            ).setVisible(true);

        });

        logoutButton.addActionListener(
                e -> logout()
        );

        JPanel topPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        topPanel.add(title);
        topPanel.add(userLabel);

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(ipButton);
        buttonPanel.add(applicationButton);

        if (user.getRole() == Role.ADMIN) {

            buttonPanel.add(userButton);
        }

        buttonPanel.add(logoutButton);

        add(
                topPanel,
                BorderLayout.NORTH
        );

        add(
                buttonPanel,
                BorderLayout.CENTER
        );
    }

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame();

            loginFrame.setVisible(true);
        }
    }
}