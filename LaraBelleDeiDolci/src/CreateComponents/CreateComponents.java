package CreateComponents;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;

public class CreateComponents {
        public static JButton createRoundedButton(String text, int x, int y, int width, int height, int arcWidth, int arcHeight, Color foregroundColor, Color backgroundColor){
        JButton button = new JButton()
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Dimension arcs = new Dimension(arcWidth, arcHeight);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
            }
        };


        button.setText(text);
        button.setBounds(x, y, width, height);
        button.setOpaque(false);
        button.setForeground(foregroundColor);
        button.setBackground(backgroundColor);
        button.setLayout(null);

        return button;
    }

         public static JPanel ImagePanel(String imagePath, int x, int y, int w, int h)
            {
        
                JPanel panel = new JPanel(){
                    Image image;
                    {
                        
                        try{
                            image = ImageIO.read(new File(imagePath));
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    protected void paintComponent(Graphics graphics)
                    {
                        super.paintComponent(graphics);
                        if(image!= null)
                        {
                            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };

                panel.setBounds(x,y,w,h);
                panel.setOpaque(false);
                return panel;
            }

        public static JTextField TextField(int x, int y, int w, int h, int fr, int fg, int fb, String fontStyle, int fontWeight, int fontSize, int br, int bg, int bb){
        JTextField textField = new JTextField();
        textField.setBounds(x,y,w,h);
        textField.setForeground(new Color(fr,fg,fb));
        textField.setFont(new Font(fontStyle, fontWeight, fontSize));
        textField.setBackground(new Color(br,bg,bb));
        return textField;
        }

        public static JPasswordField PasswordField(int x, int y, int w, int h, int fr, int fg, int fb, String fontStyle, int fontWeight, int fontSize, int br, int bg, int bb){
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x,y,w,h);
        passwordField.setForeground(new Color(fr,fg,fb));
        passwordField.setFont(new Font(fontStyle, fontWeight, fontSize));
        passwordField.setBackground(new Color(br,bg,bb));
        return passwordField;
        }


        public static JPanel StatusBar(int score, int x,int y){
            JPanel outline, statusBar;
            int width = score == 0 ? 10 : 170/10*score;
            statusBar = Panel(3, 2, width , 10, 86, 201, 170, 255, 86, 201, 170, 255, 25);
            outline = Panel(x, y, 180, 15, 255, 255, 255, 255, 255, 255, 255, 255, 25);

            outline.add(statusBar);

            return outline;
        }
        public static JPanel Modal(int score){
            JPanel modal;
            JLabel title, desc;
            title = Label(160,0, 300, 100, "Your Score:", "Arial", Font.PLAIN, 30, 0,0,0,255,255,255);
            desc = Label(150,110, 300, 100, score+"/10", "Arial", Font.PLAIN, 90, 0,0,0,255,255,255);

            modal = Panel(0, 0, 500, 300, 33, 40, 32, 255, 33, 40, 32, 255, 0);
            modal.setLayout(null);

            modal.setLocation(500, 260);

            modal.add(title);
            modal.add(desc);

            return modal;
        }

        public static JLabel Label(int x, int y, int w, int h, String text , String fontFamily, int fontWeight, int fontSize, int r, int g, int b, int fr, int fg, int fb){
        JLabel label = new JLabel();
        label.setBounds(x, y,w, h);
        label.setBackground(new Color(r, g, b));
        label.setForeground(new Color(fr, fg, fb));
        label.setFont(new Font(fontFamily, fontWeight, fontSize));
        label.setText(text);
        return label;
    }

        public static JButton Button(int x, int y, int w, int h, String text , String fontFamily, int fontWeight, int fontSize, int r, int g, int b, int fr, int fg, int fb){
        JButton button = new JButton(text);
        button.setBounds(x, y,w, h);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        button.setBackground(new Color(r, g, b));
        button.setForeground(new Color(fr, fg, fb));
        button.setFont(new Font(fontFamily, fontWeight, fontSize));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    } 

    public static JPanel Panel(int x, int y, int width, int height, int startR, int startG, int startB, int startA, int endR, int endG, int endB, int endA, int cornerRadius)
    {
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                super.setBackground(null);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius,
                cornerRadius);

                GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(startR, startG, startB, startA), getWidth(), getHeight(), new Color(endR, endG, endB, endA));
                
                g2d.setPaint(gradientPaint);
                g2d.fill(roundedRectangle);

                g2d.draw(roundedRectangle);
                g2d.dispose();
            }
        };
        panel.setBounds(x, y, width, height);
        panel.setLayout(null);
        panel.setOpaque(false);

        return panel;
    }
}
