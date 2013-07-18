<%@ tag pageEncoding="UTF-8" import="java.util.Enumeration,org.apache.commons.lang3.StringUtils"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ attribute name="excludePage" type="java.lang.Boolean" required="false" rtexprvalue="true"%>
<%
Enumeration names = request.getAttributeNames();
while(names.hasMoreElements()) {
	String name = (String) names.nextElement();
	if(StringUtils.startsWith(name, "search_") || StringUtils.startsWith(name, "page_sort")
			 || (("page".equals(name)||"page_size".equals(name)) && (excludePage==null || !excludePage))
		) {
		String[] values = (String[]) request.getAttribute(name);
		for(String value : values) {
			if(StringUtils.isNotBlank(value)) {
%>
<input type="hidden" name="<%=name%>" value="<%=value%>"/>	
<%
			}
		}
	}
}
%>