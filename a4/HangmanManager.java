/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:Diyuan Dai
 *  email address:daidiyuan@126.com
 *  UTEID:dd33653
 *  Section 5 digit ID: 50276
 *  Grader name: Tony
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.util.*;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance vars
    private ArrayList<String> wordFamily;
    private boolean debugOn;
    private int numGuesses;
    private ArrayList<Character> guessMade;
    private int wordLen;
    private char [] pattern;
    private Set<String> inputWords;
    private HangmanDifficulty diff;
    private int diffTerm;


    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        inputWords = words;
        wordFamily = new ArrayList<String>();
        // create a deep copy
        wordFamily.addAll(words);
        // set debugStatus
        this.debugOn = debugOn;

    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases. 
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        inputWords = words;
        // create a iterator for the deep copy
        wordFamily = new ArrayList<String>();
        // create a deep copy
        wordFamily.addAll(words);
        // set debugStatus
        this.debugOn = false;

    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given length
     */
    public int numWords(int length) {
        Iterator<String> it = inputWords.iterator();
        int count = 0;
        while (it.hasNext()) {
            String current = it.next();
            if (current.length() == length) {
                count++;
            }
        }
        return count;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a complete game of Hangman.
     * @param wordLen the length of the word to pick this time. numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {

        this.wordLen = wordLen;
        wordFamily = findLengthWord();
        this.numGuesses = numGuesses;
        pattern = new char[wordLen];
        for (int i = 0; i < wordLen; i++) {
            pattern[i] = ' ';
        }
        guessMade = new ArrayList<>();
        this.diff = diff;
        diffTerm = 0;
    }


    //helper method to find the words that meet length requirement.
    private ArrayList<String> findLengthWord() {
        // creat a new set to store the list of words that meet the length requirement
        ArrayList<String> temp = new ArrayList<>();
        Iterator<String> it = inputWords.iterator();
        // Using add int new set instead of using a lot of remove in original one can possibly save time
        // Because add is at most big O(1)
        while (it.hasNext()) {
            String current = it.next();
            if (current.length() == wordLen) {
                temp.add(current);
            }
        }
        return temp;
    }


    /**
     * The number of words still possible (live) based on the guesses so far. Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return wordFamily.size();
    }


    /**
     * Get the number of wrong guesses the user has left in this round (game) of Hangman.
     * @return the number of wrong guesses the user has left in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numGuesses;
    }


    /**
     * Return a String that contains the letters the user has guessed so far during this round.
     * The String is in alphabetical order. The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user has guessed so far during this round.
     */
    public String getGuessesMade() {
        StringBuilder sb = new StringBuilder();
        if (guessMade.size() == 0) {
            return "[]";
        } else {
            sb.append("[");
            // append all characters except the last one
            for (int i = 0; i < guessMade.size() - 1; i++) {
                sb.append(guessMade.get(i) + ", ");
            }
            // append the last one with the final "]" and no space.
            sb.append(guessMade.get(guessMade.size() - 1) + "]");
            return sb.toString();
        }
    }


    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman, false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        boolean result = guessMade.contains(guess);
        return result;
    }


    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {
        return toPattern(pattern);
    }


    // pre: !alreadyGuessed(ch)
    // post: return a tree map with the resulting patterns and the number of
    // words in each of the new patterns.
    // the return value is for testing and debugging purposes
    /**
     * Update the game status (pattern, wrong guesses, word list), based on the give
     * guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("The HangmanMain should not let this letter pass to makeGuess"
                    + " because this letter has already been guessed.");
        }
        // if it is not guessed, add this letter to the character list
        guessMade.add(guess);
        ArrayList<String> wordList = new ArrayList<>();
        TreeMap<String, Integer> resultMap = new TreeMap<>();
        char [] currentOnlyEvilPattern;
        // for all the words,
        // check if it contains the character guessed
        addWordContainGuess(wordList, resultMap, guess);
        ArrayList<String> evilPatternList;
        // do tie breaking and ensure there is the only one most evil Pattern
        evilPatternList = getEvilPatterns(resultMap, findWordNum(resultMap));
        currentOnlyEvilPattern = theOnlyEvilPattern(evilPatternList);
        // update wordList
        updateWordList(wordList, currentOnlyEvilPattern, guess);
        return resultMap;
    }

    private void addWordContainGuess(ArrayList<String> wordList, TreeMap<String, Integer> patternMap, char guess) {
        for (String currentWords : wordFamily) {
            boolean containGuess = false;
            char [] temp = new char[wordLen];
            // create a copy of current pattern
            for (int i = 0; i < wordLen; i++) {
                temp[i] = pattern[i];
            }
            // check for one single word
            for (int index = 0; index < wordLen; index++) {
                if (currentWords.charAt(index) == (guess)) {
                    containGuess = true;
                    temp[index] = guess;
                }
            }
            updatePatternMap(temp, patternMap);
            if (containGuess) {
                // add the word contains that letter into a list
                wordList.add(currentWords);
            }
        }

    }


    // a helper method to find the wordNum, which is the number of words in word family required under different difficulty
    private int findWordNum(TreeMap<String, Integer> resultMap) {
        ArrayList<Integer> wordNumList = new ArrayList<>();
        wordNumList.addAll(resultMap.values());
        Collections.sort(wordNumList);
        diffTerm++;
        if (ifHardest() || wordNumList.size() < 2) {
            //return the biggest word family's word number
            return wordNumList.get(wordNumList.size() - 1);
        } else {
            //return the second big word family word number
            return wordNumList.get(wordNumList.size() - 2);
        }
    }

    // a helper method to find the patterns which has most words under that pattern;
    private ArrayList<String> getEvilPatterns(TreeMap<String, Integer> resultMap, int maxCount) {
        ArrayList<String> evilPatternList = new ArrayList<>();
        for (String patternKey : resultMap.keySet()) {
            if (resultMap.get(patternKey) == maxCount) {
                evilPatternList.add(patternKey);
            }
        }
        return evilPatternList;
    }


    // a helper method to update word family for the next guess.
    private void updateWordList(ArrayList<String> wordList, char [] currentOnlyEvilPattern, char guess) {
        // to check if player is not necessarily correct
        if (toPattern(pattern).equals(toPattern(currentOnlyEvilPattern))) {
            numGuesses--;
            // if the current pattern is the most evil pattern, we need to remove those words contain guessed letter
            wordFamily.removeAll(wordList);
        } else {
            // update pattern to the new most evil pattern
            pattern = currentOnlyEvilPattern;
            // get the guess
            for (int wordIndex = 0; wordIndex < wordList.size(); wordIndex++) {
                // create a new list of character to compare
                String currentWord = wordList.get(wordIndex);
                char [] currentPattern = currentWord.toCharArray();
                // if the word does not meet the pattern, remove it
                boolean ifRemove = false;
                for (int charIndex = 0; charIndex < wordLen; charIndex++) {
                    // remove the words which contain the letter but do not follow the pattern
                    if ((pattern[charIndex] == guess && currentPattern[charIndex] != guess)
                            || currentPattern[charIndex] == guess && pattern[charIndex] != guess) {
                        wordList.remove(currentWord);
                        ifRemove = true;
                    }
                }
                if (ifRemove) {
                    wordIndex--;
                }
            }
            wordFamily = wordList;
        }
    }

    //a helper method to update the patternMap and return the updated value
    private int updatePatternMap(char [] tempPattern, TreeMap<String, Integer> patternMap) {
        // if the pattern is a new key,
        // put value 1
        if (!patternMap.containsKey(toPattern(tempPattern))) {
            patternMap.put(toPattern(tempPattern), 1);
            return 1;
        } else {
            // if the key is in the map before, update the value
            int currentNumber = patternMap.get(toPattern(tempPattern)) + 1;
            patternMap.put(toPattern(tempPattern), currentNumber);
            return currentNumber;
        }
    }

    // helper method to convert char[] to one word in string with '-';
    private String toPattern(char [] list) {
        StringBuilder sb = new StringBuilder();
        // append all characters
        for (int i = 0; i < wordLen; i++) {
            if (list [i] == ' ') {
                sb.append("-");
            } else {
                sb.append(list[i]);
            }
        }
        return sb.toString();
    }


    // helper method to do tie breaking
    private char [] theOnlyEvilPattern(ArrayList<String> patternList) {
        if (patternList.size() == 0) {
            throw new IllegalArgumentException("There must be at least one evil pattern");
        }
        char [] evilPattern = new char[wordLen];
        // if there are no tie
        if (patternList.size() == 1) {
            // if the max
            evilPattern = patternList.get(0).toCharArray();
            return evilPattern;
        } else if (ifHardest()) {
            // if there are two or more families with the maximum number of elements
            // break the tie by picking the one that reveals the fewest characters
            int maxEmptySpaces = 0;
            for (String pattern : patternList) {
                int currentEmptySpaces = 0;
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) == '-') {
                        currentEmptySpaces++;
                    }
                }
                // picking the one that reveals the fewest characters
                if (maxEmptySpaces < currentEmptySpaces) {
                    maxEmptySpaces = currentEmptySpaces;
                    evilPattern = (pattern).toCharArray();
                }
                // reveal the same number of characters  break the tie by picking the pattern
                // that is "smallest" based based on the lexigraphical ordering of Strings.
                if (maxEmptySpaces == currentEmptySpaces) {
                    if (pattern.compareTo(toPattern(evilPattern)) < 0) {
                        evilPattern = (pattern).toCharArray();
                    }
                }
            }
            return evilPattern;
        } else {
            return theSecondEvilPattern(patternList);
        }
    }


    // helper method to find secondEvilPattern for easier conditions
    private char [] theSecondEvilPattern(ArrayList<String> patternList) {
        TreeMap<Integer, String> map = new TreeMap<>();
        String evilPattern = "";
        for (String pattern : patternList) {
            int currentEmptySpaces = 0;
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == '-') {
                    currentEmptySpaces++;
                }
            }
            map.put(currentEmptySpaces, pattern);
        }
        ArrayList<Integer> intList = new ArrayList<>();
        intList.addAll(map.keySet());
        evilPattern = map.get(intList.get(intList.size() - 2));
        return evilPattern.toCharArray();
    }

    //helper method to determine whether the list should be the hardest
    private boolean ifHardest () {
        if (diff == HangmanDifficulty.HARD) {
            // return the number of word in the biggest word family
            return true;
        } else if (diff == HangmanDifficulty.MEDIUM) {
            // if it is the 4th or times of 4 guess, return the second big word family
            if (diffTerm % 4 == 0) {
                return false;

            } else {
                // return the number of word in the biggest word family
                return true;
            }
            //easy case
        } else {
            if (diffTerm % 2 == 0) {
                return false;
            } else {
                // return the number of word in the biggest word family
                return true;
            }
        }
    }


    /**
     * Return the secret word this HangmanManager finally ended up picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if (numWordsCurrent() <= 0) {
            throw new IllegalArgumentException("No words left in wordFamily");
        }
        if (numWordsCurrent() == 1) {
            return wordFamily.get(0);
        } else {
            Random r = new Random();
            // randomly choose one
            String result = wordFamily.get(r.nextInt(numWordsCurrent()));
            return result;
        }
    }
}
