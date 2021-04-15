/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Diyuan Dai, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Number of slip days I am using: 2
 */


import java.util.*;
import java.util.Comparator;
public class AnagramSolver {
    private static HashMap<String, LetterInventory> letterInventoryMap;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("DICTIONS ERROR");
        }
        // CS314 Students, add your code here
        letterInventoryMap = new HashMap<>();
        for (String word : dictionary) {
            letterInventoryMap.put(word, new LetterInventory(word));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (maxWords < 0 || s == null) {
            throw new IllegalArgumentException("para error");
        }
        // if maxwords is 0, unchain that limit
        if (maxWords == 0) {
            maxWords--;
        }
        LetterInventory inputWordInv = new LetterInventory(s);
        ArrayList<List<String>> result = recursionBeginner(inputWordInv, maxWords);
        //sort my result with my comparator for List<String>
        Collections.sort(result, AnagramSolver.wordComparator);
        return result;
    }


    //Accepts a parameter LetterLV as input and prepares for recursion step with a list of possible words
    public static ArrayList<List<String>> recursionBeginner(LetterInventory inputLV, int maxWords) {
        ArrayList<String> currentWordFamily = new ArrayList<>();
        System.out.println("currentSize = " + inputLV.size());
        for (String currentWord : letterInventoryMap.keySet()) {
            // add the possible valid words in to a new ArrayList
            if (inputLV.subtract(letterInventoryMap.get(currentWord)) != null
                    && inputLV.size() >= currentWord.length()) {
                currentWordFamily.add(currentWord);
            }
        }
        System.out.println(currentWordFamily);
        ArrayList<List<String>> con = new ArrayList<>();
        recursionStep(inputLV, currentWordFamily, maxWords, new ArrayList<String>(), 0, con);
        return con;
    }


    public static void recursionStep(LetterInventory inputLV, ArrayList<String> wordFamily, int maxWords, List<String> currentTry, int index, ArrayList<List<String>> con) {
        // check the continuing condition, base case would be just doing nothing
        if (!inputLV.isEmpty() && maxWords != 0) {
            while (index < wordFamily.size()) {
                // go through all the words in wordFamily
                String currentWord = wordFamily.get(index);
                //System.out.println("currentWord" + currentWord);
                index++;
                //create a new index for next round, it will be iterated in next round
                int newIndex = index;
                LetterInventory currentLV = letterInventoryMap.get(currentWord);
                if (currentWord.length() <= inputLV.size() && inputLV.subtract(currentLV) != null) {
                    List<String> newCurrentTry = new ArrayList<>();
                    newCurrentTry.addAll(currentTry);
                    // do one hard copy of currentTry and then update the deep copy
                    newCurrentTry.add(currentWord);
                    newIndex--;
                    //System.out.println("currentTryUpdated");
                    Collections.sort(newCurrentTry);
                    LetterInventory newInputLV = inputLV.subtract(currentLV);
                    //the new param for next round will be iterated, will do backtrack automatically
                    if (newInputLV.isEmpty()) {
                        con.add(newCurrentTry);
                        //System.out.println("conUpdated");
                    }
                        //System.out.println("recursion");
                        recursionStep(newInputLV, wordFamily, maxWords - 1, newCurrentTry, newIndex, con);
                        // backtrack then
                    //System.out.println("currentTry reversed, backtrack done");
                    }
                }
            }
        }

        // inner class to compare the List<String>
    private static Comparator<List<String>> wordComparator = new Comparator<List<String>>() {

        @Override
        public int compare(List<String> listA, List<String> listB) {
            // compare the size firstly
            if (listA.size() < listB.size()) {
                return -1;
            } else if (listA.size() > listB.size()) {
                return 1;
            }
            //otherwise, they have same size
            int length = listA.size();
            //compare every corresponding words consecutively
            for (int i = 0; i < length; i++) {
                int comparaResult = listA.get(i).compareTo(listB.get(i));
                if (comparaResult != 0) {
                    return comparaResult;
                }
            }
            return 0;
        }

    };

}

