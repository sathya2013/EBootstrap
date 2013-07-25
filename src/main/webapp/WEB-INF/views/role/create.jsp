<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<div>
		<ul class="breadcrumb">
			<li>
				<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">新增角色</a>
			</li>
		</ul>
	</div>

    <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 新增角色表单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${ctx}/management/role/create.do" method="post">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="username">角色名</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="name" name="name" type="text" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="realname">角色描述</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="description" name="description" type="text" >
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