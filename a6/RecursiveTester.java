

/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Diyuan Dai and Yinglei Fang, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: dd33653
 *  email address: daidiyuan@126.com
 *  Grader name: Tony
 *  Section number: 50276
 *
 *  Student 2
 *  UTEID: yf3675
 *  email address: 1608412635@qq.com
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        studentTests();
    }

    private static void doMazeTests() {
        int mazeTestNum = 1;
        String maze = "*****S$*****";
        runMazeTest(maze, 3, 0, mazeTestNum++);
        maze = "*E$$$$$$$$$YY**$$$SY";
        runMazeTest(maze, 5, 2, mazeTestNum++);
    }

    private static void runMazeTest(String rawMaze, int rows, int expected, int num) {
        char[][] maze = makeMaze(rawMaze, rows);
        num += 14;
        System.out.println("Can escape maze test number " + num);
        printMaze(maze);
        int actual = Recursive.canEscapeMaze(maze);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result:   " + actual);
        if (expected == actual) {
            System.out.println("passed test " + num);
        } else {
            System.out.println("FAILED TEST " + num);
        }
        System.out.println();
    }

    // print the given maze
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                    + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    private static void doFairTeamsTests() {

        int[] abilities = {7, 7, 7, 7, 7, 7, 7};
        if (Recursive.minDifference(3, abilities) == 7)
            System.out.println( "Test 11 passed. min difference.");
        else
            System.out.println( "Test 11 failed. min difference.");

        if (Recursive.minDifference(7, abilities) == 0)
            System.out.println( "Test 12 passed. min difference.");
        else
            System.out.println( "Test 12 failed. min difference.");

        abilities = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        if (Recursive.minDifference(4, abilities) == 0)
            System.out.println( "Test 13 passed. min difference.");
        else
            System.out.println( "Test 13 failed. min difference.");

        System.out.println( "Test 14. Stress Test. min difference \n " +
                "only print time used, no standard with how fast is required");
        int stressTestN = 20;
        abilities = new int[stressTestN];
        Random r = new Random();
        for (int index = 0; index < stressTestN - 1; index++) {
            abilities[index] = r.nextInt(20);
        }
        Stopwatch timer = new Stopwatch();
        timer.start();
        System.out.println("minDif of such a random array with "
                + stressTestN + " number of scores in it: " +
                Recursive.minDifference(3, abilities));
        timer.stop();
        System.out.println(timer.toString());
    }

    private static void doFlowOffMapTests() {
        int testNum = 9;
        int[][] world = {{5,5,5,5,5,5},
                {5,4,4,4,4,5},
                {5,4,4,4,4,5},
                {5,4,4,4,4,5},
                {5,4,4,4,4,5},
                {5,4,4,4,4,5},
                {5,4,4,4,4,5},
                {5,5,5,5,5,5}};
        doOneFlowTest(world, 3, 3, false, testNum++);
        world = new int[][]
                       {{10, 10, 10, 10, 10, 0, 10},
                        {10, 10, 10,  7, 10, 1, 10},
                        {10, 10, 10,  6,  5,  2, 10},
                        {10, 10, 10,  6, 10, 10, 10},};

        doOneFlowTest(world, 3, 1, true, testNum++);
    }

    private static void doOneFlowTest(int[][] world, int r, int c, boolean expected, int testNum) {
        System.out.println("Can Flow Off Map Test Number " + testNum);
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        System.out.println("Start location = " + r + ", " + c);
        System.out.println("Expected result = " + expected + " actual result = " + actual);
        if (expected == actual) {
            System.out.println("passed test " + testNum + " can flow off map.");
        } else {
            System.out.println("FAILED TEST " + testNum + " can flow off map.");
        }
        System.out.println();
    }
    //你写
    private static void doListMnemonicsTests() {
        ArrayList<String> mnemonics = Recursive.listMnemonics("23");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("AD");
        expected.add("AE");
        expected.add("AF");
        expected.add("BD");
        expected.add("BE");
        expected.add("BF");
        expected.add("CD");
        expected.add("CE");
        expected.add("CF");
        if (mnemonics.equals(expected))
            System.out.println( "Test 7 passed. Phone mnemonics.");
        else
            System.out.println( "Test 7 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("33");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("DD");
        expected.add("DE");
        expected.add("DF");
        expected.add("EF");
        expected.add("EE");
        expected.add("ED");
        expected.add("FE");
        expected.add("FD");
        expected.add("FF");
        Collections.sort(expected);
        if (mnemonics.equals(expected))
            System.out.println( "Test 8 passed. Phone mnemonics.");
        else
            System.out.println( "Test 8 failed. Phone mnemonics.");
    }

    private static void doNextIsDoubleTests() {
        int[] numsForDouble = {1, 2, 4, 0, 16, 32, 64, 128, 0};
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 5;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 5 passed. next is double.");
        else
            System.out.println( "Test 5 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 7;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 6 passed. next is double.");
        else
            System.out.println( "Test 6 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
    }

    private static void doReverseTests() {
        String actualRev = Recursive.revString("WoWWW");
        String expectedRev = "WWWoW";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 3 passed. reverse string.");
        else
            System.out.println( "Test 3 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);

        actualRev = Recursive.revString("Garen and Darius");
        expectedRev = "suiraD dna neraG";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 4 passed. reverse string.");
        else
            System.out.println( "Test 4 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
    }

    private static void doBinaryTests() {
        String actualBinary = Recursive.getBinary(20);
        String expectedBinary = "10100";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 1 passed. get binary.");
        else
            System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        actualBinary = Recursive.getBinary(77);
        expectedBinary = "1001101";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 2 passed. get binary");
        else
            System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
        System.out.println();
    }

    // pre: r != null
    // post: run student test
    private static void studentTests() {
        // CS314 students put your tests here
        doBinaryTests();
        doReverseTests();
        doNextIsDoubleTests();
        doListMnemonicsTests();
        doFlowOffMapTests();
        doFairTeamsTests();
        doMazeTests();
    }
}

