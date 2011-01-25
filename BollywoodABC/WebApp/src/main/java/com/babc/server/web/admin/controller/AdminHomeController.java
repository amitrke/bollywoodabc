package com.babc.server.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public String home() {
		return "admin.homepage";
	}

}
