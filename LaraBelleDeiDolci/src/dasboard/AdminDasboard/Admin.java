package dasboard.AdminDasboard;
import javax.swing.*;
import CreateComponents.CreateComponents;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends JPanel {
    private JTextField nameField;
    private JComboBox<String> statusComboBox;
    private DefaultListModel<String> listModel;
    private JList<String> pastryList;
    private List<String> pastries;

    public Admin(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(Color.decode("#FFF7F8"));

        JPanel logoPanel = CreateComponents.ImagePanel("Images\\hello sweetiePie.jpg", 0, 0, 1000, 800);

        JLabel titleLabel = new JLabel("Edit Pastries", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(350, 10, 300, 30);
        add(titleLabel);

        int contentWidth = 600;
        int contentHeight = 500;
        int xPosition = (1000 - contentWidth) / 2;
        int yPosition = (800 - contentHeight) / 2;

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(xPosition + 100, yPosition + 60, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(xPosition + 200, yPosition + 60, 200, 25);
        add(nameField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(xPosition + 100, yPosition + 100, 100, 25);
        add(statusLabel);

        statusComboBox = new JComboBox<>(new String[]{"Available", "Not Available"});
        statusComboBox.setBounds(xPosition + 200, yPosition + 100, 200, 25);
        add(statusComboBox);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(xPosition + 100, yPosition + 150, 100, 30);
        add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(xPosition + 210, yPosition + 150, 100, 30);
        add(deleteButton);

        JButton returnButton = new JButton("Return");
        returnButton.setBounds(xPosition + 320, yPosition + 150, 100, 30);
        add(returnButton);

        pastryList = new JList<>();
        listModel = new DefaultListModel<>();
        pastryList.setModel(listModel);
        pastryList.setBounds(xPosition + 100, yPosition + 200, 400, 300);
        add(new JScrollPane(pastryList));

        pastries = new ArrayList<>();
        loadPastries();

        updateButton.addActionListener(e -> updatePastry());
        deleteButton.addActionListener(e -> deletePastry());
        returnButton.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

        pastryList.addListSelectionListener(e -> loadSelectedPastry());
        add(logoPanel);
    }

    private void loadPastries() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pasties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pastries.add(line);
                listModel.addElement(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshPastryList() {
        listModel.clear();
        pastries.clear();
        loadPastries();
    }
    
    private void updatePastry() {
        String name = nameField.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();
    
        if (!name.isEmpty() && status != null) {
            boolean updated = false;
            for (int i = 0; i < pastries.size(); i++) {
                String[] parts = pastries.get(i).split(",");
                if (parts[0].equalsIgnoreCase(name)) {
                    String updatedEntry = parts[0] + "," + parts[1] + "," + status;
                    pastries.set(i, updatedEntry);
                    updated = true;
                    break;
                }
            }
            if (updated) {
                saveToFile();
                refreshPastryList();
                JOptionPane.showMessageDialog(this, "Pastry updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Pastry not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a pastry name and select a status.");
        }
    }
    
    private void deletePastry() {
        int selectedIndex = pastryList.getSelectedIndex();
        if (selectedIndex != -1) {
            pastries.remove(selectedIndex);
            saveToFile();
            refreshPastryList();
            JOptionPane.showMessageDialog(this, "Pastry deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a pastry to delete.");
        }
    }
    

    private void loadSelectedPastry() {
        int selectedIndex = pastryList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = pastryList.getSelectedValue();
            String[] parts = selectedValue.split(",");
            nameField.setText(parts[0]);
            statusComboBox.setSelectedItem(parts[2]);
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pasties.txt"))) {
            for (String pastry : pastries) {
                writer.write(pastry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
