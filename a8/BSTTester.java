/*  Student information for assignment:
 *
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Number of slip days I am using:
 */


/*
 * Place results of experiments here:

 BST records:
--------------------------
Average time for N = 1000: 7.186E-4
Average height for N = 1000: 20.7
Average size for N = 1000: 1000.0
--------------------------
Average time for N = 2000: 6.4715E-4
Average height for N = 2000: 23.2
Average size for N = 2000: 2000.0
--------------------------
Average time for N = 4000: 9.674099999999999E-4
Average height for N = 4000: 26.8
Average size for N = 4000: 4000.0
--------------------------
Average time for N = 8000: 0.00262936
Average height for N = 8000: 29.1
Average size for N = 8000: 7999.9
--------------------------
Average time for N = 16000: 0.00536498
Average height for N = 16000: 31.9
Average size for N = 16000: 15999.9
--------------------------
Average time for N = 32000: 0.01184907
Average height for N = 32000: 35.0
Average size for N = 32000: 31999.7
--------------------------
Average time for N = 64000: 0.031959329999999994
Average height for N = 64000: 36.7
Average size for N = 64000: 63999.7
--------------------------
Average time for N = 128000: 0.08945387
Average height for N = 128000: 39.4
Average size for N = 128000: 127997.8
--------------------------
Average time for N = 256000: 0.17066873000000002
Average height for N = 256000: 42.6
Average size for N = 256000: 255991.2
--------------------------
Average time for N = 512000: 0.42594290999999995
Average height for N = 512000: 45.6
Average size for N = 512000: 511967.7
--------------------------
Average time for N = 1024000: 1.0803305499999998
Average height for N = 1024000: 50.2
Average size for N = 1024000: 1023869.6

 minimum possible tree height is log(N)
 *
 * TreeSet records:
 * --------------------------
Average time for N = 1000: 0.00180081
--------------------------
Average time for N = 2000: 6.6358E-4
--------------------------
Average time for N = 4000: 0.00138785
--------------------------
Average time for N = 8000: 0.00311978
--------------------------
Average time for N = 16000: 0.0064646700000000005
--------------------------
Average time for N = 32000: 0.01613085
--------------------------
Average time for N = 64000: 0.022309519999999996
--------------------------
Average time for N = 128000: 0.06632726
--------------------------
Average time for N = 256000: 0.17734329
--------------------------
Average time for N = 512000: 0.4565505900000001
--------------------------
Average time for N = 1024000: 1.0698535900000001

* The time spending for each N using BST and TreeSet are very close
*
*
* Repeating above experiments with an ascending list of ints:
* --------------------------
Average time for N = 1000: 0.0031266299999999996
Average height for N = 1000: 999.0
Average size for N = 1000: 1000.0
--------------------------
Average time for N = 2000: 0.0055812200000000005
Average height for N = 2000: 1999.0
Average size for N = 2000: 2000.0
--------------------------
Average time for N = 4000: 0.02410046
Average height for N = 4000: 3999.0
Average size for N = 4000: 4000.0
--------------------------
Average time for N = 8000: 0.10040754999999998
Average height for N = 8000: 7999.0
Average size for N = 8000: 8000.0
--------------------------
Average time for N = 16000: 0.35543073999999997
Average height for N = 16000: 15999.0
Average size for N = 16000: 16000.0

* Continuing with predicts:
* Average time for N = 32000: 1.3
Average height for N = 32000: 31999.0
Average size for N = 32000: 32000.0
*
* Average time for N = 64000: 5.0
Average height for N = 64000: 63999.0
Average size for N = 64000: 64000.0
*
* Average time for N = 128000: 20.0
Average height for N = 128000: 127999.0
Average size for N = 128000: 128000.0
*
* Average time for N = 256000: 100.0
Average height for N = 256000: 255999.0
Average size for N = 256000: 256000.0
*
* Average time for N = 512000: 400.0
Average height for N = 512000: 511999.0
Average size for N = 512000: 512000.0
*
*
Records for repeated experiment using integers in sorted order, but use the java TreeSet class:
--------------------------
Average time for N = 1000: 0.0013272999999999998
--------------------------
Average time for N = 2000: 9.8473E-4
--------------------------
Average time for N = 4000: 0.0020291099999999998
--------------------------
Average time for N = 8000: 0.00195308
--------------------------
Average time for N = 16000: 0.0027757999999999997
--------------------------
Average time for N = 32000: 0.00434471
--------------------------
Average time for N = 64000: 0.01093271
--------------------------
Average time for N = 128000: 0.02391108
--------------------------
Average time for N = 256000: 0.04792741
--------------------------
Average time for N = 512000: 0.10274111000000001
--------------------------
Average time for N = 1024000: 0.22128755000000006
*
*Using treeSet for the ascending order data is much faster than using BST
*Balancing a tree at build time is insufficient, as sequences of
*operations can eventually transform that carefully balanced tree into the dreaded list
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<>();

        //test 1
        System.out.println("Test 1: empty tree created. test size");
        showTestResults( t.size() == 0, 1 );

        //test 2
        t.add("FPXis the Champion!");
        System.out.println("Test 2: size is now 1");
        showTestResults( t.size() == 1, 2 );

        //test3
        t = new BinarySearchTree<>();
        t.add("g2");
        t.add("and not quite respectful");
        System.out.println("Test 3: empty tree created. add 2 values");
        showTestResults( t.size() == 2, 3 );
        t.add("abs");
        //test 4
        System.out.println("Test 4: added \"abs\" to the" +
        "tree. Size must be 3.");
        showTestResults( t.size() == 3, 4 );

        boolean boolResult = t.remove("abs");
        //test 5
        System.out.println("Test 5: Remove an existing value");
        showTestResults( boolResult == true, 5 );

        //test 6
        boolResult = t.remove("RNG");
        System.out.println("Test 6: Remove an non-existing value");
        showTestResults(!boolResult, 6 );

        //test 7
        System.out.println("Test 7: Test the size after 1 valid removement");
        showTestResults( t.size() == 2, 7);

        //test 8
        System.out.println("Test 8: Test isPresent");
        showTestResults(!t.isPresent("RNG"), 8);

        //test 9
        System.out.println("Test 9: Test isPresent");
        showTestResults(t.isPresent("g2"), 9);

        System.out.println("Test 9.1: Test isPresent");
        showTestResults(!t.isPresent("www"), 9);

        t.add("www");
        System.out.println("Test 9.2: Test isPresent");
        showTestResults(t.isPresent("www"), 9);
        t.remove("www");

        //test 10
        t.remove("g2");
        System.out.println("Test 10: Test the size after 2 valid removements");
        showTestResults( t.size() == 1, 10);

        //test 11
        System.out.println("Test 11: height of tree with 1" +
        "element must be 0.");
        showTestResults( t.height() == 0, 11 );

        //test 12
        t.add("GG,EU");
        t.add("FPXOP");
        System.out.println("Test 12: height of tree with 3" +
                "element must be 2.");
        showTestResults( t.height() == 2, 12 );

        //test 13
        List<String> getAllResult = t.getAll();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("FPXOP");
        expected.add("GG,EU");
        expected.add("and not quite respectful");
        System.out.println("Test 13: getAll method");
        showTestResults( getAllResult.equals(expected), 13 );

        //test 14
        t = new BinarySearchTree<>();
        getAllResult = t.getAll();
        expected = new ArrayList<>();
        System.out.println("Test 14: testing getAll with new empty tree");
        showTestResults( getAllResult.equals(expected), 14 );

        //test 15
        System.out.println("Test 15: max method");
        t.add("yaoshui");
        t.add("sunxiaochuan258");
        t.add("zhoushuyi");
        showTestResults( t.max().equals("zhoushuyi"), 15 );


        //test 16
        t.add("zzoushuyi");
        System.out.println("Test 16: adding another string which is bigger than original one");

        showTestResults( t.max().equals("zzoushuyi"), 16 );

        //test 17
        System.out.println("Test 17: min method");
        showTestResults( t.min().equals("sunxiaochuan258"), 17 );

        //test 18
        t.remove("sunxiaochuan258");
        System.out.println("Test 18: min method, removing the original min");
        showTestResults( t.min().equals("yaoshui"), 18 );

        //test 19
        t.iterativeAdd("ikun");
        System.out.println("Test 19: iterativeAdd");
        showTestResults( t.min().equals("ikun"), 19 );

        t.iterativeAdd("ikunn");
        System.out.println("Test 20: iterativeAdd");
        showTestResults( t.size() == 5, 20 );

        t.iterativeAdd("jinitaimei");
        System.out.println("Test 21: iterativeAdd");
        showTestResults( t.isPresent("jinitaimei"), 21 );

        System.out.println("Test 22: get kth");
        showTestResults( t.get(0).equals("ikun"), 22 );

        System.out.println("Test 23: get kth");
        showTestResults( t.get(3).equals("yaoshui"), 23 );

        expected = new ArrayList<>();
        expected.add("ikun");
        expected.add("ikunn");
        List<String> getAllLessThan = t.getAllLessThan("jinitaimei");
        System.out.println("Test 24: getAllLessThan");
        showTestResults(expected.equals(getAllLessThan), 24 );

        expected = new ArrayList<>();
        expected.add("ikun");
        expected.add("ikunn");
        expected.add("jinitaimei");
        getAllLessThan = t.getAllLessThan("llllll");
        System.out.println("Test 25: getAllLessThan");
        showTestResults(expected.equals(getAllLessThan), 25 );

        List<String> getAllGreaterThan = t.getAllGreaterThan("ccc");
        expected.add("yaoshui");
        expected.add("zhoushuyi");
        expected.add("zzoushuyi");
        System.out.println("Test 26: getAllGreaterThan");
        showTestResults( expected.equals(getAllGreaterThan), 26 );

        getAllGreaterThan = t.getAllGreaterThan("zzzzzzzzzzzzz");
        expected = new ArrayList<>();
        System.out.println("Test 27: getAllGreaterThan");
        showTestResults( expected.equals(getAllGreaterThan), 27 );

        t = new BinarySearchTree<>();
        t.add("bbbb");
        t.add("cccc");
        t.add("gggg");
        t.add("aaaa");
        t.add("aaab");
        t.add("bbcc");
        t.add("qqqq");
        t.add("bbbb");
        System.out.println("Test 28: numNodesAtDepth");
        showTestResults( t.numNodesAtDepth(2) == 3, 28 );
        System.out.println("Test 29: numNodesAtDepth");
        showTestResults( t.numNodesAtDepth(3) == 1, 29 );

        // experiments
        /*for (int i = 0; i <= 10; i++) {
            timeBSTExperiments(1000*((int)Math.pow(2,i)));
        }

        for (int i = 0; i <= 10; i++) {
            timeTreeSetExperiments(1000*((int)Math.pow(2,i)));
        }

        for (int i = 0; i <= 6; i++) {
            timeBSTExperimentsInOrder(1000*((int)Math.pow(2,i)));
        } */

        for (int i = 0; i <= 10; i++) {
            timeTreeSetExperimentsInOrder(1000*((int)Math.pow(2,i)));
        }

    }
    private static void timeBSTExperiments(int n) {
        double timeSum = 0.00;
        int heightSum = 0;
        int sizeSum = 0;
        for (int j = 0; j < 10; j++) {
            //System.out.println("N: " + n);
            //System.out.println("Test number: " + j);
            Stopwatch s = new Stopwatch();
            s.start();
            Random r = new Random();
            BinarySearchTree b = new BinarySearchTree();
            for (int i = 0; i < n; i++) {
                b.add(new Integer(r.nextInt()));
            }
            s.stop();
            //System.out.println(s.toString());
            timeSum += s.time();
            int currentHeight = b.height();
            //System.out.println(currentHeight);
            heightSum += currentHeight;
            int currentSize = b.size();
            //System.out.println(currentSize);
            sizeSum += currentSize;
        }
        System.out.println("--------------------------");
        System.out.print("Average time for N = " + n + ": ");
        System.out.println(timeSum / 10);
        System.out.print("Average height for N = " + n + ": ");
        System.out.println((double) heightSum / 10);
        System.out.print("Average size for N = " + n + ": ");
        System.out.println((double) sizeSum / 10);
    }

    private static void timeTreeSetExperiments(int n) {
        double timeSum = 0.00;
        int heightSum = 0;
        int sizeSum = 0;
        for (int j = 0; j < 10; j++) {
            //System.out.println("N: " + n);
            //System.out.println("Test number: " + j);
            Stopwatch s = new Stopwatch();
            s.start();
            Random r = new Random();
            TreeSet<Integer> b = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                b.add(r.nextInt());
            }
            s.stop();
            //System.out.println(s.toString());
            timeSum += s.time();
        }
        System.out.println("--------------------------");
        System.out.print("Average time for N = " + n + ": ");
        System.out.println(timeSum / 10);
    }

    private static void timeBSTExperimentsInOrder(int n) {
        double timeSum = 0.00;
        int heightSum = 0;
        int sizeSum = 0;
        for (int j = 0; j < 10; j++) {
            //System.out.println("N: " + n);
            //System.out.println("Test number: " + j);
            Stopwatch s = new Stopwatch();
            s.start();
            BinarySearchTree b = new BinarySearchTree();
            for (int i = 1; i <= n; i++) {
                b.iterativeAdd(i);
            }
            s.stop();
            //System.out.println(s.toString());
            timeSum += s.time();
            int currentHeight = b.height();
            //System.out.println(currentHeight);
            heightSum += currentHeight;
            int currentSize = b.size();
            //System.out.println(currentSize);
            sizeSum += currentSize;
        }

        System.out.println("--------------------------");
        System.out.print("Average time for N = " + n + ": ");
        System.out.println(timeSum / 10);
        System.out.print("Average height for N = " + n + ": ");
        System.out.println((double) heightSum / 10);
        System.out.print("Average size for N = " + n + ": ");
        System.out.println((double) sizeSum / 10);
    }

    private static void timeTreeSetExperimentsInOrder(int n) {
        double timeSum = 0.00;
        int heightSum = 0;
        int sizeSum = 0;
        for (int j = 0; j < 10; j++) {
            //System.out.println("N: " + n);
            //System.out.println("Test number: " + j);
            Stopwatch s = new Stopwatch();
            s.start();
            TreeSet<Integer> b = new TreeSet<>();
            for (int i = 1; i <= n; i++) {
                b.add(i);
            }
            s.stop();
            //System.out.println(s.toString());
            timeSum += s.time();
        }
        System.out.println("--------------------------");
        System.out.print("Average time for N = " + n + ": ");
        System.out.println(timeSum / 10);
    }

    private static void showTestResults(boolean passed, int testNum) {
        if( passed )
            System.out.println( "Test " + testNum + " passed.");
        else
            System.out.println( "TEST " + testNum + " FAILED.");
    }
}