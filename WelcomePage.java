package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener{

    JButton button = new JButton();

    public WelcomePage(){

        ImageIcon icon = new ImageIcon("img/GoBoomIcon.png");
        ImageIcon buttonIcon = new ImageIcon("img/startButton.png");
        ImageIcon backGround = new ImageIcon("img/background.jpg");
        
        button.setIcon(buttonIcon);
        button.setOpaque(true);
        button.setBounds(205,300,405,195);
        button.addActionListener(this);
        button.setFocusable(false);
        
        JLabel label = new JLabel();
        label.setIcon(backGround);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);

        JLabel label1 = new JLabel("WELCOME TO GO BOOM");
        label1.setOpaque(true);
        label1.setBackground(Color.WHITE);
        label1.setBounds(205,100,500,50);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.CENTER);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,0,0);

        layeredPane.add(label1,Integer.valueOf(0));
        layeredPane.add(button,Integer.valueOf(1));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            this.dispose();
            new UsernamePage();
        }
    }
}
