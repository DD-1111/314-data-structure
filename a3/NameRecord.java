import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that record the popularity of a name and reflect the tendency
 */

public class NameRecord {
    //instance variables

    private String dataName;
    private ArrayList<Integer> rankData;


    //  A constructor to create a new NameRecord
    public NameRecord(String scData) {
        Scanner data = new Scanner(scData);
        dataName = data.next();
        rankData = new ArrayList<Integer>();
        while (data.hasNextInt()) {
            rankData.add(data.nextInt());
        }
    }

    // to return data's name
    public String getDataName(){
        return dataName;
    }

    // to return the baseDecade
    public int getBaseDecade(){
        return Names.baseDecade;
    }

    //  A method to return the NameRecords rank for a given decade
    public Integer getRank(int decadeIndex){
        return rankData.get(decadeIndex);
    }

    //  A method to return the most popular year for a name
    public int getBestDecade(){
        int minimum = 1000;
        int minIndex =0;
        ArrayList<Integer> dataNoZero = getRidOfZero();
        //use for loop to find the minimum number's index
        for (int i = 0 ; i < dataNoZero.size(); i++ ){
            // the valid minimum is not 0
            if (dataNoZero.get(i) <= minimum && dataNoZero.get(i) != 0){
                minimum = dataNoZero.get(i);
                minIndex = i;

            }
        }
        return getBaseDecade() + minIndex * 10;
    }

    //  A method to count the number of decades this name has been ranked in the top 1000.
    public int getRankedTime(){
        int count = 0 ;
        for (int i = 0 ; i < rankData.size(); i++ ){
            if (rankData.get(i) != 0){
                count++;
            }
        }
        return count;
    }

    //a method that returns true if this name has been ranked in the top 1000 in every decade
    public boolean alwaysPopular(){
        for (int i = 0 ; i < rankData.size(); i++ ){
            if (rankData.get(i) == 0){
                return false;
            }
        }
        return true;
    }

    //a method that returns true if this name has been ranked in the top 1000 in only one decade
    public boolean onlyOncePopular(){
        int found = 0;
        int index = 0;
        // to save time, use while loop that will stop when the second non-zero value is found
        // if the nonzero value is not found and index is in the bound
        while(found <= 1 && index < rankData.size()){
            //if the nonzero value is not found or only found once
            //keep checking if the value in current index is zero
            if (rankData.get(index) != 0){
                found++;
            }
            index++;
        }
        // if non-0 value was found only once
        if (found == 1){
            return true;
        }
        // if when the while loop end and haven't return true, that means 0 was found at least twice
        else {
            return false;
        }
    }

    //Helper method, to create a deep copy of the Arraylist that replace 0 with 1001 for methods sorting value.
   private ArrayList<Integer> getRidOfZero(){
        ArrayList<Integer> data = new ArrayList<>();
        // make copy but replace all 0 with 1001
        for (int i = 0; i < rankData.size(); i++){
            if(rankData.get(i) == 0){
                data.add(1001);
            }
            else{
                data.add(rankData.get(i));
            }
        }
        return data;
    }

    //the method check if the value in Arraylist is always decreasing, which means getting more and more popular
    public boolean morePopular(){

        for(int i = 0; i < getRidOfZero().size() - 1; i++){
            //once there is a case that current value is smaller than the next value, return false
            if(getRidOfZero().get(i) <= getRidOfZero().get(i+1)){
                return false;
            }
        }
        //otherwise return true
        return true;
    }

    //the method check if the value in Arraylist is always increasing, which means getting less and less popular
    public boolean lessPopular(){
        for(int i = 0; i < getRidOfZero().size() - 1; i++){
            //once there is a case that current value is bigger than the next value, return false
            if(getRidOfZero().get(i) >= getRidOfZero().get(i+1)){
                return false;
            }
        }
        //otherwise return true
        return true;
    }


    public ArrayList<Integer> getRankData(){
        return rankData;
    }



    //override toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(dataName + "\n");
        for (int i = 0; i < rankData.size(); i++){
            sb.append(getBaseDecade() + i * 10+ ": " + rankData.get(i) + "\n");
        }
        return sb.toString();
    }

    //method designed by my own
    //for this method to return true,
    //the rank list should go up and down alternatively for consecutive decades
    //just like 0 900 950 100 250 200 0 300 0
    public boolean rankUpDown() {
        //the two consecutive "0"s at the beginning will cause a mistake
        if ((rankData.get(0) == 0) && (rankData.get(1) == 0)){
            return false;
        }
        //check if the first element is bigger than the second
        boolean bigger = (getRidOfZero().get(0) > getRidOfZero().get(1));
        for (int i = 1; i < rankData.size() - 1; i++){
            //if the the next value is still bigger or still small than its next one,
            //return false, no need to continue, save time;
            if ((getRidOfZero().get(i) > getRidOfZero().get(i + 1) == bigger)){
                return false;
            }
            //reverse the boolean bigger because the boolean bigger required should be alternative
            bigger = !bigger;
        }
        return true;
    }
}

