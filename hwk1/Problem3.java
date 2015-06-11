//*****************************************
//PROBLEM: Programming #3
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class uses an overloaded 
//binarySearch() method to recursively 
//search an array given by a command-line
//entered text file for a user-inputted 
//value. 
//****************************************

import java.util.*; 
import java.io.*;

public class Problem3
{
	//This method is the driver method for the binary
	//recursive search. It returns an int and takes two
	//parameters: an array of integers and an int x that is
	//being searched for. 
	public static int binarySearch(int [] arr, int x)
	{
		return binarySearch(arr, x, 0, arr.length-1);
	} 

	//This method is the recursive method for the binary
	//recursive search. So long as the value has not been
	//found and the base case has not been reached, it will
	//continue the binary search for int x. It returns either
	//-1 if x is not found, or the index of x if it is in the 
	//array. 
	public static int binarySearch(int [] arr, int x, int start, int stop)
	{
		if(start > stop)
			return -1; 

		int mid = (start + stop)/2; 

		if(arr[mid] < x)
			return binarySearch(arr, x, mid+1, stop);
		else if(arr[mid] > x) 
			return binarySearch(arr, x, start, mid-1);
		else 
			return mid; 
	} 

	//This main tester method creates a scanner that reads in 
	//a command-line txt file. It will throw an exception if
	//the file cannot be found. This file is turned into an 
	//array. The user inputs a value to search for within the
	//array and binarySearch() is called. 
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(new File(args[0]));
		
			int length = 0; 
      			
      			while (sc.hasNextInt()) 	//This loop goes through the next file and records how many values it has. 
      			{				//int length is incremented. length is used for the length of the int [] to
      	  			sc.nextInt(); 		//be searched later. 
          			length ++; 
      			}	
        
       			int [] array = new int[length]; 
        		int index = 0; 
			sc = new Scanner(new File(args[0]));
    			while (sc.hasNextInt())				//This loop fills the array with each value from the text file. 
    			{
    				array[index] = sc.nextInt();  
    				index++; 
    			}

    			sc = new Scanner(System.in); 
       			System.out.print("What value are you looking for? ");
       			int num = sc.nextInt();		

    			int result = binarySearch(array, num); 
    			if (result == -1)
    				System.out.println("That value is not in the array.");
    			else 
			{
    				System.out.println("We found " + num +"!");
    				System.out.println("That value is located at index " + result + ".");
      			}
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileError: File not found."); 
		}
	}
}
