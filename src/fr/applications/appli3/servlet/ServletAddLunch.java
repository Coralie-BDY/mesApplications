package fr.applications.appli3.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.applications.appli3.BusinessException;
import fr.applications.appli3.bll.RepasManager;

/**
 * Servlet implementation class ServletAddLunch
 */
@WebServlet("/applis/appli3/ServletAddLunch")
public class ServletAddLunch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddLunch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/repas/ajout.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate date = null;
		LocalTime hour = null;
		String lunch = null;
		request.setCharacterEncoding("UTF-8");
		List<Integer> listError = new ArrayList<>();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date = LocalDate.parse(request.getParameter("date"),dtf);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listError.add(CodeResultServlet.FORMAT_LUNCH_DATE_ERROR);
		} try {
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");
			hour= LocalTime.parse(request.getParameter("heure"),dtf1);
		} catch(DateTimeParseException e) {
			e.printStackTrace();
			listError.add(CodeResultServlet.FORMAT_LUNCH_HOUR_ERROR);
		}

		lunch = request.getParameter("repas");
		if(lunch == null || lunch.trim().isEmpty()) {
			listError.add(CodeResultServlet.FORMAT_LUNCH_ALIMENT_ERROR);
		}
		
		if(listError.size()>0) {
			request.setAttribute("listError",listError);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/repas/ajout.jsp");
			rd.forward(request, response);
		} else {
			RepasManager repasManager = new RepasManager();
			try {
				repasManager.addLunch(date, hour, Arrays.asList(lunch.split(",")));
				RequestDispatcher rd = request.getRequestDispatcher("/applis/appli3/ServletDisplayLunch");
				rd.forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listError",e.getlistError());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/repas/ajout.jsp");
				rd.forward(request, response);
			}
			
		}
	}

}
