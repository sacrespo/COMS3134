//*****************************************
//PROBLEM: Programming #1, Question 1.15
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class implements Comparator 
//using Rectangle objects. It uses the 
//methods getLenth() and getWidth() from the 
//Rectangle class to compare perimeters. 
//****************************************

import java.util.Comparator;

public class ComparePerimeter implements Comparator<Rectangle>
{
 //This method returns an int 0, 1, or -1 based on if the 
 //first rectangle is equal to, larger, or smaller than 
 //the second rectangle respectively based on perimeter. 
 public int compare(Rectangle rec1, Rectangle rec2) 
 {
    double perim1 = 2*(rec1.getLength())+(rec1.getWidth());
    double perim2 = 2*(rec2.getLength())+(rec2.getLength());
    if (perim1 == perim2) 
      return 0; 
   else if (perim1 > perim2)
      return 1; 
   else 
      return -1; 
  }
}