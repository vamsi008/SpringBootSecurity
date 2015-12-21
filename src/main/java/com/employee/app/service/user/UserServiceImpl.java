package com.employee.app.service.user;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.model.auth.User;
import com.employee.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User getUserByEmailAddress(String email) {
		return userRepository.findOneByEmailAddress(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll();
	}

}
