/* CS 230 Final Project
 * Whitney Fahnbulleh, Ella Chao, Sophia Sun
 * Random Tree Class
 */

import java.util.*;
import javafoundations.LinkedQueue;

public class RandomTree {
  
  private final int DEPTH = 4;
  private int capacity = (int)Math.pow(2, DEPTH+1)-1;
  private int[] tree;
 
  
  //================================================================
  // constructor: calls function to generate random tree
  //================================================================
  public RandomTree(){
    tree = new int[capacity];
    generateRandomTree();
  }
  

  //================================================================
  // getter function, so that other program can access the tree array
  //================================================================
  public int[] getTree(){
    return tree;
  }
   
  //================================================================
  // generateRandomTree(): randomly generate a tree with
  // depth = DEPTH
 //================================================================
  
  public void generateRandomTree()
  {
    //assigns each existing node with a possitive integer
    int counter = 1;
    
    //first level and second level(depth 0 & 1): always there
    tree[0] = counter;
    counter ++;
    
    tree[1] = counter;
    counter ++;
    
    tree[2] = counter;
    counter ++;
    
    //third level and after (depth >=2): probability: 0.75^(depth-1)
    //-1: doesnt exist
    //0: not filled / determined yet (default)
    //possitive integers: node exists 
    
    for (int depth = 2; depth <= DEPTH; depth ++)
    {
      //finds out the start and end of the current level
      int start = (int)Math.pow(2, depth)-1;
      int end = (int)Math.pow(2, depth+1)-1;
      
      for (int i = start ; i < end ; i++){
        
        // if this node can't possibly exist (parent doesn't exist), make its children into -1
        if (tree[1] == -1){
          if ( 2*i+2 < capacity) tree[2*i+1] = tree[2*(i+1)] = -1;
        }
        
        //if parent exist, then generate the child according to probablility
        else {  
          
          // we set the probability as (3/4)^(depth-1)
          // so nodes at depth = 2 has (3/4) probability to exist
          // nodes at depth = 2 has (9/16) probability to exist
          // nodes at depth = 2 has (27/64), and so on.
          
          //generate random number according to denominator, 4^(depth-1)
          int random = (int)(Math.random()*Math.pow(4, depth-1));
          
          //The tree node exists if it is less than 3^(depth-1)
          if (random <= Math.pow(3, depth-1)){
            tree[i] = counter;
            counter++;
            
            // if it doesnt exist,
            //make the node itself and its children into -1
          } else {
            tree[i] = -1;
            if ( 2*i+2 < capacity) tree[2*i+1] = tree[2*(i+1)] = -1;
          }
          
        }
      }
      
    }
    
  }
  
  
  //================================================================
  // toString method
  // Returns the string representation of the binary tree,
  // one line per level.
  //================================================================
  public String toString()
  {
    String s = "";
    
    for (int i =0; i< capacity; i++)
    {
      s += tree[i] + "   ";
      
      if (i==0 || i==2 || i==6 || i==14)
      {
        s+= "\n";
      }
    }
    return s;
  }
  
  
  
  
  //================================================================
  //  IN-ORDER TRAVERSAL
  //  Performs an inorder traversal on the binary tree by calling an
  //  overloaded, recursive inorder method that starts with
  //  the root.
  //================================================================
   public LinkedQueue<Integer> queueInOrder() 
   {
      LinkedQueue<Integer> inOrder = new LinkedQueue<Integer>();
      inorder (0, inOrder);
      return inOrder;
   }  // method inorder

   //================================================================
   //  Performs a recursive inorder traversal.
   //================================================================
   protected void inorder (int node, LinkedQueue<Integer> queue) 
   {
     //check if node is in the array
     if (node < capacity)
     {
       //check if node exists
         if (tree[node] != -1)
         {
            inorder ((node)*2+1, queue);
            
            Integer elm = new Integer(tree[node]);
            queue.enqueue(elm);
            
            inorder ((node+1)*2, queue);
            
         }
     }

   }  


   
   //================================================================
   //  PRE-ORDER TRAVERSAL
   //  Performs an preorder traversal on the binary tree by calling an
   //  overloaded, recursive preorder method that starts with
   //  the root.
   //================================================================
   public LinkedQueue<Integer> queuePreOrder() 
   {
      LinkedQueue<Integer> preOrder = new LinkedQueue<Integer>();
      preorder (0, preOrder);
      return preOrder;
   }  

   
   //================================================================
   //  Performs a recursive preorder traversal.
   //================================================================
   protected void preorder (int node, LinkedQueue<Integer> queue) 
   {
     //check if node is in the array
     if (node < capacity)
     {   
       //check if node exists
         if (tree[node] != -1) 
      { 
            Integer elm = new Integer(tree[node]);
            queue.enqueue(elm);
            
            preorder ((node)*2+1, queue);
            preorder ((node+1)*2, queue);
      }

   }  
   }
   
   
   //================================================================
   //  POST-ORDER TRAVERSAL
   //  Performs an postorder traversal on the binary tree by calling
   //  an overloaded, recursive postorder method that starts
   //  with the root.
   //================================================================
   public LinkedQueue<Integer> queuePostOrder() 
   {
      LinkedQueue<Integer> postOrder = new LinkedQueue<Integer>();
      postorder (0, postOrder);
      return postOrder;
   } 

   //================================================================
   //  Performs a recursive postorder traversal.
   //================================================================
   protected void postorder (int node, LinkedQueue<Integer> queue) 
   {
     //check if node is in the array
     if (node < capacity) {
       
       //check if node exists
         if (tree[node] != -1) 
      {
            postorder ((node)*2+1, queue); 
            postorder ((node+1)*2, queue);
            
            //enqueues the integer element
            Integer elm = new Integer(tree[node]);
            queue.enqueue(elm);
            
         }//if
     }
   }  // method postorder

   
   //================================================================
   //  LEVEL ORDER TRAVERSAL
   //  Performs a levelorder traversal on the binary tree
   //  loops through the array and enqueues all existing node 
   //================================================================
   public LinkedQueue<Integer> queueLevelOrder() 
   {
      LinkedQueue<Integer> levelOrder = new LinkedQueue<Integer>();
      for (int i=0; i<capacity; i++){
        Integer elm = new Integer(tree[i]);
        if (tree[i] != -1) levelOrder.enqueue(elm);
      }
      return levelOrder;
   }  
  
  
   
   
   //================================================================
   //  TESTING
   //================================================================
   public static void main(String[] args){
     
     RandomTree tree = new RandomTree();
     System.out.println(tree);
     
     System.out.println("\n\nLevel order queue:");
     System.out.println(tree.queueLevelOrder());
     
     System.out.println("\n\nIn order queue:");
     System.out.println(tree.queueInOrder());
     
     System.out.println("\n\nPre order queue:");
     System.out.println(tree.queuePreOrder());
     
     System.out.println("\n\nPost order queue:");
     System.out.println(tree.queuePostOrder());
     
     
   }
   
}