<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="${ctx}/js/jqueryeasyui/themes/bootstrap/easyui.css" rel="stylesheet">
<link href="${ctx}/js/jqueryeasyui/themes/icon.css" rel="stylesheet">
<script src="${ctx}/js/jqueryeasyui/jquery.easyui.min.js"></script>	
<script src="${ctx}/js/custom/module.js"></script>	

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">模块管理</a>
					</li>
				</ul>
			</div>
				
			<div class="container-fluid">
            <div class="row-fluid">
                <div class="span3">
                    <div class="well">
                      
                      
                      
                    </div><!--/.well -->
                  </div><!--/span-->
                  
                  <div class="span9" id="mainContent">
                      <div class="row-fluid" id="moduleList">
                              
                      </div>
                      <div class="span8" id="reporter"></div>
                  </div>
            </div>
        </div>


