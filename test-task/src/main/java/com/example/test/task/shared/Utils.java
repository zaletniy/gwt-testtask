package com.example.test.task.shared;

import java.util.Date;

/**
 * Util class.
 * 
 * @author Ilya Sviridov
 *
 */
public class Utils {
	
	/**
	 * Compares strings. Allows nulls
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static int compare(String o1, String o2) {
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null) {
			return -1;
		} else
			return o1.compareTo(o2);
	}

	/**
	 * Compares dates. Allows nulls
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static int compare(Date o1, Date o2) {
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null) {
			return -1;
		} else if (o2 == null) {
			return 1;
		} else
			return o1.compareTo(o2);
	}
}
