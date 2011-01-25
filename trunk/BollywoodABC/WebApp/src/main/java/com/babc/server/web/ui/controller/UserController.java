package com.babc.server.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/login.htm", method = RequestMethod.GET)
	public String login(){
		return "ui.disclaimer";
	}
	
}
