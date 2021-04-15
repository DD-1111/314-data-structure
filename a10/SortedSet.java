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
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    //O(1)
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */

    //O(NlogN)
    public SortedSet(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<E> target = new ArrayList<>();
        myCon = new ArrayList<>();
        for (E element : other) {
            target.add(null);
            myCon.add(element);
        }
        mergeSort(myCon);
    }


    //O(Nlog(N))
    public void mergeSort(ArrayList<E> con) {
        mergeSortHelper(con);
    }

    private void mergeSortHelper(ArrayList<E> con) {
        ArrayList<E> left = new ArrayList<>();
        ArrayList<E> right = new ArrayList<>();
        if (con.size() > 1) {
            int pivotIndex = con.size() / 2;
            // copy left half of numbers
            for (int i = 0; i < pivotIndex; i++) {
                left.add(con.get(i));
            }
            //copy right half of numbers
            for (int j = pivotIndex; j < con.size(); j++) {
                right.add(con.get(j));
            }
            mergeSortHelper(left);
            mergeSortHelper(right);
            merge(con, left, right);
        }
    }

    private void merge(ArrayList<E> list, ArrayList<E> left, ArrayList<E> right) {
        ArrayList<E> temp = new ArrayList<>();
        int elementsIndex = 0, leftIndex = 0, rightIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0 ) {
                list.set(elementsIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                list.set(elementsIndex, right.get(rightIndex));
                rightIndex++;
            }
            elementsIndex++;
        }
        copyRemaining(leftIndex, rightIndex, elementsIndex, temp, left, right, list);
    }


    public void copyRemaining(int leftIndex, int rightIndex, int elementsIndex, ArrayList<E> temp, ArrayList<E> left, ArrayList<E> right, ArrayList<E> list) {
        int tempIndex = 0;
        if (leftIndex >= left.size()) {
            temp = right;
            tempIndex = rightIndex;
        } else {
            temp = left;
            tempIndex = leftIndex;
        }

        for (int i = tempIndex; i < temp.size(); i++) {
            list.set(elementsIndex, temp.get(i));
            elementsIndex++;
        }
    }


    // O(N)
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (myCon.size() == 0) {
            myCon.add(item);
            return true;
        }
        if (!this.contains(item)) {
            int position = 0;
            while (item.compareTo(myCon.get(position)) > 0) {
                if (position >= myCon.size() - 1) {
                    myCon.add(item);
                    return true;
                }
                position++;
            }
            myCon.add(position, item);
            return true;
        }
        return false;
    }


    // O(N)
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("an other set can't be null");
        }
        if (otherSet instanceof SortedSet) {
            ArrayList<E> temp = new ArrayList<>();
            temp.addAll(myCon);
            boolean added = false;
            for (E element : ((SortedSet<E>) otherSet).myCon) {
                if (!myCon.contains(element)) {
                    temp.add(element);
                    added = true;
                }
            }
            mergeSort(temp);
            myCon = temp;
            return added;
        } else {
            return super.addAll(otherSet);
        }

    }

    // O(N)
    public void clear() {
        myCon = new ArrayList<E>();
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    // O(N)
    public boolean equals (Object other) {
        return super.equals(other);
    }


    /**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    @Override
    // O(N)

    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException();
        }
        ISet<E> result = new SortedSet<>();
        if (otherSet instanceof SortedSet) {
            diffInterHelper(otherSet, result, false);
        } else {
            //same as unsorted one. O(n2)
            for (E item : myCon) {
                if (!otherSet.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }


    @Override
    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    //  O(N)
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException();
        }
        ISet<E> result = new SortedSet<>();
        if (otherSet instanceof SortedSet) {
            diffInterHelper(otherSet, result, true);
        } else {
            //same as unsorted one. O(n2)
            for (E item : myCon) {
                if (otherSet.contains(item))
                    result.add(item);
            }
        }
        return result;
    }


    // helper method to do the repetitive part in both method Intersection and Difference
    private void diffInterHelper(ISet<E> otherSet, ISet<E> result, boolean isInter) {
        SortedSet<E> thatSortedSet = (SortedSet<E>) otherSet;
        int thisIndex = 0, thatIndex = 0;
        while (thisIndex < myCon.size()
                && thatIndex < thatSortedSet.myCon.size()) {
            E thisValue = myCon.get(thisIndex);
            E thatValue = thatSortedSet.myCon.get(thatIndex);
            if (thisValue.compareTo(thatValue) == 0) {
                //found an element
                thatIndex++;
                thisIndex++;
                if (isInter) {
                    result.add(thisValue);
                }
            } else if (thisValue.compareTo(thatValue) < 0) {
                // haven't found it keep it found.
                thisIndex++;
                if (!isInter) {
                    result.add(thisValue);
                }
            } else {
                thatIndex++;
                //haven't found it yet, but it might exist
            }
        }
        if (!isInter) {
            while (thisIndex < myCon.size()) {
                result.add(myCon.get(thisIndex));
                thisIndex++;
            }
        }
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */

    //  O(N)
    public ISet<E> union (ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException();
        }
        ISet<E> output = otherSet.difference(this);
        output.addAll(this);
        return output;
    }



    //O(1)
    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }


    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    //  O(1)
    public int size() {
        return myCon.size();
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */
    //  O(N)
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("removed item can;t be null");
        }
        return myCon.remove(item);
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    //  O(logN)
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item added cant be null");
        }
        return binarySearch(item) != -1;
    }

    // helper method to realize binary search to save efficiency
    private int binarySearch(E target) {
        int pivot, left = 0;
        int right = myCon.size() - 1;
        while (left <= right) {
            // take new mid point as pivot
            pivot = left + (right - left) / 2;
            int compResult = myCon.get(pivot).compareTo(target);
            if (compResult == 0) {
                // gotcha
                return pivot;
            }
            if (compResult > 0) {
                // if current mycon.get is bigger, change the right index to pivot - 1
                right = pivot - 1;
            } else {
                // change the left index to pivot + 1 otherwise
                left = pivot + 1;
            }
        }
        return -1;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     * false otherwise.
     */

    //O(N)
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Set can't be empty");
        }
        if (otherSet instanceof SortedSet) {
            int thisIndex = 0, thatIndex = 0;
            SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
            while (!(thisIndex >= myCon.size()) && !(thatIndex >= otherSortedSet.myCon.size())) {
                E thisValue = myCon.get(thisIndex);
                E thatValue = otherSortedSet.myCon.get(thatIndex);
                if (!thisValue.getClass().equals(thatValue.getClass())) {
                    return false;
                }
                if (thisValue.compareTo(thatValue) == 0) {
                    //found an element
                    thisIndex++;
                    thatIndex++;
                } else if (thisValue.compareTo(thatValue) < 0) {
                    //haven't found it yet, it might exist
                    thisIndex++;
                } else {
                    //element does not exist, return false
                    return false;
                }
            }
            //check if we've went through the otherSet
            boolean result = thatIndex == otherSortedSet.myCon.size();
            return result;
        } else {
            //if otherSet is a unsorted set so just super
            boolean result = super.containsAll(otherSet);
            return result;
        }
    }
}
