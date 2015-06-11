//************************************************
//PROBLEM: Programming #1
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: An edited version of Weiss's binary 
//heap code. NOTE: only edited methods have been
//commented. 
//************************************************

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class PriorityQueue<AnyType extends Comparable<? super AnyType>>
{
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;   
    private AnyType [ ] array; 

    public PriorityQueue()
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1 ];
    }

    public PriorityQueue(int capacity)
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity+ 1 ];
    }
    
    public PriorityQueue(AnyType[] items)
    {
            currentSize = items.length;
            array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

            int i = 1;
            for( AnyType item : items )
                array[ i++ ] = item;
            buildHeap( );
    }
    
    public void insert(AnyType x)
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

            // Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }

    private void enlargeArray( int newSize )
    {
            AnyType [] old = array;
            array = (AnyType []) new Comparable[ newSize ];
            for( int i = 0; i < old.length; i++ )
                array[ i ] = old[ i ];        
    }
    
    //Added a size() method to return an int
    //of the size of the priority queue. 
    public int size( )
    {
        return currentSize;
    }

    public void clear( )
    {
        currentSize = 0;
    }

    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[1];
    }

    public AnyType deleteMin( )
    {
        AnyType min = findMin( );
        array[1] = array[ currentSize-- ];
        percolateDown(1);

        return min;
    }

    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
}
class UnderflowException extends RuntimeException {}