/**
 * 封装了easyui常用的控件
 */
$.extend({
	DataGridePanel:function(options){
		//datagrid控件默认的属性
		var defaults={
				width:'auto',
				height:450,
				dialogHeight:'auto',
				stripted:true,
				fitColumns:true,
				loadMsg:'加载中……',
				singleSelect:true,
				pagination:true,
				rownumbers:false,
				modal:true,
				pageSize:20,
				pageList:[5,10,20,50]
		}
		
		var options=$.extend(defaults,options);
		
		$('body').css("overflow-x","auto");
		
		var obj=$('<table id='+options.typename+'></table>');
		$('body').append(obj);
		
		$('body').append('<div id="tb" style="padding:0px;height:auto"><div style="margin:0px;border-top:1px solid #D4D4D4"></div></div>');
		
		//＊＊＊＊＊＊＊＊按钮开始＊＊＊＊＊＊＊＊＊
		function inner_add(){
			add(options);
		}
		
		$.each(options.T_bar,function(key,value){
			if(key==0&&value){
				$('#tb').find('div').append('<a iconCls="icon-search" plain="true" href="javascript:search(\''+options.typename+'\')">查询</a>');
			}else if(key==1&&value){
				$('#tb').find('div').append('<a iconCls="icon-add" plain="true" href="javascript:add(\''+options.typename+'\')">添加</a>');
			}else if(key==2&&value){
				$('#tb').find('div').append('<a iconCls="icon-edit" plain="true" href="javascript:edit(\''+options.typename+'\')">编辑</a>');
			}else if(key==3&&value){
				$('#tb').find('div').append('<a iconCls="icon-remove" plain="true" href="javascript:del(\''+options.typename+'\')">删除</a>');
			}else if(key>=4){
				$('#tb').find('div').append(value);
			}
		})
		$('#tb').find('a').linkbutton({});
		//＊＊＊＊＊＊＊＊按钮结束＊＊＊＊＊＊＊＊＊

		if(typeof(objects[options.typename].findCondition)!="undefined"){
			var fidcon=objects[options.typename].findCondition;
			for (var i = fidcon.length-1;  i>= 0; i--) {
				$('#tb').prepend('<div id="tbar'+i+'" style="padding-top:5px;padding-bottom:5px;height:auto;"></div>');
				for(var j=0;j<fidcon[i].length;j++){
					$('#tbar'+i).append(fidcon[i][j].tbar);
					if(fidcon[i][j].type=="textbox"){
						$('#tbar'+i).find('input:last').textbox({})
					}else if(fidcon[i][j].type=="combo"){
						$('#tbar'+i).find('input:last').combo({})
					}else if(fidcon[i][j].type=="datebox"){
						$('#tbar'+i).find('input:last').datebox({})
					}
				}
			}
		}
		
		$('#'+options.typename).datagrid({
			title:options.title||'',
			width:options.width,
			height:options.height,
			stripted:options.stripted,
			singleSelect:options.singleSelect,
			url:options.url,
			loadMsg:options.loadMsg,
			fit:true,
			pagination:options.pagination,
			rownumbers:options.rownumbers,
			pageSize:options.pageSize,
			pageList:options.pageList,
			toolbar:'#tb',
			columns:objects[options.typename].column,
			onBeforeLoad:function(param){
				//param.id=id;
				if(typeof(objects[options.typename].findCondition)!="undefined"){
					fidcon=objects[options.typename].findCondition;
					for (var i = fidcon.length-1;  i>= 0; i--) {
						for(var j=0;j<fidcon[i].length;j++){
							param[fidcon[i][j].field]=$('input[name=_'+fidcon[i][j].field+']').val();	
						}
					}
				}
			}
		});
	}
})

function search(typename){
	$('#' + typename + '').datagrid('reload');
}

function add(typename){
	if ($('#add_dlg').length == 0) {//判断id为add_dlg的元素是否存在
		$('body').append('<div id=add_dlg style="width:400px;height:280px;padding:10px 20px"><form id=add_fr method="post"></form></div>');
	}
	$('#add_fr').form('clear');
	$('#add_fr').form('reset');//clear清不掉 表单中的t_number所以加了一句reset
	for (i = objects[typename].items.length - 1; i >= 0; i--) {
		$('#add_fr').prepend(objects[typename].items[i].div);
		if (objects[typename].items[i].type == "textbox") {
            $('#' + objects[typename].items[i].field + '').textbox({
                required:objects[typename].items[i].required,
                validType: objects[typename].items[i].validType,
                missingMessage: objects[typename].items[i].missingMessage
            });
        }
	}
	
	 $('#add_dlg').dialog({
	        title: "添加",
	        width: 300,
	        height: 'auto',
	        closed: false,
	        modal: true,
	        buttons: [{
	            text: "确定",
	            handler: function () {
	                $('#add_fr').form('submit', {
	                    url:'/'+typename+'/insert.do',
	                    onSubmit: function (param) {
	                        return $(this).form('validate');
	                    },
	                    success: function (result) {
	                    	 var result = eval('(' + result + ')');
	                    	 if(!result.success){
	                    		 $.messager.alert("提示", result.msg, 'error');
	                    	 }else{
		                    	 $('#add_dlg').dialog('close');
		                    	 $.messager.alert("提示", result.msg, 'info');
		                    	 $('#' + typename + '').datagrid('reload');
	                    	 }
	                    }
	                });
	            }
	        }, {
	            text: "关闭",
	            handler: function () {$('#add_dlg').dialog('close'); }
	        }]
	    });
}

function edit(typename){
	var row = $('#' + typename + '').datagrid('getSelections');
    if (row.length == 0) {
        $.messager.alert("错误", "请选择一条记录");
    }else if (row.length > 1) {
        $.messager.alert("错误", "编辑时只能选择一条记录");
    }else if(row.length == 1){
    	if ($('#edit_dlg').length == 0) {
            $('body').append('<div id=edit_dlg style="width:400px;height:280px;padding:10px 20px"><form id=edit_fr method="post"></form></div>');
        }
    	$('#edit_fr').form('reset');
    	for (i = objects[typename].items.length - 1; i >= 0; i--) {
    		$('#edit_fr').prepend(objects[typename].items[i].div);
    		if (objects[typename].items[i].type == "textbox") {
                $('input[name=' + objects[typename].items[i].field + ']').textbox({
                    required: objects[typename].items[i].required,
                    validType: objects[typename].items[i].validType,
                    missingMessage: objects[typename].items[i].missingMessage
                });
            }
    	}
    	
    	$('#edit_dlg').dialog({
	        title: "编辑",
	        width: 300,
	        height: 'auto',
	        closed: false,
	        modal: true,
	        buttons: [{
	            text: "确定",
	            handler: function () {
	                $('#edit_fr').form('submit', {
	                    url:'/'+typename+'/update.do',
	                    onSubmit: function (param) {
	                    	param[objects[typename].id]=row[0][objects[typename].id];
	                        return $(this).form('validate');
	                    },
	                    success: function (result) {
	                    	 var result = eval('(' + result + ')');
	                    	 if(!result.success){
	                    		 $.messager.alert("提示", result.msg, 'error');
	                    	 }else{
		                    	 $('#edit_dlg').dialog('close');
		                    	 $.messager.alert("提示", result.msg, 'info');
		                    	 $('#' + typename + '').datagrid('reload');
	                    	 }
	                    }
	                });
	            }
	        }, {
	            text: "关闭",
	            handler: function () {$('#edit_dlg').dialog('close'); }
	        }]
	    });
    	 $('#edit_fr').form('load', row[0]);	
    }
}

function del(typename){
	var rows = $('#' + typename + '').datagrid('getSelections');
    if (rows.length == 0) {
    	$.messager.alert("错误", "请选择一条记录");
    }else{
    	var ids="";
    	for(var i=0;i<rows.length;i++){
    		ids=ids+rows[i][objects[typename].id]+',';
    	}
    	$.messager.confirm('确认？','确定要删除这条记录吗?',function(r){
    		if(r){
    			$.ajax({
                    type: 'post',
                    url:'/'+typename+'/delete.do',
                    data: { ids: ids },
                    success: function (result) {
                        //var result = eval('(' + result + ')');
                        if (!result.success) {
                            $.messager.alert("提示", result.msg, 'error');
                        } else {
                        	$.messager.alert("提示", result.msg, 'info');
	                    	 $('#' + typename + '').datagrid('reload');
                        }
                    }
                });
    		}
    	});
    }
}