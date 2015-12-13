/* AboutPanel.java
 CS230 Assignment 5 
 Written by: Mai Li Goodman and Whitney Fahnbulleh
 Modified date: 10/21/2015
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartPanel extends JPanel{
   //private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBackground = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelStart = new JPanel();
    private JButton infoButton, startButton;
    public StartPanel()
    {
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 600, 400);
        //panelBackground.setBackground(Color.BLUE);
        ImageIcon background = new ImageIcon("images/start.jpg"); 
        //creates a label and sets the image to it
        JLabel bthumb = new JLabel();
        bthumb.setIcon(background);
        
//        ImageIcon info = new ImageIcon("info.png"); 
//        //creates a label and sets the image to it
//        JLabel ithumb = new JLabel();
//        ithumb.setIcon(info);
//        
//        
//        ImageIcon start = new ImageIcon("startGame.png"); 
//        //creates a label and sets the image to it
//        JLabel sthumb = new JLabel();
//        sthumb.setIcon(start);
        
        panelBackground.add(bthumb);
        
        panelBackground.setBounds(0, 0, 1345, 1095);
        panelBackground.setOpaque(true);
        panelInfo.setBackground(new Color(0,0,0,0));
        panelInfo.setBounds(210, 614, 200, 300);
        panelInfo.setOpaque(false);
        BufferedImage infoButtonIcon=null;
        BufferedImage startButtonIcon=null;
        try{
        infoButtonIcon = ImageIO.read(new File("images/info.png"));
        startButtonIcon = ImageIO.read(new File("images/startGame.png"));
        }catch(IOException e){
         System.out.println("yolo");
        }
        infoButton = new JButton(new ImageIcon(infoButtonIcon));
        infoButton.setBorder(BorderFactory.createEmptyBorder());
        infoButton.setContentAreaFilled(false);
        infoButton.setBorderPainted(false);
        infoButton.setFocusPainted( false );
        infoButton.setOpaque( false );
        infoButton.addActionListener(new ButtonListener());
        panelInfo.add(infoButton);
        
        panelStart.setBackground(new Color(0,0,0,0));
        panelStart.setBounds(630, 590, 350, 325);
        panelStart.setOpaque(false);
        startButton = new JButton(new ImageIcon(startButtonIcon));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted( false );
        startButton.setOpaque( false );
        startButton.addActionListener(new ButtonListener());
        panelStart.add(startButton);
        
        
        
        lpane.add(panelBackground, new Integer(0), 0);
        lpane.add(panelInfo, new Integer(1), 0);
        lpane.add(panelStart, new Integer(1), 0);
    }
private class ButtonListener implements ActionListener{
    
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == infoButton) {
        System.out.println("info button press"); 
        InfoPanel infoPanel = new InfoPanel();
        add(infoPanel);
        validate();
        repaint();
        remove(lpane);
      }
       if (event.getSource() == startButton)  {
         ChoicePanel choicePanel = new ChoicePanel();
         add(choicePanel);
         validate();
         repaint();
         remove(lpane);
     }
      
    }
}
}