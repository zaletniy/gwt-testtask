package com.example.test.task.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * The callback for tests
 * 
 * @author Ilya Sviridov
 *
 * @param <T>
 */
public class TestCallBack<T> implements AsyncCallback<T> {
	T result;
	Throwable error;
	public void onFailure(Throwable caught) {
		this.error=caught;
	}

	public void onSuccess(T result) {
		this.result=result;
	}

	public T getResult() {
		return result;
	}

	public Throwable getError() {
		return error;
	}
}
