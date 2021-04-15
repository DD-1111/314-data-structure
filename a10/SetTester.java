/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Diyuan Dai and Yinglei Fang), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:1
 *
 *  Student 1 (Student whose turn in account is being used) Diyuan Dai
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:Tony
 *  Section number:50276
 *
 *  Student 2
 *  UTEID:yf3675
 *  email address:1608412635@qq.com
 *  Grader name:Tony
 *  Section number:50276
 *
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*
CS 314 Students, put your results to the experiments and answers to
questions here:



Oom Gert Vertel en Ander Gedigte by C. Louis Leipoldt
151 kB





***** CS314 SortedSet: *****
Time to add the elements in the text to this set: elapsed time: 0.3380889 seconds.
Total number of words in text including duplicates: 24590
Number of distinct words in this text 6606
Enter y to see the contents of this set: y

****** CS314 UnsortedSet: *****
Time to add the elements in the text to this set: elapsed time: 0.8950388 seconds.
Total number of words in text including duplicates: 24590
Number of distinct words in this text 6606
Enter y to see the contents of this set:

***** Java HashSet ******
Time to add the elements in the text to this set: elapsed time: 0.0817328 seconds.
Total number of words in text including duplicates: 24590
Number of distinct words in this text 6606
Enter y to see the contents of this set:

***** Java TreeSet ******
Time to add the elements in the text to this set: elapsed time: 0.1160373 seconds.
Total number of words in text including duplicates: 24590
Number of distinct words in this text 6606
Enter y to see the contents of this set:




Bible Pictures and Stories in Large Print by Isabella M. Aldon
82kB


***** CS314 SortedSet: *****
Time to add the elements in the text to this set: elapsed time: 0.2198259 seconds.
Total number of words in text including duplicates: 14627
Number of distinct words in this text 3482
Enter y to see the contents of this set:

****** CS314 UnsortedSet: *****
Time to add the elements in the text to this set: elapsed time: 0.3715651 seconds.
Total number of words in text including duplicates: 14627
Number of distinct words in this text 3482
Enter y to see the contents of this set:

***** Java HashSet ******
Time to add the elements in the text to this set: elapsed time: 0.0283906 seconds.
Total number of words in text including duplicates: 14627
Number of distinct words in this text 3482
Enter y to see the contents of this set:

***** Java TreeSet ******
Time to add the elements in the text to this set: elapsed time: 0.0307521 seconds.
Total number of words in text including duplicates: 14627
Number of distinct words in this text 3482
Enter y to see the contents of this set:


Adam & Eve & Pinch Me by Alfred Edgar Coppard



***** CS314 SortedSet: *****
Time to add the elements in the text to this set: elapsed time: 1.2598615 seconds.
Total number of words in text including duplicates: 75089
Number of distinct words in this text 14272
Enter y to see the contents of this set:


****** CS314 UnsortedSet: *****
Time to add the elements in the text to this set: elapsed time: 3.2341824 seconds.
Total number of words in text including duplicates: 75089
Number of distinct words in this text 14272
Enter y to see the contents of this set:

***** Java HashSet ******
Time to add the elements in the text to this set: elapsed time: 0.1205409 seconds.
Total number of words in text including duplicates: 75089
Number of distinct words in this text 14272
Enter y to see the contents of this set:

***** Java TreeSet ******
Time to add the elements in the text to this set: elapsed time: 0.1725575 seconds.
Total number of words in text including duplicates: 75089
Number of distinct words in this text 14272
Enter y to see the contents of this set:

O(1) for HashSet add
O(logN) for TreeSet add
O(N) for SortedSet add
O(N) for UnsortedSet add
O(NlogM) for SortedSet processText
O(N * M) for UnsortedSet processText

A HashSet does not have a fixed order while a
TreeSet prints elements in ascending sorted order.


CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:
We can’t create sorted set or unsorted set object in abstract class,
 which means we have to have intersection, union, difference calling
 each other to create the returned object, thus implementing all of
 them will create an infinite loop.

*/


public class SetTester {

    public static void main(String[] args) {

        ISet<Integer> s1 = new SortedSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(5);
        s1.add(3);
        ISet<Integer> s2 = new SortedSet<>();
        s2.add(2);
        s2.add(3);
        s2.add(5);
        s2.add(1);
//test 1
        if (s1.equals(s2))
            System.out.println("Passed test 1: add method SortedSet");
        else
            System.out.println("Failed test 1: add method SortedSet");

//test 2
        s1.addAll(s2);
        if (s1.equals(s2))
            System.out.println("Passed test 2: addAll method SortedSet");
        else
            System.out.println("Failed test 2: addAll method SortedSet");

//test 3
        s1.clear();
        if(s1.size() == 0 && !s1.contains(1))
            System.out.println("Passed test 3: clear method SortedSet");
        else
            System.out.println("Failed test 3: clear method SortedSet");

//test 4
        s1.addAll(s2);
        if( s1.equals(s2) )
            System.out.println("Passed test 4: equals method SortedSet");
        else
            System.out.println("Failed test 4: equals method SortedSet");

//test 5
        s2.add(7);
        s1.add(6);
        SortedSet<Integer> result = new SortedSet<>();
        result.add(6);
        if (s1.difference(s2).equals(result))
            System.out.println("Passed test 5: difference method SortedSet");
        else
            System.out.println("Failed test 5: difference method SortedSet");

//test 6
        result.remove(6);
        result.add(1);
        result.add(2);
        result.add(5);
        result.add(3);
        if (s1.intersection(s2).equals(result))
            System.out.println("Passed test 6: intersection method SortedSet");
        else
            System.out.println("Failed test 6:intersection method SortedSet");

//test 7
        result.add(7);
        result.add(6);
        if (s1.union(s2).equals(result))
            System.out.println("Passed test 7: union method SortedSet");
        else
            System.out.println("Failed test 7: union method SortedSet");

//test 8
        if (s1.iterator().next() == 1)
            System.out.println("Passed test 8: iterator method SortedSet");
        else
            System.out.println("Failed test 8: iterator method SortedSet");

//test 9
        if (s1.size() == 5 && s2.size() == 5)
            System.out.println("Passed test 9: size method SortedSet");
        else
            System.out.println("Failed test 9: size method SortedSet");

//test 10
        s1.remove(0);
        s2.remove(1);
        if (s1.size() == 5 && s2.size() == 4)
            System.out.println("Passed test 10: remove method SortedSet");
        else
            System.out.println("Failed test 10: remove method SortedSet");

//test 11
        if (s1.contains(1) && !s2.contains(1))
            System.out.println("Passed test 11: contains method SortedSet");
        else
            System.out.println("Failed test 11: contains method SortedSet");

//test 12
        result.remove(7);
        if (s1.containsAll(result))
            System.out.println("Passed test 12: containsAll method SortedSet");
        else
            System.out.println("Failed test 12: containsAll method SortedSet");

//test 13
        UnsortedSet<Integer> unsorted = new UnsortedSet<>();
        unsorted.add(1);
        unsorted.add(5);
        unsorted.add(3);
        unsorted.add(7);
        SortedSet<Integer> sorted = new SortedSet<>(unsorted);
        SortedSet<Integer> excepted = new SortedSet<>();
        excepted.add(1);
        excepted.add(5);
        excepted.add(7);
        excepted.add(3);
        if (sorted.equals(excepted))
            System.out.println("Passed test 13: SortedSet constructor");
        else
            System.out.println("Failed test 13: SortedSet constructor");

        ISet<Integer> u1 = new UnsortedSet<>();
        u1.add(2);
        u1.add(1);
        ISet<Integer> u2 = new UnsortedSet<>();
        u2.add(2);
        u2.add(1);
//test 14
        if (u1.equals(u2))
            System.out.println("Passed test 14: add method UnsortedSet");
        else
            System.out.println("Failed test 14: add method UnsortedSet");

//test 15
        u1.clear();
        if (u1.size() == 0)
            System.out.println("Passed test 15: clear method UnsortedSet");
        else
            System.out.println("Failed test 15: clear method UnsortedSet");

//test 16
        u1.add(1);
        if (u1.difference(u2).size() == 0)
            System.out.println("Passed test 16: difference method UnsortedSet");
        else
            System.out.println("Failed test 16: difference method UnsortedSet");

//test 17
        if (u1.intersection(u2).size() == 1)
            System.out.println("Passed test 17: intersection method UnsortedSet");
        else
            System.out.println("Failed test 17: intersection method UnsortedSet");

//test 18
        u1.add(3);
        u1.add(4);
        if (u1.union(u2).size() == 4)
            System.out.println("Passed test 18: union method UnsortedSet");
        else
            System.out.println("Failed test 18: union method UnsortedSet");

//test 19
        u1.addAll(u2);
        if (u1.size() == 4)
            System.out.println("Passed test 19: addAll method AbstractSet");
        else
            System.out.println("Failed test 19: addAll method AbstractSet");

//test 20
        u2.add(3);
        u2.add(4);
        if (u1.containsAll(u2))
            System.out.println("Passed test 20: containsAll method AbstractSet");
        else
            System.out.println("Failed test 20: containsAll method AbstractSet");

//test 21
        if (u2.iterator().next() == 2)
            System.out.println("Passed test 21: iterator method AbstractedSet");
        else
            System.out.println("Failed test 21: iterator method AbstractedSet");

//test 22
        if (u1.remove(1) && u1.size() == 3)
            System.out.println("Passed test 22: remove method AbstractedSet");
        else
            System.out.println("Failed test 22: remove method AbstractedSet");

//test 23
        if (u2.size() == 4)
            System.out.println("Passed test 23: size method AbstractedSet");
        else
            System.out.println("Failed test 23: size method AbstractedSet");

//test 24
        u1.add(1);
        if (u2.equals(u1))
            System.out.println("Passed test 24: equals method AbstractedSet");
        else
            System.out.println("Failed test 24: equals method AbstractedSet");


        largeTest();


    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}