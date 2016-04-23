<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>





<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>

</head>
<body>
<%@ include file="nuv.jsp" %>

	<form action="PostPersonServlet" method="POST">
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">name</span> <input
				type="text" class="form-control" name="name" size="20">
		</div>
		<input type="submit" name="getUser" value="Enter">
	</form>

	<%
		String name = (String) request.getAttribute("PersonId");
		System.out.println(name);
	%>

</body>
</html>

