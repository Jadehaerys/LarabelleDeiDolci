package dasboard;
import javax.swing.*;
import java.awt.*;
import CreateComponents.CreateComponents;
import dasboard.Labels.LabelSZ;
import dasboard.PastriesDetails.*;
import dasboard.PastriesList.*;

public class Dashboard {
    private JPanel mainPanel, heading, content;
    private JLabel storeName, headline;
    ImageIcon logoImage = new ImageIcon("Images/Logo-removebg-preview.png");
    JLabel logoLabel = new JLabel(logoImage);
    
   
    
    public Dashboard() {
        initialize();
    }

    public void initialize() {
        mainPanel = new JPanel();
        content = new JPanel();
        storeName = CreateComponents.Label(35, 20, 500, 30, "Belle Dei Dolci", "Times New Roman", Font.BOLD, 25, 36, 37, 42, 255, 255, 255);
        heading = new JPanel();
        heading.setLayout(null);
        logoLabel.setBounds(270, 20, 500, 80);
        mainPanel.setBackground(Color.decode("#FF91A4"));
        mainPanel.setLayout(null);
        heading.setBounds(0, 0, 985, 110);
        heading.setBackground(Color.decode("#FF2B50"));
        content.setBounds(40, 115, 900, 615);
        content.setBackground(Color.decode("#FF2B50"));
        content.setLayout(null);
        JPanel logoPanel = CreateComponents.ImagePanel("", 0, -5, 130, 115);
        content.add(storeName);
        heading.add(logoLabel);
        content.add(PastryList.getPastries());
        content.add(LabelSZ.labels());
        content.add(PastryDetails.getPastriesDetails());
        mainPanel.add(logoPanel);
        mainPanel.add(content);
        mainPanel.add(heading);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

