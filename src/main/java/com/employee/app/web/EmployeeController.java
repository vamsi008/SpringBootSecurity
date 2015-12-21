package com.employee.app.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.employee.app.service.user.CurrentUser;
import com.employee.app.stringcontants.Status;
import com.employee.app.util.ResultBuilderUtil;
import com.employee.app.vo.Result;
import com.employee.app.vo.UserRoleVO;
import com.employee.app.vo.UserVO;

@RestController
public class EmployeeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public @ResponseBody Result addEmployee(@RequestBody UserVO emp) {

		User user = new User();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		user.setEmailAddress(emp.getEmail());
		user.setFullName(emp.getFirstname());
		if (Gender.MALE.equals(emp.getGender())) {
			user.setGender(Gender.MALE);
		} else {
			user.setGender(Gender.FEMALE);
		}
		user.setManager(userRepository.findOneByEmailAddress(emp.getManager()));
		user.setUsername(emp.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		if (emp.getUserRoles() != null) {
			for (UserRoleVO userRoleVO : emp.getUserRoles()) {
				UserRole userRole = new UserRole();

				if (Role.ADMIN.toString().equals(userRoleVO.getRoleName())) {
					userRole.setRole(Role.ADMIN);
				} else {
					if (Role.MANAGER.toString().equals(userRoleVO.getRoleName())) {
						userRole.setRole(Role.MANAGER);
					} else {
						userRole.setRole(Role.USER);
					}
				}
				userRole.setUser(user);
				userRoles.add(userRole);
			}
		}
		user.setUserRoles(userRoles);
		userRepository.save(user);
		List<String> sample = new ArrayList<String>();
		return ResultBuilderUtil.buildResult(Status.CREATE, sample, new UserVO(user));
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody Result getEmployeeList() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		List<User> userList = null;
		List<UserVO> userVOList = null;
		if (currentUser.getRole().equals(Role.MANAGER)) {
			userList = userRepository.findByManager(userRepository.findOne(currentUser.getId()));
		} else {

			userList = userRepository.findAll();

		}

		if (userList != null) {
			userVOList = new ArrayList<UserVO>();
			for (User user : userList) {
				userVOList.add(new UserVO(user));
			}
		}

		return ResultBuilderUtil.buildResult(Status.GET, null, userVOList);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public @ResponseBody Result getEmployee() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

		User user = userRepository.findOne(currentUser.getId());
		user = userRepository.findOneByEmailAddress(user.getEmailAddress());

		if (user != null) {
			return ResultBuilderUtil.buildResult(Status.GET, null, new UserVO(user));
		}

		return null;
	}

	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/employee/{mail}", method = RequestMethod.GET)
	public @ResponseBody Result getEmployeeUnderManager(@RequestParam String mail) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		User user = userRepository.findOne(currentUser.getId());

		if (user != null) {
			userRepository.findByEmailAddressAndManager(mail, user);
			return ResultBuilderUtil.buildResult(Status.GET, null, new UserVO(user));
		}

		return null;
	}

	@RequestMapping(value = "/employee/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadTemplate(@RequestParam(required = false) final MultipartFile templateDataFile) {
		return "File uploaded sucess fully";
	}

}
