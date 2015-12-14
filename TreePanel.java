import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.*;

public class TreePanel extends JPanel{
  private static final int WIDTH=36; //width of 2-d array
  private static final int LEVELDISTANCE=3; //distance between each level
  private int[][] grid;
  private JLayeredPane lpane = new JLayeredPane();
  private JPanel panelBackground = new JPanel();
  private JPanel panelLabel1 = new JPanel();
  private JPanel panelLabel2 = new JPanel();
  private JPanel panelLabel3 = new JPanel();
  private JPanel panelReplay = new JPanel();
  private JPanel p = new JPanel();
  private RandomTree tree;
  private JLabel orderLabel,messageLabel, bthumb, replayLabel, rthumb;
  private boolean key_right, key_left, key_down, key_up, key_space; // Input booleans
  //coordinates of makar
  private int x; 
  private int y;
  private int temp; //value of grid[y][x]
  private AnswerQueue ans;
  private boolean added = false;
  private JFrame topFrame;
  
  public TreePanel(int number){
    setLayout(new BorderLayout());
    this.setFocusable(true);
    addKeyListener(new KeyInput()); 
    tree=new RandomTree();
    topFrame = (JFrame) SwingUtilities.getRoot(this);
    
    convertTree(tree.getTree());
    x=16;
    y=32;
    temp=grid[y][x];
    grid[y][x]=-2;
    setOpaque(false);
    ans = new AnswerQueue(number, tree);
    
    messageLabel = new JLabel("Good luck!");
    messageLabel.setFont(new Font("Verdana",1,20));
    
    drawTree();
    
  }
  
  public RandomTree getTree(){
    return tree;
  }
  
  private void drawTree(){
    //list of rupee images
    String[] images={"rupee1.jpg","rupee2.jpg","rupee3.jpg","rupee4.jpg","rupee5.jpg","rupee6.jpg"};
    //iterate through 2-d array
    //0 for grass
    //-1 for road
    //-2 for makar
    //everything else is gem
    for(int y=0;y<WIDTH;y++){
      for(int x=0;x<WIDTH;x++){
        //use value in 2-d array to display corresponding image
        if(grid[y][x]==0){
          JLabel img =new JLabel(new ImageIcon("images/grass.jpg"));
          //images are 30 by 30
          img.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img);
        }
        else if(grid[y][x]==-1){
          JLabel img2 =new JLabel(new ImageIcon("images/road.jpg"));
          img2.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img2);
        }
        else if(grid[y][x]==-2){
          JLabel makar =new JLabel(new ImageIcon("images/newmakar.png"));
          makar.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(makar);
        }
        else{
          JLabel img3 =new JLabel(new ImageIcon("images/"+images[grid[y][x]%images.length]));
          img3.setBounds(x*30,(y+10)%WIDTH*30,30,30);
          add(img3);
        }
      }
    ImageIcon background = new ImageIcon("images/panel.jpg"); 
    bthumb = new JLabel();
    bthumb.setIcon(background);
    panelBackground.setBounds(460, -5, 1345, 1200);

    panelBackground.setOpaque(false);
    
    orderLabel = new JLabel("<html><p style=\"text-align:center\">Pick up the rupees</p><p style=\"text-align:center\">in <b><i>"+ans.getTraversal() +"</i></b>!</p></html>");
    orderLabel.setFont(new Font("Courier New",1,20));
    panelLabel1.setOpaque(false);
    panelLabel1.setBounds(955,300,500,500);
    


    panelLabel2.setOpaque(false);
    panelLabel2.setBounds(955,400,500,500);
    
        
    replayLabel = new JLabel("Play Again");
    replayLabel.setFont(new Font("Courier New",1,20));
    panelLabel3.setOpaque(false);
    panelLabel3.setBounds(955,470,500,100);
    
    ImageIcon replay = new ImageIcon("images/replay.png"); 
    //creates a label and sets the image to it
    rthumb = new JLabel();
    rthumb.setIcon(replay);
    panelReplay.setBounds(955, 500, 500, 300);
    panelReplay.setOpaque(false);
    
    if(added==false){
    panelReplay.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                  System.out.println("reply button");
                  GamePanel game = new GamePanel();
                  String[] cool= {"hello"};
                  game.main(cool);
                  //topFrame.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
    
    });
    panelBackground.add(bthumb);
    panelLabel1.add(orderLabel);
    panelLabel2.add(messageLabel);
    panelLabel3.add(replayLabel);
    panelReplay.add(rthumb);
    added=true;
    }
    lpane.add(panelBackground, new Integer(0), 0);
    lpane.add(panelLabel1, new Integer(1), 1);
    lpane.add(panelLabel2, new Integer(1), 1);
    lpane.add(panelLabel3, new Integer(1), 1);
    lpane.add(panelReplay, new Integer(1), 1);
    add(lpane, BorderLayout.CENTER);
    setBackground(new Color(218,218,218));
  }
  }
  
  
  
  //converts list of nodes to a 2-d array 
  public int[][] convertTree(int[] intArray){
    //0 for grass because int is 0 by default
    int row=0;
    int index=0; //level
    int limit=0; //number of nodes in level
    int power=(int)Math.pow(2,index);
    int segment=WIDTH/(power+1); //distance between each node in that level
    int distance=segment;
    int[][] grid=new int[WIDTH][WIDTH];
    for (int i=0; i<intArray.length;i++){
      if(i>limit){
        //go to next level
        //update variables
        index++;
        row+=3;
        power=(int)Math.pow(2,index);
        limit+=power;
        segment=WIDTH/(power+1);
        distance=segment;       
      }
      //if there is a node
      if (intArray[i]>0){
        //update element to value from intArray
        grid[row][distance]=intArray[i];
      }
      //updates distance to position of next node
      distance+=segment;
    }
    fillPath(grid);
    return grid;
  }
  
  //connect parent nodes to children
  public void fillPath(int[][] grid){
    //fills paths with -1
    int pX;
    int pY;
    //loops through 2-d array but skip first level
    for(int y=LEVELDISTANCE;y<grid.length;y+=3){
      for(int x=0;x<grid[y].length;x++){
        if(grid[y][x]>=1){
          pX=findParent(x,y); //x-coordinate for parenet
          pY=y-LEVELDISTANCE;
          //fills in vertical path
          for(int i=1;i<LEVELDISTANCE;i++){
            grid[y-i][x]=-1;
          }
          //fills in horizontal path
          if(pX!=x){
            for(int i=0;i<Math.abs(pX-x);i++){
              if(x>pX){
                //to the left
                grid[pY][x-i]=-1;
              }
              else{
                //to the right
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
  private void moveMakar(int shiftX, int shiftY){
    //restore previous of where makar is right now
    grid[y][x]=temp;
    //calculates new coordinate
    int newY=((y+shiftY)%WIDTH<0)?(WIDTH-1):(y+shiftY)%WIDTH;
    int newX=((x+shiftX)%WIDTH<0)?(WIDTH-1):(x+shiftX)%WIDTH;
    temp=grid[newY][newX]; //stores value of where Makar will move to
    grid[newY][newX]=-2; //update that postion to makar
    //updates coordinates of makar
    x=newX;
    y=newY;
  }
  
  public void paintComponent(Graphics g) {
    //super.paintComponent(g);
    //check which keys is pressed
    //call moveMakar accordingly
    if (key_down) {  
      moveMakar(0,1);
      
    }
    else if (key_up) {  
      moveMakar(0,-1);
    }
    
    else if (key_right) {  
      moveMakar(1,0);
    }
    
    else if (key_left) {  
      moveMakar(-1,0);
    }
    else if (key_space) {
      //check to see if we collided with a gem
      //if yes then pick up the gem
      // if no then do nothing
      // if wrong gem display message oops
      // otherwise make the gem disappear
      //add it to the game board menu as a recently picked up gem
      
 
      
    }
    removeAll();
    revalidate();
    drawTree();
    repaint();
  }

  private class KeyInput implements KeyListener {
    public void keyTyped(KeyEvent e) {} //abstract method that has to be implemented
    public void keyReleased(KeyEvent e) {
      //checks key and updates instance variables accordingly
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
      if (e.getKeyCode() == e.VK_SPACE) {
        
        System.out.println("space!");
        
        int result = ans.checkAnswer(temp);
        System.out.println(ans);
        System.out.println(temp+ " and result:  "+ result);
        
        //game Won
        if (result == -1) {
          temp=-1;
          messageLabel.setText("You Won!");
        }
        else if (result == 0) {
          messageLabel.setText("Wrong gem. Please try again!");
        }
        else if (result == 1) {
          temp=-1;
          messageLabel.setText("Correct!");
        }
        
      }
    }
  }

}