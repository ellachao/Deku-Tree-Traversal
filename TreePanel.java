import java.awt.*;
import javax.swing.*;
import java.lang.Math;

public class TreePanel extends JPanel{
  private static final int WIDTH=36;
  private static final int LEVELDISTANCE=3;
  
  public TreePanel(){
    ImageIcon img =new ImageIcon("grass.jpg");
    JLabel label=new JLabel();
    label.setIcon(img);
    add(label);
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
      if (intArray[i]!=0){
        grid[row][distance]=1;
      }
//      if (WIDTH/(power+1)==0){
//        segment=1;
//      }
//      else if (WIDTH/(power+1)==1){
//        segment=2;
//      }
      distance+=segment;
    }
    fillPath(grid);
    String r="";
    for (int i=0;i<grid.length;i++){
      for(int j=0;j<grid[i].length;j++){
        r+=grid[i][j]+" ";
      }
      r+="\n";
    }
    System.out.println(r);
    return grid;
  }
  
  public int[][] fillPath(int[][] grid){
    //fills paths with -1
    int pX;
    int pY;
    for(int y=LEVELDISTANCE;y<grid.length;y+=3){
      for(int x=0;x<grid[y].length;x++){
        if(grid[y][x]==1){
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
    return grid;
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
  
  public static void main(String[] args){
    //int test[]={1,1,1,1,1,1,1,0,0,1,1,1,1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0};
    int test[]={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    
    TreePanel t = new TreePanel();
    t.convertTree(test);
//    System.out.println("[18]"+t.findParent(24,3));
//    System.out.println("[12]"+t.findParent(7,6));
//    System.out.println("[12]"+t.findParent(14,6));
//    System.out.println("[24]"+t.findParent(21,6));
//    System.out.println("[24]"+t.findParent(28,6));
//    System.out.println("[7]"+t.findParent(4,9));
//    System.out.println("[7]"+t.findParent(8,9));
//    System.out.println("[14]"+t.findParent(12,9));
//    System.out.println("[14]"+t.findParent(16,9));
//    System.out.println("[21]"+t.findParent(20,9));
//    System.out.println("[21]"+t.findParent(24,9));
//    System.out.println("[28]"+t.findParent(28,9));
//    System.out.println("[28]"+t.findParent(32,9));
//    System.out.println("[4]"+t.findParent(2,12));
//    System.out.println("[4]"+t.findParent(4,12));
//    System.out.println("[8]"+t.findParent(6,12));
//    System.out.println("[8]"+t.findParent(8,12));
//    System.out.println("[12]"+t.findParent(10,12));
//    System.out.println("[12]"+t.findParent(12,12));
//    System.out.println("[16]"+t.findParent(14,12));
//    System.out.println("[16]"+t.findParent(16,12));
//    System.out.println("[20]"+t.findParent(18,12));
//    System.out.println("[20]"+t.findParent(20,12));
//    System.out.println("[24]"+t.findParent(22,12));
//    System.out.println("[24]"+t.findParent(24,12));
//    System.out.println("[28]"+t.findParent(26,12));
//    System.out.println("[28]"+t.findParent(28,12));
//    System.out.println("[32]"+t.findParent(30,12));
//    System.out.println("[32]"+t.findParent(32,12));
  }
}