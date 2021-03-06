/* Kruskal.java */

package graphalg;

import graph.*;
import binaryTree.*;
import set.*;
import hashTable.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g)  {
      WUGraph mstGraph = new WUGraph();
      BinaryTree edgeBinaryTree = new BinaryTree();

      Object[] vertHashKeysArray = g.getVertices();
      for (int i=0; i < vertHashKeysArray.length; i++)  {
          /** 1. File all the vertices*/
          mstGraph.addVertex(vertHashKeysArray[i]);
          Neighbors vertHashKeyNeighbors = g.getNeighbors(vertHashKeysArray[i]);
          /** 2. Use binaryTree to store non-repetitive sorted edges*/
          for (int j=0; j < vertHashKeyNeighbors.neighborList.length; j++)  {
              SenKruskalEdge edge = new SenKruskalEdge(vertHashKeysArray[i],
                                            vertHashKeyNeighbors.neighborList[j],
                                            vertHashKeyNeighbors.weightList[j]);
              edgeBinaryTree.insertValueUnique(edge, edge);
          }

      }

      SenBinaryTreeBookmark edgeTreeBookmark = new SenBinaryTreeBookmark(edgeBinaryTree.getLeftestTreeNode());
      //System.out.println(edgeBinaryTree);

      /** DisjointSets can help easily tell if two vertices are already connected (in the same set)
       *  HashTableChained is for building the disjointSets' code mapped from very single vertex */
      DisjointSets vertsConnectionTellerDS = new DisjointSets(g.vertexCount());
      HashTableChained vertToDSCodeHashTable = new HashTableChained();
      for (int i = 0; i < vertHashKeysArray.length; ++i) {
          vertToDSCodeHashTable.insert(vertHashKeysArray[i], i);
      }
      int count = 0;
      SenKruskalEdge edge = null;

      while(edgeTreeBookmark.hasNext()) {
          count++;

          try{
              edge = (SenKruskalEdge)edgeTreeBookmark.nextAscend();
              //System.out.println("Ascending  Edge Weight:\t" + edge.weight);
          }catch(Exception e)   {
              System.err.println("edgeTreeBookmark.nextAscend() " + e.toString());
          }

          int st1 = vertsConnectionTellerDS.find((Integer)(vertToDSCodeHashTable.find(edge.uVertHashKey)).value());
          int st2 = vertsConnectionTellerDS.find((Integer)(vertToDSCodeHashTable.find(edge.vVertHashKey)).value());
          if (st1 != st2) {
              vertsConnectionTellerDS.union(st1, st2);
              mstGraph.addEdge(edge.uVertHashKey, edge.vVertHashKey, edge.weight);
          }
      }//*/

      return mstGraph;
  }

}
