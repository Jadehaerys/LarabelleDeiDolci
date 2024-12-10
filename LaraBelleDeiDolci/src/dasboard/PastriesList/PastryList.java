package dasboard.PastriesList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import dasboard.PastriesDetails.PastryDetails;
import CreateComponents.CreateComponents;
import java.util.List;
import java.util.ArrayList;

public class PastryList {
    private static JPanel mainPanel;
    private static List<String> names;
    private static List<Double> prices;
    private static List<String> statuses;

    public static JPanel getPastries() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setBounds(35, 75, 500, 400);
            mainPanel.setBackground(Color.decode("#FF91A4"));
        }
        reloadPastries();
        return mainPanel;
    }

    public static void reloadPastries() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        names = new ArrayList<>();
        prices = new ArrayList<>();
        statuses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pasties.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    names.add(parts[0].trim());
                    prices.add(Double.parseDouble(parts[1].trim()));
                    statuses.add(parts[2].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading pastries file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        createPastryButtons();
    }

    private static void createPastryButtons() {
        JButton[] pastryPanels = new JButton[names.size()];
        JLabel[] pastryName = new JLabel[names.size()];
        JLabel[] pastryPrice = new JLabel[names.size()];
        JLabel[] pastryStatus = new JLabel[names.size()];

        JPanel[] imagePanel = new JPanel[5];
        imagePanel[0] = CreateComponents.ImagePanel("Images\\ensaymada.png", 21, 10, 45, 45);
        imagePanel[1] = CreateComponents.ImagePanel("Images\\AuChocolat.jpg", 21, 10, 45, 45);
        imagePanel[2] = CreateComponents.ImagePanel("Images\\Eclair.jpg", 21, 10, 45, 45);
        imagePanel[3] = CreateComponents.ImagePanel("Images\\Croissant.jpg", 21, 10, 45, 45);
        imagePanel[4] = CreateComponents.ImagePanel("Images\\baguette.jpg", 21, 10, 45, 45);

        for (int i = 0; i < names.size(); i++) {
            int panelHeight = 70;
            int y = i * (panelHeight + 10);
            pastryPanels[i] = new JButton();
            pastryPanels[i].setBounds(10, y, mainPanel.getWidth() - 20, panelHeight);
            pastryPanels[i].setBackground(Color.decode("#FFF7F8"));
            pastryPanels[i].setLayout(null);
            pastryPanels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            pastryName[i] = new JLabel(names.get(i));
            pastryName[i].setBounds(80, 10, 200, 25);
            pastryName[i].setFont(new Font("Comic Sans", Font.BOLD, 18));
            pastryName[i].setForeground(Color.decode("#A62122"));

            pastryPrice[i] = new JLabel("P" + prices.get(i));
            pastryPrice[i].setBounds(300, 10, 100, 25);
            pastryPrice[i].setFont(new Font("Comic Sans", Font.BOLD, 18));
            pastryPrice[i].setForeground(Color.decode("#A62122"));

            pastryStatus[i] = new JLabel();
            String statusMsg = statuses.get(i).equalsIgnoreCase("Available")
                    ? "<html><span style='color: #00FF00;'>Available</span></html>"
                    : "<html><span style='color: red;'>Out of Stock</span></html>";
            pastryStatus[i].setText(statusMsg);
            pastryStatus[i].setBounds(80, 40, 150, 20);

            if (i < 5) {
                pastryPanels[i].add(imagePanel[i]);
            }

            pastryPanels[i].add(pastryName[i]);
            pastryPanels[i].add(pastryPrice[i]);
            pastryPanels[i].add(pastryStatus[i]);

            int index = i;
            pastryPanels[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (statuses.get(index).equalsIgnoreCase("Available")) {
                        PastryDetails.addPastry(names.get(index), prices.get(index));
                    } else {
                        JOptionPane.showMessageDialog(null, names.get(index) + " is not available.", "Unavailable", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            mainPanel.add(pastryPanels[i]);
        }
    }
}
