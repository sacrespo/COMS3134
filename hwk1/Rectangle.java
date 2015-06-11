//*********************************************
//PROBLEM: Programming #1, Question 1.15
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is for Rectangle objects.
//There are two private instance variables  
//representing length and width of a Rectangle. 
//These are accessible using getLength() and 
//getWidth().  
//********************************************

public class Rectangle
{
   private double myLength; 
   private double myWidth; 

   //This constructor takes in two parameters: 
   //two doubles for length and width of a rectangle. 
   public Rectangle(double length, double width)
   {
      myLength = length;
      myWidth = width; 
   }

   //Accessor method returning the private instance
   //variable myLength.
   public double getLength()
   {
      return myLength; 
   }

   //Accessor method returning the private instance
   //variable myWidth. 
   public double getWidth()
   {
      return myWidth; 
   }
}