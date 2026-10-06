package ui;

import dao.UserDAO;
import model.Role;
import model.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UserManagementFrame extends JFrame {

    private JTextField idField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Role> roleBox;
    private JCheckBox showPasswordBox;

    private JTextArea outputArea;

    private UserDAO dao;
    private User loggedInUser;

    private static final Color BG_COLOR =
            new Color(244, 247, 252);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color NAVY =
            new Color(30, 43, 68);

    private static final Color BLUE =
            new Color(47, 100, 220);

    private static final Color GREEN =
            new Color(42, 157, 91);

    private static final Color RED =
            new Color(210, 67, 70);

    private static final Color GREY =
            new Color(116, 128, 148);

    private static final Color TEXT =
            new Color(25, 35, 55);

    private static final Color SUBTEXT =
            new Color(91, 105, 130);

    public UserManagementFrame(User user) {

        loggedInUser = user;
        dao = new UserDAO();

        setTitle("User Management");
        setSize(1000, 700);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );
        setLocationRelativeTo(null);

        JPanel background =
                new JPanel(new BorderLayout(0, 14));

        background.setBackground(BG_COLOR);

        background.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("User Management");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Manage system users and access roles"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(SUBTEXT);

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(3)
        );

        titlePanel.add(subtitle);

        // =====================================================
        // USER BADGE
        // =====================================================

        JPanel userBadge =
                new JPanel();

        userBadge.setLayout(
                new BoxLayout(
                        userBadge,
                        BoxLayout.Y_AXIS
                )
        );

        userBadge.setBackground(Color.WHITE);

        userBadge.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        218,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                8,
                                14,
                                8,
                                14
                        )
                )
        );

        JLabel userLabel =
                new JLabel(
                        "User: "
                                + user.getUsername()
                );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        userLabel.setForeground(TEXT);

        JLabel roleLabel =
                new JLabel(
                        "Role: "
                                + user.getRole()
                );

        roleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        roleLabel.setForeground(BLUE);

        userBadge.add(userLabel);

        userBadge.add(
                Box.createVerticalStrut(3)
        );

        userBadge.add(roleLabel);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                userBadge,
                BorderLayout.EAST
        );

        // =====================================================
        // USER DETAILS CARD
        // =====================================================

        JPanel formCard =
                new JPanel(new BorderLayout());

        formCard.setBackground(CARD_COLOR);

        formCard.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        218,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel formTitle =
                new JLabel("User Details");

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        formTitle.setForeground(TEXT);

        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(Color.WHITE);

        idField =
                new JTextField();

        usernameField =
                new JTextField();

        passwordField =
                new JPasswordField();

        roleBox =
                new JComboBox<>(
                        Role.values()
                );

        showPasswordBox =
                new JCheckBox(
                        "Show password"
                );

        showPasswordBox.setBackground(
                Color.WHITE
        );

        showPasswordBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        styleTextField(idField);
        styleTextField(usernameField);
        styleTextField(passwordField);
        styleComboBox(roleBox);

        // Row 0
        addLabel(
                formPanel,
                "User ID",
                0,
                0
        );

        addLabel(
                formPanel,
                "Username",
                1,
                0
        );

        // Row 1
        addField(
                formPanel,
                idField,
                0,
                1
        );

        addField(
                formPanel,
                usernameField,
                1,
                1
        );

        // Row 2
        addLabel(
                formPanel,
                "Password",
                0,
                2
        );

        addLabel(
                formPanel,
                "Role",
                1,
                2
        );

        // Row 3
        addField(
                formPanel,
                passwordField,
                0,
                3
        );

        addField(
                formPanel,
                roleBox,
                1,
                3
        );

        // Show password
        GridBagConstraints showPasswordConstraints =
                new GridBagConstraints();

        showPasswordConstraints.gridx = 0;
        showPasswordConstraints.gridy = 4;

        showPasswordConstraints.weightx = 1;

        showPasswordConstraints.fill =
                GridBagConstraints.HORIZONTAL;

        showPasswordConstraints.anchor =
                GridBagConstraints.WEST;

        showPasswordConstraints.insets =
                new Insets(
                        2,
                        0,
                        2,
                        18
                );

        formPanel.add(
                showPasswordBox,
                showPasswordConstraints
        );

        formCard.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // PASSWORD TOGGLE
        // =====================================================

        char defaultEcho =
                passwordField.getEchoChar();

        showPasswordBox.addActionListener(e -> {

            if (showPasswordBox.isSelected()) {

                passwordField.setEchoChar(
                        (char) 0
                );

            } else {

                passwordField.setEchoChar(
                        defaultEcho
                );
            }
        });

        // =====================================================
        // BUTTONS
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

        JButton addButton =
                createButton(
                        "Add",
                        BLUE,
                        new PlusIcon()
                );

        JButton viewButton =
                createButton(
                        "View All",
                        new Color(
                                65,
                                78,
                                105
                        ),
                        new ViewIcon()
                );

        JButton updateButton =
                createButton(
                        "Update",
                        GREEN,
                        new EditIcon()
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        RED,
                        new DeleteIcon()
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        GREY,
                        new ClearIcon()
                );

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // =====================================================
        // ADMIN INFO
        // =====================================================

        JPanel infoPanel =
                new JPanel(
                        new BorderLayout()
                );

        infoPanel.setBackground(
                new Color(
                        235,
                        242,
                        255
                )
        );

        infoPanel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        190,
                                        207,
                                        240
                                )
                        ),
                        new EmptyBorder(
                                9,
                                12,
                                9,
                                12
                        )
                )
        );

        JLabel infoLabel =
                new JLabel(
                        "Admin controls: "
                                + "manage users and assign system roles."
                );

        infoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        infoLabel.setForeground(
                new Color(
                        55,
                        75,
                        110
                )
        );

        infoPanel.add(
                infoLabel,
                BorderLayout.CENTER
        );

        // =====================================================
        // RECORDS CARD
        // =====================================================

        JPanel recordsCard =
                new JPanel(
                        new BorderLayout(0, 10)
                );

        recordsCard.setBackground(
                CARD_COLOR
        );

        recordsCard.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        218,
                                        232
                                )
                        ),
                        new EmptyBorder(
                                16,
                                20,
                                16,
                                20
                        )
                )
        );

        JLabel recordsTitle =
                new JLabel(
                        "Registered Users"
                );

        recordsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        recordsTitle.setForeground(TEXT);

        outputArea =
                new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        12
                )
        );

        outputArea.setForeground(Color.WHITE);

        outputArea.setBackground(NAVY);

        outputArea.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );

        scrollPane.setBorder(
                new LineBorder(
                        new Color(
                                185,
                                195,
                                212
                        )
                )
        );

        scrollPane.setPreferredSize(
                new Dimension(
                        850,
                        130
                )
        );

        recordsCard.add(
                recordsTitle,
                BorderLayout.NORTH
        );

        recordsCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setOpaque(false);

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        buttonPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        infoPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        recordsCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        centerPanel.add(formCard);

        centerPanel.add(
                Box.createVerticalStrut(10)
        );

        centerPanel.add(buttonPanel);

        centerPanel.add(
                Box.createVerticalStrut(10)
        );

        centerPanel.add(infoPanel);

        centerPanel.add(
                Box.createVerticalStrut(10)
        );

        centerPanel.add(recordsCard);

        background.add(
                header,
                BorderLayout.NORTH
        );

        background.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(background);

        // =====================================================
        // ACTIONS
        // =====================================================

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

    // =========================================================
    // FORM HELPERS
    // =========================================================

    private void addLabel(
            JPanel panel,
            String text,
            int column,
            int row
    ) {

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = column;
        gbc.gridy = row;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        4,
                        18
                );

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(TEXT);

        panel.add(
                label,
                gbc
        );
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

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        2,
                        18
                );

        panel.add(
                field,
                gbc
        );
    }

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        200,
                                        210,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                7,
                                8,
                                7,
                                8
                        )
                )
        );
    }

    private void styleComboBox(
            JComboBox<Role> box
    ) {

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        box.setBackground(Color.WHITE);

        box.setBorder(
                new LineBorder(
                        new Color(
                                200,
                                210,
                                225
                        )
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

        JButton button =
                new JButton(
                        text,
                        icon
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setIconTextGap(8);

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setOpaque(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setUI(
                new javax.swing.plaf.basic.BasicButtonUI() {

                    @Override
                    public void paint(
                            Graphics g,
                            JComponent c
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints
                                        .KEY_ANTIALIASING,
                                RenderingHints
                                        .VALUE_ANTIALIAS_ON
                        );

                        if (((JButton) c)
                                .getModel()
                                .isRollover()) {

                            g2.setColor(
                                    color.brighter()
                            );

                        } else {

                            g2.setColor(
                                    color
                            );
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

                        super.paint(
                                g,
                                c
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // ADD USER
    // =========================================================

    private void addUser() {

        try {

            String username =
                    usernameField
                            .getText()
                            .trim();

            String password =
                    new String(
                            passwordField
                                    .getPassword()
                    );

            Role role =
                    (Role)
                            roleBox
                                    .getSelectedItem();

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
                        "Username already exists.",
                        "Duplicate User",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            User user =
                    new User(
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
                                + dao.getLastError(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VIEW USERS
    // =========================================================

    private void viewUsers() {

        outputArea.setText(
                dao.getAllUsers()
        );
    }

    // =========================================================
    // UPDATE USER
    // =========================================================

    private void updateUser() {

        try {

            if (idField
                    .getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the User ID to update."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            String username =
                    usernameField
                            .getText()
                            .trim();

            String password =
                    new String(
                            passwordField
                                    .getPassword()
                    );

            Role role =
                    (Role)
                            roleBox
                                    .getSelectedItem();

            if (username.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username and password are required."
                );

                return;
            }

            User user =
                    new User(
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
                                + dao.getLastError(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
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
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    // =========================================================
    // DELETE USER
    // =========================================================

    private void deleteUser() {

        try {

            if (idField
                    .getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the User ID to delete."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            if (id ==
                    loggedInUser.getId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You cannot delete the currently logged-in admin.",
                        "Action Not Allowed",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this user?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice !=
                    JOptionPane.YES_OPTION) {

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
                                + dao.getLastError(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID must be a number."
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        idField.setText("");

        usernameField.setText("");

        passwordField.setText("");

        roleBox.setSelectedItem(
                Role.RESEARCHER
        );

        showPasswordBox.setSelected(
                false
        );

        passwordField.setEchoChar(
                (char) 8226
        );

        idField.requestFocus();
    }

    // =========================================================
    // ICONS
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
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(
                    Color.WHITE
            );

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

    private static class ViewIcon
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
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(
                    Color.WHITE
            );

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

    private static class EditIcon
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
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(
                    Color.WHITE
            );

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

    private static class DeleteIcon
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
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(
                    Color.WHITE
            );

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

    private static class ClearIcon
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
                int y
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setColor(
                    Color.WHITE
            );

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