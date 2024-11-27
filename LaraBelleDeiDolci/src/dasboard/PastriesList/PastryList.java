package dasboard.PastriesList;
import javax.swing.*;
import java.awt.*;
import dasboard.PastriesDetails.PastryDetails;
import CreateComponents.CreateComponents;

public class PastryList {
    public static JPanel getPastries() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(35, 75, 500, 400);
        mainPanel.setBackground(Color.decode("#FF91A4"));
        JButton[] pastryPanels = new JButton[5];
        JLabel[] pastryName = new JLabel[5];
        JLabel[] pastryPrice = new JLabel[5];
        JLabel[] pastryStatus = new JLabel[5];
        String[] names = { "Ensaymada", "AuChocolat", "Éclair", "Croissant", "Baguette" };
        double[] prices = { 100.0, 200.0, 150.0, 300.0, 250.0 };
        String[] statuses = { "Available", "Available", "Available", "Not Available", "Not Available" };

        for (int i = 0; i < 5; i++) {
            
            int panelHeight = 70; 
            int y = i * (panelHeight + 10);
            pastryPanels[i] = new JButton();
            pastryPanels[i].setBounds(10, y, mainPanel.getWidth() - 20, panelHeight);
            pastryPanels[i].setBackground(Color.decode("#FFF7F8"));
            pastryPanels[i].setLayout(null);
            pastryPanels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 
            
            pastryName[i] = new JLabel(names[i]);
            pastryName[i].setBounds(80, 10, 200, 25); 
            pastryName[i].setFont(new Font("Comic Sans", Font.BOLD, 18));
            pastryName[i].setForeground(Color.decode("#A62122"));

          
            pastryPrice[i] = new JLabel("P" + prices[i]);
            pastryPrice[i].setBounds(300, 10, 100, 25);
            pastryPrice[i].setFont(new Font("Comic Sans", Font.BOLD, 18));
            pastryPrice[i].setForeground(Color.decode("#A62122"));

            
            pastryStatus[i] = new JLabel();
            String statusMsg;
            if (statuses[i].equals("Available")) {
                statusMsg = "<html><span style='color: #00FF00;'>Available</span></html>";
            } else {
                statusMsg = "<html><span style='color: red;'>Out of Stock</span></html>";
            }
            pastryStatus[i].setText(statusMsg);
            pastryStatus[i].setBounds(80, 40, 150, 20);
            // Image Panel
            JPanel[] imagePanel = new JPanel[5];
            imagePanel[0] = CreateComponents.ImagePanel("Images\\ensaymada.png", 21,10, 45,45);
            imagePanel[1] = CreateComponents.ImagePanel("Images\\AuChocolat.jpg", 21,10, 45,45);
            imagePanel[2] = CreateComponents.ImagePanel("Images\\Eclair.jpg", 21,10, 45,45);
            imagePanel[3] = CreateComponents.ImagePanel("Images\\Croissant.jpg", 21,10, 45,45);
            imagePanel[4] = CreateComponents.ImagePanel("Images\\baguette.jpg", 21,10, 45,45);

         
            int index = i; 
            pastryPanels[i].addActionListener(e -> {
                if (statuses[index].equals("Available")) {
                    PastryDetails.addPastry(names[index], prices[index]);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        names[index] + " is not available.", 
                        "Unavailable", 
                        JOptionPane.WARNING_MESSAGE);
                }
            });

           
            pastryPanels[i].add(imagePanel[i]);
            pastryPanels[i].add(pastryName[i]);
            pastryPanels[i].add(pastryPrice[i]);
            pastryPanels[i].add(pastryStatus[i]);

          
            mainPanel.add(pastryPanels[i]);
        }

        return mainPanel;
    }
}