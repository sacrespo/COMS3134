//*********************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class takes in user input in 
//the form of postfix expression, evaluates it
//and outputs equivalent infix and prefix
//expressions.
//********************************************

import java.util.*; 
import java.io.*;

//This is a tester class that allows the user to
//input at postfix expression. This expression is 
//turned builds an ExpressionTree, is evaluated, 
//and its prefix and infix equivalents are printed.
public class Problem2
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);

	  System.out.print("Enter a postfix expression: ");
	  char [] expression = scan.next().toCharArray();

	  ExpressionTree tree = new ExpressionTree();
	  tree.buildTree(expression); 

	  System.out.println("The prefix equivalent: " + tree.prefix());
	  System.out.println("The infix equivalent: " + tree.infix());
	  System.out.println("This evaluates to: " + tree.evaluateTree());
   }
}