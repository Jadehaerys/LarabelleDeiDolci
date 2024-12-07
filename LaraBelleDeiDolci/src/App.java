import javax.swing.*;
import java.awt.*;
import LogIn.Login;  
import dasboard.Dashboard;  

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        setTitle("Belle Dei Dolci");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        ImageIcon img = new ImageIcon("Images\\BelleDeiDolciLogo.png");
        setIconImage(img.getImage());

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        Login loginPanel = new Login(cardLayout, cardPanel);
        Dashboard dashboard = new Dashboard();

        cardPanel.add(loginPanel.getPanel(), "Login");
        cardPanel.add(dashboard.getPanel(), "Dashboard");
        cardLayout.show(cardPanel, "Login");

        setContentPane(cardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new App());
        App a = new App();
    }
}