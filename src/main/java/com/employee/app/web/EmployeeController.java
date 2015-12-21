package com.employee.app.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.app.model.auth.Gender;
import com.employee.app.model.auth.Role;
import com.employee.app.model.auth.User;
import com.employee.app.model.auth.UserRole;
import com.employee.app.repository.UserRepository;
import com.employee.app.vo.UserRoleVO;
import com.employee.app.vo.UserVO;

@RestController
public class EmployeeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public @ResponseBody UserVO addEmployee(@RequestBody UserVO emp) {

		User user = new User();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		user.setEmailAddress(emp.getEmail());
		user.setFullName(emp.getFirstname());
		if (Gender.MALE.equals(emp.getGender())) {
			user.setGender(Gender.MALE);
		} else {
			user.setGender(Gender.FEMALE);
		}
		user.setUsername(emp.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		if (emp.getUserRoles() != null) {
			for (UserRoleVO userRoleVO : emp.getUserRoles()) {
				UserRole userRole = new UserRole();
				if (userRoleVO.getRoleName().equals(Role.ADMIN)) {
					userRole.setRole(Role.ADMIN);
				} else {
					userRole.setRole(Role.USER);
				}
				userRole.setUser(user);
				userRoles.add(userRole);
			}
		}
		user.setUserRoles(userRoles);
		userRepository.save(user);
		return new UserVO(user);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
	public String getEmployeeList() {
		return "Has access to employees.";
	}

	@RequestMapping(value = "/employee/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadTemplate(@RequestParam(required = false) final MultipartFile templateDataFile) {
		return "File uploaded sucess fully";
	}

}
