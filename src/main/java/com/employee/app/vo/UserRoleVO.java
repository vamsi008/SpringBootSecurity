package com.employee.app.vo;

import com.employee.app.model.auth.UserRole;

public class UserRoleVO {

	private long id;

	private String roleName;

	public UserRoleVO() {

	}

	public UserRoleVO(UserRole userRole) {
		roleName = userRole.getRole().toString();
		id = userRole.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
