import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, Diyuan Dai, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID:dd33653
*  email address:daidiyuan@126.com
*  Unique section number:50276
*  Number of slip days I am using:
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {
    // instance variable
    private int [][] data;
    private int nRows;
    private int nCols;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        if (mat == null|| mat.length <= 0|| mat[0].length <= 0) {  //check the pre conditions
            throw new IllegalArgumentException("Input matrix should be valid");
        }
        data = new int [mat.length][mat[0].length];  //build the matrix according to the proper size
        for(int y = 0; y < mat.length; y++){
            for(int x = 0; x < mat[0].length; x++){
                data[y][x]=mat[y][x];                //make deep copy
            }
        }
        nRows = data.length;
        nCols = data[0].length;                      //ensure the size of MathMatrix is the same with mat
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if (numRows <= 0|| numCols <= 0) {
            throw new IllegalArgumentException("Numbers of rows and cols should both be positive integer.");
        }

        data = new int [numRows][numCols];                    //build the matrix according to the proper size
        for(int y = 0; y < numRows; y++){
            for(int x = 0; x < numCols; x++){
                data[y][x]=initialVal;                        //assign the initial value to each cell
            }
        }
        nRows = data.length;
        nCols = data[0].length;

    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return nRows;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return nCols;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        if (row<0|| col<0|| row>getNumRows()|| col>getNumColumns()){
            throw new IllegalArgumentException("Numbers of rows and cols should both be positive integer and should not exceed the size.");
        }
        return data[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumRows()|| rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Numbers of rows and cols should both be positive integer and should not exceed the size.");
        }
        int[][] result = new int[getNumRows()][getNumColumns()];               //create a new int[][] to store the result of add according to size of MathMatrix
        for(int y = 0; y < getNumRows(); y++){
            for(int x = 0; x < getNumColumns(); x++){
                result[y][x]=data[y][x]+rightHandSide.getVal(y,x);             //for each cell in result, store the answer of adding two values in the same position in two MathMatrix
            }
        }
        return new MathMatrix(result);
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumRows()||rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Numbers of rows and cols should both be positive integer and should not exceed the size.");
        }
        int[][] result = new int[getNumRows()][getNumColumns()];                  //create a new int[][] to store the result of add according to size of MathMatrix
        for(int y = 0; y < getNumRows(); y++){
            for(int x = 0; x < getNumColumns(); x++){
                result[y][x]=data[y][x]-rightHandSide.getVal(y,x);                //for each cell in that int[][] , store the answer of minus result
            }
        }
        return new MathMatrix(result);
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of column    vffr in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("The number of rows of the second matrix should be equal to the columns number of the first one.");
        }
        int[][] result = new int[getNumRows()][rightHandSide.getNumColumns()];           //create a new int[][] with the size of current row number and the column number of the other MathMatrix
        for(int y = 0; y < result.length; y++){
            for(int x = 0; x < result[0].length; x++){                                   //for each cell in int[][] result
                int unitSum = 0;                                                         //create a new int to store sum of the multiplying of every 2 elements
                for (int multIndex = 0; multIndex < data[0].length; multIndex++) {
                    unitSum += data[y][multIndex] * rightHandSide.getVal(multIndex, x);  //sum of the multiplying of every 2 elements in that rows and columns accordingly
                }
                result[y][x]=unitSum;                                                    //assign the value to cells in int[][]result

            }
        }
        return new MathMatrix(result);
    } 


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        int[][] result = new int[getNumRows()][getNumColumns()];                       //create a new int[][] with the original size
        for(int y = 0; y < getNumRows(); y++){
            for(int x = 0; x < getNumColumns(); x++){
                result[y][x]= data[y][x]*factor;                                       //assign the scaled value to the corresponding position in the new int[][]
            }
        }
        return new MathMatrix(result);
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        int[][] result = new int[getNumColumns()][getNumRows()];                      //create a new int[][] with the original column number as new row number
        for(int y = 0; y < result.length; y++){                                       // and original row number as the new column number
            for(int x = 0; x < result[0].length; x++){
                result[y][x]= data[x][y];                                             // do the transpose, reverse the coordinate of the elements
            }
        }
        return new MathMatrix(result);
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         */
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix) rightHandSide;
            if( getNumRows() == otherMatrix.getNumRows() && getNumColumns() == otherMatrix.getNumColumns()){
                for(int y = 0; y < getNumRows(); y++){
                    for(int x = 0; x < getNumColumns(); x++){              // for every cells in the current MathMatrix
                        if(data[y][x]!=otherMatrix.getVal(y, x)){          // if the value is not equivalent
                            return false;                                  // stop comparing and just return false
                        }
                    }
                }
                return true;                                               // all the corresponding cells equal to each other so return true
            }
        }
        return false;                                                      // this means the object rightHandSide is not a non null MathMatrix just return false
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
        if (nRows == 0||nCols==0) {                                                                  // return an empty matrix since that is not a valid matrix
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int maxLen = getMaxLen();
        for(int y = 0; y < data.length; y++){                                                        //for each row
            sb.append("|");
            for (int x = 0; x< data[0].length; x++){                                                 //for the nth element in that row
                int numSpace = maxLen +1 - String.valueOf(data[y][x]).length();                      // the number of spaces before each number
                for (int i = 0; i < numSpace; i++){
                    sb.append(" ");                                                                  // append that amount of spaces
                }
                sb.append(data[y][x]);
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    private int getMaxLen() {      // to find the length of the longest element
        int max = 0;
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[0].length; x++){
                if ( max <String.valueOf(data[y][x]).length()){
                    max = String.valueOf(data[y][x]).length();         // count the digital number one by one and find the biggest
                }
            }
        }
        return max;
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular(){
        if (!rectangularMatrix(data)){                             //it mast be rectangular if it is possible for it to be upper triangular
           return false;
        }
        int i =0;
        for (int y = 1; y < data.length; y++){
            for (int x = 0; x <= i; x++){
                if (data[y][x]!=0){                                //check the "triangle" consisted of 0
                    return false;                                  //if there is one cell which should be a "0" and actually not a "0". Return false and save time
                }
            }
        }
        return true;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}