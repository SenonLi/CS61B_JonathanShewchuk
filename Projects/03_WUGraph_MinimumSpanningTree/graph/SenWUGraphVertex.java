/* SenWUGraphVertex.java
 * SenWUGraphVertex, adjacencyList are hidden from application */
package graph;

import list.*;

public class SenWUGraphVertex {

	/** Use the application visible vertex Object to be HashKey for internal SenWUGraphVertex */
	private     Object      vertexHashKey;      // for locating in verticesHashTable
	private     ListNode    listNodeAddress;    // for locating in verticesList
	protected   DList       adjacencyList;

	SenWUGraphVertex(Object appVertexHashKey) {
		listNodeAddress = null;
		vertexHashKey = appVertexHashKey;
		adjacencyList = new DList();
	}

	public void setListNodeAddress(ListNode node)   {
		listNodeAddress = node;
	}

	public Object getVertexHashKey()    { return vertexHashKey; }

}
