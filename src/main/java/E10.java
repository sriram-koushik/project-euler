/**
 * Solution for problem #10 (https://projecteuler.net/problem=10)
 * @author Sriram
 * @version 1.0
 */

import utils.PrimeUtils;

/**
 * Class E10 is the class which consists of the main method.
 */
class E10 {
  /**
   * Main static method for the class.
   * 
   * @param args
   *            Command line arguments for the main method
   */
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    try {
      // Maximum bound until which prime numbers are computed
      int totalBound = 2000000;
      
      // Sum to be computed until the sumLimit bound
      int sumLimit = 2000000;
      
      // Create a PrimeUtils Object
      PrimeUtils pu = new PrimeUtils(totalBound);
      
      System.out.println("The sum of prime numbers until " + sumLimit
          + " is " + pu.sumRange(sumLimit));
    } catch (Error er) {
      System.out.println(er.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    long endTime = System.currentTimeMillis();
    
    System.out.println("This program took " + (endTime - startTime)
        + "ms to run");
  }
}
