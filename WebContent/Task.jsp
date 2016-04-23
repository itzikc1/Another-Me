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

	<!--Task-->
	<form action="Task" method="POST">
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">personId
			</span> <input type="text" class="form-control" name="personId" size="20">
		</div>
		<div class="input-group">
			<span class="input-group-addon-btn" id="sizing-addon1">txt</span> <input
				type="text" class="form-control" name="txt" size="20">
		</div>
		<select id="tripType" name="Type" onchange="tripType()">
			<option selected value="default">Please select an option</option>
			<option value="Nothing">Nothing</option>
			<option value="Ask">Ask</option>
			<option value="PopUp">PopUp</option>
			<option value="Sms">Sms</option>
			<option value="Ticket">Ticket</option>
		</select> <select id="tripType" name="withWho" onchange="tripType()">
			<option selected value="default">with who?</option>
			<option value="Nothing">Nothing</option>
			<option value="Ask">Ask</option>
			<option value="PopUp">PopUp</option>
			<option value="Sms">Sms</option>
			<option value="Ticket">Ticket</option>
		</select> <input type="submit" name="task" value="Enter Task">
	</form>

</body>
</html>