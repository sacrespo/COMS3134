//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is the blueprint to create 
//a vertice at a coordinate (x,y) and a name 
//representing a city. 
//************************************************

import java.util.*; 

public class Vertex implements Comparable<Vertex>
{
   public int x, y; 
   public ArrayList<Edge> adj;  //Adjacency list of cities. 
   public double distance;
   public String name; 
   public Vertex path; 

   //A constructor that creates a Vertex with a name
   //representing a city's name and (x,y) coordinates 
   //representing its location.
   public Vertex(String cityName, int x, int y) 
   { 
      name = cityName; 
      this.x = x; 
      this.y = y;
      adj = new ArrayList<Edge>();
   }
   
   //A method to return the name of the vertex. 
   public String toString() 
   { 
      return name; 
   }

   //A method to compare the distances between
   //two vertices. 
   public int compareTo(Vertex other)
   {
      return Double.compare(distance, other.distance);
   }
}