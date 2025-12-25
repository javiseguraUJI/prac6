package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.DiaSemana;
import modelo.GestorReservas;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet("/ServletHacerReserva")
public class ServletHacerReserva extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final String ATTR_GESTOR = "gestorReservas";
  private static final String ATTR_COD_USUARIO = "codUsuario";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1) Recuperar gestor y usuario de sesión
    GestorReservas gestor = (GestorReservas) getServletContext().getAttribute(ATTR_GESTOR);
    HttpSession ses = request.getSession(false);
    String codUsuario = (ses == null) ? null : (String) ses.getAttribute(ATTR_COD_USUARIO);

    if (gestor == null || codUsuario == null) {
      response.sendRedirect(request.getContextPath() + "/index.html");
      return;
    }

    // 2) Leer parámetros del formulario
    String diaStr = request.getParameter("dia");
    String actividad = request.getParameter("actividad");
    String horaStr = request.getParameter("hora");

    JSONObject resultado = new JSONObject();

    try {
      DiaSemana dia = DiaSemana.valueOf(diaStr);   
      long hora = Long.parseLong(horaStr);

      // 3) Intentar reservar
      resultado = gestor.hazReserva(codUsuario, actividad, dia, hora);

    } catch (Exception e) {
      // Si hay error de parseo, dejamos resultado vacío
      resultado = new JSONObject();
    }

    // 4) Enviar resultado a la vista
    request.setAttribute("resultado", resultado);
    RequestDispatcher rd = request.getRequestDispatcher("/resultadoReserva.jsp");
    rd.forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Si entran por GET, mandamos al formulario
    response.sendRedirect(request.getContextPath() + "/formHacerReserva.html");
  }
}
