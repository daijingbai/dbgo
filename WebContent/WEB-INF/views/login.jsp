<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>登录界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/views/include/include.jsp"%>
<script type="text/javascript">
	function loginsubmit(){
		/* $.ajax({
		    type: 'POST',
		    url: '${ctx}/Index/login.do',
		    data: {'username':$('#usercode').val(),'password':$('#pwd').val()} ,
		    success: function(data){
		    	
		    }
		}); */
		//$("#loginform").submit();
		$("#loginform").submit();
	}
</script>
</head>
<body>
	<form id="loginform" action="login.do"
		method="post">
		<div style="margin-bottom:10px">
			<table width="100%" height="100%" border="0">
				<tr>
					<td align="center" valign="middle">
						<TABLE border="0" width="260px" cellSpacing="6" cellPadding="8">
							<TBODY>
								<TR>
									<TD><input type="text" class="easyui-textbox"
										id="usercode" name="username"
										style="width: 100%; height: 40px; padding: 12px"
										data-options="prompt:'用户编码',iconCls:'icon-man',iconWidth:38" /></TD>
								</TR>
								<TR>
									<TD><input type="password" id="pwd" class="easyui-textbox"
										name="password"
										style="width: 100%; height: 40px; padding: 12px"
										data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38" />
									</TD>
								</TR>
								<tr>
									<td><input type="checkbox" name="rememberMe" />自动登陆</td>
								</tr>

								<TR>
									<TD align="center"><div>
											<a href="javascript:loginsubmit()" class="easyui-linkbutton"
												data-options="iconCls:'icon-ok'"
												style="padding: 5px 0px; width: 100%;"> <span
												style="font-size: 14px;">Login</span>
											</a>
										</div>
								</TR>
							</TBODY>
						</TABLE>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>