/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name:Diyuan Dai
*  email address: daidiyuan@126.com
*  UTEID: dd33653
*  Grader name: Tony
*  Number of slip days used on this assignment:
*/

//Experiment results. CS314 students, place your experiment
//results here:
/*
For ArrayList:
Getting random, iterator, Getting all using get method
are faster than LinkedList
For LinkedList
Adding at end, Adding at front, removing from front
are faster than ArrayList

Based on time data I got here:
both of adding at end seem O(N)
Adding at front: ArrayList seems O(N^2)
Adding at front: LinkedList seems O(N)
Removing from front: ArrayList seems O(N^2)
removing from front: LinkedList seems O(N)
Getting random: ArrayList seems O(N)
Getting random: LinkedList seems O(N^2)
both of Getting all using iterator seem O(N)
Getting all using get method: ArrayList seems O(N)
Getting all using get method: LinkedList seems O(N^2)
 */


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

   public static void main(String[] args) {

       LinkedList<String> list = new LinkedList<>();
       Object[] actual;
       Object[] expected;

       //test 0
       System.out.println("\nTest 0: initial list is empty");
       if (list.toString().equals("[]"))
           System.out.println("Passed test 0");
       else
           System.out.println("Failed test 0");

       //test 1
       System.out.println("\nTest 1: add to end");
       list.add("ABF");
       if (list.get(0).equals("ABF"))
           System.out.println("Passed test 1");
       else
           System.out.println("Failed test 1");

       //test 2
       System.out.println("\nTest 2: add to end");
       list.add("ABF");
       if (list.get(0).equals("ABF") || list.get(1).equals("ABF"))
           System.out.println("Passed test 2");
       else
           System.out.println("Failed test 2");

       //test 3
       System.out.println("\nTest 3: add to end");
       list.add("gg");
       if (list.get(2).equals("gg"))
           System.out.println("Passed test 3");
       else
           System.out.println("Failed test 3");

       //test 4
       System.out.println("\nTest 4: insert");
       list.insert(1, "ie2");
       if (list.toString().equals("[ABF, ie2, ABF, gg]"))
           System.out.println("Passed test 4");
       else
           System.out.println("Failed test 4");

       //test 5
       System.out.println("\nTest 5: insert");
       list.insert(0, "ie2");
       if (list.toString().equals("[ie2, ABF, ie2, ABF, gg]"))
           System.out.println("Passed test 5");
       else
           System.out.println("Failed test 5");

       //test 6
       list.insert(4, "fpx");
       checkList(6, "insert", list, "[ie2, ABF, ie2, ABF, fpx, gg]");

       //test 7
       list.set(0, "ig");
       checkList(7, "set", list, "[ig, ABF, ie2, ABF, fpx, gg]");

       //test 8
       list.set(5, "DWG");
       checkList(8, "set", list, "[ig, ABF, ie2, ABF, fpx, DWG]");

       //test 9
       list.set(1, "JT");
       checkList(9, "set", list, "[ig, JT, ie2, ABF, fpx, DWG]");

       //test 10
       System.out.println("\nTest 10: get");
       if (list.get(0).equals("ig"))
           System.out.println("Passed test 10");
       else
           System.out.println("Failed test 10");

       //test 11
       System.out.println("\nTest 11: get");
       if (list.get(2).equals("ie2"))
           System.out.println("Passed test 11");
       else
           System.out.println("Failed test 11");

       //test 12
       System.out.println("\nTest 12: get");
       if (list.get(4).equals("fpx"))
           System.out.println("Passed test 12");
       else
           System.out.println("Failed test 12");

       //test 13
       list.remove(2);
       checkList(13, "remove", list, "[ig, JT, ABF, fpx, DWG]");

       //test 14
       list.remove(2);
       checkList(14, "remove", list, "[ig, JT, fpx, DWG]");

       //test 15
       list.remove(3);
       checkList(15, "remove", list, "[ig, JT, fpx]");

       //test 16.0
       System.out.println("\nTest 16.0: remove");
       boolean result = list.remove("ig");
       if (result)
           System.out.println("Passed test 16.0");
       else
           System.out.println("Failed test 16.0");
       //test 16
       checkList(16, "remove", list, "[JT, fpx]");

       //test 17.0
       System.out.println("\nTest 17.0: remove");
       result = list.remove("ig");
       if (!result)
           System.out.println("Passed test 17.0");
       else
           System.out.println("Failed test 17.0");
       //test 17
       checkList(17, "remove", list, "[JT, fpx]");

       //test 18.0
       System.out.println("\nTest 18.0: remove");
       result = list.remove("JT");
       if (result)
           System.out.println("Passed test 18.0");
       else
           System.out.println("Failed test 18.0");
       //test 18
       checkList(18, "remove", list, "[fpx]");

       //test 19
       IList subList = list.getSubList(0, 1);
       checkList(19, "getSublist", (LinkedList<String>) subList, "[fpx]");

       //test 20
       list.add("RNG");
       list.add("EDG");
       subList = list.getSubList(1, 3);
       checkList(20, "getSublist", (LinkedList<String>) subList, "[RNG, EDG]");

       //test 21
       subList = list.getSubList(1,1);
       checkList(21, "getSublist", (LinkedList<String>) subList, "[]");

       //test 22
       System.out.println("\nTest 22: size");
       int numberResult = list.size();
       if (numberResult == 3)
           System.out.println("Passed test 22");
       else
           System.out.println("Failed test 22");

       list.add("RNG");
       list.add("EDG");
       //test 23
       System.out.println("\nTest 23: size");
       numberResult = list.size();
       if (numberResult == 5)
           System.out.println("Passed test 23");
       else
           System.out.println("Failed test 23");

       //test 24
       list.makeEmpty();
       System.out.println("\nTest 24: size");
       numberResult = list.size();
       if (numberResult == 0)
           System.out.println("Passed test 24");
       else
           System.out.println("Failed test 24");

       //test 25
       System.out.println("\nTest 25~27: indexOf");
       numberResult = list.indexOf("RNG");
       if (numberResult == -1)
           System.out.println("Passed test 25");
       else
           System.out.println("Failed test 25");

       //test 26
       list.add("RNG");
       numberResult = list.indexOf("RNG");
       if (numberResult == 0)
           System.out.println("Passed test 26");
       else
           System.out.println("Failed test 26");

       //test 27
       list.add("EDG");
       list.add("LGD");
       numberResult = list.indexOf("LGD");
       if (numberResult == 2)
           System.out.println("Passed test 27");
       else
           System.out.println("Failed test 27");

       //test 28
       System.out.println("\nTest 28~30: indexOf with pos");
       numberResult = list.indexOf("LGD", 2);
       if (numberResult == 2)
           System.out.println("Passed test 28");
       else
           System.out.println("Failed test 28");

       //test 29
       numberResult = list.indexOf("RNG", 2);
       if (numberResult == -1)
           System.out.println("Passed test 29");
       else
           System.out.println("Failed test 29");

       //test 30
       numberResult = list.indexOf("FPX", 0);
       if (numberResult == -1)
           System.out.println("Passed test 30");
       else
           System.out.println("Failed test 30");

       //test 31
       System.out.println("\nTest 31: making empty");
       list.makeEmpty();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println("Expected result: " + Arrays.toString(expected));
       System.out.println("Actual result: " + Arrays.toString(actual));
       if (arraysSame(actual, expected))
           System.out.println("Passed test 31");
       else
           System.out.println("Failed test 31");

       //test 32
       System.out.println("\nTest 32: making empty");
       list.makeEmpty();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println("Expected result: " + Arrays.toString(expected));
       System.out.println("Actual result: " + Arrays.toString(actual));
       if (arraysSame(actual, expected))
           System.out.println("Passed test 32");
       else
           System.out.println("Failed test 32");

       //test 33
       list.add("VG");
       list.add("V5");
       list.add("JDG");
       list.add("SNG");
       list.add("TOP");
       System.out.println("\nTest 33: making empty");
       list.makeEmpty();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println("Expected result: " + Arrays.toString(expected));
       System.out.println("Actual result: " + Arrays.toString(actual));
       if (arraysSame(actual, expected))
           System.out.println("Passed test 33");
       else
           System.out.println("Failed test 33");

       //test 34
       list.add("VG");
       list.add("V5");
       list.add("JDG");
       list.add("SNG");
       list.add("TOP");
       list.add("LGD");
       list.removeRange(0, 3);
       checkList(34, "removeRange", list, "[SNG, TOP, LGD]");

       //test 35
       list.removeRange(0, 0);
       checkList(35, "removeRange", list, "[SNG, TOP, LGD]");

       //test 36
       list.removeRange(0, 1);
       checkList(36, "removeRange", list, "[TOP, LGD]");

       //test 37
       System.out.println("\nTest 37: toString");
       String stringActual = list.toString();
       String stringExpected = "[TOP, LGD]";
       System.out.println("Expected result: " + stringExpected);
       System.out.println("Actual result: " + stringActual);
       if (stringActual.equals(stringExpected))
           System.out.println("Passed test 37");
       else
           System.out.println("Failed test 37");

       //test 38
       System.out.println("\nTest 38: toString");
       list.add("TOP");
       list.add("LGD");
       stringActual = list.toString();
       stringExpected = "[TOP, LGD, TOP, LGD]";
       System.out.println("Expected result: " + stringExpected);
       System.out.println("Actual result: " + stringActual);
       if (stringActual.equals(stringExpected))
           System.out.println("Passed test 38");
       else
           System.out.println("Failed test 38");

       //test 39
       System.out.println("\nTest 39: toString");
       list.makeEmpty();
       stringActual = list.toString();
       stringExpected = "[]";
       System.out.println("Expected result: " + stringExpected);
       System.out.println("Actual result: " + stringActual);
       if (stringActual.equals(stringExpected))
           System.out.println("Passed test 39");
       else
           System.out.println("Failed test 39");

       //test 40
       System.out.println("\nTest 40: equals");
       list.add("VG");
       list.add("V5");
       list.add("JDG");
       list.add("SNG");
       list.add("TOP");
       list.add("LGD");
       list.add("BLG");
       String lolTeams = "[VG, V5, JDG, SNG, TOP, LGD, BLG]";
       if (!list.equals(lolTeams))
           System.out.println("Passed test 40");
       else
           System.out.println("Failed test 40");

       //test 41
       System.out.println("\nTest 41: equals");
       LinkedList<String> otherList = new LinkedList<>();
       otherList.add("SKT");
       otherList.add("GRF");
       otherList.add("DWG");
       if (!list.equals(otherList))
           System.out.println("Passed test 40");
       else
           System.out.println("Failed test 40");

       //test 42
       System.out.println("\nTest 42: equals");
       list.makeEmpty();
       list.add("SKT");
       list.add("GRF");
       list.add("DWG");
       if (list.equals(otherList))
           System.out.println("Passed test 42");
       else
           System.out.println("Failed test 42");

       //test 43
       list.addFirst("SSW");
       checkList(43, "addFirst", list, "[SSW, SKT, GRF, DWG]");

       //test 44
       list.addFirst("SSG");
       checkList(44, "addFirst", list, "[SSG, SSW, SKT, GRF, DWG]");

       //test 45
       list.addFirst("KT");
       checkList(45, "addFirst", list, "[KT, SSG, SSW, SKT, GRF, DWG]");

       //test 46
       list.addLast("KZ");
       checkList(46, "addLast", list, "[KT, SSG, SSW, SKT, GRF, DWG, KZ]");

       //test 47
       list.addLast("AFC");
       checkList(47, "addLast", list, "[KT, SSG, SSW, SKT, GRF, DWG, KZ, AFC]");

       //test 48
       list.addLast("OT");
       checkList(47, "addLast", list, "[KT, SSG, SSW, SKT, GRF, DWG, KZ, AFC, OT]");


       //test 49
       list.removeFirst();
       checkList(49, "removeFirst", list, "[SSG, SSW, SKT, GRF, DWG, KZ, AFC, OT]");

       //test 50
       list.removeFirst();
       checkList(50, "removeFirst", list, "[SSW, SKT, GRF, DWG, KZ, AFC, OT]");

       //test 51
       list.removeFirst();
       checkList(51, "removeFirst", list, "[SKT, GRF, DWG, KZ, AFC, OT]");

       //test 52
       list.removeLast();
       checkList(52, "removeLast", list, "[SKT, GRF, DWG, KZ, AFC]");

       //test 53
       list.removeLast();
       checkList(53, "removeLast", list, "[SKT, GRF, DWG, KZ]");

       //test 54
       list.removeLast();
       checkList(54, "removeLast", list, "[SKT, GRF, DWG]");

       //test 55
       System.out.println("\nTest 55: iterator.hasNext");
       LinkedList<String> list1 = new LinkedList<>();
       Iterator<String> it1 = list1.iterator();
       if (!it1.hasNext())
           System.out.println("Passed test 55");
       else
           System.out.println("Failed test 55");

       //test 56
       System.out.println("\nTest 56: iterator.hasNext");
       LinkedList<String> list2 = new LinkedList<>();
       list2.add("TL");
       Iterator<String> it2 = list2.iterator();
       if (it2.hasNext())
           System.out.println("Passed test 56");
       else
           System.out.println("Failed test 56");

       //test 57
       System.out.println("\nTest 57: iterator.hasNext");
       it2.next();
       if (!it2.hasNext())
           System.out.println("Passed test 56");
       else
           System.out.println("Failed test 56");

       //test 58
       System.out.println("\nTest 58: iterator.Next");
       LinkedList<String> list3 = new LinkedList<>();
       list3.add("G2");
       list3.add("UNC");
       list3.add("C9");
       list3.add("TSM");
       list3.add("HKA");
       list3.add("FW");
       list3.add("AGK");
       Iterator<String> it3 = list3.iterator();
       String resultString = it3.next();
       if (resultString.equals("G2"))
           System.out.println("Passed test 58");
       else
           System.out.println("Failed test 58");

       //test 59
       System.out.println("\nTest 59: iterator.Next");
       resultString = it3.next();
       if (resultString.equals("UNC"))
           System.out.println("Passed test 59");
       else
           System.out.println("Failed test 59");

       //test 60
       System.out.println("\nTest 60: iterator.Next");
       resultString = it3.next();
       if (resultString.equals("C9"))
           System.out.println("Passed test 60");
       else
           System.out.println("Failed test 60");

       //test 61
       it3.remove();
       checkList(61, "iterator.remove", list3, "[G2, UNC, TSM, HKA, FW, AGK]");

       //test 62
       it3.next();
       it3.remove();
       checkList(62, "iterator.remove", list3, "[G2, UNC, HKA, FW, AGK]");

       //test 63
       it3.next();
       it3.remove();
       checkList(63, "iterator.remove", list3, "[G2, UNC, FW, AGK]");
       //CS314 students. Add your tests above:

       // CS314 Students:
       // uncomment the following line to run tests comparing
       // your LinkedList class to the java ArrayList class.
       comparison();
   }

   private static Object[] toArray(LinkedList<String> list) {
       Object[] result = new Object[list.size()];
       Iterator<String> it = list.iterator();
       int index = 0;
       while ( it.hasNext() ){
           result[index] = it.next();
           index++;
       }
       return result;
   }

   //pre: none
   private static boolean arraysSame(Object[] one, Object[] two)  {
       boolean same;
       if( one == null || two == null ) {
           same = (one == two);
       }
       else {
           //neither one or two are null
           assert one != null && two != null;
           same = one.length == two.length;
           if( same ) {
               int index = 0;
               while( index < one.length && same ) {
                   same = ( one[index].equals(two[index]) );
                   index++;
               }
           }
       }
       return same;
   }
  
   
   public static final int NUM_DOUBLINGS_OF_N = 5;
   private static final int NUM_REPEATS_OF_TEST = 100;

   // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
   public static void comparison() {
       Stopwatch s = new Stopwatch();
       
       int initialN = 30000;
       addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 2000;
       addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 10000;
       addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 2000;
       removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 5000;
       removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 10000;
       getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 1000;
       getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 50000;
       getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
       getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 100000;
       getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 1000;
       getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

   }

   // method to check the list result of tests
   public static void checkList (int testNumber, String testedMethod, LinkedList<String> list, String expected) {
       System.out.println("\nTest " + testNumber + ": " + testedMethod);
       if( list.toString().equals(expected) )
           System.out.println("Passed test " + testNumber);
       else
           System.out.println("Failed test " + testNumber);
   }

   // These pairs of method illustarte a failure to use polymorphism
   // If the students had implemented the Java list interface there
   // could be a single method. Also we could use function objects to
   // reduce the awful repitition of code.
   public static void addEndArrayList(Stopwatch s, int initialN, int numTests) {

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: ArrayList", totalTimes, initialN);
   }

   private static void showResults(String title, double[] times, int initialN) {
       System.out.println();
       System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
       System.out.println(title);
       for (double time : times) {
           System.out.print("N = " + initialN + ", total time: ");
           System.out.printf("%7.4f\n", time);
           initialN *= 2;
       }
       System.out.println();
   }

   public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: LinkedList", totalTimes, initialN);
   }

   public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: ArrayList", totalTimes, initialN);
   }

   public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.insert(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: LinkedList", totalTimes, initialN);
   }

   public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
       double[] totalTimes = new double[numTests];        
       for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<String> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j + "");
               }
               s.start();
               while (!javaList.isEmpty()) {
                   javaList.remove(0);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Removing from front: ArrayList", totalTimes, initialN);
   }

   public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;    
           for (int i = 0; i < numTests; i++) {
               LinkedList<String> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j + "");
               }
               s.start();
               while (studentList.size() != 0) {
                   studentList.removeFirst();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("removing from front: LinkedList", totalTimes, initialN);
   }

   public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += javaList.get(r.nextInt(javaList.size()));
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: ArrayList", totalTimes, initialN);
   }

   public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList< >();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += studentList.get( r.nextInt(studentList.size()) );
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               Iterator<Integer> it = javaList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }        
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               Iterator<Integer> it = studentList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < javaList.size(); j++) {
                   x += javaList.get(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using get method: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < studentList.size(); j++) {
                   x += studentList.get(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using get method: LinkedList", totalTimes, initialN);
   }

   // for future changes
   private static interface ArrayListOp {
       public <E> void op(ArrayList<E> list, E data);
   }
   
   private ArrayListOp addAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(data);
       }
   };
   
   private ArrayListOp addFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(0, data);
       }
   };
   
   private ArrayListOp removeFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.remove(0);
       }
   };

   private static interface LinkedListOp<E> {
       public void op(LinkedList<E> list);
   }
}
