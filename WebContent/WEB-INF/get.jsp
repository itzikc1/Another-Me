<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript" src="dojo.js"></script>

	<script type="text/javascript"> 
 dojo.require("dojo.io.*");
 dojo.require("dojo.event.*");
 dojo.require("dojo.html.*");
</script>

	<script>
function getPaneInfo(id) {
 var params = new Array();
 params['paneId'] = id;
 var bindArgs = {
  url: "servlet/CreateForm",
  error: function(type, data, evt){alert("error");},
  mimetype: "text/json",
  content: params
 };
 var req = dojo.io.bind(bindArgs);
 dojo.event.connect(req, "load", this, "showProperty");
}
  
function showProperty(type, data, evt) {
 var propertiesDiv = document.getElementById("Properties");
 if (!data) {
  propertiesDiv.innerHTML = "Damn!";
 } else {
  propertiesDiv.innerHTML = "PaneID: " + data.paneId + "<br/>ParentPane: " + data.parentPaneId;  
 }
}
</script>
</body>
</html>