package com.me.bootstrap.web.back;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.me.bootstrap.entity.Module;
import com.me.bootstrap.model.Node;
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
	
	@RequestMapping(value="/subnode.do",method={RequestMethod.GET,RequestMethod.POST})
	public String subModules(Long id,HttpServletRequest request,HttpServletResponse response)
	{
		Module module = moduleService.get(id);
		Set<Module> modules = module.getChildren();
		List<Node> nodeList =new ArrayList<Node>();
		for(Module mos:modules)
		{
			Node node =new Node();
			node.setId(mos.getId());
			node.setText(mos.getName());
			if(CollectionUtils.isNotEmpty(mos.getChildren()))
			{	
				node.setState("closed");
			}else {
				node.getAttributes().put("url", mos.getUrl());
			}
			nodeList.add(expendSubNode(mos,node));
		}
		RenderUtil.renderJson(response, nodeList, "encoding:UTF-8");
		return  null;
	}
	
	
	private Node expendSubNode(Module parent,Node node)
	{
		if(CollectionUtils.isEmpty(parent.getChildren()))
		{
			return node;
		}
		for(Module module:parent.getChildren())
		{
			Node sub =new Node();
			sub.setId(module.getId());
			sub.setText(module.getName());
			if(CollectionUtils.isNotEmpty(module.getChildren()))
			{	
				sub.setState("closed");
			}else {
				sub.getAttributes().put("url", module.getUrl());
			}
			node.getChildren().add(expendSubNode(module,sub));
		}
		return node;
	}
	
	@RequestMapping(value ="/loadmodule",method={RequestMethod.GET,RequestMethod.POST})
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
			map.put("operation", "<a href=''>操作</a>");
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
}
