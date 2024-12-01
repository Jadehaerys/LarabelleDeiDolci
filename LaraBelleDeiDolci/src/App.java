import javax.swing.*;
import java.awt.*;
import LogIn.Login;  // Make sure to import the Login class
import dasboard.Dashboard;  // Import the Dashboard class

public class App {
    public static void main(String[] args) {
        // Create the main JFrame
        JFrame frame = new JFrame("Belle Dei Dolci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); 
        ImageIcon img = new ImageIcon("Images\\BelleDeiDolciLogo.png");
        frame.setIconImage(img.getImage());     

        // Create the CardLayout and JPanel to hold both panels (Login and Dashboard)
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Create the Login and Dashboard panels
        Login loginPanel = new Login(cardLayout, cardPanel); // Pass cardLayout and cardPanel to Login
        Dashboard dashboard = new Dashboard();

        // Add both panels to the CardLayout container
        cardPanel.add(loginPanel.getPanel(), "Login");
        cardPanel.add(dashboard.getPanel(), "Dashboard");

        // Set the initial panel to be the login panel
        cardLayout.show(cardPanel, "Login");

        // Set the content pane to the CardLayout container
        frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }
}