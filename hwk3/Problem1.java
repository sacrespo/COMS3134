//*********************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This is a command line application
//that indexes the words contained in a text 
//file line by line, extracting each word and
//inserting that word, along with its location,
//into an AVL tree. It prints out each unique
//word and the list of lines on which the word
//appears. 
//********************************************


import java.util.*; 
import java.io.*;

public class Problem1
{
   public static void main(String[] args)
   {
	  try
		{
		      Scanner sc = new Scanner(new File(args[0]));
      		int lineNumber = 0;
      		String word; 
      		String line; 
      		Scanner lineScan;
      		AvlTree tree = new AvlTree();

      		while (sc.hasNextLine()) 	
      		{	
			      lineNumber ++; 
      			line = sc.nextLine();
      			lineScan = new Scanner(line);

      		while(lineScan.hasNext())
      		{
      			word = lineScan.next().toLowerCase();
               //Strips words of punctuation and symbols at the end of the word. 
				   while(word.charAt(word.length()-1) == '.' 
                     || word.charAt(word.length()-1) == ','
                     || word.charAt(word.length()-1) == '!' 
                     || word.charAt(word.length()-1) == '?'
                     || word.charAt(word.length()-1) == ':'
                     || word.charAt(word.length()-1) == '-'
                     || word.charAt(word.length()-1) == ';'
                     || word.charAt(word.length()-1) == ')'
                     || word.charAt(word.length()-1) == '"'
                     || word.charAt(word.length()-1) == '_') 
      			{
      				word = word.substring(0,word.length()-1);
      			}

               //Strips words of punctuation and symbols at the start of the word.
				  while(word.charAt(0) == '.'|| word.charAt(0) == '('|| word.charAt(0) == '"') 
      				{
      					word = word.substring(1,word.length()-1);
      				}

      				tree.insert(word, lineNumber);
      	  		}
      	  	}

      	  	tree.printTree();
      	}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileError: File not found."); 
		}
   }
}