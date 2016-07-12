/**
 * 
 */
var T_cm=function(name,field){
	var result={
			title:name,
			width:100,
			field:field
	};
	for (var i = 2; i < arguments.length; i++) {
		$.extend(result,arguments[i]);
	}
	return result;
}

var T_field=function(name,field){
	var defaults={width:150,required:false,height:20,type:'textbox',padding:'1px 1px',missingMessage:'请输入'+name};
	for(var i=2;i<arguments.length;i++){
		dafaults=$.extend(defaults,arguments[i]);
	}

	var tbar='&nbsp;&nbsp;<a style=display:inline-table;width:80px;>'+name+': </a><input name=_'+field+' style="width:'+defaults.width+'px;margin-top:0px">';
	var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:80px;>'+name+':</a>'+
			'<input id='+field+' name='+field+' style=margin-left:0px;width:'+defaults.width+'px></input></div>');
	var divs={div:div,tbar:tbar,field:field}
	defaults=$.extend(defaults,divs);
	return defaults;
}

var T_date=function(name,field){
	var defaults={width:120,required:false,panelHeight:200,panelWidth:200,type:'datebox',editable:false};
	for(var i=2;i<arguments.length;i++){
		dafaults=$.extend(defaults,arguments[i]);
	}
	var tbar='&nbsp;&nbsp;<a style=display:inline-table;width:50px;>'+name+': </a><input name='+field+' style="width:'+defaults.width+'px;margin-top:0px">';
	var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:130px;>'+name+'</a>'+
			'<input id='+field+' name='+field+' style=width:'+defaults.width+'px></input></div>');
	var divs={div:div,tbar:tbar,field:field}
	defaults=$.extend(defaults,divs);
	return defaults;
}

var T_datetime=function(type,field){
	var defaults={width:155,required:false,panelHeight:200,panelWidth:240,type:'datetimebox',editable:false};
	for(var i=2;i<arguments.length;i++){
		dafaults=$.extend(defaults,arguments[i]);
	}
	var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:130px;>'+language[type][field]+'</a>'+
			'<input id='+field+' name='+field+' style=width:'+defaults.width+'px></input></div>');
	var divs={div:div,field:field,model:type}
	defaults=$.extend(defaults,divs);
	return defaults;
}

var T_combo=function(name,field){
	var defaults={width:120,url:"",valueField:'value',textField:'text',type:'combo',editable:false};
	for(var i=2;i<arguments.length;i++){
		dafaults=$.extend(defaults,arguments[i]);
	}
	var tbar='&nbsp;&nbsp;<a style=display:inline-table;width:50px;>'+name+': </a><input name='+field+' style="width:'+defaults.width+'px;margin-top:0px">';
	var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:130px;>'+name+'</a>'+
			'<div id='+field+' name='+field+' style=margin-bottom:5px;width:'+defaults.width+'px></div></div>');
	var divs={div:div,tbar:tbar}
	defaults=$.extend(defaults,divs);
	return defaults;
}

var T_combogrid=function(type,field){
	var defaults={width:156,height:20,padding:'1px 1px',url:"",idField:'id',textField:'text',type:'combogrid'};
	for(var i=2;i<arguments.length;i++){
		dafaults=$.extend(defaults,arguments[i]);
	}
	var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:130px;>'+language[type][field]+'</a>'+
			'<div id='+field+' name='+field+' style=margin-bottom:5px;width:'+defaults.width+'px></div></div>');
	var divs={div:div,field:field,model:type}
	defaults=$.extend(defaults,divs);
	return defaults;
}

var DateBoxFormate = function (val) {
    if (val == "" || val == null) return;
    var date;
    if (typeof (val) == "string") {
        date = new Date(Date.parse(val.replace('-', '/')));
    }
    if (typeof (val) == "object") {
        date = val;
    }
    var year = date.getFullYear().toString();
    var month = (date.getMonth() + 1);
    var day = date.getDate().toString();
    var hour = date.getHours().toString();
    var minutes = date.getMinutes().toString();
    var seconds = date.getSeconds().toString();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    if (hour == "00") {
        return year + "-" + month + "-" + day;
    } else {
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }

}

var format = function () {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};

