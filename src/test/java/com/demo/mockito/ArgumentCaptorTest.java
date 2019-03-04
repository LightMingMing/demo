package com.demo.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ArgumentCaptorTest {

	@Captor
	public ArgumentCaptor<String> captor;

	private Foo foo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		foo = Mockito.mock(Foo.class);
		Mockito.when(foo.bar(captor.capture())).thenReturn("hello");
	}

	@Test
	public void captorTest() {
		Assert.assertEquals("hello", foo.bar("123"));
		Assert.assertEquals("123", captor.getValue());

		Assert.assertEquals("hello", foo.bar("456"));
		Assert.assertEquals("456", captor.getValue());

		Assert.assertEquals("123", captor.getAllValues().get(0));
		Assert.assertEquals("456", captor.getAllValues().get(1));
	}

	interface Foo {

		String bar(String hello);
	}
}
