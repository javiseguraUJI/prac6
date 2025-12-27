package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.GestorReservas;

import java.io.IOException;

/**
 * Servlet implementation class ServletSalir
 */
@WebServlet("/ServletSalir")
public class ServletSalir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private static final String ATTR_GESTOR = "gestorReservas";
	private static final String ATTR_COD_USUARIO = "codUsuario";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSalir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestorReservas gestor = (GestorReservas) getServletContext().getAttribute(ATTR_GESTOR);
		gestor.guardaDatos();
		response.sendRedirect(request.getContextPath() + "/salir.jsp");
	}



}
