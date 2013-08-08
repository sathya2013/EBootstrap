<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<div>
		<ul class="breadcrumb">
			<li>
				<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">角色权限分配</a>
			</li>
		</ul>
	</div>

    <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>角色权限分配</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${ctx}/management/role/save.do" method="post">
							<fieldset>
							  <c:forEach items="${allModules}" var="modules">
							   <c:if test="${!empty modules.permissions }">
							     <div class="control" id="module_${modules.id}">
							       <h5><i class="icon-edit"></i> ${modules.name}</h5>
							       <c:forEach items="${modules.permissions}" var="permission">
							          ${permission.name}<input type="checkbox" name="permissions[0].id" value="${permission.id}"   
							              <c:forEach items="${selectedPermission}" var="selectP">
							                 <c:if test="${selectP.id eq permission.id }"> selected</c:if>
							              </c:forEach>
							          />&nbsp;&nbsp;
							       </c:forEach>
							     </div>
							     </c:if>
							  </c:forEach>
							</fieldset>
						  </form>
					</div>
				</div>
			</div>