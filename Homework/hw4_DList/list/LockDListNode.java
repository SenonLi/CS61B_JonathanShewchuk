package list;

/**
 * Created by SenonLi on 6/27/2017.47.397938,8.4992
 */
public class LockDListNode extends DListNode {

	LockDListNode (Object i, DListNode p, DListNode n)  {
		super(i,p,n);
		nodeLocked = false;
	}

	boolean nodeLocked = false;

}
