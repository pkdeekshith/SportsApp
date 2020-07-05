<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Gateway</title>
<style type="text/css">
div {
	box-shadow: 10px 10px 5px #888888;
}
</style>
<script type="text/javascript">
			function postData(){	
				document.forms[0].submit();	
			}	
		</script>
</head>

<body onload="postData();">
	<%
			String mid=(String)request.getAttribute("mid");
			String merchantRequest =(String)  request.getAttribute("merchantRequest");
			String url = (String) request.getAttribute("paymentURL");
		%>

	<form action="<%=url%>"
		method="post">
		<center>

			Please do not close or refresh the browser. <input type="hidden"
				name="merchantRequest" id="merchantRequest"
				value="<%=merchantRequest%>" /> <input type="hidden" name="MID"
				id="MID" value="<%=mid%>" />
		</center>
	</form>
</body>
</html>