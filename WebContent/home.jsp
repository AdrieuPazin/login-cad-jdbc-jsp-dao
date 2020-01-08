<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Início</title>
</head>
<body>

<%@include file="./util/header.jsp" %>

	<%
		String msg = (String) request.getAttribute("mensagem");
		
		if(msg != null){
			out.println(msg);
		}
		
	%>
	
	

<%@include file="./util/footer.jsp" %>

</body>
</html>