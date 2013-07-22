package com.me.bootstrap.web.back;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public String login()
	{
		return "/login";
	}
	
	@RequestMapping(value="/logout",method={RequestMethod.GET,RequestMethod.POST})
	public String logout()
	{
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "/login";
	}
}
