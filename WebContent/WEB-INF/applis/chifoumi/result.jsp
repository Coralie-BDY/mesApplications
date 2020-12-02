<%@page import="fr.applications.appli2.bo.Actions"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Résultat</title>
</head>
<body>
	<%
		int gamer = (int)request.getAttribute("gamer");
		int server = (int)request.getAttribute("server");
		int result = (int)request.getAttribute("result");
	%>
	<h1>Résultat</h1>
	
	<h4>Joueur</h4>
	<%= request.getAttribute("gamer") %>
	<% if (gamer == Actions.CHI) { %>
			<img alt="CHI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/pierre.jpeg"/>
	<% } else if (gamer == Actions.FOU) { %>
			<img alt="FOU" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/feuille.jpg"/>
	<% } else if (gamer == Actions.MI) { %>
			<img alt="MI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/ciseau.jpg"/>
	<% } else {
			out.print("quel est votre choix ?");
		} %>
	
	<h4>Serveur</h4>
	<%= request.getAttribute("server") %>
	<% if (server == Actions.CHI) { %>
			<img alt="CHI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/pierre.jpeg"/>
	<% } else if (server == Actions.FOU) { %>
			<img alt="FOU" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/feuille.jpg"/>
	<% } else if (server == Actions.MI) { %>
			<img alt="MI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/ciseau.jpg"/>
		<% } else {
			out.print("quel est votre choix ?");
		} %>
	
	<h4>Résultat</h4>
	<%=request.getAttribute("result") %>
	<%
		if (result == 0) {
			out.write("Egalite!");
		} else if(result == 1) {
			out.write("Vous avez gagne!!");
		} else {
			out.write("Vous avez perdu!");
		} %>
	<div>
		<a href="<%=request.getContextPath() %>/applis/appli2/ServletGame">Rejouer</a>
	</div>
</body>
</html>



















