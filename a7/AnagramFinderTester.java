import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
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

public class AnagramFinderTester {


    private static final String testCaseFileName = "C:\\Users\\beast\\IdeaProjects\\ass7\\src\\testCaseAnagrams.txt";
    private static final String dictionaryFileName = "C:\\Users\\beast\\IdeaProjects\\ass7\\src\\d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {

        cs314StudentTestsForLetterInventory();

        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }


    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here.
        LetterInventory lets1 = new LetterInventory("weweq");
        Object expected = 5;
        Object actual = lets1.size();
        showTestResults(expected, actual, 1, " size on empty LetterInventory");

        lets1 = new LetterInventory("2213%%#$34weweweweq");
        expected = 9;
        actual = lets1.size();
        showTestResults(expected, actual, 2, " size on empty LetterInventory");

        expected = "eeeeqwwww";
        actual = lets1.toString();
        showTestResults(expected, actual, 3, " toString on empty LetterInventory");

        lets1 = new LetterInventory("2233");
        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 4, " toString on empty LetterInventory");

        expected = 0;
        actual = lets1.get('B');
        showTestResults(expected, actual, 5, " get on empty LetterInventory");

        lets1 = new LetterInventory("zz2233");
        expected = 2;
        actual = lets1.get('z');
        showTestResults(expected, actual, 6, " get on empty LetterInventory");

        expected = false;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 7, " isEmpty on not-empty LetterInventory");

        lets1 = new LetterInventory("2233");
        expected = true;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 8, " isEmpty on not-empty LetterInventory");


        LetterInventory lets2 = new LetterInventory("\"Stanford AaUniversity\"!! Jr<>(())G");
        System.out.println(lets2.toString());
        expected = "aaadefgiijnnorrrssttuvy";
        actual = lets2.toString();
        showTestResults(expected, actual, 9, " LetterInventory constructor and toString");

        expected = 23;
        actual = lets2.size();
        showTestResults(expected, actual, 10, " LetterInventory size");

        expected = null;
        actual = lets1.subtract(lets2);
        showTestResults(expected, actual, 11, "LetterInventory subtract");

        lets1 = new LetterInventory("standa ---- ton");
        expected = "aefgiijrrrsuvy";
        actual = lets2.subtract(lets1).toString();
        showTestResults(expected, actual, 12, "LetterInventory subtract");

        expected = 14;
        actual = lets2.subtract(lets1).size();
        showTestResults(expected, actual, 13, "LetterInventory size after subtract");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 14, "LetterInventory isEmpty after subtract");

        expected = false;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 15, "LetterInventory equals");

        lets2 = new LetterInventory("staand ---- not");
        expected = true;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 16, "LetterInventory equals");

        lets1 = new LetterInventory("zzz");
        expected = "aadnnosttzzz";
        System.out.println(lets2.toString());
        LetterInventory lets3 = lets1.add(lets2);
        actual = lets3.toString();
        showTestResults(expected, actual, 17, "LetterInventory add");

        expected = 12;
        actual = lets3.size();
        showTestResults(expected, actual, 18, "LetterInventory size after add");

        expected = false;
        actual = lets3.isEmpty();
        showTestResults(expected, actual, 19, "LetterInventory isEmpty after add");

        lets3 = lets1.add(lets2);
        LetterInventory lets4 = lets2.add(lets1);
        System.out.println(lets3.size());
        System.out.println(lets4.size());
        System.out.println(lets3.equals(lets4));
        showTestResults(lets3, lets4, 20, "LetterInventory add and equals");

        expected = false;
        StringBuilder foo = new StringBuilder();
        actual = lets3.equals(foo);
        showTestResults(expected, actual, 21, "LetterInventory equals");

        expected = false;
        String str = "abeills";
        lets3 = new LetterInventory("ISAbell!!");
        actual = lets3.equals(str);
        showTestResults(expected, actual, 22, "LetterInventory equals");

    }


    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, int testNum, String featureTested) {
        System.out.println("Test Number " + testNum + " testing " + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, boolean displayAnagrams) {
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if (checkPassOrFailTest(currentTest, actualAnagrams)) {
                    solverTestCasesPassed++;
                }
                System.out.println();
                System.out.println("Time to find anagrams: " + st.time());
                if (displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    System.out.println();
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }
                System.out.println("\n******************************************\n");
                // System.out.println("Number of calls to recursive helper method: " + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for(List<String> singleAnagram : anagrams)
            System.out.println(singleAnagram);
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " + currentTest.anagrams.size());
        if (actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, but either a difference in anagrams or anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<String>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams : "Wrong number of angrams read or expected";
        }
    }
}
