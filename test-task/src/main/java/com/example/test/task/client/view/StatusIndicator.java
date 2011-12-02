package com.example.test.task.client.view;

public interface StatusIndicator {
	void setInfoStatus(String message);
	void setWarnStatus(String message);
	void setErrorStatus(String message);
	void clear();
}
