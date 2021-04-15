import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

/*  Student information for assignment:
 *
 *  Name:DIYUAN DAI
 *  email address:daidiyuan@126.com
 *  UTEID:dd33653
 *  Section 5 digit ID: 50276
 *  Grader name:
 *  Number of slip days used on this assignment:
 */


/* CS314 Students: place results of shared birthdays experiments in this comment.
experiment1: What is the average number of pairs of people with shared birthdays?
        The average is 45 pairs of people with shared birthdays
experiment2:
Num people: 2, number of experiments with one or more shared birthday: 132, percentage: 0.264
Num people: 3, number of experiments with one or more shared birthday: 424, percentage: 0.848
Num people: 4, number of experiments with one or more shared birthday: 809, percentage: 1.618
Num people: 5, number of experiments with one or more shared birthday: 1356, percentage: 2.712
Num people: 6, number of experiments with one or more shared birthday: 2060, percentage: 4.12
Num people: 7, number of experiments with one or more shared birthday: 2892, percentage: 5.784
Num people: 8, number of experiments with one or more shared birthday: 3709, percentage: 7.418
Num people: 9, number of experiments with one or more shared birthday: 4649, percentage: 9.298
Num people: 10, number of experiments with one or more shared birthday: 5770, percentage: 11.54
Num people: 11, number of experiments with one or more shared birthday: 7000, percentage: 14.0
Num people: 12, number of experiments with one or more shared birthday: 8265, percentage: 16.53
Num people: 13, number of experiments with one or more shared birthday: 9830, percentage: 19.66
Num people: 14, number of experiments with one or more shared birthday: 11111, percentage: 22.222
Num people: 15, number of experiments with one or more shared birthday: 12579, percentage: 25.158
Num people: 16, number of experiments with one or more shared birthday: 14293, percentage: 28.586
Num people: 17, number of experiments with one or more shared birthday: 15793, percentage: 31.586
Num people: 18, number of experiments with one or more shared birthday: 17252, percentage: 34.504
Num people: 19, number of experiments with one or more shared birthday: 18945, percentage: 37.89
Num people: 20, number of experiments with one or more shared birthday: 20642, percentage: 41.284
Num people: 21, number of experiments with one or more shared birthday: 22106, percentage: 44.212
Num people: 22, number of experiments with one or more shared birthday: 23740, percentage: 47.48
Num people: 23, number of experiments with one or more shared birthday: 25316, percentage: 50.632
Num people: 24, number of experiments with one or more shared birthday: 26827, percentage: 53.654
Num people: 25, number of experiments with one or more shared birthday: 28612, percentage: 57.224
Num people: 26, number of experiments with one or more shared birthday: 29991, percentage: 59.982
Num people: 27, number of experiments with one or more shared birthday: 31491, percentage: 62.982
Num people: 28, number of experiments with one or more shared birthday: 32791, percentage: 65.582
Num people: 29, number of experiments with one or more shared birthday: 34068, percentage: 68.136
Num people: 30, number of experiments with one or more shared birthday: 35351, percentage: 70.702
Num people: 31, number of experiments with one or more shared birthday: 36497, percentage: 72.994
Num people: 32, number of experiments with one or more shared birthday: 37740, percentage: 75.48
Num people: 33, number of experiments with one or more shared birthday: 38886, percentage: 77.772
Num people: 34, number of experiments with one or more shared birthday: 39746, percentage: 79.492
Num people: 35, number of experiments with one or more shared birthday: 40811, percentage: 81.622
Num people: 36, number of experiments with one or more shared birthday: 41599, percentage: 83.198
Num people: 37, number of experiments with one or more shared birthday: 42333, percentage: 84.666
Num people: 38, number of experiments with one or more shared birthday: 43196, percentage: 86.392
Num people: 39, number of experiments with one or more shared birthday: 43891, percentage: 87.782
Num people: 40, number of experiments with one or more shared birthday: 44491, percentage: 88.982
Num people: 41, number of experiments with one or more shared birthday: 45117, percentage: 90.234
Num people: 42, number of experiments with one or more shared birthday: 45585, percentage: 91.17
Num people: 43, number of experiments with one or more shared birthday: 46031, percentage: 92.062
Num people: 44, number of experiments with one or more shared birthday: 46625, percentage: 93.25
Num people: 45, number of experiments with one or more shared birthday: 47083, percentage: 94.166
Num people: 46, number of experiments with one or more shared birthday: 47315, percentage: 94.63
Num people: 47, number of experiments with one or more shared birthday: 47759, percentage: 95.518
Num people: 48, number of experiments with one or more shared birthday: 48011, percentage: 96.022
Num people: 49, number of experiments with one or more shared birthday: 48274, percentage: 96.548
Num people: 50, number of experiments with one or more shared birthday: 48480, percentage: 96.96
Num people: 51, number of experiments with one or more shared birthday: 48709, percentage: 97.418
Num people: 52, number of experiments with one or more shared birthday: 48850, percentage: 97.7
Num people: 53, number of experiments with one or more shared birthday: 49095, percentage: 98.19
Num people: 54, number of experiments with one or more shared birthday: 49181, percentage: 98.362
Num people: 55, number of experiments with one or more shared birthday: 49302, percentage: 98.604
Num people: 56, number of experiments with one or more shared birthday: 49408, percentage: 98.816
Num people: 57, number of experiments with one or more shared birthday: 49480, percentage: 98.96
Num people: 58, number of experiments with one or more shared birthday: 49556, percentage: 99.112
Num people: 59, number of experiments with one or more shared birthday: 49660, percentage: 99.32
Num people: 60, number of experiments with one or more shared birthday: 49705, percentage: 99.41
Num people: 61, number of experiments with one or more shared birthday: 49763, percentage: 99.526
Num people: 62, number of experiments with one or more shared birthday: 49822, percentage: 99.644
Num people: 63, number of experiments with one or more shared birthday: 49826, percentage: 99.652
Num people: 64, number of experiments with one or more shared birthday: 49852, percentage: 99.704
Num people: 65, number of experiments with one or more shared birthday: 49862, percentage: 99.724
Num people: 66, number of experiments with one or more shared birthday: 49904, percentage: 99.808
Num people: 67, number of experiments with one or more shared birthday: 49919, percentage: 99.838
Num people: 68, number of experiments with one or more shared birthday: 49931, percentage: 99.862
Num people: 69, number of experiments with one or more shared birthday: 49954, percentage: 99.908
Num people: 70, number of experiments with one or more shared birthday: 49959, percentage: 99.918
Num people: 71, number of experiments with one or more shared birthday: 49967, percentage: 99.934
Num people: 72, number of experiments with one or more shared birthday: 49965, percentage: 99.93
Num people: 73, number of experiments with one or more shared birthday: 49978, percentage: 99.956
Num people: 74, number of experiments with one or more shared birthday: 49988, percentage: 99.976
Num people: 75, number of experiments with one or more shared birthday: 49983, percentage: 99.966
Num people: 76, number of experiments with one or more shared birthday: 49988, percentage: 99.976
Num people: 77, number of experiments with one or more shared birthday: 49993, percentage: 99.986
Num people: 78, number of experiments with one or more shared birthday: 49989, percentage: 99.978
Num people: 79, number of experiments with one or more shared birthday: 49996, percentage: 99.992
Num people: 80, number of experiments with one or more shared birthday: 49994, percentage: 99.988
Num people: 81, number of experiments with one or more shared birthday: 49996, percentage: 99.992
Num people: 82, number of experiments with one or more shared birthday: 49996, percentage: 99.992
Num people: 83, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 84, number of experiments with one or more shared birthday: 49998, percentage: 99.996
Num people: 85, number of experiments with one or more shared birthday: 49999, percentage: 99.998
Num people: 86, number of experiments with one or more shared birthday: 49999, percentage: 99.998
Num people: 87, number of experiments with one or more shared birthday: 49999, percentage: 99.998
Num people: 88, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 89, number of experiments with one or more shared birthday: 49999, percentage: 99.998
Num people: 90, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 91, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 92, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 93, number of experiments with one or more shared birthday: 49998, percentage: 99.996
Num people: 94, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 95, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 96, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 97, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 98, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 99, number of experiments with one or more shared birthday: 50000, percentage: 100.0
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.0




*/

 
public class CodeCampTester {

    public static void main(String[] args){
        final String newline = System.getProperty("line.separator");



        // Test extra 1,hamming distance
        int h1[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int h2[] = new int[] {-2, -4, -3, 0, -6, 3, 6, 7, 9, 10};
        int expected = 8;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test extra 1 hamming distance: expected value: "
                + expected + ", actual value: " + actual);
        if( expected == actual )
            System.out.println(" passed Test extra 1 , hamming distance");
        else
            System.out.println(" ***** FAILED ***** Test extra 1 , hamming distance");
        // Test extra 2,hamming distance
        h1 = new int[] {-2, 5, 5, 66, 9, 0, 0, 0, 10, 10};
        h2 = new int[] {-2, -4, -3, 0, -6, 3, 6, 7, 10, 10};
        expected = 7;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test extra 2 hamming distance: expected value: "
                + expected + ", actual value: " + actual);
        if( expected == actual )
            System.out.println(" passed Test extra 2 , hamming distance");
        else
            System.out.println(" ***** FAILED ***** Test extra 2 , hamming distance");
        //Test extra 3, isPermutation
        int[] a = {1, 2, 3, 5};
        int[] b = {2, 1, 3, 4};
        boolean expectedBool = false;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test extra 3 isPermutation: expected value: "
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed Test extra 3, isPermutation");
        else
            System.out.println(" ***** FAILED ***** Test extra 3, isPermutation");

        //Test extra 4, is Permutation
        b = new int[]{2, 1, 5, 3};
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test extra 4 isPermutation: expected value: "
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed Test extra 4, isPermutation");
        else
            System.out.println(" ***** FAILED ***** Test extra 4, isPermutation");

        // Test extra 5, mostVowels

        String[] sList = {"aaairaaaua7799a", "aei23eou"};
        int expectedResult = 0;
        int actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + " Test extra 5: expected result: "
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed  Test extra 5, mostVowels");
        else
            System.out.println("***** FAILED *****  Test extra 5, mostVowels");

        
        // Test extra 6, mostVowels
        sList = new String[] {"Stauuying", null, "", "moo", "SEqethqUhgtrgfaiooritIOnAl FOrEst", "!!!!32424>>+13_)(*&^%$#@!>)))???\\///\n\n/n"};
        expectedResult = 4;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + " Test extra 6 mostVowels: expected result: "
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed  Test extra 6, mostVowels");
        else
            System.out.println("***** FAILED *****  Test extra 6, mostVowels");
        

        //Test extra 7, sharedBirthdays, simple test
        int pairs = CodeCamp.sharedBirthdays(6, 1);
        System.out.println(newline + "Test extra 7 shared birthdays: expected: 15, actual: " + pairs);
        int expectedShared = 15;
        if( pairs == expectedShared )
            System.out.println("passed Test extra 7, shared birthdays");
        else
            System.out.println("***** FAILED ***** Test extra 7, shared birthdays");

        //test 27, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(200, 1);
        System.out.println(newline + "Test extra 8 shared birthdays: expected: 19900" +
                ", actual: " + pairs);
        if( pairs == 19900 )
            System.out.println("passed Test extra 8, shared birthdays");
        else
            System.out.println("***** FAILED ***** Test extra 8, shared birthdays. " +
                    "Expected pairs to be 19900. Value returned: " + pairs);
 

        
        //Test extra 9, queensAreASafe
        char[][] board = { {'.', '.', '.'},
                          {'q', 'q', 'q'},
                          {'.', '.', 'q'}};
        
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test extra 9 queensAreSafe: expected value: "
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" Test extra 9, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** Test extra 9, queensAreSafe");

        //Test extra 10, queensAreASafe
        board = new char[][]{{'.', '.', '.', 'q'},
                             {'.', 'q', '.', '.'},
                             {'.', '.', '.', '.'},
                             {'.', '.', 'q', '.'}};
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test extra 10 queensAreSafe: expected value: "
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed Test extra 10, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** Test extra 10, queensAreSafe");

      
        // Test extra 11, getValueOfMostValuablePlot
        int[][] city = {{34, -2, -7},
                          {9, 23, -6},
                          {-4, 1, -8}};

        
        expected = 64;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test extra 11 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed Test extra 11, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** Test extra 11, getValueOfMostValuablePlot");


         // Test extra 12, getValueOfMostValuablePlot
        city[2][2] = 21;
        expected = 69;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test extra 12 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed Test extra 12, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** Test extra 12, getValueOfMostValuablePlot");

    } // end of main method
    
    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if(list == null)
            throw new IllegalArgumentException("list parameter may not be null.");
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for(int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}
