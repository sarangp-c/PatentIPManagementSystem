package ui;

import dao.IPRecordDAO;
import model.Copyright;
import model.IntellectualProperty;
import model.Patent;
import model.Role;
import model.Trademark;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IPRecordFrame extends JFrame {

    private JTextField idField;
    private JComboBox<String> typeBox;
    private JTextField titleField;
    private JTextField inventorField;
    private JTextField dateField;
    private JComboBox<String> statusBox;
    private JTextField subtypeField;
    private JTextArea descriptionArea;
    private JTextArea outputArea;

    private IPRecordDAO dao;
    private User loggedInUser;

    private JButton addButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton loadButton;

    private String addPermissionMessage = "";
    private String updatePermissionMessage = "";
    private String deletePermissionMessage = "";

    // =========================================================
    // COLOURS
    // =========================================================

    private final Color NAVY =
            new Color(25, 45, 80);

    private final Color BLUE =
            new Color(45, 100, 220);

    private final Color BLUE_HOVER =
            new Color(35, 82, 190);

    private final Color GREEN =
            new Color(45, 155, 95);

    private final Color GREEN_HOVER =
            new Color(35, 130, 78);

    private final Color RED =
            new Color(205, 70, 70);

    private final Color RED_HOVER =
            new Color(180, 55, 55);

    private final Color DARK =
            new Color(70, 82, 105);

    private final Color DARK_HOVER =
            new Color(55, 68, 90);

    private final Color GREY =
            new Color(120, 130, 145);

    private final Color GREY_HOVER =
            new Color(100, 110, 125);

    private final Color BACKGROUND =
            new Color(244, 247, 252);

    private final Color CARD =
            Color.WHITE;

    private final Color TEXT =
            new Color(35, 42, 55);

    private final Color SECONDARY =
            new Color(105, 115, 130);

    private final Color BORDER =
            new Color(215, 221, 232);

    public IPRecordFrame(User user) {

        loggedInUser = user;
        dao = new IPRecordDAO();

        setTitle(
                "IP Records - " + user.getRole()
        );

        setSize(
                1000,
                720
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createInterface();
    }

    // =========================================================
    // MAIN INTERFACE
    // =========================================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(0, 18)
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        JLabel titleLabel =
                new JLabel("IP Records");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                TEXT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Manage patents, trademarks and copyrights"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                SECONDARY
        );

        titlePanel.add(titleLabel);

        titlePanel.add(
                Box.createVerticalStrut(4)
        );

        titlePanel.add(subtitleLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        // =====================================================
        // USER BADGE
        // =====================================================

        JPanel userBadge =
                new JPanel(
                        new GridLayout(2, 1)
                );

        userBadge.setBackground(
                CARD
        );

        userBadge.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                15,
                                8,
                                15
                        )
                )
        );

        JLabel userLabel =
                new JLabel(
                        "User: "
                                + loggedInUser.getUsername()
                );

        userLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        userLabel.setForeground(
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
                        Font.BOLD,
                        11
                )
        );

        roleLabel.setForeground(
                BLUE
        );

        userBadge.add(userLabel);
        userBadge.add(roleLabel);

        headerPanel.add(
                userBadge,
                BorderLayout.EAST
        );

        // =====================================================
        // FORM CARD
        // =====================================================

        JPanel formCard =
                createCard();

        formCard.setLayout(
                new BorderLayout(0, 14)
        );

        JLabel formTitle =
                new JLabel(
                        "Record Details"
                );

        formTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        formTitle.setForeground(
                TEXT
        );

        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );

        JPanel fieldsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                18,
                                12
                        )
                );

        fieldsPanel.setOpaque(false);

        // =====================================================
        // RECORD ID + LOAD
        // =====================================================

        idField =
                createTextField();

        idField.setToolTipText(
                "Enter Record ID and press Enter to load"
        );

        loadButton =
                createActionButton(
                        "Load",
                        new ViewIcon(),
                        DARK,
                        DARK_HOVER
                );

        loadButton.setPreferredSize(
                new Dimension(
                        85,
                        35
                )
        );

        JPanel idPanel =
                new JPanel(
                        new BorderLayout(6, 0)
                );

        idPanel.setOpaque(false);

        idPanel.add(
                idField,
                BorderLayout.CENTER
        );

        idPanel.add(
                loadButton,
                BorderLayout.EAST
        );

        addField(
                fieldsPanel,
                "Record ID",
                idPanel
        );

        // =====================================================
        // TYPE
        // =====================================================

        typeBox =
                new JComboBox<>(
                        new String[]{
                                "Patent",
                                "Trademark",
                                "Copyright"
                        }
                );

        styleComboBox(typeBox);

        addField(
                fieldsPanel,
                "IP Type",
                typeBox
        );

        // =====================================================
        // TITLE
        // =====================================================

        titleField =
                createTextField();

        addField(
                fieldsPanel,
                "Title",
                titleField
        );

        // =====================================================
        // INVENTOR
        // =====================================================

        inventorField =
                createTextField();

        addField(
                fieldsPanel,
                "Inventor / Owner",
                inventorField
        );

        // =====================================================
        // FILING DATE
        // =====================================================

        dateField =
                createTextField();

        dateField.setToolTipText(
                "Example: 2026-10-06"
        );

        addField(
                fieldsPanel,
                "Filing Date",
                dateField
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusBox =
                new JComboBox<>(
                        new String[]{
                                "Pending",
                                "Under Review",
                                "Approved",
                                "Rejected",
                                "Registered"
                        }
                );

        styleComboBox(statusBox);

        addField(
                fieldsPanel,
                "Status",
                statusBox
        );

        // =====================================================
        // SUBTYPE
        // =====================================================

        subtypeField =
                createTextField();

        subtypeField.setToolTipText(
                "Patent category, trademark class or copyright work type"
        );

        addField(
                fieldsPanel,
                "Category / Class / Work Type",
                subtypeField
        );

        // Empty slot
        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setOpaque(false);

        fieldsPanel.add(
                emptyPanel
        );

        formCard.add(
                fieldsPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JPanel descriptionPanel =
                new JPanel(
                        new BorderLayout(0, 6)
                );

        descriptionPanel.setOpaque(false);

        JLabel descriptionLabel =
                new JLabel(
                        "Description"
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        descriptionLabel.setForeground(
                TEXT
        );

        descriptionArea =
                new JTextArea(3, 20);

        descriptionArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        descriptionArea.setLineWrap(true);

        descriptionArea.setWrapStyleWord(true);

        descriptionArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );

        descriptionPanel.add(
                descriptionLabel,
                BorderLayout.NORTH
        );

        descriptionPanel.add(
                new JScrollPane(
                        descriptionArea
                ),
                BorderLayout.CENTER
        );

        formCard.add(
                descriptionPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        addButton =
                createActionButton(
                        "Add",
                        new PlusIcon(),
                        BLUE,
                        BLUE_HOVER
                );

        viewButton =
                createActionButton(
                        "View All",
                        new ViewIcon(),
                        DARK,
                        DARK_HOVER
                );

        updateButton =
                createActionButton(
                        "Update",
                        new EditIcon(),
                        GREEN,
                        GREEN_HOVER
                );

        deleteButton =
                createActionButton(
                        "Delete",
                        new DeleteIcon(),
                        RED,
                        RED_HOVER
                );

        clearButton =
                createActionButton(
                        "Clear",
                        new ClearIcon(),
                        GREY,
                        GREY_HOVER
                );

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // =====================================================
        // RECORDS CARD
        // =====================================================

        JPanel recordsCard =
                createCard();

        recordsCard.setLayout(
                new BorderLayout(0, 10)
        );

        JLabel recordsTitle =
                new JLabel(
                        "Saved IP Records"
                );

        recordsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        recordsTitle.setForeground(
                TEXT
        );

        recordsCard.add(
                recordsTitle,
                BorderLayout.NORTH
        );

        outputArea =
                new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        13
                )
        );

        outputArea.setForeground(
                new Color(
                        225,
                        232,
                        245
                )
        );

        outputArea.setBackground(
                new Color(
                        30,
                        40,
                        58
                )
        );

        outputArea.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        JScrollPane outputScroll =
                new JScrollPane(
                        outputArea
                );

        outputScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                45,
                                55,
                                75
                        )
                )
        );

        recordsCard.add(
                outputScroll,
                BorderLayout.CENTER
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setOpaque(false);

        formCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        buttonPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        recordsCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        centerPanel.add(formCard);

        centerPanel.add(
                Box.createVerticalStrut(12)
        );

        centerPanel.add(buttonPanel);

        centerPanel.add(
                Box.createVerticalStrut(12)
        );

        centerPanel.add(recordsCard);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =====================================================
        // ACTIONS
        // =====================================================

        loadButton.addActionListener(
                e -> loadRecord()
        );

        idField.addActionListener(
                e -> loadRecord()
        );

        addButton.addActionListener(e -> {

            if (!addPermissionMessage.isEmpty()) {

                showPermissionMessage(
                        addPermissionMessage
                );

            } else {

                addRecord();
            }
        });

        viewButton.addActionListener(
                e -> viewRecords()
        );

        updateButton.addActionListener(e -> {

            if (!updatePermissionMessage.isEmpty()) {

                showPermissionMessage(
                        updatePermissionMessage
                );

            } else {

                updateRecord();
            }
        });

        deleteButton.addActionListener(e -> {

            if (!deletePermissionMessage.isEmpty()) {

                showPermissionMessage(
                        deletePermissionMessage
                );

            } else {

                deleteRecord();
            }
        });

        clearButton.addActionListener(
                e -> clearFields()
        );

        applyPermissions();

        viewRecords();
    }

    // =========================================================
    // LOAD RECORD
    // =========================================================

    private void loadRecord() {

        try {

            String idText =
                    idField.getText().trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a Record ID to load.",
                        "Missing Record ID",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(idText);

            IntellectualProperty ip =
                    dao.getById(id);

            if (ip == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No IP record found with ID "
                                + id
                                + ".",
                        "Record Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // =================================================
            // LOAD COMMON DETAILS
            // =================================================

            titleField.setText(
                    ip.getTitle()
            );

            inventorField.setText(
                    ip.getInventorName()
            );

            dateField.setText(
                    ip.getFilingDate()
            );

            statusBox.setSelectedItem(
                    ip.getStatus()
            );

            descriptionArea.setText(
                    ip.getDescription()
            );

            // =================================================
            // LOAD TYPE + SUBTYPE
            // =================================================

            if (ip instanceof Patent) {

                typeBox.setSelectedItem(
                        "Patent"
                );

                subtypeField.setText(
                        ((Patent) ip)
                                .getPatentCategory()
                );

            } else if (
                    ip instanceof Trademark) {

                typeBox.setSelectedItem(
                        "Trademark"
                );

                subtypeField.setText(
                        ((Trademark) ip)
                                .getTrademarkClass()
                );

            } else if (
                    ip instanceof Copyright) {

                typeBox.setSelectedItem(
                        "Copyright"
                );

                subtypeField.setText(
                        ((Copyright) ip)
                                .getWorkType()
                );
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Record ID "
                            + id
                            + " loaded successfully.\n\n"
                            + "You can now change only the details you want to update.",
                    "Record Loaded",
                    JOptionPane.INFORMATION_MESSAGE
            );

            titleField.requestFocus();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Record ID must be a valid number.",
                    "Invalid Record ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =========================================================
    // CARD
    // =========================================================

    private JPanel createCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // FORM FIELD
    // =========================================================

    private void addField(
            JPanel panel,
            String labelText,
            JComponent component) {

        JPanel fieldPanel =
                new JPanel();

        fieldPanel.setLayout(
                new BoxLayout(
                        fieldPanel,
                        BoxLayout.Y_AXIS
                )
        );

        fieldPanel.setOpaque(false);

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                TEXT
        );

        fieldPanel.add(label);

        fieldPanel.add(
                Box.createVerticalStrut(5)
        );

        fieldPanel.add(component);

        panel.add(fieldPanel);
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        field.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                7,
                                9,
                                7,
                                9
                        )
                )
        );

        return field;
    }

    // =========================================================
    // COMBO BOX
    // =========================================================

    private void styleComboBox(
            JComboBox<String> comboBox) {

        comboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        comboBox.setBackground(
                Color.WHITE
        );

        comboBox.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        comboBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        comboBox.setToolTipText(
                "Select an option"
        );
    }

    // =========================================================
    // ATTRACTIVE BUTTON
    // =========================================================

    private JButton createActionButton(
            String text,
            Icon icon,
            Color normalColor,
            Color hoverColor) {

        JButton button =
                new RoundedButton(
                        text,
                        icon
                );

        button.setPreferredSize(
                new Dimension(
                        120,
                        42
                )
        );

        button.setBackground(
                normalColor
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                hoverColor
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                normalColor
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // PERMISSIONS
    // =========================================================

    private void applyPermissions() {

        Role role =
                loggedInUser.getRole();

        if (role == Role.ADMIN) {

            addButton.setToolTipText(
                    "Add a new IP record"
            );

            viewButton.setToolTipText(
                    "View all saved IP records"
            );

            updateButton.setToolTipText(
                    "Update an existing IP record"
            );

            deleteButton.setToolTipText(
                    "Delete an IP record"
            );

            clearButton.setToolTipText(
                    "Clear all form fields"
            );

        } else if (role == Role.RESEARCHER) {

            addButton.setToolTipText(
                    "Add a new IP record"
            );

            viewButton.setToolTipText(
                    "View all saved IP records"
            );

            updateButton.setToolTipText(
                    "Update an existing IP record"
            );

            deletePermissionMessage =
                    "Only Admin can delete IP records.";

            makeRestrictedButton(
                    deleteButton,
                    deletePermissionMessage
            );

            clearButton.setToolTipText(
                    "Clear all form fields"
            );

        } else if (role == Role.REVIEWER) {

            addPermissionMessage =
                    "Only Admin or Researcher can add IP records.";

            updatePermissionMessage =
                    "Only Admin or Researcher can update IP records.";

            deletePermissionMessage =
                    "Only Admin can delete IP records.";

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
                    "View all saved IP records"
            );

            clearButton.setToolTipText(
                    "Clear all form fields"
            );
        }
    }

    // =========================================================
    // RESTRICTED BUTTON
    // =========================================================

    private void makeRestrictedButton(
            JButton button,
            String message) {

        button.setToolTipText(message);

        button.setForeground(
                new Color(
                        215,
                        215,
                        215
                )
        );

        button.setBackground(
                new Color(
                        145,
                        150,
                        160
                )
        );
    }

    // =========================================================
    // PERMISSION MESSAGE
    // =========================================================

    private void showPermissionMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Access Restricted",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // CREATE IP OBJECT
    // =========================================================

    private IntellectualProperty createIP() {

        int id =
                idField.getText()
                        .trim()
                        .isEmpty()
                        ? 0
                        : Integer.parseInt(
                                idField.getText()
                                        .trim()
                        );

        String type =
                (String)
                        typeBox.getSelectedItem();

        String title =
                titleField.getText().trim();

        String inventor =
                inventorField.getText().trim();

        String date =
                dateField.getText().trim();

        String status =
                (String)
                        statusBox.getSelectedItem();

        String subtype =
                subtypeField.getText().trim();

        String description =
                descriptionArea.getText().trim();

        if ("Patent".equals(type)) {

            return new Patent(
                    id,
                    title,
                    inventor,
                    date,
                    status,
                    description,
                    subtype
            );

        } else if ("Trademark".equals(type)) {

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

    // =========================================================
    // ADD
    // =========================================================

    private void addRecord() {

        try {

            if (titleField.getText()
                    .trim()
                    .isEmpty()
                    ||
                    inventorField.getText()
                            .trim()
                            .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Title and Inventor/Owner are required.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            IntellectualProperty ip =
                    createIP();

            if (dao.add(ip)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                viewRecords();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add IP record.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Record ID must be a valid number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VIEW
    // =========================================================

    private void viewRecords() {

        outputArea.setText(
                dao.getAll()
        );

        outputArea.setCaretPosition(
                0
        );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private void updateRecord() {

        try {

            if (idField.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the Record ID first.",
                        "Missing Record ID",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                                    .trim()
                    );

            /*
             * Make sure the record actually exists.
             * Normally the user will have loaded it first.
             */
            IntellectualProperty existing =
                    dao.getById(id);

            if (existing == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No IP record found with ID "
                                + id
                                + ".\n\n"
                                + "Load the record first.",
                        "Record Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            IntellectualProperty ip =
                    createIP();

            if (dao.update(ip)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                viewRecords();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update IP record.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Record ID must be a valid number.",
                    "Invalid Record ID",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteRecord() {

        try {

            if (idField.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the ID of the record to delete.",
                        "Missing ID",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                                    .trim()
                    );

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete IP record ID "
                                    + id
                                    + "?\n\n"
                                    + "This action cannot be undone.",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            if (dao.delete(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "IP record deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                viewRecords();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Record not found.",
                        "Delete Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Record ID must be a valid number.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        idField.setText("");

        typeBox.setSelectedIndex(0);

        titleField.setText("");

        inventorField.setText("");

        dateField.setText("");

        statusBox.setSelectedIndex(0);

        subtypeField.setText("");

        descriptionArea.setText("");

        idField.requestFocus();
    }

    // =========================================================
    // ROUNDED BUTTON
    // =========================================================

    private static class RoundedButton
            extends JButton {

        private RoundedButton(
                String text,
                Icon icon) {

            super(
                    text,
                    icon
            );

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            setHorizontalTextPosition(
                    SwingConstants.RIGHT
            );

            setIconTextGap(
                    8
            );
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    getBackground()
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    12,
                    12
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================================================
    // PLUS ICON
    // =========================================================

    private static class PlusIcon
            implements Icon {

        public int getIconWidth() {
            return 16;
        }

        public int getIconHeight() {
            return 16;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            2.5f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    x + 8,
                    y + 3,
                    x + 8,
                    y + 13
            );

            g2.drawLine(
                    x + 3,
                    y + 8,
                    x + 13,
                    y + 8
            );

            g2.dispose();
        }
    }

    // =========================================================
    // VIEW ICON
    // =========================================================

    private static class ViewIcon
            implements Icon {

        public int getIconWidth() {
            return 17;
        }

        public int getIconHeight() {
            return 17;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(2f)
            );

            g2.drawOval(
                    x + 2,
                    y + 4,
                    13,
                    9
            );

            g2.fillOval(
                    x + 7,
                    y + 7,
                    4,
                    4
            );

            g2.dispose();
        }
    }

    // =========================================================
    // EDIT ICON
    // =========================================================

    private static class EditIcon
            implements Icon {

        public int getIconWidth() {
            return 17;
        }

        public int getIconHeight() {
            return 17;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            2.5f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    x + 4,
                    y + 13,
                    x + 13,
                    y + 4
            );

            g2.drawLine(
                    x + 3,
                    y + 14,
                    x + 7,
                    y + 13
            );

            g2.drawLine(
                    x + 13,
                    y + 4,
                    x + 15,
                    y + 6
            );

            g2.dispose();
        }
    }

    // =========================================================
    // DELETE ICON
    // =========================================================

    private static class DeleteIcon
            implements Icon {

        public int getIconWidth() {
            return 17;
        }

        public int getIconHeight() {
            return 17;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            2.3f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    x + 4,
                    y + 4,
                    x + 13,
                    y + 13
            );

            g2.drawLine(
                    x + 13,
                    y + 4,
                    x + 4,
                    y + 13
            );

            g2.dispose();
        }
    }

    // =========================================================
    // CLEAR ICON
    // =========================================================

    private static class ClearIcon
            implements Icon {

        public int getIconWidth() {
            return 17;
        }

        public int getIconHeight() {
            return 17;
        }

        public void paintIcon(
                Component c,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            2.2f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawArc(
                    x + 3,
                    y + 3,
                    11,
                    11,
                    40,
                    290
            );

            g2.drawLine(
                    x + 12,
                    y + 3,
                    x + 14,
                    y + 7
            );

            g2.dispose();
        }
    }
}