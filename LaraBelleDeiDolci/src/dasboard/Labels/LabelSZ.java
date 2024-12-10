package dasboard.Labels;
import javax.swing.*;

import CreateComponents.CreateComponents;

import java.awt.*;

public class LabelSZ {
    public static JPanel labels(){

        JPanel dashPanel = new JPanel();
        dashPanel.setBounds(35,400,500,200);
        dashPanel.setBackground(Color.decode("#FF5E7A"));
        dashPanel.setLayout(null);

        JLabel Message1,Message2,Message3,Message4;

       
        String Message1Text = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Sweeten Your Day with Belle Dei Dolci!</div></html>";
        Message1 = createLabel(0,80,350,30,Message1Text);
        dashPanel.add(Message1);

        String Message2Text = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Where Every Bite Feels Like Heaven!</div></html>";
        Message2 = createLabel(0,100,350,30,Message2Text);
        dashPanel.add(Message2);

        String Message3Text = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Delicious Moments, Freshly Baked Just for You!</div></html>";
        Message3 = createLabel(0,120,350,30,Message3Text);
        dashPanel.add(Message3);

        String Message4Text = "<html><div style='text-align: center;'><span style='color: #FF91A4; font-size: 14px;'>❤️</span>Let the Sweetness Begin with Belle Dei Dolci!</div></html>";
        Message4 = createLabel(0,140,300,30,Message4Text);
        dashPanel.add(Message4);

        

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

