/* SenKruskalEdge.java */

package graphalg;

public class SenKruskalEdge implements Comparable {

	protected Object uVertHashKey;
	protected Object vVertHashKey;
	protected int weight;

	public SenKruskalEdge(Object u, Object v, int w) {
		uVertHashKey = u;
		vVertHashKey = v;
		weight = w;
	}

	public int compareTo(Object o)  {
		if (weight < ((SenKruskalEdge)o).weight)		return -1;
		else if (weight > ((SenKruskalEdge)o).weight)	return 1;
		else                                            return 0;
	}
}