/**
 * @author Sriram
 * @version 1.0
 * Test file for class E10
 */
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import values.ValueStrings;

public class E10Test {
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
   * Various tests in instantiation of the main class
   */
  @Test
  public void instantiation1() throws Exception {
    E10.main(new String[] {});
    assertEquals(outStreamByte.toString().contains(ValueStrings.sumUntilNotif), true);
    assertEquals(outStreamByte.toString().contains(ValueStrings.timeTakenNotif1), true);
  }

  @Test
  public void instantiation2() throws Exception {
    E10 target = new E10();
    assertThat(target, notNullValue());
  }

  @Test
  public void instantiation3() throws Exception {
    String[] args = new String[] {};
    E10.main(args);
  }
  
  /*
   * Type checking test for null
   */
  @Test
  public void type() throws Exception {
    assertThat(E10.class, notNullValue());
  }
}
