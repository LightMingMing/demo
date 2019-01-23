package com.demo.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.repository.UserRepository;
import com.demo.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

	@Test
	public void countTest() {
		userService.count();
		Mockito.verify(userRepository).count();
	}

	@Test
	public void countTest2() {
		Mockito.when(userRepository.count()).thenReturn(100);
		Assert.assertEquals(100, userService.count());
		Mockito.verify(userRepository).count();
	}
}
