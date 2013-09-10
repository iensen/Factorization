package cs.ttu.vvclass.activities;

import android.util.Log;

/**
 * A wrapper for the shared variables counting the number of running tasks
 */
public class TaskCounter {

	private int runningTasksCount;
	private int receivedTasksCount = 0;

	/**
	 * Constructor
	 */
	public TaskCounter() {
		this.runningTasksCount = 0;
	}

	/**
	 * Add a task: increase the number of received tasks and the number of the
	 * running asks
	 * 
	 * @param logMessage
	 *            message to be written to the log
	 */
	public void addTask(String logMessage) {
		receivedTasksCount++;
		runningTasksCount++;
		Log.v("", logMessage);
	}

	/**
	 * 
	 * @param logMessage
	 */
	public void removeTask(String logMessage) {
		Log.v("", logMessage);
		runningTasksCount--;
	}


	// Getters:
	
	public int getRunningTasksCount() {
		return runningTasksCount;
	}

	public int getRecievedTasksCount() {
		return receivedTasksCount;
	}

}
