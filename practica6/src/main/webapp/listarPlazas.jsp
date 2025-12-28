<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html lang="es">
<head>
  	<meta charset="UTF-8">
  	<title>Plazas disponibles</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

</head>
<body>

<img src="<%=request.getContextPath()%>/images/Cabecera.png" alt="Cabecera">

	<%
	  String actividad = (String) request.getAttribute("actividad");
	  JSONArray plazas = (JSONArray) request.getAttribute("plazas");
	%>

	<h2>Lista plazas disponibles de <%= actividad %></h2>

	<% if (plazas == null || plazas.isEmpty()) { %>
	  <p>No hay plazas disponibles.</p>
	<% } else { %>
	  <ul class="jsonlist">
	    <% for (Object o : plazas) {
	         JSONObject sesion = (JSONObject) o;
	    %>
	      <li><pre class="json"><%= sesion.toJSONString() %></pre></li>
	    <% } %>
	  </ul>
	<% } %>
	
	<p style="margin-top:30px;">
	  <a href="<%=request.getContextPath()%>/menu.html">Menú</a>
	</p>
	
	</body>
</html>
