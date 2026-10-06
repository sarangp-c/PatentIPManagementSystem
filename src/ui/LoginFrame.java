package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton clearButton;
    private JButton showPasswordButton;

    private JLabel statusLabel;

    private UserDAO userDAO;

    // =========================================================
    // COLOURS
    // =========================================================

    private final Color NAVY =
            new Color(25, 45, 80);

    private final Color BLUE =
            new Color(45, 100, 220);

    private final Color BLUE_HOVER =
            new Color(35, 82, 190);

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

    public LoginFrame() {

        userDAO = new UserDAO();

        userDAO.createDefaultUsers();

        setTitle(
                "Login - Patent & IP Management System"
        );

        setSize(900, 560);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createLoginScreen();
    }

    // =========================================================
    // MAIN LOGIN SCREEN
    // =========================================================

    private void createLoginScreen() {

        JPanel mainPanel =
                new JPanel(
                        new GridLayout(1, 2)
                );

        // =====================================================
        // LEFT BRANDING
        // =====================================================

        JPanel leftPanel =
                new JPanel(
                        new GridBagLayout()
                );

        leftPanel.setBackground(
                NAVY
        );

        JPanel brandingPanel =
                new JPanel();

        brandingPanel.setLayout(
                new BoxLayout(
                        brandingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        brandingPanel.setOpaque(false);

        JLabel logo =
                new JLabel("IP");

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        40
                )
        );

        logo.setForeground(
                Color.WHITE
        );

        brandingPanel.add(logo);

        brandingPanel.add(
                Box.createVerticalStrut(25)
        );

        JLabel title =
                new JLabel(
                        "<html><div style='text-align:center'>"
                                + "Patent &amp;<br>"
                                + "Intellectual Property<br>"
                                + "Management System"
                                + "</div></html>"
                );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                Color.WHITE
        );

        brandingPanel.add(title);

        brandingPanel.add(
                Box.createVerticalStrut(20)
        );

        JLabel description =
                new JLabel(
                        "<html><div style='text-align:center'>"
                                + "Manage patents, trademarks,<br>"
                                + "copyrights and applications<br>"
                                + "in one secure system."
                                + "</div></html>"
                );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        description.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                new Color(
                        210,
                        220,
                        240
                )
        );

        brandingPanel.add(description);

        brandingPanel.add(
                Box.createVerticalStrut(120)
        );

        JLabel tagline =
                new JLabel(
                        "Secure  •  Organized  •  Efficient"
                );

        tagline.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tagline.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        tagline.setForeground(
                new Color(
                        175,
                        190,
                        220
                )
        );

        brandingPanel.add(tagline);

        leftPanel.add(
                brandingPanel
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel rightPanel =
                new JPanel(
                        new GridBagLayout()
                );

        rightPanel.setBackground(
                BACKGROUND
        );

        // =====================================================
        // LOGIN CARD
        // =====================================================

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                32,
                                44,
                                32,
                                44
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(
                        390,
                        455
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.weightx = 1.0;

        // =====================================================
        // WELCOME TITLE
        // =====================================================

        JLabel welcome =
                new JLabel(
                        "Welcome Back",
                        SwingConstants.CENTER
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        welcome.setForeground(
                TEXT
        );

        card.add(
                welcome,
                gbc
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        6,
                        0,
                        0,
                        0
                );

        JLabel subtitle =
                new JLabel(
                        "Sign in to continue",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                SECONDARY
        );

        card.add(
                subtitle,
                gbc
        );

        // =====================================================
        // USERNAME LABEL
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        30,
                        0,
                        6,
                        0
                );

        JLabel usernameLabel =
                new JLabel(
                        "Username"
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

        card.add(
                usernameLabel,
                gbc
        );

        // =====================================================
        // USERNAME FIELD
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        usernameField =
                createTextField();

        card.add(
                usernameField,
                gbc
        );

        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        18,
                        0,
                        6,
                        0
                );

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        passwordLabel.setForeground(
                TEXT
        );

        card.add(
                passwordLabel,
                gbc
        );

        // =====================================================
        // PASSWORD ROW
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        JPanel passwordPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2
                        )
                );

        passwordPanel.setPreferredSize(
                new Dimension(
                        300,
                        42
                )
        );

        passwordPanel.setMinimumSize(
                new Dimension(
                        300,
                        42
                )
        );

        passwordPanel.setMaximumSize(
                new Dimension(
                        300,
                        42
                )
        );

        passwordPanel.setOpaque(false);

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        showPasswordButton =
                new JButton(
                        "Show"
                );

        showPasswordButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        showPasswordButton.setForeground(
                BLUE
        );

        showPasswordButton.setBackground(
                new Color(
                        248,
                        249,
                        252
                )
        );

        showPasswordButton.setFocusPainted(
                false
        );

        showPasswordButton.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        showPasswordButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        passwordPanel.add(
                passwordField
        );

        passwordPanel.add(
                showPasswordButton
        );

        card.add(
                passwordPanel,
                gbc
        );

        // =====================================================
        // STATUS
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        8,
                        0,
                        0,
                        0
                );

        statusLabel =
                new JLabel(
                        " "
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        statusLabel.setForeground(
                SECONDARY
        );

        card.add(
                statusLabel,
                gbc
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        14,
                        0,
                        0,
                        0
                );

        loginButton =
                new JButton(
                        "Login"
                );

        stylePrimaryButton(
                loginButton
        );

        card.add(
                loginButton,
                gbc
        );

        // =====================================================
        // CLEAR BUTTON
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        9,
                        0,
                        0,
                        0
                );

        clearButton =
                new JButton(
                        "Clear"
                );

        styleSecondaryButton(
                clearButton
        );

        card.add(
                clearButton,
                gbc
        );

        // =====================================================
        // FOOTER
        // =====================================================

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        18,
                        0,
                        0,
                        0
                );

        JLabel footer =
                new JLabel(
                        "Patent & IP Management System",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        10
                )
        );

        footer.setForeground(
                SECONDARY
        );

        card.add(
                footer,
                gbc
        );

        // =====================================================
        // ADD CARD TO RIGHT SIDE
        // =====================================================

        rightPanel.add(
                card
        );

        mainPanel.add(
                leftPanel
        );

        mainPanel.add(
                rightPanel
        );

        add(mainPanel);

        // =====================================================
        // ACTIONS
        // =====================================================

        loginButton.addActionListener(
                e -> login()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        showPasswordButton.addActionListener(
                e -> togglePassword()
        );

        usernameField.addActionListener(
                e -> passwordField.requestFocus()
        );

        passwordField.addActionListener(
                e -> login()
        );
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
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        300,
                        42
                )
        );

        field.setMinimumSize(
                new Dimension(
                        300,
                        42
                )
        );

        field.setMaximumSize(
                new Dimension(
                        300,
                        42
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        return field;
    }

    // =========================================================
    // LOGIN BUTTON
    // =========================================================

    private void stylePrimaryButton(
            JButton button) {

        button.setPreferredSize(
                new Dimension(
                        300,
                        43
                )
        );

        button.setMinimumSize(
                new Dimension(
                        300,
                        43
                )
        );

        button.setMaximumSize(
                new Dimension(
                        300,
                        43
                )
        );

        button.setBackground(
                BLUE
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

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
                                BLUE_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                BLUE
                        );
                    }
                }
        );
    }

    // =========================================================
    // CLEAR BUTTON
    // =========================================================

    private void styleSecondaryButton(
            JButton button) {

        button.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        button.setMinimumSize(
                new Dimension(
                        300,
                        38
                )
        );

        button.setMaximumSize(
                new Dimension(
                        300,
                        38
                )
        );

        button.setBackground(
                new Color(
                        238,
                        241,
                        247
                )
        );

        button.setForeground(
                TEXT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

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
                                new Color(
                                        225,
                                        230,
                                        240
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                new Color(
                                        238,
                                        241,
                                        247
                                )
                        );
                    }
                }
        );
    }

    // =========================================================
    // SHOW / HIDE PASSWORD
    // =========================================================

    private void togglePassword() {

        if (passwordField.getEchoChar()
                == '\u0000') {

            passwordField.setEchoChar(
                    '•'
            );

            showPasswordButton.setText(
                    "Show"
            );

        } else {

            passwordField.setEchoChar(
                    '\u0000'
            );

            showPasswordButton.setText(
                    "Hide"
            );
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            statusLabel.setText(
                    "Please enter your username and password."
            );

            statusLabel.setForeground(
                    new Color(
                            200,
                            70,
                            70
                    )
            );

            return;
        }

        statusLabel.setText(
                "Signing in..."
        );

        statusLabel.setForeground(
                BLUE
        );

        User user =
                userDAO.login(
                        username,
                        password
                );

        if (user != null) {

            statusLabel.setText(
                    "Login successful!"
            );

            statusLabel.setForeground(
                    new Color(
                            40,
                            150,
                            90
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, "
                            + user.getUsername()
                            + "!\n\n"
                            + "Role: "
                            + user.getRole(),
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            MainFrame mainFrame =
                    new MainFrame(user);

            mainFrame.setVisible(true);

            dispose();

        } else {

            statusLabel.setText(
                    "Invalid username or password."
            );

            statusLabel.setForeground(
                    new Color(
                            200,
                            70,
                            70
                    )
            );

            passwordField.setText("");

            passwordField.requestFocus();
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        usernameField.setText("");

        passwordField.setText("");

        statusLabel.setText(
                " "
        );

        usernameField.requestFocus();
    }
}