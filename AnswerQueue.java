/* CS 230 Final Project
 * Whitney Fahnbulleh, Ella Chao, Sophia Sun
 * Answer Queue Class
 */


import java.util.*;
import javafoundations.LinkedQueue;

public class AnswerQueue {
  
  private String traversal;
  private LinkedQueue answer;
  
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
    
    public boolean isEmpty() {
      return (answer.isEmpty());
    }
    
    public String toString() {
      return (answer.toString());
    }
    
    public Object peek () {
      return (answer.first());
    }
    
    public int checkAnswer(int i) {
      Integer input = new Integer(i);
      
      if (input.equals(peek())){
        answer.dequeue();
        
        //GAME WON!!!
        if (isEmpty()) return -1;
        
        //picks up the right gem
        else return 1;
        
      } else {
        //wrong pick sorry :(
        return 0;
      }
    }
    
    
  }