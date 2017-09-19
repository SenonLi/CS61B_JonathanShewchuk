/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private List[] bucketsTable;
  private int numberOfBuckets, numberOfEntrys;
  private int numCollisions;

    public int getNumOfCollisions()   { return numCollisions; }
    public int getNumberOfBuckets()   { return numberOfBuckets; }

    /** Sen Add resize to chained HashTable based on load factor, on Sep 18th, 2017 */
    private static final float MAX_LOADFACTOR = 0.996f;
    private static final float MIN_LOADFACTOR = 0.3f;

    private float getLoadFactor(){
        return (float)numberOfEntrys/numberOfBuckets;
    }

    private void resizeBucketsTable(float factor)   {
        int oldNumBuckets = numberOfBuckets;
        List[] oldBucketsTable = bucketsTable;

        numberOfBuckets = nextPrime((int) (numberOfBuckets * factor));
        makeEmpty(); // Create new bucketsTable based on the resized numberOfBuckets
        try{
            for (int i = 0; i < oldNumBuckets; i++) {
                List bucketList = oldBucketsTable[i];
                ListNode currNode = bucketList.front();
                while (currNode != null) {
                    this.insert(((Entry) currNode.item()).key(), ((Entry) currNode.item()).value());
                    currNode = currNode.next();
                }
            }
        }catch (InvalidNodeException e) {
            System.out.println("InvalidNodeException in resizeBucketsTable() " + e.toString());
        }
    }

    /**
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
  public HashTableChained(int sizeEstimate) {
      // Your solution here.
      numberOfBuckets = nextPrime(sizeEstimate);
      bucketsTable = new List[numberOfBuckets];
      for (int i=0; i < bucketsTable.length; i++) {
          bucketsTable[i] = new DList();
      }
      numberOfEntrys  = 0;
      numCollisions   = 0;
  }

  /**
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
      // Your solution here.
      this(100);
  }

  /**
   *  Converts (compress) a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compressFunction(int code) {
      // Replace the following line with your solution.
      //code = (7 * code + 13) % nextPrime(numberOfBuckets * 175);
      code = (233333 * code + 23333333) % 16908799;
      if (code < 0) {
        code = (code + 16908799)  % 16908799;
      }
      return code % numberOfBuckets;
  }

  /**
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
      // Replace the following line with your solution.
      return numberOfEntrys;
  }

  /**
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
      // Replace the following line with your solution.
      return numberOfEntrys == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
      // Replace the following line with your solution.
      Entry entry = new Entry();
      entry.key = key;
      entry.value = value;

      int hashTableIndex = compressFunction(key.hashCode());
      if(!bucketsTable[hashTableIndex].isEmpty())
          numCollisions++;

      bucketsTable[hashTableIndex].insertBack(entry);
      numberOfEntrys++;

      if (this.getLoadFactor() >= MAX_LOADFACTOR){
          this.resizeBucketsTable(2);
      }

      return entry;
  }

  /**
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    if(key != null) {
        int hashTableIndex = compressFunction(key.hashCode());
        if (bucketsTable[hashTableIndex].isEmpty())
          return null;

        ListNode entryNode = bucketsTable[hashTableIndex].front();
        while(entryNode.isValidNode())  {
            try {
                if (entryNode.item() instanceof Entry)  {
                    Entry entry = (Entry)entryNode.item();
                    if (key.equals(entry.key()))
                          return entry;
                    else  entryNode = entryNode.next();
                }else
                  System.err.println("public Entry find(Object key): Invalid entryNode!!");
            }catch  (InvalidNodeException e)  {
                System.err.println("public Entry find(Object key): " + e);
            }
        }
    }
    return null;
  }

  /**
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
      // Replace the following line with your solution.
      if (isEmpty() || key == null)     return null;

      int hashTableIndex = compressFunction(key.hashCode());
      if (bucketsTable[hashTableIndex].isEmpty())
          return null;

      ListNode entryNode = bucketsTable[hashTableIndex].front();
      while(entryNode.isValidNode())  {
          try {
              if (entryNode.item() instanceof Entry)  {
                  Entry entry = (Entry)entryNode.item();
                  if (key.equals(entry.key()))  {
                      entryNode.remove();
                      numberOfEntrys--;
                      if(bucketsTable[hashTableIndex].isEmpty())
                          numCollisions--;

                      if (this.getLoadFactor() <= MIN_LOADFACTOR){
                          this.resizeBucketsTable(0.5f);
                      }
                      return entry;
                  }
                  else  entryNode = entryNode.next();
              }else
                  System.err.println("public Entry find(Object key): Invalid entryNode!!");
          }catch  (InvalidNodeException e)  {
              System.err.println("public Entry find(Object key): " + e);
          }
      }

      return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
      // Your solution here.
      bucketsTable = new List[numberOfBuckets];
      for (int i=0; i < bucketsTable.length; i++) {
          bucketsTable[i] = new DList();
      }
      numberOfEntrys  = 0;
      numCollisions   = 0;
  }

    public String toString() {
        String s = "\n";
        int index = 0;
        for (int i = 1; i <= numberOfBuckets; i++) {
            s += "[" + Integer.toString(bucketsTable[i-1].length())+ "]";
            if (i % 10 == 0)
                s += "\n";
        }
        s += "\n";

        return s;
    }

  /**
   * Return the number of expected collisions given keys selected
   * randomly, where a collision is defined as any addition of a key
   * to a bucket with at least one key already inside it.
   */
  public double expectedCollisions() {
      int n = numberOfEntrys;    // number of keys
      int N = numberOfBuckets;   // number of buckets
      return (n - N + N * Math.pow(1 - 1.0 / N, n));
  }


  /**
     * Private method that finds the next prime number greater than or
     * equal to n.
     * @param numToBegin the number to begin the search at
     * @return the first prime found greater than or equal to n. Returns
     * -1 if a prime is not found less than n + numToGo (200).
     */
  public static int nextPrime(int numToBegin) {
      if (numToBegin <= 2)   return 2;
      final int numToGo = 200;
      boolean[] prime = new boolean[numToBegin + numToGo];
      int i;
      for (i = 2; i < numToBegin + numToGo; i++) {
          prime[i] = true;
      }

      for (int divisor = 2; divisor * divisor < numToBegin + numToGo; divisor++) {
          if (prime[divisor]) {
              for (i = 2 * divisor; i < numToBegin + numToGo; i += divisor) {
                  prime[i] = false;
              }
          }
      }

      for (i = numToBegin; i < numToBegin + numToGo; i++) {
          if (prime[i]) {
              return i;
          }
      }

      return -1; // this should not occur if n < 5 * 10^6
  }


}
