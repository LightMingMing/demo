package com.demo.bdd;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class BDDMockitoTest {

	@Test
	public void simpleBDDTest() {
		Echo echo = mock(Echo.class);
		// given
		given(echo.echo("echo")).willReturn("123");
		// when
		assertEquals("123", echo.echo("echo"));
		// then
		then(echo).should(times(1)).echo("echo");
	}

	@Test
	public void willReturnGivenWillNotCallSpyObjectRealMethodTest() {
		Echo echo = spy(new Echo());
		// given
		willReturn("echo").given(echo).throwException(); // not call real method
		// when
		assertEquals("echo", echo.throwException());
		// then
		then(echo).should(times(1)).throwException();
	}

	@Test(expected = RuntimeException.class)
	public void givenWillReturnWillCallSpyObjectRealMethodTest() {
		Echo echo = spy(new Echo());
		// given
		given(echo.throwException()).willReturn("echo"); // call real method
		// when
		assertEquals("echo", echo.throwException());
		// then
		then(echo).should(times(1)).throwException();
	}

	@Test
	public void doReturnWhenWillNotCallSpyObjectRealMethodTest() {
		Echo echo = spy(new Echo());

		doReturn("echo").when(echo).throwException();// not call real method

		assertEquals("echo", echo.throwException());

		verify(echo, times(1)).throwException();
	}

	@Test(expected = RuntimeException.class)
	public void whenThenWillCallSpyObjectRealMethodTest() {
		Echo echo = spy(new Echo());

		when(echo.throwException()).thenReturn("echo"); // call real method

		assertEquals("echo", echo.throwException());

		verify(echo, times(1)).throwException();
	}

	public class Echo {

		public String echo(String... echos) {
			return "echo";
		}

		public String throwException() {
			throw new RuntimeException();
		}
	}
}
