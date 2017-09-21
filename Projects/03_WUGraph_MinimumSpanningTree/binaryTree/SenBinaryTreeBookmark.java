/* SenBinaryTreeIterator.java */

package binaryTree;

import java.util.NoSuchElementException;

public class SenBinaryTreeBookmark  {

  /**
   *  Define any variables associated with a RunIterator object here.
   *  These variables MUST be private.
   */
    BinaryTreeNode currRunNode;

  /**
   *  RunIterator() constructs a new iterator starting with a specified run.
   *
   *  @param beginNode the run where this iterator starts.
   */
  public SenBinaryTreeBookmark(BinaryTreeNode beginNode) {
      // Your solution here.  You may add parameters to the method signature.
      currRunNode = beginNode;
  }

  /**
   *  hasNext() returns true if this iterator has more runs.  If it returns
   *  false, then the next call to next() may throw an exception.
   *
   *  @return true if the iterator has more elements.
   */
  public boolean hasNext() {
      // Replace the following line with your solution.
      return currRunNode != null && currRunNode.isValidTreeNode(); // Attention: not currRunNode.next != null
  }

  public Object nextAscend() throws Exception{
      if (currRunNode == null)  return null;
      if (!currRunNode.isValidTreeNode())
          throw new Exception("Error invalid currRunNode!! this node doesn't belong to a Tree ! ");

      Entry currEntry = currRunNode.entry;
      /** point to the null if currRunNode already the rightest */
      if (currRunNode == currRunNode.myTree.getRightestTreeNode())  {
          currRunNode = null;
          return currEntry.value();
      }

      if (currRunNode.rightChild != null) {
          /** descend, go for the rightChild's leftest */
          currRunNode = currRunNode.rightChild;
          while (currRunNode.leftChild != null)   currRunNode = currRunNode.leftChild;
      }else {
          if (currRunNode.parent == null)
              throw new Exception("Error rightestNode!! Cannot find a larger Node! ");
          /** Re-assign currRunNode its predecessor that on its righter */
          currRunNode = currRunNode.parent;
          while ( ((Comparable)currRunNode.entry.key()).compareTo(currEntry.key()) < 0) {
              if (currRunNode.parent.parent == null)
                  throw new Exception("Error rightestNode!! Cannot find a larger Node! ");
              currRunNode = currRunNode.parent;
          }
      }
      return currEntry.value();
  }

  public Object nextDescend() throws Exception {
      if (currRunNode == null)  return null;
      if (!currRunNode.isValidTreeNode())
          throw new Exception("Error invalid currRunNode!! this node doesn't belong to a Tree ! ");

      Entry currEntry = currRunNode.entry;
      /** point to the null if currRunNode already the leftest */
      if (currRunNode == currRunNode.myTree.getLeftestTreeNode())  {
          currRunNode = null;
          return currEntry.value();
      }

      if (currRunNode.leftChild != null) {
          /** descend, go for the leftChild's rightest */
          currRunNode = currRunNode.leftChild;
          while (currRunNode.rightChild != null)   currRunNode = currRunNode.rightChild;
      }else {
          if (currRunNode.parent == null)
              throw new Exception("Error leftestNode!! Cannot find a smaller Node! ");
          /** Re-assign currRunNode its predecessor that on its righter */
          currRunNode = currRunNode.parent;
          while ( ((Comparable)currRunNode.entry.key()).compareTo(currEntry.key()) > 0) {
              if (currRunNode.parent.parent == null)
                  throw new Exception("Error leftestNode!! Cannot find a smaller Node! ");
              currRunNode = currRunNode.parent;
          }
      }
      return currEntry.value();
  }

  /**
   *  remove() would remove from the underlying run-length encoding the run
   *  identified by this iterator, but we are NOT implementing it.
   *
   *  DO NOT CHANGE THIS METHOD.
   */
  public void remove() {
    throw new UnsupportedOperationException("remove");
  }
}
