import javax.swing.*;
import java.awt.*;
import LogIn.Login;  /
import dasboard.Dashboard;  

public class App {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Belle Dei Dolci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);  
        ImageIcon img = new ImageIcon("Images\\BelleDeiDolciLogo.png");
        frame.setIconImage(img.getImage());     
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        Login loginPanel = new Login(cardLayout, cardPanel); 
        Dashboard dashboard = new Dashboard();

        cardPanel.add(loginPanel.getPanel(), "Login");
        cardPanel.add(dashboard.getPanel(), "Dashboard");
        cardLayout.show(cardPanel, "Login");

        frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }
}