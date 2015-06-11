//*********************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class scans a text file and 
//outputs each line of the file that is a 
//palindrome. 
//********************************************

import java.util.*; 
import java.io.*;

public class FindPalindromes
{
   public static void main(String[] args)
   {
      try
      {
         Scanner sc = new Scanner(new File(args[0]));
         char [] line;
	 String palindrome;
         MyStack <Character> stack = new MyStack <Character>();
         boolean isPalindrome = false;

	 //While the text file has a next line, the line will be
	 //checked to see if it's a palindrome.
         while (sc.hasNextLine())    
         {
	    palindrome = sc.nextLine();
            line = palindrome.replaceAll("\\W", "").toLowerCase().toCharArray();  //Replaces all whitespace and punctuation with "", thus getting rid of whitespace and punctuation.

            for(int i=0; i < (line.length)/2; i++)	//Pushes the first half of the line onto the stack.
            {
               stack.push(line[i]);
            }

            while(!(stack.isEmpty()))			//While the stack is not empty, it pops elements off the stack and compares them to last half of the the char array.
            {
	       if(line.length % 2 != 0) 		//If the line has an odd number of letters, it starts checking at one after the middle element.
	       {
             	  for(int i = ((line.length)/2) +1; i < line.length; i++) 
          	  {
                 	if(stack.pop() == line[i])
                       	    isPalindrome = true;
		  	else
		     	    isPalindrome = false;
               	  }
               }
	       else
               {
    	  	  for(int i = (line.length)/2; i < line.length; i++) //If the line has an even number of letters, it starts checking at the element after the last element to be pushed onto the stack.
          	  {
                 	if(stack.pop() == line[i])
                       	    isPalindrome = true;
		  	else
		     	    isPalindrome = false;
               	  }
	       }
            }

            if(isPalindrome)
               System.out.println("\"" + palindrome + "\" is a palindrome!");
         }    
      }                       
      catch (FileNotFoundException ex) 				//If the file is not found, it throws a FileNotFoundException.
      {
         System.out.println("FileError: File not found."); 
      }
   }
}