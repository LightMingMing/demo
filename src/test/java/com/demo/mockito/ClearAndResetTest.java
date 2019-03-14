package com.demo.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

import org.junit.Test;

public class ClearAndResetTest {

	@Test
	public void testClearInvocations() {
		Echo echo = spy(new Echo());
		echo.setEcho("echo");
		assertEquals("echo", echo.getEcho());

		clearInvocations(echo);
		assertEquals("echo", echo.getEcho());
	}

	@Test
	public void testReset() {
		Echo echo = spy(new Echo());
		echo.setEcho("echo");
		assertEquals("echo", echo.getEcho());

		reset(echo);
		assertEquals("echo", echo.getEcho());
	}

	static class Echo {

		private String echo;

		public void setEcho(String echo) {
			this.echo = echo;
		}

		public String getEcho() {
			return echo;
		}
	}
}
