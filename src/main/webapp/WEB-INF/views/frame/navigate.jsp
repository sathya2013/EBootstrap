<!-- left menu starts -->
<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<ul class="nav nav-tabs nav-stacked main-menu">
	<li class="nav-header hidden-tablet">系统管理</li>
	<li><a class="ajax-link" href="${ctx}/management/index/center.do" target="frame_center"><i class="icon-home"></i><span class="hidden-tablet">我的工作台</span></a></li>
	<li><a class="ajax-link" href="${ctx}/management/user/list.do" target="frame_center"><i class="icon-edit"></i><span class="hidden-tablet"> 用户管理</span></a></li>
	<li><a class="ajax-link" href="${ctx}/management/org/list.do" target="frame_center"><i class="icon-eye-open"></i><span class="hidden-tablet"> 部门管理</span></a></li>
	<li><a class="ajax-link" href="${ctx}/management/role/list.do" target="frame_center"><i class="icon-list-alt"></i><span class="hidden-tablet"> 角色管理</span></a></li>
	<li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
	<li><a class="ajax-link" href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
	<li class="nav-header hidden-tablet">Sample Section</li>
	<li><a class="ajax-link" href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
	<li><a class="ajax-link" href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
	<li><a class="ajax-link" href="grid.html"><i class="icon-th"></i><span class="hidden-tablet"> Grid</span></a></li>
	<li><a class="ajax-link" href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
	<li><a href="tour.html"><i class="icon-globe"></i><span class="hidden-tablet"> Tour</span></a></li>
	<li><a class="ajax-link" href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
	<li><a href="error.html"><i class="icon-ban-circle"></i><span class="hidden-tablet"> Error Page</span></a></li>
	<li><a href="login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
</ul>
<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>


