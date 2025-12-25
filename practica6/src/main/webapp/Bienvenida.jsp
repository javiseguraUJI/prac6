<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Bienvenida</title>
</head>
<body>
<h1>Página de bienvenida en <%= request.getAttribute("ruta")%></h1>
<p>Bienvenido <%=request.getParameter("nombre")%>
              <%=request.getParameter("apellidos")%></p>
</body>
</html>