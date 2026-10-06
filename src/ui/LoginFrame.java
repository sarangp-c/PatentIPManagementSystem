package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private UserDAO userDAO;

    public LoginFrame() {

        userDAO = new UserDAO();
        userDAO.createDefaultUsers();

        setTitle("Login - Patent & IP Management System");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Patent & IP Management System",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton clearButton = new JButton("Clear");

        JPanel formPanel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());

        clearButton.addActionListener(e -> clearFields());

        passwordField.addActionListener(e -> login());
    }

    private void login() {

        String username = usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        User user = userDAO.login(username, password);

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!\nRole: "
                            + user.getRole()
            );

            MainFrame mainFrame =
                    new MainFrame(user);

            mainFrame.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        usernameField.setText("");
        passwordField.setText("");
    }
}