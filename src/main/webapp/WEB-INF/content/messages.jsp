<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Liste des messages"); %>
<%@ include file="../template/head.jsp" %>
<h1>Les messages du livre d'or</h1>
<%@ include file="../template/errors.jsp" %>

<% if (request.getAttribute("messages") == null || ((List<MessageDor>) request.getAttribute("messages")).size() == 0) {%>
	Aucun message trouvé, essayez d'en créer un.
<% } else { %>
<table>
	<thead>
		<tr>
			<th>#NumMsg</th>
			<th>Pseudo</th>
			<th>Message</th>
		</tr>
	</thead>
	<tbody>
		<% for (MessageDor messageDor : (List<MessageDor>) request.getAttribute("messages")) {%>
			<tr>
				<td><%= messageDor.getNumMsg() %></td>
				<td><%= messageDor.getPseudo() %></td>
				<td><%= messageDor.getMessage() %></td>
			</tr>
		<% } %>
		</tbody>
</table>
<% } %>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../template/tail.jsp" %>