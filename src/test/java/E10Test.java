import static org.junit.Assert.*;
import utils.PrimeUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Random;

public class E10Test {
 
    int inputBound = 5; 
    PrimeUtils test = new PrimeUtils(inputBound);

    @Rule
        public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testSumBoundException() throws Exception {
        int boundGreaterThanInputBound = inputBound+1;      
        thrown.expect(IllegalArgumentException.class);      
        test.sumRange(boundGreaterThanInputBound);

    }
    
    @Test
    public void testSumBoundException1() throws Exception { 
        thrown.expect(NegativeArraySizeException.class);        
        new PrimeUtils(Integer.MAX_VALUE);
    }
    
    /*
	@Test
    public void testSumBoundException2() throws Exception { 
        thrown.expect(OutOfMemoryError.class);      
        PrimeUtils test = new PrimeUtils(Integer.MAX_VALUE-1);
        test.sumRange(Integer.MAX_VALUE-1);
    }
    */
    
    @Test
    public void test1() throws Exception{
        
        //Boundary Value Testing
        /*
         * 1)Randomly generate a bound
         * 2)Generate the nextbound by "bound+1"
         * 3)Difference between 1 and 2 should equate to bound if (1) is prime else equate to 0
         */
        int bound = new Random().nextInt(100);
        new PrimeUtils(bound);
        int sumBound = 0;
        for(int i=0;i<bound;i++){
            if(isPrime(i))
                sumBound = sumBound + i;
        }
        
        int nextBound = bound+1;
        int sumNextBound=0;
        for(int i=0;i<nextBound;i++){
            if(isPrime(i))
                sumNextBound = sumNextBound + i;
        }
        
        int differnce=0;
        if(isPrime(bound))
            differnce = bound;
        
        assertTrue((sumNextBound-sumBound) == (differnce));

    }
    
    boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    


}