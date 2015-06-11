//*************************************
//PROBLEM: Programming #2, Question 2.7 
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class uses a timer to 
//determine how long it takes to run 
//different algorithms. 
//*************************************

import java.util.Scanner;

public class CheckTime
{
	//A timer is created and a user inputs a value, long n, which dictates
	//how many values each algorithm must cycle through to produce a sum. 
	public static void main(String[] args)
	{
		TimeInterval timer1 = new TimeInterval(); 
		int sum = 0;
		
		Scanner in = new Scanner(System.in); 
       		System.out.println("Enter N Value:  ");
       		long n = in.nextLong();		
		timer1.startTiming();  
		
		//Code for each part of the question was commented out as it was completed.
		/*for(int i=0; i<n; i++)
		{
			sum++;
		}

		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) 
				sum++;

		for(int i=0; i<n; i++)
			for(int j=0; j<n*n; j++)
				sum++;

		for(int i=0; i<n; i++)
			for(int j =0; j<i; j++)
				sum++; 

		for(int i=0; i<n; i++)
			for(int j=0; j<i*i;j++)
				for(int k=0; k<j; k++)
					sum++;*/

		for(int i=1; i<n; i++)
			for(int j=1; j<i*i;j++)
				if(j%i==0)
					for(int k=0; k<j; k++)
						sum++;

		timer1.endTiming();
		double time1 = timer1.getElapsedTime(); 
		System.out.println(time1); 
	}
}

