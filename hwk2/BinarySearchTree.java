//********************************************
//PROBLEM: Programming #3
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: A modified version of Mark Allen
//Weiss's code for a binary search tree that
//implements lazy deletion. 
//********************************************

import java.util.*; 
import java.io.*;

//Comments are written for all modified code only.
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    private BinaryNode<AnyType> root;

    public BinarySearchTree( )
    {
        root = null;
    }

    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    public void makeEmpty( )
    {
        makeEmpty(root);
    }

    //Marks all nodes as being deleted. 
    public void makeEmpty(BinaryNode<AnyType> t)
    {
        if( t != null )
        {
            makeEmpty( t.left );
            remove(t.element);
            makeEmpty( t.right );
        }
    }

    public boolean isEmpty()
    {
        return isEmpty(root); 
    }

    //Checks if all nodes have been deleted. 
    public boolean isEmpty(BinaryNode<AnyType> t)
    {
        if( t == null )
            return true;

        if(!t.deleted)   
            return false; 

        if( t.right == null )
            return isEmpty(t.left);
        else if( t.left == null )
            return isEmpty(t.right);
        else 
            return (isEmpty(t.right) && isEmpty(t.left));
    }

    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    //Inserts a Node. If a deleted node is re-inserted, it 
    //sets the boolean deleted to false. 
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            t.deleted = false;  // Duplicate; do nothing
        return t;
    }

    //Removes a Node using lazy deletion. It sets a boolean deleted
    //to true to represent deletion. 
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else
            t.deleted = true;
        return t;
    }

    //Finds the minimum element in the tree not including lazily
    //deleted elements. 
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if ((t.left == null || isEmpty(t.left)) && (t.right == null || isEmpty(t.right)))	//Logically null subtrees.
            return (t.deleted) ? null : t;  
        else if( t.left == null || isEmpty(t.left))			//Logcally only has a right subtree. 
            return (t.deleted) ? findMin(t.right) : t;    		
        else 								//Logically only has a left subtree.
            return findMin(t.left);      
    }

    //Finds the maximum element in the tree not including lazily 
    //deleted elements.
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if ((t.left == null || isEmpty(t.left)) && (t.right == null || isEmpty(t.right)))	//Logically null subtrees.
            return (t.deleted) ? null : t;  
        else if( t.right == null || isEmpty(t.right))			//Logically only has a left subtree.
            return (t.deleted) ? findMax(t.left) : t;     
        else								//Logically only has a right subtree.
            return findMax(t.right);
    }

    //Returns a boolean representing if a certain Node is contained
    //in the tree. Returns false if the Node has previously been deleted.
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return !(t.deleted);    // Match
    }

    //Prints the tree, replacing deleted nodes with "X". 
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            if(t.deleted)
                System.out.println("X");
            else
                System.out.println( t.element );
            printTree( t.right );
        }
    }

    //Lazily deleted nodes are still included in height
    //calculation. 
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    //The class to create BinaryNodes is essentially the same, 
    //however, a boolean is added to record if a Node is deleted
    //or not. It is set to false when the BinaryNode is first 
    //constructed. 
    private static class BinaryNode<AnyType>
    {   
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        boolean deleted; 

        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            deleted = false; 
        }
    }

    //A tester class designed to show the newly modified
    //methods work. 
    public static void main( String [ ] args )
    {
        BinarySearchTree tree = new BinarySearchTree(); 
        
        tree.insert(6);
        tree.insert(5);
        tree.insert(3);
        tree.insert(4);
        tree.insert(7);
        tree.insert(2);
        tree.insert(1);
        tree.insert(9);
        tree.insert(8);
        tree.insert(10);

        tree.printTree();
        System.out.println("Does the tree contain 7?" + tree.contains(7));
        
        tree.remove(7);
        tree.printTree();
        System.out.println("Does the tree contain 7?" + tree.contains(7));
        
        tree.insert(7);
        tree.printTree();
        System.out.println("Does the tree contain 7?" + tree.contains(7));

        System.out.println("Max: " + tree.findMax());
        System.out.println("Min: " + tree.findMin());

        tree.remove(6);
        tree.remove(1);
        tree.remove(2);
        tree.remove(10);
        tree.remove(9);

        tree.printTree();
        System.out.println("Max: " + tree.findMax());
        System.out.println("Min: " + tree.findMin());

        tree.makeEmpty();
        tree.printTree();
        System.out.println("Is the tree empty?: " + tree.isEmpty(tree.root));
        
        tree.insert(4);
        tree.printTree();
        System.out.println("Max: " + tree.findMax());
        System.out.println("Min: " + tree.findMin());

    }
}

class UnderflowException extends RuntimeException {}