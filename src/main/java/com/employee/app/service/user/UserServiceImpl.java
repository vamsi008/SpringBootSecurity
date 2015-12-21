package com.employee.app.service.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.app.model.auth.Role;
import com.employee.app.model.auth.User;
import com.employee.app.model.auth.UserRole;
import com.employee.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

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

	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setEmailAddress(form.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		Role role = form.getRole();

		List<UserRole> roleList = new ArrayList<UserRole>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		user.setUserRoles(roleList);
		return userRepository.save(user);
	}

}
