package com.demo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoFinalTest {

	@Test
	public void finalTest() {
		FinalEcho finalEcho = Mockito.mock(FinalEcho.class);
		Mockito.when(finalEcho.finalEcho(Mockito.any())).thenReturn("failure");

		Assert.assertNotEquals(finalEcho.finalEcho("1"), "success");
		Assert.assertEquals(finalEcho.finalEcho("1"), "failure");
	}

	public final class FinalEcho {

		public final String finalEcho(String... echo) {
			return "success";
		}
	}
}
