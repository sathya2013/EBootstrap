<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
   function checkAll(inst)
   {
	  var idName =$(inst).attr("name");
	  var prefix =idName.split("_")[0];
	  var $filedSet =$("#"+prefix+"_fieldset");
	  if($(inst).attr("checked"))
	  {
		  $("input[type='checkbox']",$filedSet).each(function(index,node){ 
			  if($(node).attr("id")!='all')
			  {
				  //alert($(node).attr('name')+"---"+$(node).attr('checked'));
				  $(node).attr("checked",true);  
			  }
		  });
	  }else
	  {
		  $("input[type='checkbox']",$filedSet).each(function(index,node){
			  if($(node).attr("id")!='all')
			  {
				  //alert($(node).attr('name')+"---"+$(node).attr('checked'));
				  $(node).attr("checked",false);
			  } 
		  });
	  }
   }
   $(function(){
	   $('#btn_save').click(function(){
		   
		   var roleId =$('#roleId').val();
		   var permissionStr="";
		   $("input[type='checkbox']").each(function(index,node){
			   if($(node).attr("id")!='all'&& $(node).attr("checked")=='checked')
			   {
				     permissionStr +=$(node).val()+",";
			   }
		   });
		   $.post("${ctx}/management/role/saverolepermission.do",{roleId:roleId,permissionStr:permissionStr},function(result){
			   if(result.status==1)
			    {
			    	alert(result.msg);
			    	window.location.href="${ctx}/management/role/list.do";
			    }else
			    {
			    	alert("保存失败!");
			    }
		   });
	   });
   });
//-->
</script>	
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
							
							  <c:forEach items="${allModules}" var="modules">
							   <c:if test="${!empty modules.permissions }">
							    <fieldset id="${modules.id}_fieldset">
							     <div class="control" id="module_${modules.id}">
							       <h5><i class="icon-edit"></i> ${modules.name} <input type="checkbox" id="all" name="${modules.id}_checkall" onclick="javascript:checkAll(this);"/>全选</h5>
							       <c:forEach items="${modules.permissions}" var="permission" varStatus="st">
							          ${permission.name}<input type="checkbox" name="permissions_${permission.id}" id="permissions_${permission.id}" value="${permission.id}"   
							              <c:forEach items="${selectedPermission}" var="selectP">
							                 <c:if test="${selectP.id eq permission.id }"> selected</c:if>
							              </c:forEach>
							          />&nbsp;&nbsp;
							       </c:forEach>
							     </div>
							       </fieldset>
							       <hr>
							     </c:if>
							  </c:forEach>
							<input name="roleId" id="roleId" type="hidden" value="${id}"/>
						  </form>
					</div>
					
					<div class="form-actions">
								<button type="submit" id="btn_save" class="btn btn-primary">保存</button>
								<button class="btn" onclick="javascript:window.history.go(-1);">取消</button>
				    </div>
				</div>
			</div>