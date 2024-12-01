package dasboard.PastriesDetails;
import CreateComponents.CreateComponents;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PastryDetails {
    private static JPanel pastryPanel = new JPanel();
    private static DefaultListModel<String> selectedPastriesModel = new DefaultListModel<>();
    private static JList<String> selectedPastriesList = new JList<>(selectedPastriesModel);
    private static JLabel subtotalLabel = new JLabel("Subtotal: P0.0");
    private static double subtotal = 0.0;
    private static JTextField quantityField = new JTextField("1");
    private static String currentSelectedItem = null;
    private static HashMap<String, Double> itemPrices = new HashMap<>();
    private static HashMap<String, Integer> itemQuantities = new HashMap<>();

    public static JPanel getPastriesDetails() {
        pastryPanel.setLayout(null);
        pastryPanel.setBounds(586, 30, 300, 600);
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

        JButton addButton = createRoundedButton("Images/add.png");
        addButton.setBounds(20, 360, 120, 50);
        addButton.setBackground(Color.decode("#FF5E7A"));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);
        addButton.setVerticalAlignment(SwingConstants.CENTER);
        addButton.setMargin(new Insets(10, 20, 10, 20));
        addButton.addActionListener(new AddQuantityListener());
        pastryPanel.add(addButton);

        JButton subtractButton = createRoundedButton("Images/subtracting-button.png");
        subtractButton.setBounds(160, 360, 120, 50);
        subtractButton.setBackground(Color.decode("#FF5E7A"));
        subtractButton.setHorizontalAlignment(SwingConstants.CENTER);
        subtractButton.setVerticalAlignment(SwingConstants.CENTER);
        subtractButton.setMargin(new Insets(10, 20, 10, 20));
        subtractButton.addActionListener(new SubtractQuantityListener());
        pastryPanel.add(subtractButton);

        JButton checkoutButton = new JButton("CHECKOUT");
        checkoutButton.setBounds(20, 420, 260, 50);
        checkoutButton.setBackground(Color.decode("#FF5E7A"));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Serif", Font.BOLD, 20));
        checkoutButton.setBorder(BorderFactory.createEmptyBorder());
        checkoutButton.setFocusPainted(false);
        checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkoutButton.addActionListener(new CheckoutListener());
        pastryPanel.add(checkoutButton);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(20, 480, 260, 50);
        removeButton.setBackground(Color.decode("#FF5E7A"));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Serif", Font.BOLD, 20));
        removeButton.setBorder(BorderFactory.createEmptyBorder());
        removeButton.setFocusPainted(false);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(new RemoveSelectedListener());
        pastryPanel.add(removeButton);

        return pastryPanel;
    }

    public static void addPastry(String name, double price) {
        if (itemQuantities.containsKey(name)) {
            itemQuantities.put(name, itemQuantities.get(name) + 1);
        } else {
            itemQuantities.put(name, 1);
            itemPrices.put(name, price);
        }
        updateList();
    }

    private static void updateList() {
        selectedPastriesModel.clear();
        subtotal = 0.0;
        for (String name : itemQuantities.keySet()) {
            int quantity = itemQuantities.get(name);
            double price = itemPrices.get(name);
            subtotal += quantity * price;
            selectedPastriesModel.addElement(name + " x" + quantity + " - P" + (quantity * price));
        }
        updateSubtotal();
    }

    private static void updateSubtotal() {
        subtotalLabel.setText("Subtotal: P" + subtotal);
    }

    private static JButton createRoundedButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JButton button = new JButton(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(getBackground().darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setOpaque(true);
        button.setBackground(Color.decode("#FF5E7A"));
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return button;
    }

    private static class AddQuantityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = selectedPastriesList.getSelectedValue();
            if (selectedItem != null) {
                String pastryName = selectedItem.split(" x")[0];
                itemQuantities.put(pastryName, itemQuantities.getOrDefault(pastryName, 0) + 1);
                updateList();
            }
        }
    }

    private static class SubtractQuantityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = selectedPastriesList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = selectedPastriesList.getSelectedValue().split(" x")[0];
                int currentQuantity = itemQuantities.get(selectedItem);
                if (currentQuantity > 1) {
                    itemQuantities.put(selectedItem, currentQuantity - 1);
                } else {
                    itemQuantities.remove(selectedItem);
                    itemPrices.remove(selectedItem);
                }
                updateList();
            }
        }
    }

    private static class CheckoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!itemQuantities.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Total: P" + subtotal + "\nThank you for your purchase!", "Checkout",JOptionPane.INFORMATION_MESSAGE);
                itemQuantities.clear();
                itemPrices.clear();
                updateList();
            } else {
                JOptionPane.showMessageDialog(null, "Your cart is empty!", "Checkout", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private static class RemoveSelectedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = selectedPastriesList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = selectedPastriesList.getSelectedValue().split(" x")[0];
                itemQuantities.remove(selectedItem);
                itemPrices.remove(selectedItem);
                updateList();
            }
        }
    }
}
