<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Free HTML5 Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- The styles -->
	<link id="bs-css" href="${ctx}/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="${ctx}/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${ctx}/css/charisma-app.css" rel="stylesheet">
	<link href="${ctx}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='${ctx}/css/fullcalendar.css' rel='stylesheet'>
	<link href='${ctx}/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='${ctx}/css/chosen.css' rel='stylesheet'>
	<link href='${ctx}/css/uniform.default.css' rel='stylesheet'>
	<link href='${ctx}/css/colorbox.css' rel='stylesheet'>
	<link href='${ctx}/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='${ctx}/css/jquery.noty.css' rel='stylesheet'>
	<link href='${ctx}/css/noty_theme_default.css' rel='stylesheet'>
	<link href='${ctx}/css/elfinder.min.css' rel='stylesheet'>
	<link href='${ctx}/css/elfinder.theme.css' rel='stylesheet'>
	<link href='${ctx}/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='${ctx}/css/opa-icons.css' rel='stylesheet'>
	<link href='${ctx}/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="${ctx}/img/favicon.ico">
	
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="${ctx}/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="${ctx}/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="${ctx}/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="${ctx}/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="${ctx}/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="${ctx}/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="${ctx}/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="${ctx}/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="${ctx}/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="${ctx}/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="${ctx}/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="${ctx}/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="${ctx}/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="${ctx}/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='${ctx}/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='${ctx}/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="${ctx}/js/excanvas.js"></script>
	<script src="${ctx}/js/jquery.flot.min.js"></script>
	<script src="${ctx}/js/jquery.flot.pie.min.js"></script>
	<script src="${ctx}/js/jquery.flot.stack.js"></script>
	<script src="${ctx}/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="${ctx}/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="${ctx}/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="${ctx}/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="${ctx}/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="${ctx}/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="${ctx}/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="${ctx}/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="${ctx}/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="${ctx}/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="${ctx}/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="${ctx}/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="${ctx}/js/charisma.js"></script>
	<script type="text/javascript">
	function SetWinHeight(obj) 
	{ 
	  	var win=obj; 
		if (document.getElementById) 
		{ 
			if (win && !window.opera) 
			{ 
			  if (win.contentDocument && win.contentDocument.body.offsetHeight) 
			  {
				  win.height = win.contentDocument.body.offsetHeight; 
			  }
			  else if(win.Document && win.Document.body.scrollHeight)
			  {
				  win.height = win.Document.body.scrollHeight;
			  }
			   
			 } 
		} 
	}
	</script>
</head>