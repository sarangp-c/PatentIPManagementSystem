package ui;

import dao.IPRecordDAO;
import model.Copyright;
import model.IntellectualProperty;
import model.Patent;
import model.Trademark;

import javax.swing.*;
import java.awt.*;

public class IPRecordFrame extends JFrame {

    private JTextField idField;
    private JComboBox<String> typeBox;
    private JTextField titleField;
    private JTextField inventorField;
    private JTextField dateField;
    private JTextField statusField;
    private JTextField subtypeField;
    private JTextArea descriptionArea;
    private JTextArea outputArea;

    private IPRecordDAO dao;

    public IPRecordFrame() {

        dao = new IPRecordDAO();

        setTitle("IP Records");
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 8, 8));

        idField = new JTextField();

        typeBox = new JComboBox<>(
                new String[]{"Patent", "Trademark", "Copyright"}
        );

        titleField = new JTextField();
        inventorField = new JTextField();
        dateField = new JTextField();
        statusField = new JTextField();
        subtypeField = new JTextField();

        descriptionArea = new JTextArea(3, 20);

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeBox);

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Inventor / Owner:"));
        formPanel.add(inventorField);

        formPanel.add(new JLabel("Filing Date:"));
        formPanel.add(dateField);

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusField);

        formPanel.add(new JLabel("Category / Class / Work Type:"));
        formPanel.add(subtypeField);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));

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

        addButton.addActionListener(e -> addRecord());

        viewButton.addActionListener(e -> {
            outputArea.setText(dao.getAll());
        });

        updateButton.addActionListener(e -> updateRecord());

        deleteButton.addActionListener(e -> deleteRecord());

        clearButton.addActionListener(e -> clearFields());
    }

    private IntellectualProperty createIP() {

        int id = idField.getText().isEmpty()
                ? 0
                : Integer.parseInt(idField.getText());

        String type = (String) typeBox.getSelectedItem();

        String title = titleField.getText();
        String inventor = inventorField.getText();
        String date = dateField.getText();
        String status = statusField.getText();
        String subtype = subtypeField.getText();
        String description = descriptionArea.getText();

        if (type.equals("Patent")) {

            return new Patent(
                    id,
                    title,
                    inventor,
                    date,
                    status,
                    description,
                    subtype
            );

        } else if (type.equals("Trademark")) {

            return new Trademark(
                    id,
                    title,
                    inventor,
                    date,
                    status,
                    description,
                    subtype
            );

        } else {

            return new Copyright(
                    id,
                    title,
                    inventor,
                    date,
                    status,
                    description,
                    subtype
            );
        }
    }

    private void addRecord() {

        try {

            if (titleField.getText().isEmpty()
                    || inventorField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Title and Inventor/Owner are required."
                );

                return;
            }

            IntellectualProperty ip = createIP();

            if (dao.add(ip)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record added successfully!"
                );

                clearFields();
                outputArea.setText(dao.getAll());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add IP record."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input: " + e.getMessage()
            );
        }
    }

    private void updateRecord() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the ID of the record to update."
                );

                return;
            }

            IntellectualProperty ip = createIP();

            if (dao.update(ip)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record updated successfully!"
                );

                outputArea.setText(dao.getAll());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Record not found."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input: " + e.getMessage()
            );
        }
    }

    private void deleteRecord() {

        try {

            if (idField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the ID of the record to delete."
                );

                return;
            }

            int id = Integer.parseInt(idField.getText());

            if (dao.delete(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record deleted successfully!"
                );

                clearFields();
                outputArea.setText(dao.getAll());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Record not found."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid ID."
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        titleField.setText("");
        inventorField.setText("");
        dateField.setText("");
        statusField.setText("");
        subtypeField.setText("");
        descriptionArea.setText("");
    }
}