package com.peishujuan.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peishujuan.hgshop.service.UserService;

@Controller
public class UserController {

	@Reference(timeout=500000,version="1.0.0")
	UserService userService;
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(String username,String password) {
		System.out.println("username " + username + " password " + password);
		if(userService.login(username, password)) {
			return "redirect:/";
		}else {
			return "login";
		}
		
	}
}
