import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:
 *  Number of slip days I am using:
 */



/* CS314 Students. Put your experiment results and
 * answers to questions here.
 */
 /*Experiment1:
| Size of the Matrix | Time used to complete 1000 times addition |
| 1000*1000          | 3.4951599 seconds                         |
| 2000*2000          | 12.369784 seconds                         |
| 4000*4000          | 50.402047 seconds                         |

   Experiment2:
| Size of the Matrix | Time used to complete 100 times multiplication |
| 200*200            | 1.0314678 seconds                              |
| 400*400            | 10.135464 seconds                              |
| 800*800            | 112.76483 seconds                              |


Answers to questions
    1.  About 210 seconds
    2.  O(N^2). Because my add operation need to do 2 for loops and each "for loop" will be O(N).
        My data support this because each time I double the size of N, 4 times original time spent and 4 is square of 2.
    3.  About 1000 seconds
    4.  O(N^3)  Because my multiple operation uses 3 "for loops".
        I believe my data support that. However, it looks like that when I double N, the time will be 10 times bigger.
    5.  I tried about 20 times using concept of binary searching and found that the biggest matrix is a 16204*16204 matrix.
        So the default memory capacity is about 1.05*10^9 bytes as calculated.

  */


/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { {5, 2, 3},
                {2, 3, 4}};
        int[][] data2 = { {4, 2, 2},
                {2, 3, 1}};
        int[][] e1;

        //test 001, specify size and values constructor
        MathMatrix mat1 = new MathMatrix(3, 3, 3);
        e1 = new int[][] {{3, 3, 3},{3, 3, 3},{3, 3, 3}};
        printTestResult( get2DArray(mat1), e1, 1, "Constructor with size and initial val specified.");

        //test 002, specify size and values constructor
        MathMatrix mat2 = new MathMatrix(4, 4, 4);
        e1 = new int[][] {{4, 4, 4, 4},{4, 4, 4, 4},{4, 4, 4, 4},{4, 4, 4, 4}};
        printTestResult( get2DArray(mat2), e1, 2, "Constructor with size and initial val specified.");

        //tests 003 int[][] constructor, deep copy
        mat1 = new MathMatrix( data1 );
        data1[0][0] = 10;
        // alter data1. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {10, 2, 3}, {2, 3, 4} };
        printTestResult( data1, e1, 3, "constructor with one parameter of type int[][]");
        //tests 003 int[][] constructor, deep copy
        // data1 altered. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {5, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 3, "constructor with one parameter of type int[][]. Testing deep copy made.");

        //tests 004 int[][] constructor, deep copy
        mat1 = new MathMatrix( data1 );
        data1[1][1] = 5;
        // alter data1. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {10, 2, 3}, {2, 5, 4} };
        printTestResult( data1, e1, 4, "constructor with one parameter of type int[][]");
        //tests 004 int[][] constructor, deep copy
        // data1 altered. mat1 should be unchanged if deep copy made
        e1 = new int[][] { {10, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 4, "constructor with one parameter of type int[][]. Testing deep copy made.");

        //tests 005, addition
        data1 =new int[][]{ {5, 2, 3}, {2, 3, 4} };
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { {5, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 5, "add method. Testing mat1 unchanged.");
        e1 = new int[][] { {4, 2, 2}, {2, 3, 1} };
        printTestResult( get2DArray(mat2), e1, 5, "add method. Testing mat2 unchanged.");
        e1 = new int[][] { {9, 4, 5}, {4, 6, 5} };
        printTestResult( get2DArray(mat3), e1, 5, "add method. Testing mat3 correct result.");

        //tests 006, addition
        data1[1][1] = 2;
        data2[0][0] = 5;
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.add(mat2);
        e1 = new int[][] { {5, 2, 3}, {2, 2, 4} };
        printTestResult( get2DArray(mat1), e1, 6, "add method. Testing new mat1 unchanged.");
        e1 = new int[][] { {5, 2, 2}, {2, 3, 1} };
        printTestResult( get2DArray(mat2), e1, 6, "add method. Testing new mat2 unchanged.");
        e1 = new int[][] { {10, 4, 5}, {4, 5, 5} };
        printTestResult( get2DArray(mat3), e1, 6, "add method. Testing new mat3 correct result.");

        //tests 007, subtraction
        data1 =new int[][]{ {5, 2, 3}, {2, 3, 4} };
        data2 = new int[][]{ {4, 2, 2}, {2, 3, 1}};
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.subtract(mat2);
        e1 = new int[][] { {5, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 7, "subtract method. Testing mat1 unchanged.");
        e1 = new int[][] { {4, 2, 2}, {2, 3, 1} };
        printTestResult( get2DArray(mat2), e1, 7, "subtract method. Testing mat2 unchanged.");
        e1 = new int[][] { {1, 0, 1}, {0, 0, 3} };
        printTestResult( get2DArray(mat3), e1, 7, "subtract method. Testing mat3 correct result.");

        //tests 008, subtraction
        data1[1][1] = 2;
        data2[0][0] = 5;
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.subtract(mat2);
        e1 = new int[][] { {5, 2, 3}, {2, 2, 4} };
        printTestResult( get2DArray(mat1), e1, 8, "subtract method. Testing mat1 unchanged.");
        e1 = new int[][] { {5, 2, 2}, {2, 3, 1} };
        printTestResult( get2DArray(mat2), e1, 8, "subtract method. Testing mat2 unchanged.");
        e1 = new int[][] { {0, 0, 1}, {0, -1, 3} };
        printTestResult( get2DArray(mat3), e1, 8, "subtract method. Testing mat3 correct result.");


        //test 009, multiplication
        data1 =new int[][]{ {5, 2, 3}, {2, 3, 4} };
        data2 = new int[][] { {1, 3}, {5, 1}, {3, 1} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {11, 11, 15}, {27, 13, 19}, {17, 9, 13} };
        printTestResult( get2DArray(mat3), e1, 9, "multiply method");

        //test 010, multiplication
        data2 = new int[][] { {3, 3}, {5, 2}, {3, 0} };
        mat2 = new MathMatrix(data2);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {21, 15, 21}, {29, 16, 23}, {15, 6, 9} };
        printTestResult( get2DArray(mat3), e1, 10, "multiply method");

        //tests 011 getScaledMatrix
        data2 = new int[][] { {1, 3}, {5, 1}, {3, 1} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat1.getScaledMatrix(3);
        e1 = new int[][] { {5, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 11, "getScaledMatrix method. Testing mat1 unchanged.");
        e1 = new int[][] { {15, 6, 9}, {6, 9, 12} };
        printTestResult( get2DArray(mat3), e1, 11, "getScaledMatrix method. Testing mat3 correct result.");

        //tests 012 getScaledMatrix
        mat3 = mat2.getScaledMatrix(0);
        e1 = new int[][]{ {1, 3}, {5, 1}, {3, 1} };
        printTestResult( get2DArray(mat2), e1, 12, "getScaledMatrix method. Testing mat2 unchanged.");
        e1 = new int[][] { {0, 0}, {0, 0}, {0, 0} };
        printTestResult( get2DArray(mat3), e1, 12, "getScaledMatrix method. Testing mat3 correct result.");


        //tests 013 getTranspose
        mat3 = mat1.getTranspose();
        e1 = new int[][] { {5, 2, 3}, {2, 3, 4} };
        printTestResult( get2DArray(mat1), e1, 13, "getTranspose method. Testing mat1 unchanged.");
        e1 = new int[][] { {5, 2}, {2, 3}, {3, 4}};
        printTestResult( get2DArray(mat3), e1, 13, "getTranspose method. Testing mat3 correct result.");


        //tests 014 getTranspose
        data2 =new int [][] { {5, 2, 2}, {2, 3, 1} };
        mat2 = new MathMatrix(data2);
        mat3 = mat2.getTranspose();
        e1 = new int[][]{ {5, 2, 2}, {2, 3, 1} };
        printTestResult( get2DArray(mat2), e1, 14, "getTranspose method. Testing mat2 unchanged.");
        e1 = new int[][] { {5, 2}, {2, 3}, {2, 1}};
        printTestResult( get2DArray(mat3), e1, 14, "getTranspose method. Testing mat3 correct result.");

        //test 015, toString()
        data1 = new int[][] {{-111110, 100, 101, -1000},
            {10000, 10, 55, 4},
            {1, -1, 4, 0}};
            mat1 = new MathMatrix(data1);
            String expected = "| -111110     100     101   -1000|\n|   10000      10      55       4|\n|       1      -1       4       0|\n";
            if (mat1.toString().equals( expected )) {
                System.out.println("passed test 15,toString method.");
            } else {
                System.out.println("failed test 15,toString method.");
            }
        //test 016, toString()
        data1 = new int[][] {{-1, 1, 1, -1},
                {1, 1, 5, 4},
                {1, -1, 4, 0}};
        mat1 = new MathMatrix(data1);
        expected = "| -1  1  1 -1|\n|  1  1  5  4|\n|  1 -1  4  0|\n";
        if (mat1.toString().equals( expected )) {
            System.out.println("passed test 16,toString method.");
        } else {
            System.out.println("failed test 16,toString method.");
        }

            //test 017, upperTriangular
            data1 = new int[][] {{3, 2, 3, 0, 0}, {0, 2, 5, 3, 4}, {0, 0, 1, -1, -5}, {0, 0, 0, 12, 7}, {0, 0, 0, 0, 1}};
            mat1 = new MathMatrix(data1);
            if (mat1.isUpperTriangular()) {
                System.out.println("Passed test 17,upperTriangular method.");
            } else {
                System.out.println("Failed test 17,upperTriangular method.");
            }

            //test 018, upperTriangular
            data1 = new int[][] {{3, 2, 3, 0}, {0, 4, 2, 3}, {7, 0, 4, -1}, {0, 0, 0, 0}};
            mat1 = new MathMatrix(data1);
            if (!mat1.isUpperTriangular()) {
                System.out.println("Passed test 18,upperTriangular method.");
            } else {
                System.out.println("Failed test 18,upperTriangular method.");
            }


        //tests 019 getNumRows
        if (mat1.getNumRows()==4){
            System.out.println("Passed test 19,getNumRows method.");
        } else {
            System.out.println("Failed test 19,getNumRows method.");
        }

        //tests 020 getNumColumns
        if (mat1.getNumColumns()==4){
            System.out.println("Passed test 20,getNumColumns method.");
        } else {
            System.out.println("Failed test 20,getNumColumns method.");
        }

        //tests 021 getNumRows
        mat2 = new MathMatrix(4, 4, 4);
        if (mat2.getNumRows()==4){
            System.out.println("Passed test 21,getNumRows method.");
        } else {
            System.out.println("Failed test 21,getNumRows method.");
        }

        //tests 022 getNumColumns
        mat2 = new MathMatrix(4, 4, 4);
        if (mat2.getNumColumns()==4){
            System.out.println("Passed test 22,getNumColumns method.");
        } else {
            System.out.println("Failed test 22,getNumColumns method.");
        }

        //tests 023 equals
        e1 = new int[][] {{4, 4, 4, 4},{4, 4, 4, 4},{4, 4, 4, 4},{4, 4, 4, 4}};
        MathMatrix A = new MathMatrix(e1);
        if(mat2.equals(A)){
            System.out.println("Passed test 23,equals method.");
        } else {
        System.out.println("Failed test 23,equals method.");
        }

        //tests 024 equals
        e1 = new int[][]  { {5, 2, 3}, {2, 3, 4} };
        MathMatrix B = new MathMatrix(e1);
        if(!mat1.equals(A)){
            System.out.println("Passed test 24,equals method.");
        } else {
            System.out.println("Failed test 24,equals method.");
        }

        Random r = new Random();
        //Experiment 1
       /* Stopwatch time1 = new Stopwatch();
        Stopwatch time2 = new Stopwatch();
        Stopwatch time3 = new Stopwatch();


        MathMatrix matA = createMat(r,1000,1000,20);
        MathMatrix matB = createMat(r,1000,1000,20);
        time1.start();
        for(int i = 0; i < 1000; i++){
            matA.add(matB);
        }
        time1.stop();
        System.out.println(time1.toString());

        MathMatrix matC = createMat(r,2000,2000,20);
        MathMatrix matD = createMat(r,2000,2000,20);
        time2.start();
        for(int i = 0; i < 1000; i++){
            matC.add(matD);
        }
        time2.stop();
        System.out.println(time2.toString());

        MathMatrix matE = createMat(r,4000,4000,20);
        MathMatrix matF = createMat(r,4000,4000,20);
        time3.start();
        for(int i = 0; i < 1000; i++){
            matE.add(matF);
        }
        time3.stop();
        System.out.println(time3.toString());

       //Experiment 2
        /*Stopwatch time1 = new Stopwatch();
        Stopwatch time2 = new Stopwatch();
        Stopwatch time3 = new Stopwatch();

        Random r = new Random();
        MathMatrix matA = createMat(r,200,200,20);
        MathMatrix matB = createMat(r,200,200,20);
        time1.start();
        for(int i = 0; i < 100; i++){
            matA.multiply(matB);
        }
        time1.stop();
        System.out.println(time1.toString());
        MathMatrix matC = createMat(r,400,400,20);
        MathMatrix matD = createMat(r,400,400,20);
        time2.start();
        for(int i = 0; i < 100; i++){
            matC.multiply(matD);
        }
        time2.stop();
        System.out.println(time2.toString());
        MathMatrix matE = createMat(r,800,800,20);
        MathMatrix matF = createMat(r,800,800,20);
        time3.start();
        for(int i = 0; i < 100; i++){
            matE.multiply(matF);
        }
        time3.stop();
        System.out.println(time3.toString());*/

        // to create a really big matrix
        // MathMatrix matG = createMat(r,16204,16204,20);
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        } 
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }


    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if(rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "****failed****";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with 
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        if  ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for(int r = 0; r < result.length; r++) {
            for(int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        //check precondition
        if((data1 == null) || (data1.length == 0) 
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                ||  (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null" 
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }


}

