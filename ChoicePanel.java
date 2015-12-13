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

public class ChoicePanel extends JPanel{
  //private JFrame frame = new JFrame();
  private JLayeredPane lpane = new JLayeredPane();
  private JPanel panelBackground = new JPanel();
  private JPanel panelInorder, panelPost, panelLevel, panelPre, panelSurprise;
  // private JPanel panelStart = new JPanel();
  private JButton inorderButton, postButton, levelButton, preButton, surpriseButton;
  public ChoicePanel()
  {
    setLayout(new BorderLayout());
    add(lpane, BorderLayout.CENTER);
    lpane.setBounds(0, 0, 600, 400);
    //panelBackground.setBackground(Color.BLUE);
    ImageIcon background = new ImageIcon("images/choice.jpg"); 
    //creates a label and sets the image to it
    JLabel bthumb = new JLabel();
    bthumb.setIcon(background);
    
    
    panelBackground.add(bthumb);
    
    panelBackground.setBounds(0, 0, 1345, 1095);
    panelBackground.setOpaque(true);
    
    panelInorder = new JPanel();
    panelInorder.setBackground(new Color(0,0,0,0));
    panelInorder.setBounds(85, 415, 450, 150);
    panelInorder.setOpaque(false);
    
    
    
    BufferedImage postButtonIcon=null;
    BufferedImage inorderButtonIcon=null;
    BufferedImage levelButtonIcon=null;
    BufferedImage preButtonIcon=null;
    BufferedImage surpriseButtonIcon=null;
    
    try{
      postButtonIcon = ImageIO.read(new File("images/post.png"));
      inorderButtonIcon = ImageIO.read(new File("images/inorder.png"));
      levelButtonIcon = ImageIO.read(new File("images/level.png"));
      preButtonIcon = ImageIO.read(new File("images/pre.png"));
      surpriseButtonIcon = ImageIO.read(new File("images/surprise.png"));
    }catch(IOException e){
      System.out.println("yolo");
    }
    inorderButton = new JButton(new ImageIcon(inorderButtonIcon));
    inorderButton.setBorder(BorderFactory.createEmptyBorder());
    inorderButton.setContentAreaFilled(false);
    inorderButton.setBorderPainted(false);
    inorderButton.setFocusPainted( false );
    inorderButton.setOpaque( false );
    inorderButton.addActionListener(new ButtonListener());
    panelInorder.add(inorderButton);
    
    
    panelPost = new JPanel();
    panelPost.setBounds(612, 415, 450, 150);
    panelPost.setOpaque(false);
    
    postButton = new JButton(new ImageIcon(postButtonIcon));
    postButton.setBorder(BorderFactory.createEmptyBorder());
    postButton.setContentAreaFilled(false);
    postButton.setBorderPainted(false);
    postButton.setFocusPainted( false );
    postButton.setOpaque( false );
    postButton.addActionListener(new ButtonListener());
    
    panelPost.add(postButton);
    
    
    
    panelLevel = new JPanel();
    panelLevel.setBounds(612, 600, 450, 150);
    panelLevel.setOpaque(false);
    
    levelButton = new JButton(new ImageIcon(levelButtonIcon));
    levelButton.setBorder(BorderFactory.createEmptyBorder());
    levelButton.setContentAreaFilled(false);
    levelButton.setBorderPainted(false);
    levelButton.setFocusPainted( false );
    levelButton.setOpaque( false );
    levelButton.addActionListener(new ButtonListener());
    
    panelLevel.add(levelButton);
    
    panelPre = new JPanel();
    panelPre.setBounds(85, 600, 450, 150);
    panelPre.setOpaque(false);
    
    preButton = new JButton(new ImageIcon(preButtonIcon));
    preButton.setBorder(BorderFactory.createEmptyBorder());
    preButton.setContentAreaFilled(false);
    preButton.setBorderPainted(false);
    preButton.setFocusPainted( false );
    preButton.setOpaque( false );
    preButton.addActionListener(new ButtonListener());
    
    panelPre.add(preButton);
    
    
    panelSurprise = new JPanel();
    panelSurprise.setBounds(340, 778, 450, 150);
    panelSurprise.setOpaque(false);
    
    surpriseButton = new JButton(new ImageIcon(surpriseButtonIcon));
    surpriseButton.setBorder(BorderFactory.createEmptyBorder());
    surpriseButton.setContentAreaFilled(false);
    surpriseButton.setBorderPainted(false);
    surpriseButton.setFocusPainted( false );
    surpriseButton.setOpaque( false );
    surpriseButton.addActionListener(new ButtonListener());
    
    panelSurprise.add(surpriseButton);
    
    
    lpane.add(panelBackground, new Integer(0), 0);
    lpane.add(panelInorder, new Integer(1), 0);
    lpane.add(panelPost, new Integer(1), 0);
    lpane.add(panelLevel, new Integer(1), 0);
    lpane.add(panelPre, new Integer(1), 0);
    lpane.add(panelSurprise, new Integer(1), 0);
  }
  private class ButtonListener implements ActionListener{
    
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == inorderButton) {
        System.out.println("inorder button press");
        TreePanel treePanel = new TreePanel(0);
        add(treePanel);
        validate();
        repaint();
        remove(lpane);
        
      }
      if (event.getSource() == postButton)  {
        System.out.println("post button press");
        TreePanel treePanel = new TreePanel(2);
        add(treePanel);
        validate();
        repaint();
        remove(lpane);
      }
      if (event.getSource() == levelButton)  {
        System.out.println("level button press");
        TreePanel treePanel = new TreePanel(3);
        add(treePanel);
        validate();
        repaint();
        remove(lpane);
      }
      if (event.getSource() == preButton)  {
        System.out.println("pre button press");
        TreePanel treePanel = new TreePanel(1);
        add(treePanel);
        validate();
        repaint();
        remove(lpane);
      }
      if (event.getSource() == surpriseButton)  {
        System.out.println("surprise button press");
        TreePanel treePanel = new TreePanel(4);
        add(treePanel);
        validate();
        repaint();
        remove(lpane);
      }
    }
  }
}