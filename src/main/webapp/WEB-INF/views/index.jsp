<%@include file="include_inc.jsp"%>
<body>	
<jsp:include page="/WEB-INF/views/frame/head.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row-fluid">
	    <iframe name="container" style="position:absolute;width:100%;height:100%;" frameborder="0" src="${ctx}/management/index/container.do"></iframe>
	</div>
</div>	
</body>
</html>
