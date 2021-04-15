/*  Student information for assignment:
*
*  On my honor, Diyuan Dai, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID:dd33653
*  email address:daidiyuan@126.com
*  Number of slip days I am using:
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {
    public static int baseDecade;
    private int decadeNum;
    private ArrayList<NameRecord> names;

    /**
     * Construct a new Names object based on the data source the Scanner 
     * sc is connected to. Assume the first two lines in the 
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded 
     * and are not part of the resulting Names object. 
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names 
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
         baseDecade = Integer.parseInt(sc.nextLine());
         decadeNum = Integer.parseInt(sc.nextLine());
         names = new ArrayList<>();
         // for all the lines
         while(sc.hasNextLine()){
             String lineData = sc.nextLine();
             //check pre con using helper method
             if (checkLineData(lineData)) {
                 names.add(new NameRecord(lineData));
            }
             //if it does not fit pre con just skip that line
         }
    }


    //A helper method to check pre con: if ranks all 0 or
    // lines without the correct number of decades are discarded
    private boolean checkLineData(String data){
        //create a new scanner to check the number of ranks and if it is all 0
        Scanner checkPreCon = new Scanner(data);
        boolean allZero = true;
        int count = 0;
        //skip the name
        checkPreCon.next();
        //to check trough that line
        while(checkPreCon.hasNextInt()){
            // if one rank is not zero, assign allZero false and count add one
            if (checkPreCon.nextInt() != 0){
                allZero = false;
                count++;
            }
            // otherwise we just add count
            else {count ++;}
        }
        //return the result if the lineData suitable to be stored as NameRecord
        return (!allZero && count ==decadeNum) ;
    }


    /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<NameRecord> getMatches(String partialName) {
       // check pre con
       if (partialName == null || !(partialName.length() > 0)){
           throw new IllegalArgumentException("parameter partialName may not be null or empty");
       }
       //create a new ArrayList to store the match result
       ArrayList<NameRecord> matched = new ArrayList<NameRecord>();
       for (int i = 0; i < names.size(); i++){
           //if the the name in an NameRecord contains PartialName,
           //add that NameRecord to the new ArrayList
           if( containsIgnoreCase(names.get(i).getDataName(),partialName)){
               matched.add(names.get(i));
           }
       }
       // sort the matches ArrayList use the comparator
       Collections.sort(matched, new NameRecordSort());
       return matched;
   }

    //a helper method that similar to contains but it is not case sensitive

    private boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }




   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   
   public ArrayList<String> rankedEveryDecade() {
       ArrayList<String> matched = new ArrayList<String>();
       for (int i = 0; i < names.size(); i++){
           //if the the name in an NameRecord is always popular for all the rank records,
           //add that name to the new ArrayList
           if(names.get(i).alwaysPopular()){
               matched.add(names.get(i).getDataName());
           }
       }
       // sort the matches ArrayList use the comparator
       Collections.sort(matched, String::compareToIgnoreCase);
       return matched;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedOnlyOneDecade() {
       ArrayList<String> matched = new ArrayList<>();
       for (int i = 0; i < names.size(); i++){
           //if the the name in an NameRecord is only once ranked above 1000,
           //add that name to the new ArrayList
           if(names.get(i).onlyOncePopular()){
               matched.add(names.get(i).getDataName());
           }
       }
       // sort the matches ArrayList use the comparator
       Collections.sort(matched, String::compareToIgnoreCase);
       return matched;
       
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysMorePopular() {
       ArrayList<String> matched = new ArrayList<>();
       for (int i = 0; i < names.size(); i++){
           //if the the name in an NameRecord is always getting more
           //popular every decade add that name to the new ArrayList
           if(names.get(i).morePopular()){
               matched.add(names.get(i).getDataName());
           }
       }
       // sort the matches ArrayList use the comparator
       Collections.sort(matched, String::compareToIgnoreCase);
       return matched;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysLessPopular() {
       ArrayList<String> matched = new ArrayList<>();
       for (int i = 0; i < names.size(); i++){
           //if the the name in an NameRecord is always getting less
           //popular every decade, add that name to the new ArrayList
           if(names.get(i).alwaysPopular()){
               matched.add(names.get(i).getDataName());
           }
       }
       // sort the matches ArrayList use the comparator
       Collections.sort(matched, String::compareToIgnoreCase);
       return matched;
   }
   
   /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
   public NameRecord getName (String name) {
       if(name == null)
           throw new IllegalArgumentException("The parameter name cannot be null");
       for (int i = 0; i < names.size(); i++){
           //for each NameRecord in the Array list name
           //if the name equals the parameter target
           //return that NameRecord
           if(names.get(i).getDataName().equalsIgnoreCase(name)){
               return names.get(i);
           }
       }
       //return null if not founded
       return null;
   }
    /**
     * this method will return an ArrayList contains NameRecords that have up-down ranks in the limited decade
     * the ranklist meet the conditions will go up and down alternatively for consecutive decades
     * just like 0 900 0 100 250 200 600 300 0
     *
     * The condition checking method is wrote in NameRecord class
     * two consecutive "0" will not be necessarily meet the requirement so will not be returned in the ArrayList
     *
     * This is pretty uncommon to happen so that no name in names.txt meet the requirement
     * However, "Issac" in names2.txt meet the requirement
     *
     */

    public ArrayList<String> alternativelyUpDown() {
        ArrayList<String> matched = new ArrayList<>();
        for (int i = 0; i < names.size(); i++){
            //if the the name in an NameRecord will go up and down for consecutive decades
            //add that name to the new ArrayList
            if(names.get(i).rankUpDown()){
                matched.add(names.get(i).getDataName());
            }
        }
        // sort the matches ArrayList use the comparator
        Collections.sort(matched, String::compareToIgnoreCase);
        return matched;
    }
    //create a new class implements the comparator interface and override the compare method
    private class NameRecordSort implements Comparator<NameRecord> {
        public int compare(NameRecord comparedName, NameRecord otherName){
            String stringName = comparedName.getDataName();
            String stringOtherName = otherName.getDataName();
            return stringName .compareToIgnoreCase(stringOtherName);
        }
    }

}



