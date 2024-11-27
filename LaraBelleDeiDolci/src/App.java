import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import dasboard.Dashboard;

public class App {
    public static void main(String[] args) {
    JFrame frame = new JFrame("Larabelle Dei Dolci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); // Set the frame size to match the panel dimensions
        
        // Initialize the Dashboard
        Dashboard dashboard = new Dashboard();
        
        // Add the main panel of the Dashboard to the JFrame
        frame.setContentPane(dashboard.getPanel());

        // Make the JFrame visible
        frame.setVisible(true);


    }
}