package com.me.bootstrap.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class RenderUtil {
	
	

	/**
	 * encoding前缀
	 */
	private static final String ENCODING_PREFIX = "encoding";
	/**
	 * 不缓存
	 */
	private static final String NOCACHE_PREFIX = "no-cache";
	/**
	 * 默认编码UTF-8
	 */
	private static final String ENCODING_DEFAULT = "UTF-8";
	/**
	 * 默认不缓存
	 */
	private static final boolean NOCACHE_DEFAULT = true;
	
	
	
	/**
	 * 
	* @Title: renderText
	* @Description: 直接输出文本.
	* @param text 文本
	* @param headers 响应头
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response,final String text, final String... headers) {
		render(response,"text/plain", text, headers);
	}

	/**
	 * 
	* @Title: renderHtml
	* @Description: 直接输出HTML.
	* @param html html文本
	* @param headers 响应头
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response,final String html, final String... headers) {
		render(response,"text/html", html, headers);
	}

	/**
	 * 
	* @Title: renderXml
	* @Description: 直接输出XML.
	* @param xml xml内容
	* @param headers 响应头
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response,final String xml, final String... headers) {
		render(response,"text/xml", xml, headers);
	}

	/**
	 * 
	* @Title: renderJson
	* @Description: 直接输出JSON.
	 * 
	 * @param string json字符串.
	 * @param headers 响应头
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response,final Object object, final String... headers) {
		
		JsonMapper mapper =new JsonMapper();
		render(response,"application/json", mapper.toJson(object), headers);
	}
	
	
	


	
	/**
	 * 
	* @Title: render
	* @Description:直接输出内容的简便函数.
	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param contentType 类型
	 * @param content 内容
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final HttpServletResponse response,final String contentType, final String content, final String... headers) {
		if(contentType == null || content == null) {
			throw new NullPointerException("传入的参数不能为空");
		}
		try {
			//分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			if(headers != null) {
				for (String header : headers) {
					String headerName = StringUtils.substringBefore(header, ":");
					String headerValue = StringUtils.substringAfter(header, ":");

					if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
						encoding = headerValue;
					} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
						noCache = Boolean.parseBoolean(headerValue);
					} else
						throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
				}
			}
			//设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			
		}
	}
}
