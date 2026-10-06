package ui;

import dao.UserDAO;
import model.Role;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserManagementFrame extends JFrame {

    private JTextField idField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Role> roleBox;

    private JTextArea outputArea;

    private UserDAO dao;
    private User loggedInUser;

    public UserManagementFrame(User user) {

        loggedInUser = user;
        dao = new UserDAO();

        setTitle("User Management - ADMIN");
        setSize(800, 550);
        setLocationRelativeTo(null);

        JPanel formPanel =
                new JPanel(new GridLayout(4, 2, 8, 8));

        idField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleBox = new JComboBox<>(Role.values());

        formPanel.add(new JLabel("User ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Role:"));
        formPanel.add(roleBox);

        JButton addButton =
                new JButton("Add");

        JButton viewButton =
                new JButton("View All");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(
                formPanel,
                BorderLayout.NORTH
        );

        add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(
                new JScrollPane(outputArea),
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addUser()
        );

        viewButton.addActionListener(
                e -> viewUsers()
        );

        updateButton.addActionListener(
                e -> updateUser()
        );

        deleteButton.addActionListener(
                e -> deleteUser()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        viewUsers();
    }

    private void addUser() {

        try {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            Role role =
                    (Role) roleBox.getSelectedItem();

            if (username.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username and password are required."
                );

                return;
            }

            if (dao.userExists(username)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists."
                );

                return;
            }

            User user = new User(
                    0,
                    username,
                    password,
                    role
            );

            if (dao.createUser(user)) {

                JOptionPane.showMessageDialog(
                        this,
                        "User added successfully!"
                );

                clearFields();
                viewUsers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add user.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void viewUsers() {

        outputArea.setText(
                dao.getAllUsers()
        );
    }

    private void updateUser() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the User ID to update."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                    );

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            Role role =
                    (Role) roleBox.getSelectedItem();

            if (username.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username and password are required."
                );

                return;
            }

            User user = new User(
                    id,
                    username,
                    password,
                    role
            );

            if (dao.updateUser(user)) {

                JOptionPane.showMessageDialog(
                        this,
                        "User updated successfully!"
                );

                clearFields();
                viewUsers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update user.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID must be a number."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void deleteUser() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the User ID to delete."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                    );

            if (id == loggedInUser.getId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You cannot delete the currently logged-in admin."
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this user?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            if (dao.deleteUser(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "User deleted successfully!"
                );

                clearFields();
                viewUsers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "User not found or could not be deleted.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID must be a number."
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        roleBox.setSelectedItem(Role.RESEARCHER);
    }
}