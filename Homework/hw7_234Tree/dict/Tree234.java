/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
      // Fill in your solution here.
      // if tree is empty, make new root node containing the key
      if (isEmpty()) {
        root = new Tree234Node(null, key);
        size = 1;
        return;
      }

      // find the ptrNode and insertKeyToNode()
      Tree234Node ptrNode = root;
      while (ptrNode != null)  {
          /** Check if key already in this ptrNode or not */
          if (keyInNode(ptrNode, key)) {
              return;
          }

          /** if not in current prtNode, check next possible Node **/
          // for special 3-keys-node
          if (ptrNode.keys == 3)  {
              // send the middle node to either parent or new root
              if (ptrNode == root) {
                  root = new Tree234Node(null, ptrNode.key2);
                  ptrNode.parent = root;
              } else {
                  insertKeyToNode(ptrNode.parent, ptrNode.key2);
              }

              // Split ptrNode, and assign/re-arrange children of ptrNode's parent
              Tree234Node parentNode = ptrNode.parent;
              Tree234Node left = new Tree234Node(parentNode, ptrNode.key1);
              Tree234Node right = new Tree234Node(parentNode, ptrNode.key3);
              if (ptrNode.key2 == parentNode.key1) {
                  parentNode.child4 = parentNode.child3;
                  parentNode.child3 = parentNode.child2;
                  parentNode.child2 = right;
                  parentNode.child1 = left;
              }else if (ptrNode.key2 == parentNode.key2)  {
                  parentNode.child4 = parentNode.child3;
                  parentNode.child3 = right;
                  parentNode.child2 = left;
              }else {
                  parentNode.child4 = right;
                  parentNode.child3 = left;
              }

              // re-arrange children of left and right nodes
              left.child1   = ptrNode.child1;
              left.child2   = ptrNode.child2;
              right.child1  = ptrNode.child3;
              right.child2  = ptrNode.child4;

              // ptrNode has either no child, or 4 children;
              // if 4 children, set their parentNode
              if (ptrNode.child1 != null) {
                  left.child1.parent = left;
                  left.child2.parent = left;
                  right.child1.parent = right;
                  right.child2.parent = right;
              } else {  // otherwise, choose the correct node as leaf node
                  if (key < ptrNode.key1)     ptrNode = left;
                  else                        ptrNode = right;
              }
          }

          // no sibling checking;
          // if not in this node, has to be under one branch of subtree
          Tree234Node nextNode = getNextNode(ptrNode, key);
          // if reach a leaf, insert key to ptrNode
          if (nextNode == null) {
              insertKeyToNode(ptrNode, key);
              size++;
          }else
            ptrNode = nextNode;
      }
  }

    private boolean keyInNode(Tree234Node node, int key)  {
        return (key == node.key1) || (key == node.key2) || (key == node.key3);
    }

    private Tree234Node getNextNode(Tree234Node node, int key) {
        if (key < node.key1) {
            return node.child1;
        } else if ((node.keys == 1) || (key < node.key2)) {
            return node.child2;
        } else if ((node.keys == 2) || (key < node.key3)) {
            return node.child3;
        } else {
            return node.child4;
        }
    }

    private void insertKeyToNode(Tree234Node nodeToInsert, int key) {
        if (key < nodeToInsert.key1) {
            nodeToInsert.key3 = nodeToInsert.key2;
            nodeToInsert.key2 = nodeToInsert.key1;
            nodeToInsert.key1 = key;
        } else if ((nodeToInsert.keys == 1) || (key < nodeToInsert.key2)) {
            nodeToInsert.key3 = nodeToInsert.key2;
            nodeToInsert.key2 = key;
        } else {
            nodeToInsert.key3 = key;
        }
        nodeToInsert.keys++;
    }


  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
