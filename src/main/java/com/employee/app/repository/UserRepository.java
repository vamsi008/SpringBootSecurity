package com.employee.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.app.model.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 User findOneByEmailAddress(String email);
}
