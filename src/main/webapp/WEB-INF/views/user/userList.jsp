<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
     function deleteAssign(id)
     {
		$.get("${ctx}/management/user/deleteassign.do?id="+id, function(result){ 
			alert(result.message);
		});
     }
//-->
</script>
			
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">用户管理</a>
					</li>
				</ul>
			</div>
				
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>用户列表</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
					<!-- 过滤头 -->
						<div class="row-fluid">
						        <form method="post">
		                        <tags:search_params/>	
								<div class="span10">
									<div id="DataTables_Table_0_filter" class="dataTables_filter">
									<label>
									查询:
									<input type="text" aria-controls="DataTables_Table_0">
									</label>
									</div>
								</div>	
								</form>	
								<div class="span2">
								    <a class="btn btn-danger" href="${ctx}/management/user/createform.do">
											<i class="icon-trash icon-white"></i> 
											新增人员
									</a>
								</div>
						</div>
						
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>登录名称</th>
								  <th>真实名字</th>
								  <th>电话</th>
								  <th>所在组织</th>
								  <th>账户状态</th>
								  <th>创建时间</th>
								  <th>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
							 <c:forEach var="bean" varStatus="status" items="${pagedList.content}">
								<tr>
									<td class="center">${bean.username}</td>
									<td class="center">${bean.realname}</td>
									<td class="center">
										${bean.phone}
									</td>
									<td class="center">
										${!empty bean.orgnization?bean.orgnization.name:''}
									</td>
									<td class="center">
										<c:choose>
										     <c:when test="${bean.status eq 'enabled'}">
										        <span class="label label-success">激活</span> 
										     </c:when>
										     <c:otherwise>
										        <span class="label label-important">禁用</span>
										     </c:otherwise>
										</c:choose>
									</td>
									<td class="center"><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td class="center">
										<a class="btn btn-info" href="${ctx}/management/user/update.do?id=${bean.id}">
											<i class="icon-edit icon-white"></i>  
											编辑                                            
										</a>
										<a class="btn btn-danger" href="${ctx}/management/user/preassign.do?id=${bean.id}">
											<i class="icon-trash icon-white"></i> 
											 分配角色
										</a>
										<a class="btn btn-danger" href="#" onclick="javascript:deleteAssign(${bean.id})">
											<i class="icon-trash icon-white"></i> 
											删除角色
										</a>
										<a class="btn btn-danger" href="#">
											<i class="icon-trash icon-white"></i> 
											 删除
										</a>
									</td>
								</tr>
							</c:forEach>
						  </tbody>
					  </table>  
					  <form action="${ctx}/management/user/list.do" method="get" class="ls_page">
							<tags:search_params excludePage="true"/>
						    <tags:pagination pagedList="${pagedList}"/>
					  </form>	          
					</div>
				</div>
           </div>


