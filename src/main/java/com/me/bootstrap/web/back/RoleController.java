package com.me.bootstrap.web.back;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.Module;
import com.me.bootstrap.entity.Permission;
import com.me.bootstrap.entity.Role;
import com.me.bootstrap.entity.RolePermission;
import com.me.bootstrap.service.ModuleService;
import com.me.bootstrap.service.RoleService;
import com.me.bootstrap.web.Servlets;

@Controller
@RequestMapping("/management/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value="/list.do")
	public String listUser(@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap){
		
		Map<String, String[]> params = Servlets.getParameterValuesMap(request, BootstrapConstants.SEARCH_PREFIX);
		Page<Role> pagedList = roleService.findPage(params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		return "/role/roleList";
	}
	
	@ModelAttribute("preloadRole")
	public Role getOne(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			Role role = roleService.get(id);
			return role;
		}
		return null;
	}
	
	
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	public String preUpdate(Long id,HttpServletRequest request)
	{
		Role role =roleService.get(id);
		request.setAttribute("role", role);
		return "/role/update";
	}
	
	
	@RequestMapping(value="/save.do",method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("preloadRole") Role role)
	{
		ModelAndView modelAndView =new ModelAndView("redirect:list.do");
		roleService.save(role);
		return modelAndView;
	}
	
	@RequestMapping(value="/precreate.do",method=RequestMethod.GET)
	public String create(HttpServletRequest request){
		return "/role/create";
	}
	
	@RequestMapping(value="/create.do",method=RequestMethod.POST)
	public String saveCreate(Role role,HttpServletRequest request)
	{
		roleService.save(role);
		return "redirect:list.do";
	}
	
	/**
	 * 进入角色权限分配页面
	 * @return
	 */
	@RequestMapping(value="initrolepermission.do",method=RequestMethod.GET)
	public String initRolePermission(Long id,HttpServletRequest request)
	{
		Role role =roleService.get(id);
		Set<Permission> selectedPermission =new HashSet<Permission>();
		Set<RolePermission> rolePermissions =role.getRolePermissions();
		for(RolePermission rp:rolePermissions)
		{
			selectedPermission.add(rp.getPermission());
		}
		List<Module> allModules =moduleService.findAll();
		request.setAttribute("selectedPermission", selectedPermission);
		request.setAttribute("allModules", allModules);
		return "/role/initAssignPermission";
	}
	
	
	
	
}
