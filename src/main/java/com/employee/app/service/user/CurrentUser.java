package com.employee.app.service.user;

import org.springframework.security.core.authority.AuthorityUtils;

import com.employee.app.model.auth.Role;
import com.employee.app.model.auth.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;

	public CurrentUser(User user) {
		super(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getUserRoles().get(0).getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getUserRoles().get(0).getRole();
	}

	@Override
	public String toString() {
		return "CurrentUser{" + "user=" + user + "} " + super.toString();
	}
}
