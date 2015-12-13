import javax.swing.*;  
import java.awt.Dimension;




public class GUI {



  public static void main(String[] args) {  
    JFrame f=new JFrame();
    f.setPreferredSize(new Dimension(1356, 1100)); //wxh
    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    TreePanel tree=new TreePanel(1);
    tree.setLayout(null);
    //f.getContentPane().add(tree);
    f.add(tree);
    //tp.add( new InfoPanel());
    //tp.add( new StartPanel());
    //frame.getContentPane().add(tp);
    //tp.setVisible(false);

    f.pack();
    f.setVisible(true);
  }
          
}
