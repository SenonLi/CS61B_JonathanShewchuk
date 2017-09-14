/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q){
      // Replace the following line with your solution.
      if (q == null) return null;
      LinkedQueue queueOfQueues = new LinkedQueue();

      while(!q.isEmpty()) {
          LinkedQueue queueObject = new LinkedQueue();

          try {
              queueObject.enqueue(q.dequeue());
          } catch(QueueEmptyException e) {
              System.err.println("In makeQueueOfQueues()" + e);
          }

          queueOfQueues.enqueue(queueObject);
      }

      return queueOfQueues;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException{
      // Replace the following line with your solution.
      if(q1 == null || q2 == null)    throw new QueueEmptyException("null in mergeSortedQueues");

      LinkedQueue mergedSortedQueue = new LinkedQueue();
      try {
          while(!q1.isEmpty() && !q2.isEmpty()) {
              Object nextSmallest = ((Comparable)q1.front()).compareTo(q2.front()) < 0 ? q1.dequeue() : q2.dequeue();
              mergedSortedQueue.enqueue(nextSmallest);
          }
          while(!q1.isEmpty())   mergedSortedQueue.enqueue(q1.dequeue());
          while(!q2.isEmpty())   mergedSortedQueue.enqueue(q2.dequeue());
      } catch(QueueEmptyException e) {
          System.err.println("In mergeSortedQueues()" + e);
      }
      return mergedSortedQueue;
  }

  private static LinkedQueue mergeVeryTwoNodes(LinkedQueue queueOfQueues) {
      LinkedQueue mergedQueueOfQueues = new LinkedQueue();
      if(queueOfQueues != null) {
          while (!queueOfQueues.isEmpty()) {
              try {
                  Object firstQueue = queueOfQueues.dequeue();
                  Object secondQueue = queueOfQueues.isEmpty() ? new LinkedQueue() : queueOfQueues.dequeue();
                  mergedQueueOfQueues.enqueue(mergeSortedQueues((LinkedQueue)firstQueue, (LinkedQueue)secondQueue));
              } catch(QueueEmptyException e) {
                  System.err.println("In mergeVeryTwoNodes()" + e);
              }
          }
      }
      return mergedQueueOfQueues;
  }
  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
      // Your solution here.
      /** 1. Split into single Object Queues and save and a queueOfQueues */
      LinkedQueue splitedQueueOfQueues = makeQueueOfQueues(q);
      /** 2. mergeSort recursively into a one-sorted-queue queueOfQueues */
      while(splitedQueueOfQueues.size() > 1)  {
          splitedQueueOfQueues = mergeVeryTwoNodes(splitedQueueOfQueues);
      }
      /** 3. move sorted objects in the one-sorted-queue queueOfQueues back to queue q */
      try {
          LinkedQueue sortedQueue = (LinkedQueue)splitedQueueOfQueues.front();
          while(!sortedQueue.isEmpty())     q.enqueue(sortedQueue.dequeue());
      }catch(QueueEmptyException e) {
          System.err.println("In mergeSort()" + e);
      }
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    */
  }

}
