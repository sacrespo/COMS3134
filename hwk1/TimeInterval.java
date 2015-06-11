//******************************************
//PROBLEM: Programming #2, Question 2.7 
//CODE FROM: Paul Blaer
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is for a timer.
//After using startTiming() it is important 
//to stop the timer before using 
//getElapsedTime().
//******************************************

public class TimeInterval 
{
   private long startTime, endTime;
   private long elapsedTime; // Time Interval in milliseconds

   public void startTiming() {
      elapsedTime = 0;
      startTime = System.currentTimeMillis();
   }

   public void endTiming() {
      endTime = System.currentTimeMillis();
      elapsedTime = endTime - startTime;
   }

   public double getElapsedTime() {
      return (double) elapsedTime / 1000.0;
   }

}