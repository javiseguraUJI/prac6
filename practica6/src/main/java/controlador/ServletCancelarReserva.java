package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.GestorReservas;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet("/ServletCancelarReserva")
public class ServletCancelarReserva extends HttpServlet {
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

    // 2) Leer código de reserva
    String codReservaStr = request.getParameter("codReserva");
    long codReserva;

    try {
      codReserva = Long.parseLong(codReservaStr);
    } catch (Exception e) {
      // parámetro inválido -> volvemos al formulario
      response.sendRedirect(request.getContextPath() + "/formCancelar.html");
      return;
    }

    // 3) Intentar cancelar en el modelo
    JSONObject resultado = gestor.cancelaReserva(codUsuario, codReserva);

    // 4) Enviar a la vista
    request.setAttribute("codReserva", codReserva);
    request.setAttribute("resultado", resultado);

    RequestDispatcher rd = request.getRequestDispatcher("/resultadoCancelar.jsp");
    rd.forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // si entran por GET, mandamos al formulario
    response.sendRedirect(request.getContextPath() + "/formCancelarReserva.html");
  }
}
