/**
 * Solution for problem #41 (https://projecteuler.net/problem=41)
 * @author Sriram
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class E41 is the class which consists of the main method.
 */
class E41 {
  /**
   * Main static method for the class.
   * 
   * @param args
   *            Command line arguments for the main method
   */
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    /*
     * We initialize the longestPDP to be -1 (clearly not a PDP) in case pan
     * digital primes never exists We also know that the largest possible
     * Pan Digital number is 987654321 which fits in the INT range Hence
     * longestPDP is an integer number and we don't have to check anything
     * else
     */
    int longestPdp = -1;
    try {
      // Create a list of all digits 1-9
      final List<Integer> digits = new ArrayList<Integer>(Arrays.asList(
          9, 8, 7, 6, 5, 4, 3, 2, 1));
      /*
       * We are going to create all the permutations of lengths from 9 to
       * 1, from the 'digits' list. We are starting from 9 because we need
       * the longest pan digital number, so we can stop searching when we
       * get one
       */
      for (int length = 9; length >= 1; length--) {
        // The 'perms' list contains all the permutations of length
        List<Integer> perms = new ArrayList<Integer>();
        /*
         * permutations() is called which will create a list of
         * permutations for a a sublist of a given size.
         * digits.subList(digits.size()-length, digits.size()) is a very
         * cool hack: The digits is already ordered in non-increasing
         * way. So for every iteration in the loop, we send a sublist
         * after removing the digits.size()-length elements from the
         * front
         */
        permutations(perms, new ArrayList<Integer>(),
            digits.subList(digits.size() - length, digits.size()));
        // 'perms' now contain all pan-digital permutations of size
        // length
        for (int perm : perms) {
          if (isPrimeNumber(perm)) {
            longestPdp = perm;
            break;
          }
        }
        if (longestPdp != -1) {
          break;
        }
      }
      if (longestPdp != -1) {
        System.out.println("The longest pan digital number is "
            + longestPdp);
      } else {
        System.out
          .println("There's no pandigital numbers which are prime");
      }
    } catch (Exception e) {
      System.out.println("There's an error in the processing"
          + e.getStackTrace());
    }
    long endTime = System.currentTimeMillis();
    System.out.println("This program took " + (endTime - startTime)
        + "ms to run");
  }

  private static boolean isPrimeNumber(int num) {
    // It is enough we check if there are factors for a number until the
    // Square Root of that number
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

  private static void permutations(List<Integer> retList,
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
        // This is to avoid duplicates as we need only one of every
        // digit
        if (tempDigits.contains(digits.get(i))) {
          continue;
        }
        // We add the number if it's not present already
        tempDigits.add(digits.get(i));
        permutations(retList, tempDigits, digits);
        // Recursively we remove the last element to substitute with a
        // different element in the next recursive for loop
        tempDigits.remove(tempDigits.size() - 1);
      }
    }
  }
}
