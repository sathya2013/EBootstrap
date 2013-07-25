package com.me.bootstrap.web.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.model.Info;

@Controller
public class IndexController {

	@RequestMapping(value={ "", "/index.jspx" })
	public String index(HttpServletRequest request) {
		String ctx="/EBootstrap";
		request.setAttribute(BootstrapConstants.CTX, ctx != null ? ctx : "");
		request.setAttribute(BootstrapConstants.FILES, ctx.concat("/").concat("files/1/bluewise/_files"));
		request.setAttribute(BootstrapConstants.FORE, ctx != null ? ctx + BootstrapConstants.FORE_PATH : BootstrapConstants.FORE_PATH);
		List<Info> infoList =new ArrayList<Info>();
		for(int i=0;i<10;i++)
		{
			Info info =new Info();
			info.setTitle("这是一个测试，测试"+i);
			info.setUrl("www.baidu.com");
			infoList.add(info);
		}
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("infoList", infoList);
		return "/1/bluewise/index.html";
	}
	
	@RequestMapping(value="/test.jspx",method=RequestMethod.GET)
	@ResponseBody
	public Object test(HttpServletRequest request)
	{
		Map<String, Object> map =Maps.newHashMap();
		map.put("status", 1);
		map.put("message", "这是一个测试!");
		return map;
	}
}
