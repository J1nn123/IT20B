
package magdurait20b;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class priorityqueueAct{

  
    public static void main(String[] args) {
        
         Queue<String> my = new PriorityQueue<>(Collections.reverseOrder());
         
        my.offer("B");
        my.offer("C");
        my.offer("A");
        my.offer("E");
        my.offer("D");
        
        while(!my.isEmpty()){
            System.out.println(my.poll());
        }
         
        
    }
    
}
