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

    public Dashboard() {
        initialize();
    }

    public void initialize() {
        mainPanel = new JPanel();
        content = new JPanel();
        storeName = CreateComponents.Label(35, 20, 500, 30, "Belle Dei Dolci", "Times New Roman", Font.BOLD, 25, 36, 37, 42, 255, 255, 255);
        heading = new JPanel();
        heading.setLayout(null);
        headline = CreateComponents.Label(270,30,500,50, "Make Every Day Sweeter with Belle Dei Dolci!", "Times New Roman", Font.BOLD, 25,36, 37, 42, 255, 255, 255);
        mainPanel.setBackground(Color.decode("#FF91A4"));
        mainPanel.setLayout(null);
        heading.setBounds(0, 0, 985, 110);
        heading.setBackground(Color.decode("#FF2B50"));
        content.setBounds(40, 115, 900, 615);
        content.setBackground(Color.decode("#FF2B50"));
        content.setLayout(null);
        JPanel logoPanel = CreateComponents.ImagePanel("Images\\Pink Blue Cupcake Bakery Logo.png", 0, -5, 130, 115);
        content.add(storeName);
        heading.add(headline);
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

