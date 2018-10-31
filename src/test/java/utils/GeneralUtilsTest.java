/**
 * @author Sriram
 * @version 1.0
 * Test file for class utils/GeneralUtils
 */
package utils;

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

public class GeneralUtilsTest {
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
      new PrimeMock(44701 , true),
      new PrimeMock(2000000, false)
      )
    );
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /*
   * Various tests in instantiation of the class
   */
  @Test
  public void type() throws Exception {
    assertThat(GeneralUtils.class, notNullValue());
  }
  
  /*
   * Test if prime numbers are checked correctly 
   */
  @Test
  public void isPrimeTest() throws Exception {
    /*
     * A positive test which also covers negative numbers
     * In the case of a negative number, no exception is thrown,
     * but we return false which is expected behavior
     */
    for (PrimeMock primeMockObj : primeCheckList) {
      assertEquals(GeneralUtils.isPrimeNumber(primeMockObj.val), primeMockObj.isPrime);
    }
  }
  //Test permutations and split

}
