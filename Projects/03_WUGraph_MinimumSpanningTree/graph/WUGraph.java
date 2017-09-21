/* WUGraph.java */

package graph;
import list.*;
import hashTable.*;
/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
    protected DList verticesList;
    protected HashTableChained verticesHashTable;
    protected HashTableChained edgesHashTable;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
      verticesList = new DList();
      verticesHashTable = new HashTableChained();
      edgesHashTable = new HashTableChained();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount()  {   return verticesList.length();  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount()    {   return edgesHashTable.size();   }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
      Object[] verticesArray = new Object[verticesList.length()];
      ListNode vertexNode = verticesList.front();
      int index = 0;
      try   {
          while(vertexNode.isValidNode()) {
              verticesArray[index++] = ((SenWUGraphVertex)vertexNode.item()).getVertexHashKey();
              vertexNode = vertexNode.next();
          }
      }catch(InvalidNodeException e)    {
          System.err.println("InvalidNodeException in getVertices" + e.toString());
      }

      return verticesArray;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex)  {
      if (isVertex(vertex))     return;

      SenWUGraphVertex myVertex = new SenWUGraphVertex(vertex);
      verticesHashTable.insert(vertex, myVertex);
      verticesList.insertBack(myVertex);
      myVertex.setListNodeAddress(verticesList.back());
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex)    {
      return verticesHashTable.find(vertex) != null;
  }

    /**
     * removeVertex() removes a vertex from the graph.  All edges incident on the
     * deleted vertex are removed as well.  If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex) {
        Entry vertEntry = verticesHashTable.find(vertex);
        if (vertEntry == null)  return;

        SenWUGraphVertex vertexToRemove = (SenWUGraphVertex)vertEntry.value();
        try {
            if (vertexToRemove.getDegree() == 0)    {
                ((DListNode)vertexToRemove.getVertListNode()).remove();
                verticesHashTable.remove(vertex);
                return;
            }

            ListNode firstEdgeNode = vertexToRemove.adjacencyEdgeList.front();
            if(!(firstEdgeNode instanceof  DListNode))
                System.err.println("Supposed to use DListNode for the adjacencyEdgeList");
            DListNode edgeNodeToRemove = (DListNode)firstEdgeNode;

            /** First, Remove all the edges */
            while(edgeNodeToRemove.isValidNode()) {
                SenWUGraphEdge edgeToRemove = (SenWUGraphEdge)edgeNodeToRemove.item();
                edgeNodeToRemove = (DListNode)edgeNodeToRemove.next();// has to get next() before remove
                removeEdge(edgeToRemove.getSenWUGraphVertexU().getVertexHashKey(),
                    edgeToRemove.getSenWUGraphVertexV().getVertexHashKey());
            }
            /** Second, remove the vertex from verticesList and verticesHashTable */
            ((DListNode)vertexToRemove.getVertListNode()).remove();
            verticesHashTable.remove(vertex);

        }catch (InvalidNodeException e) {
            System.err.println("InvalidNodeException in removeVertex" + e.toString());
        }
    }

    /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex)  {
      Entry vertEntry = verticesHashTable.find(vertex);
      if (vertEntry == null)    return 0;

      return  ((SenWUGraphVertex)vertEntry.value()).getDegree();
  }

    /**
     * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     *
     * Running time:  O(1).
     */
    public boolean isEdge(Object u, Object v) {
        if (!isVertex(u) || !isVertex(v))     return false;

        VertexPair edgeHashKeyVertPair = new VertexPair(u, v);
        return edgesHashTable.find(edgeHashKeyVertPair) != null;
    }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight)   {
      Entry vertEntryU = verticesHashTable.find(u);
      Entry vertEntryV = verticesHashTable.find(v);
      if (vertEntryU == null || vertEntryV == null)     return;

      VertexPair edgeHashKeyVertPair = new VertexPair(u, v);
      Entry edgeEntry = edgesHashTable.find(edgeHashKeyVertPair);
      if (edgeEntry != null)    {
          /** This graph already contains edge (u, v), so update the weight */
          Object edgeToUpdate = edgeEntry.value();
          ((SenWUGraphEdge)edgeToUpdate).setWeight(weight);
      }else {
          /** Create new SenWUGraphEdge */
          SenWUGraphVertex myVertexU = (SenWUGraphVertex)vertEntryU.value();
          SenWUGraphVertex myVertexV = (SenWUGraphVertex)vertEntryV.value();
          SenWUGraphEdge   edgeToAdd = new SenWUGraphEdge(myVertexU, myVertexV, weight);

          /** 1. Update two 'SenWUGraphVertex's of the new created edgeToAdd
           *        adjacentEdgeList + degree               */
          myVertexU.adjacencyEdgeList.insertBack(edgeToAdd);
          myVertexU.degreeIncrease();
          if (myVertexV != myVertexU)   {
              /** in case of self-edge, prevent doubling the same SenWUGraphVertex */
              myVertexV.adjacencyEdgeList.insertBack(edgeToAdd); // undirected, same edge
              myVertexV.degreeIncrease();
          }

          /** 2. Update edgeToAdd, the two 'AdjListEdgeNode's of two SenWUGraphVertex
           *        before insert into the edgesHashTable */
          edgeToAdd.setAdjListEdgeNodeU(myVertexU.adjacencyEdgeList.back());
          if (myVertexV != myVertexU)
              edgeToAdd.setAdjListEdgeNodeV(myVertexV.adjacencyEdgeList.back());

          /**3. insert edgeToAdd into the edgesHashTable */
          edgesHashTable.insert(edgeHashKeyVertPair, edgeToAdd);
      }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v)    {
      if (!isVertex(u) || !isVertex(v))     return;

      VertexPair edgeHashKeyVertPair = new VertexPair(u, v);
      Entry edgeEntry = edgesHashTable.find(edgeHashKeyVertPair);
      if (edgeEntry == null)                return;

      SenWUGraphEdge edgeToRemove = (SenWUGraphEdge)edgeEntry.value();


      /** 1. Update two 'SenWUGraphVertex's, adjacencyEdgeList + degree */
      SenWUGraphVertex vertU = edgeToRemove.getSenWUGraphVertexU();
      SenWUGraphVertex vertV = edgeToRemove.getSenWUGraphVertexV();
      try {
          vertU.degreeDecrease();
          if (edgeToRemove.getAdjListEdgeNodeU() instanceof  DListNode)
          ((DListNode)edgeToRemove.getAdjListEdgeNodeU()).remove();

          if(vertU != vertV)    {
              vertV.degreeDecrease();
              if (edgeToRemove.getAdjListEdgeNodeV() instanceof  DListNode)
                  ((DListNode)edgeToRemove.getAdjListEdgeNodeV()).remove();
          }
      }catch (InvalidNodeException e)   {
          System.err.println("InvalidNodeException in removeEdge()" + e.toString());
      }

      /** 2. Remove edgeToRemove from the edgesHashTable */
      edgesHashTable.remove(edgeHashKeyVertPair);
  }


    /**
     * getNeighbors() returns a new Neighbors object referencing two arrays.  The
     * Neighbors.neighborList array contains each object that is connected to the
     * input object by an edge.  The Neighbors.weightList array contains the
     * weights of the corresponding edges.  The length of both arrays is equal to
     * the number of edges incident on the input vertex.  If the vertex has
     * degree zero, or if the parameter "vertex" does not represent a vertex of
     * the graph, null is returned (instead of a Neighbors object).
     *
     * The returned Neighbors object, and the two arrays, are both newly created.
     * No previously existing Neighbors object or array is changed.
     *
     * (NOTE:  In the neighborList array, do not return any internal data
     * structure you use to represent vertices!  Return only the same objects
     * that were provided by the calling application in calls to addVertex().)
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public Neighbors getNeighbors(Object vertex)    {
        Entry vertEntry = verticesHashTable.find(vertex);
        if (vertEntry == null)  return null;

        SenWUGraphVertex myCenterVertex = (SenWUGraphVertex)vertEntry.value();
        if (myCenterVertex.getDegree() == 0)      return null;

        Neighbors neighbors = new Neighbors();
        neighbors.neighborList = new Object[myCenterVertex.getDegree()];
        neighbors.weightList = new int[myCenterVertex.getDegree()];

        ListNode firstEdgeNode = myCenterVertex.adjacencyEdgeList.front();
        /** degree != 0, so that firstEdgeNode != null */
        if(!(firstEdgeNode instanceof  DListNode))
            System.err.println("Supposed to use DListNode for the adjacencyEdgeList");
        DListNode edgeNode = (DListNode)firstEdgeNode;

        int index = 0;
        try {
            while(edgeNode.isValidNode()) {
                SenWUGraphEdge edge = (SenWUGraphEdge)edgeNode.item();
                SenWUGraphVertex edgeVertU = edge.getSenWUGraphVertexU();
                SenWUGraphVertex edgeVertV = edge.getSenWUGraphVertexV();

                neighbors.weightList[index] = edge.getWeight();
                if (edgeVertU.equals(myCenterVertex))
                    neighbors.neighborList[index] = edgeVertV.getVertexHashKey();
                else
                    neighbors.neighborList[index] = edgeVertU.getVertexHashKey();

                index++;
                edgeNode = (DListNode)edgeNode.next();
            }
        }catch(InvalidNodeException e)  {
            System.err.println("InvalidNodeException in getNeighbors()" + e.toString());
        }

        return neighbors;
    }


  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
      if (!isVertex(u) || !isVertex(v))     return 0;

      VertexPair edgeHashKeyVertPair = new VertexPair(u, v);
      Entry edgeEntry = edgesHashTable.find(edgeHashKeyVertPair);
      if (edgeEntry == null)                return 0;

      return ((SenWUGraphEdge)edgeEntry.value()).getWeight();
  }

}
