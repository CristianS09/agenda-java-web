<%@page import="model.Agenda"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%Agenda agenda = (Agenda) request.getAttribute("agenda"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualizar anotação</title>
<link rel="icon" href="images/icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Anotação</h1>
	<form action="update">
		<table>
			<tr>
				<td><input type="text" name="id" class="box" value="<%=agenda.getId()%>" hidden required></td>
			</tr>
			<tr>
				<td><input type="text" name="titulo" class="box" placeholder="Insira o titulo" value="<%=agenda.getTitulo()%>" required></td>
			</tr>
			
			<tr>
				<td><input type="text" name="conteudo" class="box" placeholder="Insira o conteúdo" value="<%=agenda.getConteudo()%>" required></td>
			</tr>
		</table>
		<input type="submit" class="butao" value="Enviar">
	</form>
</body>
</html>