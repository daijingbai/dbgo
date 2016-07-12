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
				url:'${ctx}/Role/findByPage.do',
				T_bar:[true,true,true,true,
				       '<a iconCls="icon-link" plain="true" href="javascript:test1()">权限管理</a>'],
				typename:"Role"
			}); 
		})
	</script>
</body>
</html>