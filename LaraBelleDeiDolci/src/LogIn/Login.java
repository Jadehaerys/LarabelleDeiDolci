package LogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CreateComponents.CreateComponents;
import dasboard.Dashboard;

public class Login {
    private JPanel cardContainer, mainContainer, authPanel, userInputPanel, passInputPanel, logoPanel;
    private JLabel headerLabel;
    private TextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
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
        logoPanel = CreateComponents.ImagePanel("Images/BelleDeiDolciLogo.png", 355, 25, 250, 250);
        usernameField = new TextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        headerLabel = new JLabel("<html>" + "<h1 style=\"font-size:80px\">Belle" + "<span style=\"color:#FF2B50\"> Dei Dolci</span>" + "</h1>" + "</html>");

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
        SwingUtilities.invokeLater(() -> mainContainer.getRootPane().setDefaultButton(loginButton));
        authPanel.add(logoPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authentication.Authenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                    cardLayout.show(cardContainer, "Dashboard");
                } else {
                    JOptionPane.showMessageDialog(mainContainer, "ERROR! INVALID LOGIN", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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
