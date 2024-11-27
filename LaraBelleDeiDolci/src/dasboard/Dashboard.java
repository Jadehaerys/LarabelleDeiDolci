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
        hr.setBackground(new Color(155, 155, 155));

        content.setBounds(75, 115, 900, 615);
        content.setBackground(new Color(36, 37, 42));
        content.setLayout(null);
        
        content.add(storeName);
        content.add(PastryList.getPastries());
        content.add(LabelSZ.labels());
        content.add(PastryDetails.getPastriesDetails());
        mainPanel.add(content);
        mainPanel.add(hr);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

