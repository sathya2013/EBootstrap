package com.me.bootstrap.web.back;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.me.bootstrap.entity.Module;
import com.me.bootstrap.entity.Permission;
import com.me.bootstrap.service.ModuleService;
import com.me.bootstrap.util.RenderUtil;

@Controller
@RequestMapping("/management/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;

	@RequestMapping(value="/index.do",method=RequestMethod.GET)
	public String index(HttpServletRequest request)
	{
		List<Module> modules =moduleService.findAll();
		request.setAttribute("modules", modules);
		return "/module/moduleList";
	}
	
	@RequestMapping(value ="/loadmodule.do",method={RequestMethod.GET,RequestMethod.POST})
	public String loadRootModule(HttpServletResponse response)
	{
		
		List<Module> modules =moduleService.findRootModules();
		Map<String,Object> jsonMap =Maps.newHashMap();
		List<Map<String,Object>> modelList =Lists.newArrayList();
		for(Module module:modules)
		{
			Map<String, Object> map =Maps.newHashMap();
			map.put("id", module.getId());
			map.put("name", module.getName());
			map.put("priority", module.getPriority());
			map.put("sn", module.getSn());
			map.put("url", module.getUrl());
			map.put("parent", module.getParent()!=null?module.getParent().getName():"");
			map.put("addChild", "<a href='javascript:addChildRoot("+module.getId()+")'><font color='black'>添加子模块<font></a>");
			if(CollectionUtils.isNotEmpty(module.getChildren()))
			{
				map.put("state", "closed");
			}
			map.put("operation", "<a href='javascript:void(0);' onclick='javascript:addSubModule("+module.getId()+");'>添加子模块</a>");
			modelList.add(map);
		}
		jsonMap.put("total", CollectionUtils.isEmpty(modelList)?0:modelList.size());
		jsonMap.put("rows", modelList);
		RenderUtil.renderJson(response, jsonMap, "encoding:UTF-8");
		return null;
	}
	
	
	@RequestMapping(value="/submodule.do",method={RequestMethod.GET,RequestMethod.POST})
	public String loadSubModule(HttpServletRequest request,HttpServletResponse response)
	{
		String id =request.getParameter("id");
		List<Module> modules =moduleService.findByParentId(Long.valueOf(id));
		List<Map<String,Object>> modelList =Lists.newArrayList();
		for(Module module:modules)
		{
			Map<String, Object> map =Maps.newHashMap();
			map.put("id", module.getId());
			map.put("name", module.getName());
			map.put("priority", module.getPriority());
			map.put("sn", module.getSn());
			map.put("url", module.getUrl());
			if(CollectionUtils.isNotEmpty(module.getChildren()))
			{
				map.put("state", "closed");
			}
			map.put("parent", module.getParent()!=null?module.getParent().getName():"");
			map.put("addChild", "<a href='javascript:addChildRoot("+module.getId()+")'><font color='black'>添加子模块<font></a>");
			map.put("operation", "<a href=''>操作</a>");
			modelList.add(map);
		}
		RenderUtil.renderJson(response, modelList, "encoding:UTF-8");
		return null;
	}
	
	@RequestMapping(value="/savemodule.do",method=RequestMethod.POST)
	public String saveModule(Module module,HttpServletResponse response)
	{
		int result =1;
		try {
			List<Permission> permissions =module.getPermissions();
			for(Permission permission:permissions)
			{
				permission.setModule(module);
			}
			moduleService.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			result =0;
		}
		RenderUtil.renderHtml(response, "<script type='text/javascript' type='language'>parent.window.callback("+result+")</script>", "encoding:UTF-8");
		return null;
	}
	
	@RequestMapping(value="/savesubmodule.do",method=RequestMethod.POST)
	public String saveSubModule(Module module,HttpServletResponse response){
		 
		int result =1;
		try {
			
			Module parentModule =moduleService.get(module.getParent().getId());
			module.setParent(parentModule);
			for(Permission permission:module.getPermissions())
			{
				permission.setModule(module);
			}
			moduleService.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			result=0;
		}
		RenderUtil.renderHtml(response, "<script type='text/javascript' type='language'>parent.window.callback("+result+")</script>", "encoding:UTF-8");
		return null;
	}
	
	@RequestMapping(value="/loadmodulename.do",method=RequestMethod.GET)
	public String getModuleName(Long id,HttpServletResponse response)
	{
		
		Module module =moduleService.get(id);
		Map<String, Object> map =Maps.newHashMap();
		map.put("name", module.getName());
		RenderUtil.renderJson(response,map , "encoding:UTF-8");
		return null;
	}
}
