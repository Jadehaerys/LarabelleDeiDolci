import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import dasboard.Dashboard;

public class App {
    public static void main(String[] args) {
    JFrame frame = new JFrame("Belle Dei Dolci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); 
        ImageIcon img = new ImageIcon("Images\\Lorebelle-removebg-preview.png");
        frame.setIconImage(img.getImage());     
        Dashboard dashboard = new Dashboard(); 
        frame.setContentPane(dashboard.getPanel());
        frame.setVisible(true);


    }
}