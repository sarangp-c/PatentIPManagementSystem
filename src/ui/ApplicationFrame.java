package ui;

import dao.ApplicationDAO;
import model.Application;
import model.Role;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    private JTextField idField;
    private JTextField ipIdField;
    private JTextField stageField;
    private JTextField dateField;
    private JTextField reviewerField;
    private JTextField remarksField;

    private JTextArea outputArea;

    private ApplicationDAO dao;
    private User loggedInUser;

    private JButton addButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private String addPermissionMessage = "";
    private String updatePermissionMessage = "";
    private String deletePermissionMessage = "";

    public ApplicationFrame(User user) {

        loggedInUser = user;
        dao = new ApplicationDAO();

        setTitle(
                "Applications - "
                        + user.getRole()
        );

        setSize(850, 600);
        setLocationRelativeTo(null);

        JPanel formPanel =
                new JPanel(
                        new GridLayout(6, 2, 8, 8)
                );

        idField = new JTextField();
        ipIdField = new JTextField();
        stageField = new JTextField();
        dateField = new JTextField();
        reviewerField = new JTextField();
        remarksField = new JTextField();

        formPanel.add(
                new JLabel("Application ID:")
        );
        formPanel.add(idField);

        formPanel.add(
                new JLabel("IP Record ID:")
        );
        formPanel.add(ipIdField);

        formPanel.add(
                new JLabel("Current Stage:")
        );
        formPanel.add(stageField);

        formPanel.add(
                new JLabel("Last Updated:")
        );
        formPanel.add(dateField);

        formPanel.add(
                new JLabel("Reviewer ID:")
        );
        formPanel.add(reviewerField);

        formPanel.add(
                new JLabel("Remarks:")
        );
        formPanel.add(remarksField);

        addButton =
                new JButton("Add");

        viewButton =
                new JButton("View All");

        updateButton =
                new JButton("Update");

        deleteButton =
                new JButton("Delete");

        clearButton =
                new JButton("Clear");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        outputArea =
                new JTextArea();

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
                e -> {

                    if (!addPermissionMessage.isEmpty()) {

                        showPermissionMessage(
                                addPermissionMessage
                        );

                    } else {

                        addApplication();
                    }
                }
        );

        viewButton.addActionListener(e ->
                outputArea.setText(
                        dao.getAll()
                )
        );

        updateButton.addActionListener(
                e -> {

                    if (!updatePermissionMessage.isEmpty()) {

                        showPermissionMessage(
                                updatePermissionMessage
                        );

                    } else {

                        updateApplication();
                    }
                }
        );

        deleteButton.addActionListener(
                e -> {

                    if (!deletePermissionMessage.isEmpty()) {

                        showPermissionMessage(
                                deletePermissionMessage
                        );

                    } else {

                        deleteApplication();
                    }
                }
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        applyPermissions();
    }

    private void applyPermissions() {

        Role role =
                loggedInUser.getRole();

        if (role == Role.ADMIN) {

            addButton.setToolTipText(
                    "Add applications"
            );

            viewButton.setToolTipText(
                    "View all applications"
            );

            updateButton.setToolTipText(
                    "Update applications"
            );

            deleteButton.setToolTipText(
                    "Delete applications"
            );

            clearButton.setToolTipText(
                    "Clear the form"
            );

        } else if (role == Role.REVIEWER) {

            addPermissionMessage =
                    "Only Admin can add applications.";

            deletePermissionMessage =
                    "Only Admin can delete applications.";

            makeRestrictedButton(
                    addButton,
                    addPermissionMessage
            );

            makeRestrictedButton(
                    deleteButton,
                    deletePermissionMessage
            );

            viewButton.setToolTipText(
                    "View all applications"
            );

            updateButton.setToolTipText(
                    "Update application status and details"
            );

            clearButton.setToolTipText(
                    "Clear the form"
            );

        } else if (role == Role.RESEARCHER) {

            addPermissionMessage =
                    "Only Admin can add applications.";

            updatePermissionMessage =
                    "Only Admin or Reviewer can update applications.";

            deletePermissionMessage =
                    "Only Admin can delete applications.";

            makeRestrictedButton(
                    addButton,
                    addPermissionMessage
            );

            makeRestrictedButton(
                    updateButton,
                    updatePermissionMessage
            );

            makeRestrictedButton(
                    deleteButton,
                    deletePermissionMessage
            );

            viewButton.setToolTipText(
                    "View all applications"
            );

            clearButton.setToolTipText(
                    "Clear the form"
            );
        }
    }

    private void makeRestrictedButton(
            JButton button,
            String message) {

        button.setToolTipText(message);

        button.setForeground(
                UIManager.getColor(
                        "Button.disabledText"
                )
        );

        button.setBackground(
                UIManager.getColor(
                        "Button.background"
                )
        );
    }

    private void showPermissionMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Access Restricted",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private Application createApplication() {

        int id =
                idField.getText().isEmpty()
                        ? 0
                        : Integer.parseInt(
                                idField.getText()
                        );

        int ipId =
                Integer.parseInt(
                        ipIdField.getText()
                );

        int reviewerId =
                reviewerField.getText().isEmpty()
                        ? 0
                        : Integer.parseInt(
                                reviewerField.getText()
                        );

        return new Application(
                id,
                ipId,
                stageField.getText(),
                dateField.getText(),
                reviewerId,
                remarksField.getText()
        );
    }

    private void addApplication() {

        try {

            if (ipIdField.getText().isEmpty()
                    || stageField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP Record ID and Current Stage are required."
                );

                return;
            }

            Application app =
                    createApplication();

            if (dao.add(app)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application added successfully!"
                );

                clearFields();

                outputArea.setText(
                        dao.getAll()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add application.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "IP Record ID and Reviewer ID must be numbers."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    private void updateApplication() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the application ID to update."
                );

                return;
            }

            Application app =
                    createApplication();

            if (dao.update(app)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application updated successfully!"
                );

                outputArea.setText(
                        dao.getAll()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Application not found or update failed.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID fields must contain numbers."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    private void deleteApplication() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the application ID to delete."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                    );

            if (dao.delete(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application deleted successfully!"
                );

                clearFields();

                outputArea.setText(
                        dao.getAll()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Application not found.\n\n"
                                + dao.getLastError()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Application ID must be a number."
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        ipIdField.setText("");
        stageField.setText("");
        dateField.setText("");
        reviewerField.setText("");
        remarksField.setText("");
    }
}