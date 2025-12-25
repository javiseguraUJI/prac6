package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.GestorReservas;

import java.io.IOException;

/**
 * Servlet implementation class ServletAcceso
 */
@WebServlet("/ServletAcceso")
public class ServletAcceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // Nombre del atributo en el contexto para compartir el gestor
    private static final String ATTR_GESTOR = "gestorReservas";
    
    // Nombre del atributo en sesión para guardar el código del cliente
    private static final String ATTR_COD_USUARIO = "codUsuario";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAcceso() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) Crear el GestorReservas la primera vez y guardarlo en el contexto
        ServletContext ctx = getServletContext();
        synchronized (ctx) {
            if (ctx.getAttribute(ATTR_GESTOR) == null) {
                GestorReservas gestor = new GestorReservas();
                ctx.setAttribute(ATTR_GESTOR, gestor);
            }
        }
        
        // 2) Guardar el código del cliente en sesión 
        String cod = request.getParameter("cod"); 
        if (cod == null || cod.isBlank()) {
            // si no llega código, vuelve al índice con mensaje
            request.setAttribute("error", "Debes introducir un código de usuario.");
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            rd.forward(request, response);
            return;
        }

       
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTR_COD_USUARIO, cod.trim());
        
        
        // 3) Ir a menu.html
        RequestDispatcher rd = request.getRequestDispatcher("/menu.html");
        rd.forward(request, response);
        
	}

}
