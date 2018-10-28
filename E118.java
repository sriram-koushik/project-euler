/**
 * Solution for problem #118 (https://projecteuler.net/problem=118)
 * @author Sriram
 * @version 1.0
 */

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Class E41 is the class which consists of the main method
 */
class E118 {
    /**
     * Main static method for the class
     * @param args Command line arguments for the main method
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        try 
        {
            /*
             * Create a list of all digits 1-9. The order is important because we are going
             * to consider only increasing permutations to avoid duplicates
             */
            final List<Integer> digits = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
            /*
             * Final return count is stored here. 
             * Since the count of all 10 digit pandigital numbers equals 10! (3628800),
             * we can store it in an Integer
             */
            int retCount = 0;
            //The 'perms' list will contain all the permutations of the given length 
            List<Integer> perms = new ArrayList<Integer>();
            permutations(perms, new ArrayList<Integer>(), digits);
            //'perms' now contains all pan-digital permutations of length 10
            for(int perm:perms)
            {
                //previous value sent as argument is set to 0 because pandigital numbers
                //can consist of values above 1, so 0 guarantees this check will hold initially
                retCount+=splitAndCheck(String.valueOf(perm),0,0);
            }
            System.out.println("The total number of sets that can be formed by combining digits which are prime numbers: "+ retCount);
        } 
        catch (Exception e) 
        {
            System.out.println("There's an error in the processing" + e.getStackTrace());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("This program took "+(endTime-startTime)+"ms to run");
    }
    
    /**
     * isPrimeNumber checks if a given number is prime
     * @param num the Integer to check
     * @return boolean true if argument is prime else return false
     */
    private static boolean isPrimeNumber(int num)
    {
        //It is enough we check if there are factors for a number until the Square Root of that number
        /*
         * We are checking for every number instead of using a sieve because this is far efficient than creating a sieve for 987654321 numbers
         * Here we will compute this only for a maximum of 9! = 362880 numbers
         */
        int boundary = (int)Math.floor(Math.sqrt(num));
        if (num <= 1) 
        {
            return false;
        }
        if (num == 2) 
        {
            return true;
        }
        for (int i = 2; i <= boundary; ++i)
        {
            if (num % i == 0) 
                return false;
        }
        return true;
    }
    
    /**
     * perumatations is method to find all possible permutations of a give list of integers
     * @param retList is a list which returns a combination of the returned various permutations
     * @param tempDigits is a temporary list for the stack memory to keep track of history
     * @param digits is the input list from which permutations are generated
     */
    private static void permutations(List<Integer> retList, List<Integer> tempDigits, List<Integer> digits)
    {
       /*
        * We generate a list of pan-digital permutations,
        * we push permutation once the required size has been attained,
        * by combining the list and converting it to a single number
        */
        //Base condition is when required size is achieved
        if(tempDigits.size() == digits.size())
        {
           StringBuilder permFormation = new StringBuilder();
           for(int i:tempDigits)
           {
               permFormation.append(i+"");
           }
           retList.add(Integer.parseInt(permFormation.toString()));
        } 
        else
        {
           for(int i = 0; i < digits.size(); i++)
           { 
              //This is to avoid duplicates as we need only one of every digit
              if(tempDigits.contains(digits.get(i))) 
                  continue; 
              //We add the number if it's not present already
              tempDigits.add(digits.get(i));
              permutations(retList, tempDigits, digits);
              //Recursively we remove the last element to substitute with a different element in the next recursive for loop
              tempDigits.remove(tempDigits.size() - 1);
           }
        }
     } 
     /**
      * splitAndCheck checks if a particular permutation string can be split and if the splits are all prime
      * @param permutation the given permutation string to check
      * @param index Initial index of the string to check from
      * @param prevVal is the previous split value to compare with
      * @return the total number of splits which are possible with the given constraints
      */
     private static int splitAndCheck(String permutation, int index, int prevVal) {
        /*
         * The idea is to split them until the prefix is a prime,
         * all splits are prime and no split is lesser than the previous split;
         * We need a condition to check if all split combinations that we return are unique,
         * hence we employ the condition to check if a split is always greater than the previous split.
         * For example {2,5,47,631,89} is a valid split, but so is {2,5,47,89,631}. So we maintain only the increasing splits
         */
        int count = 0;
        //Run the entire algorithm until every character of the permutation
        for (int i = index; i < permutation.length(); i++) 
        { 
            //Form the number until the previous index
            int curr = Integer.parseInt(permutation.substring(index, i+1));
            /*
             * Check if the current split is greater than the previous number and if it is a prime
             * If either fails, we ignore further computations
             */
            if(curr>prevVal && isPrimeNumber(curr))
            {
                //This is the end base case suggesting we have utilized all numbers in the permutation
                //We update the count since the current form of split satisfies the condition
                if(i == (permutation.length()-1)) 
                    return count + 1;
                else
                    //We continue recursion with the current value as the previous value
                    count = count+splitAndCheck(permutation, i + 1, curr);
            }
        }
        return count;
    }
}