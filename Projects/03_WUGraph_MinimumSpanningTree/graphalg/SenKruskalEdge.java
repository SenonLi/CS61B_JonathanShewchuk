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

	public int hashCode() {
		if (uVertHashKey.equals(vVertHashKey)) {
			return uVertHashKey.hashCode() + 1;
		} else {
			return uVertHashKey.hashCode() + vVertHashKey.hashCode();
		}
	}

	/**
	 * equals() returns true if this SenKruskalEdge represents the same unordered
	 * pair of objects as the parameter "o".  The order of the pair does not
	 * affect the equality test, so (u, v) is found to be equal to (v, u).
	 */
	public boolean equals(Object o) {
		if (o instanceof SenKruskalEdge) {
			return ((uVertHashKey.equals(((SenKruskalEdge) o).uVertHashKey)) &&
				(vVertHashKey.equals(((SenKruskalEdge) o).vVertHashKey))) ||
				((uVertHashKey.equals(((SenKruskalEdge) o).vVertHashKey)) &&
					(vVertHashKey.equals(((SenKruskalEdge) o).uVertHashKey)));
		} else {
			return false;
		}
	}

	public String toString() {
		return (new Integer(this.weight)).toString();
	}
}