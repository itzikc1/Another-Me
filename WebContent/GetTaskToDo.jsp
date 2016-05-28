<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>

<body>

</head>
<body>
<%@ include file="nuv.jsp" %>

	<form action="GetTasksToDo" method="POST">
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">name</span> <input
				type="text" class="form-control" name="name" size="20">
		</div>
		<input type="submit" name="getUser" value="Enter">
	</form>

</body>
</html>