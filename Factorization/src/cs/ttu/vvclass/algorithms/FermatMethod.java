package cs.ttu.vvclass.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;



/**
 * The class implements Fermat method for number factorization. The resulting
 * factorization is not guaranteed to be correct . Some numbers in the resulting
 * factorization may not be prime due to the wrong output of
 * BigInteger.isProbablyPrime() occurring with probability 1/2^certainty
 * @author Evgenii Balai
 * @author Cong Pu
 */
public class FermatMethod implements FactorizationAlgo {

	// constant for certainty in prime testing
	static final int certainty = 100;

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
		// avoid infinite loop:
		if (number.equals(BigInteger.ONE)) {
			result.add(BigInteger.ONE);
			return result;
		}
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
			BigInteger A = NumericUtils.SquareRootFloor(number);
			BigInteger B = A.multiply(A).subtract(number);
			
			while (!isPerfectSquare(B)) {
				A = A.add(BigInteger.ONE);
				B = A.multiply(A).subtract(number);
			}
            B=NumericUtils.SquareRootFloor(B);
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
		BigInteger floorRoot = NumericUtils.SquareRootFloor(number);
		return floorRoot.multiply(floorRoot).equals(number);
	}



}
