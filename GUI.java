import javax.swing.*;  
import java.awt.Dimension;

public class GUI {
  
  public static void displayFrame(){
    JFrame f=new JFrame();
    f.setSize(new Dimension(700, 600));
    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(new TreePanel());
    f.setVisible(true);
  }


  public static void main(String[] args) {  
   displayFrame();
  }
          
}
