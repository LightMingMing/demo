package com.demo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

public class MockitoAnswerTest {

	@Test
	public void answerTest() {
		Echo echo = Mockito.mock(Echo.class);

		Mockito.doAnswer(AdditionalAnswers.returnsFirstArg()).when(echo).echo(Mockito.any());

		Assert.assertEquals(echo.echo("firstArg", "secondArg", "thirdArg"), "firstArg");
	}

	@Test
	public void answerTest2() {
		Echo echo = new Echo();
		Echo spyEcho = Mockito.spy(echo);

		Mockito.when(spyEcho.echo(Mockito.anyString())).thenAnswer(AdditionalAnswers.returnsFirstArg());
		Mockito.when(spyEcho.echo(Mockito.anyString())).thenAnswer(AdditionalAnswers.returnsFirstArg());

		Assert.assertEquals(spyEcho.echo("firstArg"), "firstArg");
		Assert.assertEquals(spyEcho.echo("secondArg"), "secondArg");
		Assert.assertEquals(spyEcho.echo("thirdArg"), "thirdArg");
	}

	@Test
	public void callbackTest() {
		Echo echo = Mockito.mock(Echo.class);
		Mockito.when(echo.echo(Mockito.any())).thenAnswer(i -> {
			return i.getArgument(0);
		});
		Mockito.doAnswer(AdditionalAnswers.returnsFirstArg()).when(echo).echo(Mockito.any());

		Assert.assertEquals(echo.echo("firstArg", "secondArg", "thirdArg"), "firstArg");

	}

	public class Echo {

		public String echo(String... echos) {
			return "echo";
		}
	}
}
