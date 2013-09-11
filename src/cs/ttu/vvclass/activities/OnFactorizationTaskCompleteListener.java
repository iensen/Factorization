package cs.ttu.vvclass.activities;

/**
 * The interface for callback used for communication between factorization
 * threads and the GUI thread
 * When a factorization thread is complete, it calls onTaskComplete with 
 * the argument containing the factorization result 
 * @author Evgenii Balai
 * @author Cong Pu
 */
public interface OnFactorizationTaskCompleteListener {
	public void onTaskComplete(String result);
}
