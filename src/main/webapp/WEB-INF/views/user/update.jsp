<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<div>
		<ul class="breadcrumb">
			<li>
				<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">新增用户</a>
			</li>
		</ul>
	</div>

    <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 新增用户表单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${ctx}/management/user/save.do" method="post">
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
								<label class="control-label" for="phone">联系电话</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="phone" name="phone" type="text" value="${user.phone}" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="email">用户邮箱</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="email" name="email" type="text" value="${user.email}">
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="status">用户状态</label>
								<div class="controls">
								  <select id="status" name="status">
									<option value="enabled" ${user.status == "enabled" ? 'selected="selected"' : ''}>可用</option>
				                    <option value="disabled" ${user.status == "disabled" ? 'selected="selected"' : ''}>不可用</option>
								  </select>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="orgnization">Modern Select</label>
								<div class="controls">
								  <select id="orgnization" name="orgnization.id" data-rel="chosen">
								  <c:forEach items="${orgnizations}" var="org">
								     <c:if  test="${!empty org.parent}">
								       <option value="${org.id}" <c:if test="${user.orgnization.id eq org.id }">selected</c:if>>${org.name}</option>
								     </c:if>
								  </c:forEach>
								  </select>
								</div>
							  </div>
							  
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">保存</button>
								<button class="btn">取消</button>
							  </div>
							</fieldset>
						  </form>
					</div>
				</div>
			</div>