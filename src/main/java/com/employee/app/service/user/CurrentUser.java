package com.employee.app.service.user;

import org.springframework.security.core.authority.AuthorityUtils;

import com.employee.app.model.auth.Role;
import com.employee.app.model.auth.User;
import com.employee.app.vo.UserVO;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;
	private UserVO userVO;

	public CurrentUser(User user) {
		super(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getUserRoles().get(0).getRole().toString()));
		this.user = user;
		this.userVO = new UserVO(user);
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

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
}
