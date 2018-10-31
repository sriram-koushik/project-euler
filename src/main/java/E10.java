/**
 * Solution for problem #10 (https://projecteuler.net/problem=10)
 * @author Sriram
 * @version 1.0
 */

import utils.PrimeUtils;
import values.ValueStrings;

/**
 * Class E10 is the class which consists of the main method.
 */
class E10 {
  /**
   * Main static method for the class.
   * 
   * @param args Command line arguments for the main method
   */
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      // Maximum bound until which prime numbers are computed
      final int totalBound = 2000000;
      // Sum to be computed until the sumLimit bound
      final int sumLimit = 2000000;
      // Create a PrimeUtils Object
      PrimeUtils pu = new PrimeUtils(totalBound);
      System.out.println(ValueStrings.sumUntilNotif + sumLimit
        + " = " + pu.sumRange(sumLimit));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    long endTime = System.currentTimeMillis();

    System.out.println(ValueStrings.timeTakenNotif1 + (endTime - startTime)
      + ValueStrings.timeTakenNotif2);
  }
}
