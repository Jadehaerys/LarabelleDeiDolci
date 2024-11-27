package dasboard;
import javax.swing.*;
import java.awt.*;
import CreateComponents.CreateComponents;
import dasboard.Labels.LabelSZ;
import dasboard.PastriesDetails.*;
import dasboard.PastriesList.*;

public class Dashboard {
    private JPanel mainPanel, hr, content;
    private JLabel storeName;

    public Dashboard() {
        initialize();
    }

    public void initialize() {
        mainPanel = new JPanel();
        content = new JPanel();
        storeName = CreateComponents.Label(35, 20, 500, 30, "Larabelle Dei Dolci", "Arial", Font.BOLD, 25, 36, 37, 42, 255, 255, 255);
        hr = new JPanel();

        mainPanel.setBackground(Color.decode("#FF91A4"));
        mainPanel.setLayout(null);

    

        hr.setBounds(0, 100, 975, 10);
        hr.setBackground(Color.decode("#F7002B"));

        content.setBounds(75, 115, 900, 615);
        content.setBackground(Color.decode("#FF2B50"));
        content.setLayout(null);
        JPanel logoPanel = CreateComponents.ImagePanel("Images\\Lorebelle-removebg-preview.png", 0, 250, 130, 250);
        content.add(storeName);
        content.add(PastryList.getPastries());
        content.add(LabelSZ.labels());
        content.add(PastryDetails.getPastriesDetails());
        mainPanel.add(logoPanel);
        mainPanel.add(content);
        mainPanel.add(hr);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

