package dasboard;

import javax.swing.*;
import java.awt.*;
import CreateComponents.CreateComponents;
import dasboard.Labels.LabelSZ;
import dasboard.PastriesDetails.*;
import dasboard.PastriesList.*;
import common.UIComponent;

public class Dashboard implements UIComponent {
    private JPanel mainPanel, heading, content;
    private JLabel storeName, headline;
    ImageIcon logoImage = new ImageIcon("Images/Logo-removebg-preview.png");
    JLabel logoLabel = new JLabel(logoImage);
    private JButton returnButton;
    private CardLayout cardLayout;
    private JPanel cardContainer;

    public Dashboard(CardLayout cardLayout, JPanel cardContainer) {
        this.cardLayout = cardLayout;
        this.cardContainer = cardContainer;
        initialize();
    }

    @Override
    public void initialize() {
        mainPanel = new JPanel();
        content = new JPanel();
        storeName = CreateComponents.Label(35, 20, 500, 30, "Pick your Treat! Sweetie", "Serif", Font.ITALIC, 25, 36, 37, 42, 255, 255, 255);
        storeName.setForeground(Color.BLACK);
        heading = new JPanel();
        heading.setLayout(null);
        logoLabel.setBounds(270, 20, 500, 80);

        returnButton = new JButton("Return");
        returnButton.setBounds(0, 590, 100, 30);
        returnButton.setBackground(Color.decode("#FF2B50"));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font(null, Font.PLAIN, 20));
        returnButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        mainPanel.setBackground(Color.decode("#FFF7F8"));
        mainPanel.setLayout(null);
        heading.setBounds(0, 0, 985, 110);
        heading.setBackground(Color.decode("#FFF7F8"));
        content.setBounds(40, 115, 900, 615);
        content.setBackground(Color.decode("#FF5E7A"));
        content.setLayout(null);
        JPanel logoPanel = CreateComponents.ImagePanel("", 0, -5, 130, 115);

        content.add(storeName);
        heading.add(logoLabel);
        
     
        content.add(PastryList.getPastries()); 
        content.add(LabelSZ.labels());
        content.add(PastryDetails.getPastriesDetails()); 
        content.add(returnButton);

        mainPanel.add(logoPanel);
        mainPanel.add(content);
        mainPanel.add(heading);

        returnButton.addActionListener(e -> cardLayout.show(cardContainer, "MainMenu"));
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    public void reload() {
        PastryList.reloadPastries();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
