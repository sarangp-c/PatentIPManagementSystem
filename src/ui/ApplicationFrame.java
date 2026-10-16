package ui;

import dao.ApplicationDAO;

import model.Application;

import model.Role;

import model.User;

import javax.swing.*;

import javax.swing.border.*;

import java.awt.*;

import java.awt.geom.RoundRectangle2D;

public class ApplicationFrame extends JFrame {

    private JTextField idField;

    private JTextField ipIdField;

    private JComboBox<String> stageBox;

    private JTextField dateField;

    private JTextField reviewerField;

    private JTextArea remarksArea;

    private JTable recordsTable;
    private javax.swing.table.DefaultTableModel recordsModel;

    private JButton addButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private ApplicationDAO dao;

    private User loggedInUser;

    private static final Color BG_COLOR = new Color(244, 247, 252);

    private static final Color CARD_COLOR = Color.WHITE;

    private static final Color NAVY = new Color(30, 43, 68);

    private static final Color BLUE = new Color(47, 100, 220);

    private static final Color GREEN = new Color(42, 157, 91);

    private static final Color RED = new Color(210, 67, 70);

    private static final Color GREY = new Color(116, 128, 148);

    private static final Color TEXT = new Color(25, 35, 55);

    private static final Color SUBTEXT = new Color(91, 105, 130);

    public ApplicationFrame(User user) {

        loggedInUser = user;

        dao = new ApplicationDAO();

        setTitle("Applications");

        setSize(1000, 700);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel background = new JPanel(new BorderLayout(0, 14));

        background.setBackground(BG_COLOR);

        background.setBorder(new EmptyBorder(18, 20, 18, 20));

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());

        header.setOpaque(false);

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Applications");

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(

                "Track and manage intellectual property applications"

        );

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        subtitle.setForeground(SUBTEXT);

        titlePanel.add(title);

        titlePanel.add(Box.createVerticalStrut(3));

        titlePanel.add(subtitle);

        JPanel userBadge = new JPanel();

        userBadge.setLayout(new BoxLayout(userBadge, BoxLayout.Y_AXIS));

        userBadge.setBackground(Color.WHITE);

        userBadge.setBorder(new CompoundBorder(

                new LineBorder(new Color(210, 218, 232)),

                new EmptyBorder(8, 14, 8, 14)

        ));

        JLabel userLabel = new JLabel(

                "User: " + user.getUsername()

        );

        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        userLabel.setForeground(TEXT);

        JLabel roleLabel = new JLabel(

                "Role: " + user.getRole()

        );

        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        roleLabel.setForeground(BLUE);

        userBadge.add(userLabel);

        userBadge.add(Box.createVerticalStrut(3));

        userBadge.add(roleLabel);

        header.add(titlePanel, BorderLayout.WEST);

        header.add(userBadge, BorderLayout.EAST);

        // ================= FORM CARD =================

        JPanel formCard = new JPanel(new BorderLayout());

        formCard.setBackground(CARD_COLOR);

        formCard.setBorder(new CompoundBorder(

                new LineBorder(new Color(210, 218, 232)),

                new EmptyBorder(18, 20, 18, 20)

        ));

        JLabel formTitle = new JLabel("Application Details");

        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        formTitle.setForeground(TEXT);

        formCard.add(formTitle, BorderLayout.NORTH);

        // GridBagLayout gives precise control over label and field positions

        JPanel formPanel = new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        idField = new JTextField();

        ipIdField = new JTextField();

        stageBox = new JComboBox<>(new String[]{

                "Application Submitted",

                "Initial Review",

                "Technical Review",

                "Legal Review",

                "Examination",

                "Approved",

                "Rejected",

                "Registered"

        });

        dateField = new JTextField();

        reviewerField = new JTextField();

        remarksArea = new JTextArea(3, 20);

        remarksArea.setLineWrap(true);

        remarksArea.setWrapStyleWord(true);

        styleTextField(idField);

        styleTextField(ipIdField);

        styleTextField(dateField);

        styleTextField(reviewerField);

        styleComboBox(stageBox);

        remarksArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        remarksArea.setBorder(new CompoundBorder(

                new LineBorder(new Color(200, 210, 225)),

                new EmptyBorder(7, 8, 7, 8)

        ));

        // Row 0

        addLabel(formPanel, "Application ID", 0, 0);

        addLabel(formPanel, "IP Record ID", 1, 0);

        addField(formPanel, idField, 0, 1);

        addField(formPanel, ipIdField, 1, 1);

        // Row 2

        addLabel(formPanel, "Current Stage", 0, 2);

        addLabel(formPanel, "Last Updated", 1, 2);

        addField(formPanel, stageBox, 0, 3);

        addField(formPanel, dateField, 1, 3);

        // Row 4

        addLabel(formPanel, "Reviewer ID", 0, 4);

        addField(formPanel, reviewerField, 0, 5);

        // Remarks spans both columns

        addLabel(formPanel, "Remarks", 0, 6, 2);

        GridBagConstraints remarksConstraints =

                new GridBagConstraints();

        remarksConstraints.gridx = 0;

        remarksConstraints.gridy = 7;

        remarksConstraints.gridwidth = 2;

        remarksConstraints.weightx = 1;

        remarksConstraints.fill = GridBagConstraints.BOTH;

        remarksConstraints.insets =

                new Insets(3, 0, 0, 0);

        formPanel.add(

                new JScrollPane(remarksArea),

                remarksConstraints

        );

        formCard.add(formPanel, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel buttonPanel = new JPanel(new FlowLayout(

                FlowLayout.LEFT,

                10,

                0

        ));

        buttonPanel.setOpaque(false);

        addButton = createButton(

                "Add",

                BLUE,

                new PlusIcon()

        );

        viewButton = createButton(

                "View All",

                new Color(65, 78, 105),

                new ViewIcon()

        );

        updateButton = createButton(

                "Update",

                GREEN,

                new EditIcon()

        );

        deleteButton = createButton(

                "Delete",

                RED,

                new DeleteIcon()

        );

        clearButton = createButton(

                "Clear",

                GREY,

                new ClearIcon()

        );

        buttonPanel.add(addButton);

        buttonPanel.add(viewButton);

        buttonPanel.add(updateButton);

        buttonPanel.add(deleteButton);

        buttonPanel.add(clearButton);

        // ================= RECORDS CARD =================

        JPanel recordsCard = new JPanel(new BorderLayout(0, 10));

        recordsCard.setBackground(CARD_COLOR);

        recordsCard.setBorder(new CompoundBorder(

                new LineBorder(new Color(210, 218, 232)),

                new EmptyBorder(16, 20, 16, 20)

        ));

        JLabel recordsTitle = new JLabel("Application Records");

        recordsTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));

        recordsTitle.setForeground(TEXT);

        recordsModel = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "IP Record ID", "Current Stage", "Last Updated", "Reviewer ID", "Remarks"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        recordsTable = new JTable(recordsModel);
        recordsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        recordsTable.setRowHeight(25);
        recordsTable.setForeground(TEXT);
        recordsTable.setBackground(Color.WHITE);
        recordsTable.setSelectionBackground(new Color(220, 232, 255));
        recordsTable.setSelectionForeground(TEXT);
        recordsTable.setGridColor(new Color(225, 231, 240));
        recordsTable.setAutoCreateRowSorter(true);
        recordsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        recordsTable.getTableHeader().setBackground(new Color(235, 240, 248));
        recordsTable.getTableHeader().setForeground(TEXT);
        recordsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recordsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && recordsTable.getSelectedRow() >= 0) {
                loadSelectedRecord();
            }
        });

        JScrollPane scrollPane = new JScrollPane(recordsTable);

        scrollPane.setBorder(

                new LineBorder(new Color(185, 195, 212))

        );

        scrollPane.setPreferredSize(

                new Dimension(850, 110)

        );

        recordsCard.add(recordsTitle, BorderLayout.NORTH);

        recordsCard.add(scrollPane, BorderLayout.CENTER);

        // ================= CENTER =================

        JPanel centerPanel = new JPanel();

        centerPanel.setOpaque(false);

        centerPanel.setLayout(new BoxLayout(

                centerPanel,

                BoxLayout.Y_AXIS

        ));

        formCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        recordsCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPanel.add(formCard);

        centerPanel.add(Box.createVerticalStrut(12));

        centerPanel.add(buttonPanel);

        centerPanel.add(Box.createVerticalStrut(12));

        centerPanel.add(recordsCard);

        background.add(header, BorderLayout.NORTH);

        background.add(centerPanel, BorderLayout.CENTER);

        add(background);

        // ================= ACTIONS =================

        addButton.addActionListener(e -> addApplication());

        viewButton.addActionListener(e -> viewApplications());

        updateButton.addActionListener(e -> updateApplication());

        deleteButton.addActionListener(e -> deleteApplication());

        clearButton.addActionListener(e -> clearFields());

        applyPermissions();

        viewApplications();

    }

    // =========================================================

    // FORM HELPERS

    // =========================================================

    private void addLabel(

            JPanel panel,

            String text,

            int column,

            int row

    ) {

        addLabel(panel, text, column, row, 1);

    }

    private void addLabel(

            JPanel panel,

            String text,

            int column,

            int row,

            int width

    ) {

        GridBagConstraints gbc =

                new GridBagConstraints();

        gbc.gridx = column;

        gbc.gridy = row;

        gbc.gridwidth = width;

        gbc.weightx = 1;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets =

                new Insets(8, 0, 4, 18);

        JLabel label = new JLabel(text);

        label.setFont(

                new Font("Segoe UI", Font.BOLD, 12)

        );

        label.setForeground(TEXT);

        panel.add(label, gbc);

    }

    private void addField(

            JPanel panel,

            JComponent field,

            int column,

            int row

    ) {

        GridBagConstraints gbc =

                new GridBagConstraints();

        gbc.gridx = column;

        gbc.gridy = row;

        gbc.weightx = 1;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets =

                new Insets(0, 0, 2, 18);

        panel.add(field, gbc);

    }

    private void styleTextField(JTextField field) {

        field.setFont(

                new Font("Segoe UI", Font.PLAIN, 13)

        );

        field.setBorder(new CompoundBorder(

                new LineBorder(

                        new Color(200, 210, 225)

                ),

                new EmptyBorder(7, 8, 7, 8)

        ));

    }

    private void styleComboBox(

            JComboBox<String> box

    ) {

        box.setFont(

                new Font("Segoe UI", Font.PLAIN, 13)

        );

        box.setBackground(Color.WHITE);

        box.setBorder(

                new LineBorder(

                        new Color(200, 210, 225)

                )

        );

    }

    // =========================================================

    // BUTTON

    // =========================================================

    private JButton createButton(

            String text,

            Color color,

            Icon icon

    ) {

        JButton button = new JButton(text, icon);

        button.setFont(

                new Font("Segoe UI", Font.BOLD, 13)

        );

        button.setForeground(Color.WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setIconTextGap(8);

        button.setBorder(

                new EmptyBorder(10, 18, 10, 18)

        );

        button.setOpaque(false);

        button.setCursor(

                new Cursor(Cursor.HAND_CURSOR)

        );

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {

            @Override

            public void paint(

                    Graphics g,

                    JComponent c

            ) {

                Graphics2D g2 =

                        (Graphics2D) g.create();

                g2.setRenderingHint(

                        RenderingHints.KEY_ANTIALIASING,

                        RenderingHints.VALUE_ANTIALIAS_ON

                );

                if (((JButton) c).getModel().isRollover()) {

                    g2.setColor(

                            color.brighter()

                    );

                } else {

                    g2.setColor(color);

                }

                g2.fill(

                        new RoundRectangle2D.Double(

                                0,

                                0,

                                c.getWidth(),

                                c.getHeight(),

                                10,

                                10

                        )

                );

                g2.dispose();

                super.paint(g, c);

            }

        });

        return button;

    }

    // =========================================================

    // PERMISSIONS

    // =========================================================

    private void applyPermissions() {
        Role role = loggedInUser.getRole();
        viewButton.setEnabled(true);
        clearButton.setEnabled(true);

        if (role == Role.ADMIN) {
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setFieldsEditable(true);
            return;
        }

        if (role == Role.REVIEWER) {
            // Keep restricted buttons clickable so their permission message appears.
            addButton.setEnabled(true);
            deleteButton.setEnabled(true);
            updateButton.setEnabled(true);
            idField.setEditable(false);
            ipIdField.setEditable(false);
            dateField.setEditable(false);
            reviewerField.setEditable(true);
            stageBox.setEnabled(true);
            remarksArea.setEditable(true);
            addButton.setToolTipText("Only an administrator can add applications.");
            deleteButton.setToolTipText("Only an administrator can delete applications.");
            return;
        }

        if (role == Role.RESEARCHER) {
            // Keep restricted buttons clickable; action methods show access-denied popups.
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setFieldsEditable(false);
            addButton.setToolTipText("Researchers can only view application progress.");
            updateButton.setToolTipText("Only reviewers or administrators can update applications.");
            deleteButton.setToolTipText("Only an administrator can delete applications.");
            clearButton.setToolTipText("Clear the selected application details.");
            recordsTable.setToolTipText("Select an application row to view its details.");
        }
    }

    private void setFieldsEditable(boolean editable) {
        idField.setEditable(editable);
        ipIdField.setEditable(editable);
        dateField.setEditable(editable);
        reviewerField.setEditable(editable);
        stageBox.setEnabled(editable);
        remarksArea.setEditable(editable);
    }

    // ADD

    // =========================================================

    private void addApplication() {

                if (loggedInUser.getRole() != Role.ADMIN) {
            JOptionPane.showMessageDialog(this, "Only an administrator can add applications.", "Access denied", JOptionPane.WARNING_MESSAGE);
            return;
        }
try {

            if (ipIdField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(

                        this,

                        "Enter the IP Record ID."

                );

                return;

            }

            if (dateField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(

                        this,

                        "Enter the Last Updated date."

                );

                return;

            }

            int ipId = Integer.parseInt(

                    ipIdField.getText().trim()

            );

            String stage =

                    (String) stageBox.getSelectedItem();

            String date =

                    dateField.getText().trim();

            int reviewerId = 0;

            if (!reviewerField.getText().trim().isEmpty()) {

                reviewerId =

                        Integer.parseInt(

                                reviewerField.getText().trim()

                        );

            }

            String remarks =

                    remarksArea.getText().trim();

            Application app =

                    new Application(

                            0,

                            ipId,

                            stage,

                            date,

                            reviewerId,

                            remarks

                    );

            if (dao.add(app)) {

                JOptionPane.showMessageDialog(

                        this,

                        "Application added successfully!"

                );

                clearFields();

                viewApplications();

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Failed to add application.\n\n"

                                + dao.getLastError(),

                        "Error",

                        JOptionPane.ERROR_MESSAGE

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

    // =========================================================

    // VIEW

    // =========================================================

    private void viewApplications() {
        recordsModel.setRowCount(0);
        String data = dao.getAll();
        if (data == null || data.trim().isEmpty()) {
            if (loggedInUser.getRole() == Role.RESEARCHER) {
                JOptionPane.showMessageDialog(this, "No applications found.", "Application Tracking", JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }

        // ApplicationDAO.getAll() currently returns lines such as:
        // ID: 2 | IP ID: 1 | Stage: Approved | Last Updated: 2026-10-06 | Reviewer ID: null | Remarks: ...
        for (String line : data.split("\\R")) {
            String trimmed = line.trim();
            if (!trimmed.startsWith("ID:")) continue;
            String[] parts = trimmed.split("\\s*\\|\\s*", 6);
            if (parts.length < 5) continue;
            String appId = valueAfterColon(parts[0]);
            String ipId = valueAfterColon(parts[1]);
            String stage = valueAfterColon(parts[2]);
            String updated = valueAfterColon(parts[3]);
            String reviewer = valueAfterColon(parts[4]);
            String remarks = parts.length >= 6 ? valueAfterColon(parts[5]) : "";
            recordsModel.addRow(new Object[]{appId, ipId, stage, updated, reviewer, remarks});
        }

        if (recordsModel.getRowCount() == 0 && loggedInUser.getRole() == Role.RESEARCHER) {
            JOptionPane.showMessageDialog(this, "No applications found.", "Application Tracking", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String valueAfterColon(String text) {
        int colon = text.indexOf(':');
        return colon >= 0 ? text.substring(colon + 1).trim() : text.trim();
    }

    private void loadSelectedRecord() {
        int selected = recordsTable.getSelectedRow();
        if (selected < 0) return;
        int row = recordsTable.convertRowIndexToModel(selected);
        idField.setText(String.valueOf(recordsModel.getValueAt(row, 0)));
        ipIdField.setText(String.valueOf(recordsModel.getValueAt(row, 1)));
        String stage = String.valueOf(recordsModel.getValueAt(row, 2));
        if (((javax.swing.DefaultComboBoxModel<String>) stageBox.getModel()).getIndexOf(stage) < 0) {
            stageBox.addItem(stage);
        }
        stageBox.setSelectedItem(stage);
        dateField.setText(String.valueOf(recordsModel.getValueAt(row, 3)));
        String reviewer = String.valueOf(recordsModel.getValueAt(row, 4));
        reviewerField.setText("null".equalsIgnoreCase(reviewer) ? "" : reviewer);
        remarksArea.setText(String.valueOf(recordsModel.getValueAt(row, 5)));
    }

    // =========================================================

    // UPDATE

    // =========================================================

    private void updateApplication() {

                if (loggedInUser.getRole() == Role.RESEARCHER) {
            JOptionPane.showMessageDialog(this, "Researchers can only view application progress.", "Access denied", JOptionPane.WARNING_MESSAGE);
            return;
        }
try {

            if (idField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(

                        this,

                        "Enter the Application ID to update."

                );

                return;

            }

            int id =

                    Integer.parseInt(

                            idField.getText().trim()

                    );

            int ipId =

                    Integer.parseInt(

                            ipIdField.getText().trim()

                    );

            String stage =

                    (String) stageBox.getSelectedItem();

            String date =

                    dateField.getText().trim();

            int reviewerId = 0;

            if (!reviewerField.getText().trim().isEmpty()) {

                reviewerId =

                        Integer.parseInt(

                                reviewerField.getText().trim()

                        );

            }

            String remarks =

                    remarksArea.getText().trim();

            Application app =

                    new Application(

                            id,

                            ipId,

                            stage,

                            date,

                            reviewerId,

                            remarks

                    );

            if (dao.update(app)) {

                JOptionPane.showMessageDialog(

                        this,

                        "Application updated successfully!"

                );

                clearFields();

                viewApplications();

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Failed to update application.\n\n"

                                + dao.getLastError(),

                        "Error",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Application ID, IP Record ID and Reviewer ID must be numbers."

            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Error: " + e.getMessage()

            );

        }

    }

    // =========================================================

    // DELETE

    // =========================================================

    private void deleteApplication() {

                if (loggedInUser.getRole() != Role.ADMIN) {
            JOptionPane.showMessageDialog(this, "Only an administrator can delete applications.", "Access denied", JOptionPane.WARNING_MESSAGE);
            return;
        }
try {

            if (idField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(

                        this,

                        "Enter the Application ID to delete."

                );

                return;

            }

            int id =

                    Integer.parseInt(

                            idField.getText().trim()

                    );

            int choice =

                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to delete this application?",

                            "Confirm Delete",

                            JOptionPane.YES_NO_OPTION,

                            JOptionPane.WARNING_MESSAGE

                    );

            if (choice != JOptionPane.YES_OPTION) {

                return;

            }

            if (dao.delete(id)) {

                JOptionPane.showMessageDialog(

                        this,

                        "Application deleted successfully!"

                );

                clearFields();

                viewApplications();

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Application not found or could not be deleted.\n\n"

                                + dao.getLastError(),

                        "Error",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(

                    this,

                    "Application ID must be a number."

            );

        }

    }

    // =========================================================

    // CLEAR

    // =========================================================

    private void clearFields() {

        idField.setText("");

        ipIdField.setText("");

        stageBox.setSelectedIndex(0);

        dateField.setText("");

        reviewerField.setText("");

        remarksArea.setText("");
        if (recordsTable != null) recordsTable.clearSelection();
        idField.requestFocus();

    }

    // =========================================================

    // ICONS

    // =========================================================

    private static class PlusIcon implements Icon {

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

                int y

        ) {

            Graphics2D g2 =

                    (Graphics2D) g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(

                    new BasicStroke(

                            2.2f,

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

    private static class ViewIcon implements Icon {

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

                int y

        ) {

            Graphics2D g2 =

                    (Graphics2D) g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(

                    new BasicStroke(1.8f)

            );

            g2.drawOval(

                    x + 2,

                    y + 4,

                    12,

                    8

            );

            g2.fillOval(

                    x + 6,

                    y + 6,

                    4,

                    4

            );

            g2.dispose();

        }

    }

    private static class EditIcon implements Icon {

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

                int y

        ) {

            Graphics2D g2 =

                    (Graphics2D) g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(

                    new BasicStroke(

                            2.2f,

                            BasicStroke.CAP_ROUND,

                            BasicStroke.JOIN_ROUND

                    )

            );

            g2.drawLine(

                    x + 4,

                    y + 12,

                    x + 12,

                    y + 4

            );

            g2.drawLine(

                    x + 3,

                    y + 13,

                    x + 7,

                    y + 12

            );

            g2.dispose();

        }

    }

    private static class DeleteIcon implements Icon {

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

                int y

        ) {

            Graphics2D g2 =

                    (Graphics2D) g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(

                    new BasicStroke(

                            2.2f,

                            BasicStroke.CAP_ROUND,

                            BasicStroke.JOIN_ROUND

                    )

            );

            g2.drawLine(

                    x + 4,

                    y + 4,

                    x + 12,

                    y + 12

            );

            g2.drawLine(

                    x + 12,

                    y + 4,

                    x + 4,

                    y + 12

            );

            g2.dispose();

        }

    }

    private static class ClearIcon implements Icon {

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

                int y

        ) {

            Graphics2D g2 =

                    (Graphics2D) g.create();

            g2.setColor(Color.WHITE);

            g2.setStroke(

                    new BasicStroke(2.2f)

            );

            g2.drawArc(

                    x + 3,

                    y + 3,

                    10,

                    10,

                    45,

                    285

            );

            g2.drawLine(

                    x + 11,

                    y + 3,

                    x + 13,

                    y + 6

            );

            g2.dispose();

        }

    }

}
