package values;

/**
 * @author Sriram
 * @version 1.0
 * 
 * ValueStrings interface consists of the all the common Error ValueStrings.
 * This separation of error ValueStrings makes it easy for developers/technical writers to change this later or
 * localize to another language*
 */

public interface ValueStrings {
  /*
   * ValueString messages for class utils/PrimeUtils
   */
  String outBoundError = "The upper bound is either negative or above the expected range";
  String primeBoundError1 = "Prime numbers computed only until";
  String sumBoundError = ", can't find sum until ";
  String primeBoundError2 = ", can't check if prime for number ";
  String memoryBoundError = "The input bound is very large. Please provide a lower number";

  /*
   * ValueString messages for class E10
   */
  String sumUntilNotif = "The sum of prime numbers until ";
  String timeTakenNotif1 = "This program took ";
  String timeTakenNotif2 = "ms to run";

  /*
   * ValueString message for class E41
   */
  String noPandigitalNotif = "There's no pandigital numbers which are prime";
  String longestPandigitalNotif = "The longest pan digital number is ";
  String processingError = "There's an error in the processing";
  String pdigitalPrimeSetNotif = "The total number of sets that can be formed by combining digits which are prime numbers: ";
}
