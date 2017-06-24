/* SenDList.java */
/**
 *  A SenDList is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */
public class SenDList {
  /**
   *  head references the first node.
   *  tail references the last node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */
  protected SenDListNode head;
  protected SenDListNode tail;
  protected long size;

  /* SenDList invariants:
   *  1)  head.prev == null.
   *  2)  tail.next == null.
   *  3)  For any SenDListNode x in a DList, if x.next == y and x.next != null, then y.prev == x.
   *  4)  For any SenDListNode x in a DList, if x.prev == y and x.prev != null, then y.next == x.
   *  5)  The tail can be accessed from the head by a sequence of "next" references.
   *  6)  size is the number of DListNode1s that can be accessed from the
   *      head by a sequence of "next" references.
   */

  /**
   *  SenDList() constructor for an empty SenDList.
   */
  public SenDList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  SenDList() constructor for a one-node SenDList.
   */
  public SenDList(Object o) {
    head = new SenDListNode();
    tail = head;
    head.item = o;
    size = 1;
  }

  /**
   *  SenDList() constructor for a two-node SenDList.
   */
  public SenDList(Object a, Object b) {
    head = new SenDListNode();
    head.item = a;
    tail = new SenDListNode();
    tail.item = b;
    head.next = tail;
    tail.prev = head;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a SenDList.
   */
  public void insertFront(Object o) {
    // Your solution here.
	SenDListNode newHead = new SenDListNode(o);
	newHead.next = head;
	if (tail == null) tail = newHead;
	else if (tail == head) tail.prev = newHead;
	else head.prev = newHead;
	head = newHead;
	size++;
  }
	/**
	 *  insertFront() inserts an item at the back of a SenDList.
	 */
	public void insertBack(Object o) {
		SenDListNode newTail = new SenDListNode(o);
		newTail.prev = tail;
		if (head == null) head = newTail;
		else if (head == tail) head.next = newTail;
		else tail.next = newTail;
		tail = newTail;
		size++;
	}

	public void insertBefore(SenDListNode addToMyLeftNode, Object o)  {
		if (addToMyLeftNode == null)  {
			System.err.println("Error SenDListNode input for method insertBefore");
			return;
		}
		if (addToMyLeftNode == head) {
			insertFront(o);
			return;
		}
		SenDListNode newNode = new SenDListNode(o, addToMyLeftNode.prev, addToMyLeftNode);
		if (addToMyLeftNode.prev != null)
			addToMyLeftNode.prev.next = newNode;
		addToMyLeftNode.prev = newNode;
		size++;
	}

	public void insertAfter(SenDListNode addToMyRightNode, Object o)  {
		if (addToMyRightNode == null)  {
			System.err.println("Error SenDListNode input for method addToMyRightNode");
			return;
		}
		if (addToMyRightNode == tail) {
			insertBack(o);
			return;
		}
		SenDListNode newNode = new SenDListNode(o, addToMyRightNode, addToMyRightNode.next);
		if (addToMyRightNode.next != null)
			addToMyRightNode.next.prev = newNode;
		addToMyRightNode.next = newNode;
		size++;
	}

	public void remove(SenDListNode nodeToRemove)   {
		if (size > 0)   {
			if  (nodeToRemove == head) removeFront();
			else if (nodeToRemove == tail) removeBack();
			else {
				nodeToRemove.prev.next = nodeToRemove.next;
				nodeToRemove.next.prev = nodeToRemove.prev;
				size--;
			}
		}else
			System.err.println("There is no SenDListNode to remove !!");
	}
	/**
   *  removeFront() removes the first item (and node) from a SenDList.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
	if (head != null)	{
		if (head == tail) {
			head = null;
			tail = null;
		}else {
			head.next.prev = null;
			head = head.next;
		}	
		size -= 1;
	}
  }

	public void removeBack() {
		// Your solution here.
		if (tail != null)	{
			if (head == tail) {
				head = null;
				tail = null;
			}else {
				tail.prev.next = null;
				tail = tail.prev;
			}
			size -= 1;
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
    SenDListNode current = head;
    while (current != null) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
    // DO NOT CHANGE THE FOLLOWING CODE.

//    SenDList l = new SenDList();
//    System.out.println("### TESTING insertFront ###\nEmpty list is " + l);
//
//    l.insertFront(9);
//    System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
//    if (l.head == null) {
//      System.out.println("head is null.");
//    } else {
//      if (l.head.item != 9) {
//        System.out.println("head.item is wrong.");
//      }
//      if (l.head.prev != null) {
//        System.out.println("head.prev is wrong.");
//      }
//    }
//    if (l.tail == null) {
//      System.out.println("tail is null.");
//    } else {
//      if (l.tail.item != 9) {
//        System.out.println("tail.item is wrong.");
//      }
//      if (l.tail.next != null) {
//        System.out.println("tail.next is wrong.");
//      }
//    }
//    if (l.size != 1) {
//      System.out.println("size is wrong.");
//    }
//
//    l.insertFront(8);
//    System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
//    if (l.head == null) {
//      System.out.println("head is null.");
//    } else {
//      if (l.head.item != 8) {
//        System.out.println("head.item is wrong.");
//      }
//      if (l.head.prev != null) {
//        System.out.println("head.prev is wrong.");
//      }
//      if (l.head.next != l.tail) {
//        System.out.println("head.next is wrong.");
//      }
//    }
//    if (l.tail == null) {
//      System.out.println("tail is null.");
//    } else {
//      if (l.tail.item != 9) {
//        System.out.println("tail.item is wrong.");
//      }
//      if (l.tail.next != null) {
//        System.out.println("tail.next is wrong.");
//      }
//      if (l.tail.prev != l.head) {
//        System.out.println("tail.prev is wrong.");
//      }
//    }
//    if (l.size != 2) {
//      System.out.println("size is wrong.");
//    }
//
//
//
//    l = new SenDList(1, 2);
//    System.out.println("\n\n### TESTING removeFront ###\nList with 1 and 2 is "
//                       + l);
//
//    l.removeFront();
//    System.out.println("\nRemoving front node.\nList with 2 is " + l);
//    if (l.head.item != 2) {
//      System.out.println("head.item is wrong.");
//    }
//    if (l.head.prev != null) {
//      System.out.println("head.prev is wrong.");
//    }
//    if (l.tail.item != 2) {
//      System.out.println("tail.item is wrong.");
//    }
//    if (l.tail.next != null) {
//      System.out.println("tail.next is wrong.");
//    }
//    if (l.size != 1) {
//      System.out.println("size is wrong.");
//    }
//
//    l.removeFront();
//    System.out.println("\nRemoving front node.\nEmpty list is " + l);
//    if (l.head != null) {
//      System.out.println("head is wrong.");
//    }
//    if (l.tail != null) {
//      System.out.println("tail is wrong.");
//    }
//    if (l.size != 0) {
//      System.out.println("size is wrong.");
//    }
//
//    l.removeFront();
//    System.out.println("\nRemoving front node.\nEmpty list is " + l);
//    if (l.head != null) {
//      System.out.println("head is wrong.");
//    }
//    if (l.tail != null) {
//      System.out.println("tail is wrong.");
//    }
//    if (l.size != 0) {
//      System.out.println("size is wrong.");
//    }
  } // end of main

} // end of SenDList
