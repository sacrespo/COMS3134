//************************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class is the blueprint to create 
//sets containing vertices for Kruskal's algorithm.
//************************************************

import java.util.*;

public class Sets
{
    ArrayList<Set> sets;

    //A constructor to create a larger set of
    //sets. 
    public Sets()
    {
        sets = new ArrayList<Set>();
    }

    //A method to create a set containing a
    //vertex. 
    public void createSet(Vertex e)
    {
            sets.add(new Set(e));
    }

    //A method to find a set containing a 
    //certain vertex. 
    public Set findSet(Vertex e)
    {
        for(Set s : sets)
        {
            if(s.vertices.contains(e))
            {
                return s;
            }
        }
        return null;
    }

    //A method to merge the sets respectively
    //containing each vertice into a common set.
    public void union(Vertex e1, Vertex e2)
    {
        Set s1 = findSet(e1);
        Set s2 = findSet(e2);

        for(Vertex e : s2.vertices)
        {
            s1.vertices.add(e);
        }
        sets.remove(s2);
    }

    //A class to create a set containing vertices. 
    static class Set
    {
        ArrayList<Vertex> vertices;

        //A constructor to create a set with an ArrayList
        //of vertices. 
        public Set(Vertex e)
        {
            vertices = new ArrayList<Vertex>();
            vertices.add(e);
        }

        //A toString() method to print out the vertices
        //contained in the set. 
        public String toString()
        {
            String out = "{";
            for(Vertex v: vertices)
            {
                out += v.toString() +", ";
            }
            return out + "}";
        }
    }
}