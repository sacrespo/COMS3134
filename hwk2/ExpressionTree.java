//*********************************************
//PROBLEM: Programming #2
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: This class builds expression trees
//based off of user input.
//********************************************

import java.util.*; 
import java.io.*;

public class ExpressionTree
{
  //This class is a blueprint to create individual
  //ExpressionNode objects. 
  private static class ExpressionNode 
  {
    Character element;
    ExpressionNode left;
    ExpressionNode right;

    //This is a one-parameter constructor that calls
    //the three-paramenter constructor. 
    ExpressionNode(Character e)
    {
      this(e, null, null);
    }

    //This three-parameter constructor gives the ExpressionNode
    //a child on the left and a child on the right. 
    ExpressionNode(Character e, ExpressionNode l, ExpressionNode r)
    {
      element = e;
      left = l;
      right = r;
    }
  }

  private ExpressionNode root;
  private MyStack<ExpressionTree> stack = new MyStack<ExpressionTree>();

  //This constructor creates an ExpressionTree with a null root.
  public ExpressionTree()
  {
    root = null;
  }  

  //This constructor creates an ExpressionTree with a certain
  //ExpressionNode r as its root. 
  public ExpressionTree(ExpressionNode r)
  {
    root = r; 
  }

  //This constructor creates an ExpressionTree in which the root is an 
  //operator and the two children are operands. 
  public ExpressionTree(ExpressionTree tree1, ExpressionTree tree2, ExpressionNode operand)
  {
    root = operand; 
    root.left = tree1.root;
    root.right = tree2.root; 
  }

  //This method builds the tree using an array of characters. 
  //If the character is not an operator it pushes on an ExpressionTree of 
  //a single node. If the character is an operand, it pops previous
  //ExpressionTrees off of the stack and creates a new ExpressionTree 
  //using the popped operands as the children of the operator (the root). 
  public void buildTree(char [] input)
  {
    for(int i=0; i < input.length; i++)
    {
      if(Character.isDigit(input[i]))
      {
        stack.push(new ExpressionTree(new ExpressionNode(input[i])));
      }
      else
      {
	try
	{
       	 	ExpressionTree op2 = stack.pop();
        	ExpressionTree op1 = stack.pop(); 
       		stack.push(new ExpressionTree(op1, op2, (new ExpressionNode(input[i]))));
	}
	catch(StackUnderflowException e)
	{
		System.err.println("Improper postfix expression.");
		System.exit(0);
	}		
      }
    }
    this.root = stack.pop().root;
    if(!stack.isEmpty()) 
    {
	System.err.println("Improper postfix expression."); 
 	System.exit(0); 
    }
  }

  //This is the driver method for evaluateTree().
  public int evaluateTree()
  {
    return evaluateTree(this.root);
  }

  //This method evaluates the ExpressionTree.
  public int evaluateTree(ExpressionNode node)
  {
    if(node.left == null && node.right == null)			//This is an operand. It returns the Integer value of this operand. 
    {
      return Integer.parseInt(node.element.toString());
    }
    else
    {
      if(node.element == '+')					//Evaluates two operand nodes with the operator '+'.
      {
        return evaluateTree(node.left) + evaluateTree(node.right);
      }
      if(node.element == '-')					//Evaluates two operand nodes with the operator '-'.
      {
        return evaluateTree(node.left) - evaluateTree(node.right);
      }
      if(node.element == '*')					//Evaluates two operand nodes with the operator '*'.
      {
        return evaluateTree(node.left) * evaluateTree(node.right);
      }
      if(node.element == '/')					//Evaluates two operand nodes with the operator '/'.
      {
        return evaluateTree(node.left) / evaluateTree(node.right);
      }
 	return 0;						//Appease the compiler. 
    }
  }
    //Driver method for prefix(). 
    public String prefix()
    {
      return prefix(this.root);
    }

    //Uses preorder traversal to print out prefix expression.
    public String prefix(ExpressionNode node)
    {
      if(node.left == null && node.right == null)
      {
        return node.element.toString();
      }
      else
      {
        return node.element.toString() + prefix(node.left) + prefix(node.right); 
      }
    }

    //Driver method for infix().
    public String infix()
    {
      return infix(this.root);
    }

    //Uses in order traversal to print out infix expression. 
    public String infix(ExpressionNode node)
    {
      if(node.left == null && node.right == null)
      {
        return node.element.toString();
      }
      else
      {
        return "(" + infix(node.left) + node.element.toString() + infix(node.right) + ")";
      }
    }
}
