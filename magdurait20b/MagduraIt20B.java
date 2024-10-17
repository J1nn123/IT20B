
package magdurait20b;

import java.util.LinkedList;


public class MagduraIt20B {

  
    public static void main(String[] args) {
        
        
       
        
        
        LinkedList<String> Person = new LinkedList<>();
        
        
// insertion
        Person.add("jin");
        Person.addFirst("jino");
        Person.addLast("Magdura");
        Person.add(1, "T.");
        Person.remove("jin");

        System.out.println("Linked List Original: " + Person);
        System.out.println(Person.size());

        
        
        
         if (Person.contains("jino")) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        boolean containsPerson = Person.contains("jino");
        System.out.println(containsPerson);
        
    }
    
}

    
    

