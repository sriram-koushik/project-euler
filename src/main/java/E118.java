/**
 * Solution for problem #118 (https://projecteuler.net/problem=118)
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
class E118 {
  /**
   * Main static method for the class.
   * 
   * @param args
   *            Command line arguments for the main method
   */
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      /*
       * Create a list of all digits 1-9. The order is important because
       * we are going to consider only increasing permutations to avoid
       * duplicates
       */
      final List<Integer> digits = new ArrayList<Integer>(Arrays.asList(
          1, 2, 3, 4, 5, 6, 7, 8, 9));
      /*
       * Final return count is stored here. Since the count of all 10
       * digit pandigital numbers equals 10! (3628800), we can store it in
       * an Integer
       */
      int retCount = 0;
      
      // The 'perms' list will contain all the permutations of the given
      // length
      List<Integer> perms = new ArrayList<Integer>();
      
      GeneralUtils.permutations(perms, digits);
      
      // 'perms' now contains all pan-digital permutations of length 10
      for (int perm : perms) {
        // previous value sent as argument is set to 0 because
        // pandigital numbers
        // can consist of values above 1, so 0 guarantees this check
        // will hold initially
        retCount += GeneralUtils.splitAndCheck(String.valueOf(perm));
      }
      
      System.out
          .println(ValueStrings.pdigitalPrimeSetNotif
          + retCount);
    } catch (Exception e) {
      System.out.println(ValueStrings.processingError
          + e.getStackTrace());
    }
    
    long endTime = System.currentTimeMillis();
    
    System.out.println(ValueStrings.timeTakenNotif1 + (endTime - startTime)
        + ValueStrings.timeTakenNotif2);
  }
}
