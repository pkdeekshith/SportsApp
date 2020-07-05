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
		String TransactionRefNo = (String) request.getAttribute("TransactionRefNo");
		String OrderID = (String) request.getAttribute("OrderID");
		String TransactionDateTime = (String) request.getAttribute("TransactionDateTime");
		String status = (String) request.getAttribute("status");
		String statusDesc = (String) request.getAttribute("statusDesc");
		String url = (String) request.getAttribute("redirectURL");
	%>

	<div style="text-align: center">
		<form id="form1" action="<%=url%>" method="post">
			<center>
				<H3>Transaction Status</H3>
			</center>
			<input type="hidden"
				name="OrderID" id="OrderID"
				value="<%=OrderID%>" />
			<br> <br> <br> <br>
			<table class="tg" style="table-layout: fixed; width: 527px">
				<colgroup>
					<col style="width: 504px">
					<col style="width: 504px">
				</colgroup>
				<tbody>
					<tr>
						<td class="tg-0pky">Transaction Ref No</td>
						<td class="tg-0lax"><%=TransactionRefNo%></td>
					</tr>
					<tr>
						<td class="tg-0lax">Order ID</td>
						<td class="tg-0lax"><%=OrderID%></td>
					</tr>
					<tr>
						<td class="tg-0lax">Transaction Date Time</td>
						<td class="tg-0lax"><%=TransactionDateTime%></td>
					</tr>
					<tr>
						<td class="tg-0lax">Transaction Status</td>
						<td class="tg-0lax">
							<%
								if ("S".equals(status)) {
									out.print("Success");
								} else {
									out.print("Fail");
								}
							%>
						</td>
					</tr>
					<tr>
						<td class="tg-0lax">Transaction Status Description</td>
						<td class="tg-0lax"><%=statusDesc%></td>
					</tr>
				</tbody>
			</table>
			<H6>
				Please click <a href="javascript:{}" onclick="document.getElementById('form1').submit(); return false;">here</a> if
				you are not redirected within a few seconds
			</H6>
		</form>
	</div>
</body>
</html>