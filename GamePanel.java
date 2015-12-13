/* GamePanel.java
 CS230 Assignment 5 
 Written by: Whitney Fahnbulleh
 Modified date: 10/21/2015
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel{
  
  
   public static void main(String[] args)throws IOException{

    JFrame frame = new JFrame("Deku Tree Traversal");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closed when you click close
    frame.setPreferredSize(new Dimension(1356, 1100)); //wxh
    //JTabbedPane tp = new JTabbedPane();//creating a tabbed Pane
    //tp.setBackground(Color.decode("#ffffff"));//sets the background of the tabbed pane to white
    //creates the tabs for the respective panels
    //tp.addTab ("Home", new StartPanel());
    //frame.add(new ChoicePanel());
    frame.add(new StartPanel());
    //tp.add( new InfoPanel());
    //tp.add( new StartPanel());
    //frame.getContentPane().add(tp);
    //tp.setVisible(false);

    frame.pack();
    frame.setVisible(true);
}
}