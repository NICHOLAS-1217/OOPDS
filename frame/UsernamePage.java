package frame;

import javax.swing.*;
import java.awt.*;

public class UsernamePage extends JFrame{

    public UsernamePage(){
        ImageIcon icon = new ImageIcon("img/GoBoomIcon.png");
        ImageIcon backGround = new ImageIcon("img/background.jpg");

        JLabel label = new JLabel();
        label.setIcon(backGround);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,0,0);

        this.add(layeredPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(750,750));
        this.setVisible(true);
        this.setTitle("GO BOOM"); 
        this.setIconImage(icon.getImage());
        this.setResizable(false); 
        this.add(label);
        this.pack();
    }
}
