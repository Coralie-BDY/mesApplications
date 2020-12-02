<%@ page import="fr.applications.appli2.bo.Actions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" content="text/html">
		<title>CHI-FOU-MI</title>
	</head>
<body>

	<h1>JOUEZ AU CHI-FOU-MI ! </h1>
	
	<a href="<%= request.getContextPath() %>/applis/appli2/ServletGame ? choix= <%= Actions.CHI %>">
		<img alt="CHI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/pierre.jpeg"/>
	</a>
	<a href="<%= request.getContextPath() %> /applis/appli2/ServletGame ? choix= <%= Actions.FOU %>">
		<img alt="FOU" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/feuille.jpg"/>
	</a>
	<a href="<%= request.getContextPath() %>/applis/appli2/ServletGame ? choix= <%= Actions.MI %>">
		<img alt="MI" width="50" src="<%= request.getContextPath() %>/applis/appli2/images/ciseau.jpg"/>
	</a>
	
	<h1>JOUEZ AU CHI-FOU-MI ! </h1>
		<form action="<%=request.getContextPath()%>/applis/appli2/ServletGame" method="post">
		<ul>
			<li>
				<label for="choice <%=Actions.CHI%>">
					<img alt="CHI" width="50" src="<%=request.getContextPath()%>/applis/appli2/images/pierre.jpeg"/>
				</label>
				<input type="radio" id="choice <%=Actions.CHI%>" name="choice" value="<%=Actions.CHI%>"/>
			</li>
			<li>
				<label for="choice <%=Actions.FOU%>">
					<img alt="FOU" width="50" src="<%=request.getContextPath()%>/applis/appli2/images/feuille.jpg"/></label>
				<input type="radio"  id="choice <%=Actions.FOU%>" name="choice" value="<%=Actions.FOU%>"/>
			</li>
			<li>
				<label for="choice <%=Actions.MI%>">
					<img alt="MI" width="50" src="<%=request.getContextPath()%>/applis/appli2/images/ciseau.jpg"/></label>
				<input type="radio" id="choice <%=Actions.MI%>" name="choice" value="<%=Actions.MI%>"/>
			</li>
		</ul>
		<input type="submit" value="Valider">
	</form>
</body>


</html>
	
	
