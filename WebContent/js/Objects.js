/**
 * 
 */
var objects={
	User:{
		//findCondition:[[T_field("内容","text"),T_date("日期","text")],[T_combo("内容3","text"),T_field("内容4","text")]],
		findCondition:[[T_field("用户编码","test"),T_field("用户姓名","userName")]],
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("ID","userId",{width:100}),
		         T_cm("用户编码","userCode",{width:150}),
		         T_cm("用户姓名","userName",{width:150})
		]]
	}
}