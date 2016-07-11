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
				findConditionFlag:true,
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
		$.each(options.T_bar,function(key,value){
			if(key==0&&value){
				$('#tb').find('div').append('<a iconCls="icon-add" plain="true" href="javascript:add()">添加</a>');
			}else if(key==1&&value){
				$('#tb').find('div').append('<a iconCls="icon-edit" plain="true" href="javascript:edit()">编辑</a>');
			}else if(key==2&&value){
				$('#tb').find('div').append('<a iconCls="icon-remove" plain="true" href="javascript:del()">删除</a>');
			}else if(key>=3){
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
							param[fidcon[i][j].field]=$('input[name='+fidcon[i][j].field+']').val();	
						}
					}
				}
			}
		});
	}
})

function add(){
	alert('add');
}

function edit(){
	alert('edit');
}

function del(){
	alert('del');
}