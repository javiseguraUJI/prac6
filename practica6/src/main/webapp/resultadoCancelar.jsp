<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Resultado cancelar</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>

<img src="<%=request.getContextPath()%>/images/Cabecera.png" alt="Cabecera">

	<%
	  String codUsuario = (String) session.getAttribute("codUsuario");
	  Long codReserva = (Long) request.getAttribute("codReserva");
	  JSONObject resultado = (JSONObject) request.getAttribute("resultado");
	
	  boolean ok = (resultado != null && !resultado.isEmpty());
	%>
	
	<% if (ok) { %>
	  <h1>Enhorabuena, <%= codUsuario %>, has cancelado la reserva <%= codReserva %></h1>
	
	  <%
	    Object notificarObj = resultado.get("notificar");
	    boolean notificar = (notificarObj != null && Boolean.TRUE.equals(notificarObj));
	    if (notificar) {
	  %>
	    <p><b>Nota:</b> La sesión estaba llena. Al cancelar, se ha liberado una plaza.</p>
	  <%
	    }
	  %>
	
	<% } else { %>
	  <h1>Lo lamento, <%= codUsuario %>, no hemos podido cancelar la reserva <%= codReserva %></h1>
	  <p>Había indicado una reserva inexistente o de otro usuario.</p>
	<% } %>
	
	<p style="margin-top:30px;">
	  <a href="<%=request.getContextPath()%>/menu.html">Menú</a>
	</p>

</body>
</html>
