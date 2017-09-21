/* BinaryTreeNode.java */

package binaryTree;

import list.List;

/**
 *  BinaryTreeNode represents a node in a binary tree.
 *
 *  DO NOT CHANGE THIS FILE.
 **/
class BinaryTreeNode {

  protected BinaryTree myTree;
  protected Entry entry;
  protected BinaryTreeNode parent;
  protected BinaryTreeNode leftChild, rightChild;


  BinaryTreeNode(Entry entry) {
      this(entry, null, null, null, null);
  }
  BinaryTreeNode(Entry entry, BinaryTree tree) {
      this(entry, tree, null, null, null);
  }
  BinaryTreeNode(Entry entry, BinaryTree tree, BinaryTreeNode parent) {
      this(entry, tree, parent, null, null);
  }

  BinaryTreeNode(Entry entry, BinaryTree tree, BinaryTreeNode parent, BinaryTreeNode left, BinaryTreeNode right) {
      this.entry = entry;
      this.parent = parent;
      myTree = tree;
      leftChild = left;
      rightChild = right;
  }

    public boolean isValidTreeNode() {
        return myTree != null;
    }

  public String toString() {
    String s = "";

    if (leftChild != null) {
      s = "(" + leftChild.toString() + ")";
    }
    s = s + " " + entry.key().toString() + " ";// + entry.value();
    if (rightChild != null) {
      s = s + "(" + rightChild.toString() + ")";
    }
    return s;
  }
}
