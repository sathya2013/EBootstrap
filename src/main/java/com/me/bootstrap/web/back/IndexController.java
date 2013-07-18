package com.me.bootstrap.web.back;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.Module;
import com.me.bootstrap.entity.UserRole;
import com.me.bootstrap.service.ModuleService;
import com.me.bootstrap.service.UserRoleService;
import com.me.bootstrap.shiro.ShiroDbRealm;



@Controller
@RequestMapping("/management/index")
public class IndexController {


	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@RequiresAuthentication 
	@RequestMapping(value="main.do",method=RequestMethod.GET)
	public String index(HttpServletRequest request)
	{
		
		Subject subject = SecurityUtils.getSubject();
		ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser)subject.getPrincipal();
		
		//User user = userService.get(shiroUser.getLoginName());
//		List<UserRole> userRoles = userRoleService.find(shiroUser.getId());
//		shiroUser.getUser().setUserRoles(new HashSet<UserRole>(userRoles));
//		
//		Module menuModule = getMenuModule(new HashSet<UserRole>(userRoles));

		// 这个是放入user还是shiroUser呢？
		request.getSession().setAttribute(BootstrapConstants.LOGIN_USER, shiroUser.getUser());
	//	request.setAttribute("menuModule", menuModule);
	    return "/index";
	}
	
	private Module getMenuModule(Set<UserRole> userRoles) {
		// 得到所有权限
		Set<String> permissionSet = Sets.newHashSet();
		for (UserRole userRole : userRoles) {
			Set<String> tmp = Sets.newHashSet(userRole.getRole().getPermissionList());
			permissionSet.addAll(tmp);
		}
		
		// 组装菜单,只获取二级菜单
		//Module rootModule = moduleService.get(1L);
		Module rootModule = moduleService.getTree();
		List<Module> list1 = Lists.newArrayList();
		for (Module m1 : rootModule.getChildren()) {
			// 只加入拥有view权限的Module
			if (permissionSet.contains(m1.getSn() + ":" + BootstrapConstants.OPERATION_VIEW)) {
				List<Module> list2 = Lists.newArrayList();
				for (Module m2 : m1.getChildren()) {
					if (permissionSet.contains(m2.getSn() + ":" + BootstrapConstants.OPERATION_VIEW)) {
						list2.add(m2);
					}
				}
				m1.setChildren(new HashSet<Module>(list2));
				list1.add(m1);
			}
		}
		rootModule.setChildren(new HashSet<Module>(list1));
		
		return rootModule;
	}
	
	@RequestMapping(value="ui.do",method=RequestMethod.GET)
	public String ui(HttpServletRequest request)
	{
		return "/system/ui";
	}
	
	@RequestMapping(value="center.do",method=RequestMethod.GET)
	public String dashboard(HttpServletRequest request)
	{
		return "/frame/center";
	}
	
	@RequestMapping(value="/container.do",method=RequestMethod.GET)
	public String container(HttpServletRequest request)
	{
		return "/frame/container";
	}
	
	@RequestMapping(value="/nav.do",method=RequestMethod.GET)
	public String top(HttpServletRequest request)
	{
		return "/frame/navigate";
	}
	
}
