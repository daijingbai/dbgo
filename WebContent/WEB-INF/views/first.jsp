<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大贲科技增值税管理系统</title>
<%@ include file="/WEB-INF/views/include/include.jsp"%>
<script type="text/javascript">
	function logout(){
		location.href = '${ctx}/logout.do';
	}
	
	function newTab(treeNode) {
        var $tbs = $("#tbs"); //获取tab容器
        //新根据 标题 判断 是否以存在 同名tab
        if ($tbs.tabs("exists", treeNode.text)) {
            $tbs.tabs("select", treeNode.text);
        } else {
            $tbs.tabs("add", {
                title: treeNode.text,
                content: newIframe(treeNode.attributes.url),
                closable: true,
                fit: true
            });
        }
    }
	
	function newIframe(url) {
        var ifrStr = "<iframe frameborder='0' marginwidth='2' marginheight='2' src='" + url + "' style='width:100%;height:99%;border:0px;marging:0px'></iframe>";
        return ifrStr;
    }
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:'true'">
		<div data-options="region:'north'" style="height:80px">
				<span style="padding-right: 20px;padding-top:50px;float: right;">
					欢迎当前用户：${activeUser.userName} &nbsp;&nbsp;<a title='修改密码'>修改密码</a> &nbsp;&nbsp; <a onclick="logout()">退出系统</a>
				</span>
		</div>
		<!-- <div data-options="region:'south',split:true" style="height:50px;"></div> -->
		<!-- <div data-options="region:'east',split:true" title="East" style="width:100px;"></div> -->
		<div data-options="region:'west',split:true" title="菜单" style="width:200px;">
			<div class="easyui-panel" style="padding: 5px;border:0px">
				<ul class="easyui-tree"
					data-options="url:'${ctx}/Resource/getMenus.do',lines: true,method:'get',
					onClick: function(node){
						if ($(this).tree('isLeaf', node.target))
                        newTab(node);
				    },animate:true"></ul>
			</div>
		</div>
		<div data-options="region:'center',title:''">
			<div id="tbs" class="easyui-tabs" data-options="fit:true,border:false,pill:true"></div>
		</div>
	</div>
</body>
</html>