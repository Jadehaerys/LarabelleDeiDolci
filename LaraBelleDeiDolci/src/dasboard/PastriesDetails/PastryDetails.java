package dasboard.PastriesDetails;
import CreateComponents.CreateComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PastryDetails {
    private static JPanel pastryPanel = new JPanel();
    private static DefaultListModel<String> selectedPastriesModel = new DefaultListModel<>();
    private static JList<String> selectedPastriesList = new JList<>(selectedPastriesModel);
    private static JLabel subtotalLabel = new JLabel("Subtotal: P0.0");
    private static double subtotal = 0.0;

    public static JPanel getPastriesDetails() {
        pastryPanel.setLayout(null);
        pastryPanel.setBounds(586, 30, 300, 500);
        pastryPanel.setBackground(Color.decode("#FFC4CE"));

 
        JScrollPane scrollPane = new JScrollPane(selectedPastriesList);
        scrollPane.setBounds(20, 20, 260, 300);
        selectedPastriesList.setFont(new Font("Arial", Font.PLAIN, 18));
        selectedPastriesList.setBackground(Color.WHITE);
        selectedPastriesList.setForeground(Color.BLACK);
        pastryPanel.add(scrollPane);

       
        subtotalLabel.setFont(new Font("Inter", Font.BOLD, 25));
        subtotalLabel.setForeground(Color.WHITE);
        subtotalLabel.setBounds(20, 330, 260, 30);
        pastryPanel.add(subtotalLabel);

        JButton checkoutButton = new JButton("CHECKOUT");
        checkoutButton.setBounds(20, 380, 260, 50);
        checkoutButton.setBackground(Color.decode("#FF5E7A"));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Serif", Font.BOLD, 20));
        checkoutButton.setBorder(BorderFactory.createEmptyBorder());
        checkoutButton.setFocusPainted(false);
        checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });
        pastryPanel.add(checkoutButton);

       
        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(20, 440, 260, 50);
        removeButton.setBackground(Color.decode("#FF5E7A"));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Serif", Font.BOLD, 20));
        removeButton.setBorder(BorderFactory.createEmptyBorder());
        removeButton.setFocusPainted(false);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelected();
            }
        });
        pastryPanel.add(removeButton);

        return pastryPanel;
    }

   
    public static void addPastry(String name, double price) {
        selectedPastriesModel.addElement(name + " - P" + price);
        subtotal += price;
        updateSubtotal();
    }

  
    private static void removeSelected() {
        int selectedIndex = selectedPastriesList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selected = selectedPastriesModel.get(selectedIndex);
            double price = Double.parseDouble(selected.split(" - P")[1]);
            subtotal -= price;
            selectedPastriesModel.remove(selectedIndex);
            updateSubtotal();
        }
    }

    
    private static void checkout() {
        if (!selectedPastriesModel.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Total: P" + subtotal + "\nThank you for your purchase!","Checkout", JOptionPane.INFORMATION_MESSAGE);
            selectedPastriesModel.clear();
            subtotal = 0.0;
            updateSubtotal();
        } else {
            JOptionPane.showMessageDialog(null,"Your cart is empty!", "Checkout", JOptionPane.WARNING_MESSAGE);
        }
    }

  
    private static void updateSubtotal() {
        subtotalLabel.setText("Subtotal: P" + subtotal);
    }
}