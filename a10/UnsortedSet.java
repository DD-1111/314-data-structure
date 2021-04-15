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
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;

	//O(1)
	public UnsortedSet() {
		myCon = new ArrayList<E>();
	}

	// O(N)
	public boolean add(E item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (contains(item)) {
			return false;
		}
		else{
			myCon.add(item);
		}
		return true;
	}

	// O(N^2)
	@Override
	public boolean addAll(ISet<E> otherSet) {
		return super.addAll(otherSet);
	}

	//O(1)
	public void clear() {
		myCon = new ArrayList<E>();
	}

	// O(N^2)
	public boolean containsAll(ISet<E> otherSet) {
		return super.containsAll(otherSet);
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
	//O(N^2)
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException();
		}
		UnsortedSet<E> output = new UnsortedSet<>();
		for (E val : myCon) {
			if (!otherSet.contains(val)) {
				output.add(val);
			}
		}
		return output;
	}



	//O(N^2)
	@Override
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException();
		}
		UnsortedSet<E> output = new UnsortedSet<E>();
		for (E val : otherSet) {
			if (myCon.contains(val)) {
				output.add(val);
			}
		}
		return output;
	}

	// O(1)
	@Override
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	//O(N)
	@Override
	public boolean remove(E item) {
		return super.remove(item);
	}

	//O(1)
	@Override
	public int size() {
		return myCon.size();
	}


	//O(N^2)
	@Override
	/**
	 * Create a new set that is the union of this set and otherSet.
	 * <br>pre: otherSet != null
	 * <br>post: returns a set that is the union of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br> pre: otherSet != null
	 * @param otherSet != null
	 * @return a set that is the union of this set and otherSet
	 */
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null){
			throw new IllegalArgumentException();
		}
		ISet<E> output = otherSet.difference(this);
		output.addAll(this);
		return output;
	}

	//O(N)
	/**
	 * Determine if this set is equal to other.
	 * Two sets are equal if they have exactly the same elements.
	 * The order of the elements does not matter.
	 * <br>pre: none
	 * @param other the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
