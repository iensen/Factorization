package com.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
/**
 * The class implements Fermat method for number factorization.
 * The resulting factorization is not guaranteed to be correct . 
 * Some numbers in the resulting factorization may 
 * not be prime due to the wrong output of BigInteger.isProbablyPrime()
 * occurring with  probability 1/2^certainty
 */
public class FermatMethod implements FactorizationAlgo {
	
	
	// constant for certainty in prime testing
	static final int certainty=100;
	
	/**
	 * Run Fermat algorithm
	 * 
	 * @param number
	 *            to be factored
	 * @return list of factors
	 */

	@Override
	public ArrayList<BigInteger> run(BigInteger number) {

		ArrayList<BigInteger> result = new ArrayList<BigInteger>();

		if (number.isProbablePrime(certainty)) {
			result.add(number);
		}
		// if the number is even, we divide it by two and call the algorithm
		// recursively
		else if (number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
			result.add(BigInteger.valueOf(2));
			result.addAll(run(number.shiftRight(1)));
		} else {
			// we search for two numbers A and B such that A^2-B^2=N.
			BigInteger A = SquareRootFloor(number);
			BigInteger B = A.multiply(A).subtract(number);
			while (!isPerfectSquare(B)) {
				A = A.add(BigInteger.ONE);
				B = A.multiply(A).subtract(number);
			}
			
			// given A^2-B^2=N, we can write N=(A-B)*(A+B), so A-B is a divisor
			result.addAll(run(A.subtract(B)));
			result.addAll(run(A.add(B)));
		}
		return result;
	}

	/**
	 * Check if the number is a perfect square
	 */
	private boolean isPerfectSquare(BigInteger number) {
		BigInteger floorRoot = SquareRootFloor(number);
		return floorRoot.multiply(floorRoot).equals(number);
	}

	/**
	 * Compute square root of number n. If the number is not a perfect square,
	 * return the floor(sqrt(number))
	 * 
	 * @param number
	 *            - input number
	 * @return BigInteger
	 */
	private BigInteger SquareRootFloor(BigInteger number) {
		BigInteger low = BigInteger.ZERO;
		BigInteger high = number.add(BigInteger.ONE);

		// use binary search to find two numbers low and high, such that
		// low*low<=number<high*high
		while (high.subtract(low).compareTo(BigInteger.ONE) > 0) {
			BigInteger mid = low.add(high).shiftRight(1);
			if (mid.multiply(mid).compareTo(number) <= 0) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}

}
