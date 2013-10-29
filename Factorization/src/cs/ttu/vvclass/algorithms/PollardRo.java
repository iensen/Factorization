package cs.ttu.vvclass.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


/**
 * The class implements Pollard-Ro probabilistic algorithm for number factorization.
 * The resulting factorization is not guaranteed to be correct . 
 * Some numbers in the resulting factorization may 
 * not be prime due to the wrong output of BigInteger.isProbablyPrime()
 * occurring with  probability 1/2^certainty
 * @author Evgenii Balai
 * @author Cong Pu
 */
public class PollardRo implements FactorizationAlgo{

    // constant for certainty in prime testing
	static final int certainty=100;
	
	
	/**
	 * Run pollard-ro algorithm
	 * @param number to be factored
	 * @return list of factors
	 */
	@Override
	public ArrayList<BigInteger> run(BigInteger number) {
		
		
		
		ArrayList<BigInteger> result =new ArrayList<BigInteger>();
		// avoid infinite loop:
		if (number.equals(BigInteger.ONE)) {
			result.add(BigInteger.ONE);
			return result;
		}
		// if number is a prime, it is the only divisor:
		if(number.isProbablePrime(certainty)) {
			result.add(number);
		}
		else {
		
	    BigInteger value = null;
		//save current number 
		BigInteger buffer = null;
		BigInteger bufferNextIndex = null;
		
		BigInteger index=BigInteger.ONE;
		final int minIteration=10;
		int maxIteration=Math.max(minIteration,NumericUtils.SquareRootFloor(NumericUtils.SquareRootFloor(number)).intValue());
		int curIteration=0;
		BigInteger shift = null;
		while(true) {
		  ++curIteration;
		  if(curIteration==maxIteration || curIteration==1) // reinitialize the algorithm!
		  {
			  value=getRandomNumber(number);
			  curIteration=1;
			  buffer=value;
			  bufferNextIndex=BigInteger.valueOf(2);
			  index=BigInteger.ONE;
			  shift=getConstantShift(number);
		  }
		  index=index.add(BigInteger.ONE);
		  value=(value.multiply(value).subtract(BigInteger.ONE).add(shift)).mod(number);
		  BigInteger gcd=NumericUtils.GCD(buffer.subtract(value).abs(),number);
		  if(!gcd.equals(number) && !gcd.equals(BigInteger.ONE)) {
	       //we have non-primitive divisor
		   result.addAll(run(gcd));
		   result.addAll(run(number.divide(gcd)));
		   break;
		  }
		  if(index.equals(bufferNextIndex)) {
			  buffer=value;
			  bufferNextIndex=bufferNextIndex.multiply(BigInteger.valueOf(2));
		  }
		}
		}
		return result;
	}
	
	private BigInteger getRandomNumber(BigInteger upperLimit) {
		
		BigInteger value;
		//sample a random value uniformly distributed between 0 and n-1
		Random randomGen=new Random(new Date().getTime());
		do {
		    value = new BigInteger(upperLimit.bitLength(), randomGen);
		} while (value.compareTo(upperLimit)>=0);	
		
		return value;

	}
	
	private BigInteger getConstantShift(BigInteger upperLimit) {
		
		BigInteger value;
		
		// the values 0 and upperLimit-2 must be avoided!
		
		//sample a random value uniformly distributed between 0 and n-1
		Random randomGen=new Random(new Date().getTime());
		do {
		    value = new BigInteger(upperLimit.bitLength(), randomGen);
		} while (value.compareTo(upperLimit)>=0 && !value.equals(BigInteger.ZERO) && !value.equals(BigInteger.valueOf(2)));	
		
		return value;
	}
	
	

	

}
