/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Number of slip days I am using:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinartSearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int size;
    // CS314 students. Add any other instance variables you want here

    // CS314 students. Add a default constructor here.

    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("added value can't be null");
        }
        int oldSize = size;
        root = addHelp(root, value);
        return oldSize != size;
    }

    // Note from the lecture
    private BSTNode<E> addHelp(BSTNode n, E val) {
        //n == null when tree is empty
        if (n == null) {
            size++;
            return new BSTNode<>(val);
        } else {
            int dir = (n.data).compareTo(val);
            if (dir > 0) {
                n.left = addHelp(n.left, val);
            } else if (dir < 0) {
                n.right = addHelp(n.right, val);
            }
            return n;
        }
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("added value can't be null");
        }
        if (isPresent(value)) {
            int oldSize = size;
            root = removeHelper(root, value);
            return oldSize != size;
        }
        return false;
    }

    private BSTNode<E> removeHelper(BSTNode<E> n, E val) {
        // Base Case tree is empty
        if (n == null) {
            return root;
        }
        int dir = val.compareTo(n.data);
        if (dir < 0) {
            n.left = removeHelper(n.left, val);
        } else if (dir > 0) {
            n.right = removeHelper(n.right, val);
        // gotcha, kill it
        } else {
            // if no child, this will also work
            if (n.left == null) {
                size--;
                return n.right;
            } else if (n.right == null) {
                size--;
                return n.left;
            }
            // node with two children find smallest in the right subtree
            n.data = minValue(n.right);
            // kill the lucky chosen node (smallest in the right subtree)
            n.right = removeHelper(n.right, n.data);
        }
        return n;
    }


    // helper method to give back the minValue of one subtree
    private E minValue(BSTNode<E> n) {
        E minVal = n.data;
        while (n.left != null) {
            minVal = n.left.data;
            n = n.left;
        }
        return minVal;
    }

    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if (value == null) {
            throw new IllegalArgumentException("added value can't be null");
        }
        return isPresent(root, value);
    }

    private boolean isPresent(BSTNode<E> n, E value) {
        if (n == null) {
            return false;
        }
        int dir = n.data.compareTo(value);
        if (dir == 0) {
            return true;
        } else if (dir > 0) {
            return isPresent(n.left, value);
        } else {
            return isPresent(n.right, value);
        }
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return htHelp(root);
    }

    //Code from the lecture
    private int htHelp(BSTNode<E> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(htHelp(n.left), htHelp(n.right));
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order. 
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        ArrayList<E> result = new ArrayList<>();
        getAllHelper(result, root);
        Collections.sort(result);
        return result;
    }

    private void getAllHelper(ArrayList<E> result, BSTNode<E> n) {
        if (n == null) {
            return;
        }
        // for leaves
        result.add(n.data);
        if (n.left != null && n.right != null) {
            getAllHelper(result, n.left);
            getAllHelper(result, n.right);
        } else if (n.right == null) {
            getAllHelper(result, n.left);
        } else if (n.left == null) {
            getAllHelper(result, n.right);
        }
    }



    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        if (size <= 0) {
            throw new IllegalArgumentException("the tree can not be empty");
        }
        BSTNode<E> temp = root;
        E maxVal = temp.data;
        while (temp.right != null) {
            maxVal = temp.right.data;
            temp = temp.right;
        }
        return maxVal;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        if (size <= 0) {
            throw new IllegalArgumentException("the tree can not be empty");
        }
        return minValue(root);
    }

    /**
     * An add method that implements the add algorithm iteratively instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if (data == null) {
            throw new IllegalArgumentException("added node's data can't be null");
        }
        BSTNode<E> newNode = new BSTNode<>(data);
        BSTNode<E> temp = root;
        BSTNode<E> pointer = null;
        while (temp != null) {
            int dir = (data).compareTo(temp.data);
            pointer = temp;
            if (dir < 0) {
                temp = temp.left;
            } else if (dir > 0) {
                temp = temp.right;
            } else {
                //equal case: gotcha, same data, no-op
                return false;
            }
        }
        // here we are at the proper position
        if (pointer == null) {
            pointer = newNode;
            root = pointer;
            size++;
            return true;
        }
        int dir = (data).compareTo(pointer.data);
        if (dir < 0) {
            pointer.setLeft(newNode);
        } else if (dir > 0) {
            pointer.setRight(newNode);
        } else {
            return false;
        }
        size++;
        return true;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the 
     * smallest value (minimum) is returned. If kth = 1 the second smallest value is
     * returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        if (!(0 <= kth) || !(kth < size())) {
            throw new IllegalArgumentException("<br>pre: 0 <= kth < size()");
        }
        List<E> list = new ArrayList<>();
        kthHelper(root, list, kth);
        return list.get(kth);
    }

    private void kthHelper(BSTNode<E> n, List<E> list, int kth) {
        if (n == null) {
            return;
        }
        if (list.size() > kth) {
            return;
        }
        kthHelper(n.left, list, kth);
        list.add(n.data);
        kthHelper(n.right, list, kth);
    }


    /**
     * Return a List with all values in this Binary Search Tree that are less than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than the parameter value. If there are
     * no values in this tree less than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        ArrayList<E> result = new ArrayList<>();
        getAllLessThanHelper(root, value, result);
        Collections.sort(result);
        return result;
    }

    private void getAllLessThanHelper(BSTNode<E> n, E value, ArrayList<E> result) {
        if (n == null) {
            return;
        }
        E current = n.data;
        int dir = current.compareTo(value);
        if (dir < 0) {
            result.add(current);
            getAllLessThanHelper(n.left, value, result);
            getAllLessThanHelper(n.right, value, result);
        } else {
            // there will still be some suitable value in the left sub tree
            getAllLessThanHelper(n.left, value, result);
        }
    }


    /**
     * Return a List with all values in this Binary Search Tree that are greater than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the parameter value. If there are
     * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        ArrayList<E> result = new ArrayList<>();
        getAllGreaterThanHelper(root, value, result);
        Collections.sort(result);
        return result;
    }

    private void getAllGreaterThanHelper(BSTNode<E> n, E value, ArrayList<E> result) {
        if (n == null) {
            return;
        }
        E current = n.data;
        int dir = current.compareTo(value);
        if (dir > 0) {
            result.add(current);
            getAllGreaterThanHelper(n.left, value, result);
            getAllGreaterThanHelper(n.right, value, result);
        } else {
            // there will still be some suitable value in the right sub tree
            getAllGreaterThanHelper(n.right, value, result);
        }
    }



    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return numNodesAtDepthHelper(root, d, 0);
    }

    private int numNodesAtDepthHelper(BSTNode<E> n, int tgtD, int currentD) {
        if (n == null) {
            return 0;
        }
        if (currentD < tgtD) {
            return numNodesAtDepthHelper(n.left, tgtD, currentD + 1)
                    + numNodesAtDepthHelper(n.right, tgtD, currentD + 1);
        } else {
            return 1;
        }
    }


    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if (n != null) {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null); 
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,        
                BSTNode<E> initRight) {
            data = initValue; 
            left = initLeft; 
            right = initRight; 
        }

        public E getData() { 
            return data; 
        }

        public BSTNode<E> getLeft() { 
            return left;
        }

        public BSTNode<E> getRight() { 
            return right; 
        }

        public void setData(E theNewValue) { 
            data = theNewValue; 
        }

        public void setLeft(BSTNode<E> theNewLeft) { 
            left = theNewLeft; 
        }

        public void setRight(BSTNode<E> theNewRight) { 
            right = theNewRight; 
        }    
    }
}
