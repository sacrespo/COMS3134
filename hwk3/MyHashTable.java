//*********************************************
//PROBLEM: Programming #2, MyHashTable Class
//NAME: Sheryl Crespo
//UNI: szc2103
//PURPOSE: A modified version of Mark Allen 
//Weiss's QuadraticProbingHashTable code
//with a new hash function for Strings.
//NOTE: I have only commented parts I have 
//altered.
//********************************************

public class MyHashTable<AnyType>
{
    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType>[] array;
    private int occupied;                 
    public int theSize;

    public MyHashTable()
    {
        this(DEFAULT_TABLE_SIZE);
    }

    public MyHashTable(int size)
    {
        allocateArray(size);
        doClear();
    }

    public boolean insert(AnyType x)
    {
        int currentPos = findPos(x);
        if(isActive(currentPos))
            return false;

        array[currentPos] = new HashEntry<AnyType>(x, true);
        theSize++;

        if(++occupied > array.length / 2)
            rehash();
        
        return true;
    }

    private void rehash()
    {
        HashEntry<AnyType>[] oldArray = array;

        allocateArray(2 * oldArray.length);
        occupied = 0;
        theSize = 0;

        for(HashEntry<AnyType> entry : oldArray)
            if(entry != null && entry.isActive)
                insert(entry.element);
    }

    private int findPos(AnyType x)
    {
        int offset = 1;
        int currentPos = myhash(x);
        
        while(array[ currentPos ] != null &&
                !array[ currentPos ].element.equals(x))
        {
            currentPos += offset;
            offset += 2;
            if(currentPos >= array.length)
                currentPos -= array.length;
        }
        
        return currentPos;
    }

    public boolean remove(AnyType x)
    {
        int currentPos = findPos(x);
        if(isActive(currentPos))
        {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        }
        else
            return false;
    }

    public int size()
    {
        return theSize;
    }

    public int capacity()
    {
        return array.length;
    }

    public boolean contains(AnyType x)
    {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    private boolean isActive(int currentPos)
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    public void makeEmpty()
    {
        doClear();
    }

    private void doClear()
    {
        occupied = 0;
        for(int i = 0; i < array.length; i++)
            array[ i ] = null;
    }
    
    //New hash function for Strings. 
    //If something other than a String is
    //entered, an Exception is caught and 
    //the typical hashCode() method is called.
    private int hashCode(AnyType x)
    {
        try
        {
            String string = (String) x;

            int hashVal = 0;
            for(int i = 0; i < string.length(); i++)
            {
                hashVal = hashVal * 41 + string.charAt(i);
            }
            return hashVal;
        }
        catch(ClassCastException cce)
        {
            return x.hashCode();
        }
    }
    
    //Implements new hash function via
    //the altered hashCode() method.
    private int myhash(AnyType x)
    {
        int hashVal = hashCode(x);

        hashVal %= array.length;
        if(hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }
    
    private static class HashEntry<AnyType>
    {
        public AnyType  element; 
        public boolean isActive;
        
        public HashEntry(AnyType e)
        {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i)
        {
            element  = e;
            isActive = i;
        }
    }

    private void allocateArray(int arraySize)
    {
        array = new HashEntry[nextPrime(arraySize)];
    }

    private static int nextPrime(int n)
    {
        if(n % 2 == 0)
            n++;

        while(!isPrime(n))
        {  
            n += 2;
        }

        return n;
    }

    private static boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
            return true;

        if(n == 1 || n % 2 == 0)
            return false;

        for(int i = 3; i * i <= n; i += 2)
            if(n % i == 0)
                return false;

        return true;
    }
}