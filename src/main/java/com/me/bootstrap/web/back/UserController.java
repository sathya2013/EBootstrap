package com.me.bootstrap.web.back;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.entity.User;
import com.me.bootstrap.exception.ExistedException;
import com.me.bootstrap.service.OrgnizationService;
import com.me.bootstrap.service.UserService;
import com.me.bootstrap.web.Servlets;




@Controller
@RequestMapping("/management/user")
public class UserController {
  

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgnizationService orgnizationService;
	
	@ModelAttribute("preloadUser")
	public User getOne(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			User user = userService.get(id);
			user.setOrgnization(null);
			return user;
		}
		return null;
	}
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
}
