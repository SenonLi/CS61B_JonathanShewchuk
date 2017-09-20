/* SenWUGraphVertex.java
 * SenWUGraphVertex, adjacencyList are hidden from application */
package graph;

import list.*;

public class SenWUGraphVertex {

	/** Use the application visible vertex Object to be HashKey for internal SenWUGraphVertex */
	private     Object      vertexHashKey;      // for locating in verticesHashTable
	private     ListNode    vertListNode;       // for locating in verticesList
	private     int         degree;
	protected   DList       adjacencyEdgeList;


	SenWUGraphVertex(Object appVertexHashKey) {
		vertexHashKey       = appVertexHashKey;
		vertListNode        = null;
		adjacencyEdgeList   = new DList();
		degree              = 0;
	}

	void setListNodeAddress(ListNode node)   {
		vertListNode = node;
	}

	ListNode getVertListNode()  {   return vertListNode;  }
	Object getVertexHashKey()   {   return vertexHashKey; }
	int getDegree()             {   return degree;  }
	void degreeIncrease()       {   degree++;   }
	void degreeDecrease()       {   degree--;   }
}
