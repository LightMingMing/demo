package com.demo.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AssertFailDemo {

	@Test
	public void test() {
		try {
			new ThrowException().throwException();
			fail("Expected exception");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}

	static class ThrowException {
		public void throwException() {
			throw new RuntimeException();
		}
	}
}
