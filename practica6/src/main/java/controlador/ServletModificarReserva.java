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

import java.io.IOException;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletModificarReserva
 */
@WebServlet("/ServletModificarReserva")
public class ServletModificarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	  private static final String ATTR_GESTOR = "gestorReservas";
	  private static final String ATTR_COD_USUARIO = "codUsuario";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si entran por GET, mandamos al formulario
		response.sendRedirect(request.getContextPath() + "/formModificarReserva.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    GestorReservas gestor = (GestorReservas) getServletContext().getAttribute(ATTR_GESTOR);
	    HttpSession ses = request.getSession(false);
	    String codUsuario = (ses == null) ? null : (String) ses.getAttribute(ATTR_COD_USUARIO);

	    if (gestor == null || codUsuario == null) {
	      response.sendRedirect(request.getContextPath() + "/index.html");
	      return;
	    }

	    // 2) Leer parámetros del formulario
	    String codReservaStr = request.getParameter("codReserva");
	    String diaStr = request.getParameter("dia");
	    String horaStr = request.getParameter("hora");

	    JSONObject resultado = new JSONObject();


	    try {
	      long codReserva = Long.parseLong(codReservaStr);
	      DiaSemana dia = DiaSemana.valueOf(diaStr);   
	      long hora = Long.parseLong(horaStr);
	      
	      // 3) Intentar modificar en el modelo
	      resultado = gestor.modificaReserva(codUsuario, codReserva, dia, hora);
	    } catch (Exception e) {
	      // parámetro inválido -> volvemos al formulario
	      response.sendRedirect(request.getContextPath() + "/formModificarReserva.html");
	      return;
	    }



	    // 4) Enviar a la vista
	    request.setAttribute("resultado", resultado);

	    RequestDispatcher rd = request.getRequestDispatcher("/resultadoModificar.jsp");
	    rd.forward(request, response);
	}

}

