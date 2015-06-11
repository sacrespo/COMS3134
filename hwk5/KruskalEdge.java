//************************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is the blueprint to create 
//an edge connecting two vertices. It takes in the
//starting and ending vertices and determines the 
//weight of the edge based on the distance between
//the two vertices. 
//************************************************

public class KruskalEdge implements Comparable<KruskalEdge>
{
    Vertex v1, v2;
    double weight;

    //A constructor that takes in two vertices and 
    //based on their coordinates, determines the weight
    //of the edge from the distance. 
    public KruskalEdge(Vertex v1, Vertex v2)
    {
        this.v1 = v1;
        this.v2 = v2;
        weight = Math.sqrt(Math.pow(v1.x-v2.x,2)+Math.pow(v1.y-v2.y,2));
    }
    
    //An accessor method to return the weight of the edge.
    public double getWeight()
    {
        return weight;
    }

    //A toString() method that prints out the two 
    //vertices connected by the edge.
    public String toString()
    {
        return "{" + v1 + ", " + v2 + "}";
    }

    //A method to compare the weights of two edges. 
    public int compareTo(KruskalEdge edge)
    {
    	if(this.weight < edge.weight)
        	return -1; 
        else if (this.weight > edge.weight)
        	return 1;
        else
        	return 0;
    }
}