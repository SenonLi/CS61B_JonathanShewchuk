/* BinaryTree.java */

package binaryTree;

/**
 *  BinaryTree implements a Dictionary as a binary tree (unbalanced).  Multiple
 *  entries with the same key are permitted.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 *
 *  @author Jonathan Shewchuk
 **/
public class BinaryTree implements Dictionary {

  /** 
   *  size is the number of items stored in the dictionary.
   *  root is the BinaryTreeNode that serves as root of the tree.
   *  If there are no items, size is zero and root is null.
   **/
  protected int size;
  protected BinaryTreeNode root;
  private BinaryTreeNode leftest, rightest;

  public BinaryTreeNode getLeftestTreeNode()    {   return leftest; }
  public BinaryTreeNode getRightestTreeNode()   {   return rightest;    }

  /**
   *  Construct an empty binary tree.
   **/
  public BinaryTree() {
    makeEmpty();
  }

  /**
   *  makeEmpty() removes all the entries from the dictionary.
   */
  public void makeEmpty() {
      size = 0;
      root = null;
      leftest = null;
      rightest = null;
  }

  /** 
   *  size() returns the number of entries stored in the dictionary.
   *
   *  @return the number of entries stored in the dictionary.
   **/
  public int size() {
    return size;
  }

  /** 
   *  isEmpty() tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  insert() constructs and inserts a new Entry object, consisting of
   *  a (key, value) pair, into the dictionary, and returns a reference to the
   *  new Entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.  Must be of
   *  a class that implements java.lang.Comparable.
   *  @param value an arbitrary object associated with the key.
   *  @return an Entry object referencing the key and value.
   **/
  public Entry insert(Object key, Object value) {
    Entry entry = new Entry(key, value);
    if (root == null) {
        root = new BinaryTreeNode(entry, this);
        leftest = root;
        rightest = root;
    } else {
        BinaryTreeNode insertedTreeNode = insertHelper(entry, (Comparable) key, root);
        if (((Comparable)key).compareTo(leftest.entry.key()) <= 0)
            leftest = insertedTreeNode;
        if (((Comparable)key).compareTo(rightest.entry.key()) > 0)
            rightest = insertedTreeNode;
    }

    size++;
    return entry;
  }

    public Entry insertValueUnique(Object key, Object value) {
        /** key is for sorted inserting, value.hashCode() is for testing repetition */
        Entry entry = new Entry(key, value);
        if (root == null) {
            root = new BinaryTreeNode(entry, this);
            leftest = root;
            rightest = root;
        } else {
            BinaryTreeNode insertedTreeNode = insertValueUniqueHelper(entry, (Comparable) key, root);
            if(insertedTreeNode != null)    {
                if (((Comparable)key).compareTo(leftest.entry.key()) <= 0)
                    leftest = insertedTreeNode;
                if (((Comparable)key).compareTo(rightest.entry.key()) > 0)
                    rightest = insertedTreeNode;
            }else
                return null; /** There already be a node with same key, do nothing, return null */
        }

        size++;
        return entry;
    }

    /**
   *  insertHelper() recursively does the work of inserting a new Entry object
   *  into the dictionary.
   *
   *  @param entry the Entry object to insert into the tree.
   *  @param key the key by which the entry can be retrieved.
   *  @param node the root of a subtree in which the new entry will be
   *         inserted.
   **/
  private BinaryTreeNode insertHelper(Entry entry, Comparable key, BinaryTreeNode node) {
      BinaryTreeNode insertedTreeNode = null;
      if (key.compareTo(node.entry.key()) <= 0) {
          if (node.leftChild == null) {
              insertedTreeNode = new BinaryTreeNode(entry, this, node);
              node.leftChild = insertedTreeNode;
          } else {
              insertedTreeNode = insertHelper(entry, key, node.leftChild);
          }
      } else {
          if (node.rightChild == null) {
              insertedTreeNode = new BinaryTreeNode(entry, this, node);
              node.rightChild = insertedTreeNode;
          } else {
              insertedTreeNode = insertHelper(entry, key, node.rightChild);
          }
      }

      return insertedTreeNode;
  }

    private BinaryTreeNode insertValueUniqueHelper(Entry entry, Comparable key, BinaryTreeNode node) {
        BinaryTreeNode insertedTreeNode = null;
        if (key.compareTo(node.entry.key()) > 0) {
            if (node.rightChild == null) {
                insertedTreeNode = new BinaryTreeNode(entry, this, node);
                node.rightChild = insertedTreeNode;
            } else {
                insertedTreeNode = insertValueUniqueHelper(entry, key, node.rightChild);
            }
        }else {
            if (key.compareTo(node.entry.key()) == 0) {
                /** Check if entry.value() is a repetition based on value's hashCode */
                if (entry.value().hashCode() == node.entry.value().hashCode())
                    return null;
            }

            if (node.leftChild == null) {
                insertedTreeNode = new BinaryTreeNode(entry, this, node);
                node.leftChild = insertedTreeNode;
            } else {
                insertedTreeNode = insertValueUniqueHelper(entry, key, node.leftChild);
            }
        }

        return insertedTreeNode;
    }

    /**
   *  find() searches for an entry with the specified key.  If such an entry is
   *  found, it returns the Entry object; otherwise, it returns null.  If more
   *  than one entry has the key, one of them is chosen arbitrarily and
   *  returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry find(Object key) {
    BinaryTreeNode node = findHelper((Comparable) key, root);
    if (node == null) {
      return null;
    } else {
      return node.entry;
    }
  }

  /**
   *  Search for a node with the specified key, starting from "node".  If
   *  a matching key is found (meaning that key1.compareTo(key2) == 0), return
   *  a node containing that key.  Otherwise, return null.
   *
   *  Be sure this method returns null if node == null.
   **/

  private BinaryTreeNode findHelper(Comparable key, BinaryTreeNode node) {
      // Replace the following line with your solution.
      if (node == null) return null;

      if (key.compareTo(node.entry.key()) < 0) {
          return findHelper(key, node.leftChild);
      }else if (key.compareTo(node.entry.key()) > 0) {
          return findHelper(key, node.rightChild);
      }else {
          return node;
      }
  }

  /** 
   *  remove() searches for an entry with the specified key.  If such an entry
   *  is found, it removes the Entry object from the Dictionary and returns it;
   *  otherwise, it returns null.  If more than one entry has the key, one of
   *  them is chosen arbitrarily, removed, and returned.
   *
   *  @param key the search key.  Must be of a class that implements
   *         java.lang.Comparable.
   *  @return an Entry referencing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry remove(Object key) {
    // Replace the following line with your solution.
      BinaryTreeNode nodeToRemove = findHelper((Comparable) key, root);
      if (nodeToRemove == null)   return null;

      boolean hasLeftChild  = nodeToRemove.leftChild != null;
      boolean hasRightChild = nodeToRemove.rightChild != null;
      boolean isRoot = (nodeToRemove == root) && (nodeToRemove.parent == null);
      boolean atParentLeft  = isRoot ? false : (nodeToRemove.parent.leftChild == nodeToRemove);
      boolean atParentRight = isRoot ? false : (nodeToRemove.parent.rightChild == nodeToRemove);

      /** 1. no children */
      if (!hasLeftChild && !hasRightChild) {
          if (isRoot)               root = null;
          else if (atParentLeft)    nodeToRemove.parent.leftChild   = null;
          else if (atParentRight)   nodeToRemove.parent.rightChild  = null;

      } else if (hasLeftChild && hasRightChild) {
      /** 2. Two side child, find minRightNode to replace with nodeThis */
          if (nodeToRemove.rightChild.leftChild == null) {
              nodeToRemove.entry = nodeToRemove.rightChild.entry;
              if (nodeToRemove.rightChild.rightChild != null) {
                nodeToRemove.rightChild.rightChild.parent = nodeToRemove;
              }
              nodeToRemove.rightChild = nodeToRemove.rightChild.rightChild;
          }else {
              BinaryTreeNode minRightNode = findMinRightNode(nodeToRemove);

              nodeToRemove.entry = minRightNode.entry;
              if (minRightNode.rightChild == null) {
                minRightNode.parent.leftChild = null;
              } else {
                minRightNode.rightChild.parent = minRightNode.parent;
                minRightNode.parent.leftChild = minRightNode.rightChild;
              }
          }
      } else {
          /** 3. One side child, move the child up to the correct position */
          if (isRoot) {
              root = hasLeftChild ? nodeToRemove.leftChild : nodeToRemove.rightChild;
              root.parent = null;
          }else if (atParentLeft) {
              nodeToRemove.parent.leftChild = hasLeftChild ? nodeToRemove.leftChild : nodeToRemove.rightChild;
              nodeToRemove.parent.leftChild.parent =  nodeToRemove.parent;
          }else if (atParentRight){
              nodeToRemove.parent.rightChild = hasLeftChild ? nodeToRemove.leftChild : nodeToRemove.rightChild;
              nodeToRemove.parent.rightChild.parent =  nodeToRemove.parent;
          }
      }

      /** Process leftest and rightest if on a special case  */
      if (nodeToRemove == leftest)  {
          leftest = root;
          while(leftest != null && leftest.leftChild != null)     leftest = leftest.leftChild;
      }
      if (nodeToRemove == rightest) {
          rightest = root;
          while(rightest != null && rightest.rightChild != null)    rightest = rightest.rightChild;
      }

      size--;
      return nodeToRemove.entry;
  }

  private BinaryTreeNode findMinRightNode(BinaryTreeNode targetNode) {
      if (targetNode == null || targetNode.rightChild == null ) return null;

      BinaryTreeNode minRightNode = targetNode.rightChild;
      while (minRightNode.leftChild != null) {
          minRightNode = minRightNode.leftChild;
      }
      return minRightNode;
  }

  /**
   *  Convert the tree into a string.
   **/

  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /* Tests the binary search tree. */
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    System.out.println("Inserting 1A, 6V, 3K, 2Z, 5L, 9L:");
    tree.insert(new Integer(1), "A");
    tree.insert(new Integer(6), "V");
    tree.insert(new Integer(3), "K");
    tree.insert(new Integer(2), "Z");
    tree.insert(new Integer(5), "L");
    tree.insert(new Integer(9), "L");
    System.out.println("The tree is:  " + tree);
    System.out.println("Size:  " + tree.size());

    System.out.println("\nTesting find() ...");
    tree.testFind(1, "A");
    tree.testFind(9, "L");
    tree.testFind(5, "L");
    tree.testFind(4, null);
    tree.testFind(6, "V");
    tree.testFind(3, "K");

    System.out.println("\nTesting remove() (for nodes with < 2 children) ...");
    tree.testRemove(5, "1A(((2Z)3K)6V(9L))");
    tree.testRemove(3, "1A((2Z)6V(9L))");
    tree.testRemove(1, "(2Z)6V(9L)");
    tree.insert(new Integer(7), "S");
    tree.insert(new Integer(8), "X");
    tree.insert(new Integer(10), "B");
    System.out.println("After inserting 7S, 8X, 10B:  " + tree);
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 6) {
      System.out.println("  SHOULD BE 6.");
    }

    System.out.println("\nTesting remove() (for nodes with 2 children) ...");
    tree.testRemove(6, "(2Z)7S((8X)9L(10B))");
    tree.testRemove(9, "(2Z)7S((8X)10B)");
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 4) {
      System.out.println("  SHOULD BE 4.");
    }
  }

  private void testRemove(int n, String shouldBe) {
    Integer key = new Integer(n);
    System.out.print("After remove(" + n + "):  ");
    remove(key);
    System.out.println(this);
    if (!toString().equals(shouldBe)) {
      System.out.println("  SHOULD BE " + shouldBe);
    }
  }

  private void testFind(int n, Object truth) {
    Integer key = new Integer(n);
    Entry entry = find(key);
    System.out.println("Calling find() on " + n);
    if (entry == null) {
      System.out.println("  returned null.");
      if (truth != null) {
        System.out.println("  SHOULD BE " + truth + ".");
      }
    } else {
      System.out.println("  returned " + entry.value() + ".");
      if (!entry.value().equals(truth)) {
        if (truth == null) {
          System.out.println("  SHOULD BE null.");
        } else {
          System.out.println("  SHOULD BE " + truth + ".");
        }
      }
    }
  }
  
}
