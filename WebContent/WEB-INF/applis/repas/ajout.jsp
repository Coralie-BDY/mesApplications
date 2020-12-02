<%@page import="fr.applications.appli3.messages.messages" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>css/style.css" rel="stylesheet">
<meta content="text/html; charset=UTF-8">
<title>AJOUT</title>
</head>
<body>
<div class="contenu">
	<% List<Integer> listError = (List<Integer>)request.getAttribute("listError");
			if(listError != null) { %>
				<p style="color:red;">Erreur, le repas n'a pas pu être ajouté :</p>
				<% for(int codeErreur:listError) { %>
					<p><%= messages.getMessageError(codeErreur)%></p>
				<%	}
			} %>
	
		<form action="<%=request.getContextPath()%>/applis/appli3/ServletAddLunch" method="post">
			<div class="saisie">
				<label for="date">Date : </label>
				<input type="date" name="date" type="date" value="<%=request.getParameter("date")%>"/>
			</div>
			<div class="saisie">
				<label for="heure">Heure : </label>
				<input type="time" name="heure" value="<%=request.getParameter("heure")%>"/>
			</div>
			<div class="saisie">
				<label for="repas">Repas : </label>
				<textarea rows="5" cols="30" id="repas" name="repas" ><%=request.getParameter("repas")!=null?request.getParameter("repas"):""%></textarea>
			</div>
			
			<div>
				<input type="submit" value="Valider"/>
				<a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
			</div>
		</form>
	
	</div>
</body>
</html>