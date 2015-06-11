//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: An application that takes in a text
//file and builds a Huffman Tree and code from
//it. A GUI is produced that allows users to 
//enter text to encode and decode based off of
//the code generated from the original text file.
//************************************************

import java.awt.*;         
import java.awt.event.*;   
import javax.swing.*;      
import java.io.*;          
import java.util.*;         
import java.awt.geom.*;     

public class Problem1
{
	private static HuffmanTree tree;
    private static JFrame frame;
    private static JTextField encodeField, decodeField;
    private static JTextArea output, keyLabel;
    private static JButton encode, decode;
    private static final Font FONT = new Font("Arial", Font.PLAIN, 12);

  	public static void main(String args[])
  	{

        //Takes in a command line file for the basis of the code.
	  	try
	  	{
	    	tree = new HuffmanTree(new File(args[0]));
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Please enter a file.");
	    	System.exit(0);
	    }

        //Creates a GUI for the Huffman Tree.
	    frame = new JFrame("Huffman Tree Program");
        frame.setLayout(new BorderLayout());

        //Panel for input text fields and buttons
        JPanel inputPanel = new JPanel(new FlowLayout());
        encodeField = new JTextField(20);
        inputPanel.add(new JLabel("Encode: "));
        inputPanel.add(encodeField);

        //When pressed this button encodes the inputted string
        //and displays it in the output text area as well as
        //the command line
        encode = new JButton("GO");
        encode.addMouseListener(new MouseAdapter(){
            //Anonymous subclass of "MouseAdapter";
            //Overide "mousePressed"
        public void mousePressed(MouseEvent e) 
            {
            	try
            	{
            	  String input = Problem1.encodeField.getText();
            	  Problem1.output.setText(Problem1.tree.encode(input));
                  System.out.println(Problem1.tree.encode(input));
            	}
            	catch(Exception excep)
            	{
            		Problem1.output.setText("INVALID INPUT");
            	}
            }
        });
        inputPanel.add(encode, BorderLayout.EAST);

        //Label at the bottom of the GUI displaying the 
        //entire Huffman Code (e.g. a -- 001).
        keyLabel = new JTextArea(4, 60);
        keyLabel.setText(tree.makeKey());
        keyLabel.setFont(FONT);
        keyLabel.setEditable(false);
        keyLabel.setFocusable(false);
        keyLabel.setLineWrap(true);
        frame.add(keyLabel, BorderLayout.SOUTH);

        //When pressed this button decodes the inputted string
        //and displays it in the output text area as well as
        //the command line
        decode = new JButton("GO");
        decode.addMouseListener(new MouseAdapter(){
            //Anonymous subclass of "MouseAdapter";
            //Overide "mousePressed"
            public void mousePressed(MouseEvent e) 
            {
            	try
            	{
            	  String input = Problem1.decodeField.getText();
            	  Problem1.output.setText(Problem1.tree.decode(input));
                  System.out.println(Problem1.tree.decode(input));
            	}
            	catch(Exception excep)
            	{
            		Problem1.output.setText("INVALID INPUT");
            	}
            }
        });

        //Text area to display output
        output = new JTextArea(20, 40);
        output.setFont(FONT);
        output.setEditable(false);
        output.setFocusable(false);
        output.setLineWrap(true);

        //Panel for output text area and drawing of tree
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputPanel.add(output);
        outputPanel.add(tree.g);

        inputPanel.add(new JLabel("Decode: "));
        decodeField = new JTextField(20);
        inputPanel.add(decodeField);
        inputPanel.add(decode);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
  	}
}