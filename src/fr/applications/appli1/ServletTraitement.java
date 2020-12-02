package fr.applications.appli1;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;





/**
 * Servlet implementation class ServletTraitement
 */

public class ServletTraitement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int numberTrue;
	private boolean win;
	private int mini = 0;
	private int maxi = 10;
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTraitement() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	if (this.getInitParameter("MINI") != null && !this.getInitParameter("MINI").equals("")) {
    		String value = this.getInitParameter("MINI");
    		if (StringUtils.isNumeric(value)) {
    			this.mini = Integer.parseInt(value);
    		}
    	}
    	if (this.getInitParameter("MAXI") != null && !this.getInitParameter("MAXI").equals("")) {
    		String value = this.getInitParameter("MAXI");
    		if (StringUtils.isNumeric(value)) {
    			this.maxi = Integer.parseInt(value);
    		}
    	}
    	this.numberTrue = new Random().nextInt(this.maxi - this.mini + 1) + this.mini;
    	this.win = false;
    	System.out.println(this.numberTrue);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String capture = request.getParameter("number");
		if (capture.equals(String.valueOf(this.numberTrue)) && !this.win) {
			response.sendRedirect("victoire.html");
			this.win = true;
		} else {
			response.sendRedirect("echec.html");
		}
	}

}
