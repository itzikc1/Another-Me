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


	<%@ include file="nuv.jsp"%>

	<!--sms-->
	<form action="AddPopUp" method="POST">
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">personId
			</span> <input type="text" class="form-control" name="personId" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">txt</span>
			<input type="text" class="form-control" name="txt" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">Send To</span>
			<input type="text" class="form-control" name="sendTo" size="20">
		</div>
		
		<input type="submit" name="enterPopUp" value="Enter PopUp">

	</form>
</body>
</html>