<%@page import="model.Agenda"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%List<Agenda> agendaList = (List<Agenda>) request.getAttribute("lista"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="icon" href="images/icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<h1>Agenda</h1>
		<a href="form" class="butao">Adicionar anotação</a>
		<div class="flex">
		<%
		if(agendaList.isEmpty()){
			out.print("<h3>Ainda não foi adicionado nenhuma anotação</h3>");
		}else{
		for(Agenda agenda : agendaList){
			out.print("<div id='agendaCard'>");
			out.print("<h1 id='titulo'>");
			out.print(agenda.getTitulo());
			out.print("</h1>");
			out.print("<p>");
			out.print(agenda.getConteudo());
			out.print("</p>");
			out.print("<div id='butaoAgenda'>");
			out.print("<a href='put?id=" + agenda.getId() + "' id='update'>Atualizar</a>");
			out.print("<a href='delete?id=" + agenda.getId() + "' id='delete'>Deletar</a>");
			out.print("</div>");
			out.print("</div>");
		}	
		}
		%>
		</div>
	</div>
</body>
</html>