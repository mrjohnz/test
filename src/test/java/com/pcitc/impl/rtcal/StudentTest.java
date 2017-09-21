package com.pcitc.impl.rtcal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StudentTest extends TestCase {
	
	public StudentTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StudentTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testStudent() {
		assertTrue(true);
	}
}
