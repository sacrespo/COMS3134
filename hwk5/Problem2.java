//************************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class creates a minimum spanning tree
//that creates a GUI window and draws a map of the 
//cities connected by the tree using Kruskal's algorithm.
//************************************************

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*; 
import java.io.*;   

public class Problem2
{
	private static KruskalGraph g;
    private static JFrame frame;
    public static JComboBox start, end;
    public static JLabel dist; 

	public static void main(String[] args) 
	{		
		frame = new JFrame();
		final int FRAME_WIDTH = 900;
		final int FRAME_HEIGHT = 650;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Kruskal's Algorithm");
		frame.setLayout(new BorderLayout());

		//Creates a graphical component that is created based off of
		//Kruskal's algorithm.
		g = new KruskalGraph(args);

		frame.add(g, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

		//This class is the graphical component of the GUI. 
		//Kruskal's algorithm is included in this class.
		static class KruskalGraph extends JComponent
		{
			public static HashMap<String, Vertex> cities = new HashMap<String, Vertex>();
			public static PriorityQueue<KruskalEdge> queue = new PriorityQueue<KruskalEdge>();
			public static ArrayList<Line2D.Double> tree = new ArrayList<Line2D.Double>();
			private static String[] arguments; 

			//A constructor that takes in the command line arguments entered
			//by the user. 
			public KruskalGraph(String[] a) 
			{
				this.arguments = a;
				initialize();
				kruskal();
			}


			//This method parses through the files entered by the user, 
			//filling the hashmap and breaking the files into their 
			//individual parts. 
			public void initialize() 
			{
			   //Scans through the first file containing the city and its
			   //x and y coordinates. Puts the city name and its vertex into
			   //a hashmap of cities. 
	           try 
	     	   {   
	            	Scanner in = new Scanner(new File(arguments[0]));

		            while (in.hasNextLine())
		            {
		                String line = in.nextLine();
		                Scanner lineBreaker = new Scanner(line);

		                try 
		                {   
		                	String city = lineBreaker.next();
		                	int x = lineBreaker.nextInt();
		                	int y = lineBreaker.nextInt();

		                	cities.put(city, new Vertex(city, x, y));
		                }
		                catch(Exception e)
		                {
		                	System.exit(0);
		                }
		            }

		            for(Vertex v: cities.values())
		            {
		            	for(Vertex w: cities.values())
		            	{
		            		if(!v.equals(w))
		            		{
		            			queue.add(new KruskalEdge(v,w));
		            		}
		            	}
		            }
				}
				catch (FileNotFoundException e)
	            {
	                System.out.println("File not found.");
	            }
	        }

	        //The paint component of the graph and draws lines and oval to represent
	        //cities and paths, scaled down based on their x and y values. 
			public void paintComponent(Graphics g) 
			{
				Graphics2D g2 = (Graphics2D) g;
				double gWidth = getWidth();
				double gHeight = getHeight();

				g2.setColor(Color.WHITE);
				g2.fill(new Rectangle2D.Double(0, 0, gWidth, gHeight));

				//Draws the paths representing the minimum spanning tree
				//based off of Kruskal's algorithm.
				g2.setColor(Color.RED); 
				for (Line2D.Double l : tree)
				{
					g2.draw(l);
				}

				for (Vertex v : cities.values()) 
				{
					g2.setColor(Color.WHITE);
		        	g2.fillOval(v.x/3+15, 500 - v.y/3-10, 20, 20);

		        	g2.setColor(Color.BLACK);
		        	g2.drawOval(v.x/3+15, 500 - v.y/3-10, 20, 20);

		        	g2.setFont(new Font("arial", Font.BOLD, 8));
		        	g2.drawString(v.name.substring(0,3).toUpperCase(), v.x/3+17,500- v.y/3+4);
				}
			}

			//Uses Kruskal's algorithm. Initially, the set is filled with sets
			//that are each one vertice. In each step, add the cheapest edge 
			//that does not create a cycle. Continues until the sets are merged 
			//into a single set that is the minimum spanning tree. 
			public static void kruskal()
	    	{
	    		ArrayList<KruskalEdge> edges = new ArrayList<KruskalEdge>();

	    		Sets s = new Sets();

	    		//Pulls edges off of the queue and into an ArrayList of 
	    		//KruskalEdges. 
	    		while(!queue.isEmpty())
	    		{
	    			edges.add(queue.poll());
	    		}

	    		//Creates sets of individual vertices from the hashmap 
	    		//of cities. 
	    		for(Vertex v: cities.values())
	    		{
	    			s.createSet(v);
	    		}

	    		//For each edge in the ArrayList of edges, if the sets that 
	    		//contain an edge's starting and ending vertex are not the same,
	    		//add a new line to the minimum spanning tree connecting those 
	    		//vertices and merge the two sets. 
	    		for(KruskalEdge e: edges)
	    		{
	    			if(!s.findSet(e.v1).equals(s.findSet(e.v2)))
	    			{
	    				tree.add(new Line2D.Double(e.v1.x/3+25,500-e.v1.y/3,e.v2.x/3+25,500-e.v2.y/3));
	    				s.union(e.v1, e.v2);
	    				System.out.println("Accepted: " + e);
	    			}
	    		}
	    	}
		}
	}


