package com.example.test.task.client.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * Status indicator impelmentation. As a siple span
 * 
 * @author Ilya Sviridov
 *
 */
public class SpanStatusIndicator implements StatusIndicator {
	/**
	 * Lable object
	 */
	Label status=new Label();
	
	/**
	 * Constructor
	 */
	public SpanStatusIndicator() {
		super();
		RootPanel.get("statusIndicator").add(status);
	}
	
	/**
	 * Info message
	 */
	public void setInfoStatus(String message) {
		status.setText("INFO: "+message);
	}
	
	/**
	 * Warn message
	 */
	public void setWarnStatus(String message) {
		status.setText("WARN: "+message);
	}

	/**
	 * Error message
	 */
	public void setErrorStatus(String message) {
		status.setText("ERROR: "+message);
	}
	
	/**
	 * Clears state
	 */
	public void clear() {
		status.setText("");
	}

}
