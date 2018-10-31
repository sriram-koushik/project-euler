/**
 * Solution for problem #41 (https://projecteuler.net/problem=41)
 * @author Sriram
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.GeneralUtils;
import values.ValueStrings;

/**
 * Class E41 is the class which consists of the main method.
 */
class E41 {
  /**
   * Main static method for the class.
   * 
   * @param args Command line arguments for the main method
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
        GeneralUtils.permutations(perms, digits.subList(digits.size() - length, digits.size()));
        // 'perms' now contain all pan-digital permutations of size
        // length
        for (int perm : perms) {
          if (GeneralUtils.isPrimeNumber(perm)) {
            longestPdp = perm;
            break;
          }
        }
        if (longestPdp != -1) {
          break;
        }
      }
      if (longestPdp != -1) {
        System.out.println(ValueStrings.longestPandigitalNotif
            + longestPdp);
      } else {
        System.out
          .println(ValueStrings.noPandigitalNotif);
      }
    } catch (Exception e) {
      System.out.println(ValueStrings.processingError
          + e.getStackTrace());
    }
    long endTime = System.currentTimeMillis();
    System.out.println(ValueStrings.timeTakenNotif1 + (endTime - startTime)
        + ValueStrings.timeTakenNotif2);
  }

}
