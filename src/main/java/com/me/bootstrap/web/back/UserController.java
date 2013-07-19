package com.me.bootstrap.web.back;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.User;
import com.me.bootstrap.service.UserService;
import com.me.bootstrap.web.Servlets;




@Controller
@RequestMapping("/management/user")
public class UserController {
  

	@Autowired
	private UserService userService;
	
    //1:列表,
    //2:添加
	@RequestMapping(value="/list.do")
	public String listUser(@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap){
		
		Map<String, String[]> params = Servlets.getParameterValuesMap(request, BootstrapConstants.SEARCH_PREFIX);
		Page<User> pagedList = userService.findPage(params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		return "/user/userList";
	}
	
	@RequestMapping(value="/createform.do",method=RequestMethod.GET)
	public String createForm(HttpServletRequest request)
	{
		
		
		return "/user/create";
	}
	
	
}
