package cs.ttu.vvclass.activities;

import java.math.BigInteger;
import java.util.ArrayList;

import cs.ttu.vvclass.algorithms.FactorizationAlgo;
import android.os.AsyncTask;

/**
 * The class implements AsyncTasks(Threads) which are used for factoring a
 * number The input parameter is of the form Pair<BigInteger,
 * FactorizationAlgo>, where BigInteger is the number which will be factored and
 * FactorizationAlgo is the algorithm selected by a user
 */
class FactorizationTask extends
		AsyncTask<Pair<BigInteger, FactorizationAlgo>, Void, Void> {

	/**
	 * Call back used for passing the result back to GUI thread
	 */
	private OnFactorizationTaskCompleteListener callback;
	/**
	 * The link to the shared variable counting running threads
	 */
	private TaskCounter taskCounter;
	/**
	 * The flag indicates whether taskComplete method was called at least once
	 */
	boolean taskComplete = false;
	/**
	 * the string storing the factorization result it only takes non-null value
	 * after doInBackground method is complete
	 */
	private String factorizationResult;

	/**
	 * The constructor
	 * 
	 * @param callback
	 * @param taskCounter
	 */
	FactorizationTask(OnFactorizationTaskCompleteListener callback,
			TaskCounter taskCounter) {
		this.callback = callback;
		this.taskCounter = taskCounter;
	}

	/**
	 * The method to run the factorization
	 * 
	 * @param arg0
	 *            - sequence of input parameters In our case it will always
	 *            consists of one element
	 */
	@Override
	protected Void doInBackground(Pair<BigInteger, FactorizationAlgo>... arg0) {

		// First, increase the number of running threads in the shared variable

		synchronized (taskCounter) {
			taskCounter.addTask("Thread " + Thread.currentThread().getId()
					+ ". Starting factorization: " + arg0[0].first);
		}

		// DO THE FACTORIZATION
		factorizationResult = getMultiplicationString(arg0[0].second
				.run(arg0[0].first));
		// call task complete in order to pass the result into GUI THREAD
		completeTask();
		return null;
	}

	/**
	 * Call this method when the task is complete in order to update the shared
	 * counter and send the result to the GUI thread!
	 */
	public void completeTask() {
		synchronized (taskCounter) {
			// if the task was completed once, we do not need to complete it
			// again:)
			if (!taskComplete) {
				// reduce the number of running threads in the shared variable
				// by one
				taskCounter.removeTask("Thread "
						+ Thread.currentThread().getId()
						+ ". factorization found: " + factorizationResult);
				this.callback.onTaskComplete(factorizationResult);
				// update the flag:
				taskComplete = true;
			}
		}

	}

	/**
	 * Get a string to be displayed as a factorization result
	 * 
	 * @param factors
	 *            list of factors
	 * @return factors separated by '*'
	 */
	private String getMultiplicationString(ArrayList<BigInteger> factors) {
		StringBuilder result = new StringBuilder();
		boolean firstFactor = true;
		for (BigInteger factor : factors) {
			if (!firstFactor) {
				result.append("*");
			}
			result.append(factor.toString());
			firstFactor = false;
		}
		return result.toString();
	}

	/**
	 * Cancel handler
	 */
	@Override
	protected void onCancelled() {
		// if the task was cancelled, pass 'null' to the main thread
		this.callback.onTaskComplete(null);
	}
}
