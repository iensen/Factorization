package cs.ttu.vvclass.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
/**
 * The class implements primitive division algorithm for number factorization
 */
public class PrimitiveDivision implements FactorizationAlgo {

	/**
	 * Run primitive division algorithm
	 * @param number to be factored
	 * @return list of factors
	 */
	@Override
	public ArrayList<BigInteger> run(BigInteger number) {
		
	    boolean  squareRootReached=false;
	    BigInteger divisor=BigInteger.valueOf(2);
	    ArrayList<BigInteger> factorization=new ArrayList<BigInteger>();
	    
		// for one, no prime divisor exists:
		if (number.equals(BigInteger.ONE)) {
			factorization.add(BigInteger.ONE);
			return factorization;
		}
	    // iterate through the divisors starting from two and
	    // ending by the square root (rounded down) of the number 
	    BigInteger originalNumber=number;
	    while(!squareRootReached) {
	       //check if we have a divisor and add it to factorization
	       while(number.remainder(divisor).equals(BigInteger.ZERO)) {
	    	   factorization.add(divisor);
	    	   number=number.divide(divisor);
	       }
	       divisor=divisor.add(BigInteger.ONE);
	       // check for reaching the square root
	       // compute the square:
	       BigInteger square=divisor.multiply(divisor);
	       if(square.compareTo(originalNumber)>=0) {
	    	   squareRootReached=true;
	       }
	    }
	    
	    // we may have a prime number which is greater than square root left
	    if(number.compareTo(BigInteger.ONE)>0) {
	    	factorization.add(number);
	    }
	    return factorization;
	}

}
