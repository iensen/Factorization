package cs.ttu.vvclass.algorithms;

import java.math.BigInteger;

public class NumericUtils {
	/**
	 * Compute square root of number n. If the number is not a perfect square,
	 * return the floor(sqrt(number))
	 * 
	 * @param number
	 *            - input number
	 * @return BigInteger
	 */
	public static BigInteger SquareRootFloor(BigInteger number) {
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
	
	/**
	 * Compute greatest common divisor of two numbers
	 * @param number1
	 * @param number2
	 * @return greatest common divisor
	 */
	public static BigInteger GCD(BigInteger number1, BigInteger number2) {
		if(number2.equals(BigInteger.ZERO))
				return number1;
			else
				return GCD (number2, number1.mod(number2));
	}
	
}
