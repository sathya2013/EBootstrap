$(function(){
	
	$('#moduleList').treegrid({
		title:'',
		iconCls:'',
		height:509,
		nowrap: false,
		rownumbers: false,
		//animate:true,
		collapsible:true, 
		striped: true,
		//fitColumns:true,
		url:'loadmodule.do',
		idField:'id',
		treeField:'name',
		pagination : true,
        pageSize : 10,
		frozenColumns:[[
		]],
		columns:[[
                 
		          {field:'name',title:'模块名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'priority',title:'优先级',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'sn',title:'授权名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'parent',title:'父模块名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'addChild',title:'添加子模块',width:loadContentWidth(0.2),rowspan:2,align:'center'},		  
		          {field:'operation',title:'操作',rowspan:2,width:loadContentWidth(0.9),align:'center'}
		          
		]],
		toolbar:[
		         { 
					 text:'添加',
				     iconCls:'icon-add',
				     handler:function(){
				       $('#addmodule').modal('show');
			         }
		         },
		         '-',
		         { 
					 text:'修改',
				     iconCls:'icon-edit',
				     handler:function(){
				    	 var row = $('#moduleList').datagrid('getSelected');
				    	 alert(row.id);
			         }
		         },
		         '-',
		         { 
					 text:'删除',
				     iconCls:'icon-delete',
				     handler:function(){
				       alert('add');
			         }
		         }
		     ],
		onBeforeLoad:function(row,param){
			if (row){
				$(this).treegrid('options').url = 'submodule.do?id='+row.id;
			} else {
				$(this).treegrid('options').url = 'loadmodule.do';
			}
		}
	});
	
});

function loadContentWidth(index){
	return $("#moduleList").width()*index;
}

function addSubModule(moduleId)
{
	$.get("loadmodulename.do?id="+moduleId, function(result){ 
  		     if(result.name)
  			 {
  				$("input[id=parentId]").val(moduleId);
  				$("input[id=parentName]").val(result.name);
  				$('#addsubmodule').modal('show');
  			 }
  	});
}

function editModule(moduleId)
{
	$.get("getmoduledetail.do?id="+moduleId, function(result){ 
		     
		     var module =result.module;
		     var permissions =result.permissions;
		     var $editfield = $('#editdiv');
		     var $editPermissionfield =$('#toNewEditPermission');
		     if(module)
		     {
		    	 $("input[name=id]",$editfield).val(module.id);
		    	 $("input[name=name]",$editfield).val(module.name);
		    	 $("input[name=url]",$editfield).val(module.url);
		    	 $("input[name=sn]",$editfield).val(module.sn);
		    	 $("input[name=priority]",$editfield).val(module.priority);
		     }
		     if(permissions)
		     {
		    	$editPermissionfield.empty();
		    	$(permissions).each(function(index,node){
		    		$editPermissionfield.append(node.name + '(' + node.shortName + ')' + '<input type="checkbox" name="permissions[' + index + '].shortName" value="' + node.shortName + '" checked="checked" rel="' + index + '"/>&nbsp;&nbsp;'); 
		    		$editPermissionfield.append('<input type="hidden" name="permissions[' + index + '].name" value="' + node.name + '" rel="' + index + '"/>');
		    		$editPermissionfield.append('<input type="hidden" name="permissions[' + index + '].id" value="' + node.id + '" rel="' + index + '"/>');
		    	});
		     }
		     $('#editmodule').modal('show');
	});
}
