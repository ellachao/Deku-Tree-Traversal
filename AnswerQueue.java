/* CS 230 Final Project
 * Whitney Fahnbulleh, Ella Chao, Sophia Sun
 * Answer Queue Class
 * (primarily written by Sophia)
 */


import javafoundations.LinkedQueue;

/**
 * Creates a linked Queue for the given traversal,  
 * corresponds with TreePanel class to check answers
 */
public class AnswerQueue {
  
  private String traversal;
  private LinkedQueue<Integer> answer;
  
  //================================================================
  // constructor: makes answer queue according to input
  // a switch is used.
  // 0: in-order
  // 1: pre-order
  // 2: post-order
  // 3: level order
  // 4: random
  //================================================================

  public AnswerQueue(int i, RandomTree t){

    RandomTree gameTree = t;
    
    if (i==4) i = (int)Math.random()*3;
    
    switch(i){
      case 0: 
        traversal = "In-Order";
        answer = gameTree.queueInOrder();
        break;
        
      case 1:
        traversal = "Pre-Order";
        answer = gameTree.queuePreOrder();
        break;

      case 2:
        traversal = "Post-Order";
        answer = gameTree.queuePostOrder();
        break;
      
      case 3:
        traversal = "Level-Order";
        answer = gameTree.queueLevelOrder();
        break;
      
      default:
        throw new IllegalArgumentException("Invalid input. (please chose between 0~4)");
     
    }
  }
  
  public String getTraversal(){
    return traversal;
  }
    
    public String toString() {
      return (answer.toString());
    }
    
    public Object peek () {
      return (answer.first());
    }
    
    //----------------------------------------------------
   /**
    * check answer here, returns an int as result
    * -1: game won
    * 0: wrong pick
    * 1: right pick
    */
   //----------------------------------------------------
    public int checkAnswer(int i) {
      Integer input = new Integer(i);
      
      if (input.equals(peek())){
        answer.dequeue();
        
        //GAME WON!!!
        if (answer.isEmpty()) return -1;
        
        //picks up the right gem
        else return 1;
        
      } else {
        //wrong pick sorry :(
        return 0;
      }
    }
    
    
  }