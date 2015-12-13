import javax.swing.*;  
import java.awt.Dimension;




public class GUI  extends JPanel{



  public static void main(String[] args) {  
    JFrame f=new JFrame();
    f.setPreferredSize(new Dimension(1356, 1100)); //wxh
    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//    TreePanel tree=new TreePanel();
//    tree.setLayout(null);
//    f.getContentPane().add(tree);
//    f.setVisible(true);
    f.add(new TreePanel(1));
    //tp.add( new InfoPanel());
    //tp.add( new StartPanel());
    //frame.getContentPane().add(tp);
    //tp.setVisible(false);

    f.pack();
    f.setVisible(true);
  }
          
}
