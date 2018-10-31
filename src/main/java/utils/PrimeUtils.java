/**
 * PrimeUtils is a class which consists the implementations of the 
 * common Prime number related computations
 * @author Sriram
 * @version 1.0
 */
package utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import values.ValueStrings;

/**
 * Class 'PrimeUtils' is a Utility class.
 * This provides necessary utility functions for prime number
 * computations
 */
public class PrimeUtils {
  // Upper bound until which prime numbers are computed
  private int upperBound;
  /*
   * List to store the prime numbers until the upper bound value.
   * Reason why we persist the numbers is we can use the numbers for other
   * operations such as lookup
   */
  private List<Integer> primeList;
  /*
   * A sieve which denotes whether the number represented by the index is
   * prime or not for 'i' where 0<= i < sieve.length i is prime if and only if
   * sieve[i] = true
   */
  // We use a BitSet as opposed to a boolean array to make the program memory efficient
  private BitSet sieve;

  /**
   * Get method for the list of primes
   * @return The list of primes computed
   */
  public List<Integer> getprimeList()
  {
    return primeList;
  }

  /**
   * Constructor for PrimeUtils.
   * 
   * @param bound the highest value until which prime numbers are computed
   */

  public PrimeUtils(int bound) {
    /*
     * If the total upper bound is 0 or above, we create the list
     * accordingly. If the bound is less than 0, we set the bound to -1 so
     * that that no elements are created in createList()
     */
    if (bound < 0 || bound == Integer.MAX_VALUE) {
      bound = -1;
      System.err.println(ValueStrings.outBoundError);
    }
    upperBound = bound;
    // Function call to create the list of prime numbers
    createList();
  }

  /**
   * Method sumRange is to calculate the sum of prime numbers until the given value.
   * @param bound the value until which sum should be calculated
   * @return the total sum until the given range
   * @throws Exception IllegalArgument Exception is thrown when a value above the range provided
   */
  public long sumRange(int bound) {
    if (bound > upperBound) {
      /*
       * We have stored numbers only until 'upperbound', but we are
       * attempting to compute sum until a number greater than
       * 'upperbound'
       */
      if (upperBound < 0) {
        throw new IllegalArgumentException(
          ValueStrings.primeBoundError1 + 0
            + ValueStrings.sumBoundError + bound);
      } else {
        throw new IllegalArgumentException(
          ValueStrings.primeBoundError1 + upperBound
          + ValueStrings.sumBoundError + bound);
      }
    }
    // The sum might exceed the Integer range, hence using a long variable
    long sum = 0;
    // Compute sum until the given bound
    for (int primeNumber : primeList) {
      if (primeNumber >= bound) {
        break;
      } else {
        sum += primeNumber;
      }
    }
    return sum;
  }

  /**
   * Check if a given number is prime.
   * 
   * @param num is the input number to be tested
   * @return boolean true if prime and false if not
   */
  boolean isPrime(int num) {
    if (num < 0 || num > sieve.length()) {
      /*
       * We are trying to check if a number outside of the sieve range is
       * prime
       */
      if (upperBound < 0) {
        throw new IllegalArgumentException(
          ValueStrings.primeBoundError1 + 0
            + ValueStrings.primeBoundError2 + num);
      } else {
        throw new IllegalArgumentException(
          ValueStrings.primeBoundError1 + upperBound
          + ValueStrings.primeBoundError2 + num);
      }
    }
    return sieve.get(num);
  }

  /**
   * A private method to create the list.
   */
  private void createList() {
    // Initialize list and sieve
    try {
      primeList = new ArrayList<Integer>();
      sieve = new BitSet(upperBound + 1);

    } catch (OutOfMemoryError er) {
      throw new OutOfMemoryError(
        ValueStrings.memoryBoundError);
    }
    /*
     * We know that there are no prime numbers less than a value 1,
     * hence we return immediately
     */
    if (sieve.size() <= 1) {
      return;
    }
    for (int i = 0; i < sieve.size(); i++) {
      sieve.set(i, true);
    }
    // We know numbers 0 and 1 are not prime, hence setting them to false
    sieve.set(0, false);
    sieve.set(1, false);
    /*
     * We perform Sieve of Eratosthenes to fill the
     * sieve(https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
     * We check until the factor product is less than the provided upper bound
     */
    for (int num = 2; num * num <= upperBound; num++) {
      if (sieve.get(num) == true) {
        /*
         * We mark all the multiples of number 'num' to false because
         * they are not prime. Example: For num = 3,6,9... are marked as false
         */
        for (int multiple = num * 2; multiple <= upperBound; multiple += num) {
          sieve.set(multiple, false);
        }
      }
    }
    // Add all the prime numbers to a list
    for (int i = 0; i < sieve.size(); i++) {
      if (sieve.get(i) == true) {
        primeList.add(i);
      }
    }
  }
}
