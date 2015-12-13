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

public class InfoPanel extends JPanel{
  //private JFrame frame = new JFrame();
  private JLayeredPane lpane = new JLayeredPane();
  private JPanel panelBackground = new JPanel();
  private JPanel panelStart = new JPanel();
  private JButton startButton;
  public InfoPanel()
  {
    setLayout(new BorderLayout());
    add(lpane, BorderLayout.CENTER);
    lpane.setBounds(0, 0, 600, 400);
    //panelBackground.setBackground(Color.BLUE);
    ImageIcon background = new ImageIcon("images\infoBackground.jpg"); 
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
    BufferedImage startButtonIcon=null;
    try{
      startButtonIcon = ImageIO.read(new File("images\startGame.png"));
    }catch(IOException e){
      System.out.println("yolo");
    }
    
    panelStart.setBackground(new Color(0,0,0,0));
    panelStart.setBounds(426, 630, 350, 325); //x y w h
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
    lpane.add(panelStart, new Integer(1), 0);
  }
  private class ButtonListener implements ActionListener{
    
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton)  {
        System.out.println("start button press");
      }
      
    }
  }
}