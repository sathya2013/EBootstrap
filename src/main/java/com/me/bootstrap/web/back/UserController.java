package com.me.bootstrap.web.back;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Maps;
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.entity.Role;
import com.me.bootstrap.entity.User;
import com.me.bootstrap.entity.UserRole;
import com.me.bootstrap.exception.ExistedException;
import com.me.bootstrap.model.Node;
import com.me.bootstrap.service.OrgnizationService;
import com.me.bootstrap.service.RoleService;
import com.me.bootstrap.service.UserRoleService;
import com.me.bootstrap.service.UserService;
import com.me.bootstrap.web.Servlets;




@Controller
@RequestMapping("/management/user")
public class UserController {
  

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgnizationService orgnizationService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ModelAttribute("preloadUser")
	public User getOne(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			User user = userService.get(id);
			user.setOrgnization(null);
			return user;
		}
		return null;
	}
   
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
		List<Orgnization> orgList =orgnizationService.findAll();
		request.setAttribute("orgnizations", orgList);
		return "/user/create";
	}
	@RequestMapping(value="/create.do",method=RequestMethod.POST)
	public String create(User user,HttpServletRequest request)
	{
		user.setCreateTime(new Date());
		try {
			userService.save(user);
		} catch (ExistedException e) {
			e.printStackTrace();
		}
		return "redirect:list.do";
	}
	
	@RequestMapping(value="/save.do", method=RequestMethod.POST)
	public  ModelAndView update(@ModelAttribute("preloadUser")User user) {
		ModelAndView modelAndView  =new ModelAndView("redirect:list.do");
		userService.update(user);
		return modelAndView;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String preUpdate(Long id, Map<String, Object> map) {
		User user = userService.get(id);
		List<Orgnization> orgList =orgnizationService.findAll();
		map.put("orgnizations", orgList);
		map.put("user", user);
		return "user/update";
	}
	
	@RequestMapping(value="/preassign.do",method=RequestMethod.GET)
	public String preAssign(Long id,ModelMap map)
	{
		User user =userService.get(id);
	    List<Role> roleList = roleService.findAll();
		map.put("user", user);
		map.put("roles", roleList);
		return "user/assignRole";
	}
	
	
	@RequestMapping(value="/saveassign.do",method=RequestMethod.POST)
	public String saveAssign(Long id,Long[]role,HttpServletRequest request)
	{
		User user =userService.get(id);
		user.getUserRoles().clear();
		for(Long roleId:role)
		{
			UserRole userRole =new UserRole();
			Role roles =roleService.get(roleId);
			userRole.setUser(user);
			userRole.setRole(roles);
			user.getUserRoles().add(userRole);
		}
		userService.update(user);
		return "redirect:list.do";
	}
	
	
	@RequestMapping(value="/deleteassign.do",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Object deleteAssign(Long id,HttpServletRequest request)
	{
		Map<String, Object> map =Maps.newHashMap();
		try {
			userRoleService.deleteUserRole(id);
			map.put("status", 1);
			map.put("message", "角色删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/delete.do",method=RequestMethod.GET)
	public Object delete(HttpServletRequest request)
	{
		Map<String,Object> map =Maps.newHashMap();
		Long id =StringUtils.isNotBlank(request.getParameter("id"))?Long.parseLong(request.getParameter("id")):null;
		User user =userService.get(id);
		map.put("status", 1);
		map.put("message", "删除成功!");
		return map;
	}
}
