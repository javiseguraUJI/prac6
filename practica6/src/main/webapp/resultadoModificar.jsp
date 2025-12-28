<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Resultado reserva</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>

<img src="<%=request.getContextPath()%>/images/Cabecera.png" alt="Cabecera">

	<%
	  String codUsuario = (String) session.getAttribute("codUsuario");
	  JSONObject resultado = (JSONObject) request.getAttribute("resultado");
	
	  boolean ok = (resultado != null && !resultado.isEmpty());
	%>

	<% if (ok) { %>
	  <h2>Enhorabuena, <%= codUsuario %>, has modificado la reserva con código: <%= resultado.get("codReserva") %></h2>
	<% } else { %>
	  <h2>No se ha podido modificar la reserva</h2>
	  <p>Comprueba que la reserva existe y que existe una sesión con plazas en el día y hora indicados.</p>
	<% } %>
	
	<p style="margin-top:30px;">
	  <a href="<%=request.getContextPath()%>/menu.html">Menú</a>
	</p>

</body>
</html>
