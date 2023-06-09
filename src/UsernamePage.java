
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

        JTextField t1 = new JTextField("enter player name");

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,0,0);
        layeredPane.add(t1, Integer.valueOf(1));

        this.add(layeredPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(750,750));
        this.setVisible(true);
        this.setTitle("GO BOOM"); 
        this.setIconImage(icon.getImage());
        this.setResizable(false); 
        this.add(label);
        this.add(t1);
        this.pack();
    }
}
