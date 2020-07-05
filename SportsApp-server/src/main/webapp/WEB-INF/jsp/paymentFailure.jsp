<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Payment Status</title>
<style>
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	margin: 0px auto;
}

.tg td {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-0pky {
	border-color: inherit;
	text-align: left;
	vertical-align: top
}

.tg .tg-0lax {
	text-align: left;
	vertical-align: top
}

#form_login {
	display: table-cell;
	text-align: center;
	vertical-align: middle;
}

body {
	font-family: Verdana, sans-serif;
	font-size: :12px;
}

.wrapper {
	width: 980px;
	margin: 0 auto;
}

table {
	
}

tr {
	padding: 5px
}

td {
	padding: 5px;
}

input {
	padding: 5px;
}
</style>

</head>
<script type="text/javascript">
	setTimeout("document.forms[0].submit()", 2000);
</script>
<body>
	<%
		String url = (String) request.getAttribute("redirectBaseURL");
	%>
	<div style="text-align: center">
		<form id="form2" action="" method="get">
			<center>
				<H3>Transaction Failed</H3>
			</center>
			<br> <br> <br> <br>
		</form>
	</div>
</body>
</html>