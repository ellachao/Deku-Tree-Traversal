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

public class TreePanel extends JPanel{
  private static final int WIDTH=36;
  private static final int LEVELDISTANCE=3;
  private int[][] grid;
  private JLayeredPane lpane = new JLayeredPane();
  private JPanel panelStart = new JPanel();
  private JPanel panelBackground = new JPanel();
  private RandomTree tree;
  private boolean key_right, key_left, key_down, key_up, key_space; // Input booleans
  private int x;
  private int y;
  private int temp;
  
  public TreePanel(int number){
    tree=new RandomTree();
    convertTree(tree.getTree());
    x=16;
    y=32;
    drawTree();
    temp=grid[y][x];
    setBackground(new Color(218,218,218));
    setLayout(new BorderLayout());
    lpane.setBounds(0, 0, 600, 400);
    //panelBackground.setBackground(Color.BLUE);
    ImageIcon background = new ImageIcon("images/infoBackground.jpg"); 
    //creates a label and sets the image to it
    JLabel bthumb = new JLabel();
    bthumb.setIcon(background);
    panelBackground.add(bthumb);
    
    panelBackground.setBounds(-40, 0, 1345, 1095);
    addKeyListener(new KeyInput()); 
    
    //panelStart.setBackground(Color.black);
    //lpane.setBackground(Color.black);
    //lpane.add(panelStart, new Integer(3));
    panelBackground.setOpaque(false);
    lpane.add(panelBackground, new Integer(1), 0);
    add(lpane, BorderLayout.CENTER);
  }
  
  public RandomTree getTree(){
    return tree;
  }
  private void drawTree(){
    String[] images={"rupee1.jpg","rupee2.jpg","rupee3.jpg","rupee4.jpg","rupee5.jpg","rupee6.jpg"};
    for(int y=0;y<WIDTH;y++){
      for(int x=0;x<WIDTH;x++){
        if(grid[y][x]==0){
          JLabel img =new JLabel(new ImageIcon("images/grass.jpg"));
          img.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img);
        }
        else if(grid[y][x]==-1){
          JLabel img2 =new JLabel(new ImageIcon("images/road.jpg"));
          img2.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img2);
        }
        else if(grid[y][x]==-2){
          JLabel makar =new JLabel(new ImageIcon("images/makar.png"));
          makar.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(makar);
        }
        else{
          JLabel img3 =new JLabel(new ImageIcon("images/"+images[grid[y][x]%images.length]));
          img3.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img3);
        }
      }
    }
  }
  
  public int[][] convertTree(int[] intArray){
    //0 for grass; 1 for nodes
    int row=0;
    int index=0;
    int limit=0;
    int power=(int)Math.pow(2,index);
    int segment=WIDTH/(power+1);
    int distance=segment;
    int[][] grid=new int[WIDTH][WIDTH];
    for (int i=0; i<intArray.length;i++){
      if(i>limit){
        index++;
        row+=3;
        power=(int)Math.pow(2,index);
        limit+=power;
        segment=WIDTH/(power+1);
        distance=segment;       
      }
      if (intArray[i]>-1){
        grid[row][distance]=intArray[i];
      }
      distance+=segment;
    }
    fillPath(grid);
    return grid;
  }
  
  public void fillPath(int[][] grid){
    //fills paths with -1
    int pX;
    int pY;
    for(int y=LEVELDISTANCE;y<grid.length;y+=3){
      for(int x=0;x<grid[y].length;x++){
        if(grid[y][x]>=1){
          pX=findParent(x,y);
          pY=y-LEVELDISTANCE;
          //fills in vertical path
          for(int i=1;i<LEVELDISTANCE;i++){
            grid[y-i][x]=-1;
          }
          //fills in horizontal path
          if(pX!=x){
            for(int i=0;i<Math.abs(pX-x);i++){
              if(x>pX){
                grid[pY][x-i]=-1;
              }
              else{
                grid[pY][x+i]=-1;
              }
            }
          }
        }    
      }
    }
    this.grid=grid;
  }
  public int findParent(int x,int y){
    //number of nodes in parent level
    int numPNodes=(int)Math.pow(2,(y-LEVELDISTANCE)/LEVELDISTANCE);
    //length of parent segment
    int parentSeg=WIDTH/(numPNodes+1);
    //length of current segment
    int childSeg=WIDTH/((int)(Math.pow(2,y/LEVELDISTANCE))+1);
    //find out which parent current node belongs to
    return (((x/childSeg)+1)/2)*parentSeg; //returns parent's x coordinate
  }
  private void moveMakar(int x, int y){
    int temp = grid[y][x];
    grid[y][x]=-2;
  }
//  public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    
//    // ??
//    if (key_down) {  
//      
//    }
//    
//    // ??
//    else if (key_up) {  
//     
//    }
//    
//    else if (key_right) {  
//
//    }
//    
//    else if (key_left) {  
//     
//    }
//    
//    else if (key_space) {
//      //check to see if we collided with a gem
//      //if yes then pick up the gem
//      // if no then do nothing
//      // if wrong gem display message oops
//      // otherwise make the gem disappear
//      //add it to the game board menu as a recently picked up gem
// 
//      
//    }
//    
//    // ?? - ??????? 
//    //for (int index = 0; index < 10000000; index++) {}
//    
//    repaint();
//  }
  private class KeyInput implements KeyListener {
    public void keyTyped(KeyEvent e) {}
    
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == e.VK_DOWN) key_down = false;
      if (e.getKeyCode() == e.VK_UP) key_up = false;
      if (e.getKeyCode() == e.VK_RIGHT) key_right = false;
      if (e.getKeyCode() == e.VK_LEFT) key_left = false;
      if (e.getKeyCode() == e.VK_SPACE) key_space = false;
    }
    
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == e.VK_DOWN) key_down = true;
      if (e.getKeyCode() == e.VK_UP) key_up = true;
      if (e.getKeyCode() == e.VK_RIGHT) key_right = true;
      if (e.getKeyCode() == e.VK_LEFT) key_left = true;
      if (e.getKeyCode() == e.VK_SPACE) key_space = true;
    }
  }
  public static void main(String[] args){
    //int test[]={1,1,1,1,1,1,1,0,0,1,1,1,1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0};
    int test[]={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    
    //TreePanel t = new TreePanel();
    //t.convertTree(test);
  }
}