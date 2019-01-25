package com.demo.mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MockitoOrderTest {

	private List first, second;
	private InOrder singleMock, multiMocks;

	@Before
	public void startup() {
		first = mock(List.class);
		singleMock = inOrder(first);

		second = mock(List.class);
		multiMocks = inOrder(first, second);
	}

	@Test
	public void test() {
		first.add("11111");
		first.add("22222");
		singleMock.verify(first).add("11111");
		singleMock.verify(first).add("22222");

		second.add("33333");
		second.add("44444");
		multiMocks.verify(first).add("11111");
		multiMocks.verify(first).add("22222");
		multiMocks.verify(second).add("33333");
		multiMocks.verify(second).add("44444");
	}

}
