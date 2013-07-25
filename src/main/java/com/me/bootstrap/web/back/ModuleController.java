package com.me.bootstrap.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/management/module")
public class ModuleController {

	@RequestMapping(value="/index.do",method=RequestMethod.GET)
	public String index(HttpServletRequest request)
	{
		
		
		
		return "/module/moduleList";
	}
}
