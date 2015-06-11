//*********************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class allows for the creation
//of a stack abstract data type.
//********************************************

import java.util.*; 

public class MyStack <AnyType>
{
   ArrayList <AnyType> arr;
   int top; 
   
   //This constructor creates an ArrayList of <AnyType>
   //and makes the top of the stack -1 (just before the
   //first index of the ArrayList). 
   public MyStack()
   {
      arr = new ArrayList <AnyType>();
      top = -1;
   }

   //Allows you to insert an element in the stack by
   //pushing it onto the top of it. Increments the top
   //by one (thus, pushing the first element into index
   //0). 
   public void push(AnyType e)
   {
         top++;
         arr.add(top, e);
   }
   
   //Allows you to remove an element from the stack by
   //taking off the first element on at the top and 
   //decrementing top by one. 
   public AnyType pop()
   {
      if(isEmpty())
      {
	throw new StackUnderflowException(); 
      }
      	AnyType element = arr.remove(top);
      	top--;
    
      return element; 
   }
   
   //Returns the element at the top of the stack. If
   //the stack is empty, it returns null. 
   public AnyType peek()
   {  
      if(isEmpty()) 
           return null;
      else
           return arr.get(top);
   } 
 
   //Returns a boolean indicating if the stack is 
   //empty or not. 
   public boolean isEmpty()
   {     
      if(top < 0) 
           return true;
      else
	   return false;
   } 
}

class StackUnderflowException extends RuntimeException {}