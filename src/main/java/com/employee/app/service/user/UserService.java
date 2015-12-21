package com.employee.app.service.user;

import java.util.Collection;

import com.employee.app.model.auth.User;

public interface UserService {

	User getUserById(long id);

	User getUserByEmailAddress(String email);

	Collection<User> getAllUsers();

	

}
