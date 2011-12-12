package com.example.test.task.client;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Test util class for stubbing {@link AsyncCallback}.
 * 
 * @author Ilya Sviridov
 * 
 */
public class AsyncMockStubber {
	/**
	 * Stubbing success responce
	 * 
	 * @param data
	 *            to retun
	 * @return stabber
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Stubber callSuccessWith(final T data) {
		return Mockito.doAnswer(new Answer() {
			@SuppressWarnings("unchecked")
			public T answer(InvocationOnMock invocationOnMock) throws Throwable {
				final Object[] args = invocationOnMock.getArguments();
				((AsyncCallback) args[args.length - 1]).onSuccess(data);
				return null;
			}
		});
	}

	/**
	 * Stubbing error responce
	 * 
	 * @param throwable
	 *            error
	 * @return stabber
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Stubber callErrorWith(final Throwable throwable) {
		return Mockito.doAnswer(new Answer() {
			public T answer(InvocationOnMock invocationOnMock) throws Throwable {
				final Object[] args = invocationOnMock.getArguments();
				((AsyncCallback) args[args.length - 1]).onFailure(throwable);
				return null;
			}
		});
	}
}
