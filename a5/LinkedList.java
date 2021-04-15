
/*
 * Student information for assignment: On my honor, <NAME>, this programming
 * assignment is my own work and I have not provided this code to any other
 * student. 
 * UTEID: dd33653
 * email address: daidiyuan@126.com
 * Grader name: Tony
 * Number of slip days I am using:
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {
    // CS314 students. Add you instance variables here.
    // You decide what instance variables to use.
    // Must adhere to assignment requirements. No ArrayLists or Java
    // LinkedLists.
    private DoubleListNode<E> first;
    private DoubleListNode<E> last;
    private int size;
    // CS314 students, add constructors here:

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    // CS314 students, add methods here:
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<E> {
        private DoubleListNode<E> nodeWithNext;
        private boolean removeOk;
        private int removePos;

        private DLLIterator() {
            nodeWithNext = first;
            removePos = -1;
        }

        public boolean hasNext() {
            return nodeWithNext != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            removePos++;
            E result = nodeWithNext.getData();
            nodeWithNext = nodeWithNext.getNext();
            removeOk = true;
            return result;
        }

        @Override
        public void remove() {
            if (!removeOk) {
                throw new IllegalStateException("must call next");
            }
            removeOk = false;
            LinkedList.this.remove(removePos);
            removePos--;
        }
    }

    @Override
    public void add(E item) {
        // O(1)
        if (item == null) {
            throw new IllegalArgumentException("the added item should not be null");
        }
        if (size == 0) {
            DoubleListNode<E> targetNode = new DoubleListNode<E>(null, item, null);
            first = targetNode;
            last = targetNode;
        } else {
            DoubleListNode<E> targetNode = new DoubleListNode<E>(last, item, null);
            last.setNext(targetNode);
            last = targetNode;
        }
        size++;
    }

    @Override
    public void insert(int pos, E item) {
        // O(N) because  getNodeAtPos is O(N)
        if (!(pos >= 0) || !(pos <= size()) || item == null) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos <= size())"
                    + " and the inserted item should not be null");
        }
        if (pos == size) {
            add(item);
        } else if (pos == 0) {
            addFirst(item);
        } else {
            DoubleListNode<E> temp = getNodeAtPos(pos - 1);
            DoubleListNode<E> newNode = new DoubleListNode<E>(temp, item, temp.getNext());
            temp.setNext(newNode);
            newNode.getNext().setPrev(newNode);
            size++;
        }
    }

    // return a reference to the node at the given position
    // pre: 0 <= pos < size
    private DoubleListNode<E> getNodeAtPos(int pos) {
        // O(N)
        if (!(pos >= 0) || !(pos < size())) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos < size())");
        }
        if (pos == size - 1) {
            return last;
        }
        // if the pos is in the beginning half, start from beginning
        if (pos < size / 2) {
            DoubleListNode<E> temp = first;
            for (int i = 0; i < pos; i++) {
                temp = temp.getNext();
            }
            return temp;
        } else {
            // if pos is near end, start from end
            DoubleListNode<E> temp = last;
            for (int i = size - 1; i > pos; i--) {
                temp = temp.getPrev();
            }
            return temp;
        }
    }

    @Override
    public E set(int pos, E item) {
        //O(1)
        if (!(pos >= 0) || !(pos < size())) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos < size())");
        }
        E oldElement = get(pos);
        DoubleListNode<E> oldNode = getNodeAtPos(pos);
        oldNode.setData(item);
        return oldElement;

    }

    @Override
    public E get(int pos) {
        //O(1)
        if (!(pos >= 0) || !(pos < size())) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos < size())");
        }
        DoubleListNode<E> tgt = getNodeAtPos(pos);
        return tgt.getData();
    }

    @Override
    public E remove(int pos) {
        // O(N) because  getNodeAtPos is O(N)
        if (!(pos >= 0) || !(pos < size())) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos < size())");
        }
        if (pos == 0) {
            return removeFirst();
        } else if (pos == size - 1) {
            return removeLast();
        } else {
            DoubleListNode<E> temp = getNodeAtPos(pos - 1);
            E result = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            temp.getNext().setPrev(temp.getPrev().getPrev());
            size--;
            return result;
        }
    }

    @Override
    public boolean remove(E obj) {
        //O(N) because iterator is O(N)
        if (obj == null) {
            throw new IllegalArgumentException("the target item should not be null");
        }
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(obj)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public IList<E> getSubList(int start, int stop) {
        //O(N)
        if (!(start >= 0) || !(start < size()) || !(stop >= 0) || !(stop <= size())) {
            throw new IllegalArgumentException("the target start and stop index should be valid (0 <= start < size, start <= stop <= size)");
        }
        LinkedList<E> resultSubList = new LinkedList<>();
        for (int i = start; i < stop; i++) {
            resultSubList.add(this.get(i));
        }
        return resultSubList;
    }

    @Override
    public int size() {
        //O(1)
        return size;
    }

    @Override
    public int indexOf(E item) {
        //O(N)
        if (item == null) {
            throw new IllegalArgumentException("the target item should not be null");
        }
        if (size == 0) {
            return -1;
        } else {
            return indexOf(item, 0);
        }
    }

    @Override
    public int indexOf(E item, int pos) {
        //O(N) because getNodeAtPos is O(N)
        if (!(pos >= 0) || !(pos < size())) {
            throw new IllegalArgumentException("the target position should be valid (0 <= pos < size())");
        } else if (item == null) {
            throw new IllegalArgumentException("the target item should not be null");
        }
        if (size == 0) {
            return -1;
        }
        int result = pos;
        DoubleListNode<E> temp = getNodeAtPos(pos);
        while (result < size) {
            result++;
            if (temp.getData().equals(item)) {
                return result - 1;
            }
            temp = temp.getNext();
        }
        return -1;
    }

    @Override
    public void makeEmpty() {
        //O(1)
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void removeRange(int start, int stop) {
        //O(N) because  getNodeAtPos is O(N)
        if (!(start >= 0) || !(start < size()) || !(stop >= 0) || !(stop < size())) {
            throw new IllegalArgumentException("the target start and stop index should be valid (0 <= start < size, start <= stop < size)");
        }
        if (start == 0) {
            DoubleListNode<E> followingNode = getNodeAtPos(stop);
            first = followingNode;
            followingNode.setPrev(null);
        } else {
            DoubleListNode<E> startingNode = getNodeAtPos(start - 1);
            DoubleListNode<E> followingNode = getNodeAtPos(stop);
            startingNode.setNext(followingNode);
            followingNode.setPrev(startingNode);
        }
        size = size - (stop - start);
    }

    @Override
    public String toString() {
        //O(N)
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            DoubleListNode<E> temp = first;
            sb.append("[" + temp.getData().toString());
            temp = temp.getNext();
            // append all the elements left
            for (int i = 0; i < size - 1; i++) {
                sb.append(", " + temp.getData().toString());
                temp = temp.getNext();
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        //O(N)
        // return false if the object is not a LinkedList
        if (obj instanceof IList) {
            // if they have same size, keep checking
            if (this.size() == ((IList<E>) obj).size()) {
                // if both of them are empty return true
                if (size == 0) {
                    return true;
                }
                // check if only the elements in both lists are the same
                if (((IList<E>) obj).get(0).equals(this.get(0))) {
                    //creat iterators for each of both list and compare
                    //using iterators instead of for loop of get(i) can save time
                    Iterator<E> itOfThisList = this.iterator();
                    Iterator<E> itOfOtherList = ((LinkedList<E>) obj).iterator();
                    while (itOfThisList.hasNext()) {
                        E thisCurrentElement = itOfThisList.next();
                        E otherCurrentElement = itOfOtherList.next();
                        if (!thisCurrentElement.equals(otherCurrentElement)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * add item to the front of the list. <br>
     * pre:
     * @param item the  item != null <br>
     *      *      * pst: size() = old size() + 1, get(0) = item
     *      *      *      * daota to add to the front of this list
     */
    public void addFirst(E item) {
        //O(1)
        if (item == null) {
            throw new IllegalArgumentException("the target item should not be null");
        }
        if (size == 0) {
            add(item);
        } else {
            DoubleListNode<E> newFirstNode = new DoubleListNode<>(null, item, first);
            first.setPrev(newFirstNode);
            first = newFirstNode;
            size++;
        }
    }

    /**
     * add item to the end of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(size() -1) = item
     * 
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
        //O(1)
        add(item);
    }

    /**
     * remove and return the first element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old first element of this list
     */
    public E removeFirst() {
        //O(1)
        if (size == 0) {
            throw new IllegalArgumentException("Empty list, nothing to remove");
        }
        E result = first.getData();
        if (size == 1) {
            first = null;
            last = null;
        } else {
            DoubleListNode<E> secondNode = first.getNext();
            secondNode.setPrev(null);
            first = secondNode;
        }
        size--;
        return result;
    }

    /**
     * remove and return the last element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old last element of this list
     */
    public E removeLast() {
        //O(1)
        if (size == 0) {
            throw new IllegalArgumentException("Empty list, nothing to remove");
        }
        E result = last.getData();
        if (size == 1) {
            first = null;
            last = null;
        } else {
            DoubleListNode<E> lastSecondNode = last.getPrev();
            lastSecondNode.setNext(null);
            last = lastSecondNode;
        }
        size--;
        return result;
    }

}
