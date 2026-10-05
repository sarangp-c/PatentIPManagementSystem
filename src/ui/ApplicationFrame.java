package ui;

import dao.ApplicationDAO;
import model.Application;

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

    public ApplicationFrame() {

        dao = new ApplicationDAO();

        setTitle("Applications");
        setSize(850, 600);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));

        idField = new JTextField();
        ipIdField = new JTextField();
        stageField = new JTextField();
        dateField = new JTextField();
        reviewerField = new JTextField();
        remarksField = new JTextField();

        formPanel.add(new JLabel("Application ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("IP Record ID:"));
        formPanel.add(ipIdField);

        formPanel.add(new JLabel("Current Stage:"));
        formPanel.add(stageField);

        formPanel.add(new JLabel("Last Updated:"));
        formPanel.add(dateField);

        formPanel.add(new JLabel("Reviewer ID:"));
        formPanel.add(reviewerField);

        formPanel.add(new JLabel("Remarks:"));
        formPanel.add(remarksField);

        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View All");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        addButton.addActionListener(e -> addApplication());

        viewButton.addActionListener(e -> {
            outputArea.setText(dao.getAll());
        });

        updateButton.addActionListener(e -> updateApplication());

        deleteButton.addActionListener(e -> deleteApplication());

        clearButton.addActionListener(e -> clearFields());
    }

    private Application createApplication() {

        int id = idField.getText().isEmpty()
                ? 0
                : Integer.parseInt(idField.getText());

        int ipId = Integer.parseInt(ipIdField.getText());

        int reviewerId = reviewerField.getText().isEmpty()
                ? 0
                : Integer.parseInt(reviewerField.getText());

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

            Application app = createApplication();

            if (dao.add(app)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application added successfully!"
                );

                clearFields();
                outputArea.setText(dao.getAll());

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
                    "Error: " + e.getMessage()
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

            Application app = createApplication();

            if (dao.update(app)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application updated successfully!"
                );

                outputArea.setText(dao.getAll());

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
                    "Error: " + e.getMessage()
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

            int id = Integer.parseInt(idField.getText());

            if (dao.delete(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application deleted successfully!"
                );

                clearFields();
                outputArea.setText(dao.getAll());

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