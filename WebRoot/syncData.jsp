<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据同步</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script src="assets/js/jquery.min.js"></script>
 <script type="text/javascript">

	function start(){
	$.post("startsyncReportAction", 
	{"synctime": $("#synctime").val()},
	 function(data){
			alert("开始同步");
		});
	}
	function stop(){
	$.post("stopsyncReportAction", 
	 function(data){
			alert("停止同步");
		});
	}
	
	function ds(){
		var a = $("#synctime").val();
		setTimeout("dszx()",a * 1000);
	}
	
	function dszx(){
		$.post("startsyncReportAction", 
		{"synctime": $("#synctime").val()},
		 function(data){
			console.log("开始同步");
			});
	}
	</script>
  </head>
  
  <body>
    <s:textfield  id='synctime' name="synctime" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " value="60"></s:textfield>
    <s:label>秒</s:label>
    <button type="button" onclick="start()">启动</button>
    <button type="button"  onclick="stop()">停止</button>
    
     <button type="button"  onclick="ds()">定时执行</button>
  </body>
</html>
