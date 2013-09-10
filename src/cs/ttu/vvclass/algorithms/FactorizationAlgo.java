package cs.ttu.vvclass.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Interface of strategy pattern for factorization.
 */
public interface FactorizationAlgo {
  /**
   * do factorization
   * @param number to be factored 
   * @return list of all prime divisors, representing the factorization
   */
  public ArrayList<BigInteger> run(BigInteger number); 
}
