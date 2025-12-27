<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html lang="es">
<head>
  	<meta charset="UTF-8">
  	<title>Despedida</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

</head>
<body>

<img src="<%=request.getContextPath()%>/images/Cabecera.png" alt="Cabecera">

	
	<%
	  String codUsuario = (String) session.getAttribute("codUsuario");

	%>
	
	<h1>Hasta luego <%= codUsuario %></h1>
	
	<h1>Te estaremos esperando</h1>
	
	
	</body>
</html>
