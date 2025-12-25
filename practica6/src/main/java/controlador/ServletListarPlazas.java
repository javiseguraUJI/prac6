package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.GestorReservas;

import java.io.IOException;

@WebServlet("/ServletListarPlazas")
public class ServletListarPlazas extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final String ATTR_GESTOR = "gestorReservas";
  private static final String ATTR_COD_USUARIO = "codUsuario";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1) Recuperar gestor del contexto
    GestorReservas gestor = (GestorReservas) getServletContext().getAttribute(ATTR_GESTOR);

    // 2) Recuperar usuario de sesión (no crear sesión nueva)
    HttpSession ses = request.getSession(false);
    String cod = (ses == null) ? null : (String) ses.getAttribute(ATTR_COD_USUARIO);

    // 3) Si no hay sesión o gestor, volver al inicio
    if (gestor == null || cod == null) {
      response.sendRedirect(request.getContextPath() + "/index.html");
      return;
    }

    // 4) Leer actividad del formulario
    String actividad = request.getParameter("actividad");
    if (actividad == null || actividad.isBlank()) {
      response.sendRedirect(request.getContextPath() + "/formPlazasDisponiblesActividad.html");
      return;
    }

    // 5) Consultar plazas disponibles en el modelo
    request.setAttribute("actividad", actividad);
    request.setAttribute("plazas", gestor.listaPlazasDisponibles(actividad));

    // 6) Enviar a la vista
    RequestDispatcher rd = request.getRequestDispatcher("/listarPlazas.jsp");
    rd.forward(request, response);
  }
}
