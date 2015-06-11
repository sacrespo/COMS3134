//*****************************************
//PROBLEM: Programming #1, Question 1.15
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class implements Comparator 
//using Rectangle objects. It uses the 
//methods getLenth() and getWidth() from the 
//Rectangle class to compare areas. 
//****************************************

import java.util.Comparator;

public class CompareArea implements Comparator<Rectangle> 
{
 //This method returns an int 0, 1, or -1 based on if the 
 //first rectangle is equal to, larger, or smaller than 
 //the second rectangle respectively based on area. 
 public int compare(Rectangle rec1, Rectangle rec2) 
 {
    double area1 = (rec1.getLength())*(rec1.getWidth());
    double area2 = (rec2.getLength())*(rec2.getLength());
    if (area1 == area2) 
      return 0; 
   else if (area1 > area2)
      return 1; 
   else 
      return -1; 
  }
}