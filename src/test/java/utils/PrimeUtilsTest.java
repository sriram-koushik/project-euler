/**
 * @author Sriram
 * @version 1.0
 * Test file for class utils/PrimeUtils
 */
package utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import values.ValueStrings;

public class PrimeUtilsTest {
  /*
   * Mock Class for basic mock testing of a Prime number
   */
  class PrimeMock {
    int val;
    boolean isPrime;

    PrimeMock(int valInp, boolean isPrimeInp) {
      val = valInp;
      isPrime = isPrimeInp;
    }
  }
  /*
   * Mock class for testing with the ground truth of sum until N
   */
  class PrimeSumMock {
    int val;
    long sum;

    PrimeSumMock(int valInp, long sumInp) {
      val = valInp;
      sum = sumInp;
    }
  }

  /*
   * Streams declaration for checking console output
   */
  private final ByteArrayOutputStream outStreamByte = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errStreamByte = new ByteArrayOutputStream();
  private final PrintStream outStream = System.out;
  private final PrintStream errStream = System.err;

  /*
   * Initialize the various streams
   */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outStreamByte));
    System.setErr(new PrintStream(errStreamByte));
  }

  /*
   * Restore the various streams
   */
  @After
  public void restoreStreams() {
    System.setOut(outStream);
    System.setErr(errStream);
  }

  /*
   * Populate the ground truth for some prime numbers.
   * Using Boundary Value Analysis
   */
  final List<PrimeMock> primeCheckList = new ArrayList<PrimeMock>(
    Arrays.asList(
      new PrimeMock(100, false),
      new PrimeMock(0, false),
      new PrimeMock(2, true),
      new PrimeMock(65537, true),
      new PrimeMock(2000000, false)
      )
    );
  /*
   * Populate the ground truth for some prime sum.
   * Generate sum from proven prime values
   */
  final List<PrimeSumMock> primeSumList = new ArrayList<PrimeSumMock>(
    Arrays.asList(
      new PrimeSumMock(10, 17),
      new PrimeSumMock(11, 17),
      new PrimeSumMock(230, 5117)
      )
    );
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /*
   * Various tests in instantiation of the main class
   */
  @Test
  public void type() throws Exception {
    assertThat(PrimeUtils.class, notNullValue());
  }

  @Test
  public void instantiate1() throws Exception {
    int upperBound = Integer.MAX_VALUE + 1;
    new PrimeUtils(upperBound);
    assertEquals(ValueStrings.outBoundError + "\r\n", errStreamByte.toString());
  }

  @Test
  public void instantiate2() throws Exception {
    int upperBound = Integer.MAX_VALUE;
    new PrimeUtils(upperBound);
    assertEquals(ValueStrings.outBoundError + "\r\n", errStreamByte.toString());
  }

  @Test
  public void instantiate3() throws Exception {
    int upperBound = -1;
    PrimeUtils primeUtilsObj = new PrimeUtils(upperBound);
    assertThat(primeUtilsObj.getprimeList().size(), (equalTo(0)));
  }

  @Test
  public void instantiate4() throws Exception {
    int upperBound = 100000;
    PrimeUtils primeUtilsObj = new PrimeUtils(upperBound);
    assertThat(primeUtilsObj.getprimeList().size(), is(not(equalTo(0))));
  }

  /*
   * Various tests for verification of isPrime method
   */
  @Test
  public void isPrimeTest() throws Exception {
    // A positive test where the ranges are correct
    for (PrimeMock primeMockObj : primeCheckList) {
      PrimeUtils primeUtilsObj = new PrimeUtils(primeMockObj.val + 1);
      assertEquals(primeUtilsObj.isPrime(primeMockObj.val), primeMockObj.isPrime);
    }
  }

  @Test
  public void isPrimeNegTest1() throws Exception {
    // A negative test where the ranges are incorrect
    thrown.expect(IllegalArgumentException.class);
    PrimeUtils primeUtilsObj = new PrimeUtils(0);
    for (PrimeMock primeMockObj : primeCheckList) {
      primeUtilsObj.isPrime(primeMockObj.val);
    }

  }

  @Test
  public void isPrimeNegTest2() throws Exception {
    // A negative test where a negative range is specified
    thrown.expect(IllegalArgumentException.class);
    PrimeUtils primeUtilsObj = new PrimeUtils(-10);
    for (PrimeMock primeMockObj : primeCheckList) {
      primeUtilsObj.isPrime(primeMockObj.val);
    }
  }

  /*
   * Various tests for sum of primes
   */
  @Test
  public void primeSumTest() throws Exception {
    // A positive test where the ranges are correct
    for (PrimeSumMock primeSumMockObj : primeSumList) {
      PrimeUtils primeUtilsObj = new PrimeUtils(primeSumMockObj.val + 1);
      assertEquals(primeUtilsObj.sumRange(primeSumMockObj.val), primeSumMockObj.sum);
    }
  }

  @Test
  public void primeSumNegTest() throws Exception {
    // A negative test where the ranges are incorrect
    thrown.expect(IllegalArgumentException.class);
    PrimeUtils primeUtilsObj = new PrimeUtils(0);
    for (PrimeMock primeMockObj : primeCheckList) {
      primeUtilsObj.sumRange(primeMockObj.val);
    }
  }

}
