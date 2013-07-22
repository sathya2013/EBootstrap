<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<div>
		<ul class="breadcrumb">
			<li>
				<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">分配角色</a>
			</li>
		</ul>
	</div>

    <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 分配角色表单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${ctx}/management/user/saveassign.do" method="post">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="username">登陆名</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="username" name="username" type="text" value="${user.username}" readonly="readonly">
								   <input type="hidden" name="id" id="id" value="${user.id}"/>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="realname">真实名字</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="realname" name="realname" type="text" value="${user.realname}" readonly="readonly">
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="role">角色分配</label>
								<div class="controls">
								  <select id="role" name="role" multiple data-rel="chosen" style="width:350px">
								  <c:forEach items="${roles}" var="role">								     
								       <option value="${role.id}" 
								         <c:forEach items="${user.userRoles}" var="ur">
								            <c:if test="${role.id eq ur.role.id}">selected</c:if>
								         </c:forEach>
								       >${role.name}</option>								
								  </c:forEach>
								  </select>
								</div>
							  </div>
							  
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">保存</button>
								<button class="btn" onclick="javascript:window.history.go(-1);">取消</button>
							  </div>
							</fieldset>
						  </form>
					</div>
				</div>
			</div>