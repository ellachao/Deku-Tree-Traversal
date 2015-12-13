import javax.swing.*;  
import java.awt.Dimension;

public class GUI {
  
  public static void displayFrame(){
    JFrame f=new JFrame();
    f.setSize(new Dimension(1356, 1100));
    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    TreePanel tree=new TreePanel();
    tree.setLayout(null);
    f.getContentPane().add(tree);
    f.setVisible(true);
  }


  public static void main(String[] args) {  
   displayFrame();
  }
          
}
