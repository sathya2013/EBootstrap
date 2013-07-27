<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="${ctx}/js/jqueryeasyui/themes/bootstrap/easyui.css" rel="stylesheet">
<link href="${ctx}/js/jqueryeasyui/themes/icon.css" rel="stylesheet">
<script src="${ctx}/js/jqueryeasyui/jquery.easyui.min.js"></script>	
<script src="${ctx}/js/custom/module.js"></script>	
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>模块列表</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
					  <table class="table table-striped table-bordered bootstrap-datatable datatable" id="moduleList">
						
					  </table>  					           
					</div>
				</div>
				
           </div>


