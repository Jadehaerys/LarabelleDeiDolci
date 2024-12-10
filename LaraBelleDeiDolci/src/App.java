import javax.swing.*;
import java.awt.*;
import LogIn.Login;
import dasboard.*;
import dasboard.AdminDasboard.*;
import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame implements ActionListener {

    JButton adminButton = new JButton("Admin");
    JButton customerButton = new JButton("Customer");

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    Dashboard dashboard;

    public App() {
        super("Main Menu");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BelleDeiDolci");
        ImageIcon logo = new ImageIcon("Images\\BelleDeiDolciLogo.png");
        setIconImage(logo.getImage());
        JPanel mainMenuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("Images\\Logo-removebg-preview.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(Color.decode("#FFF7F8"));
        customerButton.setBounds(540, 550, 150, 50);
        adminButton.setBounds(290, 550, 150, 50);
        adminButton.setFocusPainted(false);
        adminButton.setBackground(Color.decode("#FF2B50"));
        adminButton.setForeground(Color.WHITE);
        customerButton.setFocusPainted(false);
        customerButton.setBackground(Color.decode("#FF2B50"));
        customerButton.setForeground(Color.WHITE);
        adminButton.addActionListener(this);
        customerButton.addActionListener(this);

        mainMenuPanel.add(adminButton);
        mainMenuPanel.add(customerButton);

        Login loginPanel = new Login(cardLayout, cardPanel);
        dashboard = new Dashboard(cardLayout, cardPanel); // Initialize the dashboard
        Admin adminPanel = new Admin(cardLayout, cardPanel);

        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(loginPanel.getPanel(), "Login");
        cardPanel.add(dashboard.getPanel(), "Dashboard");
        cardPanel.add(adminPanel, "Admin"); 
        setContentPane(cardPanel);
        cardLayout.show(cardPanel, "MainMenu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            cardLayout.show(cardPanel, "Login");
        } else if (e.getSource() == customerButton) {
            dashboard.reload();
            cardLayout.show(cardPanel, "Dashboard");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}

