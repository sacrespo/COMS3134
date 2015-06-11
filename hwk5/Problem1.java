//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class calculates the shortest path
//between any given city pair using Dijkstra's 
//algorithm. It reads in the city pairs and distances
//from a text file and inputs vertices into a priority
//queue based on Dijkstra. The GUI allows users to
//choose two cities and maps the cities onto a window, 
//drawing the shortest path between them. 
//************************************************

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*; 
import java.io.*;   

public class Problem1
{
	private static DijkstraGraph g;
    private static JFrame frame;
    public static JComboBox start, end;
    public static JLabel dist; 

	public static void main(String[] args) 
	{		
		frame = new JFrame();
		final int FRAME_WIDTH = 900;
		final int FRAME_HEIGHT = 650;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Dijkstra's Algorithm");
		frame.setLayout(new BorderLayout());

		//Creates a graphical component that is created based off of
		//Dijkstra's algorithm.
		g = new DijkstraGraph(args);

		start = new JComboBox(g.cities.keySet().toArray(new String[g.cities.keySet().size()]));
        JLabel startLabel = new JLabel("Start:");

        end = new JComboBox(g.cities.keySet().toArray(new String[g.cities.keySet().size()]));
        JLabel endLabel = new JLabel("End:");

        dist = new JLabel("Distance: 0.0");

        //Creates a button that, when pressed, uses Dijkstra's algorithm
        //to find the shortest path between two selected cities (start and end)
        //and draws the shortest path by repainting the graph. 
        JButton go = new JButton("GO");
        go.addMouseListener(new MouseAdapter(){
            //Anonymous subclass of "MouseAdapter";
            //Overide "mousePressed"
            public void mousePressed(MouseEvent e) 
            {
                DijkstraGraph.path.clear();
                
                String start = Problem1.start.getSelectedItem().toString();
                String end = Problem1.end.getSelectedItem().toString();

                Problem1.g.computePaths(Problem1.g.cities.get(start));
                Problem1.g.getShortestPath(Problem1.g.cities.get(end));
                Problem1.dist.setText("Distance: "+(Problem1.g.cities.get(end).distance-Problem1.g.cities.get(end).distance%0.001));
                System.out.println();
                Problem1.g.repaint();
            }
        });

        JPanel choose = new JPanel();
        choose.add(startLabel);
        choose.add(start);
        choose.add(endLabel);
        choose.add(end);
        choose.add(go);
        choose.add(dist);

		frame.add(g, BorderLayout.CENTER);
		frame.add(choose, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	

		
	}
		//This class is the graphical component of the GUI. 
		//Dijkstra's algorithm is included in this class.
		static class DijkstraGraph extends JComponent
		{
			public static HashMap<String, Vertex> cities = new HashMap<String, Vertex>();
			public static ArrayList<Line2D.Double> path = new ArrayList<Line2D.Double>();
			private static String[] arguments; 

			//A constructor that takes in the command line arguments entered
			//by the user. 
			public DijkstraGraph(String[] a) 
			{
				this.arguments = a;
				initialize();
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

		            //Scans through the second file and breaks it down into
		            //two cities and the weight of the edge between the two 
		            //cities. Adds these cities into the adjacency lists of 
		            //each city in the hashmap. 
		            in = new Scanner(new File(arguments[1]));

		            while (in.hasNextLine())
		            {
		                String line = in.nextLine();
		                Scanner lineBreaker = new Scanner(line);

		                try 
		                {   
		                	String city1 = lineBreaker.next();
		                	String city2 = lineBreaker.next();
		                	double weight = lineBreaker.nextDouble();

		                	cities.get(city1).adj.add(new Edge(cities.get(city2), weight));
		                	cities.get(city2).adj.add(new Edge(cities.get(city1), weight));
		                }
		                catch(Exception e)
		                {
		                	System.exit(0);
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
				g2.setColor(Color.BLACK); 
				for(Vertex v : cities.values())
        		{
		            for(Edge e : v.adj)
		            {
		                g2.drawLine(v.x/3+25,500-v.y/3,e.target.x/3+25,500-e.target.y/3);
		            }
		        }

		        //Draws over the initial paths in red, representing 
		        //paths that make up the shortest path between two cities. 
				g2.setColor(Color.RED); 
				for (Line2D.Double l : path)
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

			//This method is Dijkstra's algorithm using a priority queue. 
			public static void computePaths(Vertex source)
	    	{

	        	PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();

	        	for(Vertex v: cities.values())
	        	{
	        		v.distance = Double.MAX_VALUE;
            		v.path = null;
	        	}

	        	source.distance = 0;
		      	vertexQueue.add(source);

		    	while (!vertexQueue.isEmpty()) 
		        {
		    	    Vertex u = vertexQueue.poll();

		            for (Edge e : u.adj)
		            {
		                if (u.distance + e.weight < e.target.distance) 
		                {
		            	    e.target.distance = u.distance + e.weight;
		            	    e.target.path = u;
		            	    if(!vertexQueue.contains(e.target))
		            	    {
		            	    	vertexQueue.add(e.target);
		            	    }
		            	}
		            }
		        }
	    	}

	    	//This method builds the shortest combination of paths between
	    	//two cities and adds these paths to a list of 2D lines to be
	    	//drawn. 
		    public static void getShortestPath(Vertex v)
		    {
		      
		       if(v.path != null)
		       {
		            getShortestPath(v.path);
		            path.add(new Line2D.Double(v.x/3+25,500-v.y/3,v.path.x/3+25,500-v.path.y/3));
		            System.out.print(" to ");
		       }
		       System.out.print(v.name);
		    }
		}
	}


