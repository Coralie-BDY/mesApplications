<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>style.css" rel="stylesheet">
<meta content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
	<h1>ACCUEIL</h1>
	
	<div class="contenu">
		<a href="<%=request.getContextPath()%>/applis/appli3/ServletAddLunch"><input type="button" value="Nouveau repas"/></a>
	</div>
	<div class="contenu">
		<a href="<%=request.getContextPath()%>/applis/appli3/ServletDisplayLunch"><input type="button" value="Voir repas"/></a>
	</div>
</body>
</html>