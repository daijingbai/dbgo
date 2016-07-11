<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/WEB-INF/views/include/include.jsp"%>
</head>
<body>
	<script type="text/javascript">
	$(function(){
		$.DataGridePanel({
			url:'${ctx}/User/findByPage.do',
			T_bar:[true,true,true,
			       '<a iconCls="icon-search" plain="true" href="javascript:search()">查询</a>',
			       '<a iconCls="icon-print" plain="true" href="javascript:test1()">打印</a>'],
			typename:"User"
		}); 
	})
	
	function search(){
		$('#User').datagrid('reload');
	}
	
	function test1(){
		alert('test1');
	}
	</script>
	 
<!-- <div id="tb" style="padding:5px;height:auto">
    <div>
		Date From: <input class="easyui-datebox" style="width:80px">
		To: <input class="easyui-datebox" style="width:80px">
		Language: 
		<input class="easyui-combobox" style="width:100px"
				url="data/combobox_data.json"
				valueField="id" textField="text">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
	<div style="margin-bottom:5px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	</div>
	
</div> -->
  
</body>
</html>