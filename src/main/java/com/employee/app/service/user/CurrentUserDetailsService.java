package com.employee.app.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.app.model.auth.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
	private final UserService userService;

	@Autowired
	public CurrentUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.debug("Authenticating user with email={}", email.replaceFirst("@.*", "@***"));
		User user = null;
		try {
			user = userService.getUserByEmailAddress(email);
			if (user == null) {
				new UsernameNotFoundException(String.format("User with email=%s was not found", email));
			}

		} catch (Exception e) {
			new UsernameNotFoundException(String.format("User with email=%s was not found", email));
		}
		
		 
		return new CurrentUser(user);
	}

}