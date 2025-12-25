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

/**
 * Servlet implementation class ServletListarReservas
 */
@WebServlet("/ServletListarReservas")
public class ServletListarReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATTR_GESTOR = "gestorReservas";
	private static final String ATTR_COD_USUARIO = "codUsuario";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarReservas() {
        super();
    }
    

	/**
	 * Atiende las peticiones GET para listar las reservas del usuario.
	 *
	 * 1) Recupera el gestor de reservas del contexto.
	 * 2) Recupera la sesión actual y el código del usuario.
	 * 3) Si no existe sesión o usuario, redirige al índice.
	 * 4) Obtiene las reservas del usuario desde el modelo.
	 * 5) Envía las reservas a la vista listarReservas.jsp.
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener el gestor de reservas compartido en el contexto
		GestorReservas gestor = (GestorReservas) getServletContext().getAttribute(ATTR_GESTOR);
		
		// Obtener la sesión sin crear una nueva si no existe
	    HttpSession ses = request.getSession(false);
	    
	    // Recuperar el código del usuario almacenado en sesión
	    String cod = (ses == null) ? null : (String) ses.getAttribute(ATTR_COD_USUARIO);

	    
	    // Si no hay gestor o no hay usuario autenticado, volver al índice
	    if (gestor == null || cod == null) {
	      response.sendRedirect(request.getContextPath() + "/index.html");
	      return;
	    }

	    // Obtener las reservas del usuario desde el modelo
	    request.setAttribute("reservas", gestor.listaReservasUsuario(cod));
	    
	    // Enviar la información a la vista JSP
	    RequestDispatcher rd = request.getRequestDispatcher("/listarReservas.jsp");
	    rd.forward(request, response);
	}


}
