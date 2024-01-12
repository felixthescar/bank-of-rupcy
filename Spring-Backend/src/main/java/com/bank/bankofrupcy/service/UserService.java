package com.bank.bankofrupcy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.bankofrupcy.model.User;
import com.bank.bankofrupcy.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Find all Users
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		return ResponseEntity.ok(allUsers);
	}

	// Find User By Id
	public ResponseEntity<User> findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Modify or Add User
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		User newUser = userRepository.save(user);
		return ResponseEntity.ok(newUser);
	}

	// Delete User
	public ResponseEntity<String> deleteUser(Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok("User Deleted Successfully");
	}

}