/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Diyuan Dai and Yinglei Fang), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:1
 *
 *  Student 1 (Student whose turn in account is being used) Diyuan Dai
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Section number:50276
 *  
 *  Student 2 
 *  UTEID:yf3675
 *  email address:1608412635@qq.com
 *  Grader name:Tony
 *  Section number:50276
 *  
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* NO INSTANCE VARIABLES ALLOWED.
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * Also no direct references to ArrayList or other Java Collections.
     */

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */

    // O(N)
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item added cant be null");
        }
        if (this.contains(item)) {
            return false;
        } else {
            this.add(item);
            return true;
        }
    }

    /**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    // O(N^2)
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("set added cant be null");
        }
        boolean result = false;
        for (E val : otherSet) {
            if (!this.contains(val)) {
                this.add(val);
                result = true;
            }
        }
        return result;
    }


    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */

    //O(N)
    public void clear() {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }


    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    //O(N)
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item added cant be null");
        }
        for (E element : this) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }


    //O(N^2)
    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     * false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {
        for (E element : otherSet) {
            boolean contains = contains(element);
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */

    //O(N^2)
    public boolean equals(Object other) {
        if (other instanceof AbstractSet) {
            AbstractSet<E> otherSet = (AbstractSet<E>) other;
            if (otherSet.size() == this.size() && this.containsAll(otherSet)) {
                    return true;
            }
        }
        return false;
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    //O(N^2)
    public ISet<E> union(ISet<E> otherSet) {
        for (E element : otherSet) {
            for (E val : this) {
                if (!val.equals(element)) {
                    this.add(val);
                }
            }
        }
        return this;
    }
    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */

    //O(N)
    public boolean remove(E item) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (item.equals(it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }


    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */

    //O(N)
    public int size() {
        int size = 0;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            size++;
        }
        return size;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }
        result.append(")");
        return result.toString();
    }
}
