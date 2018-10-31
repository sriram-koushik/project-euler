/**
 * @author Sriram
 * @version 1.0
 * Test file for class E41
 */
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

public class E41Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
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
   * Close the opened streams
   */
  @After
  public void restoreStreams() {
    System.setOut(outStream);
    System.setErr(errStream);
  }

  /*
   * Various tests in instantiation of the main class
   */
  @Test
  public void instantiation1() throws Exception {
    E41.main(new String[] {});
    assertEquals(
      outStreamByte.toString().contains(ValueStrings.longestPandigitalNotif) ||
        outStreamByte.toString().contains(ValueStrings.noPandigitalNotif), true);
    assertEquals(outStreamByte.toString().contains(ValueStrings.timeTakenNotif1), true);
  }

  @Test
  public void instantiation2() throws Exception {
    E41 target = new E41();
    assertThat(target, notNullValue());
  }

  @Test
  public void instantiation3() throws Exception {
    String[] args = new String[] {};
    E41.main(args);
  }

  /*
   * Type checking test for null
   */
  @Test
  public void type() throws Exception {
    assertThat(E41.class, notNullValue());
  }
}
