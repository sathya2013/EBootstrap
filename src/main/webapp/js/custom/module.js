$(function(){
	
	$('#moduleList').treegrid({
		title:'',
		iconCls:'',
		height:309,
		nowrap: false,
		rownumbers: false,
		//animate:true,
		collapsible:true, 
		striped: true,
		//fitColumns:true,
		url:'${ctx}/management/module/loadmodule.do',
		idField:'id',
		treeField:'name',
		frozenColumns:[[
		]],
		columns:[[
		          {field:'name',title:'模块名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'priority',title:'优先级',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'sn',title:'授权名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'parent',title:'父模块名称',width:loadContentWidth(0.2),rowspan:2,align:'left'},
		          {field:'addChild',title:'添加子模块',width:loadContentWidth(0.2),rowspan:2,align:'center'},		  
		          {field:'operation',title:'操作',rowspan:2,width:loadContentWidth(0.3),align:'center'}
		          
		]],
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
