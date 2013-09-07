package com.example.multithread;

import android.util.Log;
/**
 * A wrapper for the shared variable counting the number of running tasks
 */
public class TaskCounter {

	private int runningTasksCount;
	private int recievedTasksCount=0;
    
    
	public TaskCounter(int expectedTasksCount) {
		this.runningTasksCount = 0;

		
	}
	
	public void addTask(String logMessage)
	{
		recievedTasksCount++;
		runningTasksCount++;
		Log.v("", logMessage);
	}
	
	public void removeTask(String logMessage)
	{
		Log.v("", logMessage);
		runningTasksCount--;	
	}
	
	public int getRunningTasksCount() {
		return runningTasksCount;
	}
	
	public int getRecievedTasksCount() {
		return recievedTasksCount;
	}
	
	
	
}
