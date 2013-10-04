
package cs.ttu.vvclass.activities;

/**
 * Class to keep Pair of objects of two different types
 * Used as a wrapper to pass parameters to FactorizationTask 
 * @param <FIRST> the type(class) of the first object
 * @param <SECOND> the type(class) of the seconds object
 * @author Evgenii Balai
 * @author Cong Pu
 */
public class Pair<FIRST, SECOND> {

    public final FIRST first;
    public final SECOND second;

    /**Constructor**/
    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }
}