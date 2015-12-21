package com.employee.app.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employee.app.model.auth.Role;
import com.employee.app.service.user.CurrentUser;

@Controller
public class ApplicationController {


	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/tiles", method=RequestMethod.GET)
	public String getTiles1() {
	    return "site.homepage";
	}

	@RequestMapping("/user/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/home")
	public String gethome() {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CurrentUser currentUser = (CurrentUser)authentication.getPrincipal();
	
		if (currentUser.getRole().equals(Role.ADMIN)) {
			return "adminHome";
		}
		return "userHome";

	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

}
