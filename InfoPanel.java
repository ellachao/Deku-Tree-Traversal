/* CS 230 Final Project
 * Whitney Fahnbulleh, Ella Chao, Sophia Sun
 * InfoPanel Class
 * (primarily written by Whitney)
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Creates a panel with instructions on 
 * how to play the game
 */
public class InfoPanel extends JPanel{
  //private JFrame frame = new JFrame();
  private JLayeredPane lpane = new JLayeredPane();
  private JPanel panelStart, panelBackground;
  private JButton startButton;
 
  public InfoPanel()
  {
    setLayout(new BorderLayout());
    add(lpane, BorderLayout.CENTER);
    lpane.setBounds(0, 0, 600, 400);
    panelBackground = new JPanel();
    panelStart = new JPanel();
    
    //creates a label and sets the image to it
    ImageIcon background = new ImageIcon("images/infoBackground.jpg"); 
    JLabel bthumb = new JLabel();
    bthumb.setIcon(background);
    panelBackground.add(bthumb);
    panelBackground.setBounds(0, 0, 1345, 1095);
    
    
    BufferedImage startButtonIcon=null;
    
    //try to create the images
    try{
      startButtonIcon = ImageIO.read(new File("images/startGame.png"));
    }catch(IOException e){
      System.out.println("Image not Found");
    }
    
    //modifies and adds startbutton to panel
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
    
    
    //adds background and startbuttonpanel to layeredpane
    lpane.add(panelBackground, new Integer(0), 0);
    lpane.add(panelStart, new Integer(1), 0);
  }
  
  //button listener
  private class ButtonListener implements ActionListener{
    
    //adds choicePanel when startbutton is pressed
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton)  {
        System.out.println("start button from info press");
         ChoicePanel choicePanel = new ChoicePanel();
         add(choicePanel);
         validate();
         repaint();
         remove(lpane);
      }
      
    }
  }
}