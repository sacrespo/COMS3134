//*********************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: An application that takes in 1 or 2 
//dictionaries and, after inputing those files in
//a HashTable, checks a text file for spelling
//errors.
//********************************************

import java.util.*; 
import java.io.*;

public class Problem2
{
   public static MyHashTable<String> table;

   public static void main(String[] args)
   {
	 table = new MyHashTable<String>(); 

	  if(args.length == 2)    //Fills table if one dictionary is provided.
	  {
	  	fillTable(args[0]);
	  	spellCheck(args[1]);
	  }
	  else if (args.length == 3) //Fills table if two dictionaries are provided.
	  {
	  	fillTable(args[0],args[1]);
	  	spellCheck(args[2]);
	  }
	  else
	  {
	  	System.out.println("Incorrect number of arguments.");
	  	System.out.println("Please input 1, or 2 dictionaries and a file to be spellchecked.");
	  }
   }

   //Fills the HashTable if one dictionary is provided.
   //Fills the HashTable with the contents of the dictionary file provided.
   private static void fillTable(String dictionary)
   {
		try
		{
   			Scanner input = new Scanner(new File(dictionary));

   			while(input.hasNext())
   			{
   				table.insert(input.nextLine().toLowerCase());
   			}
            System.out.println(table.size());
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileError: File not found."); 
		}
   }

   //Fills the HashTable if two dictionaries are provided.
   //Fills the HashTable with the contents of the dictionary files provided.
   private static void fillTable(String dictionary1, String dictionary2)
   {
		try
		{
   			Scanner input1 = new Scanner(new File(dictionary1));
   			while(input1.hasNext())
   			{

   				table.insert(input1.nextLine().toLowerCase());
   			}
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileError: File not found."); 
		}
		try
		{
   			Scanner input2 = new Scanner(new File(dictionary2));
   			while(input2.hasNext())
   			{
   				table.insert(input2.nextLine().toLowerCase());
   			}
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileError: File not found."); 
		}   
   }	

   //A method that checks the spelling of the file based off of
   //the words previously added to the HashTable.
   private static void spellCheck(String lines)
   {
	  try
	  {
	
  	 	    Scanner sc = new Scanner(new File(lines));
      		String word; 
      		String line; 
      		Scanner lineScan;
            int lineNum = 0;

   		while (sc.hasNextLine()) 	
      		{
      			line = sc.nextLine();
      			lineScan = new Scanner(line);
               lineNum++;

      		while(lineScan.hasNext())
      		{
      			word = lineScan.next().toLowerCase(); 
				while(word.charAt(word.length()-1) == '.' 
                  || word.charAt(word.length()-1) == ','
                  || word.charAt(word.length()-1) == '!' 
                  || word.charAt(word.length()-1) == '?'
                  || word.charAt(word.length()-1) == ':'
                  || word.charAt(word.length()-1) == '-'
                  || word.charAt(word.length()-1) == ';'
                  || word.charAt(word.length()-1) == ')'
                  || word.charAt(word.length()-1) == '"'
                  || word.charAt(word.length()-1) == '_')   //Strips off punctuation and symbols from the end of words.
      		{
      			word = word.substring(0,word.length()-1);
      		}
				while(word.charAt(0) == '.'|| word.charAt(0) == '('|| word.charAt(0) == '"') //Strips off punctuation and symbols from the start of words.
      		{
      			word = word.substring(1,word.length()-1);
      		}

      		if(!table.contains(word))      //If the word is not found in the dictionary, it is marked as incorrectly spelled.
      		{
      			  System.out.println("\"" + word + "\" at line " + lineNum + " is spelled incorrectly.");
      	        ArrayList<String> suggestions = suggest(word);        //Calls the method suggest() to find alternatives to the incorrectly spelled word.
                 if(suggestions.size()==0)                  //If no suggestions are found, it states that none were found.
                 {
                      System.out.println("No suggestions found.");     
                 }
                    else                     //If suggestions were found, it lists them.
                    {
                        System.out.println("Did you mean " + suggestions + "?");
                    }
      			}
      		}
      	}
	  }
	  catch (FileNotFoundException ex) 
	  {
		    System.out.println("FileError: File not found."); 
	  }
   }

   //A method to find suggested alternatives to incorrect words. 
   //Finds suggestions based on three criteria: 
   //1) Adding a character.
   //2) Removing a character.
   //3) Exchanging two characters. 
   //Returns an ArrayList<String> of all suggestions. 
   private static ArrayList<String> suggest(String w)
   {
   		ArrayList<String> suggestions = new ArrayList<String>(); 
   		String word;

         //Adds suggestions for words if, when you add a character 
         //it turns the original word into a properly spelled word.
   		for(int i = 0; i <= w.length(); i++)
   		{
   			for(char letter = 'a'; letter <= 'z'; letter++)
   			{
   				word = w.substring(0,i) + letter + w.substring(i, w.length());
   			   if(table.contains(word))
   				{
                  if(!suggestions.contains(word))
                  {
                     suggestions.add(word);
                  }
   					
   				}
			   }
   		}

         //Adds suggestions for words if, when you remove a character 
         //it turns the original word into a properly spelled word.
   		for(int i = 0; i < w.length(); i++)
   		{
   				word = w.substring(0,i) + w.substring(i+1, w.length());

   				if(table.contains(word))
   				{
   					if(!suggestions.contains(word))
                  {
                     suggestions.add(word);
                  }
   				}
   		}

         //Adds suggestions for words if, when you exchange two  
         //adjacent characters it turns the original word into a 
         //properly spelled word.
   		char[] c;
   		for(int i = 0; i < w.length()-1; i++)
   		{
   			   c = w.toCharArray();
               char temp = c[i];
				  c[i] = c[i+1];
				  c[i+1] = temp;

				  word = new String(c);
   			 	if(table.contains(word))
   				{
   					if(!suggestions.contains(word))
                  {
                     suggestions.add(word);
                  }
   				}
			
   		}
		   return suggestions;
   	}	
}