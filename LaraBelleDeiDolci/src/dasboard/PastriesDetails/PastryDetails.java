package dasboard.PastriesDetails;
import CreateComponents.CreateComponents;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

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
            JFrame checkoutFrame = new JFrame("Checkout");
            checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            checkoutFrame.setSize(500, 400);
            checkoutFrame.setLayout(new BorderLayout(10, 10));
            checkoutFrame.setBackground(Color.decode("#FF91A4"));

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());
            formPanel.setBackground(Color.decode("#FF91A4"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);

            JLabel header = new JLabel("Checkout Details", JLabel.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 20));
            header.setOpaque(true);
            header.setBackground(Color.decode("#FF2B50"));
            header.setForeground(Color.WHITE);
            header.setPreferredSize(new Dimension(500, 50));
            checkoutFrame.add(header, BorderLayout.NORTH);

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(20);
            JLabel addressLabel = new JLabel("Address:");
            JTextArea addressField = new JTextArea(3, 20);
            addressField.setLineWrap(true);
            addressField.setWrapStyleWord(true);
            JScrollPane addressScrollPane = new JScrollPane(addressField);

            JLabel contactLabel = new JLabel("Contact Number:");
            JTextField contactField = new JTextField(15);

            JLabel paymentLabel = new JLabel("Payment Method:");
            JComboBox<String> paymentDropdown = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "Mobile Payment"});

            JButton checkoutButton = new JButton("Submit & View Receipt");
            checkoutButton.setBackground(Color.decode("#FF2B50"));
            checkoutButton.setForeground(Color.WHITE);

            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            formPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(addressLabel, gbc);
            gbc.gridx = 1;
            formPanel.add(addressScrollPane, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(contactLabel, gbc);
            gbc.gridx = 1;
            formPanel.add(contactField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            formPanel.add(paymentLabel, gbc);
            gbc.gridx = 1;
            formPanel.add(paymentDropdown, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            formPanel.add(checkoutButton, gbc);

            checkoutFrame.add(formPanel, BorderLayout.CENTER);
            checkoutFrame.setLocationRelativeTo(null);
            checkoutFrame.setVisible(true);
            checkoutFrame.setBackground(Color.decode("#FF91A4"));
            ImageIcon checkoutLogo = new ImageIcon("Images\\BelleDeiDolciLogo.png");
            checkoutFrame.setIconImage(checkoutLogo.getImage());   
            checkoutButton.addActionListener(event -> {
                try {
                    String name = nameField.getText().trim();
                    String address = addressField.getText().trim();
                    String contact = contactField.getText().trim();
                    String paymentMethod = (String) paymentDropdown.getSelectedItem();
            
                    if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
                        throw new Exception("Please fill out all fields!");
                    }
            
                    if (contact.length() != 11 || !contact.matches("\\d{11}")) {
                        throw new Exception("Contact number must be 11 digits and contain only numbers.");
                    }
            
                    checkoutFrame.dispose();
                    saveReceiptToFile(name, address, contact, paymentMethod);
                    showReceiptFrame(name, address, contact, paymentMethod);
            
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            checkoutFrame,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Your cart is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveReceiptToFile(String name, String address, String contact, String paymentMethod) {
        try {
            File receiptFile = new File("receipt.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile, true));
    
            String currentDate = java.time.LocalDate.now().toString();
    
            writer.write("====================================\n");
            writer.write("Receipt\n");
            writer.write("====================================\n");
            writer.write("Date: " + currentDate + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Contact: " + contact + "\n");
            writer.write("Payment Method: " + paymentMethod + "\n");
    
            writer.write("\nItems Purchased:\n");
            Object[] itemNames = itemQuantities.keySet().toArray();
            for (int i = 0; i < itemNames.length; i++) {
                String itemName = (String) itemNames[i];
                int quantity = itemQuantities.get(itemName);
                double itemPrice = itemPrices.get(itemName);
                double totalItemPrice = itemPrice * quantity;
                writer.write(itemName + " x" + quantity + " - P" + totalItemPrice + "\n");
            }
    
            writer.write("\nTotal: P" + subtotal + "\n");
            writer.write("====================================\n\n");
    
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving receipt: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReceiptFrame(String name, String address, String contact, String paymentMethod) {
        JFrame receiptFrame = new JFrame("Receipt");
        receiptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receiptFrame.setSize(500, 600);
        receiptFrame.setLayout(null);
        ImageIcon receiptLogo = new ImageIcon("Images\\BelleDeiDolciLogo.png");
        receiptFrame.setIconImage(receiptLogo.getImage());
    
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 500, 700);
        receiptFrame.add(layeredPane);
    
        JPanel receiptBackground = CreateComponents.ImagePanel("Images\\receit logo.jpg", 0, 0, 500, 600);
        receiptBackground.setBounds(0, 0, 500, 600);
        layeredPane.add(receiptBackground, JLayeredPane.DEFAULT_LAYER);
    
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new GridBagLayout());
        receiptPanel.setOpaque(false);
        receiptPanel.setBounds(20, 10, 460, 600);
        receiptPanel.setBackground(new Color(255, 145, 164, 200)); // Semi-transparent pink background for better readability
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        layeredPane.add(receiptPanel, JLayeredPane.PALETTE_LAYER);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
    
    
        String currentDate = java.time.LocalDate.now().toString();
        JLabel dateLabel = new JLabel("Date: " + currentDate, JLabel.CENTER);
        dateLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        receiptPanel.add(dateLabel, gbc);
    
        JLabel nameLabel = new JLabel("Name: " + name, JLabel.CENTER);
        nameLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridy = 2;
        receiptPanel.add(nameLabel, gbc);
    
        JLabel addressLabel = new JLabel("<html>Address: " + address.replace("\n", "<br>") + "</html>", JLabel.CENTER);
        addressLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridy = 3;
        receiptPanel.add(addressLabel, gbc);
    
        JLabel contactLabel = new JLabel("Contact Number: " + contact, JLabel.CENTER);
        contactLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridy = 4;
        receiptPanel.add(contactLabel, gbc);
    
        JLabel paymentLabel = new JLabel("Payment Method: " + paymentMethod, JLabel.CENTER);
        paymentLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridy = 5;
        receiptPanel.add(paymentLabel, gbc);
    
        JLabel itemsLabel = new JLabel("Items Purchased:", JLabel.CENTER);
        itemsLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        gbc.gridy = 6;
        receiptPanel.add(itemsLabel, gbc);
    
        Object[] itemNames = itemQuantities.keySet().toArray();
        for (int i = 0; i < itemNames.length; i++) {
            String itemName = (String) itemNames[i];
            int quantity = itemQuantities.get(itemName);
            double itemPrice = itemPrices.get(itemName);
            double totalItemPrice = itemPrice * quantity;
            JLabel itemLabel = new JLabel(itemName + " (" + quantity + ") - P" + totalItemPrice, JLabel.CENTER);
            itemLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
            gbc.gridy++;
            receiptPanel.add(itemLabel, gbc);
        }
    
        JLabel totalLabel = new JLabel("Total: P" + subtotal, JLabel.CENTER);
        totalLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
        gbc.gridy++;
        receiptPanel.add(totalLabel, gbc);
    
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.decode("#FF2B50"));
        closeButton.setForeground(Color.WHITE);
        gbc.gridy++;
        receiptPanel.add(closeButton, gbc);
    
        receiptFrame.setLocationRelativeTo(null);
        receiptFrame.setVisible(true);
    
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                receiptFrame.dispose();
            }
        });
    
        itemQuantities.clear();
        itemPrices.clear();
        updateList();
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
