/**
 * GeneralUtils is a class which consists the implementations of the 
 * commonly used Euler computations
 * @author Sriram
 * @version 1.0
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class 'GeneralUtils' is a Utility class.
 * This provides necessary utility functions for a range of string and other
 *          computations
 */

public class GeneralUtils {
  
  /**
   * Constructor for GeneralUtils.
   * 
   * @param bound the highest value until which prime numbers are computed
   */
  public static boolean isPrimeNumber(int num) {
    // It is enough we check if there are factors for a number until the square Root of that number
    /*
     * We are checking for every number instead of using a sieve because
     * this is far efficient than creating a sieve for 987654321 numbers
     * Here we will compute this only for a maximum of 9! = 362880 numbers
     */
    int boundary = (int) Math.floor(Math.sqrt(num));
    if (num <= 1) {
      return false;
    }
    if (num == 2) {
      return true;
    }
    for (int i = 2; i <= boundary; ++i) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * permutations is the wrapper method calling the permutationsCore method to return
   * the various permutations. The main reason for using a wrapper is to promote re-usability,
   * testability and to avoid user to provide unnecessary variables not related to their input.
   * This can be further enhanced to support multi-threading
   * 
   * @param retList
   *            is a list which returns a combination of the returned various
   *            permutations. This is where the output is stored
   * @param digits
   *            is the input list from which permutations are generated
   */
  public static void permutations(List<Integer> retList, List<Integer> digits) {
    permutationsCore(retList, new ArrayList<Integer>(), digits);
  }
  
  /**
   * permutationsCore is core method for finding all possible permutations of a given list.
   * 
   * @param retList
   *            is a list which returns a combination of the returned various
   *            permutations. This is where the output is stored
   * @param tempDigits
   *            is a temporary list for the stack memory to keep track of
   *            history
   * @param digits
   *            is the input list from which permutations are generated
   */
  public static void permutationsCore(List<Integer> retList,
      List<Integer> tempDigits, List<Integer> digits) {
    /*
     * We generate a list of pan-digital permutations, we push permutation
     * once the required size has been attained, by combining the list and
     * converting it to a single number
     */
    
    // Base condition is when required size is achieved
    if (tempDigits.size() == digits.size()) {
      StringBuilder permFormation = new StringBuilder();
      for (int i : tempDigits) {
        permFormation.append(i + "");
      }
      retList.add(Integer.parseInt(permFormation.toString()));
    } else {
      for (int i = 0; i < digits.size(); i++) {
        // This is to avoid duplicates as we need only one of every digit
        if (tempDigits.contains(digits.get(i))) {
          continue;
        }
        // We add the number if it's not present already
        tempDigits.add(digits.get(i));
        permutationsCore(retList, tempDigits, digits);
        /* 
         * Recursively we remove the last element to substitute with a
         * different element in the next recursive for loop
         */
        tempDigits.remove(tempDigits.size() - 1);
      }
    }
  }
  
  /**
   * splitAndCheckCore is the wrapper  function responsible for  
   * calling the core function and checking if a particular permutation string can be split.
   * The splits are in such a way that all splits are prime.  The main reason for using a wrapper 
   * is to promote re-usability, testability and to avoid user to provide unnecessary variables not related to their input.
   * This can be further enhanced to support multi-threading.
   * 
   * @param permutation
   *            the given permutation string to check
   * @return the total number of splits which are possible with the given
   *         constraints
   */
  public static int splitAndCheck(String permutation) {
    return splitAndCheckCore(permutation,0,0);
  }
  
  /**
   * splitAndCheckCore is the core function responsible for 
   * checking if a particular permutation string can be split.
   * The splits are in such a way that all splits are prime.
   * 
   * @param permutation
   *            the given permutation string to check
   * @param index
   *            Initial index of the string to check from
   * @param prevVal
   *            is the previous split value to compare with. Usually a
   *            number less than or equal to 0
   * @return the total number of splits which are possible with the given
   *         constraints
   */
  public static int splitAndCheckCore(String permutation, int index, int prevVal) {
    /*
     * The idea is to split them until the prefix is a prime, all splits are
     * prime and no split is lesser than the previous split; We need a
     * condition to check if all split combinations that we return are
     * unique, hence we employ the condition to check if a split is always
     * greater than the previous split. For example {2,5,47,631,89} is a
     * valid split, but so is {2,5,47,89,631}. So we maintain only the
     * increasing splits
     */
    int count = 0;
    // Run the entire algorithm until every character of the permutation
    for (int i = index; i < permutation.length(); i++) {
      // Form the number until the previous index
      int curr = Integer.parseInt(permutation.substring(index, i + 1));
      /*
       * Check if the current split is greater than the previous number
       * and if it is a prime If either fails, we ignore further
       * computations
       */
      if (curr > prevVal && isPrimeNumber(curr)) {
        /* 
         * This is the end base case suggesting we have utilized all
         * numbers in the permutation
         * We update the count since the current form of split satisfies
         * the condition 
         */
        if (i == (permutation.length() - 1)) {
          return count + 1;
        } else {
          // We continue recursion with the current value as the previous value
          count = count + splitAndCheckCore(permutation, i + 1, curr);
        }
      }
    }
    return count;
  }
}
