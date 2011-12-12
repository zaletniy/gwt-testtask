package com.example.test.task.shared;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testCompareStringString() {
		assertEquals(0,Utils.compare("", ""));
		
		String nullString=null;
		assertEquals(0,Utils.compare(nullString, nullString));
		assertEquals(-1,Utils.compare(nullString, ""));
	}

	@Test
	public void testCompareDateDate() {
		Date date=new Date(); 
		assertEquals(0,Utils.compare(date, date));
		date=null;
		assertEquals(0,Utils.compare(date, date));
		assertEquals(-1,Utils.compare(date, new Date()));
		assertEquals(1,Utils.compare(new Date(),date));
	}

}
