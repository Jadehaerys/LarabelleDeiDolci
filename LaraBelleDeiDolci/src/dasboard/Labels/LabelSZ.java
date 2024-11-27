package dasboard.Labels;
import javax.swing.*;
import java.awt.*;

public class LabelSZ {
    public static JPanel labels(){

        JPanel dashPanel = new JPanel();
        dashPanel.setBounds(35,400,200,160);
        dashPanel.setBackground(Color.decode("#FF2B50"));
        dashPanel.setLayout(null);

        JLabel Available, NotAvailable;

        String AvailableText = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Larabelle Dei Dolci</div></html>";
        Available = createLabel(0,80,180,30,AvailableText);
        dashPanel.add(Available);

        String NotAvailableText = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Ayn Bayot</div></html>";
        NotAvailable = createLabel(0,100,180,30,NotAvailableText);
        dashPanel.add(NotAvailable);

        return dashPanel;

    }

    public static JLabel createLabel(int x, int y, int w, int h, String text){
        JLabel label = new JLabel();
        label.setText(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.BOLD, 10)); 
        label.setBounds(x,y,w,h);
        return label;
    }
}

