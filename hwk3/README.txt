README
szc2103, HWK3 

1) This is a command line application that indexes the words contained in a text file line by line, extracting each word and inserting that word, along
with its location, into an AVL tree. It prints out each unique word and the list of lines on which the word appears. It uses a modified version of Mark
Allen Weiss's AvlTree code, allowing a Node to have a LinkedList of locations attached to it. The constructors, the insert() method, and the printTree() 
method were altered to account for the LinkedLists attached to the nodes. 

I used "Test.txt" to test it.

2) An application that takes in 1 or 2 dictionaries and, after inputing those files in a HashTable, checks a text file for spelling errors. I used a 
modified version of Mark Allen Weiss's QuadraticProbingHashTable code with a new hash function for Strings. To do this, I created an alternative 
hashCode() method for Strings -- if something other than a String is entered, an Exception is caught and the typical hashCode() method is called.

I used "TestDictionary.txt" and "TestFile.txt" to test it, and I have included the given dictionary, as "Dictionary.txt."