package ui;

import model.Role;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private User loggedInUser;

    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color PRIMARY =
            new Color(41, 98, 255);

    private final Color PRIMARY_DARK =
            new Color(30, 70, 190);

    private final Color TEXT =
            new Color(35, 40, 50);

    private final Color SECONDARY_TEXT =
            new Color(100, 110, 125);

    public MainFrame(User user) {

        loggedInUser = user;

        setTitle(
                "Patent & IP Management System - "
                        + user.getRole()
        );

        setSize(950, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                BACKGROUND
        );

        createDashboard();
    }

    private void createDashboard() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(0, 20)
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        /*
         * HEADER
         */

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        JLabel titleLabel =
                new JLabel(
                        "Patent & Intellectual Property Management System"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                TEXT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Management Dashboard"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setForeground(
                SECONDARY_TEXT
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        titlePanel.add(titleLabel);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitleLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        /*
         * USER INFORMATION
         */

        JPanel userPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        userPanel.setBackground(
                CARD_COLOR
        );

        userPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)
                        ),
                        new EmptyBorder(
                                8,
                                15,
                                8,
                                15
                        )
                )
        );

        JLabel usernameLabel =
                new JLabel(
                        "User: "
                                + loggedInUser.getUsername()
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        usernameLabel.setForeground(
                TEXT
        );

        JLabel roleLabel =
                new JLabel(
                        "Role: "
                                + loggedInUser.getRole()
                );

        roleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        roleLabel.setForeground(
                SECONDARY_TEXT
        );

        userPanel.add(usernameLabel);
        userPanel.add(roleLabel);

        headerPanel.add(
                userPanel,
                BorderLayout.EAST
        );

        /*
         * WELCOME SECTION
         */

        JPanel welcomePanel =
                new JPanel(
                        new BorderLayout()
                );

        welcomePanel.setBackground(
                CARD_COLOR
        );

        welcomePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome back, "
                                + loggedInUser.getUsername()
                                + "!"
                );

        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        welcomeLabel.setForeground(
                TEXT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "Select a module below to manage intellectual property records and applications."
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
        );

        JPanel welcomeText =
                new JPanel();

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );

        welcomeText.setOpaque(false);

        welcomeText.add(welcomeLabel);

        welcomeText.add(
                Box.createVerticalStrut(5)
        );

        welcomeText.add(descriptionLabel);

        welcomePanel.add(
                welcomeText,
                BorderLayout.WEST
        );

        /*
         * MODULE BUTTONS
         */

        JPanel modulePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                loggedInUser.getRole()
                                        == Role.ADMIN
                                        ? 2
                                        : 2,
                                20,
                                20
                        )
                );

        modulePanel.setOpaque(false);

        JButton ipButton =
                createModuleButton(
                        "IP Records",
                        "Manage patents, trademarks and copyrights"
                );

        JButton applicationButton =
                createModuleButton(
                        "Applications",
                        "Track application stages and reviews"
                );

        JButton logoutButton =
                createModuleButton(
                        "Logout",
                        "Sign out of the system"
                );

        modulePanel.add(ipButton);

        modulePanel.add(applicationButton);

        if (loggedInUser.getRole()
                == Role.ADMIN) {

            JButton userButton =
                    createModuleButton(
                            "User Management",
                            "Manage system users and roles"
                    );

            modulePanel.add(userButton);

            userButton.addActionListener(
                    e -> {

                        new UserManagementFrame(
                                loggedInUser
                        ).setVisible(true);

                    }
            );
        } else {

            JPanel emptyPanel =
                    new JPanel();

            emptyPanel.setOpaque(false);

            modulePanel.add(
                    emptyPanel
            );
        }

        modulePanel.add(logoutButton);

        ipButton.addActionListener(
                e -> {

                    new IPRecordFrame(
                            loggedInUser
                    ).setVisible(true);

                }
        );

        applicationButton.addActionListener(
                e -> {

                    new ApplicationFrame(
                            loggedInUser
                    ).setVisible(true);

                }
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        /*
         * FOOTER
         */

        JLabel footerLabel =
                new JLabel(
                        "Patent & IP Management System",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        footerLabel.setForeground(
                SECONDARY_TEXT
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(0, 20)
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                welcomePanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                modulePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private JButton createModuleButton(
            String title,
            String description) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout()
        );

        button.setBackground(
                CARD_COLOR
        );

        button.setForeground(
                TEXT
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        titleLabel.setForeground(
                PRIMARY
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='width:260px'>"
                                + description
                                + "</div></html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.setOpaque(false);

        textPanel.add(titleLabel);

        textPanel.add(
                Box.createVerticalStrut(8)
        );

        textPanel.add(descriptionLabel);

        button.add(
                textPanel,
                BorderLayout.CENTER
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                new Color(235, 241, 255)
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                PRIMARY
                                        ),
                                        new EmptyBorder(
                                                20,
                                                20,
                                                20,
                                                20
                                        )
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                CARD_COLOR
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(
                                                        225,
                                                        228,
                                                        235
                                                )
                                        ),
                                        new EmptyBorder(
                                                20,
                                                20,
                                                20,
                                                20
                                        )
                                )
                        );
                    }
                }
        );

        return button;
    }

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame();

            loginFrame.setVisible(true);
        }
    }
}