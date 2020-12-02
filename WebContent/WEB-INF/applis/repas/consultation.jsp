<%@page import="fr.applications.appli3.bo.Aliment"%>
<%@page import="fr.applications.appli3.bo.Repas"%>
<%@page import="fr.applications.appli3.messages.messages"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>css/style.css" rel="stylesheet">
<meta content="text/html; charset=UTF-8">
<title>CONSULTATION</title>
</head>
<body>
<h1>HISTORIQUE</h1>
	<div class="contenu">
		<%
		String dateFiltre="";
		if(request.getParameter("dateFiltre")!=null)
		{
			dateFiltre=request.getParameter("dateFiltre");
		}
		%>
		
		<form action="<%=request.getContextPath()%>/applis/appli3/ServletDisplayLunch" method="post">
			<input type="date" name="dateFiltre" value="<%=dateFiltre%>"/>
			<input type="submit" value="Filtrer"/>
			<a href="<%=request.getContextPath()%>/applis/appli3/ServletDisplayLunch"><input type="button" value="Réinitialiser"/></a>
		</form>
	
		<%
			List<Integer> listError = (List<Integer>)request.getAttribute("listError");
			if(listError!=null) {
		%>
				<p style="color:red;">Erreur :</p>
		<%
				for(int codeErreur:listError)
				{
		%>
					<p><%=messages.getMessageError(codeErreur)%></p>
		<%	
				}
			}
		%>
	
		<table align="center">
			<thead>
				<tr>
					<td>Date</td>
					<td>Heure</td>
					<td>Action</td>
				</tr>
			</thead>
				<%
					List<Repas> listeRepas = (List<Repas>) request.getAttribute("listeRepas");
					if(listeRepas!=null && listeRepas.size()>0)
					{
				%>
						<tbody>
							<%
							for(Repas repas : listeRepas)
							{
							%>
								<tr>
									<td><%=repas.getDate()%></td>
									<td><%=repas.getHour()%></td>
									
									<td><a href="<%=request.getContextPath()%>/applis/appli3/ServletDisplayLunch?detail=<%=repas.getId()%>&<%=dateFiltre%>">détail</a></td>
								</tr>
							<%
								if(String.valueOf(repas.getId()).equals(request.getParameter("detail")))
								{
							%>
									<tr>
										<td colspan="3">
											<ul>
												<%
												for(Aliment aliment:repas.getAlimentsList())
												{
												%>
													<li><%=aliment.getName()%></li>
												<%
												}
												%>
											</ul>
										</td>
									</tr>
							<%
								}
							}
							%>
						</tbody>
				<%
					}
					else
					{
				%>
					<p>Il n'y a aucun repas à afficher<P>
				<%
					}
				%>
	
			
			
		</table>
	
		<a href="<%=request.getContextPath()%>/applis/appli3/ServletAddLunch"><input type="button" value="Ajouter un nouveau repas"/></a>
		<a href="<%=request.getContextPath()%>"><input type="button" value="Retour à l'accueil"/></a>
		
	</div>
	
</body>
</html>
