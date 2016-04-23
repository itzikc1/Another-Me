<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>

<body>


	<%@ include file="nuv.jsp"%>


	<!--person-->
	<form action="PersonServlet" method="POST">
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">personId
			</span> <input type="text" class="form-control" name="personId" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">password</span>
			<input type="text" class="form-control" name="password" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">mail</span> <input
				type="text" class="form-control" name="mail" size="20">
		</div>

		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">phoneNumber</span>
			<input type="text" class="form-control" name="phoneNumber" size="20">
		</div>
		<input type="submit" name="enterPerson" value="Enter Person">




		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">personId</span>
			<input type="text" class="form-control" name="Username" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">taskText</span>
			<input type="text" class="form-control" name="id" size="20">
		</div>

		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">start</span> <input
				type="text" class="form-control" name="number" size="20">
		</div>

		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">end</span> <input
				type="text" class="form-control" name="number" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">platform</span>
			<input type="text" class="form-control" name="number" size="20">
		</div>

		<input type="submit" name="task" value="Enter Task">







	</form>
</body>
</html>