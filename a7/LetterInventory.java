
/* Student information for assignment:
 *
 *  On my honor, Diyuan Dai, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Number of slip days I am using: 2
 */

public class LetterInventory {
    private final int generalSize = 26;
    private int validSize;
    private int[] con;

    public LetterInventory(String inputString) {
        validSize = 0;
        inputString = inputString.toLowerCase();
        con = new int[generalSize];
        char[] letterList = inputString.toCharArray();
        for (char ch : letterList) {
            if ('a' <= ch && ch <= 'z') {
                int chIndex = ch - 'a';
                con[chIndex]++;
                validSize++;
            }
        }
    }

    /**
     * A method named get that accepts a char and returns
     * the frequency of that letter in this LetterInventory.
     *   <br>pre: 'a' <= letter && letter <= 'z'.
     *   <br>post: Returns returns the frequency of that letter in this LetterInventory.
     *   @param letter the target letter
     *   @return Int of frequency of that letter in this LetterInventory.
     */
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        if (!('a' <= letter && letter <= 'z')) {
            throw new IllegalArgumentException("parameter of this method should be a English letter");
        }
        return con[(int) letter - 'a'];
    }

    /**
     * a method named size that returns the total number of letters
     *   <br>pre: none
     *   <br>post: returns the total number of letters in this LetterInventory
     *   @return total number of letters in this LetterInventory
     *   This method shall be O(1).
     */
    public int size() {
        return validSize;
    }

    /**
     * a method named size return if the size of this LetterInventory is 0
     *   <br>pre: none
     *   <br>post: returns a boolean of the result that if the size of this LetterInventory is 0
     *   @return returns a boolean : if the size of this LetterInventory is 0
     *   This method shall be O(1).
     */
    public boolean isEmpty() {
        return (validSize == 0);
    }

    /**
     * a method named toString that returns a String representation of this LetterInventory.
     *   <br>pre: none
     *   <br>post: returns a String representation of this LetterInventory.
     *   @return returns a String representation of this LetterInventory.
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < generalSize; i++) {
            for (int timesLeft = con[i]; timesLeft > 0; timesLeft--) {
                result += (char) ('a' + i);
            }
        }
        return result;
    }

    /**a method named add with one parameter, another LetterInventory object.
     * The methods returns a new LetterInventory object
     * created by adding the letter frequencies of
     * the explicit parameter from the calling object's (this) letter frequencies.
     * <br>pre: the parameter otherLetterInventory cant be null
     * <br>post: returns a new LetterInventory object and neither the calling object
     * or the explicit parameter are altered as a result of this method call.
     * @param otherLetterInventory: the target which would add with this LV.
     */
    public LetterInventory add(LetterInventory otherLetterInventory) {
        if (otherLetterInventory == null) {
            throw new IllegalArgumentException("object can't be null");
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < generalSize; i++) {
            result.set(i, con[i] + otherLetterInventory.con[i]);
        }
        // return the new created LV as result
        return result;
    }
    /**a method named subtract with one parameter, another LetterInventory object.
     * The methods returns a new LetterInventory object
     * created by subtracting the letter frequencies of
     * the explicit parameter from the calling object's (this) letter frequencies.
     * <br>pre: the parameter otherLetterInventory cant be null
     * <br>post: returns a new LetterInventory object and neither the calling object
     * or the explicit parameter are altered as a result of this method call.
     * If any of the resulting letter counts are less than 0 the method shall return null.
     * @param otherLetterInventory: the target which would subtract with this LV.
     */
    public LetterInventory subtract(LetterInventory otherLetterInventory) {
        if (otherLetterInventory == null) {
            throw new IllegalArgumentException("object can't be null");
        }
        // no need to check, definitely a null result
        if (otherLetterInventory.size() > this.validSize) {
            return null;
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < generalSize; i++) {
            // If any of the resulting letter counts are less than 0, return null.
            if (con[i] < otherLetterInventory.con[i]) {
                return null;
            }
            result.set(i, con[i] - otherLetterInventory.con[i]);
        }
        return result;
    }

    // helper method to set value of one element and edit the size
    private void set(int index, int newValue) {
        validSize += newValue - con[index];
        con[index] = newValue;
    }
    /**override the equals method from Object. Two LetterInventorys are equal
     * if the frequency for each letter in the alphabet is the same.
     * <br>pre: none
     */
    public boolean equals(Object rightHandSide) {
        // compare two null objects
        if (rightHandSide != null && this.getClass() == rightHandSide.getClass()) {
            LetterInventory otherLetterInventory = (LetterInventory) rightHandSide;
            // can't be equal if they don't have the same size
            if (validSize != otherLetterInventory.validSize) {
                return false;
            }
            // check all the elements' value
            for (int i = 0; i < generalSize; i++) {
                if (otherLetterInventory.con[i] != this.con[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}