<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Sélection des messages à supprimer"); %>
<%@ include file="../../template/head.jsp" %>
<h1>Sélection du ou des message(s) à supprimer</h1>
<%@ include file="../../template/errors.jsp" %>

<% if (request.getAttribute("messages") == null || ((List<MessageDor>) request.getAttribute("messages")).size() == 0) {%>
	Aucun message trouvé, essayez d'en créer un.
<% } else { %>
<form action="" method="post">
	
	<table>
		<thead>
			<tr>
				<th>Choisir</th>
				<th>#NumMsg</th>
				<th>Pseudo</th>
				<th>Message</th>
			</tr>
		</thead>
		<tbody>
			<% for (MessageDor messageDor : (List<MessageDor>) request.getAttribute("messages")) {%>
				<tr>
					<td><input type="checkbox" name="num_msg[]" value="<%= messageDor.getNumMsg() %>" /></td>
					<td><%= messageDor.getNumMsg() %></td>
					<td><%= messageDor.getPseudo() %></td>
					<td><%= messageDor.getMessage() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>

	<button type="submit">Supprimer</button>
</form>

<% } %>

<div>
	<a href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>