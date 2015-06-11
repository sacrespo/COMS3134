//*****************************************
//PROBLEM: Programming #1, Question 1.15
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class uses implemented 
//Comparators to compare the sizes of 
//rectangles and determine which is larger
//based on area and perimeter. 
//****************************************

import java.util.Comparator;

public class Problem1
{
	//This method finds the largest of several Rectangles based on a certain 
	//comparator. It returns the index of the larger Rectangle.  
	public static int findMax(Rectangle [] arr, Comparator<Rectangle> cmp)
	{
		int maxIndex = 0; 

		for(int i=1; i<arr.length; i++)
			if(cmp.compare(arr[i], arr[maxIndex]) > 0)
				maxIndex = i; 
		return maxIndex; 
	} 

	//Main tester method. Several Rectangle objects are created and are put
	//into an array of Rectangles. These are then compared using findMax(). 
	public static void main(String[] args)
	{
		Rectangle R1 = new Rectangle(1.2,3.1);
		Rectangle R2 = new Rectangle(4.0,5.0);
		Rectangle R3 = new Rectangle(7.5,9.3);
		Rectangle [] rectangles = {R1, R2, R3};

		int maxArea = findMax(rectangles, new CompareArea()); 		//compares the Rectangles in the array based on area
		int maxPerim = findMax(rectangles, new ComparePerimeter());	//compares the Rectangles in the array based on perimeter

		System.out.println("The rectangle with maximum area is at index:" + maxArea);
		System.out.println("The rectangle with maximum perimeter is at index:" + maxPerim);
	}
}
