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
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.Role;

import com.me.bootstrap.service.RoleService;
import com.me.bootstrap.web.Servlets;

@Controller
@RequestMapping("/management/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/list.do")
	public String listUser(@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap){
		
		Map<String, String[]> params = Servlets.getParameterValuesMap(request, BootstrapConstants.SEARCH_PREFIX);
		Page<Role> pagedList = roleService.findPage(params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		return "/role/roleList";
	}
	
	
}
