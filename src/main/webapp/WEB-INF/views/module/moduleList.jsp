<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="${ctx}/js/jqueryeasyui/themes/bootstrap/easyui.css" rel="stylesheet">
<link href="${ctx}/js/jqueryeasyui/themes/icon.css" rel="stylesheet">
<script src="${ctx}/js/jqueryeasyui/jquery.easyui.min.js"></script>	
<script src="${ctx}/js/custom/module.js"></script>
<script type="text/javascript">
<!--
$(function(){
	
	/**点击动态新增授权**/
	$("#newpermission").click(function(){
		 var $fieldset =$("#newPermissonInput");
		 var $name =$("input[name=_name]",$fieldset);
		 var $shortName=$("input[name=_shortName]",$fieldset);
		 var $description=$("input[name=_description]",$fieldset);
		 var $toNewPermission = $("div#toNewPermission");
		 var maxId =0;
		 if($("input:last",$toNewPermission).length>0)
		 {
			 maxId= parseInt($("input:last",$toNewPermission).attr("rel"))+1;
		 }
		 $toNewPermission.append($name.val() + '(' + $shortName.val() + ')' + '<input type="checkbox" name="permissions[' + maxId + '].shortName" value="' + $shortName.val() + '" checked="checked" rel="' + maxId + '"/>&nbsp;&nbsp;'); 
	     $toNewPermission.append('<input type="hidden" name="permissions[' + maxId + '].name" value="' + $name.val() + '" rel="' + maxId + '"/>');
	     $name.val("");
	     $shortName.val("");
	     $description.val("");
	});
	
	$("#newsubpermission").click(function(){
		
		 var $fieldset =$("#newSubPermissonInput");
		 var $name =$("input[name=_name]",$fieldset);
		 var $shortName=$("input[name=_shortName]",$fieldset);
		 var $description=$("input[name=_description]",$fieldset);
		 var $toNewPermission = $("div#toNewSubPermission");
		 var maxId =0;
		 if($("input:last",$toNewPermission).length>0)
		 {
			 maxId= parseInt($("input:last",$toNewPermission).attr("rel"))+1;
		 }
		 $toNewPermission.append($name.val() + '(' + $shortName.val() + ')' + '<input type="checkbox" name="permissions[' + maxId + '].shortName" value="' + $shortName.val() + '" checked="checked" rel="' + maxId + '"/>&nbsp;&nbsp;'); 
	     $toNewPermission.append('<input type="hidden" name="permissions[' + maxId + '].name" value="' + $name.val() + '" rel="' + maxId + '"/>');
	     $name.val("");
	     $shortName.val("");
	     $description.val("");
	});
	
	
	$("#newEditpermission").click(function(){
		
		 var $fieldset =$("#newEditPermissonInput");
		 var $name =$("input[name=_name]",$fieldset);
		 var $shortName=$("input[name=_shortName]",$fieldset);
		 var $description=$("input[name=_description]",$fieldset);
		 var $toNewPermission = $("div#toNewEditPermission");
		 var maxId =0;
		 if($("input:last",$toNewPermission).length>0)
		 {
			 maxId= parseInt($("input:last",$toNewPermission).attr("rel"))+1;
		 }
		 $toNewPermission.append($name.val() + '(' + $shortName.val() + ')' + '<input type="checkbox" name="permissions[' + maxId + '].shortName" value="' + $shortName.val() + '" checked="checked" rel="' + maxId + '"/>&nbsp;&nbsp;'); 
	     $toNewPermission.append('<input type="hidden" name="permissions[' + maxId + '].name" value="' + $name.val() + '" rel="' + maxId + '"/>');
	     $name.val("");
	     $shortName.val("");
	     $description.val("");
	});
	
	
	$('#btn_save').click(function(){
		$("#addmoduleform").submit();
	});
	
	$('#btn_sub_save').click(function(){
		
		$("#addsubmoduleform").submit();
	});
	
	$('#btn_edit_save').click(function(){
		
		$('#editmoduleform').submit();
	});
});

function callback(status)
{
   if(status==1)
   {
	   alert("操作成功!");
	   $('#addmodule').modal('hide');
	   $('#moduleList').treegrid("reload");
   }else
   {
	  alert("操作失败!"); 
   }
}
//-->
</script>
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

			<div class="modal hide fade" id="addmodule">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>添加模块</h3>
				</div>
				<div class="modal-body">
				 <form class="form-horizontal" name="addmoduleform" id="addmoduleform" action="${ctx}/management/module/savemodule.do" method="post" target="hiddenframe"> 
						<fieldset>
							 <div class="control-group">
								<label class="control-label" for="name">模块名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="name" name="name" type="text" >
								</div>
							  </div>
									  
							  <div class="control-group">
								<label class="control-label" for="url">URL：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="url" name="url" type="text" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="sn">授权名称：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="sn" name="sn" type="text" >
								</div>
							  </div>
							  
							   <div class="control-group">
								<label class="control-label" for="priority">优先级：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="priority" name="priority" type="text" >
								   <span class="help-inline">默认为99</span>
								</div>
								</div>
						</fieldset>
						
						<fieldset >
						    <h4>自定义授权：</h4>
							 <div class="controls" id="toNewPermission">
								增(save)<input type="checkbox" name="permissions[0].shortName" value="save" checked="checked"  rel="0"/>&nbsp;&nbsp;
						         <input type="hidden" name="permissions[0].name" value="增" rel="0"/>
								删(delete)<input type="checkbox" name="permissions[1].shortName" value="delete" checked="checked"  rel="1"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[1].name" value="删" rel="0"/>
								查(view)<input type="checkbox" name="permissions[2].shortName" value="view" checked="checked"  rel="2"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[2].name" value="查" rel="0"/>
								改(edit)<input type="checkbox" name="permissions[3].shortName" value="edit" checked="checked"  rel="3"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[3].name" value="改" rel="0"/>
			                 </div>		
						</fieldset>
					
						<fieldset>
						       <h4>动态新增</h4>
								
								<div class="input-prepend" id="newPermissonInput">
									名称：<input class="input-small" placeholder="请输入名称" type="text" name="_name" size="32" maxlength="32" />	
									短名：<input class="input-small" placeholder="用作授权验证" type="text" name="_shortName" size="16" maxlength="16" />	
									描述：<input class="input-small" placeholder="可选输入" type="text" name="_description" size="32" maxlength="255" />
									<a href="#" id="newpermission" class="btn btn-primary">新增</a>
								</div>
						</fieldset>
					</form>	
				</div>
					
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">关闭</a>
					<a id="btn_save" href="#" class="btn btn-primary">保存</a>
				</div>
			</div>
			
			
			
			<div class="modal hide fade" id="addsubmodule">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>添加子模块</h3>
				</div>
				<div class="modal-body">
				 <form class="form-horizontal" name="addsubmoduleform" id="addsubmoduleform" action="${ctx}/management/module/savesubmodule.do" method="post" target="hiddenframe"> 
						<fieldset>
							 <div class="control-group">
								<label class="control-label" for="name">模块名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="name" name="name" type="text" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="name">上级模块名称</label>
								<div class="controls">
								  <input type="hidden" name="parent.id" id="parentId" />
								  <input class="input-xlarge focused" id="parentName" name="parent.name" type="text" >
								</div>
							  </div>
									  
							  <div class="control-group">
								<label class="control-label" for="url">URL：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="url" name="url" type="text" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="sn">授权名称：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="sn" name="sn" type="text" >
								</div>
							  </div>
							  
							   <div class="control-group">
								<label class="control-label" for="priority">优先级：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="priority" name="priority" type="text" >
								   <span class="help-inline">默认为99</span>
								</div>
								</div>
						</fieldset>
						
						<fieldset >
						    <h4>自定义授权：</h4>
							 <div class="controls" id="toNewSubPermission">
								增(save)<input type="checkbox" name="permissions[0].shortName" value="save" checked="checked"  rel="0"/>&nbsp;&nbsp;
						         <input type="hidden" name="permissions[0].name" value="增" rel="0"/>
								删(delete)<input type="checkbox" name="permissions[1].shortName" value="delete" checked="checked"  rel="1"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[1].name" value="删" rel="0"/>
								查(view)<input type="checkbox" name="permissions[2].shortName" value="view" checked="checked"  rel="2"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[2].name" value="查" rel="0"/>
								改(edit)<input type="checkbox" name="permissions[3].shortName" value="edit" checked="checked"  rel="3"/>&nbsp;&nbsp;
								<input type="hidden" name="permissions[3].name" value="改" rel="0"/>
			                 </div>		
						</fieldset>
					
						<fieldset>
						       <h4>动态新增</h4>
								
								<div class="input-prepend" id="newSubPermissonInput">
									名称：<input class="input-small" placeholder="请输入名称" type="text" name="_name" size="32" maxlength="32" />	
									短名：<input class="input-small" placeholder="用作授权验证" type="text" name="_shortName" size="16" maxlength="16" />	
									描述：<input class="input-small" placeholder="可选输入" type="text" name="_description" size="32" maxlength="255" />
									<a href="#" id="newsubpermission" class="btn btn-primary">新增</a>
								</div>
						</fieldset>
					</form>	
				</div>
					
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">关闭</a>
					<a id="btn_sub_save" href="#" class="btn btn-primary">保存</a>
				</div>
			</div>
			
			
			
			<div class="modal hide fade" id="editmodule">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>修改模块</h3>
				</div>
				<div class="modal-body" id="editdiv">
				 <form class="form-horizontal" name="editmoduleform" id="editmoduleform" action="${ctx}/management/module/update.do" method="post" target="hiddenframe"> 
						<fieldset>
							 <div class="control-group">
								<label class="control-label" for="name">模块名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="name" name="name" type="text" >
								  <input type="hidden" id="id" name="id"/>
								</div>
							  </div> 
							  <div class="control-group">
								<label class="control-label" for="url">URL：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="url" name="url" type="text" >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="sn">授权名称：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="sn" name="sn" type="text" >
								</div>
							  </div>
							  
							   <div class="control-group">
								<label class="control-label" for="priority">优先级：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="priority" name="priority" type="text" >
								   <span class="help-inline">默认为99</span>
								</div>
								</div>
						</fieldset>
						
						<fieldset >
						    <h4>自定义授权：</h4>
							 <div class="controls" id="toNewEditPermission">
								
			                 </div>		
						</fieldset>
					
						<fieldset>
						       <h4>动态新增</h4>
								
								<div class="input-prepend" id="newEditPermissonInput">
									名称：<input class="input-small" placeholder="请输入名称" type="text" name="_name" size="32" maxlength="32" />	
									短名：<input class="input-small" placeholder="用作授权验证" type="text" name="_shortName" size="16" maxlength="16" />	
									描述：<input class="input-small" placeholder="可选输入" type="text" name="_description" size="32" maxlength="255" />
									<a href="#" id="newEditpermission" class="btn btn-primary">新增</a>
								</div>
						</fieldset>
					</form>	
				</div>
					
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">关闭</a>
					<a id="btn_edit_save" href="#" class="btn btn-primary">保存</a>
				</div>
			</div>
			
			<iframe name="hiddenframe" name="hiddenframe" style="display: none;"></iframe>
			
