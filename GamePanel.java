/* CS 230 Final Project
 * Whitney Fahnbulleh, Ella Chao, Sophia Sun
 * GamePanel Class
 * (primarily written by Whitney)
 */


import java.awt.*;
import javax.swing.*;
import java.io.IOException;


/**
 * Creates the main frame of the game
 */
public class GamePanel extends JPanel{
  
  
   public static void main(String[] args)throws IOException{

    JFrame frame = new JFrame("Deku Tree Traversal");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closed when you click close
    frame.setPreferredSize(new Dimension(1356, 1100));
    frame.setBackground(new Color(218,218,218));
    frame.add(new TreePanel(1));
    frame.pack();
    frame.setVisible(true);
}
}