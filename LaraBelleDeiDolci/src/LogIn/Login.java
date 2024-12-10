package LogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CreateComponents.CreateComponents;
import dasboard.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    private JPanel cardContainer, mainContainer, authPanel, userInputPanel, passInputPanel, logoPanel;
    private JLabel headerLabel;
    private TextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, returnButton;
    private CardLayout cardLayout;
    private boolean userAuthenticated = false;

    public Login(CardLayout cardLayout, JPanel cardContainer) {
        this.cardLayout = cardLayout;
        this.cardContainer = cardContainer;
        initialize();
    }

    private void initialize() {
        int defaultWidth = 1000;
        int defaultHeight = 800;
        mainContainer = new JPanel();
        authPanel = new JPanel();
        userInputPanel = new JPanel();
        passInputPanel = new JPanel();
        logoPanel = CreateComponents.ImagePanel("Images\\Logo-removebg-preview.png", 250, 0, 450, 400);
        usernameField = new TextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        returnButton = new JButton("Return");
        headerLabel = new JLabel("<html>" + "<h1 style=\"font-size:80px\">Belle" + "<span style=\"color:#FF2B50\"> Dei Dolci</span>" + "</h1>" + "</html>");
        headerLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 80));
        mainContainer.setBackground(Color.decode("#FF91A4"));
        mainContainer.setBounds(0, 0, defaultWidth, defaultHeight);
        mainContainer.setLayout(null);
        authPanel.setBounds(0, 0, defaultWidth, defaultHeight);
        authPanel.setBackground(Color.decode("#FF91A4"));
        authPanel.setLayout(null);
        userInputPanel.setBounds(290, 400, 350, 30);
        userInputPanel.setLayout(null);

        loginButton.setBounds(415, 500, 100, 30);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font(null, Font.PLAIN, 20));
        loginButton.setBackground(Color.decode("#FF2B50"));
        loginButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        returnButton.setBounds(415, 550, 100, 30);
        returnButton.setForeground(new Color(255, 255, 255));
        returnButton.setFont(new Font(null, Font.PLAIN, 20));
        returnButton.setBackground(Color.decode("#FF2B50"));
        returnButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        passInputPanel.setBounds(290, 450, 350, 30);
        passInputPanel.setLayout(null);
        usernameField.setBounds(0, 0, 350, 30);
        usernameField.setFont(new Font(null, Font.PLAIN, 25));
        passwordField.setBounds(0, 0, 350, 30);
        passwordField.setFont(new Font(null, Font.PLAIN, 25));
        headerLabel.setForeground(new Color(255, 255, 255));
        headerLabel.setBounds(130, 250, 800, 150);
        
        userInputPanel.add(usernameField);
        passInputPanel.add(passwordField);
        mainContainer.add(authPanel);
        authPanel.add(headerLabel);
        authPanel.add(userInputPanel);
        authPanel.add(passInputPanel);
        authPanel.add(loginButton);
        authPanel.add(returnButton);
        SwingUtilities.invokeLater(() -> mainContainer.getRootPane().setDefaultButton(loginButton));
        authPanel.add(logoPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authentication.Authenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                    cardLayout.show(cardContainer, "Admin");
                } else {
                    JOptionPane.showMessageDialog(mainContainer, "ERROR! INVALID LOGIN", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardContainer, "MainMenu");
            }
        });
    }

    public boolean AuthStatus() {
        return userAuthenticated;
    }

    public JPanel getPanel() {
        return mainContainer;
    }
}
