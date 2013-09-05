package com.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
/**
 * The class implements Pollard-Ro probabilistic algorithm for number factorization.
 * The resulting factorization is not guaranteed to be correct . 
 * Some numbers in the resulting factorization may 
 * not be prime due to the wrong output of BigInteger.isProbablyPrime()
 * occurring with  probability 1/2^certainty
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
	    // if number is a prime, it is the only divisor:
		if(number.isProbablePrime(certainty)) {
			result.add(number);
		}
		else {
		BigInteger value;
		//sample a random value uniformly distributed between 0 and n-1
		Random randomGen=new Random();
		do {
		    value = new BigInteger(number.bitLength(), randomGen);
		} while (value.compareTo(number)>=0);		
		//save current number 
		BigInteger buffer=value;
	
		BigInteger bufferNextIndex=BigInteger.valueOf(2);;
		
		BigInteger index=BigInteger.ONE;
		while(true) {
		  index=index.add(BigInteger.ONE);
		  value=(value.multiply(value).subtract(BigInteger.ONE)).mod(number);
		  BigInteger gcd=GCD(buffer.subtract(value).abs(),number);
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
	
	/**
	 * Compute greatest common divisor of two numbers
	 * @param number1
	 * @param number2
	 * @return greatest common divisor
	 */
	private BigInteger GCD(BigInteger number1, BigInteger number2) {
		if(number2.equals(BigInteger.ZERO))
				return number1;
			else
				return GCD (number2, number1.mod(number2));
	}
	
	

}
