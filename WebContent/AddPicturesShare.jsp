<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%@ include file="nuv.jsp"%>


	<!--Task-->
	<form action="SharePictures" method="POST">
	
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">personId</span>
			<input type="text" class="form-control" name="personId" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">picture Name</span>
			<input type="text" class="form-control" name="pictureName" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">person To Send</span>
			<input type="text" class="form-control" name="personToSend" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">txt</span>
			<input type="text" class="form-control" name="txt" size="20">
		</div>
		
		<input type="submit" name="picture" value="Share">




	</form>
</body>
</html>