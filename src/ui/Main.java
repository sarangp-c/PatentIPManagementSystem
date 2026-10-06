package ui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            setupLookAndFeel();

            LoginFrame loginFrame =
                    new LoginFrame();

            loginFrame.setVisible(true);
        });
    }

    private static void setupLookAndFeel() {

        try {

            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(
                        info.getName())) {

                    UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Could not load Nimbus Look and Feel."
            );
        }

        // General colors
        UIManager.put(
                "control",
                new ColorUIResource(
                        244, 247, 252
                )
        );

        UIManager.put(
                "nimbusBase",
                new ColorUIResource(
                        47, 100, 220
                )
        );

        UIManager.put(
                "nimbusBlueGrey",
                new ColorUIResource(
                        65, 78, 105
                )
        );

        UIManager.put(
                "nimbusLightBackground",
                new ColorUIResource(
                        255, 255, 255
                )
        );

        UIManager.put(
                "text",
                new ColorUIResource(
                        25, 35, 55
                )
        );

        // Popup styling
        UIManager.put(
                "OptionPane.background",
                new ColorUIResource(
                        255, 255, 255
                )
        );

        UIManager.put(
                "Panel.background",
                new ColorUIResource(
                        255, 255, 255
                )
        );

        UIManager.put(
                "OptionPane.messageForeground",
                new ColorUIResource(
                        25, 35, 55
                )
        );

        UIManager.put(
                "OptionPane.messageFont",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        UIManager.put(
                "OptionPane.buttonFont",
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        // Buttons
        UIManager.put(
                "Button.background",
                new ColorUIResource(
                        47, 100, 220
                )
        );

        UIManager.put(
                "Button.foreground",
                new ColorUIResource(
                        Color.WHITE
                )
        );

        UIManager.put(
                "Button.font",
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        // Text fields
        UIManager.put(
                "TextField.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        UIManager.put(
                "TextField.background",
                new ColorUIResource(
                        Color.WHITE
                )
        );

        UIManager.put(
                "PasswordField.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        // Labels
        UIManager.put(
                "Label.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        // Tooltips
        UIManager.put(
                "ToolTip.background",
                new ColorUIResource(
                        30, 43, 68
                )
        );

        UIManager.put(
                "ToolTip.foreground",
                new ColorUIResource(
                        Color.WHITE
                )
        );

        UIManager.put(
                "ToolTip.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );
    }
}