<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="nuv.jsp" %>
 

	<form action="ViewServer" method="POST">
	<div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">name</span>
    <input type="text" class="form-control" name="Username" size="20">
	</div>
	<div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">id name</span>
    <input type="text" class="form-control" name="id" size="20">
	</div>

	<div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">number</span>
    <input type="text" class="form-control" name="number" size="20">
	</div>

        <input type="submit" name="FirstServlet" value="Enter">

     <div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">Delete name</span>
    <input type="text" class="form-control" name="name" size="20">
	</div>
	
 <input type="submit" name="delete" value="Enter">
 
  <div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">table name</span>
    <input type="text" class="form-control" name="TableName" size="20">
	</div>
	 <div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">column name</span>
    <input type="text" class="form-control" name="Name" size="20">
	</div>
	 <div class="input-group">
    <span class="input-group-addon-btn" id="sizing-addon1">number</span>
    <input type="text" class="form-control" name="Number" size="20">
	</div>
	

  
 <input type="submit" name="newColumn" value="Enter">
 
</form>
</body>
</html>