package com.example.test.task.client.view;

/**
 * Status indicator. Needed for informing user what happens.
 * 
 * @author Ilya Sviridov
 *
 */
public interface StatusIndicator {
	/**
	 * Info message
	 */
	void setInfoStatus(String message);
	
	/**
	 * Warn message
	 */
	void setWarnStatus(String message);
	
	/**
	 * Error message
	 */
	void setErrorStatus(String message);
	
	/**
	 * Clears state
	 */
	void clear();
}
