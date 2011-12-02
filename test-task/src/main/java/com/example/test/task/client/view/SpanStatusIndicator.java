package com.example.test.task.client.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class SpanStatusIndicator implements StatusIndicator {
	
	Label status=new Label();
	
	public SpanStatusIndicator() {
		super();
		RootPanel.get("statusIndicator").add(status);
	}

	public void setInfoStatus(String message) {
		status.setText("INFO: "+message);
	}

	public void setWarnStatus(String message) {
		status.setText("WARN: "+message);
	}

	public void setErrorStatus(String message) {
		status.setText("ERROR: "+message);
	}

	public void clear() {
		status.setText("");
	}

}
