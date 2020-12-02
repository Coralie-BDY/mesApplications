package fr.applications.appli3.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.applications.appli3.BusinessException;
import fr.applications.appli3.bll.RepasManager;
import fr.applications.appli3.bo.Repas;

/**
 * Servlet implementation class ServletDisplayLunch
 */
@WebServlet("/applis/appli3/ServletDisplayLunch")
public class ServletDisplayLunch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDisplayLunch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate dateFiltre=null;
		List<Integer> listeCodesErreur=new ArrayList<>();
		if(request.getParameter("dateFiltre")!=null && !request.getParameter("dateFiltre").equals("")) {
			try	{
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dateFiltre = LocalDate.parse(request.getParameter("dateFiltre"),dtf);
			} catch(DateTimeParseException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodeResultServlet.FORMAT_LUNCH_DATE_ERROR);
			}
		}
		
		if(listeCodesErreur.size()>0){
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		} else{
			try {
				RepasManager repasManager = new RepasManager();
				List<Repas> listeRepas=null;
				if(dateFiltre==null) {
					listeRepas= repasManager.allLunchesSelected();
				} else {
					listeRepas = repasManager. oneDayLunches(dateFiltre);
				}
				request.setAttribute("lunchList", listeRepas);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getlistError());
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/applis/repas/consultation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
