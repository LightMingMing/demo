package com.demo.mockito;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

public class MockitoExceptionTest {

	@SuppressWarnings("rawtypes")
	private List list;

	@Rule
	public TestRule exceptExceptionRule = (s, d) -> {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				boolean catched = false;
				try {
					s.evaluate();
				} catch (RuntimeException e) {
					catched = true;
				}
				if (!catched) {
					throw new Exception("No catch excepted exception");
				}
			}
		};
	};

	@Before
	public void startup() {
		list = mock(List.class);
	}

	@Test
	public void withRunException() {
		doThrow(new RuntimeException()).when(list).clear();
		list.clear();
		list.clear();
		Mockito.verify(list, Mockito.atLeastOnce()).clear();
	}
}
