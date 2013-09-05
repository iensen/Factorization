package com.example.multithread;

import android.util.Log;
/**
 * A wrapper for the shared variable counting the number of running tasks
 */
public class TaskCounter {

	private int runningTasksCount;
	private int recievedTasksCount=0;
    private int expectedTasksCount;
    
    
	public TaskCounter(int expectedTasksCount) {
		this.runningTasksCount = 0;
		this.expectedTasksCount=expectedTasksCount;
		
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
		if(runningTasksCount==0 && expectedTasksCount==recievedTasksCount) {
			this.notify();
		}
	}
	
	public int getRunningTasksCount() {
		return runningTasksCount;
	}
	
	public int getRecievedTasksCount() {
		return recievedTasksCount;
	}
	
	
	
}
