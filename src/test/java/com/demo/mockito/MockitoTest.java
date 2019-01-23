package com.demo.mockito;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.demo.repository.UserRepository;

@SuppressWarnings("rawtypes")
public class MockitoTest {

	@Test
	public void countTest() {
		Class<UserRepository> clazz = UserRepository.class;
		UserRepository userRepository = Mockito.mock(clazz);
		Mockito.when(userRepository.count()).thenReturn(200);
		Assert.assertEquals(userRepository.count(), 200);
	}

	@Test
	public void returnValueDependentOnMethodParameter() {
		Class<UserRepository> clazz = UserRepository.class;
		UserRepository userRepository = Mockito.mock(clazz);
		Mockito.when(userRepository.getName(Mockito.anyString())).thenReturn("1", "2", "3", "4");
		Assert.assertEquals(userRepository.getName("1"), "1");
		Assert.assertEquals(userRepository.getName("2"), "2");
		Assert.assertEquals(userRepository.getName("3"), "3");
		Assert.assertEquals(userRepository.getName("4"), "4");
		Assert.assertEquals(userRepository.getName("5"), "4");
		Assert.assertEquals(userRepository.getName("6"), "4");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testLinkedListSpyWrong() {
		List<String> list = new LinkedList<>();
		List<String> mockList = Mockito.spy(list);
		Mockito.when(mockList.get(0)).thenReturn("100");
		// Mockito.doReturn("100").when(mockList).get(0);
		Assert.assertEquals(mockList.get(0), "100");
	}

	@Test
	public void testLinkedListSpyCorrect() {
		List<String> list = new LinkedList<>();
		List<String> mockList = Mockito.spy(list);
		// // Have to use doReturn().when().get() for stubbing
		Mockito.doReturn(10).when(mockList).size();
		Mockito.doReturn("123").when(mockList).get(0);
		Assert.assertEquals(mockList.size(), 10);
		Assert.assertEquals(mockList.get(0), "123");
	}

	@Test
	// Verify the calls on the mock objects
	public void testVerify() {
		Class<UserRepository> clazz = UserRepository.class;
		UserRepository userRepository = Mockito.mock(clazz);
		Mockito.when(userRepository.count()).thenReturn(123);
		Mockito.when(userRepository.getName("MingMing")).thenReturn("ZhaoMingMing");

		userRepository.count();
		userRepository.getName("MingMing");
		userRepository.getName("MingMing");
		userRepository.getName("MingMing");

		Mockito.verify(userRepository).count();
		// Equals Mockito.verify(userRepository, Mockito.time(1)).count();
		Mockito.verify(userRepository, Mockito.atLeast(1)).getName("MingMing");
		Mockito.verify(userRepository, Mockito.atLeast(2)).getName("MingMing");

		//
	}

	@Test
	public void argumentMatchers() {
		List mockedList = Mockito.mock(List.class);

		Mockito.when(mockedList.get(Mockito.anyInt())).thenReturn("Element");

		Assert.assertEquals(mockedList.get(1), "Element");

		Mockito.verify(mockedList).get(1);
		Mockito.verify(mockedList).get(Mockito.anyInt());
	}

}
