
/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Diyuan Dai, this programming assignment is my own work
 * and I have not provided this code
 * to any other student. 
 * UTEID: dd33653
 * email address: daidiyuan@126.com
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurfer {

    public static final String NAME_FILE = "C:\\Users\\beast\\IdeaProjects\\ass3\\src\\names2.txt";

    // constants for menu choices
    public static final int SEARCH = 1;
    public static final int ONE_NAME = 2;
    public static final int APPEAR_ONCE = 3;
    public static final int APPEAR_ALWAYS = 4;
    public static final int MORE_POPULAR = 5;
    public static final int LESS_POPULAR = 6;
    public static final int UP_DOWN_RANK = 7;
    public static final int QUIT = 8;

    // CS314 students, explain your menu option 7 here:
    /**
     * this method will return an ArrayList contains NameRecords that have up-down ranks in the limited decade
     * the ranklist meet the conditions will go up and down alternatively for consecutive decades
     * for another word, they are getting more popular every other decades.
     * just like 0 900 0 100 250 200 600 300 0 = true
     * 1 0 1 0 1 0 1 0 1 = true
     * 899 300 554 21 0 777 999 234 0 255 = true
     *  899 300 554 21 0 777 999 234 255 0 = false because 234 to 255 is a increment
     *  and from 255 to 0 is also a rank number increment since 0 is some value bigger than 1000
     *
     * The condition checking method is wrote in NameRecord class
     * two consecutive "0" will not be necessarily meet the requirement so will not be returned in the ArrayList
     * 899 300 554 21 0 777 999 234 0 0 = false
     *
     * This is pretty uncommon to happen so that no name in names.txt meet the requirement
     * However, "Issac" in names2.txt meet the requirement
     *
     */

    // CS314 students, Explain your interesting search / trend here:
    // I am a foreigner and my English name is Gary. Both of my dad of mom are businessmen doing international trade so they both have English Names.
    // My dad's name is David, my mom name is Sonya.
    // All of these 3 names are pretty common to see in the last century.
    // David and Gary always appear in the top 1000 while Sonya appears 8 times.
    // My other international friends' names are like Mike, Alex, Caron, Derek
    // They are all super popular names
    // We foreigners are not good at giving themselves some rare English names, do we?


    // CS314 students, add test code for NameRecord class here:

    public static void newTest() {
        // raw data for Emmitt.
        String emmittRawData = "Emmitt 602 676 639 935 869 0 0 0 0";
        Names.baseDecade= 1910;
        NameRecord emmittNR = new NameRecord(emmittRawData) ;
                 String expected = "Emmitt\n1910: 602\n1920: 676\n1930: 639\n1940: 935\n1950: "
                        + "869\n1960: 0\n1970: 0\n1980: 0\n1990: 0\n";
        String actual = emmittNR.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual)) {
            System.out.println("passed Jake toString test.");
        } else {
            System.out.println("FAILED Jake toString test."); 
        }

        // Some getName Tests

        Names names = new Names(getFileScannerForNames(NAME_FILE));
        String[] testNames = {"Nakia", "nakia", "wdwdw", "77568", "MIKESCOTT", "Gil", "Jeffrey"};
        boolean[] expectNull = {false, false, true, true, true, false, false};
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }

        // getBaseDecade Tests
        int actualParameter1 = names.getName("Nakia").getBaseDecade();
        int actualParameter2 = names.getName("Gil").getBaseDecade();
        checkInt("getBaseDecade", actualParameter1, actualParameter2, 1910, 1910);

        // getBestDecade Tests
        actualParameter1 = names.getName("Nakia").getBestDecade();
        actualParameter2 = names.getName("Gil").getBestDecade();
        checkInt("getBestDecade", actualParameter1, actualParameter2, 1970, 1960);


        // getRank Tests
        actualParameter1 = names.getName("Nakia").getRank(2);
        actualParameter2 = names.getName("Gil").getRank(4);
        checkInt("getRank", actualParameter1, actualParameter2, 0, 797);

        // getRank Tests
        actualParameter1 = names.getName("Nakia").getRankedTime();
        actualParameter2 = names.getName("Gil").getRankedTime();
        checkInt("getRankedTime", actualParameter1, actualParameter2, 2, 2);

        // alwaysPopular Tests
        boolean actualBoolean1 = names.getName("Nakia").alwaysPopular();
        boolean actualBoolean2 = names.getName("Martha").alwaysPopular();
        checkBoolean("alwaysPopular", actualBoolean1, actualBoolean2, false, true);

        // onlyOncePopular Tests
        actualBoolean1 = names.getName("Nakia").onlyOncePopular();
        actualBoolean2 = names.getName("Raekwon").onlyOncePopular();
        checkBoolean(" onlyOncePopular", actualBoolean1, actualBoolean2, false, true);

        // morePopular Tests
        actualBoolean1 = new NameRecord("TonyStark 0 999 998 700 499 333 222 3 1").morePopular();
        actualBoolean2 = new NameRecord("PeterPark 0 990 955 455 334 222 3 3 1").morePopular();
        checkBoolean(" onlyOncePopular", actualBoolean1, actualBoolean2, true, false);

        // lessPopular Tests
        actualBoolean1 = new NameRecord("TonyStark 0 0 323 444 499 983 992 992 992").lessPopular();
        actualBoolean2 = new NameRecord("PeterPark 1 2 3 4 5 6 7 8 9").lessPopular();
        checkBoolean(" onlyOncePopular", actualBoolean1, actualBoolean2, false, true);

        // Some getRankData Tests
        ArrayList<Integer> actualList1 = new NameRecord("TonyStark 0 0 323 444 499 983 992 992 992").getRankData();
        ArrayList<Integer> actualList2 = new NameRecord("PeterPark 1 2 3 4 5 6 7 8 9").getRankData();
        ArrayList<Integer> actualList3 = new NameRecord("Thor 0 0 323 444 499 983 992 992 992").getRankData();
        System.out.println("The raw data is like this");
        System.out.println("TonyStark 0 0 323 444 499 983 992 992 992");
        System.out.println("PeterPark 1 2 3 4 5 6 7 8 9");
        System.out.println("Thor 0 0 323 444 499 983 992 992 992");
        System.out.println("This data firstly compare the Arraylist produced by TonyStark to that produced by PeterPark");
        System.out.println("then it compare the Arraylist produced by TonyStark to that produced by Thor");

        actualBoolean1 = actualList1.equals(actualList2);
        actualBoolean2 = actualList1.equals(actualList3);
        checkBoolean("getRankData", actualBoolean1, actualBoolean2, false, true);

        // rankUpDown Tests
        actualBoolean1 = new NameRecord("Nedra 753 0 936 937 920 0 22 0 1").rankUpDown();
        actualBoolean2 = new NameRecord("Abel 664 613 626 575 542 491 497 422 381 385 354").rankUpDown();
        checkBoolean(" onlyOncePopular", actualBoolean1, actualBoolean2, true, false);

    }

    //helper method to do the test for integer answer checking
    private static void checkInt(String testWhat, int actual1, int actual2, int expected1, int expected2){
        System.out.println();
        System.out.println(testWhat + " tests:");
        System.out.println();
        System.out.println("Actual result1: " + actual1);
        System.out.println("Expected result1: " + expected1);
        System.out.println("Actual result2: " + actual2);
        System.out.println("Expected result2: " + expected2);
        System.out.println();
        if (actual1 != expected1 || actual2 != expected2){
            System.out.println(testWhat+"XXXXXXXX tests failed XXXXXXXX");
        }
        else {
            System.out.println(testWhat+" tests passed");
        }
        System.out.println();
    }

    //helper method to do the test for boolean answers checking
    private static void checkBoolean(String testWhat, boolean actual1, boolean actual2, boolean expected1, boolean expected2){
        System.out.println();
        System.out.println(testWhat + " tests:");
        System.out.println();
        System.out.println("Actual result1: " + actual1);
        System.out.println("Expected result1: " + expected1);
        System.out.println("Actual result2: " + actual2);
        System.out.println("Expected result2: " + expected2);
        System.out.println();
        if (actual1 != expected1 || actual2 != expected2){
            System.out.println(testWhat+"XXXXXXXX tests failed XXXXXXXX");
        }
        else {
            System.out.println(testWhat+" tests passed");
        }
        System.out.println();
    }

    private static void performGetNameTest(Names names, String name, boolean expectNull) {
        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }


    // main method. Driver for the whole program
    public static void main(String[] args) {
        // delete the following line in the final version of your program.
        newTest();

        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    // pre: namesDatabase != null
    // ask user for options to perform on the given Names object.
    // Creates a Scanner connected to System.in.
    private static void runOptions(Names namesDatabase) {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            choice = getChoice(keyboard);
            if(choice == SEARCH) {
                search(namesDatabase, keyboard);
            } else if (choice == ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (choice == APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (choice == APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
                // CS314 students, complete this section
            } else if (choice == MORE_POPULAR) {
                setMorePopular(namesDatabase);
            } else if (choice == LESS_POPULAR) {
                setLessPopular(namesDatabase);
            } else if (choice == UP_DOWN_RANK) {
                setUpDownRank(namesDatabase);
            } else {
                System.out.println("\n\nGoodbye.");
            }
        } while(choice != QUIT);
        keyboard.close();
    }

    // pre: fileName != null
    // create a Scanner and return connected to a File with the given name.
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Problem reading the data file. Returning null for Scanner"
                    + "object. Problems likely to occur." + e);
        }
        return sc;
    }

    // method that shows names that have appeared in ever decade
    // pre: n != null
    // post: print out names that have appeared in ever decade
    private static void appearAlways(Names n) {
        if (n == null) {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        System.out.println(n.rankedEveryDecade().size()+" names appear in every decade. The names are:");
        for (int i = 0; i < n.rankedEveryDecade().size(); i ++){
            System.out.println(n.rankedEveryDecade().get(i));
        }
    }


    // method that shows names that have appeared in only one decade
    // pre: n != null
    // post: print out names that have appeared in only one decade
    private static void appearOnce(Names n) {
        if (n == null) {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        System.out.println(n.rankedOnlyOneDecade().size()+" names appear in exactly one decade. The names are:");
        for (int i = 0; i < n.rankedOnlyOneDecade().size(); i ++){
            System.out.println(n.rankedOnlyOneDecade().get(i));
        }
    }

    // method that shows data for one name, or states that name has never been
    // ranked
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the data for n or a message that n has never been in the
    // top 1000 for any decade
    private static void oneName(Names n, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (n == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a name: ");
        String inputName = keyboard.next();
        System.out.println();
        if(n.getName(inputName)!=null){
            System.out.println(n.getName(inputName ));
        }
        else{
            System.out.println(inputName+" does not appear in any decade.");
        }
    }

    // method that shows all names that contain a substring from the user
    // and the decade they were most popular in
    // pre: n != null, keyboard != null and is connected to System.in
    // post: show the correct data
    private static void search(Names n, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (n == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a partial name: ");
        String inputName = keyboard.next();
        ArrayList<NameRecord> nameMatchedList = n.getMatches(inputName);
        System.out.println("There are " + nameMatchedList.size() + " matches for " + inputName+ ".\n" );
        //only if we have some matches
        if(nameMatchedList.size() != 0){
            // we print highest ranking sentences
            System.out.println("The matches with their highest ranking decade are:");
            for (int i = 0; i <nameMatchedList.size(); i++){
                System.out.print(nameMatchedList.get(i).getDataName() + " ");
                System.out.println(nameMatchedList.get(i).getBestDecade());
            }
        }
    }

    // method that shows names that are more popular in every decade.
    // pre: n != null
    // post: print out names that are more popular in every decade.
    private static void setMorePopular(Names n){
        if (n == null) {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        System.out.println(n.alwaysMorePopular().size()+" names are getting more popular in every decade. The names are:");
        for (int i = 0; i < n.alwaysMorePopular().size(); i ++) {
            System.out.println(n.alwaysMorePopular().get(i));
        }
    }

    // method that shows names that are less popular in every decade.
    // pre: n != null
    // post: print out names that are less popular in every decade.
    private static void setLessPopular(Names n){
        if (n == null) {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        System.out.println(n.alwaysLessPopular().size()+" names are getting less popular in every decade. The names are:");
        for (int i = 0; i < n.alwaysLessPopular().size(); i ++) {
            System.out.println(n.alwaysLessPopular().get(i));
        }
    }

    // method that shows names that their ranks for each decade improve alternately.
    // pre: n != null
    // post: print out names that their ranks for each decade improve alternately.
    private static void setUpDownRank(Names n){
        if (n == null) {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        ArrayList<String> matchedList = n.alternativelyUpDown();
        if (matchedList.size() == 0){
            System.out.println("No names in data file meat the condition that their ranks for each decade improve every other decades");
        }
        else {
            System.out.println(n.alternativelyUpDown().size() + " names are getting more popular every other decades. The names are:");
            for (int i = 0; i < n.alternativelyUpDown().size(); i ++) {
                System.out.println(n.alternativelyUpDown().get(i));
            }
        }
    }


    // get choice from the user
    // keyboard != null and is connected to System.in
    // return an int that is >= SEARCH and <= QUIT
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        while (choice < SEARCH || choice > QUIT) {
            System.out.println("\n" + choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    // ensure an int is entered from the keyboard
    // pre: s != null and is connected to System.in
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // show the user the menu
    private static void showMenu() {
        System.out.println("\nOptions:");
        System.out.println("Enter " + SEARCH + " to search for names.");
        System.out.println("Enter " + ONE_NAME 
                + " to display data for one name.");
        System.out.println("Enter " + APPEAR_ONCE 
                + " to display all names that appear in only one decade.");
        System.out.println("Enter " + APPEAR_ALWAYS 
                + " to display all names that appear in all decades.");
        // CS314 students fill in other options
        System.out.println("Enter " + MORE_POPULAR
                + " to display all names that are more popular in every decade.");
        System.out.println("Enter " + LESS_POPULAR
                + " to display all names that are less popular in every decade.");
        System.out.println("Enter " + UP_DOWN_RANK
                + " to display all names that their ranks for each decade improve alternately.\n" +
                "The ranks of those names go up and down consecutively.");

        System.out.println("Enter " + QUIT + " to quit.\n");
    }

}
