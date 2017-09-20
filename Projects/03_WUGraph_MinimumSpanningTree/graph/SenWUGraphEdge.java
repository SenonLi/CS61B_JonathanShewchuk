/* SenWUGraphEdge.java
 * SenWUGraphEdge is hidden from application
 * only VertexPair object is visible to application
 * both of the two vertices in the VertexPair are 'Object's
 * VertexPair is the HashKey to map SenWUGraphEdge
 * SenWUGraphEdge means can locate/map to two internal 'SenWUGraphVertex's,
 * whose 'vertexHashKey's (two application-visible vertex Object) comprise the VertexPair*/

package graph;

import list.*;

public class SenWUGraphEdge {
	/** SenWUGraphVertex indicates two internal SenWUGraphVertex of an Edge      */
	/** ListNode for locating EdgeNode in adjacencyEdgeList of corresponding SenWUGraphVertex */
	private     SenWUGraphVertex    uVert, vVert;
	private     ListNode            uAdjListEdgeNode, vAdjListEdgeNode;
	private     int                 weight;

	SenWUGraphEdge(SenWUGraphVertex u, SenWUGraphVertex v, int weight) {
		this.uVert = u;
		this.vVert = v;
		this.weight = weight;
		this.uAdjListEdgeNode = null;
		this.vAdjListEdgeNode = null;
	}

	void setAdjListEdgeNodeU(ListNode u)  {   uAdjListEdgeNode = u;   }
	void setAdjListEdgeNodeV(ListNode v)  {   vAdjListEdgeNode = v;   }
	void setWeight(int w)   {   weight = w;     }
	int getWeight()         {   return weight;  }
	SenWUGraphVertex getSenWUGraphVertexU() {   return uVert;   }
	SenWUGraphVertex getSenWUGraphVertexV() {   return vVert;   }
	ListNode getAdjListEdgeNodeU()      {   return uAdjListEdgeNode;    }
	ListNode getAdjListEdgeNodeV()      {   return vAdjListEdgeNode;    }
}
