package fr.applications.appli2;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.applications.appli2.bo.Actions;

/**
 * Servlet implementation class ServletGame
 */
@WebServlet("/applis/appli2/ServletGame")
public class ServletGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("choice")!=null) {
			processRequest(request,response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/chifoumi/game.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int gamer = Integer.parseInt(request.getParameter("choice"));
		int server = new Random().nextInt(3)+1;
		int result;
		if (gamer==server){
			result = 0;
		}else if ((gamer == Actions.CHI && server == Actions.MI)||(gamer == Actions.FOU && server == Actions.CHI)||
				(gamer == Actions.MI && server == Actions.FOU)){
			result = 1;
		} else {
			result = -1;
		}
		
		request.setAttribute("result", result);
		request.setAttribute("gamer", gamer);
		request.setAttribute("server", server);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/chifoumi/result.jsp");
		if (rd != null){
			rd.forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}	
		
	}

}


