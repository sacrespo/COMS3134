//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is the blueprint to create 
//an edge connecting two vertices. It takes in the
//target of the first vertice and the weight of the
//edge between the two vertices. This Edge class is
//used with Dijkstra's Algorithm.
//************************************************

public class Edge
{
    Vertex target;
    double weight;

    //A constructor that takes in the target vertice
    //and the weight of the edge between two vertices.
    public Edge(Vertex target, double weight)
    {
        this.target = target;
        this.weight = weight;
    }
    
    //An accessor method to return the target vertice. 
    public Vertex getTarget()
    {
        return target;
    }
    
    //An accessor method to return the weight of the edge.
    public double getWeight()
    {
        return weight;
    }

    //A method to compare the weights of edges.
    public int compareTo(Edge edge)
    {
    	if(this.weight < edge.weight)
        	return -1; 
        else if (this.weight > edge.weight)
        	return 1;
        else
        	return 0;
    }
}