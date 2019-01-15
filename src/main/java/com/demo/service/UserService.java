package com.demo.service;

import com.demo.repository.UserRepository;

public class UserService {

	private UserRepository userRepository;

	public int count() {
		return userRepository.count();
	}

	public String getName(String id) {
		return userRepository.getName(id);
	}
}
