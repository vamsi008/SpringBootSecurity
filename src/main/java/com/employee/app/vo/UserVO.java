package com.employee.app.vo;

import java.util.ArrayList;
import java.util.List;

import com.employee.app.model.auth.User;
import com.employee.app.model.auth.UserRole;

public class UserVO {

	private long id;
	private String username;
	private String firstname;
	private String lastname;

	private String email;
	private String gender;
	private String enabled;

	private List<UserRoleVO> userRoles;

	private String manager;

	public UserVO() {

	}

	public UserVO(User user) {

		this.id = user.getId();
		this.username = user.getUsername();
		this.firstname = user.getFullName();
		this.email = user.getEmailAddress();
		if (user.getManager() != null) {
			this.manager = user.getManager().getUsername();
		}
		if (user.getUserRoles() != null) {
			userRoles = new ArrayList<UserRoleVO>();
			for (UserRole userRole : user.getUserRoles()) {

				userRoles.add(new UserRoleVO(userRole));

			}
		}

	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public List<UserRoleVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleVO> userRoles) {
		this.userRoles = userRoles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
