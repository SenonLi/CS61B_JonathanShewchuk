/* SenSentinelDList.java */

/**
 *  A SenSentinelDList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the sentinel
 *  of the list.
 */

public class SenSentinelDList {

  /**
   *  sentinel references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected SenDListNode sentinel;
  protected long size;

  /* SenSentinelDList invariants:
   *  0)  all valid nodes except sentinel must have valid item object != null if used as iterator for
   *          hasNext() { return curRunNode.item != null;}
   *  1)  sentinel != null
   *  2)  For any SenDListNode x in a SenSentinelDList, x.next != null.
   *  3)  For any SenDListNode x in a SenSentinelDList, x.prev != null.
   *  4)  For any SenDListNode x in a SenSentinelDList, if x.next == y, then y.prev == x.
   *  5)  For any SenDListNode x in a SenSentinelDList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "sentinel"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  SenSentinelDList() constructor for an empty SenSentinelDList.
   */
  public SenSentinelDList() {
    sentinel = new SenDListNode();
    sentinel.item = null;
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    size = 0;
  }

  /**
   *  SenSentinelDList() constructor for a one-node SenSentinelDList.
   */
  public SenSentinelDList(Object a) {
    sentinel = new SenDListNode();
    sentinel.item = null;
    sentinel.next = new SenDListNode();
    sentinel.next.item = a;
    sentinel.prev = sentinel.next;
    sentinel.next.prev = sentinel;
    sentinel.prev.next = sentinel;
    size = 1;
  }

  /**
   *  SenSentinelDList() constructor for a two-node SenSentinelDList.
   */
  public SenSentinelDList(Object a, Object b) {
    sentinel = new SenDListNode();
    sentinel.item = null;
    sentinel.next = new SenDListNode();
    sentinel.next.item = a;
    sentinel.prev = new SenDListNode();
    sentinel.prev.item = b;
    sentinel.next.prev = sentinel;
    sentinel.next.next = sentinel.prev;
    sentinel.prev.next = sentinel;
    sentinel.prev.prev = sentinel.next;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a SenSentinelDList.
   */
  public void insertFront(Object o) {
    // Your solution here.
	SenDListNode newRealHead = new SenDListNode(o);
	newRealHead.prev = sentinel;
	sentinel.next.prev = newRealHead;
	
	newRealHead.next = sentinel.next;
	sentinel.next = newRealHead;
	
	size++;
  }

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a SenSentinelDList.  If the list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
	if (size != 0)	{
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
		size--;
	}
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    SenDListNode current = sentinel.next;
    while (current != sentinel) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }


  public static void main(String[] args) {
    // DO NOT CHANGE THE FOLLOWING CODE.
      ;
//    SenSentinelDList l = new SenSentinelDList();
//    System.out.println("### TESTING insertFront ###\nEmpty list is " + l);
//
//    l.insertFront(9);
//    System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
//    if (l.sentinel.next.item != 9) {
//      System.out.println("sentinel.next.item is wrong.");
//    }
//    if (l.sentinel.next.prev != l.sentinel) {
//      System.out.println("sentinel.next.prev is wrong.");
//    }
//    if (l.sentinel.prev.item != 9) {
//      System.out.println("sentinel.prev.item is wrong.");
//    }
//    if (l.sentinel.prev.next != l.sentinel) {
//      System.out.println("sentinel.prev.next is wrong.");
//    }
//    if (l.size != 1) {
//      System.out.println("size is wrong.");
//    }
//
//    l.insertFront(8);
//    System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
//    if (l.sentinel.next.item != 8) {
//      System.out.println("sentinel.next.item is wrong.");
//    }
//    if (l.sentinel.next.prev != l.sentinel) {
//      System.out.println("sentinel.next.prev is wrong.");
//    }
//    if (l.sentinel.prev.item != 9) {
//      System.out.println("sentinel.prev.item is wrong.");
//    }
//    if (l.sentinel.prev.next != l.sentinel) {
//      System.out.println("sentinel.prev.next is wrong.");
//    }
//    if (l.sentinel.next.next != l.sentinel.prev) {
//      System.out.println("l.sentinel.next.next != l.sentinel.prev.");
//    }
//    if (l.sentinel.prev.prev != l.sentinel.next) {
//      System.out.println("l.sentinel.prev.prev != l.sentinel.next.");
//    }
//    if (l.size != 2) {
//      System.out.println("size is wrong.");
//    }
//
//
//
//    l = new SenSentinelDList(1, 2);
//    System.out.println("\n\n### TESTING removeFront ###\nList with 1 and 2 is "
//                       + l);
//
//    l.removeFront();
//    System.out.println("\nList with 2 is " + l);
//    if (l.sentinel.next.item != 2) {
//      System.out.println("sentinel.next.item is wrong.");
//    }
//    if (l.sentinel.next.prev != l.sentinel) {
//      System.out.println("sentinel.next.prev is wrong.");
//    }
//    if (l.sentinel.prev.item != 2) {
//      System.out.println("sentinel.prev.item is wrong.");
//    }
//    if (l.sentinel.prev.next != l.sentinel) {
//      System.out.println("sentinel.prev.next is wrong.");
//    }
//    if (l.size != 1) {
//      System.out.println("size is wrong.");
//    }
//
//    l.removeFront();
//    System.out.println("\nEmpty list is " + l);
//    if (l.sentinel.next != l.sentinel) {
//      System.out.println("sentinel.next is wrong.");
//    }
//    if (l.sentinel.prev != l.sentinel) {
//      System.out.println("sentinel.prev is wrong.");
//    }
//    if (l.size != 0) {
//      System.out.println("size is wrong.");
//    }
//
//    l.removeFront();
//    System.out.println("\nEmpty list is " + l);
//    if (l.sentinel.next != l.sentinel) {
//      System.out.println("sentinel.next is wrong.");
//    }
//    if (l.sentinel.prev != l.sentinel) {
//      System.out.println("sentinel.prev is wrong.");
//    }
//    if (l.size != 0) {
//      System.out.println("size is wrong.");
//    }
  }// end of main


} // end of class SenSentinelDList
