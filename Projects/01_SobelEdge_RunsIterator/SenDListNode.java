/* SenDListNode.java */

/**
 *  A SenDListNode is a node in a SenSentinelDList (doubly-linked list).
 */

public class SenDListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public Object item;
  public SenDListNode prev;
  public SenDListNode next;

  /**
   *  SenDListNode() constructor.
   */
  SenDListNode() {
    item = null;
    prev = null;
    next = null;
  }

  SenDListNode(Object o) {
    item = o;
    prev = null;
    next = null;
  }

}
