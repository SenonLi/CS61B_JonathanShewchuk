package list;

/**
 * Created by SenonLi on 6/27/2017.
 */
public class LockDList extends DList {

	public void lockNode(DListNode node) {
		if(node instanceof LockDListNode)   {
			((LockDListNode)node).nodeLocked = true;
		}
	}

	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}

	public LockDListNode front() {
		// Your solution here.
		if (size ==0) return null;
		if(head.next instanceof LockDListNode)
			return (LockDListNode)head.next;
		else    {
			System.err.println("public LockDListNode front(), Wrong Node was added into the LockDList, check it out!!");
			return null;
		}
	}

	public LockDListNode back() {
		// Your solution here.
		if (size ==0) return null;
		if(head.prev instanceof LockDListNode)
			return (LockDListNode)head.prev;
		else {
			System.err.println("public LockDListNode back(), Wrong Node was added into the LockDList, check it out!!");
			return null;
		}
	}

	public LockDListNode next(DListNode node) {
		// Your solution here.
		if (node == null) return null;
		else if (node.next == null) return null;
		else if(node.next instanceof LockDListNode)
			return (LockDListNode)node.next;
		else {
			System.err.println("public LockDListNode next(DListNode node),  Wrong Node was added into the LockDList, check it out!!");
			return null;
		}
	}

	public LockDListNode prev(DListNode node) {
		// Your solution here.
		if (node == null) return null;
		else if (node.prev == null) return null;
		else if (node.prev instanceof LockDListNode)
			return (LockDListNode)node.prev;
		else {
			System.err.println("public LockDListNode prev(DListNode node),  Wrong Node was added into the LockDList, check it out!!");
			return null;
		}
	}

	public void remove(DListNode node) {
		// Your solution here.
		if(node == null)  return;
		if(node instanceof LockDListNode)   {
			LockDListNode toRemove = (LockDListNode)node;
			if(toRemove.nodeLocked == false)    {
				if (toRemove.prev != null)  toRemove.prev.next = toRemove.next;
				if (toRemove.next != null)  toRemove.next.prev = toRemove.prev;
				size--;
			}
		}else   {
			System.err.println("Something Wrong, none LockDListNode was added !! Check it out!! ");
		}
	}
}
