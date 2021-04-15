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


//imports

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     *   <br>pre: n != Integer.MIN_VALUE<br>
     *   <br>post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's. 
     *   Most significant digit is at position 0
     *   @param n the base 10 int to convert to base 2
     *   @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: getBinary. "
                    + "n cannot equal Integer.MIN_VALUE. n: " + n);
        }
        if (n == 1 || n == 0 || n == -1) {
            return Integer.toString(n);
        }
        //add the current string to the new calculated value
        return getBinary(n / 2) + Integer.toString(Math.abs(n % 2));
    }


    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        }
        if (stringToRev.length() == 1) {
            return stringToRev;
        }
        //length of the current string
        int length = stringToRev.length() - 1;
        return stringToRev.charAt(length) + revString(stringToRev.substring(0, length));
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by a value that
     * is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        }
        return isDoubleHelper(data, 0);
    }


    // CS314 students, add your nextIsDouble helper method here
    public static int isDoubleHelper(int[] data, int index) {
        int num = 0;
        // base case, we are at the end of the list.
        if (index == data.length - 1) {
            return 0;
        }
        // recursive cases
        if (data[index] * 2 == data[index + 1]) {
            // if is double, increase the count
            num = isDoubleHelper(data, index + 1) + 1;
        } else {
            // else, just do next
            num = isDoubleHelper(data, index + 1);
        }
        return num;
    }



    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, number.length() > 0, all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null ||  number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: listMnemonics");
        }

        ArrayList<String> result = new ArrayList<>();
        recursiveMnemonics(result, "", number);
        return result;
    }


    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
                    String mnemonicSoFar, String digitsLeft) {
        // base case
        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
            return;
        }
        // recursive case
        String digitsLeftNextTime = digitsLeft.substring(1);
        String tgtLetters = digitLetters(digitsLeft.charAt(0));
        for (int index = 0; index < tgtLetters.length(); index++) {
            // up date the next time's mnemonicSoFar by adding the letter
            String mnemonicSoFarNextTime = mnemonicSoFar + tgtLetters.charAt(index);
            // do recursive for every single letter
            recursiveMnemonics(mnemonics, mnemonicSoFarNextTime, digitsLeftNextTime);
        }
    }


    // used by method digitLetters
    private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return letters[index];
    }


    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is a digit ('0' through '9')
     * */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }


    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }


    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
        // base case
        if (size <= limit) {
            return;
        }
        // recursive base
        size /= 3;
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                double nextXCoord = x + xIndex * size;
                double nextYCoord = y + yIndex * size;
                // if it is the central part, just draw the big white square
                if (yIndex == 1 && xIndex == 1) {
                    g.fillRect((int) x + size, (int) y + size, size, size);
                } else {
                    // else, recurse
                    drawSquares(g, size, limit, nextXCoord, nextYCoord);
                }
            }
        }
    }


    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
        }
        // base case
        if (col == 0 || col == map[0].length - 1 || row == 0 || row == map.length - 1) {
            // nice, we are out
            return true;
        }
        // use such a list to update the current position
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] eachDir : directions) {
            int newRow = row + eachDir[0];
            int newCol = col + eachDir[1];
            // if it is movable in one direction, then move
            if (map[row][col] > map[newRow][newCol]) {
                //do recurse with the new position
                if (canFlowOffMap(map, newRow, newCol)) {
                    return true;
                }
            }
        }
        return false;
    }



    /* helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 stdents you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: inbounds. "
                            + "The 2d array mat may not be null and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }


    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        // check precondition
        if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
            throw new IllegalArgumentException("Violation of precondition: minDifference.");
        }
        int[] teamScore = new int[numTeams];
        return minDifferenceHelper(teamScore, abilities, 0);
    }

    private static int minDifferenceHelper(int[] teamScore, int[] abilities, int index) {
        // base case: we get last one
        if (index == abilities.length) {
            // check if each team has at least one person.
            if (!atLeastOneMember(teamScore)) {
                // if there is one empty team, invalidate the result
                // by setting it to max_value and will be substituted by next valid minDif
                return Integer.MAX_VALUE;
            } else {
                // valid case
                return getMinDiff(teamScore);
            }
        } else {
            // recursive case: back track
            // set max_value as the beginning minDif
            int minDif = Integer.MAX_VALUE;
            // for a valid combination , each team should at least one ability, so the indexOfTeam can't
            // go beyond index of the ability, set such a boundary can save time
            for (int indexOfTeam = 0; indexOfTeam < teamScore.length && indexOfTeam <= index; indexOfTeam++) {
                // add total
                teamScore[indexOfTeam] += abilities[index];
                // try next score
                int temp = minDifferenceHelper(teamScore, abilities, index + 1);
                // back track
                // undo the adding
                teamScore[indexOfTeam] -= abilities[index];
                // update minDif
                if (temp < minDif) {
                    minDif = temp;
                }
            }
            return minDif;
        }
    }

    /**
     * get the minimum Difference between teams
     * @param teamScore
     * @return the min dif between teams for comparison
     */
    private static int getMinDiff(int[] teamScore) {
        int max = teamScore[0];
        int min = teamScore[0];
        // get every element to analyze
        for (int index = 0; index < teamScore.length; index++) {
            int score = teamScore[index];
            // check if it's a max or a min
            if (score > max) {
                max = score;
            } else if (score < min) {
                min = score;
            }
        }
        return max - min;
    }

    /**
     * check if each team has at least one member.
     * @param teamScore
     * @return true if each team has at least one person.
     * False if there are empty team.
     */
    private static boolean atLeastOneMember(int[] teamScore) {
        for (int totalScore : teamScore) {
            // check if number of people is equal to 0
            if (totalScore == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * @param rawMaze represents the maze we want to escape. 
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {
        if (rawMaze == null) {
            throw new IllegalArgumentException();
        }
        int startRow = -1;
        int startCol = -1;
        boolean exit = false;
        // check pre-cons
        for (int row = 0; row < rawMaze.length; row++) {
            for (int col = 0; col < rawMaze[0].length; col++) {
                if (rawMaze[row][col] == 'E') {
                    exit = true;
                }
                if (rawMaze[row][col] == 'S') {
                    if (startRow != -1) {
                        throw new IllegalArgumentException("There should be only one start point");
                    }
                    startRow = row;
                    startCol = col;
                }
                if ("SE$GY*".indexOf(rawMaze[row][col]) == -1) {
                    throw new IllegalArgumentException("something else exist in maze");
                }
            }
        }
        if (startRow == -1) {
            throw new IllegalArgumentException("No Start Point");
        }
        if (!exit) {
            return 0;
        }
        int coinNum = 0;
        // do the hard copy to get avoid of altering original maze
        char[][] rawMazeCopy = new char[rawMaze.length][rawMaze[0].length];
        for (int row = 0; row < rawMaze.length; row++) {
            for (int col = 0; col < rawMaze[0].length; col++) {
                rawMazeCopy[row][col] = rawMaze[row][col];
                // also count the number of coins
                if (rawMaze[row][col] == '$') {
                    coinNum++;
                }
            }
        }
        return canEnter(rawMazeCopy, startRow, startCol, 0, coinNum);
    }

    //check if we can enter a cell
    public static int canEnter(char[][] rawMaze, int startRow, int startCol, int found, int coinNum) {
        if (rawMaze[startRow][startCol] == 'E') {
            if (coinNum == 0) {
                return 2;
            }
            return 1;
        }
        //record the original cell state for possible backtrack
        char originalState = changeCellState(rawMaze, startRow, startCol);
        //we found a coin
        if (originalState == '$') {
            coinNum--;
        }
        // create a list of all directions
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] eachDir : directions) {
            int newRow = startRow + eachDir[0];
            int newCol = startCol + eachDir[1];
            // if it is movable in one direction, then move
            // firstly, new position can not go out of bounds
            if (newCol >= 0 && newCol < rawMaze[0].length && newRow < rawMaze.length && newRow >= 0) {
                // secondly, that direction can not be a '*'.
                if (rawMaze[newRow][newCol] != '*') {
                    //do recurse with the new position
                    found = canEnter(rawMaze, newRow, newCol, found, coinNum);
                    // if found is 2, return found to last recurse process
                    if (found == 2) {
                        return found;
                    }
                    // continue if found is not 2
                }
            }
        }
        // back track, undo the change
        rawMaze[startRow][startCol] = originalState;
        return found;
    }

    /**
     *  a helper method to change the cell state and return the original state;
     * <br>pre: none (because we already do pre con check in big method.
     * <br>post: return the original state of a cell
     * @param rawMaze represents the maze we want to escape.
     * rawMaze is altered as a result of this method.
     */

    private static char changeCellState(char[][] rawMaze, int startRow, int startCol) {
        if (rawMaze[startRow][startCol] == 'Y') {
            rawMaze[startRow][startCol] = '*';
            return 'Y';
        } else if (rawMaze[startRow][startCol] == 'G') {
            rawMaze[startRow][startCol] = 'Y';
            return 'G';
        } else if (rawMaze[startRow][startCol] == 'S') {
            rawMaze[startRow][startCol] = 'G';
            return 'S';
        } else {
            rawMaze[startRow][startCol] = 'Y';
            return '$';
        }
    }
}