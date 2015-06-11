//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: The blueprint for a HuffmanTree object
//that takes in files, or Strings and converts 
//them into a series of 0s and 1s based on the
//frequency of each symbol appearing. The code for
//the creation of the graphical portion of the GUI
//can be found in this class as well, as the graph
//needed to access HuffmanTree variables.
//************************************************

import java.io.*;
import java.util.*;
import java.awt.*;      //Dimension, Graphics, Color, Font
import java.awt.geom.*; 
import javax.swing.*;   //JComponent

public class HuffmanTree implements Comparable<HuffmanTree>
{	
	private char[] input;	//All symbols in the original text file. 
	private ArrayList<Character> letters;  //Each symbol in the text file without duplicates. 
	private HashMap<Character, Integer> freq;	//Each symbol and its corresponding frequency.
	private HashMap<Character, String> code;	//Each symbol and its corresponding code, e.g. a -- 001. 
	private PriorityQueue<HuffmanTree> priorityQueue;	//A priority queue filled with the HuffmanTree. 
	private Node root;
	public GraphComponent g;
	
	//A constructor that takes in a Node.
	public HuffmanTree(Node r) 
	{		
		root = r;
	}

	//A constructor that takes in a File. 
	//The file is then processed through different
	//methods to parse it, fill the priority queue,
	//build the Huffman code, write the code, and 
	//create the graphical GUI representing it.
	public HuffmanTree(File doc) 
	{		
		priorityQueue = new PriorityQueue<HuffmanTree>();
		freq = new HashMap<Character, Integer>(); 
        code = new HashMap<Character, String>();
        letters = new ArrayList<Character>();
		parseFile(doc);
		fillQueue();
		constructTree();
		buildCode(root, "");
		writeCode();
 		g = new GraphComponent(height(), root);
	}

	//A constructor that takes in the user's input from
	//the GUI; a String of text. The text is parsed, 
	//the priority queue is filled, the code is built, 
	//and written.
	public HuffmanTree(String text) 
	{		
		priorityQueue = new PriorityQueue<HuffmanTree>();
		freq = new HashMap<Character, Integer>(); 
        code = new HashMap<Character, String>();
        letters = new ArrayList<Character>();

		parseString(text);
		fillQueue();
		constructTree();
		buildCode(root, "");
		writeCode();
	}

	//A method that prints out the Huffman code in
	//a table in the console. In prints out the symbol 
	//followed by its frequency and its code. 
	private void writeCode()
	{
		System.out.println("Symbol | Frequency | Code");
		for(int i=0; i< letters.size(); i++)
		{
			if(letters.get(i) == ' ')
			{
				System.out.println("   sp  |     "+freq.get(letters.get(i))+"     | "+code.get(letters.get(i)));
			}
			else if(letters.get(i) == '\n')
			{
				System.out.println("   nl  |     "+freq.get(letters.get(i))+"     | "+code.get(letters.get(i)));
			}
			else
			{
				System.out.println("   "+letters.get(i)+"   |     "+freq.get(letters.get(i))+"     | "+code.get(letters.get(i)));
			}
		}
	}
	
	//A method that builds the Huffman code by taking
	//in a Node and a String of code that is continually
	//built onto over the course of repeated method calls.
	private void buildCode(Node node, String codeLine) 
	{
		if(node != null) 		//If the node is not null...
		{
			if(node.left == null && node.right == null)	//If the node is a leaf...
			{
				code.put(node.getSymbol(), codeLine);	//Put the symbol and its corresponding code into the HashMap.
				node.setCode(codeLine);		//Set the code attribute of the Node object to the code. 
			}

			this.buildCode(node.getLeftChild(), codeLine + "0");	//Every time the program goes to the left it adds a "0" to the code.
			this.buildCode(node.getRightChild(), codeLine + "1");	//Every time the program goes to the right it adds a "1" to the code.
		}	
	}
	
	//A method that constructs the complete HuffmanTree by 
	//taking the tiny trees off the priority queue 
	//and combining them, then combining the resulting
	//tree with another tree (of one node) until all of the
	//trees have been combined. 
	private void constructTree()
	{
		HuffmanTree tree, right, left;

		while(priorityQueue.size() > 1) 
		{
			right = priorityQueue.deleteMin();
			left = priorityQueue.deleteMin();	
			tree = new HuffmanTree(new Node(null, right.root.getFreq() + left.root.getFreq(), left.root, right.root));

			priorityQueue.insert(tree);	//Inserts a tree that has been combined back into the priority queue.
		}
		this.root= priorityQueue.deleteMin().root; 
	}
	
	//A method that fills the priority queue with tiny
	//HuffmanTrees characterized by a Node representing a 
	//symbol and the frequency of that symbol.
	private void fillQueue()
	{	
		HuffmanTree tree;
		for(int i = 0; i < letters.size(); i++) 
		{
			tree = new HuffmanTree(new Node (letters.get(i), freq.get(letters.get(i)), null, null));	//Adds a tree of one node to the priority queue.
			priorityQueue.insert(tree);
		}
	}

	//A method that parses through the text file, 
	//turning it into a String, then into a char
	//array containing all the symbols in the file. 
	//This array is then filtered into an
	//ArrayList that contains each symbol once. The
	//frequency of each symbol is determined in this
	//method as well.
	private void parseFile(File doc)
	{
		try
		{
	 		Scanner scanner = new Scanner(doc);


	        String text = scanner.nextLine();
	        int count = 0;
	        
	        //Reads the file into a String. 
	        while (scanner.hasNextLine()) 
	        {
	               text = text + scanner.nextLine() +"\n";
	        }
	        input = text.toCharArray();	//Converts the String into a char array.

	        int temp = 0;

	        //Adds each symbol into an ArrayList with no
	        //duplicate symbols. Records the frequency 
	        //of each symbol onto a HashMap. 
	        for (int i = 0; i < input.length; i++)
	        {
	            if(letters.contains(input[i]))
	            {
	            	temp = freq.get(input[i]);
	            	freq.put(input[i], temp+1);
	            }
	            else
	            {
	            	freq.put(input[i], 1);
	            	letters.add(input[i]);
	            }
	        }

        }
 		catch(FileNotFoundException fnfe)
 		{
 			System.exit(0);
 		}  
	}

	//A method that parses through a String, 
	//turning it into a char array, then 
	//adding each symbol to an ArrayList. 
	private void parseString(String text)
	{
	        input = text.toCharArray();

	        int temp = 0;

	        for (int i = 0; i < input.length; i++)
	        {
	            if(letters.contains(input[i]))
	            {
	            	temp = freq.get(input[i]);
	            	freq.put(input[i], temp+1);
	            }
	            else
	            {
	            	freq.put(input[i], 1);
	            	letters.add(input[i]);
	            }
	        }
	}

	//A method that compares the frequencies of 
	//trees (as noted by the value stored in the parent 
	//node) to determine the order of each symbol being 
	//placed in the queue. 
	public int compareTo(HuffmanTree tree)
	{
		if(this.root.getFreq()>tree.root.getFreq())
		{
			return 1;
		}
		else if(this.root.getFreq()<tree.root.getFreq())
		{
			return -1;
		}
		else
		{	
			return 0;
		}
	}

	//A method that takes in a String of symbols
	//and outputs a code of 0s and 1s based off of 
	//the Huffman Code determined by the initial 
	//file encoding. 
	public String encode(String text)
	{
		char[] tokens = text.toCharArray();		
		String output = ""; 

		for(char c: tokens)
		{
			if(code.get(c) == null)
			{
				throw new InvalidSymbolException();
			}
			else
			{
				output += code.get(c);		
			}	
		}

		return output;
	}

	//Takes in a String of 0s and 1s and outputs
	//the decoded String of symbols based off of the
	//Huffman Code determined by the initial file 
	//encoding. 
	public String decode(String text)
	{
		char[] tokens = text.toCharArray(); 
		String output = "";
		Node node = root;

		for(char c: tokens)
		{
			if(node.getLeftChild() == null && node.getRightChild() == null)	//If the node is a leaf...
			{
				if(node.getSymbol() == null)	//If the node is null an exception is thrown.
				{
					throw new InvalidSymbolException();
				}
				else				//If you reach a leaf based on the sequence of 0s and 1s, the symbol in the node is added to the output.
				{
					output += node.getSymbol();
					node = root;	//Resets so the tree can be traversed from the root again. 
				}	
			}

			if(c == '0')	//If the char is a 0, go left down the tree.
			{
				node = node.getLeftChild();
			}
			else if (c == '1')	//If the char is a 1, go right down the tree. 
			{
				node = node.getRightChild();
			}
			else //Else throw an error
			{
				throw new InvalidSymbolException();
			}
		}

		//Decodes the last character. 
		if(node.getLeftChild() == null && node.getRightChild() == null)
		{
			if(node.getSymbol() == null)
			{
				throw new InvalidSymbolException(); //If the node is null an exception is thrown.
			}
			else
			{
				output += node.getSymbol();
			}	
		}

		return output;
	}

	//A method that returns a String containing symbols
	//and their codes for a key displayed at the bottom
	//of the GUI.
	public String makeKey()
	{
		String key = "Key:";

		for(Character c: letters)
		{
			if(c == ' ')
			{
				key += "  \"" + "sp" + "\" = " + code.get(c) + ",";
			}
			else if(c == '\n')
			{
				key += "  \"" + "nl" + "\" = " + code.get(c) + ",";
			}
			else
			{
				key += "  \"" + c + "\" = " + code.get(c) + ",";
			}
		}

		return key;
	}

	//A driver method to find the height of the
	//tree.
	public int height()
	{
		return height(root);
	}

	//A recursive method based off of that used 
	//for binary trees to find the height of the 
	//tree. 
	private int height(Node node)
    {
        if(node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));    
    }

    //A private static class to create Node
    //objects that record a symbol, the symbol's
    //frequency, the left and right child nodes of
    //that node, and the code of the symbol.
	private static class Node
    {
        private Character ch;		//The symbol.
        private int freq;			//The frequency of the symbol.
        private Node left;			//The left child of the node.
        private Node right;			//The right child of the node.
        private String code;		//The code of 0s and 1s of the symbol.

        private boolean codeSet;	//A boolean for if the code has been set for the node. 

        //A constructor for a Node object that
        //has a Character symbol and an int for the
        //frequency of that symbol appearing, as well as
        //two Nodes that are that Node's children.
        public Node(Character symbol, int frequency, Node left, Node right) 
        {
            ch = symbol;
            freq = frequency; 
            this.left = left;
            this.right = right;

            codeSet = false;
        }

        //A method that sets the code for the symbol 
        //into the Node. Changes codeSet to true to show
        //the code for that node has been set. 
        public void setCode(String code)
        {
        	if(!codeSet)
        	{
        		this.code = code;
        		codeSet = true;
        	}
        }

        //An accessor method to return the code at that
        //Node.
        public String getCode()
        {
        	return code;
        }

        //An accessor method to return the symbol at that
        //Node.
        public Character getSymbol() 
        {
            return ch; 
        }

        //An accessor method to return the right child at that
        //Node.
        public Node getRightChild() 
        {
            return right;
        }

        //An accessor method to return the left child at that
        //Node.
        public Node getLeftChild() 
        {
            return left;
        }

        //An accessor method to return the frequency at that
        //Node.
        public int getFreq() 
        {
            return freq;
        }
    }

    //A class for the GraphComponent of the GUI; creates
    //the HuffmanTree visual with points for each node in the 
    //tree and the codes displayed beneath each leaf. 
	private class GraphComponent extends JComponent
	{
		private Node n; 
		private int height; 

		//A constructor that sets the height of the area in
		//which the graph is created. Takes the node at which
		//the graph begins. 
		public GraphComponent(int height, Node n)
		{
			this.n = n; 
			this.height = height; 
	        setPreferredSize(new Dimension(height*100+50, height*100+50));
	    }

	    //A method to set up the major aspects of the GUI: 
	    //the color, the font, and the graphics. 
	    public void paintComponent(Graphics g)
	    {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setColor(Color.BLACK);
	        g2.setFont(new Font("Arial", Font.BOLD, 7));
	        drawTree(g2);
	    }

	    //A driver method to draw the tree. 
	    public void drawTree(Graphics2D g2)
	    {
	    	drawTree(g2, this.n, height*50, 5, height*25);
	    }

	    //A recursive method to draw the tree that
	    //takes in Graphics2D, a node, an x and y for 
	    //coordinates, and a displacement value determined 
	    //by the height to keep nodes spaced properly.
	   	public void drawTree(Graphics2D g2, Node node, int x, int y, int displacement)
	    {
	    	double factor = 0.50;		//A factor to keep displacement changing as each level of the tree has more nodes.

	    	if(node != null)	
	    	{	
	    		if(node.getLeftChild() != null)	//If a left child exists, draw a line to the left child, then use drawTree on the left child. 
	    		{
	    			g2.drawLine(x, y, x-displacement, y+100);
	    			drawTree(g2, node.getLeftChild(), x-displacement, y+100, (int)(factor*displacement));
	    		}

	    		//Draw node
	    		g2.fillOval(x-5,y-5, 10, 10);

	    		//Draw code
	    		g2.setColor(Color.RED);
	    		if(node.getCode()!=null)
	    			g2.drawString(node.getCode(), x-10, y+10);
	    		g2.setColor(Color.BLACK);
	    		
	    		if(node.getRightChild() != null) //If a right child exists, draw a line to the right child, then use drawTree on the right child. 
	    		{
	    			g2.drawLine(x, y, x+displacement, y+100);
	    			drawTree(g2, node.getRightChild(), x+displacement, y+100, (int)(factor*displacement));
	    		}
	    	}
	    }
	}
}
class InvalidSymbolException extends RuntimeException {}
