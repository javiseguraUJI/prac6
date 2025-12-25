<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Mis reservas</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>

<img src="<%=request.getContextPath()%>/images/Cabecera.png" alt="Cabecera">

<%
  String codUsuario = (String) session.getAttribute("codUsuario");
  JSONArray reservas = (JSONArray) request.getAttribute("reservas");
%>

<h2>Lista de reservas de <%= codUsuario %></h2>

<% if (reservas == null || reservas.isEmpty()) { %>
  <p>No tienes reservas.</p>
<% } else { %>


<ul class="jsonlist">
  <% for (Object o : reservas) {
       JSONObject r = (JSONObject) o;
  %>
    <li><pre class="json"><%= r.toJSONString() %></pre></li>
  <% } %>
</ul>

<% } %>

<p style="margin-top:30px;">
  <a href="<%=request.getContextPath()%>/menu.html">Menú</a>
</p>

</body>
</html>
