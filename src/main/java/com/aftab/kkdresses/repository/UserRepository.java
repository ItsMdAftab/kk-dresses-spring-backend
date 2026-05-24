package com.aftab.kkdresses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftab.kkdresses.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndPasswordAndRoleIsNotNull(
	        String username,
	        String password
	);
	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndRole(
	        String username,
	        String role
	);
}